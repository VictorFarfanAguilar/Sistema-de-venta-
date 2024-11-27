
package charquitec.Codigo;


public class GestionadorProductoCarrito extends GestionadorProducto{
    final int MAX = 50; 
    int numDatoCarrito=0;
    public  Producto [] unProductoCarrito = new Producto[MAX];
    
    public void AgregarAlCarrito(String ID, String nombre, float precio, int cantidad){
        if(numDatoCarrito < MAX){
            Producto ObjDato = new Producto(ID  ,nombre,precio,cantidad);
            //GuardarProducto(ObjDato.toStringXML(),"DataVentas");
            this.unProductoCarrito[numDatoCarrito]=ObjDato;
            numDatoCarrito = numDatoCarrito+1;
        }else{
            System.out.println("Limite de productos sobrepasado--");
        }
    }
    
    public int cantidadproduccarrito(){
        return numDatoCarrito;
    }
}
