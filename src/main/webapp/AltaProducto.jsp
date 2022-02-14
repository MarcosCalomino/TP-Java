<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "Interfacez.VerificaNuevoPedido" %>
    <%@page import = "Logic.PedidoLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">	
</head>

<body>
<form action="AltaProducto" method="post" enctype="multipart/form-data">
<%PedidoLogic pedidoLogic = new PedidoLogic(); 
  String avisoDeCarga = (String)request.getAttribute("avisoDeCarga");
  String tipoProducto = (String)request.getAttribute("tipoProducto");
  String nombreProducto = (String)request.getAttribute("nombreProducto");
  String descripcionProducto = (String)request.getAttribute("descripcionProducto");
  Double precioProducto = (Double)request.getAttribute("precioProducto");
  String error = (String)request.getAttribute("error");
  if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");}//SI NO HAY UNA SESSION CREADA REDIRIGE AL LOGIN
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
  <h3>INGRESE DATOS DEL PRODUCTO</h3><br>
  <%if(avisoDeCarga!=null){%>
    <%if(avisoDeCarga.equals("cargado")){%>
      <p><span style="color: #00ff00;">Producto cargado con exito</span></p>
    <%} else {%>
      <p><span style="color: #ff0000;">El producto ya existe</span></p>
    <%}%>
  <%}%>
  <input type="hidden" name="tipoProducto" value="<%=tipoProducto%>">
  <%if((String)request.getAttribute("nombreProducto")==null){%>
   <input name="nombreProducto" placeholder="Ingrese nombre" type="text" class="form-control w-50"><br>
  <%} else {%>
   <input name="nombreProducto" placeholder="Ingrese nombre" value="<%=(String)request.getAttribute("nombreProducto")%>" type="text" class="form-control w-50"><br>
  <%}%>
 
  <%if((String)request.getAttribute("descripcionProducto")==null){%>
   <textarea name="descripcionProducto" placeholder="descripcion" class="form-control w-50"></textarea><br>
  <%} else {%>
   <textarea name="descripcionProducto" placeholder="descripcion" class="form-control w-50"><%=(String)request.getAttribute("descripcionProducto")%></textarea><br>
  <%}%>
  
  <%if((Double)request.getAttribute("precioProducto")==null){%>
  <div class="input-group mb-3 w-50">
    <span class="input-group-text">$</span>
      <input name="precioProducto" placeholder="precio" type="text" class="form-control"><br>
    <span class="input-group-text">.00</span>
  </div>
  <%} else {%>
  <div class="input-group mb-3 w-50">
    <span class="input-group-text">$</span>
      <input name="precioProducto" placeholder="precio" type="text" class="form-control" value="<%=(Double)request.getAttribute("precioProducto")%>"><br>
    <span class="input-group-text">.00</span>
  </div>
  <%}%>
  <font size="6">Habilitado</font>
  <select name="estadoProducto" class="btn btn-outline-secondary dropdown-toggle">  
    <option value="1">Si</option>
    <option value="2">No</option>
  </select>
  <br>
  <br>
  <br>
  <h4>Seleccione Imagen</h4>
  <input type="file" name="imagenProducto" class="form-control form-control-lg w-50" placeholder="seleccionar imagen">
  <br>
  <button name="cargar" type="submit" class="btn btn-secondary">Cargar</button>
  
  <%if(error!=null){%>
  <%if(error.equals("erroEnPrecio")){%>
  <h4><span style="color: #ff0000;">Error en precio</span></h4>
  <%} 
  else if(error.equals("errorCampoVacio")){%>
  <h4><span style="color: #ff0000;">Existen campos vacios</span></h4>
  <%}
  else if(error.equals("")){%>
  <h4><span style="color: #ff0000;"></span></h4>
  <%}%>
  <%}%>
  <%}%>
</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
