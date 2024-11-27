
package Vistas;

import charquitec.Codigo.Cliente;
import charquitec.Codigo.GestionadorCliente;
import charquitec.Codigo.GestionadorInventario;
import charquitec.Codigo.GestionadorPersona;
import charquitec.Codigo.GestionadorProducto;
import charquitec.Codigo.GestionadorProductoAlmacen;
import charquitec.Codigo.GestionadorProductoCarrito;
import charquitec.Codigo.GestionadorVendedor;
import charquitec.Codigo.GestionadorVenta;
import charquitec.Codigo.PersistenciaXML;
import charquitec.Codigo.Producto;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Vista_Vendedor extends javax.swing.JPanel {
   
     DefaultTableModel modeloCliente = new DefaultTableModel();
     GestionadorCliente GesCliente =new GestionadorCliente();

    DefaultTableModel modeloProducto = new DefaultTableModel();
    DefaultTableModel modeloCarritoProductos = new DefaultTableModel();
    
    GestionadorProducto GesProduct = new GestionadorProducto();
    
    GestionadorVenta GesVenta = new GestionadorVenta();
        DefaultTableModel modeloVentas = new DefaultTableModel();

   
    GestionadorProductoCarrito GesProductCarrito = new GestionadorProductoCarrito();
    GestionadorInventario Gestor = new GestionadorInventario("DataProductos.xml");
    PersistenciaXML dataProducto=new PersistenciaXML("DataProductos.xml");
    
    public Vista_Vendedor() {
        initComponents();      
        GesProduct.LeerDatosXML();
        GesCliente.LeerDatosXML();
       // Gestor.LeerDatosXML();
        agregarModeloTablaProducto();
        agregarModeloCarritoProducto();
        agregarModeloTablaCliente();
        agregarModeloTablaVentas();
                llenarTablaCliente(GesCliente);
                llenarTablaVentas(GesVenta);

        
        llenarTablaProductos(GesProduct);
        
         
        AgregarProductoCarrito(GesProductCarrito);
        agregarClienteComboBox();
        
        VistaVendedor.setVisible(true);
        Vista_RegistroCliente.setVisible(false);
    }
    
 private void agregarModeloTablaCliente(){
        modeloCliente.addColumn("Codigo");
        modeloCliente.addColumn("Nombre");
        modeloCliente.addColumn("Apellido");
        
        
        
    }
 private void agregarModeloTablaVentas(){
        modeloVentas.addColumn("Cliente");
        modeloVentas.addColumn("IDProd");
        modeloVentas.addColumn("Nombre");
        modeloVentas.addColumn("Precio");
        modeloVentas.addColumn("Cantidad");
        modeloVentas.addColumn("Total");


                
        
        
        
    }
 public void llenarTablaCliente1(GestionadorCliente unCliente){   
        int cantidadDatos = unCliente.cantidadPersona();       
        for( int i=0; i<cantidadDatos; i++){
            if (unCliente.unPersona[i] != null) {
            String nombre = unCliente.unPersona[i].getNombre();
            String apellido = unCliente.unPersona[i].getApellido();

            String codigo = unCliente.unPersona[i].getCodigo();
            String [] listaClientes = {nombre,apellido,codigo};
            modeloProducto.addRow(listaClientes);
        }   
        }
        
 }
        public void llenarTablaCliente(GestionadorCliente GesCliente){
            
            int tope = GesCliente.cantidadPersona();
        for(int i = 0 ; i< tope; i++){
            Object[] fila = {GesCliente.getPersona(i).getCodigo(), GesCliente.getPersona(i).getNombre(),GesCliente.getPersona(i).getApellido()};
            modeloCliente.addRow(fila);
        }
            
        }
        
        public void llenarTablaVentas(GestionadorVenta GesVenta){
            
            int tope = GesVenta.cantidadVentas();
        for(int i = 0 ; i< tope; i++){
            Object[] fila = {GesVenta.unVenta[i].getNomCliente(), GesVenta.unVenta[i].getIDproducto(),GesVenta.unVenta[i].getNombreProducto(),GesVenta.unVenta[i].getPrecioProducto(),GesVenta.unVenta[i].getCantidadProducto(),GesVenta.unVenta[i].getPrecioTotal()};
            modeloVentas.addRow(fila);
        }
            
        }
        
        public void agregarClienteComboBox(){
            
        
        int cantidad = GesCliente.cantidadClientes();
        
        String cliente[] = new String  [cantidad];
        
        for ( int i=0; i<cantidad; i++){
            
            
            comboClienteBox.addItem(GesCliente.unPersona[i].getNombre());
        }
        
        
    }
 

    private void agregarModeloTablaProducto(){
        modeloProducto.addColumn("ID");
        modeloProducto.addColumn("Nombre");
        modeloProducto.addColumn("Precio");
        modeloProducto.addColumn("Cantidad"); 
        
     }
    private void agregarModeloCarritoProducto(){
        modeloCarritoProductos.addColumn("ID");
        modeloCarritoProductos.addColumn("Nombre");
        modeloCarritoProductos.addColumn("Precio");
        modeloCarritoProductos.addColumn("Cantidad"); 
        
     }
    public void actualizarTablaProductos(GestionadorProducto GesProduct) {
        modeloProducto.setRowCount(0); // Limpiar la tabla
        llenarTablaProductos(GesProduct); // Llenar la tabla con los datos actualizados
    }
    public void llenarTablaProductos(GestionadorProducto GesProduct){
        //DATOS
        
        int tope = GesProduct.getnumDato();
        for(int i = 0 ; i< tope; i++){
            Object[] fila = {GesProduct.getProducto(i).getID(), GesProduct.getProducto(i).getNombre(), GesProduct.getProducto(i).getPrecio(),GesProduct.getProducto(i).getCantidad()};
            modeloProducto.addRow(fila);
        }
    }

    public void AgregarProductoCarrito(GestionadorProductoCarrito GesProductCarrito){
int tope = GesProductCarrito.cantidadproduccarrito();
        for(int i = 0 ; i< tope; i++){
        Object[] fila = {GesProductCarrito.unProductoCarrito[i].getID(), GesProductCarrito.unProductoCarrito[i].getNombre(), GesProductCarrito.unProductoCarrito[i].getPrecio(),GesProductCarrito.unProductoCarrito[i].getCantidad()};
        modeloCarritoProductos.addRow(fila);
        }
    }
    public void AgregarProductoCarrito1(GestionadorProductoCarrito GesProductCarrito) {
    // Limpiar la tabla del carrito antes de agregar nuevos elementos
    modeloCarritoProductos.setRowCount(0);

    // Iterar sobre todos los productos en el carrito y agregarlos a la tabla
    for (int i = 0; i < GesProductCarrito.cantidadproduccarrito(); i++) {
        Producto producto = GesProductCarrito.getProducto(i);
        if (producto != null) {
            Object[] fila = {
                producto.getID(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCantidad()
            };
            modeloCarritoProductos.addRow(fila);
        } else {
            System.out.println("Producto en índice " + i + " es null.");
        }
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane5 = new javax.swing.JScrollPane();
        Vista_RegistroCliente = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TNombreCliente = new javax.swing.JTextField();
        TApellidoCliente = new javax.swing.JTextField();
        TDNICliente = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        VistaVendedor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_RegistrarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_EliminarDelCarrito = new javax.swing.JButton();
        btn_AgregarAlCarrito = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListaProductos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblProductosCarrito = new javax.swing.JTable();
        LabelIDCliente = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LabelMonto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TCantidadProducto = new javax.swing.JTextField();
        btn_Finalizar = new javax.swing.JButton();
        btn_Inventario = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboClienteBox = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        RealizarVenta = new javax.swing.JButton();
        VistaInventario = new javax.swing.JPanel();
        btn_regresar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tbla_Inventario = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        product_code = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        A1 = new javax.swing.JTextArea();
        btn_buscar_dyv = new javax.swing.JButton();
        VistaVentas = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 204));
        setLayout(new java.awt.CardLayout());

        Vista_RegistroCliente.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Codigo");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre");

        TNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNombreClienteActionPerformed(evt);
            }
        });

        jButton4.setText("Registrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Apellido");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblClientes.setForeground(new java.awt.Color(0, 153, 255));
        tblClientes.setModel(modeloCliente);
        jScrollPane6.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 255));
        jLabel4.setText("Complete la informacion de sus clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton1.setText("Actualizar");

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cliente");

        jButton6.setText("Volver");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Vista_RegistroClienteLayout = new javax.swing.GroupLayout(Vista_RegistroCliente);
        Vista_RegistroCliente.setLayout(Vista_RegistroClienteLayout);
        Vista_RegistroClienteLayout.setHorizontalGroup(
            Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(39, 39, 39)
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TApellidoCliente)
                            .addComponent(TNombreCliente)
                            .addComponent(TDNICliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Vista_RegistroClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(190, 190, 190))
        );
        Vista_RegistroClienteLayout.setVerticalGroup(
            Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Vista_RegistroClienteLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(32, 32, 32)
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(67, 67, 67)
                        .addGroup(Vista_RegistroClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(Vista_RegistroCliente, "card3");

        VistaVendedor.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("ID Cliente");

        jLabel2.setText("Carrito del cliente");

        btn_RegistrarCliente.setText("Registrar Cliente");
        btn_RegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Lista de Productos");

        btn_EliminarDelCarrito.setText("Eliminar");
        btn_EliminarDelCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarDelCarritoActionPerformed(evt);
            }
        });

        btn_AgregarAlCarrito.setText("Agregar");
        btn_AgregarAlCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarAlCarritoActionPerformed(evt);
            }
        });

        TblListaProductos.setModel(modeloProducto);
        jScrollPane1.setViewportView(TblListaProductos);

        TblProductosCarrito.setModel(modeloCarritoProductos);
        jScrollPane2.setViewportView(TblProductosCarrito);

        LabelIDCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Monto");

        LabelMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Digite la cantidad del producto");

        btn_Finalizar.setText("Finalizar");
        btn_Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FinalizarActionPerformed(evt);
            }
        });

        btn_Inventario.setText("Gestionar inventario");
        btn_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InventarioActionPerformed(evt);
            }
        });

        jLabel7.setText("Cliente");

        jButton3.setText("Ventas ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        RealizarVenta.setText("Realizar Venta");
        RealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VistaVendedorLayout = new javax.swing.GroupLayout(VistaVendedor);
        VistaVendedor.setLayout(VistaVendedorLayout);
        VistaVendedorLayout.setHorizontalGroup(
            VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaVendedorLayout.createSequentialGroup()
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(VistaVendedorLayout.createSequentialGroup()
                                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboClienteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LabelIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaVendedorLayout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jLabel3))
                            .addGroup(VistaVendedorLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btn_RegistrarCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Inventario)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))))
                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaVendedorLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(LabelMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(23, 23, 23))
                                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                                        .addComponent(RealizarVenta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_AgregarAlCarrito)
                                        .addGap(31, 31, 31)
                                        .addComponent(btn_EliminarDelCarrito)
                                        .addGap(35, 35, 35)))
                                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_Finalizar)
                                    .addComponent(TCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(VistaVendedorLayout.createSequentialGroup()
                                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9))
                                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 25, Short.MAX_VALUE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        VistaVendedorLayout.setVerticalGroup(
            VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaVendedorLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelIDCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btn_RegistrarCliente)
                        .addComponent(btn_Inventario)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboClienteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(VistaVendedorLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(VistaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RealizarVenta)
                            .addComponent(btn_AgregarAlCarrito)
                            .addComponent(btn_EliminarDelCarrito)
                            .addComponent(btn_Finalizar))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        add(VistaVendedor, "card2");

        VistaInventario.setBackground(new java.awt.Color(204, 255, 204));

        btn_regresar.setText("Aceptar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel10.setText("Inventario CharquiTEC");

        Tbla_Inventario.setModel(modeloProducto);
        jScrollPane4.setViewportView(Tbla_Inventario);

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 2, 15)); // NOI18N
        jLabel11.setText("Ingrese ID del producto:");

        A1.setColumns(20);
        A1.setRows(5);
        jScrollPane3.setViewportView(A1);

        btn_buscar_dyv.setText("Buscar");
        btn_buscar_dyv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_dyvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VistaInventarioLayout = new javax.swing.GroupLayout(VistaInventario);
        VistaInventario.setLayout(VistaInventarioLayout);
        VistaInventarioLayout.setHorizontalGroup(
            VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaInventarioLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaInventarioLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaInventarioLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaInventarioLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(product_code, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(VistaInventarioLayout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(btn_buscar_dyv))))
                    .addComponent(btn_regresar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17))
        );
        VistaInventarioLayout.setVerticalGroup(
            VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaInventarioLayout.createSequentialGroup()
                .addGroup(VistaInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(VistaInventarioLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(VistaInventarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btn_buscar_dyv)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_regresar)
                .addGap(17, 17, 17))
        );

        add(VistaInventario, "card4");

        VistaVentas.setBackground(new java.awt.Color(0, 153, 255));

        jTable1.setModel(modeloVentas
        );
        jScrollPane7.setViewportView(jTable1);

        jButton5.setText("Generar Reporte");

        javax.swing.GroupLayout VistaVentasLayout = new javax.swing.GroupLayout(VistaVentas);
        VistaVentas.setLayout(VistaVentasLayout);
        VistaVentasLayout.setHorizontalGroup(
            VistaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaVentasLayout.createSequentialGroup()
                .addGroup(VistaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaVentasLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VistaVentasLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton5)))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        VistaVentasLayout.setVerticalGroup(
            VistaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaVentasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton5)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        add(VistaVentas, "card5");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // finaliza registrar cliente
        GestionadorCliente ges=new GestionadorCliente();
        //ges.LeerDatosXML();
        String nombre=TNombreCliente.getText();
        String apellido=TApellidoCliente.getText();
        String codigo=TDNICliente.getText();
        ges.Registrar(nombre, apellido, codigo);
        JOptionPane.showMessageDialog(null, "Cliente registrado");
        
        llenarTablaCliente(ges);
        
        LabelIDCliente.setText(codigo);
        //VistaVendedor.setVisible(true);
       // Vista_RegistroCliente.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    
    private void btn_AgregarAlCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarAlCarritoActionPerformed
