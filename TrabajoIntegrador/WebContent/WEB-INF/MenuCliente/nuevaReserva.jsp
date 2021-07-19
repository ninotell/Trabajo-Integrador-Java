<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

<!-- Datepicker  -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>


<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/nuevaReserva.css" rel="stylesheet">

<%
Usuario u = (Usuario) session.getAttribute("usuario");
%>

<title>Nueva reserva</title>

</head>


<body class="body">

	<div class="container">
		<div class="note">Elija sus preferencias</div>
		<form class="form-reserva" action="nuevaReserva" method="post">
			<div class="form-newvehiculo">
				<div class="form-content">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="categoria">Categor&iacute;a</label> <select
									class="form-control" id="inputCategoria" name="categoria">
									<option value="1">Pequeño</option>
									<option value="2">Mediano</option>
									<option value="3">Grande</option>
								</select>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="fechadesde">Fecha desde</label> <input
											name="fechadesde" id="fechadesde" class="form-control"
											type="text" placeholder="Fecha desde" readonly>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="fechahasta">Fecha hasta</label> <input
											name="fechahasta" id="fechahasta" class="form-control"
											type="text" placeholder="Fecha hasta" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="transmision">Transmisi&oacute;n</label> <select
									class="form-control" id="Transmision" name="transmision">
									<option>Autom&aacute;tico</option>
									<option>Manual</option>
								</select>
							</div>

						</div>
					</div>
					<div class="container-botones">
						<a href="home" type="button" class="btn btn-block btn-danger">Volver
							al men&uacute;</a>
						<button type="submit" class="btn btn-block btn-success"
							id="botonMostrar">Mostrar vehículos</button>

					</div>
				</div>
			</div>
	</div>

	</form>
</body>

<script type="text/javascript">
	window.onload = function() {
		if (
<%=request.getAttribute("fechaincorrecta")%>
	== true) {
			alert("Ingrese una fecha válida");
		} else {
		}
		$("#fechahasta").datepicker("option", "disabled", true);
		$("#botonMostrar").attr('disabled', 'disabled');
	}

	var botonMostrar = document.getElementById("botonMostrar"), fechadesde = document
			.getElementById("fechadesde"), fechahasta = document
			.getElementById("fechadesde");

	$(function() {

		$("#fechadesde").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					minDate : '+1d',
					numberOfMonths : 1,
					onSelect : function(selected) {
						$("#fechahasta").datepicker("option", "minDate",
								selected), $("#fechahasta").datepicker(
								"option", "disabled", false)
					}
				});

		$("#fechahasta").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					minDate : '+2d',
					onSelect : function(selected) {
						$("#fechadesde").datepicker("option", "maxDate",
								selected), $("#botonMostrar").removeAttr(
								"disabled");
					}
				});

	});
</script>

</html>