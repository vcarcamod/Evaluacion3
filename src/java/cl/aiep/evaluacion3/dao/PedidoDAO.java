/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.evaluacion3.dao;

import cl.aiep.evaluacion3.models.Estado;
import cl.aiep.evaluacion3.models.Pedido;
import cl.aiep.evaluacion3.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            ps.setString(3, p.getFecha().toString());
            ps.setInt(4, Utils.getEstado(Estado.PENDIENTE));
            int r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            throw e;
        }finally{
            desconectar();
        }
    }
}
