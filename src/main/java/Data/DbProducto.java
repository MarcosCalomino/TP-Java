package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Producto;
import Entities.TipoProducto;

public class DbProducto {

	public LinkedList<TipoProducto> GetAllTiposPrductos() {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		LinkedList<TipoProducto> lista= new LinkedList<>();
		
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from tipo_producto");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					TipoProducto tp=new TipoProducto();
					//tp.setCodTipoProducto(rs.getInt("codTipoProducto"));
					tp.setDescripcion(rs.getString("tipoProducto"));
					
					lista.add(tp);
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

	public LinkedList<Producto> GetProductsForType(String tipoProducto) {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		LinkedList<Producto> lista= new LinkedList<>();
		Producto p = null;
		  try 
		  {	
			  stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from producto where tipoProducto=?");
			  stmt.setString(1, tipoProducto);
			  rs=stmt.executeQuery();
			  if(rs!=null) 
			  {
				  while(rs.next())
				  {
					  p=new Producto();
					  p.setId_producto(rs.getInt("id_producto"));
					  p.setDescripcion(rs.getString("descripcion"));
					  p.setEstado(rs.getBoolean("habilitado"));
					  p.setImagen(rs.getString("imagen"));
					  p.setNombreProducto(rs.getString("nombreProducto"));
					  p.setNroPrecio(rs.getInt("nroPrecio"));
					  p.setTipoProducto(rs.getString("tipoProducto"));  
					  lista.add(p);
			      }				
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
		return lista;
	}

	public Producto GetOne(int idProducto) {
		PreparedStatement stmt = null;
		ResultSet rs=null;
		Producto p = null;
		  try 
		  {	
			  stmt=DbConexion.getInstancia().getConn().prepareStatement("select * from producto where id_producto=?");
			  stmt.setInt(1, idProducto);
			  rs=stmt.executeQuery();
			  if(rs!=null) 
			  {
				  while(rs.next())
				  {
					  p=new Producto();
					  p.setId_producto(rs.getInt("id_producto"));
					  p.setDescripcion(rs.getString("descripcion"));
					  p.setEstado(rs.getBoolean("habilitado"));
					  p.setImagen(rs.getString("imagen"));
					  p.setNombreProducto(rs.getString("nombreProducto"));
					  p.setNroPrecio(rs.getInt("nroPrecio"));
					  p.setTipoProducto(rs.getString("tipoProducto"));
			      }				
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

	public void AltaProducto(Producto producto) {
		ResultSet rs = null;
        PreparedStatement stmt = null;
        
        try{	
			stmt=DbConexion.getInstancia().getConn().prepareStatement("insert into producto(nombreProducto, descripcion, habilitado, nroPrecio, tipoProducto,imagen) " +
					                                                 "values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					
				stmt.setString(1, producto.getNombreProducto());
				stmt.setString(2, producto.getDescripcion());
				stmt.setBoolean(3, producto.getEstado());
				stmt.setInt(4, producto.getNroPrecio());
				stmt.setString(5, producto.getTipoProducto());
			    stmt.setString(6, producto.getImagen());
				stmt.executeUpdate();	
				rs = stmt.getGeneratedKeys();//DEVUELVE UN RESULSET
				
				if(rs!=null && rs.next())
				{
					producto.setId_producto(rs.getInt(1));
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

	public void ModificarProducto(Producto producto) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConexion.getInstancia().getConn().prepareStatement("update producto set habilitado=?, nombreProducto=?, "
					                                                + "descripcion=?, nroPrecio=?, tipoProducto=?, "
					                                                + "imagen=? where id_producto=?");
			stmt.setBoolean(1, producto.getEstado());
			stmt.setString(2, producto.getNombreProducto());
			stmt.setString(3, producto.getDescripcion());
			stmt.setInt(4, producto.getNroPrecio());
			stmt.setString(5, producto.getTipoProducto());
			stmt.setString(6, producto.getImagen());
			stmt.setInt(7, producto.getId_producto());
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

	public void EliminarProducto(Producto producto) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConexion.getInstancia().getConn().
					prepareStatement(
							"delete from producto where id_producto=?");
			stmt.setInt(1, producto.getId_producto());
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

	public LinkedList<Producto> GetAll() {
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> listaProductos= new LinkedList<Producto>();
		
		try {
			stmt= DbConexion.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from producto");
			if(rs!=null) {
				while(rs.next()) {
					Producto p = new Producto();
					p.setDescripcion(rs.getString("descripcion"));
					p.setEstado(rs.getBoolean("habilitado"));
					p.setId_producto(rs.getInt("id_producto"));
					p.setImagen(rs.getString("imagen"));
					p.setNombreProducto(rs.getString("nombreProducto"));
					p.setNroPrecio(rs.getInt("nroPrecio"));
				    p.setTipoProducto(rs.getString("tipoProducto"));
				
					listaProductos.add(p);
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
				
		return listaProductos;
	}


}
