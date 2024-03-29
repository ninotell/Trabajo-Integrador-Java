<!DOCTYPE html>
<html>
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

<title>Rent Smart</title>
<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/register.css" rel="stylesheet">

<link rel="stylesheet"
	href="http://jqueryvalidation.org/files/demo/site-demos.css">


</head>


<body>
	<form name="f1" class="form-agregavehiculo" action="confirmaRegistro"
		method="post">
		<div class="container register-form">
			<div class="form">
				<div class="note">Crear nuevo usuario</div>

				<div class="form-content">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input id="inputName" name="nombre" type="text"
									class="form-control" placeholder="Nombre *" autofocus=""
									required="" value="" />
							</div>
							<div class="form-group">
								<input id="inputApellido" name="apellido" type="text"
									class="form-control" placeholder="Apellido *" required=""
									value="" />
							</div>
							<br>
							<div class="form-group">
								<label for="tipoDoc">Tipo de documento</label> <select
									class="form-control" id="tipoDoc" name="tipodoc">
									<option>DNI</option>
									<option>CUIT/CUIL</option>
								</select>
							</div>
							<div class="form-group">
								<input id="inputDoc" name="nrodoc" type="tel" minlength="8"
									maxlength="8" class="form-control"
									placeholder="Nro. de Documento *" required="" value="" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input id="inputTel" name="telefono" type="tel" minlength="10"
									maxlength="10" class="form-control"
									placeholder="Tel&eacute;fono *" required="" value="" />
							</div>
							<div class="form-group">
								<input id="inputDireccion" name="direccion" type="text"
									class="form-control" placeholder="Direcci&oacute;n *"
									required="" value="" />
							</div>
							<div class="form-group">
								<input id="inputCorreo" name="correoelectronico"
									class="form-control" placeholder="Correo electr&oacute;nico *"
									required="" type="email" />
							</div>
							<div class="form-group">
								<input id="password" name="password" class="form-control"
									placeholder="Contrase&ntilde;a *" minlength="6" maxlength="12"
									required="" type="password" />
							</div>
							<div class="form-group">
								<input id="confpassword" name="confpassword"
									class="form-control"
									placeholder="Confirmar contrase&ntilde;a *" required=""
									type="password" />
							</div>

						</div>
					</div>
					<div class="container-botones">
						<a href="index.jsp" class="btn btn-block btn-danger">Volver
							atr&aacute;s</a>
						<button type="submit" class="btn btn-block btn-success"
							name="botoncrear">Crear usuario</button>
					</div>
				</div>
			</div>
		</div>
	</form>

</body>

<script>
	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confpassword");

	function validatePassword() {
		if (password.value != confpassword.value) {
			confirm_password.setCustomValidity("Las contraseņas no coinciden");
		} else {
			confirm_password.setCustomValidity('');
		}
	}
	password.onchange = validatePassword;
	confpassword.onkeyup = validatePassword;
</script>
</html>