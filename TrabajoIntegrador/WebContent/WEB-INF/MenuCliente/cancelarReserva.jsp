<%@page import="entities.Usuario"%>
<%@page import="entities.Reserva"%>
<%@page import="entities.Rol"%>
<%@page import="data.DataRol"%>
<%@page import="logic.Login"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	href="https://ucarecdn.com/84d815d4-b4ae-4b0a-9a13-eebc2057380e/logo.png">
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
<link href="style/cancelaReserva.css" rel="stylesheet">

<%
Usuario us = new Usuario();
DataRol dr = new DataRol();
Login ctrlLogin = new Login();
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
Integer rCount = 0;
LinkedList<Reserva> reservasUsuario = (LinkedList<Reserva>) session.getAttribute("reservasUsuario");
Usuario u = (Usuario) session.getAttribute("usuario");
%>
<title>Cancelar Reserva</title>
</head>

<body>



	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="home"> <img
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
				<li class="nav-item active "><a class="nav-link" href="home">Home<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="reservasCliente">Mis
						reservas<span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<span class="dropdown"> <a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#"> ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%>
					<%=u.getApellido()%>
			</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="contacto">Contacto</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="Logout">Cerrar sesi&oacute;n</a>
				</div>
			</span>
		</div>
		</nav>
	</div>

	<hr>
	<hr>
	<hr>
	<hr>
	<hr>

	<br>

	<div class="container-cancela">
		<%
		for (Reserva r : reservasUsuario) {
			if (r.getEstado().equals("Iniciada")) {
				rCount += 1;
		%>
		<div class="pricing py-5">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-muted text-uppercase text-center">CANCELACIÓN</h5>
					<h6 class="card-price text-center">
						ID:
						<%=r.getIdReserva()%></h6>
					<div class="text-left">
						<span class="font-weight-bold">Fecha de reserva: </span><%=dateFormat.format(r.getFechaReserva())%>
					</div>
					<hr>
					<p class="text-left">
						<span class="font-weight-bold">Fecha de retiro: </span><%=dateFormat.format(r.getFechaRetiro())%></p>
					<hr>
					<div class="text-left">
						<span class="font-weight-bold">Fecha de devoluci&oacute;n:
						</span><%=dateFormat.format(r.getFechaDevolucion())%></div>
					<hr>
					<div class="text-left">
						<span class="font-weight-bold">Estado: </span><%=r.getEstado()%></div>
				</div>
				<div class="card-footer">
					<div class="container-botones">
						<form name="f3" id="formCancela">
							<button onclick="confirmaCancelacion(<%=r.getIdReserva()%>)"
								type="button" class="btn btn-danger">Cancelar</button>
						</form>
						<a href="home" class="btn btn-danger btn">Volver atr&aacute;s</a>

					</div>
				</div>
			</div>
			<%
			}
			}
			%>

			<%
			if (rCount == 0) {
			%>
			<h5 class="text-center">No hay reservas iniciadas</h5>
			<%
			}
			%>
		</div>
	</div>





</body>

<script> 
var	idr = document.getElementById('idr').value;
function confirmaCancelacion(id){
	r = confirm("Desea cancelar su reserva ID: " + id + "?");
	if(r==true){
		var f = document.getElementById('formCancela');
		f.method="post";
		f.action='cancelaReserva?idreserva='+id;
		f.submit();
	}else{
		
	}
	}
</script>

</html>