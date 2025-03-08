package luner24022025;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usuarioI;
    private JPasswordField contraI;
    private JTextField usuarioC;
    private JPasswordField contraC;
    private Files ArchivoUC = new Files();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 566, 366);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaptionBorder);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuario");
        lblNewLabel.setBounds(30, 61, 73, 24);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contentPane.add(lblNewLabel);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(30, 161, 109, 24);
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 20));
        contentPane.add(lblContraseña);

        JLabel lblIniciarSecion = new JLabel("Iniciar sesión");
        lblIniciarSecion.setBackground(Color.WHITE);
        lblIniciarSecion.setBounds(52, 11, 195, 24);
        lblIniciarSecion.setFont(new Font("Arial", Font.BOLD, 25));
        contentPane.add(lblIniciarSecion);

        usuarioI = new JTextField();
        usuarioI.setBounds(30, 96, 138, 20);
        contentPane.add(usuarioI);
        usuarioI.setColumns(10);

        contraI = new JPasswordField();
        contraI.setBounds(30, 196, 138, 20);
        contentPane.add(contraI);

        JButton btnInicio = new JButton("Acceder");
        btnInicio.setBounds(52, 248, 116, 31);
        btnInicio.setFont(new Font("Arial Black", Font.BOLD, 15));
        contentPane.add(btnInicio);

        JLabel lblCrearUsuario = new JLabel("Crear usuario");
        lblCrearUsuario.setFont(new Font("Arial", Font.BOLD, 25));
        lblCrearUsuario.setBackground(Color.WHITE);
        lblCrearUsuario.setBounds(341, 11, 195, 24);
        contentPane.add(lblCrearUsuario);

        JLabel lblNewLabel_1 = new JLabel("Usuario");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel_1.setBounds(325, 61, 73, 24);
        contentPane.add(lblNewLabel_1);

        JLabel lblContrasea_1 = new JLabel("Contraseña");
        lblContrasea_1.setFont(new Font("Arial", Font.BOLD, 20));
        lblContrasea_1.setBounds(325, 161, 109, 24);
        contentPane.add(lblContrasea_1);

        usuarioC = new JTextField();
        usuarioC.setColumns(10);
        usuarioC.setBounds(325, 96, 138, 20);
        contentPane.add(usuarioC);

        contraC = new JPasswordField();
        contraC.setColumns(10);
        contraC.setBounds(325, 196, 138, 20);
        contentPane.add(contraC);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setFont(new Font("Arial Black", Font.BOLD, 15));
        btnCrear.setBounds(373, 248, 105, 31);
        contentPane.add(btnCrear);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setBounds(253, 0, 29, 327);
        contentPane.add(canvas);

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioC.getText();
                String contraseña = new String(contraC.getPassword());

                if (usuario.trim().isEmpty() || contraseña.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El usuario y la contraseña no pueden estar vacíos o contener solo espacios.");
                } else {
                    try {
                        ArchivoUC.writeFile(usuario, contraseña);
                        JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
                        usuarioC.setText("");
                        contraC.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
                    }
                }
            }
        });

        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioI.getText();
                String contraseña = new String(contraI.getPassword()); 

                if (usuario.trim().isEmpty() || contraseña.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El usuario y la contraseña no pueden estar vacíos o contener solo espacios.");
                } else {
                    boolean validLogin = ArchivoUC.InicioC(usuario, contraseña);
                    if (validLogin) {
                        JOptionPane.showMessageDialog(null, "¡Bienvenido!\nPuede seguir.");
                        Menu menu = new Menu();
                        menu.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                }
            }
        });
    }
}