//Agregar al carrito
    try {
        // Verificar si se ha seleccionado una fila
        int fila = TblListaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        // Mensaje de depuración para verificar la fila seleccionada
        System.out.println("Fila seleccionada: " + fila);

        // Obtener los valores de la fila seleccionada
        String codigo = TblListaProductos.getValueAt(fila, 0).toString();
        String nombre = TblListaProductos.getValueAt(fila, 1).toString();
        String precioStr = TblListaProductos.getValueAt(fila, 2).toString();
        String cantidadProductoStr = TCantidadProducto.getText();

        // Mensajes de depuración para verificar los valores obtenidos
        System.out.println("Valores obtenidos de la fila seleccionada: ");
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precioStr);
        System.out.println("Cantidad ingresada: " + cantidadProductoStr);

        // Verificar si se ha ingresado una cantidad
        if (cantidadProductoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite una cantidad");
            return;
        }

        float precio = Float.parseFloat(precioStr);
        int cantidadProducto = Integer.parseInt(cantidadProductoStr);

        int cantidadDisponible = GesProduct.ObtenerCantidad(codigo);

        // Verificar si la cantidad solicitada está disponible
        if (cantidadDisponible >= cantidadProducto) {
            int cantidadResultante = cantidadDisponible - cantidadProducto;
            String cantidad = String.valueOf(cantidadResultante);

            // Actualizando el archivo
            dataProducto.ActualizarPorID(codigo, nombre, precioStr, cantidad);

            // Pasando los archivos a una lista
            GesProduct.LeerDatosXML();

            // Actualizando tabla de productos almacen
            actualizarTablaProductos(GesProduct);

            // Agregando el producto al carrito
            GesProductCarrito.AgregarAlCarrito(codigo, nombre, precio, cantidadProducto);

            // Llenando la tabla con el carrito
            AgregarProductoCarrito(GesProductCarrito);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Producto añadido al carrito");

            // Calcular y mostrar el monto total
            float monto = CalcularMonto();
            String montoStr = Float.toString(monto);
            LabelMonto.setText(montoStr);
        } else {
            JOptionPane.showMessageDialog(null, "Cantidad no disponible");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e);
    }
    }//GEN-LAST:event_btn_AgregarAlCarritoActionPerformed
     public float CalcularMonto(){
        float monto=0;
        int Cantidadfila = TblProductosCarrito.getRowCount();
        for (int i=0;i<Cantidadfila;i++){
            String Precio = TblProductosCarrito.getValueAt(i, 2).toString();
            String Cantidad = TblProductosCarrito.getValueAt(i, 3).toString();
            float precio = Float.parseFloat(Precio);
            float cantidad = Float.parseFloat(Cantidad);
            monto=monto+precio*cantidad;
            System.out.println(monto);
        }
        return monto;
    }
    
    private void btn_RegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarClienteActionPerformed
        // registrar cliente
        
        Vista_RegistroCliente.setVisible(true);
        VistaVendedor.setVisible(false);
        VistaInventario.setVisible(false);
        
    }//GEN-LAST:event_btn_RegistrarClienteActionPerformed

    
    
    private void btn_EliminarDelCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarDelCarritoActionPerformed
        // Eliminar del carrito
        try{
            
            int fila = TblProductosCarrito.getSelectedRow();
            System.out.println(TblProductosCarrito.getSelectedRow());
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "seleecione una fila ");
                
            }else{
                String codigo = TblProductosCarrito.getValueAt(fila, 0).toString();
                modeloCarritoProductos.removeRow(TblProductosCarrito.getSelectedRow());
                JOptionPane.showMessageDialog(null,"Producto eliminado con exito");
                float Monto=CalcularMonto();
                String monto=Float.toString(Monto);
                LabelMonto.setText(monto);
            }
        }catch(Exception e){
            System.out.println("errores");
        }
    }//GEN-LAST:event_btn_EliminarDelCarritoActionPerformed

    private void TNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNombreClienteActionPerformed

    private void btn_FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FinalizarActionPerformed
        //finaliza compra
    }//GEN-LAST:event_btn_FinalizarActionPerformed

    private void btn_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InventarioActionPerformed
        VistaInventario.setVisible(true);
        Vista_RegistroCliente.setVisible(false);
        VistaVendedor.setVisible(false);
       
              
    }//GEN-LAST:event_btn_InventarioActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        VistaVendedor.setVisible(true);
        VistaInventario.setVisible(false);        
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void btn_buscar_dyvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_dyvActionPerformed
      // Buscar con divide y venceras
      String code = product_code.getText();
      
      List<String> productosEncontrados = Gestor.buscarProductosPorID(code);
      A1.setText(Gestor.mostrarProductosEncontrados(productosEncontrados));
      
      product_code.setText("");
      
      
    }//GEN-LAST:event_btn_buscar_dyvActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

