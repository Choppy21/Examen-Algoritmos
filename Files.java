package luner24022025;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class Files {

    public void writeFile(String user, String password) {
        File archivo = new File("usuarios.txt");

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error de creación de archivo.");
                return;
            }
        }

        if (usuarioExiste(user)) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
            return;
        }

        try (FileWriter escribir = new FileWriter(archivo, true)) {
            escribir.write(user + "-->" + password + "\n");
            JOptionPane.showMessageDialog(null, "El usuario fue creado.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    private boolean usuarioExiste(String user) {
        File archivo = new File("usuarios.txt");

        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(user + "-->")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean InicioC(String user, String password) {
        File archivo = new File("usuarios.txt");

        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-->");
                if (parts.length == 2) {
                    String storedUser = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    if (storedUser.equals(user) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}