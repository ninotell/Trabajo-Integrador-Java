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
	href="https://ucarecdn.com/84d815d4-b4ae-4b0a-9a13-eebc2057380e/logo.png">

<title>Rent Smart</title>

<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/signin.css" rel="stylesheet">
</head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>



<body class="text-center">


	<img class="logo rounded shadow-lg mx-auto d-block"
		src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png">
	<div class="card shadow-lg rounded">
		<h5 class="card-header">Inicie sesi&oacute;n</h5>
		<form class="form-signin mt-5" action="signin" method="post">
			<input id="inputEmail" name="email" class="form-control"
				placeholder="Correo electr&oacute;nico" required="" autofocus=""
				type="email"> <input id="inputPassword" name="password"
				class="form-control" placeholder="Contrase&ntilde;a" required=""
				type="password">
			<div class="container-botones row">
				<div class="col">
					<a href="register.jsp" class="btn btn-block btn-outline-info">Crear
						usuario</a>
				</div>
				<div class="col">
					<button class="btn btn-block btn-primary" type="submit">Iniciar
						sesi&oacute;n</button>
				</div>
			</div>
		</form>
		<br>
	</div>

</body>

<script>
	window.onload = function() {
		if (
<%=request.getAttribute("errormsg")%>
	== true) {
			alert("Error inesperado");
		} else {
		}
		if (
<%=request.getAttribute("newuser")%>
	== true) {
			alert("Bienvenido al sistema, por favor inicie sesi�n");
		} else {
		}
		if (
<%=request.getAttribute("datosincorrectos")%>
	== true) {
			alert("Usuario y/o contrase\u00F1a incorrecto/s");
		} else {
		}
	}
</script>


</html>
