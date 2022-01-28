package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;

import Entities.Cliente;
import Entities.Pedido;
import Entities.TipoProducto;

public class DbPedido {

	public void Alta(Pedido pedido) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into pedido(fechaHoraPedido, precioTotal, estadoPedido, nroCliente, DireccionID) " +
					                                                 "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);					
				stmt.setString(1, pedido.getFechaHoraPedido());
				stmt.setDouble(2, pedido.getPrecioTotal());
				stmt.setString(3, pedido.getEstadoPedido());
				stmt.setInt(4, pedido.getNroCliente());
				stmt.setInt(5, pedido.getDireccionID());
				
				stmt.executeUpdate();
				
				rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
				
				if(rs!=null && rs.next()) {
					pedido.setNroPedido(rs.getInt(1));
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

	public LinkedList<Pedido> GetAllPedidos() {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		LinkedList<Pedido> lista= new LinkedList<Pedido>();
		
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from pedido");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Pedido pedido = new Pedido();
					pedido.setDireccionID(rs.getInt("DireccionID"));
					pedido.setEstadoPedido(rs.getString("estadoPedido"));
					pedido.setFechaHoraPedido(rs.getString("fechaHoraPedido"));
					pedido.setNroCliente(rs.getInt("nroCliente"));
					pedido.setNroPedido(rs.getInt("nroPedido"));
					pedido.setPrecioTotal(rs.getDouble("precioTotal"));
					
					lista.add(pedido);
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
		return lista;
		
	}

	public LinkedList<Pedido> GetAllPedidos(String estado) {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		LinkedList<Pedido> lista= new LinkedList<Pedido>();
		
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from pedido where estadoPedido=?");
			stmt.setString(1, estado);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Pedido pedido = new Pedido();
					pedido.setDireccionID(rs.getInt("DireccionID"));
					pedido.setEstadoPedido(rs.getString("estadoPedido"));
					pedido.setFechaHoraPedido(rs.getString("fechaHoraPedido"));
					pedido.setHoraEntrega(rs.getObject("horaEntrega",LocalTime.class));
					pedido.setNroCliente(rs.getInt("nroCliente"));
					pedido.setNroPedido(rs.getInt("nroPedido"));
					pedido.setPrecioTotal(rs.getDouble("precioTotal"));
					
					lista.add(pedido);
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
		return lista;
	}

	public Pedido GetOne(int idPedido) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Pedido p=null;
		
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select fechaHoraPedido, horaEntrega, precioTotal, estadoPedido, nroCliente, DireccionID from pedido where nroPedido=?");
			stmt.setInt(1, idPedido);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p = new Pedido();
				p.setFechaHoraPedido(rs.getString("fechaHoraPedido"));
				p.setHoraEntrega(rs.getObject("horaEntrega",LocalTime.class));
				p.setPrecioTotal(rs.getDouble("precioTotal"));
				p.setEstadoPedido(rs.getString("estadoPedido"));
				p.setNroCliente(rs.getInt("nroCliente"));
				p.setDireccionID(rs.getInt("DireccionID"));
				p.setNroPedido(rs.getInt("nroPedido"));
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
		return p;
	}

	public void Modify(Pedido pedido) {
		PreparedStatement stmt = null;
		
        try {  
			stmt=DbConexion.getInstancia().getConn().prepareStatement("update pedido set fechaHoraPedido=?, horaEntrega=?, precioTotal=?, estadoPedido=?, nroCliente=?, DireccionID=?  where nroPedido=?" );
			
			stmt.setString(1, pedido.getFechaHoraPedido());
			stmt.setObject(2, pedido.getHoraEntrega());
			stmt.setDouble(3, pedido.getPrecioTotal());
			stmt.setString(4, pedido.getEstadoPedido());
			stmt.setInt(5, pedido.getNroCliente());
			stmt.setInt(6, pedido.getDireccionID());			
			stmt.setInt(7, pedido.getNroPedido());
			
			stmt.executeUpdate();
			
    }catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(stmt!=null) {stmt.close();}
				DbConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}     
		
	}

}
