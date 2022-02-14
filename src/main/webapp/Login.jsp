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



<!-- *****DEFINICION DE VARIABLEs***** -->
<%String error = (String)request.getAttribute("error");%>
<%String telefono = (String)request.getAttribute("telefono");%>




<%if(error == null){%> <!--*****CANDO SE INICIA LA PAGINA POR PRIMERA VES LA VARIABLE error ES NULL*****-->
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
  </div>
 </div>
</div>
<%}else if (error.equals("CamposEnBlanco")){%>
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
   <br>
   <span style="color: #ff0000;"><label for="name">*Existen campos en blanco</label></span>
  </div>
 </div>
</div>
<%} else if(error.equals("telefonoIncorrecto")){%>
<div class="container border">
 <div class="row justify-content-center align-items-center">
  <div class="col-auto text-center p-5">
   <h3><u>Login</u></h3><br>   
   <div class="row">
    <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="<%=telefono%>">
    <span style="color: #ff0000;"><label for="name">*Wrong phone</label></span>
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
  </div>
 </div>
</div>
<%}else if(error.equals("PasswordIncorrecta")){%>
<div class="container border">
 <div class="row justify-content-center align-items-center">
  <div class="col-auto text-center p-5">
  <h3><u>Login</u></h3><br>
  <div class="row">
   <input name="telefono" type="text" placeholder="Ingrese su nro telefonico" value="<%=telefono%>">
  </div>
  <div class="row">
   <input name="password" type="password" placeholder="Contraseña" value="">
  </div>
  <span style="color: #ff0000;"><label for="name">*Wrong password</label></span>
  <div class="row">
   <input name="enviar" type="submit" value="Acceder" class="btn btn-primary text-dark">
  </div>
  <div class="row">
   <a href="Registrarse.jsp" class="">Registrarse</a>
  </div>
  </div>
 </div>
</div>
<%}%>

</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>