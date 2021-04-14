<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/MenuCliente.css" rel="stylesheet">
	
	<%
		Usuario u = (Usuario)session.getAttribute("usuario");

	%>

	
	
	<title><%=u.getNombre()%> <%=u.getApellido()%>  </title>
</head>
 	 	
<body>



<div class="container">	
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand" href="#">
		<img class="rounded mx-auto d-block" src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png" alt="" width="50" height="50"></a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" 
          data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
          aria-expanded="false" aria-label="Toggle navigation">
    	 <span class="navbar-toggler-icon"></span>
	    </button>

 	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item active ">
        		<a class="nav-link" href="#home">Home<span class="sr-only">(current)</span></a>
	        </li>
      		<li class="nav-item">
        		<a class="nav-link" href="#">Boton 1</a>
	        </li>
	        <li class="nav-item">
				<a class="nav-link" href="#"  method="get">Boton 2</a>
	        </li>
	    </ul>
	     <span class="dropdown">
      			  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
          			ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%> <%=u.getApellido()%>
       			  </a>
    		     <div class="dropdown-menu">
          			<a class="dropdown-item" href="changePassword">Cambiar contraseña</a>
          			<a class="dropdown-item" href="Logout">Cerrar sesión</a>
			     </div>
	       </span>
     </div>
  </nav>
 </div>
 

 	<br>
 	<br>

	<div class="container-botones">
		<div class="m-0 col justify-content-center">
			<div class="col-auto p-5  text-center">
				<a href="nuevaReserva" method="get">
					<button  class="btn btn-success btn-block">Realizar reserva</button>
				</a>
				<br>
				<a href="#" method="get">
					<button  class="btn btn-primary btn-block">Devoluci&oacute;n de veh&iacute;culo</button>
				</a>
				<br>
				<a href="#" method="get">
					<button  class="btn btn-danger btn-block">Cancelar reserva</button>
				</a>
				<br>
				<a href="changePassword" method="get">
					<button  class="btn btn-warning btn-block">Cambiar contraseña</button>
				</a>
			</div>		
		</div>
	</div>
	

</body>


<footer class="text-center">
  <br>
  <h3>Precios actualizados por categoría (por día)</h3>
  <h4>Chico</h4>
  <h4>Mediano</h4>
  <h4>Grande</h4>

</footer>



<script>
window.onload= function(){ 
		if (<%=request.getAttribute("pswchange")%>==true)
			{alert("Contraseña actualizada con éxito");}
		else {}				
	
		if (<%=request.getAttribute("errormsg")%>==true)
			{alert("No tienes acceso a esta página");}
		else {}
}
	
</script>
</html>