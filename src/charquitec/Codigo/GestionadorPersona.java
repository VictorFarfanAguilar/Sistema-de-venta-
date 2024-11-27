
package charquitec.Codigo;

import java.util.List;


public abstract class GestionadorPersona {
    final int MAX = 50; 
    int numDato=0;
    public  Persona [] unPersona;
    
    public abstract void Registrar(String nombre,String apellido, String codigo);
    public abstract void LeerDatosXML();
    
    public void GuardarDatoXML(String StringXML,String NombreArchivo){
       PersistenciaXML Data = new PersistenciaXML(NombreArchivo+".xml");
       Data.EscribirLineaXML(StringXML);     
    }

    public int cantidadPersona(){   
        return this.numDato;
    }
    public void Eliminar(String codigo){
        for(int i = 0; i < numDato; i++) {
            if (unPersona[i].getCodigo().equals(codigo)) {
                // Mover los elementos restantes una posición hacia atrás
                for(int j = i; j < numDato - 1; j++) {
                    unPersona[j] = unPersona[j + 1];
                }
                unPersona[numDato - 1] = null; // Asignar null al último elemento para evitar duplicados
                numDato--;
            }
        }
    }
    public Persona getPersona(int i){
        return this.unPersona[i];
    }
}
