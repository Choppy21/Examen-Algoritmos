package luner24022025;

public class Ventaa {
    private String producto;
    private int cantidad;
    private double total;

    public Ventaa(String producto, int cantidad, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }
}