
package charquitec.Codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GestionadorInventario {
    
    String file="DataProductos.xml";
    
    private PersistenciaXML persistencia;

    public GestionadorInventario(String file) {
        persistencia = new PersistenciaXML(file);
    }

    public List<String> buscarProductosPorID(String IDBuscado) {
        List<String> lineasArchivo = persistencia.LeerArchivoXML();
        List<String> productosEncontrados = buscarProductosRecursivo(lineasArchivo, IDBuscado, 0, lineasArchivo.size() - 1);
        return productosEncontrados;
    }

    private List<String> buscarProductosRecursivo(List<String> lineas, String IDBuscado, int inicio, int fin) {
        List<String> productosEncontrados = new ArrayList<>();

        if (inicio > fin) {
            return productosEncontrados; // Caso base: rango vacío, no hay productos
        }

        int medio = inicio + (fin - inicio) / 2;
        String[] partesMedio = lineas.get(medio).split(";");

        if (partesMedio[0].equals(IDBuscado)) {
            productosEncontrados.add(lineas.get(medio)); // Agregar el producto encontrado
        }

        List<String> resultadosIzquierda = buscarProductosRecursivo(lineas, IDBuscado, inicio, medio - 1);
        List<String> resultadosDerecha = buscarProductosRecursivo(lineas, IDBuscado, medio + 1, fin);

        productosEncontrados.addAll(resultadosIzquierda); // Agregar los resultados de la mitad izquierda
        productosEncontrados.addAll(resultadosDerecha); // Agregar los resultados de la mitad derecha

        return productosEncontrados;
    }

    public String mostrarProductosEncontrados(List<String> productosEncontrados) {
        StringBuilder sb = new StringBuilder();
        if (productosEncontrados.isEmpty()) {
            sb.append("No se encontraron productos con el ID especificado.");
        } else {
            sb.append("Productos encontrados:\n");
            for (String producto : productosEncontrados) {
                sb.append(producto).append("\n");
            }
        }
        return sb.toString();
    }
      
}
