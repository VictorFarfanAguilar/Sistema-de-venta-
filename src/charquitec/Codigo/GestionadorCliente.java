
package charquitec.Codigo;

import java.util.List;

public class GestionadorCliente extends GestionadorPersona {
    
    public GestionadorCliente(){
        unPersona = new Cliente[MAX];
    }

    @Override
    public void Registrar(String nombre,String apellido, String codigo){
        if(numDato < MAX){
            Cliente ObjDato = new Cliente(nombre ,apellido,codigo);
            GuardarDatoXML(ObjDato.toStringXML(),"DataClientes");
            this.unPersona[numDato]=ObjDato;
            numDato = numDato+1;
        }else{
            System.out.println("Limite de clientes sobrepasado");
        }
    }   
    public int cantidadClientes(){   
        return numDato;
    }
    public void Eliminar1(String codigo){
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
    
    
    @Override
    public void LeerDatosXML(){     //Lee el archivo xml y lo guarda en clases como el metodo registroCliente() pero solo al iniciar el programa
        this.numDato = 0;
        PersistenciaXML Data = new PersistenciaXML("DataClientes.xml");//Nombre de la ruta del archivo .xml
        List<String> UsuariosLeidos = Data.LeerArchivoXML();  //Guardar cada linea en un espacio del List<String>
        int tamano = UsuariosLeidos.size();                   //Obtener el largo del List<String>
        String UsuarioLeido ; 
        if (tamano < this.MAX){                               //Solo si no sobrepasa el maximo  se procede a crear los objetos tipo cliente y agregarlos al Lista de objetos
            for (int i = 0; i < tamano; i++){
               UsuarioLeido = UsuariosLeidos.get(i);
               String[] DataUsuario = UsuarioLeido.split(";");
               Cliente ObjDato = new Cliente(DataUsuario[1] ,DataUsuario[2],DataUsuario[0]);
               this.unPersona[numDato]=ObjDato;
               numDato = numDato+1;        

           }           
        }else{
            System.out.println("Limite de clientes sobrepasado");
        }
    }

}
