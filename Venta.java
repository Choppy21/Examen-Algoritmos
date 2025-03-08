package luner24022025;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Venta extends JFrame {
    private JTextField txtID, txtQuantity;
    private JTextArea txtAreaVentas;
    private JButton btnSell, btnBack, btnPrintTicket;
    private Ventas ventasManager;
    private Productos productManager;
    private GenerarTicket ticketPrinter;

    public Venta() {
        ventasManager = new Ventas();
        productManager = new Productos();
        ticketPrinter = new GenerarTicket(); // Instancia de GenerarTicket

        setTitle("Registrar Venta");
        setSize(671, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(SystemColor.activeCaptionBorder);
        getContentPane().setLayout(null);

        JLabel lblID = new JLabel("ID del Producto:");
        lblID.setBounds(20, 30, 150, 25);
        lblID.setFont(new Font("Arial Black", Font.BOLD, 14));
        getContentPane().add(lblID);

        txtID = new JTextField();
        txtID.setBounds(180, 30, 180, 25);
        getContentPane().add(txtID);

        JLabel lblQuantity = new JLabel("Cantidad:");
        lblQuantity.setBounds(20, 80, 150, 25);
        lblQuantity.setFont(new Font("Arial Black", Font.BOLD, 14));
        getContentPane().add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(180, 80, 180, 25);
        getContentPane().add(txtQuantity);

        btnSell = new JButton("Registrar Venta");
        btnSell.setBounds(20, 150, 170, 40);
        btnSell.setFont(new Font("Arial Black", Font.BOLD, 14));
        getContentPane().add(btnSell);

        btnBack = new JButton("Regresar al Menú");
        btnBack.setBounds(200, 150, 180, 40);
        btnBack.setFont(new Font("Arial Black", Font.BOLD, 14));
        getContentPane().add(btnBack);

        btnPrintTicket = new JButton("Imprimir Ticket");
        btnPrintTicket.setBounds(110, 200, 180, 40);
        btnPrintTicket.setFont(new Font("Arial Black", Font.BOLD, 14));
        getContentPane().add(btnPrintTicket);

        txtAreaVentas = new JTextArea();
        txtAreaVentas.setBounds(384, 33, 261, 280);
        txtAreaVentas.setEditable(false);
        txtAreaVentas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        getContentPane().add(txtAreaVentas);

        btnSell.addActionListener(e -> registrarVenta());
        btnBack.addActionListener(e -> {
            borrarDatosTicket();
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });

        // Llama a la impresión real del ticket
        btnPrintTicket.addActionListener(e -> ticketPrinter.imprimirTicket());

        actualizarVentasDesdeTicket();
        setVisible(true);
    }

    private void registrarVenta() {
        String productId = txtID.getText().trim();
        String quantityText = txtQuantity.getText().trim();

        if (productId.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int stockDisponible = productManager.getProductStockByID(productId);
            if (stockDisponible == -1) {
                JOptionPane.showMessageDialog(this, "El producto con ID " + productId + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (quantity > stockDisponible) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock disponible. Stock actual: " + stockDisponible, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = productManager.getProductPriceByID(productId) * quantity;
            if (ventasManager.registrarVenta(productId, quantity, total)) {
                productManager.updateStock(productId, quantity);
                ventasManager.saveVentaToTicket(productId, quantity, total);
                actualizarVentasDesdeTicket();
                JOptionPane.showMessageDialog(this, "Venta registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtID.setText("");
                txtQuantity.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarVentasDesdeTicket() {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("ventaticket.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contenido.append(line).append("\n");
            }
        } catch (IOException e) {
            contenido.append("No hay ventas registradas en el ticket.");
        }
        txtAreaVentas.setText(contenido.toString());
    }

    private void borrarDatosTicket() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("ventaticket.txt"))) {
            pw.print("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al borrar los datos del ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        txtAreaVentas.setText("");
    }
}
