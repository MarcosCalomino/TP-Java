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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
</head>

<body>
<form action="Cliente" method="post" enctype="multipart/form-data">
  <%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
  else{%> 
  <%String pedidoRealizado = (String)request.getAttribute("PedidoRealiazado");
    LinkedList<Pedido> lista = (LinkedList<Pedido>)request.getAttribute("listaPedidosDeCliente");
    %>
  <nav class="navbar navbar-light bg-light border border-dark">
   <div class="container-fluid justify-content-start">
    <div class="row-12">
     <div class="col">		
		  <button name="pedidos" type="submit" class="btn btn-outline-dark">Pedidos</button>
     </div>
    </div>   
    <div class="row-12">
     <div class="col">		
		  <button name="NuevoPedido" type="submit" class="btn btn-outline-dark">Realizar Pedido</button>
     </div>
    </div>    
    <div class="row-12">
     <div class="col">		
		  <button name="cerrarSession" type="submit" class="btn btn-outline-dark">Log Out</button>
     </div>
    </div>
   </div>
  </nav>
  
  <%if(pedidoRealizado!=null){%>
  <p><span style="color: #00ff00;">Pedido Realizado con exito. En breve se le informa hora de entrega. Revise sus pedidos</span></p>
  <%} else if(lista!=null){
      for(Pedido p: lista){%>
      <div class="mt-5 border-bottom">
       <h6><%=p.getEstadoPedido()%></h6>
       <label><%=p.getFechaHoraPedido()%></label>
      </div>
  <%}%>    
  <%}%>
  <%}%>

  
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>