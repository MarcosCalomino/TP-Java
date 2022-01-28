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

<h1>Sign up!</h1>
<br>
<%if(error==null){%>
<input name="nombre" type="text" placeholder="enter name"><br>
<input name="apellido" type="text" placeholder="enter last name"><br>
<input name="telefono" type="text" placeholder="enter phone"><br>
<input name="password" type="password" placeholder="enter password"><br>
<input name="repetirPassword" type="password" placeholder="repeat password"><br>
<input name="enviar" type="submit" value="Check in">
<%}
else if(error.equals("camposEnBlanco")){%>
<input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
<input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
<input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
<input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
<input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
<input name="enviar" type="submit" value="Check in">
<br>
<span style="color: #ff0000;"><label for="name">*Existen campos en blanco</label></span>
<%}
else if(error.equals("contraseniasNoCoinciden")){%>
<input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
<input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
<input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
<input name="password" type="password" placeholder="enter password"><br>
<input name="repetirPassword" type="password" placeholder="repeat password"><br>
<span style="color: #ff0000;"><label for="name">*Contraseñas no coinciden</label></span>
<br>
<input name="enviar" type="submit" value="Check in">
<br>
<%}
else if(error.equals("telefonoIncorrecto")){%>
<input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
<input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
<input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"> <span style="color: #ff0000;"><label for="name">*Incorrect Phone</label></span><br>
<input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
<input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
<br>
<input name="enviar" type="submit" value="Check in">
<br>
<%}
else if(error.equals("apellidoIncorrecto")){%>
<input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><br>
<input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><span style="color: #ff0000;"><label for="name">*Incorrect Last Name</label></span><br>
<input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
<input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
<input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
<br>
<input name="enviar" type="submit" value="Check in">
<br>
<%}
else if(error.equals("nombreIncorrecto")){%>
<input name="nombre" type="text" placeholder="enter name" value=<%=cliente.getNombreCliente()%>><span style="color: #ff0000;"><label for="name">*Incorrect Name</label></span><br>
<input name="apellido" type="text" placeholder="enter last name" value="<%=cliente.getApellidoClienta()%>"><br>
<input name="telefono" type="text" placeholder="enter phone" value="<%=cliente.getTelefono()%>"><br>
<input name="password" type="password" placeholder="enter password" value="<%=cliente.getPassword()%>"><br>
<input name="repetirPassword" type="password" placeholder="repeat password" value="<%=cliente.getRepetirPassword()%>"><br>
<br>
<input name="enviar" type="submit" value="Check in">
<br>
<%}%>
</form>
</body>
</html>
        <!--cliente.getNombreCliente()
        	cliente.getApellidoClienta()
        	cliente.getPassword()
        	cliente.getRepetirPassword()
        	cliente.getTelefono() -->