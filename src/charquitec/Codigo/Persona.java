
package charquitec.Codigo;


public abstract class Persona {
    public String nombre;
    public String apellido;
    public String codigo;

    public Persona(String nombre,String apellido, String codigo) {
        this.nombre = nombre;
        this.apellido=apellido;
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido(){
        return this.apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCodigo() {
        return this.codigo;
    }   
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public abstract String toString();
            
    public String toStringXML() {
        String sep = ";";
        String StringXML = getCodigo()+sep+getNombre()+sep+getApellido();
        return StringXML;
    }
    
    
}
