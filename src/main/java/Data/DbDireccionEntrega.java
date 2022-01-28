package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.DireccionEntrega;

public class DbDireccionEntrega {

	public void Alta(DireccionEntrega direccionEntrega) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into direccion_entrega(calleSobre, altura, piso, calleEntre1, calleEntre2) " +
					                                                 "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);					
				stmt.setString(1, direccionEntrega.getCalleSobre());
				stmt.setInt(2, direccionEntrega.getAltura());
				stmt.setString(3, direccionEntrega.getPiso());
				stmt.setString(4, direccionEntrega.getCalleEntre1());
				stmt.setString(5, direccionEntrega.getCalleEntre2());
				
				stmt.executeUpdate();
				
				rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
				
				if(rs!=null && rs.next()) {
					direccionEntrega.setIdDireccion(rs.getInt(1));
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

}
