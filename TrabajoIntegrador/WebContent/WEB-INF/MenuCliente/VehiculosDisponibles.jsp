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
<link href="style/vehiculosDisponibles.css" rel="stylesheet">

<%
String fechadesde = (String) session.getAttribute("fechadesde");
String fechahasta = (String) session.getAttribute("fechahasta");
LinkedList<Vehiculo> vDisponibles = (LinkedList<Vehiculo>) session.getAttribute("vDisponibles");
Usuario u = (Usuario) session.getAttribute("usuario");
%>
<title><%=u.getNombre()%> <%=u.getApellido()%></title>

</head>

<body class="body">


	<div class="container text-center">
		<div class="note text-center">Veh&iacute;culos disponibles</div>
		<%
		if ((vDisponibles.isEmpty()) == true) {
		%>
		No hay veh&iacute;culos para mostrar
		<button onclick="window.history.back()" class="btn btn-danger">Volver
			a selecci&oacute;n de fechas</button>
		<%
		} else {
		%>
		<div class="content">
			<div class="pricing py-5">
				<div class="container-cards">
					<%
					for (Vehiculo v : vDisponibles) {
					%>
					<div class="card">

						<div class="card-body">
							<h5 class="card-title text-muted text-uppercase text-center"><%=v.getMarca()%></h5>
							<h6 class="card-price text-center"><%=v.getModelo()%></h6>
							<hr>
							<div class="anio" align="center">
								A�o:
								<%=v.getAnio()%></div>
						</div>
						<div class="card-footer">
							<img class="imagen" src="<%=v.getFoto()%>">
						</div>
						<div class="card-footer">
							<form action="reservaVistaPrevia" method="post">
								<input type="hidden" name="idvehiculo"
									value="<%=v.getIdVehiculo()%>">
								<td><button type="submit" class="btn btn-success">Reservar</button></td>
							</form>
						</div>

					</div>
					<%
					}
					%>
				</div>
			</div>
			<div class="container-botones">
				<button onclick="window.history.back()"
					class="btn btn-outline-danger btn-block text-center">Volver
					a selecci&oacute;n de fechas</button>
				<a href="home" type="button" class="btn btn-block btn-danger">Volver
					al men&uacute;</a>
			</div>
		</div>

	</div>

	<%
	}
	%>

</body>

</html>