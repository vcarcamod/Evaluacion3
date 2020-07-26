/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.mb;

import cl.aiep.evaluacion3.dao.ClienteDAO;
import cl.aiep.evaluacion3.dao.PedidoDAO;
import cl.aiep.evaluacion3.models.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import cl.aiep.evaluacion3.models.Pedido;
import cl.aiep.evaluacion3.models.Producto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exvicad
 */
@Named(value = "pedidoMB")
@SessionScoped
public class PedidoMB implements Serializable {

    private Pedido pedido = new Pedido();
    private ArrayList<Pedido> lista;
    private String mensaje = "";
    private String mensajeCarro = "";
    
    /**
     * Creates a new instance of PedidoMB
     */
    public PedidoMB() {
        listar();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ArrayList<Pedido> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Pedido> lista) {
        this.lista = lista;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeCarro() {
        return mensajeCarro;
    }

    public void setMensajeCarro(String mensajeCarro) {
        this.mensajeCarro = mensajeCarro;
    }

    public void registrar(){
        PedidoDAO pd;
        try{
            pd = new PedidoDAO();
            pd.registrarPedido(pedido);
        }catch(Exception ex){
            
        }
    }
    
    public void iniciarPedido(ClienteMB clienteMB){
        try {
            ClienteDAO cd = new ClienteDAO();
            Cliente temp = cd.consultarCliente(clienteMB.getCliente());
            if(temp == null){
                pedido.setCliente(clienteMB.registrarCliente());
            }else{
                pedido.setCliente(temp);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarPedido(){
        PedidoDAO pd;
        try {
            pd = new PedidoDAO();
            Cliente cliente = new Cliente();
            pedido = pd.consultarPedido(cliente);
        } catch (Exception e) {
        }
    }
    
    private void listar(){
        PedidoDAO pd;
        try {
            pd = new PedidoDAO();
            lista = pd.obtenerPedidos();
        } catch (Exception e) {
        }
    }
    
    public void agregarProducto(Producto producto){
        try {
            pedido.getProductos().add(producto);
            mensaje = producto.getNombre() + " agregado al carro.";
            mensajeCarro = "";
        } catch (Exception ex) {
            Logger.getLogger(PedidoMB.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "No se pudo agregar el producto al carro.";
        }
    }
    
    public void quitarProducto(Producto producto){
        pedido.getProductos().remove(producto);
        mensaje = "";
        mensajeCarro = producto.getNombre() + " eliminado del carro";
    }
    
    public void confirmarPedido(){
        try {
            PedidoDAO pd = new PedidoDAO();
            if(pd.registrarPedido(pedido) == 1){
                Pedido temp = pd.consultarPedido(pedido.getCliente());
                pedido.setId(temp.getId());
                if(pd.registrarProductosPedido(pedido) == 1){
                    mensaje = "";
                    mensajeCarro = "Pedido enviado. Pendiente de confirmación";
                    pedido = null;
                }
            }else{
                mensaje = "";
                mensajeCarro = "Error al registrar el pedido.";
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoMB.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "";
            mensajeCarro = "Error al registrar el pedido. " + ex.getMessage();
        }
    }
    
}
