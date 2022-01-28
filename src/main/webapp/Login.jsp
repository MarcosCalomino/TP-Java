<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <title>Login</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<form action="Login" method="POST">
<%String error = (String)request.getAttribute("error");%>
<%String telefono = (String)request.getAttribute("telefono");%>

<%if(error == null){%>
<div class="container border">
 <div class="row justify-content-center align-items-center">
  <div class="col-auto text-center p-5">
   <h3><u>Login</u></h3><br>
   <div class="row">
    <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="">
   </div>
   <div class="row">
    <input name="password" type="password" placeholder="Contraseña" value="">
   </div>
   <div class="row">
    <input name="enviar" type="submit" value="Acceder" class="btn btn-primary text-dark">
   </div>
   <div class="row"> 
   <a href="Registrarse.jsp" class="">Registrarse</a>
   </div>
   <%}else if (error.equals("CamposEnBlanco")){%>
   <br>
   <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="">
   <br>
   <input name="password" type="password" placeholder="Contraseña" value="">
   <br>
   <input name="enviar" type="submit" value="Acceder" class="btn btn-primary text-dark">
   <br>
   <a href="Registrarse.jsp" class="">Registrarse</a>
   <br>
   <span style="color: #ff0000;"><label for="name">*Existen campos en blanco</label></span>
   <%} 
   else if(error.equals("telefonoIncorrecto")){%>
   <h3>Login</h3><br>
   <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="<%=telefono%>">
   <span style="color: #ff0000;"><label for="name">*Wrong phone</label></span>
   <br>
   <input name="password" type="password" placeholder="Contraseña" value="">
   <br>
   <input name="enviar" type="submit" value="Acceder" class="btn btn-primary text-dark">
   <br>
   <a href="Registrarse.jsp" class="">Registrarse</a>
   <%}
   else if(error.equals("PasswordIncorrecta")){%>
   <br>
   <h3>Login</h3><br>
   <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="<%=telefono%>">
   <br>
   <input name="password" type="password" placeholder="Contraseña" value="">
   <span style="color: #ff0000;"><label for="name">*Wrong password</label></span>
   <br>
   <input name="enviar" type="submit" value="Acceder" class="btn btn-primary text-dark">
   <br>
   <a href="Registrarse.jsp" class="">Registrarse</a>
   </div>
  </div>
 </div>
<%}%>

</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>