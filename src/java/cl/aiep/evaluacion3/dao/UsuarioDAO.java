/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.dao;

import cl.aiep.evaluacion3.models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author exvicad
 */
public class UsuarioDAO extends ConexionSql {
    public int registrarUsuario(Usuario u) throws Exception{
        String query = "insert into usuarios values (?,?,?,?,?)";
        try{
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, 0);
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getNombre());
            ps.setString(5, u.getApellido());
            int r = ps.executeUpdate();
            return r;
        }catch(Exception e){
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public Usuario consultarUsuario(Usuario u) throws SQLException, Exception{
        String query = "select * from usuarios where usuario = ?";
        Usuario usuario = null;
        try{
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setString(1, u.getUsuario());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario = new Usuario(rs.getInt("id"), rs.getString("usuario"),
                rs.getString("password"), rs.getString("nombre"), rs.getString("apellido"));
            }
            return usuario;
        }catch(Exception ex){
            return usuario;
        }finally{
            desconectar();
        }
    }
}
