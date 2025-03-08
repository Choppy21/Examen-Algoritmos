package luner24022025;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 324, 383);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaptionBorder);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewButton = new JButton("Agregar producto");
        btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnNewButton.setBounds(58, 39, 207, 71);
        contentPane.add(btnNewButton);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Agregar_productos productMenu = new Agregar_productos();
                productMenu.setVisible(true);
                dispose();
            }
        });
        
        JButton btnNuevosProductos = new JButton("Venta");
        btnNuevosProductos.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnNuevosProductos.setBounds(58, 150, 207, 71);
        contentPane.add(btnNuevosProductos);
        
        btnNuevosProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Venta venta = new Venta();
                venta.setVisible(true);
                dispose();
            }
        });
        
        JButton btnVenta = new JButton("Cantidad venta");
        btnVenta.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnVenta.setBounds(58, 256, 207, 71);
        contentPane.add(btnVenta);
        
        btnVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReporteVentas reporteVentas = new ReporteVentas();
                reporteVentas.setVisible(true);
                dispose();
            }
        });
    }
}
