package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.LineaPedido;
import Entities.Precio;
import Entities.Producto;
import Logic.LineaPedidoLogic;
import Logic.PedidoLogic;
import Logic.PrecioLogic;
import Logic.ProductoLogic;

@MultipartConfig
@WebServlet("/AltaLineaPedido")
public class AltaLineaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AltaLineaPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		LineaPedidoLogic lineaPedidoLogic = new LineaPedidoLogic();
		PrecioLogic precioLogic = new PrecioLogic();
		ProductoLogic productoLogic = new ProductoLogic();
		PedidoLogic pedidoLogic = new PedidoLogic();
		LinkedList<LineaPedido> carrito = (LinkedList<LineaPedido>)session.getAttribute("carrito");
		LinkedList<Producto> listaProductos = new LinkedList<Producto>();
		listaProductos = productoLogic.GetAll();
		
		if(request.getParameter("pizzas")!=null)
		{	
		   request.setAttribute("listaProductos", listaProductos);
		   request.setAttribute("PresButtonPizzas", "true");
		   request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response); 
		}
		else if(request.getParameter("bebidas")!=null)
		{
			request.setAttribute("listaProductos", listaProductos);
			request.setAttribute("PresButtonBebidas", "true");
			request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response);
		}
		else if(request.getParameter("postres")!=null)
		{
			request.setAttribute("listaProductos", listaProductos);
			request.setAttribute("PresButtonPostres", "true");
			request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response);
		}
		else if(request.getParameter("agregarACarrito")!=null)
		{	
			int idProducto = Integer.parseInt(request.getParameter("agregarACarrito"));	   
			double cantidad = Double.parseDouble(request.getParameter("cantidad")); 
			Producto producto = productoLogic.GetOne(idProducto);
			Precio precio = precioLogic.GetOne(producto.getNroPrecio());
			LineaPedido lineaPedido = new LineaPedido(idProducto, cantidad, precio.getPrecio());	
			//lineaPedidoLogic.AltaLineaPedido(lineaPedido);
			if(carrito==null)
			{
				carrito = new LinkedList<LineaPedido>();    
			}
			carrito.add(lineaPedido);
			session.setAttribute("carrito", carrito);
			request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response); 			
		}
		else if(request.getParameter("eliminarDeCarrito")!=null)
		{
			for(LineaPedido lp: carrito){
				if(lp.getNroLineaPedido() == Integer.parseInt(request.getParameter("eliminarDeCarrito"))){
					carrito.remove(lp);
					lineaPedidoLogic.Eliminar(lp);
					break;
				}
			}
			session.setAttribute("carrito", carrito);
			request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response); 	
		}
		else if(request.getParameter("realizarPedido")!=null)//****SE VERIFICA SI SE LE DIO CLICK AL BOTON REALIZAR PEDIDO
		{
			if(carrito.size() == 0)
			{
				request.setAttribute("errorCarritoVacio", "true");
				request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response); 
			}
			else
			{
				request.getRequestDispatcher("./AltaPedido.jsp").forward(request, response);	
			}
		}

}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
