<%@page import="entities.Usuario"%>
<%@page import="entities.Vehiculo"%>
<%@page import="logic.Login"%>
<%@page import="java.util.LinkedList"%>
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
<link rel="icon"
	href="https://static.thenounproject.com/png/1516448-200.png">
<title>Rent Smart</title>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
	crossorigin="anonymous"></script>

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/MenuEmpleado.css" rel="stylesheet">

<%
Usuario u = (Usuario) session.getAttribute("usuario");
String errormsg = (String) request.getAttribute("errormsg");
String okmsg = (String) request.getAttribute("okmsg");
%>
<title><%=u.getNombre()%> <%=u.getApellido()%></title>

</head>

<body>



	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img
			class="rounded mx-auto d-block"
			src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png"
			alt="" width="50" height="50"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="home">Home<span
						class="sr-only"></span></a></li>
				<li class="nav-item"><a class="nav-link" href="listavehiculos">Vehiculos</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="editarolusuario"
					method="get">Asignar rol</a></li>
			</ul>
			<span class="dropdown"> <a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#">ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%>
					<%=u.getApellido()%></a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="changePassword">Cambiar
						contrase�a</a> <a class="dropdown-item" href="Logout">Cerrar
						sesi�n</a>
				</div>
			</span>
		</div>
		</nav>
	</div>


	<br>
	<br>

	<%
	if (errormsg != null) {
	%>
	<br>
	<div class="alert alert-danger" role="alert">
		<%=errormsg%>
	</div>
	<%
	}
	%>

	<%
	if (okmsg != null) {
	%>
	<br>
	<div class="alert alert-success" role="alert">
		<%=okmsg%>
	</div>
	<%
	}
	%>

	<div class="container-botones">
		<div class="m-0 col justify-content-center">
			<div class="col-auto p-5  text-center">
				<a href="agregavehiculo" 
					class="btn btn-primary btn-block">Agregar vehiculo</a> <br> 
				<a href="retirarVehiculo"
					class="btn btn-primary btn-block">Retiro/Devoluci&oacute;n</a> <br>
				<a href="verificaReserva" 
					class="btn btn-danger btn-block">Verificar reservas</a><br> 
				<a href="changePassword"
					class="btn btn-warning btn-block">Cambiar contrase�a</a>

			</div>
		</div>
	</div>

</body>

</html>