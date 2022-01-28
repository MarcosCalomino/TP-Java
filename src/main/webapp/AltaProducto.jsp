<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AltaProducto" method="post" enctype="multipart/form-data">
<%String avisoDeCarga = (String)request.getAttribute("avisoDeCarga");
  String tipoProducto = (String)request.getAttribute("tipoProducto");
  String nombreProducto = (String)request.getAttribute("nombreProducto");
  String descripcionProducto = (String)request.getAttribute("descripcionProducto");
  Double precioProducto = (Double)request.getAttribute("precioProducto");
  String error = (String)request.getAttribute("error");
  if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");}//SI NO HAY UNA SESSION CREADA REDIRIGE AL LOGIN
  else {%>
  <nav>
   <button name="pedidos" type="submit">Pedidos</button>
   <button name="menues" type="submit">Menu's</button>
  </nav>
---------------------------------------------------------------------------------------------------------------------------------------
  <h3>INGRESE DATOS DEL PRODUCTO</h3><br>
  <%if(avisoDeCarga!=null) {%>
    <%if(avisoDeCarga.equals("cargado")){%>
      <p><span style="color: #00ff00;">Producto cargado con exito</span></p>
    <%} else {%>
      <p><span style="color: #ff0000;">El producto ya existe</span></p>
    <%}%>
  <%}%>
  <input type="hidden" name="tipoProducto" value="<%=tipoProducto%>">
  <%if((String)request.getAttribute("nombreProducto")==null){%>
   <input name="nombreProducto" placeholder="Ingrese nombre" type="text"><br>
  <%} else {%>
   <input name="nombreProducto" placeholder="Ingrese nombre" value="<%=(String)request.getAttribute("nombreProducto")%>" type="text"><br>
  <%}%>
 
  <%if((String)request.getAttribute("descripcionProducto")==null){%>
   <textarea name="descripcionProducto" placeholder="descripcion"></textarea><br>
  <%} else {%>
   <textarea name="descripcionProducto" placeholder="descripcion"><%=(String)request.getAttribute("descripcionProducto")%></textarea><br>
  <%}%>
  
  <%if((Double)request.getAttribute("precioProducto")==null){%>
   <input name="precioProducto" placeholder="precio" type="text"><br>
  <%} else {%>
   <input name="precioProducto" placeholder="precio" value="<%=(Double)request.getAttribute("precioProducto")%>" type="text"><br>
  <%}%>
    
  <label>Habilitado</label>
  <select name="estadoProducto">  
     <option value="1">Si</option>
     <option value="2">No</option>
  </select><br>
  <label>Seleccione Imagen</label>
  <input type="file" name="imagenProducto">
  <br>
  <button name="cargar" type="submit">Cargar</button>
  
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
  <%}%>
</form>
</body>
</html>
