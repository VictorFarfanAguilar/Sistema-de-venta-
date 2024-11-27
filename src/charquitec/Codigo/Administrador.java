package charquitec.Codigo;


public class Administrador extends Persona{

    public Administrador(String nombre,String apellido, String codigo) {
        super(nombre,apellido, codigo);
    }
    @Override
    public String toString() {
        return "Admin:\nNombre:"+getNombre()+"\nApellido:"+getApellido()+"\nCodigo:"+getCodigo();
    } 
   
}
