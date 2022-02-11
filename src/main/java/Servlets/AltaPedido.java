package Servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.DireccionEntrega;
import Entities.LineaPedido;
import Entities.Pedido;
import Errores.BugManager;
import Logic.DireccionEntregaLogic;
import Logic.LineaPedidoLogic;
import Logic.PedidoLogic;

@WebServlet("/AltaPedido")
public class AltaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AltaPedido() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		LinkedList<LineaPedido> carrito = (LinkedList<LineaPedido>)session.getAttribute("carrito");
		DireccionEntregaLogic direccionEntregaLogic = new DireccionEntregaLogic();
		LineaPedidoLogic lineaPedidoLogic = new LineaPedidoLogic();
		PedidoLogic pedidoLogic = new PedidoLogic();
		int idCliente = (int) session.getAttribute("sessionLogin");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		Double precioTotal = 0.0;
		
		if(request.getParameter("realizarPedido")!=null)
		{
			if(request.getParameter("callePrincipal").isEmpty())
			{
				request.setAttribute("errorCallePrincipal", "true");
				request.getRequestDispatcher("./AltaPedido.jsp").forward(request, response); 
			}
			else if(request.getParameter("altura").isEmpty())
			{
				request.setAttribute("errorAltura", "true");
				request.getRequestDispatcher("./AltaPedido.jsp").forward(request, response); 
			}
			else
			{
				DireccionEntrega direccionEntrega = new DireccionEntrega(request.getParameter("callePrincipal"),
                                                                         Integer.parseInt(request.getParameter("altura")),
                                                                         request.getParameter("piso"),
                                                                         request.getParameter("paralela1"),
                                                                         request.getParameter("paralela2"));
				direccionEntregaLogic.Alta(direccionEntrega);
				for(LineaPedido lp: carrito) { precioTotal = precioTotal + lp.getPrecioLineaPedido(); }
				Pedido pedido = new Pedido(direccionEntrega.getIdDireccion(),
						                   idCliente,
						                   "espera",
						                   dtf.format(LocalDateTime.now()),
						                   precioTotal);
				pedidoLogic.Alta(pedido);
				for(LineaPedido lp: carrito) {
					lp.setNroPedido(pedido.getNroPedido());
					lineaPedidoLogic.AltaLineaPedido(lp);
				}
				request.setAttribute("PedidoRealiazado", "true");
				session.setAttribute("carrito", null);
				request.getRequestDispatcher("./Cliente.jsp").forward(request, response);
			}
			
	
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
