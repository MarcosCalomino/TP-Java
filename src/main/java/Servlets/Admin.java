package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Producto;
import Logic.ProductoLogic;


@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Admin() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoLogic productoLogic = new ProductoLogic();
		LinkedList<Producto> listaProductosPorTipo = new LinkedList<Producto>();	
			
		if(request.getParameter("menues")!= null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON MENUES*****
		{
		    request.setAttribute("listaTipoProductos", productoLogic.GetAllTiposProductos());
		    request.setAttribute("menues", "true");
		    request.getRequestDispatcher("./Admin.jsp").forward(request, response);
		}
		else if (request.getParameter("pedidos")!=null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON PEDIDOS*****
		{
			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
		}
		else if(request.getParameter("listarSegunProducto")!=null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON LISTAR*****
		{	
			request.setAttribute("tipoProducto", request.getParameter("listarSegunProducto"));
			listaProductosPorTipo = productoLogic.GetProductsForType(request.getParameter("listarSegunProducto"));
			request.setAttribute("listaProductosPorTipo", listaProductosPorTipo);
			request.getRequestDispatcher("./ListarProductos.jsp").forward(request, response);
		}
		else if(request.getParameter("agregar")!=null)//*****SE VERIFICA SI SE LE DIO CLICK AL BOTON PARA AGREGAR PRODUCTO*****
		{
			request.setAttribute("tipoProducto", request.getParameter("agregar"));
			request.getRequestDispatcher("./AltaProducto.jsp").forward(request, response);
		}
		else if(request.getParameter("cerrarSession")!=null)//SI SE LE DA CLICK AL BOTON Log Out TOMA ESTE CAMINO
		{
			request.getRequestDispatcher("./Login.jsp").forward(request, response); 
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
