package Servlets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Pedido;
import Interfacez.VerificaNuevoPedido;
import Logic.PedidoLogic;
import Logic.ProductoLogic;


@WebServlet("/ListarPedidos")
public class ListarPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListarPedidos() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PedidoLogic pedidoLogic = new PedidoLogic();
		ProductoLogic productoLogic = new ProductoLogic();
		if(request.getParameter("enEspera")!=null)//VERIFICA SI SE LE DIO CLICK AL BOTON Pedidos en Espera
		{
			request.setAttribute("enEspera", "true");
			request.setAttribute("listaPedidos", pedidoLogic.GetAllPedidos("espera"));		
			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
		}
		else if(request.getParameter("atendidos")!=null)//VERIFICA SE SE LE DIO CLICK AL BOTON Pedidos atendidos
		{
			request.setAttribute("atendidos", "true");
			request.setAttribute("listaPedidos", pedidoLogic.GetAllPedidos("atendido"));		
			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
		}
		else if (request.getParameter("atender")!=null)//VERIFICA SI SE LE DIO CLICK AL BOTON ATENDER, PARA ANTENDER LOS PEDIDOS EN ESPERA
		{
			Pedido pedido = pedidoLogic.GetOne(Integer.parseInt(request.getParameter("atender")));
			pedido.setNroPedido(Integer.parseInt(request.getParameter("atender")));
			request.setAttribute("atender", "true");
			request.setAttribute("pedido", pedido);
			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
		}
		
		else if(request.getParameter("listo")!=null)//VERIFICA SI SE LE DA CLICK AL BOTON Listo, QUE SIRVE PARA CONFIRMAR QUE SE ATENDÓ EL PEDIDO
		{	
            try {
            	Pedido pedido = pedidoLogic.GetOne(Integer.parseInt(request.getParameter("listo")));
    			pedido.setNroPedido(Integer.parseInt(request.getParameter("listo")));
    			pedido.setEstadoPedido("atendido");
    			VerificaNuevoPedido.cantPedidos = VerificaNuevoPedido.cantPedidos - 1;
    			pedido.setHoraEntrega(LocalTime.parse(request.getParameter("hora")));
    			pedidoLogic.Modify(pedido);
    			request.setAttribute("notificacion", "true");
    			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
			}catch(DateTimeParseException e) {
				Pedido pedido = pedidoLogic.GetOne(Integer.parseInt(request.getParameter("listo")));
				pedido.setNroPedido(Integer.parseInt(request.getParameter("listo")));
				request.setAttribute("atender", "true");
				request.setAttribute("pedido", pedido);
    			request.setAttribute("errorEnHora", "true");
    			request.getRequestDispatcher("./ListarPedidos.jsp").forward(request, response);
			}
			
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
