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
<link href="style/contacto.css" rel="stylesheet">

<%
Usuario u = (Usuario) session.getAttribute("usuario");
%>

<title>Contacto</title>

</head>


<body>

	<div class="container">
		<div class="note">Contacto</div>

		<div class="content">

			<div class="contacto">

				<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
					type="text" value="<%=u.getNombre()%>" class="form-control"
					readonly> <label for="apellido">Apellido</label> <input
					id="apellido" name="apellido" type="text"
					value="<%=u.getApellido()%>" class="form-control" readonly>



				<label for="email">Direcci&oacute;n de correo
					electr&oacute;nico</label> <input id="email" name="email" type="text"
					value="<%=u.getEmail()%>" class="form-control" readonly> <label
					for="telefono">Tel&eacute;fono</label> <input id="telefono"
					name="telefono" type="text" value="<%=u.getTel()%>"
					class="form-control"> <br>

				<textarea class="form-control" id="message" name="message"
					placeholder="Escriba su mensaje aqu&iacute;. Responderemos lo antes posible a su direcci&oacute;n de correo electr&oacute;nico"
					rows="7" maxlength="200"></textarea>
			</div>
			<div class="container-botones">
				<a href="home" class="btn btn-primary btn-block">Enviar</a><a
					href="home" class="btn btn-danger btn-block">Volver</a>
			</div>
		</div>
	</div>

</body>
</html>