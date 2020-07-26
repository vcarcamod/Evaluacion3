/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.mb;

import cl.aiep.evaluacion3.dao.ProductoDAO;
import cl.aiep.evaluacion3.models.Producto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exvicad
 */
@Named(value = "productosMB")
@SessionScoped
public class ProductoMB implements Serializable {
    
    private Producto producto = new Producto();
    private ArrayList<Producto> productos;
    private String mensaje = "";
    
    /**
     * Creates a new instance of ProductosMB
     */
    public ProductoMB() {
        actualizaLista();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void registrarProducto(){
        try {
            ProductoDAO pd = new ProductoDAO();
            if(pd.registrarProducto(producto) == 1){
                mensaje = "Producto registrado.";
                actualizaLista();
            }else{
                mensaje = "No se pudo registrar el producto.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizaLista(){
        try {
            ProductoDAO pd = new ProductoDAO();
            productos = pd.listarProductos();
        } catch (Exception ex) {
            Logger.getLogger(ProductoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
