
package charquitec.Codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestionadorProducto {

    

    final int MAX = 50; 

    int numDato=0;
    public  Producto [] unProducto = new Producto[MAX];
        String file="DataProductos.xml";
    public GestionadorProducto(){
        
    }
    
    public void registroProducto(String nombre, String ID, float precio, int cantidad){
        if(numDato < MAX){
            Producto ObjDato = new Producto(nombre  ,ID,precio,cantidad);
            GuardarProducto(ObjDato.toStringXML(),"DataProductos");
            this.unProducto[numDato]=ObjDato;
            numDato = numDato+1;
        }else{
            System.out.println("Limite de productos sobrepasado--");
        }
    }
    public void GuardarProducto(String StringXML,String NombreArchivo){
       PersistenciaXML Data = new PersistenciaXML(NombreArchivo+".xml");
       Data.EscribirLineaXML(StringXML);
       
    }
    public void LeerDatosXML(){     //Lee el archivo xml y lo guarda en clases como el metodo registroProducto() pero solo al iniciar el programa
        this.numDato = 0;
        PersistenciaXML Data = new PersistenciaXML("DataProductos.xml");
        System.out.println("lee");
        List<String> ProductosLeidos = Data.LeerArchivoXML();

        int tamano = ProductosLeidos.size();     //Obtener el largo del List<String>
        System.out.println("el tamaño es de "+tamano);
        
        String ProductoLeido ; 
        if (tamano <+this.MAX){                               //Solo si no sobrepasa el maximo  se procede a crear los objetos tipo Producto y agregarlos al Lista de objetos
            for (int i = 0; i < tamano; i++){
               ProductoLeido = ProductosLeidos.get(i);
               String[] DataProducto = ProductoLeido.split(";");
               Producto ObjDato = new Producto(DataProducto[0] ,DataProducto[1],Float.parseFloat(DataProducto[2]),Integer.parseInt(DataProducto[3]));
               this.unProducto[numDato]=ObjDato;
               numDato = numDato+1;        

           }           
        }else{
            System.out.println("Limite de ");
        }
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


    public void EscribirDatosXML(String cadena){
        this.numDato = 0;
        PersistenciaXML Data = new PersistenciaXML("DataProductos.xml");
        Data.EscribirLineaXML(cadena);
    }

    //comentario ESTO DEBERIAS HACERLO CON UN GETTER
    public int cantidadProductos(){   
        return numDato;
    }
    public void eliminarProducto(String codigo){
        for(int i = 0; i < numDato; i++) {
            if (unProducto[i].getID().equals(codigo)) {
                // Mover los elementos restantes una posición hacia atrás
                for(int j = i; j < numDato - 1; j++) {
                    unProducto[j] = unProducto[j + 1];
                }
                unProducto[numDato - 1] = null; // Asignar null al último elemento para evitar duplicados
                numDato--;
            }
        }
    }  
    public int ObtenerCantidad(String codigo) {
        int cantidad = 0;
        for (int i = 0; i < numDato; i++) {  // Cambiado a i < numDato
            if (codigo.equals(unProducto[i].getID())) {
                cantidad = unProducto[i].getCantidad();
                return cantidad; // Retorno temprano
            }
        }
        return cantidad; // Retornar cantidad por defecto (0) si no se encuentra el producto
    }

    public int getnumDato(){
        return this.numDato;
    }
    public Producto getProducto(int i){
        return this.unProducto[i];
    }
    
}
