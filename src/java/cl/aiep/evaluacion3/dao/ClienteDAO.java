/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.dao;

import cl.aiep.evaluacion3.models.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author exvicad
 */
public class ClienteDAO extends ConexionSql {
    public int registrarCliente(Cliente cl) throws Exception{
        String query = "insert into clientes values (?,?,?,?)";
        try{
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, 0);
            ps.setString(2, cl.getEmail());
            ps.setString(3, cl.getNombre());
            ps.setString(4, cl.getApellido());
            int r = ps.executeUpdate();
            return r;
        }catch(Exception e){
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public Cliente consultarCliente(Cliente cl) throws SQLException, Exception{
        String query = "select * from clientes where email = ?";
        Cliente cliente = null;
        try{
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setString(1, cl.getEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cliente = new Cliente(rs.getInt("id"), rs.getString("email"),
                rs.getString("nombre"), rs.getString("apellido"));
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            desconectar();
        }
        return cliente;
    }
    
    public Cliente consultarCliente(int id) throws SQLException, Exception{
        String query = "select * from clientes where id = ?";
        Cliente cliente = null;
        try{
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cliente = new Cliente(rs.getInt("id"), rs.getString("email"),
                rs.getString("nombre"), rs.getString("apellido"));
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            desconectar();
        }
        return cliente;
    }
}
