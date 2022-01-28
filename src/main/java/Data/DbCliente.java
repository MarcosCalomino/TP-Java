package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.Cliente;

public class DbCliente {

	public Cliente getOne(Cliente cliente) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Cliente c=null;
		
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select nombreCliente, apellidoCliente, nroCliente, telefono, tipoPermisos, password from cliente where telefono=?");
			stmt.setString(1, cliente.getTelefono());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				c = new Cliente();
				c.setNombreCliente(rs.getString("nombreCliente"));
				c.setApellidoClienta(rs.getString("apellidoCliente"));
				c.setNroCliente(rs.getInt("nroCliente"));
				c.setTelefono(rs.getString("telefono"));
				c.setTipoPermisos(rs.getString("tipoPermisos"));
				c.setPassword(rs.getString("password"));
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
		return c;
	}

	public void AltaCliente(Cliente c) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into cliente(nombreCliente, apellidoCliente, telefono, tipoPermisos, password) " +
					                                                 "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);					
				stmt.setString(1, c.getNombreCliente());
				stmt.setString(2, c.getApellidoClienta());
				stmt.setString(3, c.getTelefono());
				stmt.setString(4, c.getTipoPermisos());
				stmt.setString(5, c.getPassword());
				
				stmt.executeUpdate();
				
				rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
				
				if(rs!=null && rs.next()) {
					c.setNroCliente(rs.getInt(1));
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

	public Cliente getOne(int idCliente) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Cliente c=null;
		
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select nombreCliente, apellidoCliente, nroCliente, telefono, tipoPermisos, password from cliente where nroCliente=?");
			stmt.setInt(1, idCliente);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				c = new Cliente();
				c.setNombreCliente(rs.getString("nombreCliente"));
				c.setApellidoClienta(rs.getString("apellidoCliente"));
				c.setNroCliente(rs.getInt("nroCliente"));
				c.setTelefono(rs.getString("telefono"));
				c.setTipoPermisos(rs.getString("tipoPermisos"));
				c.setPassword(rs.getString("password"));
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
		return c;
	}

}
