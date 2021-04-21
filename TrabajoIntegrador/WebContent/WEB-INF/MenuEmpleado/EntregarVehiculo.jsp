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

<title>Rent Smart</title>
<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/agregavehiculo.css" rel="stylesheet">



</head>
<body>

	<form name="f1" class="form-new" action="retirarVehiculo" method="post">
		<div class="container">
			<div class="note">
				<h1>Entregar Veh&iacute;culo</h1>
			</div>
			<div class="form-newvehiculo">
				<div class="form-content">
					<div class="row">
						<div class="col">
							<label for="categoria">Ingrese el n&uacute;mero de la reserva</label>
							<div class="form-group">
								<input id="inputIdReserva" name="idReserva" type="number"
									class="form-control" placeholder="Numero Reserva *"
									autofocus="" required="" value="" />
							</div>

						</div>


					</div>
				</div>
				<div class="botones" align="center">
					<a href="home" type="button" class="btn btn-lg btn-danger">Volver
						al men&uacute; principal</a>
					<button type="submit" class="btn btn-lg btn-success "
						id="botonMostrar">Continuar</button>

				</div>
			</div>
	</form>

</body>
<script>
		window.onload = function() {
			if (
		<%=request.getAttribute("errorReserva")%>
		== true) {
				alert("No hay reserva con ese numero");
			} else {
			}
</script>
</html>
