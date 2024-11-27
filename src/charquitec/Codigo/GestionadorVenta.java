/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charquitec.Codigo;

/**
 *
 * @author VICTOR
 */
public class GestionadorVenta {
    
    
    final int MAX = 50; 

    int numDato=0;
    public  Venta [] unVenta = new Venta[MAX];
        
    
    
    public void registroVenta(String nombreCliente, String ID,String NombreProducto, String precio, String cantidad,String Total){
        if(numDato < MAX){
            Venta ObjDato = new Venta(nombreCliente  ,ID,NombreProducto,precio,cantidad,Total);
            //GuardarProducto(ObjDato.toStringXML(),"DataProductos");
            this.unVenta[numDato]=ObjDato;
            numDato = numDato+1;
        }else{
            System.out.println("Limite de Ventas sobrepasado--");
        }
    }
    
    public int cantidadVentas(){
        return numDato;
    }
    
    public void eliminarProducto(String codigo){
        for(int i = 0; i < numDato; i++) {
            if (unVenta[i].getIDproducto().equals(codigo)) {
                // Mover los elementos restantes una posición hacia atrás
                for(int j = i; j < numDato - 1; j++) {
                    unVenta[j] = unVenta[j + 1];
                }
                unVenta[numDato - 1] = null; // Asignar null al último elemento para evitar duplicados
                numDato--;
            }
        }
    }  
    
}
