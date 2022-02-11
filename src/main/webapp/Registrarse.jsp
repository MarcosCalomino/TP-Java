<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "Entities.Cliente" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <title>Registrarse</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<form action="AltaCliente" method="POST">
<%String error = (String)request.getAttribute("error"); 
  Cliente cliente = (Cliente)request.getAttribute("cliente");%>


<br>
<%if(error==null){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name"><br>
  <input name="apellido" type="text" placeholder="enter last name"><br>
  <input name="telefono" type="text" placeholder="enter phone"><br>
  <input name="password" type="password" placeholder="enter password"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password"><br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
 </div>
</div>
<%}
else if(error.equals("camposEnBlanco")){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
  <input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
  <input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
  <input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
  <br>
  <span style="color: #ff0000;"><label for="name">*Existen campos en blanco</label></span>
 </div>
</div>
<%}
else if(error.equals("contraseniasNoCoinciden")){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
  <input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
  <input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
  <input name="password" type="password" placeholder="enter password"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password"><br>
  <span style="color: #ff0000;"><label for="name">*Contraseñas no coinciden</label></span>
  <br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
  <br>
 </div>
</div>
<%}
else if(error.equals("telefonoIncorrecto")){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
  <input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
  <input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"> <span style="color: #ff0000;"><label for="name">*Incorrect Phone</label></span><br>
  <input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
  <br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
  <br>
 </div>
</div>
<%}
else if(error.equals("apellidoIncorrecto")){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
  <input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><span style="color: #ff0000;"><label for="name">*Incorrect Last Name</label></span><br>
  <input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
  <input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
  <br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
  <br>
 </div>
</div> 

<%}
else if(error.equals("nombreIncorrecto")){%>
<div class="m-0 vh-100 row justify-content-center align-items-center">
 <div class="col-auto p-5 text-center">
  <h1>Sign up!</h1>
  <input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><span style="color: #ff0000;"><label for="name">*Incorrect Name</label></span><br>
  <input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
  <input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
  <input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
  <input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
  <br>
  <input name="enviar" type="submit" value="Check in" class="btn btn-primary text-dark">
  <br>
 </div>
</div>
<%}%>
</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>