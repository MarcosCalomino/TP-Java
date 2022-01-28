<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "Entities.Producto" %>  
<%@page import = "Entities.Precio" %>  
<%@page import = "Entities.LineaPedido" %> 
<%@page import = "Logic.PrecioLogic" %>  
<%@page import = "Logic.ProductoLogic" %>
<%@page import = "java.util.LinkedList" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PRODUCTOS!</title>
</head>
<body>
<form action="AltaLineaPedido" method="post" enctype="multipart/form-data">
  <%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
  else{%>
  <%
  String errorCarritoVacio = (String)request.getAttribute("errorCarritoVacio");
  String PresButtonPizzas = (String)request.getAttribute("PresButtonPizzas");
  String PresButtonBebidas = (String)request.getAttribute("PresButtonBebidas");
  String PresButtonPostres = (String)request.getAttribute("PresButtonPostres"); 
  LinkedList<LineaPedido> carrito = (LinkedList<LineaPedido>)session.getAttribute("carrito");
  LinkedList<Producto> listaProductos = (LinkedList<Producto>)request.getAttribute("listaProductos");
  PrecioLogic precioLogic = new PrecioLogic();  
  %>
  
  <%if(carrito!=null){%>
   <nav>
   <h2>PEDIDO</h2>
   <%ProductoLogic pl = new ProductoLogic();
     for(LineaPedido lp: carrito){
     Producto producto = pl.GetOne(lp.getIdProducto());%>
     <label><b><%=producto.getNombreProducto()%></b></label> Cantidad:<lable><%=lp.getCantidad()%></lable>....$<label><%=lp.getPrecioLineaPedido()%></label> 
     <button name="eliminarDeCarrito" type="submit" value="<%=lp.getNroLineaPedido()%>" style="background-color:red">ELIMINAR</button><br>
   <%}%>
     <%if(errorCarritoVacio!=null){%>
     <p><span style="color: #ff0000;">ERROR! CARRITO VACIO!!</span></p>
     <%}%>
     <button name="realizarPedido" type="submit" style="background-color:green">REALIZAR PEDIDO</button>
     <br>
  </nav>
  <%}%>
  ---------------------------------------------------------------------------------------------------------  
  <nav>
   <button name="pizzas" type="submit">Pizzas</button>
   <button name="bebidas" type="submit">Bebidas</button>
   <button name="postres" type="submit">Postres</button>
  </nav>
  ---------------------------------------------------------------------------------------------------------  
  
  <%if(PresButtonPizzas!=null){%>
    <label>Cantidad:</label> 
    <select name="cantidad">  
    <option value="0.5">1/2</option>
    <option value="1">1</option>
    <option value="1.5">1 y 1/2</option>
    <option value="2">2</option>
    <option value="2.5">2 y 1/2</option>
    </select>
  <%for(Producto p: listaProductos){
    if(p. getEstado() && p.getTipoProducto().equals("Pizzas")){
    Precio precio = precioLogic.GetOne(p.getNroPrecio());%>
    <h3><%=p.getNombreProducto()%></h3>
    <label><%=p.getDescripcion()%></label>
    <br>    
    <img src="/ImagenesPizzeria/<%=p.getImagen() %>" width="152" height="113">
    <h3>$<%=precio.getPrecio()%></h3> 	
    <button name="agregarACarrito" type="submit" value="<%=p.getId_producto()%>">AGREGAR A CARRITO</button>
    <br>
    -------------------------------------------------------------------------------------------------------
  <%}%>
  <%}%>
  <%}else if(PresButtonBebidas!=null){%> 
     <label>Cantidad:</label> 
     <select name="cantidad">  
     <option value="1">1</option>
     <option value="2">2</option>
     <option value="3">3</option>
     <option value="4">4</option>
     <option value="5">5</option>
     </select>	
   <%for(Producto p: listaProductos){ 
	 if(p. getEstado() && p.getTipoProducto().equals("Bebidas")){ 
     Precio precio = precioLogic.GetOne(p.getNroPrecio()); %>
     <h3><%=p.getNombreProducto()%></h3>
     <label><%=p.getDescripcion()%></label>
     <br>    
     <img src="/ImagenesPizzeria/<%=p.getImagen() %>" width="152" height="113">
     <h3>$<%=precio.getPrecio()%></h3> 
     <button name="agregarACarrito" type="submit" value="<%=p.getId_producto()%>">AGREGAR A CARRITO</button>
     <br>
      -------------------------------------------------------------------------------------------------------
  <%}%>
  <%}%>
  <%}else if(PresButtonPostres!=null){%>
     <label>Cantidad:</label> 
     <select name="cantidad">  
     <option value="1">1</option>
     <option value="2">2</option>
     <option value="3">3</option>
     <option value="4">4</option>
     <option value="5">5</option>
     </select>	
   <%for(Producto p: listaProductos){
     if(p. getEstado() && p.getTipoProducto().equals("Postres")){
     Precio precio = precioLogic.GetOne(p.getNroPrecio());%>
     <h3><%=p.getNombreProducto()%></h3>
     <label><%=p.getDescripcion()%></label>
     <br>    
     <img src="/ImagenesPizzeria/<%=p.getImagen() %>" width="152" height="113">
     <h3>$<%=precio.getPrecio()%></h3> 
     <button name="agregarACarrito" type="submit" value="<%=p.getId_producto()%>">AGREGAR A CARRITO</button>
     <br>
     -------------------------------------------------------------------------------------------------------
  <%}%>   
  <%}%>
  <%}%>
  
 
  <%}%>
</form>
</body>
</html>