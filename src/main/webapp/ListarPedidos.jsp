<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "Entities.Pedido"%>
    <%@page import = "Entities.Producto"%>
    <%@page import = "Entities.Cliente"%>
    <%@page import = "Entities.LineaPedido"%>
    <%@page import = "Logic.LineaPedidoLogic"%>
    <%@page import = "Logic.ProductoLogic"%>
    <%@page import = "Logic.ClienteLogic"%>
    <%@page import= "java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedidos</title>
</head>
<body>
<form action="ListarPedidos" method="get">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {%>
  <%String notificacion = (String)request.getAttribute("notificacion");
    String enEspera = (String)request.getAttribute("enEspera");
    String atendidos = (String)request.getAttribute("atendidos");
    String atender = (String)request.getAttribute("atender");
    LinkedList<Pedido> listaPedidos = (LinkedList<Pedido>)request.getAttribute("listaPedidos");
    LinkedList<LineaPedido> listaLineaPedidosPorPedidos = new LinkedList<LineaPedido>();
    LineaPedidoLogic lineaPedidoLogic = new LineaPedidoLogic();
    ClienteLogic clienteLogic = new ClienteLogic();
    Cliente cliente = new Cliente();
    Pedido pedido = new Pedido();
    Producto producto = new Producto();
    ProductoLogic productoLogic = new ProductoLogic();%>

  <nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="menues" type="submit">Menu's</button>
  </nav>
  ---------------------------------------------------------------------------------------------------------------------------------
   <nav>
   <button name="enEspera" type="submit">Pedidos en espera</button>
   <button name="atendidos" type="submit">Pedidos atendidos</button>
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
    <button name="atender" value="<%=p.getNroPedido()%>" type="submit">Atender</button><br>
    ---------------------------------------------------------------------------------------------------------------------------------
  <%}%>
  <%} else if (atendidos!=null){%><!-- SI SE LE DA CLICK AL BOTON PEDIDOS ATENDIDOS SE MUESTRA LO SIEGUIENTE -->
       <h3>PEDIDOS ATENDIDOS</h3>
       <%for(Pedido p: listaPedidos)
       {
       listaLineaPedidosPorPedidos = lineaPedidoLogic.GetAll(p.getNroPedido()); 
       cliente = clienteLogic.getOne(p.getNroCliente());%>   
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
       ---------------------------------------------------------------------------------------------------------------------------------
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
     <button name="listo" value="<%=pedido.getNroPedido()%>" type="submit">Listo</button>
  <%}%>
<%}%>
</form>
</body>
</html>