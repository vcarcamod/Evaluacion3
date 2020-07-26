/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.mb;

import cl.aiep.evaluacion3.dao.ClienteDAO;
import cl.aiep.evaluacion3.models.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author exvicad
 */
@Named(value = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {

    private Cliente cliente = new Cliente();
    private String mensaje;
    
    /**
     * Creates a new instance of ClienteMB
     */
    public ClienteMB() {
        mensaje = "";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Cliente registrarCliente(){
        try {
            ClienteDAO cd = new ClienteDAO();
            if(cd.registrarCliente(cliente) == 1){
            mensaje = "Cliente registrado";
            cliente = cd.consultarCliente(cliente);
            }else{
                mensaje = "No se pudo registrar el cliente.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteMB.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "No se pudo registrar el cliente. " + ex.getMessage();
        }
        return cliente;
    }
    
}
