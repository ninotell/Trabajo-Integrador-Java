<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Rent Smart">
<meta name="author" content="Santiago Blanco y Nino Tell">
<meta name="theme-color" content="#1CA3FF">
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

<%
String errormsg = (String) request.getAttribute("errormsg");
String okmsg = (String) request.getAttribute("okmsg");
%>


<body class="body">
	<div class="container">
		<img class="logo mx-auto d-block"
			src="https://ucarecdn.com/a51fa61b-8c28-4159-9f43-83de3c970a24/logo_index.png">
		<div class="card rounded">
			<h5 class="card-header text-center">Inicie sesi&oacute;n</h5>
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
			<%
			if (errormsg != null) {
			%>
			<br>
			<div class="card-footer">
				<div class="alert alert-danger" role="alert">
					<%=errormsg%>
				</div>
			</div>
			<%
			}
			%>

			<%
			if (okmsg != null) {
			%>
			<br>
			<div class="card-footer">
				<div class="alert alert-success" role="alert">
					<%=okmsg%>
				</div>
			</div>
			<%
			}
			%>

		</div>
	</div>

</body>

<script>
	window.onload = function() {
		if (
<%=request.getAttribute("newuser")%>
	== true) {
			alert("Bienvenido al sistema, por favor inicie sesión");
		} else {
		}
	}
</script>


</html>
