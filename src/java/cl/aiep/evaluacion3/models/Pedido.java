/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author exvicad
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private Date fecha;
    private Estado estado;
    private ArrayList<Producto> productos;

    public Pedido(int id, Cliente cliente, Date fecha, Estado estado, ArrayList<Producto> productos) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.productos = productos;
    }

    public Pedido() {
        java.util.Date d = new java.util.Date();
        this.fecha = new Date(d.getTime());
        this.estado = Estado.PENDIENTE;
        this.productos = new ArrayList<Producto>();
    }

    public Pedido(int id, Cliente cliente, Date date, Estado estado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = date;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
