package charquitec.Codigo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private List<Producto> compras;
    public Cliente(String nombre,String apellido, String codigo) {
        super(nombre,apellido, codigo);
        this.compras = new ArrayList<>();
    }

    public List<Producto> getCompras() {
        return compras;
    }

    public void setCompras(List<Producto> compras) {
        this.compras = compras;
    }
    
    @Override
    public String toString() {
        return "Cliente:"+getNombre()+"\nApellido:"+getApellido()+"\nCodigo:"+getCodigo();
    }
    
    
}
