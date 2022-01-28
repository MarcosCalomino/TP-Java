<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.util.LinkedList" %>    
<%@page import = "Entities.Producto" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ListarProductos" method="post" enctype="multipart/form-data">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {%>
<nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="menues" type="submit">Menu's</button>
</nav>
-------------------------------------------------------------------------------------------------------------------  
<%LinkedList<Producto> listaProductosPorTipo = (LinkedList<Producto>)request.getAttribute("listaProductosPorTipo");
for(Producto p: listaProductosPorTipo ){%>
    <h3><%=p.getNombreProducto() %></h3>
    <img src="/ImagenesPizzeria/<%=p.getImagen() %>" width="152" height="113"><br>
    <label><%=p.getDescripcion()%></label><br>   
    <%if(p.getEstado()==true) {%>
    <p><span style="color: #00ff00;">Estado Actual: Habiltiado</span></p>
    <%} else {%>
    <p><span style="color: #ff0000;">Estado Actual: Deshabilitado</span></p>
    <%}%>   
    <nav>
     <button type="submit"  value="<%=p.getId_producto()%>" name="modificar">MODIFICAR</button>
     <button type="submit"  value="<%=p.getId_producto()%>" name="eliminar">ELIMINAR</button><br>
    </nav>   
<%}%>   
------------------------------------------------------------------------------------------------------------------
<%}%>
</form>
</body>
</html>