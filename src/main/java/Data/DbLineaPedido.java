package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.LineaPedido;
import Entities.Pedido;

public class DbLineaPedido {

	public void AltaLineaPedido(LineaPedido lineaPedido) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into linea_pedido(cantidad, idProducto, precioLineaPedido, idPedido)" +
					                                                 "values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);					
				stmt.setDouble(1, lineaPedido.getCantidad());
				stmt.setInt(2, lineaPedido.getIdProducto());
				stmt.setDouble(3, lineaPedido.getPrecioLineaPedido());
				stmt.setInt(4, lineaPedido.getNroPedido());
				
				stmt.executeUpdate();
				
				rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
				
				if(rs!=null && rs.next()) {
					lineaPedido.setNroLineaPedido(rs.getInt(1));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void Eliminar(LineaPedido lp) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConexion.getInstancia().getConn().
					prepareStatement("delete from linea_pedido where nroLineaPedido=?");
			stmt.setInt(1, lp.getNroLineaPedido());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConexion.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public LinkedList<LineaPedido> GetAll(int idPedido) {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		LinkedList<LineaPedido> listaLineaPedido = new LinkedList<LineaPedido>();
		
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from linea_pedido where idPedido=?");
			stmt.setInt(1, idPedido);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					LineaPedido lineaPedido = new LineaPedido();
					lineaPedido.setCantidad(rs.getDouble("cantidad"));
					lineaPedido.setIdProducto(rs.getInt("idProducto"));
					lineaPedido.setNroLineaPedido(rs.getInt("nroLineaPedido"));
					lineaPedido.setNroPedido(rs.getInt("idPedido"));
					lineaPedido.setPrecioLineaPedido(rs.getDouble("precioLineaPedido"));
					
					listaLineaPedido.add(lineaPedido);
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaLineaPedido;
	}

}
