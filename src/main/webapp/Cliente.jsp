<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entities.Pedido"%>
    <%@page import="Entities.LineaPedido"%>
    <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio Cliente</title>
</head>
<body>
<form action="Cliente" method="post" enctype="multipart/form-data">
  <%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
  else{%> 
  <%String pedidoRealizado = (String)request.getAttribute("PedidoRealiazado");
    LinkedList<Pedido> lista = (LinkedList<Pedido>)request.getAttribute("listaPedidosDeCliente");
    %>
  <nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="NuevoPedido" type="submit">Realizar Pedido</button>
   <button name="cerrarSession" type="submit">Log Out</button>
  </nav>
  
  <%if(pedidoRealizado!=null){%>
  <p><span style="color: #00ff00;">Pedido Realizado con exito. En breve se le informa hora de entrega. Revise sus pedidos</span></p>
  <%} else if(lista!=null){
      for(Pedido p: lista){%>
      <label><%=p.getEstadoPedido()%></label><br> 
      <label><%=p.getFechaHoraPedido()%></label><br> 
  <%}%>    
  <%}%>
  <%}%>

  
</form>
</body>
</html>