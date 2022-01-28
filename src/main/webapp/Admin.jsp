<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.LinkedList" %>
<%@ page import = "Entities.TipoProducto" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Inicio Admin</title>
</head>

<body>
<form action="Admin" method="post">
<%String pedidos = (String)request.getAttribute("pedidos");
  String menues = (String)request.getAttribute("menues");
  String tipoProducto = (String)request.getAttribute("tipoProducto");
  String notificacion = (String)request.getAttribute("notificacion");
/*String agregar = (String)request.getAttribute("agregar");
  String listarSegunProducto = (String)request.getAttribute("listarSegunProducto");*/
  
  if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
  else {%>
  <nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="menues" type="submit">Menu's</button>
  </nav>
  ---------------------------------------------------------------------------------------------------------------------------------
  <%if(notificacion != null){%>
  <%if(notificacion.equals("modificado")){ %>
    <p><span style="color: #00ff00;">Producto Modificado con exito</span></p>
  <%} else if(notificacion.equals("eliminado")){%>
     <p><span style="color: #00ff00;">Producto Eliminado con exito</span></p>
  <%} else if(notificacion.equals("cargado")){%>
      <p><span style="color: #00ff00;">Producto cargado con exito</span></p>
  <%}else {%>
    <p><span style="color: #ff0000;">El producto ya existe</span></p>
  <%}%>  
  <%}%>
 <!--*** SI LE DA CLICK A MENUES SE MUESTRA ESTA PARTE -->
 <%if(menues!=null){%>
 <h1>Tipos de Productos</h1>
 <%LinkedList<TipoProducto> listaTipoProductos = (LinkedList<TipoProducto>)request.getAttribute("listaTipoProductos");
 for(TipoProducto tp: listaTipoProductos){%>
 <h6><%= tp.getDescripcion().toUpperCase()%></h6>
 <nav>
 <button type="submit"  value="<%=tp.getDescripcion()%>" name="agregar">AGREGAR NUEVO</button><br>
 <button type="submit"  value="<%=tp.getDescripcion()%>" name="listarSegunProducto">LISTAR</button><br>
 </nav>
 <%}%>
 <%}%>
<%}%>
</form>
</body>
</html>