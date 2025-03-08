package luner24022025;

import java.io.*;
import java.util.*;

public class Productos {
    private Map<String, Producto> productos;
    private final String FILE_NAME = "productos.txt";

    public Productos() {
        productos = new HashMap<>();
        loadProductsFromFile();
    }

    public void addProduct(String id, String name, double price, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del producto no puede estar vacío.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        if (productos.containsKey(id)) {
            throw new IllegalArgumentException("El producto con ID " + id + " ya existe.");
        }
        productos.put(id, new Producto(id, name, price, stock));
        saveProductsToFile();
    }

    public int getProductStockByID(String productId) {
        Producto p = productos.get(productId);
        return p != null ? p.getStock() : -1;
    }

    public double getProductPriceByID(String productId) {
        Producto p = productos.get(productId);
        return p != null ? p.getPrice() : -1;
    }

    public boolean updateStock(String productId, int cantidadVendida) {
        Producto p = productos.get(productId);
        if (p != null) {
            int stockActual = p.getStock();
            if (stockActual >= cantidadVendida) {
                p.setStock(stockActual - cantidadVendida);
                saveProductsToFile();
                return true;
            }
        }
        return false;
    }

    public void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Producto p : productos.values()) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getStock());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los productos en el archivo: " + e.getMessage());
        }
    }

    public void loadProductsFromFile() {
        productos.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No se encontró el archivo de productos. Se creará uno nuevo.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    productos.put(id, new Producto(id, name, price, stock)); 
            }
           }
          } catch (IOException e) {
            System.err.println("Error al leer el archivo de productos: " + e.getMessage());
        }
    }

    public String getAllProducts() {
        StringBuilder sb = new StringBuilder();
        for (Producto p : productos.values()) { 
            sb.append(String.format("ID: %s, Nombre: %s, Precio: %.2f, Stock: %d%n",
                    p.getId(), p.getName(), p.getPrice(), p.getStock()));
        }
        return sb.toString();
    }
}