<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.util.LinkedList" %> 
    <%@page import = "Entities.LineaPedido" %> 
    <%@page import = "Entities.Producto" %>  
    <%@page import = "Logic.ProductoLogic" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DIRECCION</title>
</head>
<body>
<form action="AltaPedido" method="get">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
  else{%>
  <%LinkedList<LineaPedido> carrito = (LinkedList<LineaPedido>)session.getAttribute("carrito");
    String errorCallePrincipal = (String)request.getAttribute("errorCallePrincipal");
    String errorAltura = (String)request.getAttribute("errorAltura");
    Double precioTotal = 0.0;%>
  
   <nav>
   <h2>PEDIDO</h2>
   <%ProductoLogic pl = new ProductoLogic();
     for(LineaPedido lp: carrito){
     Producto producto = pl.GetOne(lp.getIdProducto());%>
     <label><b><%=producto.getNombreProducto()%></b></label> Cantidad:<lable><%=lp.getCantidad()%></lable>....$<label><%=lp.getPrecioLineaPedido()%></label><br> 
     <%precioTotal = precioTotal + lp.getPrecioLineaPedido(); %>
   <%}%> 
     <br>
     <label>Precio Total: <%=precioTotal %></label>
  </nav>
  <h3>INGRESE DIRECCIóN</h3>
  <%if(errorCallePrincipal!=null){%>
  <input name="callePrincipal" placeholder="calle" type="text"><p><span style="color: #ff0000;">ERROR!</span></p><br>
  <%} else {%>
  <input name="callePrincipal" placeholder="calle" type="text"><br>
  <%}%>
  <%if(errorAltura!=null){%>
  <input name="altura" placeholder="altura" type="text"><p><span style="color: #ff0000;">ERROR!</span></p><br>
  <%} else {%>
  <input name="altura" placeholder="altura" type="text"><br>
  <%}%>
  <input name="piso" placeholder="piso - opcional" type="text"><br>
  <input name="paralela1" placeholder="paralela 1 - opcional" type="text"><br>
  <input name="paralela2" placeholder="paralela 2 - opcional" type="text"><br>
  <button name="realizarPedido" type="submit" style="background-color:green">REALIZAR PEDIDO</button>
  <%}%>
</form>
</body>
</html>