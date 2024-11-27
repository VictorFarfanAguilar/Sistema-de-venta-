
package charquitec.Codigo;


public class Producto {
    private String nombre;
    private String ID;
    private float precio;
    private int cantidad;

    public Producto(String ID, String nombre, float precio, int cantidad) {
        this.nombre = nombre;
        this.ID = ID;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public float getPrecio() {
        return this.precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto:\nNombre:" + getNombre() + "\nID:" + getID() + "\nPrecio:" + getPrecio() + "\nCantidad:" + getCantidad() ;
    }
    public String toStringXML() {
        String sep = ";";
        return getID()+sep+getNombre()+sep+getPrecio()+sep+getCantidad();
    }
}
