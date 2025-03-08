package luner24022025;

import java.io.*;
import java.util.*;

public class Ventas {
    private static final String FILE_NAME = "ventas.txt";
    private static final String TICKET_FILE_NAME = "ventaticket.txt";
    private List<Ventaa> ventas;

    public Ventas() {
        ventas = new ArrayList<>();
        cargarVentasDesdeArchivo(); 
    }

    public String obtenerTodasLasVentas() {
        StringBuilder report = new StringBuilder();
        for (Ventaa venta : ventas) {
            report.append("Producto: ").append(venta.getProducto())
                  .append(", Cantidad: ").append(venta.getCantidad())
                  .append(", Total: ").append(venta.getTotal())
                  .append("\n");
        }
        return report.toString();
    }

    public String obtenerProductoMasVendido() {
        if (ventas.isEmpty()) {
            return "No hay ventas registradas.";
        }

        String productoMasVendido = ventas.get(0).getProducto();
        int cantidadMaxima = ventas.get(0).getCantidad();

        for (Ventaa venta : ventas) {
            if (venta.getCantidad() > cantidadMaxima) {
                productoMasVendido = venta.getProducto();
                cantidadMaxima = venta.getCantidad();
            }
        }

        return "Producto más vendido: " + productoMasVendido + " (Cantidad: " + cantidadMaxima + ")";
    }

    private void cargarVentasDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String producto = parts[0];
                    int cantidad = Integer.parseInt(parts[1]);
                    double total = Double.parseDouble(parts[2]);
                    ventas.add(new Ventaa(producto, cantidad, total));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las ventas: " + e.getMessage());
        }
    }

    public boolean registrarVenta(String productId, int quantity, double total) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(writer)) {
            pw.println(productId + "," + quantity + "," + total);
            ventas.add(new Ventaa(productId, quantity, total)); 
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar la venta: " + e.getMessage());
            return false; 
        }
    }

    public void saveVentaToTicket(String productId, int quantity, double total) {
        try (FileWriter writer = new FileWriter(TICKET_FILE_NAME, true);
             PrintWriter pw = new PrintWriter(writer)) {
            pw.println("Producto: " + productId + ", Cantidad: " + quantity + ", Total: " + total);
        } catch (IOException e) {
            System.err.println("Error al guardar la venta en el ticket: " + e.getMessage());
        }
    }

    public int obtenerTotalProductosVendidos() {
        int totalProductos = 0;
        for (Ventaa venta : ventas) {
            totalProductos += venta.getCantidad();
        }
        return totalProductos;
    }

    public double obtenerTotalDineroRecaudado() {
        double totalDinero = 0.0;
        for (Ventaa venta : ventas) {
            totalDinero += venta.getTotal();
        }
        return totalDinero;
    }
}