
package charquitec.Codigo;

import java.util.List;

public class GestionadorProductoAlmacen extends GestionadorProducto {
    final int MAX = 10; 
    int numDato=0;
    public  Producto [] unProducto = new Producto[MAX];
    
    public GestionadorProductoAlmacen(){
        
    }
    
    @Override
    public void GuardarProducto(String StringXML,String NombreArchivo){
       PersistenciaXML Data = new PersistenciaXML(NombreArchivo+".xml");
       Data.EscribirLineaXML(StringXML);
       
    }
    public void LeerProductos(){     //Lee el archivo xml y lo guarda en clases como el metodo registroProducto() pero solo al iniciar el programa
        this.numDato = 0;
        PersistenciaXML Data = new PersistenciaXML("DataProductos3.xml");//Nombre de la ruta del archivo .xml
        List<String> ProductosLeidos = Data.LeerArchivoXML();  //Guardar cada linea en un espacio del List<String>
        int tamano = ProductosLeidos.size();                   //Obtener el largo del List<String>
        String ProductoLeido ; 
        if (tamano < this.MAX){                               //Solo si no sobrepasa el maximo  se procede a crear los objetos tipo Producto y agregarlos al Lista de objetos
            for (int i = 0; i < tamano; i++){
               ProductoLeido = ProductosLeidos.get(i);
               String[] DataProducto = ProductoLeido.split(";");
               Producto ObjDato = new Producto(DataProducto[1] ,DataProducto[0],Float.parseFloat(DataProducto[2]),Integer.parseInt(DataProducto[3]));
               this.unProducto[numDato]=ObjDato;
               numDato = numDato+1;        

           }           
        }else{
            System.out.println("Limite de Productos sobrepasado111");
        }
    } 
}

