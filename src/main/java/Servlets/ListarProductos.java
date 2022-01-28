package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Producto;
import Logic.ProductoLogic;

@MultipartConfig
@WebServlet("/ListarProductos")
public class ListarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoLogic productoLogic = new ProductoLogic();
		Producto producto;
		if(request.getParameter("modificar")!=null)
        {
        	producto = new Producto();
        	producto = productoLogic.GetOne(Integer.parseInt(request.getParameter("modificar")));
        	request.setAttribute("producto", producto);
        	request.setAttribute("modo", "modoModificar");
        	request.getRequestDispatcher("./ModificarEliminar.jsp").forward(request, response);
        }
		else if(request.getParameter("eliminar")!=null)
		{
			producto = new Producto();
			producto = productoLogic.GetOne(Integer.parseInt(request.getParameter("eliminar")));
        	request.setAttribute("producto", producto);
        	request.setAttribute("modo", "modoEliminar");
        	request.getRequestDispatcher("./ModificarEliminar.jsp").forward(request, response);
		}
		else if(request.getParameter("menues")!=null)
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
