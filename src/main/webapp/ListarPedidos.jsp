<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "Entities.Pedido"%>
    <%@page import = "Entities.Producto"%>
    <%@page import = "Entities.Cliente"%>
    <%@page import = "Entities.LineaPedido"%>
    <%@page import = "Logic.LineaPedidoLogic"%>
    <%@page import = "Logic.ProductoLogic"%>
    <%@page import = "Logic.PedidoLogic"%>
    <%@page import = "Logic.ClienteLogic"%>
    <%@page import= "java.util.LinkedList"%>
    <%@ page import = "Interfacez.VerificaNuevoPedido" %>
    <%@ page import = "javax.swing.Timer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<form action="ListarPedidos" method="get">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {
String errorEnHora = (String)request.getAttribute("errorEnHora");
String notificacion = (String)request.getAttribute("notificacion");
String enEspera = (String)request.getAttribute("enEspera");
String atendidos = (String)request.getAttribute("atendidos");
String atender = (String)request.getAttribute("atender");
LinkedList<Pedido> listaPedidos = (LinkedList<Pedido>)request.getAttribute("listaPedidos");
LinkedList<LineaPedido> listaLineaPedidosPorPedidos = new LinkedList<LineaPedido>();
LineaPedidoLogic lineaPedidoLogic = new LineaPedidoLogic();
ClienteLogic clienteLogic = new ClienteLogic();
Cliente cliente = new Cliente();
Pedido pedido = new Pedido();
PedidoLogic pedidoLogic = new PedidoLogic();
Producto producto = new Producto();
ProductoLogic productoLogic = new ProductoLogic();%>

<nav class="navbar navbar-light bg-light border border-dark">
   <div class="container-fluid justify-content-start">
    <div class="row">
     <div class="col">
      <button name="pedidos" type="submit" class="btn btn-outline-dark">Pedidos(<%= pedidoLogic.GetAllPedidos("espera").size() %>)</button>
     </div>
     <div class="col">
      <button name="menues" type="submit" class="btn btn-outline-dark">Menu's</button>
     </div>
    </div>  
   </div>
</nav>

<nav class="navbar navbar-light bg-light border-bottom">
   <div class="container-fluid justify-content-start">
    <div class="row">
      <div class="col">
       <button name="enEspera" type="submit" class="btn btn-outline-dark btn-sm">Pedidos en espera</button>
      </div>
      <div class="col-12">
       <button name="atendidos" type="submit" class="btn btn-outline-dark btn-sm">Pedidos atendidos</button>
      </div>
    </div>
   </div> 
</nav>
<br>
<br>
<%if(notificacion!=null){%>
<p><span style="color: #00ff00;">Operacion exitosa</span></p>
<%}%>
  
  <!-- SI SE LE DA CLICK AL BOTON PEDIDOS EN ESPERA SE MUESTRA LO SIEGUIENTE -->
<%if(enEspera!=null){%>
  <h3>PEDIDOS EN ESPERA</h3>
  <%for(Pedido p: listaPedidos)
  {
    listaLineaPedidosPorPedidos = lineaPedidoLogic.GetAll(p.getNroPedido()); 
    cliente = clienteLogic.getOne(p.getNroCliente());%> 
    <div class="border border-bottom-4">
    <br><label><%=cliente.getNombreCliente()%></label> 
    <label><%=cliente.getApellidoClienta()%></label><br>
    <label>Fecha Pedido: <%=p.getFechaHoraPedido()%></label><br>
    <label>Estado: <%=p.getEstadoPedido()%></label><br>
    <%for(LineaPedido lp: listaLineaPedidosPorPedidos){
       producto = productoLogic.GetOne(lp.getIdProducto());
    %>
    <label><b><%=producto.getNombreProducto()%></b></label> Cantidad:<lable><%=lp.getCantidad()%></lable>....$<label><%=lp.getPrecioLineaPedido()%></label><br>   
    <%}%>
    <label>Precio Total: <%=p.getPrecioTotal() %></label><br>
    <button name="atender" value="<%=p.getNroPedido()%>" type="submit" class="btn btn-secondary">Atender</button><br>
    </div>  
  <%}%>
  <%} else if (atendidos!=null){%><!-- SI SE LE DA CLICK AL BOTON PEDIDOS ATENDIDOS SE MUESTRA LO SIEGUIENTE -->
       <h3>PEDIDOS ATENDIDOS</h3>
       <%for(Pedido p: listaPedidos)
       {
       listaLineaPedidosPorPedidos = lineaPedidoLogic.GetAll(p.getNroPedido()); 
       cliente = clienteLogic.getOne(p.getNroCliente());%>   
       <div class="border border-bottom-4">
       <br><label><%=cliente.getNombreCliente()%></label> 
       <label><%=cliente.getApellidoClienta()%></label><br>
       <label>Fecha Pedido: <%=p.getFechaHoraPedido()%></label><br>
       <label>Hora de Entrega: <%=p.getHoraEntrega()%></label><br>
       <%for(LineaPedido lp: listaLineaPedidosPorPedidos){
       producto = productoLogic.GetOne(lp.getIdProducto());
       %>
      <label><b><%=producto.getNombreProducto()%></b></label> Cantidad:<lable><%=lp.getCantidad()%></lable>....$<label><%=lp.getPrecioLineaPedido()%></label><br>   
      <%}%>
      <label>Precio Total: <%=p.getPrecioTotal() %></label><br>
      </div>
     <%}%>
  <%} else if(atender!=null){
      pedido = (Pedido)request.getAttribute("pedido"); 
      cliente = clienteLogic.getOne(pedido.getNroCliente());
      listaLineaPedidosPorPedidos = lineaPedidoLogic.GetAll(pedido.getNroPedido()); %>
      <br><label><%=cliente.getNombreCliente()%></label> 
      <label><%=cliente.getApellidoClienta()%></label><br>
      <label>Fecha Pedido: <%=pedido.getFechaHoraPedido()%></label><br>
      <label>Estado: <%=pedido.getEstadoPedido()%></label><br>
      <label>Precio Total: <%=pedido.getPrecioTotal() %></label><br>
     <label>Ingrese hora de entrega: </label>
     <input name="hora" type="time">
     <button name="listo" value="<%=pedido.getNroPedido()%>" type="submit" class="btn btn-secondary">Listo</button><br>
     <%if(errorEnHora!=null){%>
     <p><span style="color: #ff0000;">Ingrese Hora!</span></p>
     <%}%>
  <%}%>
<%}%>
</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>