try{
            int fila = tblClientes.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "seleecione una fila ");
                
            }else{
            String codigo = tblClientes.getValueAt(fila, 0).toString();
                   
                    modeloCliente.removeRow(tblClientes.getSelectedRow());
                GesCliente.Eliminar(codigo);
               
               
                
                
                
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
            }
        }catch(Exception e){
            System.out.println("errores");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void RealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarVentaActionPerformed
try{
            int fila = TblProductosCarrito.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "seleecione una fila ");
                
            }else{
            String cliente = comboClienteBox.getSelectedItem().toString();
            String ID = TblProductosCarrito.getValueAt(fila, 0).toString();
            String Nombre = TblProductosCarrito.getValueAt(fila, 1).toString();
            String Precio = TblProductosCarrito.getValueAt(fila, 2).toString();
            
             String Cantidad = TblProductosCarrito.getValueAt(fila, 3).toString();
             String Total = LabelMonto.getText();
            
            GesVenta.registroVenta(cliente, ID, Nombre, Precio, Cantidad,Total);
                    JOptionPane.showMessageDialog(null, "venta realizada ");
          llenarTablaVentas(GesVenta);

                   
            }
        }catch(Exception e){
            System.out.println("errores");
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_RealizarVentaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
VistaVentas.setVisible(true); 
VistaVendedor.setVisible(false);
        VistaInventario.setVisible(false);

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 Vista_RegistroCliente.setVisible(false);
        VistaVendedor.setVisible(true);
VistaVentas.setVisible(false);
VistaInventario.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea A1;
    private javax.swing.JLabel LabelIDCliente;
    private javax.swing.JLabel LabelMonto;
    private javax.swing.JButton RealizarVenta;
    private javax.swing.JTextField TApellidoCliente;
    private javax.swing.JTextField TCantidadProducto;
    private javax.swing.JTextField TDNICliente;
    private javax.swing.JTextField TNombreCliente;
    private javax.swing.JTable TblListaProductos;
    private javax.swing.JTable TblProductosCarrito;
    private javax.swing.JTable Tbla_Inventario;
    private javax.swing.JPanel VistaInventario;
    private javax.swing.JPanel VistaVendedor;
    private javax.swing.JPanel VistaVentas;
    private javax.swing.JPanel Vista_RegistroCliente;
    private javax.swing.JButton btn_AgregarAlCarrito;
    private javax.swing.JButton btn_EliminarDelCarrito;
    private javax.swing.JButton btn_Finalizar;
    private javax.swing.JButton btn_Inventario;
    private javax.swing.JButton btn_RegistrarCliente;
    private javax.swing.JButton btn_buscar_dyv;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JComboBox<String> comboClienteBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField product_code;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
