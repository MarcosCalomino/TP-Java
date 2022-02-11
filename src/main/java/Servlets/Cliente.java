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
import Entities.Pedido;
import Entities.Producto;
import Logic.LineaPedidoLogic;
import Logic.PedidoLogic;
import Logic.ProductoLogic;

@MultipartConfig
@WebServlet("/Cliente")
public class Cliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cliente(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session=request.getSession();
		PedidoLogic pedidoLogic = new PedidoLogic();
		LineaPedidoLogic lineaPedidoLogic = new LineaPedidoLogic();
		LinkedList<Pedido> listaTodosLosPedidos;
		LinkedList<Pedido> listaPedidosDeCliente;
		LinkedList<LineaPedido> listaLineasPedido;
		
		if(request.getParameter("NuevoPedido")!=null)
		{
			request.getRequestDispatcher("./AltaLineaPedido.jsp").forward(request, response); 
		}
		else if(request.getParameter("pedidos")!=null)			
		{
			listaTodosLosPedidos = pedidoLogic.GetAllPedidos();
			listaPedidosDeCliente  = new LinkedList<Pedido>();
			for(Pedido p: listaTodosLosPedidos)
			{
				if(p.getNroCliente() == (int) session.getAttribute("sessionLogin"))
				{
					listaPedidosDeCliente.add(p);
				}
			}
			request.setAttribute("listaPedidosDeCliente", listaPedidosDeCliente);
			request.getRequestDispatcher("./Cliente.jsp").forward(request, response); 
		}
		else if(request.getParameter("cerrarSession")!=null)
		{
			session.setAttribute("sessionLogin", null);
			request.getRequestDispatcher("./Login.jsp").forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
