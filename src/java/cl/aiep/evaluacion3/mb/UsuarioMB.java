/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.mb;

import cl.aiep.evaluacion3.dao.UsuarioDAO;
import cl.aiep.evaluacion3.models.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author exvicad
 */
@Named(value = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario = new Usuario();
    private String mensaje;
    
    /**
     * Creates a new instance of UsuarioMB
     */
    public UsuarioMB() {
        mensaje = "";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void registrarUsuario(){
        try {
            UsuarioDAO ud = new UsuarioDAO();
            if(ud.consultarUsuario(usuario) == null ){
                if(ud.registrarUsuario(usuario) == 1){
                    mensaje = "Usuario registrado.";
                }else{
                    mensaje = "No se pudo registrar el usuario.";
                }
            }else{
                mensaje = "Usuario ya existe. Intente nuevamente.";
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "No se pudo registrar el usuario. " + ex.getMessage();
        }
    }
    
    public void login() throws IOException{
        if(validarClave() != null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/catalogo.xhtml?i=1");
        }
    }
    
    private Usuario validarClave(){
        try {
            UsuarioDAO ud = new UsuarioDAO();
            Usuario temp = ud.consultarUsuario(usuario);
            if(temp != null){
                if(usuario.getPassword().equals(temp.getPassword())){
                    usuario = temp;
                    return temp;
                }else{
                    mensaje = "Password incorrecta.";
                }
            }else{
                mensaje = "Usuario no existe.";
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error: " + ex.getMessage();
            return null;
        }
        return null;
    }
    
    public void limpiaLogin(){
        this.usuario = null;
        this.mensaje = "";
    }
}
