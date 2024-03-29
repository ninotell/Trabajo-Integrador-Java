<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Rent Smart">
<meta name="author" content="Santiago Blanco y Nino Tell">
<link rel="icon"
	href="https://static.thenounproject.com/png/1516448-200.png">

<title>Cambio de contraseņa</title>
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
<link href="style/contacto.css" rel="stylesheet">

</head>
<%
Usuario u = (Usuario) session.getAttribute("usuario");
%>


<body>
	<div class="container text-center">
		<div class="note">
			Cambio de contraseņa para
			<%=u.getNombre()%>
			<%=u.getApellido()%>
		</div>
		<div class="content">
			<div class="container-pass">
				<form class="form-register" action="changePassword" method="post">
					<input class="form-control" id="oldPassword" name="oldPassword"
						type="password" placeholder="Contraseņa anterior"><br>
					<input class="form-control" id="newPassword" name="newPassword"
						type="password" placeholder="Nueva contraseņa" minlength="6"
						maxlength="12"><br> <input class="form-control"
						id="confirmPassword" name="confirmPassword" type="password"
						placeholder="Confirmar contraseņa">
					<div class="container-botones">
						<button id="updateButton" class="btn btn-success btn-block">Actualizar
							contraseņa</button>
						<a href="home" class="btn btn-danger btn-block">Cancelar</a>
					</div>

				</form>
			</div>

		</div>
	</div>

</body>

<script>
	var old_password = document.getElementById("oldPassword"), password = document
			.getElementById("newPassword"), confirm_password = document
			.getElementById("confirmPassword"), update_button = document
			.getElementById("updateButton");

	function validateOldPassword() {
		if (password.value == old_password.value) {
			password
					.setCustomValidity("La contraseņa no puede ser igual a la anterior");
		} else {
			password.setCustomValidity('');
		}

	}

	function validateConfirmPassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Las contraseņas no coinciden");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	old_password.onchange = validateOldPassword;
	password.onkeyup = validateOldPassword;
	password.onchange = validateConfirmPassword;
	confirm_password.onkeyup = validateConfirmPassword;

	window.onload = function() {
		if (
<%=request.getAttribute("errormsg")%>
	== true) {
			alert("Contraseņa anterior incorrecta");
		} else {
		}
	}
</script>
</html>