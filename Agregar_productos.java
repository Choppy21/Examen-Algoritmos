package luner24022025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agregar_productos extends JFrame {
    private JTextField txtID, txtName, txtPrice, txtStock;
    private JTextArea txtProducts;
    private Productos productFiles;

    public Agregar_productos() {
        getContentPane().setBackground(SystemColor.activeCaptionBorder);
        setSize(687, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        productFiles = new Productos();

        JLabel lblID = new JLabel("ID:");
        lblID.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblID.setBounds(10, 9, 33, 46);
        getContentPane().add(lblID);

        txtID = new JTextField(10);
        txtID.setBounds(47, 26, 154, 20);
        getContentPane().add(txtID);

        JLabel lblName = new JLabel("Nombre:");
        lblName.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblName.setBounds(10, 66, 120, 29);
        getContentPane().add(lblName);

        txtName = new JTextField(15);
        txtName.setBounds(140, 74, 126, 20);
        getContentPane().add(txtName);

        JLabel lblPrice = new JLabel("Precio:");
        lblPrice.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblPrice.setBounds(10, 122, 86, 29);
        getContentPane().add(lblPrice);

        txtPrice = new JTextField(10);
        txtPrice.setBounds(95, 130, 86, 20);
        getContentPane().add(txtPrice);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblStock.setBounds(10, 174, 86, 29);
        getContentPane().add(lblStock);

        txtStock = new JTextField(10);
        txtStock.setBounds(95, 182, 86, 20);
        getContentPane().add(txtStock);

        JButton btnAdd = new JButton("Agregar");
        btnAdd.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnAdd.setBounds(36, 241, 145, 46);
        getContentPane().add(btnAdd);

        JButton btnShow = new JButton("Mostrar Productos");
        btnShow.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnShow.setBounds(375, 284, 244, 46);
        getContentPane().add(btnShow);

        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnRegresar.setBounds(36, 300, 220, 46);
        getContentPane().add(btnRegresar);

        txtProducts = new JTextArea();
        txtProducts.setBounds(316, 9, 333, 245);
        txtProducts.setEditable(false);
        getContentPane().add(txtProducts);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setBounds(266, 0, 33, 361);
        getContentPane().add(canvas);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtID.getText();
                String name = txtName.getText();
                double price;
                int stock;

                try {
                    price = Double.parseDouble(txtPrice.getText());
                    stock = Integer.parseInt(txtStock.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para Precio y Stock.");
                    return;
                }

                if (id.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.");
                    return;
                }

                productFiles.addProduct(id, name, price, stock);
                txtID.setText("");
                txtName.setText("");
                txtPrice.setText("");
                txtStock.setText("");
            }
        });

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtProducts.setText(productFiles.getAllProducts());
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }
}
