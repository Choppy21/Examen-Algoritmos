package luner24022025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReporteVentas extends JFrame {
    private JTextArea txtSalesReport;
    private Ventas salesRecords;

    public ReporteVentas() {
        salesRecords = new Ventas();

        setSize(687, 414);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Reporte de Ventas");
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setBounds(264, 11, 217, 40);
        getContentPane().add(lblTitle);

        txtSalesReport = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtSalesReport);
        scrollPane.setBounds(20, 60, 640, 250);
        getContentPane().add(scrollPane);

        JButton btnShowReport = new JButton("Mostrar Reporte");
        btnShowReport.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnShowReport.setBounds(90, 320, 200, 40);
        getContentPane().add(btnShowReport);

        JButton btnBack = new JButton("Regresar al Menú");
        btnBack.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnBack.setBounds(438, 321, 200, 39);
        getContentPane().add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        });

        btnShowReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String salesReport = salesRecords.obtenerTodasLasVentas();
                    String mostSoldProduct = salesRecords.obtenerProductoMasVendido();
                    txtSalesReport.setText(salesReport + "\n\n" + mostSoldProduct);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}