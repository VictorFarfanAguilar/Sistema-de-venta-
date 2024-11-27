/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charquitec.Codigo;

/**
 *
 * @author VICTOR
 */
public class Venta {
    
    String nomCliente; 
    String IDproducto;
    String nombreProducto;
    String precioProducto;
    String cantidadProducto;
    String precioTotal;

    public Venta(String nomCliente, String IDproducto, String nombreProducto, String precioProducto, String cantidadProducto,String precioTotal) {
        this.nomCliente = nomCliente;
        this.IDproducto = IDproducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioTotal = precioTotal;
        
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public String getIDproducto() {
        return IDproducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }
    

    
    
    
    
    
    
    
    
    
}
