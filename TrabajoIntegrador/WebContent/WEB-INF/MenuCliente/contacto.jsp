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
<link rel="icon" href="http://getbootstrap.com/favicon.ico">

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
		<div class="note">
			<h1>Contacto</h1>
		</div>
		<br>

		<div align="center" class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="well well-sm">
						<form class="form-contacto" method="post">
							<fieldset>
								<div class="form-group">
									<div class="col-md-8">
										<div class="text-left">
											<label for="nombre">Nombre</label>
										</div>
										<input id="nombre" name="nombre" type="text"
											value="<%=u.getNombre()%>" class="form-control" readonly>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-8">
										<div class="text-left">
											<label for="apellido">Apellido</label>
										</div>
										<input id="apellido" name="apellido" type="text"
											value="<%=u.getApellido()%>" class="form-control" readonly>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-8">
										<div class="text-left">
											<label for="email">Direcci&oacute;n de correo
												electr&oacute;nico</label>
										</div>
										<input id="email" name="email" type="text"
											value="<%=u.getEmail()%>" class="form-control" readonly>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-8">
										<div class="text-left">
											<label for="telefono">Tel&eacute;fono</label>
										</div>
										<input id="telefono" name="phone" type="text"
											value="<%=u.getTel()%>" class="form-control">
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-8">
										<textarea class="form-control" id="message" name="message"
											placeholder="Escriba su mensaje aqu&iacute;. Responderemos lo antes posible a su direcci&oacute;n de correo electr&oacute;nico"
											rows="7" maxlength="200"></textarea>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-12 text-center">
										<a href="home" class="btn btn-danger btn-lg">Volver</a>
										<a href="home" class="btn btn-primary btn-lg">Enviar</a>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>