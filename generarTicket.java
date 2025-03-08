package luner24022025;

import javax.print.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class generarTicket {

    public String generarTickett() {
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("        Tienda do Choppy        \n");
        sb.append("================================\n\n");

        List<String> lineasTicket = leerArchivoTicket("ventaticket.txt");
        for (String linea : lineasTicket) {
            sb.append(dividirLinea(linea, 32)); 
            sb.append("\n");
        }

        sb.append("================================\n");
        sb.append("       ¡Gracias por su compra!  \n\n\n\n");
        return sb.toString();
    }

    private List<String> leerArchivoTicket(String fileName) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo del ticket: " + e.getMessage());
        }
        return lineas;
    }

    public void imprimirTicket() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service == null) {
            System.out.println("No se encontró una impresora.");
            return;
        }

        String ticket = generarTickett();

        try {
            DocPrintJob job = service.createPrintJob();
            byte[] bytes = ticket.getBytes(StandardCharsets.UTF_8);
            Doc doc = new SimpleDoc(bytes, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
            job.print(doc, null);
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
}
