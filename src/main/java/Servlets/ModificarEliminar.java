package Servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Entities.Precio;
import Entities.Producto;
import Logic.PrecioLogic;
import Logic.ProductoLogic;
@MultipartConfig
@WebServlet("/ModificarEliminar")
public class ModificarEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificarEliminar() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoLogic productoLogic = new ProductoLogic();
		PrecioLogic precioLogic = new PrecioLogic();
		Producto producto = null;
		
		if(request.getParameter("modificar")!=null)//*****SE VERIFICAR SI SE LE DIO AL BOTON MODIFICAR*****
		{
			try {
				producto = new Producto(); 
				producto.setId_producto(Integer.parseInt(request.getParameter("modificar")));
				producto.setNombreProducto(request.getParameter("nombreProducto"));
				producto.setDescripcion(request.getParameter("descripcionProducto"));
				producto.setTipoProducto(request.getParameter("tipoProducto"));
				if(Integer.parseInt(request.getParameter("estadoProducto"))==1)
			    {
			       producto.setEstado(true);
			    }
			    else
			    {
			       producto.setEstado(false);
			    }
				Precio precio = new Precio(Integer.parseInt(request.getParameter("nroPrecio")), 
	                                       Double.parseDouble(request.getParameter("precioProducto")), 
	                                       productoLogic.GetFechaActual());
				precioLogic.ModificarPrecio(precio);
				producto.setNroPrecio(precio.getNroPrecio());
				Part filePart = request.getPart("imagenProducto");
				File file = productoLogic.ManageImage(filePart);	
				producto.setImagen(file.getName());
				   
				productoLogic.ModificarProducto(producto);
				request.setAttribute("notificacion", "modificado");
				request.getRequestDispatcher("./Admin.jsp").forward(request, response);
			}catch(NumberFormatException e) {
				 request.setAttribute("error", "erroEnPrecio");
				 producto.setNroPrecio(Integer.parseInt(request.getParameter("nroPrecio")));
				 request.setAttribute("producto", producto);
				 request.setAttribute("modo", "modoModificar");
				 request.getRequestDispatcher("./ModificarEliminar.jsp").forward(request, response);
			}catch(IllegalArgumentException e) {
				 request.setAttribute("error", "errorCampoVacio");
				 request.setAttribute("producto", producto);
				 request.setAttribute("modo", "modoModificar");
				 request.getRequestDispatcher("./ModificarEliminar.jsp").forward(request, response);
			}
			
		}
		else if(request.getParameter("eliminar")!=null)//*****SE VERIFICAR SI SE LE DIO AL BOTON ELIMINAR*****
		{
			   producto = new Producto();
			   producto.setId_producto(Integer.parseInt(request.getParameter("eliminar")));
			   producto.setNroPrecio(Integer.parseInt(request.getParameter("nroPrecio")));
			   productoLogic.EliminarProducto(producto);
			   request.setAttribute("notificacion", "eliminado");
			   request.getRequestDispatcher("./Admin.jsp").forward(request, response);
		}
		else if(request.getParameter("menues")!=null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON MENUES*****
		{
			request.setAttribute("listaTipoProductos", productoLogic.GetAllTiposProductos());
			request.setAttribute("menues", "true");
			request.getRequestDispatcher("./Admin.jsp").forward(request, response);
		}
		else if (request.getParameter("pedidos")!=null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON PEDIDOS*****
		{
			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
