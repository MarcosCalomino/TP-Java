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

import Entities.Producto;
import Errores.BugManager;
import Logic.ProductoLogic;
@MultipartConfig
@WebServlet("/AltaProducto")
public class AltaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AltaProducto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoLogic productoLogic = new ProductoLogic();	
		//BugManager bugManager = new BugManager();            
		if(request.getParameter("menues")!= null)//SE VERIFICA SI SE LE DIO CLICK AL BOTON MENUES 
		{
		    request.setAttribute("listaTipoProductos", productoLogic.GetAllTiposProductos());
			request.setAttribute("menues", "true");
			request.getRequestDispatcher("./Admin.jsp").forward(request, response);
		}
		else if (request.getParameter("pedidos")!=null)//SE VERIFICA SI SE LE DIO CLICK AL BOTON PEDIDOS 
		{
			request.setAttribute("pedidos", "true");
			request.getRequestDispatcher("./Admin.jsp").forward(request, response);
		}
		else if(request.getParameter("cargar")!=null)//SE VERIFICA SI SE LE DIO CLICK AL BOTON CARGAR
		{		
			try {
				
                if(request.getParameter("nombreProducto").isEmpty() || request.getParameter("descripcionProducto").isEmpty())
			    {
                	request.setAttribute("nombreProducto", request.getParameter("nombreProducto"));
    				request.setAttribute("descripcionProducto", request.getParameter("descripcionProducto"));
    				request.setAttribute("precioProducto", Double.parseDouble(request.getParameter("precioProducto")));
    				request.setAttribute("tipoProducto", request.getParameter("tipoProducto"));
                	throw new NullPointerException();
			    }
                else
                {
                	Producto p = new Producto();
                	request.setAttribute("nombreProducto", request.getParameter("nombreProducto"));
    				request.setAttribute("descripcionProducto", request.getParameter("descripcionProducto"));
    				request.setAttribute("precioProducto", Double.parseDouble(request.getParameter("precioProducto")));
    				String tipoProducto = request.getParameter("tipoProducto");
    				String nombreProducto = request.getParameter("nombreProducto");
    				String descripcionProducto = request.getParameter("descripcionProducto");
    				Double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
    				int estadoProducto = Integer.parseInt(request.getParameter("estadoProducto"));
    				Part filePart = request.getPart("imagenProducto");
    				File file = productoLogic.ManageImage(filePart);
    				p = productoLogic.setDatos(nombreProducto, descripcionProducto, tipoProducto, precioProducto, estadoProducto, file);
    				productoLogic.AltaProducto(p);
    				if(p.getNombreProducto().isEmpty() || p.getDescripcion().isEmpty())
    				{
    					request.setAttribute("notificacion", "noCargado");
    				}
    				else
    				{
    					request.setAttribute("notificacion", "cargado");
    				}
    				request.getRequestDispatcher("./Admin.jsp").forward(request, response);	
                }				
			}catch(NumberFormatException e){				
				request.setAttribute("error", "erroEnPrecio");
				request.getRequestDispatcher("./AltaProducto.jsp").forward(request, response);
			}catch(NullPointerException e){
				request.setAttribute("error", "errorCampoVacio");
				request.getRequestDispatcher("./AltaProducto.jsp").forward(request, response);
			}catch(IllegalArgumentException e) {
				request.setAttribute("error", "errorCampoVacio");
				request.getRequestDispatcher("./AltaProducto.jsp").forward(request, response);
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
