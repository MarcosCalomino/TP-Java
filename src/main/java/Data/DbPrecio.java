package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Entities.Precio;
import Entities.Producto;

public class DbPrecio {

	public void AltaPrecioProducto(Precio pp) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try {	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into precio(precioIndividual, fechaAsignacion) " +
					                                                 "values(?,?)", Statement.RETURN_GENERATED_KEYS);
			
			stmt.setDouble(1, pp.getPrecio());
			stmt.setString(2, pp.getDisabledDate());
            stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
			
			if(rs!=null && rs.next()) {
				pp.setNroPrecio(rs.getInt(1)) ;
			}	
	    }catch (SQLException e) {
		    e.printStackTrace();
	    }finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			DbConexion.getInstancia().releaseConn();
		}catch (SQLException e) {
			e.printStackTrace();
		  }
	    }
	}

	public Precio GetOne(int idPrecio) {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		Precio p = null;
		  try 
		  {	
			  stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from precio where nroPrecio=?");
			  stmt.setInt(1, idPrecio);
				
			  rs = stmt.executeQuery();
				
			  if(rs!=null && rs.next()) {
					p  = new Precio();
					p.setNroPrecio(rs.getInt("nroPrecio"));
					p.setPrecio(rs.getDouble("precioIndividual"));
					p.setDisabledDate(rs.getString("fechaAsignacion"));
				}
		  }
		  catch(SQLException e)
		  {
				e.printStackTrace();
		  }
		  finally 
		  {
		     try
		     {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConexion.getInstancia().releaseConn();
		     }
		     catch(SQLException e)
		     {
			   e.printStackTrace();
			 }
		  }
		return p;
	}

	public void ModificarPrecio(Precio precio) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("update precio set precioIndividual=?, fechaAsignacion=? where nroPrecio=?");
			stmt.setDouble(1, precio.getPrecio());
			stmt.setString(2, precio.getDisabledDate());
			stmt.setInt(3, precio.getNroPrecio());
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

	public void EliminarPrecio(int nroPrecio) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConexion.getInstancia().getConn().
					prepareStatement(
							"delete from precio where nroPrecio=?");
			stmt.setInt(1, nroPrecio);
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

}
