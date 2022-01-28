<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "Entities.Producto" %> 
<%@page import = "Entities.Precio" %> 
<%@page import = "Logic.PrecioLogic" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<form action="ModificarEliminar" method="post" enctype="multipart/form-data">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {%>
<nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="menues" type="submit">Menu's</button>
</nav>
<%}%>
----------------------------------------------------------------------------------------
<%Producto producto = (Producto)request.getAttribute("producto");
  String modo = (String)request.getAttribute("modo");
  PrecioLogic precioLogic = new PrecioLogic();
  String error = (String)request.getAttribute("error");
  Precio precio = precioLogic.GetOne(producto.getNroPrecio());%>
<%if(modo.equals("modoModificar")){%>
    <input type="hidden" name="tipoProducto" value="<%=producto.getTipoProducto()%>"><br>
    <input type="hidden" name="nroPrecio" value="<%=producto.getNroPrecio()%>"><br>
    <input name="nombreProducto" placeholder="Ingrese nombre" value="<%=producto.getNombreProducto()%>" type="text"><br>
    <textarea name="descripcionProducto" placeholder="descripcion"><%=producto.getDescripcion()%></textarea><br>
    <input name="precioProducto" placeholder="precio" value="<%=precio.getPrecio()%>" type="text"><br>
    <%if(producto.getEstado()==true) {%>
    <label>Habilitado</label>
    <select name="estadoProducto">  
    <option value="1" selected>Si</option>
    <option value="2">No</option>
    </select><br>
    <%} else {%>
    <label>Habilitado</label>
    <select name="estadoProducto">  
    <option value="1">Si</option>
    <option value="2"selected>No</option>
    </select><br>
    <%}%>
    <label>Seleccione Imagen</label>
    <input type="file" name="imagenProducto" value="">
    <br>
    <button name="modificar" type="submit" value="<%=producto.getId_producto()%>">MODIFICAR</button>
<%} else {%>
    <input type="hidden" name="nroPrecio" value="<%=producto.getNroPrecio()%>"><br>
    <h3><%=producto.getNombreProducto() %></h3>
    <img src="/ImagenesPizzeria/<%=producto.getImagen() %>" width="152" height="113"><br>
    <label><%=producto.getDescripcion()%></label><br>   
    <%if(producto.getEstado()==true) {%>
    <p><span style="color: #00ff00;">Estado Actual: Habiltiado</span></p>
    <%} else {%>
    <p><span style="color: #ff0000;">Estado Actual: Deshabilitado</span></p>
    <%}%>   
    <br>
    <button name="eliminar" type="submit" value="<%=producto.getId_producto()%>">ELIMINAR</button>
<%}%>
<%if(error!=null){%>
  <%if(error.equals("erroEnPrecio")){%>
  <p><span style="color: #ff0000;">Error en precio</span></p>
  <%} 
  else if(error.equals("errorCampoVacio")){%>
  <p><span style="color: #ff0000;">Existen campos vacios</span></p>
  <%}
  else if(error.equals("")){%>
  <p><span style="color: #ff0000;"></span></p>
  <%}%>
<%}%>    

</form>
</body>
</html>




   <%--  <input type="hidden" name="tipoProducto" value="<%=producto.getTipoProducto()%>"><br>
    <input name="nombreProducto" placeholder="Ingrese nombre" value="<%=producto.getNombreProducto()%>" type="text"><br>
    <textarea name="descripcionProducto" placeholder="descripcion"><%=producto.getDescripcion()%></textarea><br>
    <input name="precioProducto" placeholder="precio" value="<%=precio.getPrecio()%>" type="text"><br>
    <%if(producto.getEstado()==true) {%>
    <label>Habilitado</label>
    <select name="estadoProducto">  
    <option value="1" selected>Si</option>
    <option value="2">No</option>
    </select><br>
    <%} else {%>
    <label>Habilitado</label>
    <select name="estadoProducto">  
    <option value="1">Si</option>
    <option value="2"selected>No</option>
    </select><br>
    <%}%>
    <label>Seleccione Imagen</label>
    <input type="file" name="imagenProducto" value="">
    <br> --%>