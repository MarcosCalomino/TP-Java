<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "Entities.Producto" %> 
<%@page import = "Entities.Precio" %> 
<%@page import = "Logic.PrecioLogic" %>
<%@ page import = "Interfacez.VerificaNuevoPedido" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
</head>

<body>
<form action="ModificarEliminar" method="post" enctype="multipart/form-data">
<%if(session.getAttribute("sessionLogin")==null){response.sendRedirect("Login.jsp");} //SI NO HAY UNA SESSION CREADA REDIRIGE A LA PAGINA LOGIN
else {%>
<nav class="navbar navbar-light bg-light border border-dark">
   <div class="container-fluid justify-content-start">
    <div class="row">
     <div class="col">
      <button name="pedidos" type="submit" class="btn btn-outline-dark">Pedidos(<%= VerificaNuevoPedido.cantPedidos %>)</button>
     </div>
     <div class="col">
      <button name="menues" type="submit" class="btn btn-outline-dark">Menu's</button>
     </div>
    </div>
   </div> 
</nav>
<%}%>
<%Producto producto = (Producto)request.getAttribute("producto");
  String modo = (String)request.getAttribute("modo");
  PrecioLogic precioLogic = new PrecioLogic();
  String error = (String)request.getAttribute("error");
  Precio precio = precioLogic.GetOne(producto.getNroPrecio()); %>
<%if(modo.equals("modoModificar")){%>
    <input type="hidden" name="tipoProducto" value="<%=producto.getTipoProducto()%>"><br>
    <input type="hidden" name="nroPrecio" value="<%=producto.getNroPrecio()%>"><br>
    <input name="nombreProducto" placeholder="Ingrese nombre" value="<%=producto.getNombreProducto()%>" type="text" class="form-control w-50"><br>
    <textarea name="descripcionProducto" placeholder="descripcion" class="form-control w-50"><%=producto.getDescripcion()%></textarea><br>
    <%if(precio==null){ %>
    <div class="input-group mb-3 w-50">
     <span class="input-group-text">$</span>
      <input name="precioProducto" placeholder="precio" type="text" class="form-control" value=""><br>
     <span class="input-group-text">.00</span>
    </div>
    <%} else {%>
    <div class="input-group mb-3 w-50">
     <span class="input-group-text">$</span>
      <input name="precioProducto" placeholder="precio" type="text" class="form-control" value="<%=precio.getPrecio()%>"><br>
     <span class="input-group-text">.00</span>
    </div>
    <%}%>
    <%if(producto.getEstado()==true) {%>
    <font size="6">Habilitado</font>
    <select name="estadoProducto" class="btn btn-outline-secondary dropdown-toggle">  
     <option value="1" selected>Si</option>
     <option value="2">No</option>
    </select><br><br><br>
    <%} else {%>
    <font size="6">Habilitado</font>
    <select name="estadoProducto" class="btn btn-outline-secondary dropdown-toggle">  
     <option value="1">Si</option>
     <option value="2"selected>No</option>
    </select><br><br><br>
    <%}%>
    <h4>Seleccione Imagen</h4>
    <input type="file" name="imagenProducto" class="form-control form-control-lg w-50" placeholder="seleccionar imagen">
    <br>
    <button name="modificar" type="submit" value="<%=producto.getId_producto()%>" class="btn btn-secondary mb-1">MODIFICAR</button>
<%}else{%>
<div class="border border-dark rounded mt-5 bg-light">
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
    <button name="eliminar" type="submit" value="<%=producto.getId_producto()%>" class="btn btn-secondary mb-1">ELIMINAR</button>
</div>
<%}%>
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

</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
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