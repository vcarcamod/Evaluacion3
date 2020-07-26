/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.dao;

import cl.aiep.evaluacion3.models.Pedido;
import cl.aiep.evaluacion3.models.Producto;
import cl.aiep.evaluacion3.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author exvicad
 */
public class ProductoDAO extends ConexionSql{
    
    public int registrarProducto(Producto p) throws SQLException, Exception{
        String query = "insert into productos values (?,?,?,?)";
        try {
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, 0);
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getPrecio());
            int r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public Producto consultarProducto(int id) throws Exception{
        String query = "select * from productos where id = ?";
        try {
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Producto producto = null;
            if(rs.next()){
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getInt("precio"));
            }
            return producto;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public ArrayList<Producto> listarProductos() throws Exception{
        String query = "select * from productos";
        try {
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Producto> lista = new  ArrayList();
            while(rs.next()){
                lista.add(new Producto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getInt("precio")));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
}
