package charquitec.Codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PersistenciaXML {

    private File file; // Referencia al archivo XML

    public PersistenciaXML(String filename) {
        this.file = new File(".\\src\\charquitec\\Data\\"+filename); // Inicializar el archivo con el nombre proporcionado
    }

    public List<String> LeerArchivoXML() {
        List<String> lines = new ArrayList<>(); // Crear una lista para almacenar las líneas del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Abrir el archivo para lectura
            String line;
            while ((line = br.readLine()) != null) { // Leer cada línea del archivo
                lines.add(line); // Agregar la línea a la lista
            }
        } catch (IOException e) { // Manejar excepciones de E/S
            e.printStackTrace(); // Imprimir la traza de la excepción
        }
        return lines; // Devolver la lista de líneas leídas del archivo
    }

    public void EscribirLineaXML(String line) {
        
        try (FileWriter writer = new FileWriter(file, file.exists())) { // Abrir el archivo para escritura (modo de anexión si existe)
            writer.write(line + "\n"); // Escribir la línea de texto seguida de un salto de línea
        } catch (IOException e) { // Manejar excepciones de E/S
            System.out.println("ERROR EN PERSISTENCIAXML");
            e.printStackTrace(); // Imprimir la traza de la excepción
        }
    }

    public void EliminarProductoXML(String productoId) {
        List<String> lines = LeerArchivoXML(); // Leer el archivo XML
        List<String> newLines = new ArrayList<>(); // Lista para las nuevas líneas del archivo

        for (String line : lines) {
            if (!line.contains(productoId)) { // Verificar si es el producto a eliminar
                newLines.add(line); // Agregar la línea si no es el producto a eliminar
            }
        }
    }
    public void EliminarPorID(String idProducto){
        List<String> lines = LeerArchivoXML(); // Leer todas las líneas del archivo
        List<String> nuevasLineas = new ArrayList<>(); // Crear una nueva lista para las líneas que se mantendrán

        for (String line : lines) {
            String[] parts = line.split(";");
            if (!parts[0].equals(idProducto)) { // Si el primer valor (ID) no es igual al ID del producto
                nuevasLineas.add(line); // Agregar la línea a la nueva lista

            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) { // Abrir el archivo para escritura

            for (String newLine : nuevasLineas) {
                bw.write(newLine);
                bw.newLine(); // Escribir cada línea en el archivo
            }
        }catch (IOException e) {
            e.printStackTrace(); // Manejar excepciones de E/S

        }
        // Manejar excepciones de E/S
        // Imprimir la traza de la excepción

    }
     public void ActualizarPorID(String idProducto,String nombre,String precio,String cantidad) {
        List<String> lines = LeerArchivoXML(); // Leer todas las líneas del archivo
        List<String> nuevasLineas = new ArrayList<>(); // Crear una nueva lista para las líneas que se mantendrán

        for (String line : lines) {
            String[] parts = line.split(";");
            if (!parts[0].equals(idProducto)) { // Si el primer valor (ID) no es igual al ID del producto
                nuevasLineas.add(line); // Agregar la línea a la nueva lista
            }
            else{
                nuevasLineas.add(idProducto+";"+nombre+";"+precio+";"+cantidad);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) { // Abrir el archivo para escritura
            for (String line : nuevasLineas) {
                bw.write(line); // Escribir cada línea en el archivo
                bw.newLine(); // Escribir un salto de línea
            }
        } catch (IOException e) { // Manejar excepciones de E/S
            e.printStackTrace(); // Imprimir la traza de la excepción
        }
    }
}

    /**
     * Método principal para probar la funcionalidad de la clase.
     * @param args Argumentos de línea de comandos (no utilizados en este ejemplo).
     
    public static void main(String[] args) {
        PersistenciaXML ArchivoDATA = new PersistenciaXML("charquitec\\Data\\example.xml"); // Crear un PersistenciaXML/ example.xml cambiar por la ruta deseada
        
        
        // Si el archivo existe, leer su contenido existente
        if (ArchivoDATA.file.exists()) {
            List<String> existingLines = ArchivoDATA.LeerArchivoXML();
            for (String line : existingLines) {
                System.out.println("Contenido existente: " + line);
            }
        } else {
            System.out.println("El archivo no existe. Se creará uno nuevo.");
        }
        
        // Escribir algunas líneas en el archivo XML
        ArchivoDATA.EscribirLineaXML("<tag>Primera línea</tag>");
        ArchivoDATA.EscribirLineaXML("<tag>Segunda línea</tag>");
        ArchivoDATA.EscribirLineaXML("<tag>Tercera línea</tag>");

        // Leer y mostrar el contenido del archivo XML
        List<String> lines = ArchivoDATA.LeerArchivoXML();
        for (String line : lines) {
            System.out.println(line); // Imprimir cada línea leída del archivo
        }
    }
    */

/*
    public void registroCliente(String nombre,String apellido, String codigo){
        if(numDato < MAX){
            Cliente ObjDato = new Cliente(nombre ,apellido,codigo);
            GuardarCliente(ObjDato.toStringXML(),"DataClientes");
            this.unCliente[numDato]=ObjDato;
            numDato = numDato+1;
        }else{
            System.out.println("Limite de clientes sobrepasado");
        }
    }
    public void GuardarDatosXML(String StringXML,String NombreArchivo){
       PersistenciaXML Data = new PersistenciaXML("charquitec\\Data\\"+NombreArchivo+".xml");
       Data.EscribirLineaXML(StringXML);
       
    }
    public void LeerDatosXML(String NombreArchivo){     //Lee el archivo xml y lo guarda en clases como el metodo registroCliente() pero solo al iniciar el programa
        this.numDato = 0;
        PersistenciaXML Data = new PersistenciaXML("charquitec\\Data\\"+NombreArchivo+".xml");//Nombre de la ruta del archivo .xml
        List<String> UsuariosLeidos = Data.LeerArchivoXML();  //Guardar cada linea en un espacio del List<String>
        int tamano = UsuariosLeidos.size();                   //Obtener el largo del List<String>
        String UsuarioLeido ; 
        if (tamano < this.MAX){                               //Solo si no sobrepasa el maximo  se procede a crear los objetos tipo cliente y agregarlos al Lista de objetos
            for (int i = 0; i < tamano; i++){
               UsuarioLeido = UsuariosLeidos.get(i);
               String[] DataUsuario = UsuarioLeido.split(";");
               Cliente ObjDato = new Cliente(DataUsuario[1] ,DataUsuario[2],DataUsuario[0]);
               this.unCliente[numDato]=ObjDato;
               numDato = numDato+1;        

           }           
        }else{
            System.out.println("Limite de usuarios sobrepasado");
        }
    }

*/
