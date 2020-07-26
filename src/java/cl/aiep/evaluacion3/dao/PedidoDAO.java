/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.dao;

import cl.aiep.evaluacion3.models.Cliente;
import cl.aiep.evaluacion3.models.Estado;
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
public class PedidoDAO extends ConexionSql{
    public int registrarPedido(Pedido p) throws SQLException, Exception{
        String query = "insert into pedidos values (?,?,?,?)";
        try {
            conectar();
            PreparedStatement ps= obtenerPS(query);
            ps.setInt(1, 0);
            ps.setInt(2, p.getCliente().getId());
            ps.setDate(3, p.getFecha());
            ps.setInt(4, Utils.getEstado(Estado.PENDIENTE));
            int r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public int registrarProductosPedido(Pedido pedido) throws Exception{
        String query = "insert into productos_pedido values (?,?,?)";
        try {
            conectar();
            int res = 0;
            for(Producto p:pedido.getProductos()){
                PreparedStatement ps= obtenerPS(query);
                ps.setInt(1, 0);
                ps.setInt(2, p.getId());
                ps.setInt(3, pedido.getId());
                res = ps.executeUpdate();
            }
            return res;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public ArrayList<Pedido> obtenerPedidos() throws Exception{
        String query = "select * from pedidos";
        try {
            conectar();
            PreparedStatement ps = obtenerPS(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pedido> lista = new  ArrayList();
            while(rs.next()){
                ClienteDAO cd = new ClienteDAO();
                Cliente cliente = cd.consultarCliente(rs.getInt("id_cliente"));
                lista.add(new Pedido(rs.getInt("id"), cliente, rs.getDate("fecha"), Utils.getEstado(rs.getInt("estado"))));
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
    
    public Pedido consultarPedido(Cliente cl) throws Exception{
        String query = "select * from pedidos where id_cliente = ?";
        try {
            conectar();
            PreparedStatement ps = obtenerPS(query);
            ps.setInt(1, cl.getId());
            Pedido pedido = null;
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Cliente cliente = new Cliente();
                pedido = new Pedido(rs.getInt("id"), cliente, rs.getDate("fecha"), Utils.getEstado(rs.getInt("estado")));
            }
            return pedido;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
}
