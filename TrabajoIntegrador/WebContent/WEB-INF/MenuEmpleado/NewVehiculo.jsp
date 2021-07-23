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

<script
	src="https://ucarecdn.com/libs/widget/3.x/uploadcare.full.min.js"></script>
<script>
	UPLOADCARE_LOCALE = 'es';
</script>

</head>
<body>

	<form name="f1" class="form-new" action="Agregavehiculo" method="post"
		enctype="multipart/form-data">
		<div class="container">
			<div class="note">
				<h1>Agregar nuevo vehiculo</h1>
			</div>
			<div class="form-newvehiculo">
				<div class="form-content">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<select class="form-control" id="inputCategoria"
									name="categoria" autofocus="">
									<option value="" disabled selected hidden>Categoría</option>
									<option value="1">Chico</option>
									<option value="2">Mediano</option>
									<option value="3">Grande</option>
								</select>
							</div>

							<div class="form-group">
								<input id="inputPatente" name="patente" type="text"
									class="form-control" placeholder="Patente *"
									pattern="[A-Z]{2} [1-9]{3} [A-Z]{2}|[A-Z]{3} [1-9]{3}"
									title='Formato de patente: "ABC 123" o "AB 123 CD"' required=""
									value="" />
							</div>
							<div class="form-group">
								<input id="inputMarca" name="marca" type="text"
									list="listamarcas" class="form-control" placeholder="Marca *"
									required="" value="" />
							</div>
							<div class="form-group">
								<input id="inputModelo" name="modelo" type="text"
									class="form-control" placeholder="Modelo *" required=""
									value="" />
							</div>
							<div class="form-group">
								<label for="transmision">Transmision</label> <select
									class="form-control" id="Transmision" name="transmision">
									<option value="Automatico">Automático</option>
									<option value="Manual">Manual</option>
								</select>
							</div>
							<div class="form-group">
								<input id="inputAño" name="anio" type="text"
									class="form-control" placeholder="Año *" required="" value="" />
							</div>
							<div class="form-group">
								<input id="inputKm" name="km" type="text" class="form-control"
									placeholder="Km *" required="" value="" />
							</div>
							<div class="form-group">
								<input type="hidden" role="uploadcare-uploader"
									data-public-key="892a2589bdaf7da24521" name="fotovehiculo" />
							</div>


							<div class="form-group">
								<button type="submit" class="btn btn-lg btn-success btn-block"
									name="botoncrear">Agregar vehiculo</button>
							</div>
							<div class="form-group">
								<a href="home" class="btn btn-lg btn-danger btn-block">Cancelar</a>
							</div>
						</div>
					</div>

				</div>
			</div>
	</form>
	<datalist id="listamarcas">
		<option value="Ford"></option>
		<option value="Chevrolet"></option>
		<option value="Citroen"></option>
		<option value="Toyota"></option>
		<option value="DS"></option>
		<option value="Chrysler"></option>
	</datalist>

</body>
<script>
	window.onload = function() {
		if (
<%=request.getAttribute("errorpatente")%>
	== true) {
			alert("Patente duplicada!");
		} else {
		}
	}
</script>
</html>