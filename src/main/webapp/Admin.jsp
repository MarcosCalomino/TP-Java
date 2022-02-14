<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.LinkedList" %>
<%@ page import = "Entities.TipoProducto" %>
<%@ page import = "Entities.Pedido" %>
<%@ page import = "Logic.PedidoLogic" %>
<%@ page import = "Interfacez.VerificaNuevoPedido" %>
<!DOCTYPE html>
<html>

<head>
 <meta charset="ISO-8859-1">
 <title>Inicio Admin</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<form action="Admin" method="post">

<%
if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {
String pedidos = (String)request.getAttribute("pedidos");
String menues = (String)request.getAttribute("menues");
String tipoProducto = (String)request.getAttribute("tipoProducto");
String notificacion = (String)request.getAttribute("notificacion");
PedidoLogic pedidoLogic = new PedidoLogic();
LinkedList<Pedido> listaPedidos = pedidoLogic.GetAllPedidos("espera");
%>
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
<br>
<br>
  
<%if(notificacion != null){%>
<%if(notificacion.equals("modificado")){ %>
    <p><span style="color: #00ff00;">Producto Modificado con exito</span></p>
<%} else if(notificacion.equals("eliminado")){%>
    <p><span style="color: #00ff00;">Producto Eliminado con exito</span></p>
<%} else if(notificacion.equals("cargado")){%>
    <p><span style="color: #00ff00;">Producto cargado con exito</span></p>
<%}else if(notificacion.equals("noCargado")){%>
    <p><span style="color: #ff0000;">Producto no cargado(nombre/descripcion estan en blanco)</span></p>
<%}%>  
<%}%>


 <!--*****SI LE DA CLICK A MENUES SE MUESTRA ESTA PARTE*****-->
 <%if(menues!=null){%>
 <h1>Tipos de Productos</h1>
 <br>
 <%LinkedList<TipoProducto> listaTipoProductos = (LinkedList<TipoProducto>)request.getAttribute("listaTipoProductos");
 for(TipoProducto tp: listaTipoProductos){%>
   <div class="row pt-5 mx-5 border border-dark rounded w-50">
    <div class="col-md-4">
     <div class="col-auto  pb-1"> 
       <h5><%= tp.getDescripcion().toUpperCase()%></h5>
       <button name="agregar" type="submit"  value="<%=tp.getDescripcion()%>" class="btn btn-secondary border border-dark">AGREGAR NUEVO</button><br>
       <button name="listarSegunProducto" type="submit"  value="<%=tp.getDescripcion()%>" class="btn btn-secondary border border-dark">LISTAR</button><br>
     </div> 
    </div>
   </div>
   <br>
 <%}%> 
 <%}%>
<%}%>
</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>