/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charquitec.Codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestionadorAdministrador {
    private final String usuario = "wonder";
    private final String contrasena = "123";
    ArrayList<Vendedor>arregloVendedor = new ArrayList<>();
    ArrayList<Producto>arregloProducto = new ArrayList<>();
    public GestionadorAdministrador() {
       
    }
    public boolean VerificarUsuario(String usu, String contra){
        boolean vertificar = false;
        if(this.usuario.equals(usu)){
            if(this.contrasena.equals(contra)){
                vertificar = true;                
            }
        }
        return vertificar;
    }

    
    public void RegistrarVendedor(Vendedor unvendedor){
        arregloVendedor.add(unvendedor);
        
    }
    public void RegistrarProducto(Producto unproducto){
        arregloProducto.add(unproducto);
        
    }
}
