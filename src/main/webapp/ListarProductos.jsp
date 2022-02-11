<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.LinkedList" %>    
<%@page import = "Logic.PedidoLogic"%>
<%@page import = "Entities.Producto" %>  
<%@ page import = "Interfacez.VerificaNuevoPedido" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
</head>


<body>
<form action="ListarProductos" method="post" enctype="multipart/form-data">
<%PedidoLogic pedidoLogic = new PedidoLogic(); %> 
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {%>

<nav class="navbar navbar-light bg-light border border-dark">
   <div class="container-fluid justify-content-start">
    <div class="row">
     <div class="col">
      <button name="pedidos" type="submit" class="btn btn-outline-dark">Pedidos(<%= pedidoLogic.GetAllPedidos("espera").size()%>)</button>
     </div>
     <div class="col">
      <button name="menues" type="submit" class="btn btn-outline-dark">Menu's</button>
     </div>
    </div>
   </div> 
</nav>
 
<%LinkedList<Producto> listaProductosPorTipo = (LinkedList<Producto>)request.getAttribute("listaProductosPorTipo");
for(Producto p: listaProductosPorTipo ){%>
<div class="border border-dark rounded mt-5 bg-light">
    <h3><%=p.getNombreProducto() %></h3>
    <img src="/ImagenesPizzeria/<%=p.getImagen() %>" width="152" height="113"><br>
    <label><%=p.getDescripcion()%></label><br>   
    <%if(p.getEstado()==true) {%>
    <p><span style="color: #00ff00;">Estado Actual: Habiltiado</span></p>
    <%} else {%>
    <p><span style="color: #ff0000;">Estado Actual: Deshabilitado</span></p>
    <%}%>   
    <nav>
     <button type="submit"  value="<%=p.getId_producto()%>" name="modificar" class="btn btn-secondary mb-1">MODIFICAR</button>
     <button type="submit"  value="<%=p.getId_producto()%>" name="eliminar" class="btn btn-secondary mb-1">ELIMINAR</button><br>
    </nav>  
</div> 
<%}%>   

<%}%>
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>