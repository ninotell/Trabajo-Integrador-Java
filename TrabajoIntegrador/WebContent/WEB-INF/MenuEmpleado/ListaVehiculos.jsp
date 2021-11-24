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
	href="https://static.thenounproject.com/png/1516448-200.png">
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
<link href="style/listaVehiculos.css" rel="stylesheet">

<%
LinkedList<Vehiculo> lv = (LinkedList<Vehiculo>) session.getAttribute("vehiculosEncontrados");
Usuario u = (Usuario) session.getAttribute("usuario");
%>
<title><%=u.getNombre()%> <%=u.getApellido()%></title>

</head>

<body>
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img
			class="rounded mx-auto d-block"
			src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png"
			alt="" width="50" height="50"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item  "><a class="nav-link" href="home">Home<span
						class="sr-only"></span></a></li>
				<li class="nav-item active"><a class="nav-link"
					href="listavehiculos">Vehiculos</a></li>
				<li class="nav-item"><a class="nav-link" href="editarolusuario"
					method="get">Asignar rol</a></li>
			</ul>
			<span class="dropdown"> <a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#">ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%>
					<%=u.getApellido()%></a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="changePassword">Cambiar
						contraseña</a> <a class="dropdown-item" href="Logout">Cerrar
						sesión</a>
				</div>
			</span>
		</div>
		</nav>
	</div>

	<br>
	<br>
	<br>
	<br>

	<%
	if (lv == null || lv.size() == 0) {
	%>
	<div class="text-center">
		<h3 class="text-center">No se encontraron vehículos</h3>
		<button class="btn btn-danger text-center"
			onclick="window.history.back()">Volver a la página anterior</button>
	</div>
	<%
	} else {
	%>
	<div class="container">
		<div class="container-arriba">
			<a href="agregavehiculo" class="btn btn-primary" id="btn-agregar">Agregar
				vehiculo</a>
			<div class="container-busqueda">
				<form action="listavehiculos" method="post">
					<input name="patenteBusqueda" class="componente form-control"
						
						placeholder="Buscar por patente">
					<button type="submit" class="componente btn btn-primary">Buscar</button>
				</form>
			</div>
		</div>

	</div>

	<br>

	<div class="container-lista">
		<div class="row">
			<h4 class="text-center">Vehiculos</h4>
				<div class="table-responsive">
					<table class="table table-striped table-hover"
						id="vehicles">
						<thead>
							<tr>
								<th><a href="#" onclick="sortTable(0, 'int')">ID</a></th>
								<th><a href="#" onclick="sortTable(1, 'str')">Patente</a></th>
								<th><a href="#" onclick="sortTable(2, 'str')">Marca</a></th>
								<th><a href="#" onclick="sortTable(3, 'str')">Modelo</a></th>
								<th><a href="#" onclick="sortTable(4, 'str')">Transmisión</a></th>
								<th><a href="#" onclick="sortTable(5, 'int')">Año</a></th>
								<th><a href="#">Foto</a></th>
								<th onclick="sortTable(6, 'int')">KM</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (Vehiculo v : lv) {
							%>
							<tr>
								<td class="idvehiculo"><%=v.getIdVehiculo()%></td>
								<td><%=v.getPatente()%></td>
								<td><%=v.getMarca()%></td>
								<td><%=v.getModelo()%></td>
								<td><%=v.getTransmision()%></td>
								<td><%=v.getAnio()%></td>
								<td><img class="rounded" src="<%=v.getFoto()%>" width="150"></td>
								<form name="f2" class="form-register" action="editavehiculo"
									method="post">
									<td><input type="number" class="form-control"
										placeholder="<%=v.getKm()%> km" value="<%=v.getKm()%> km" name="kms" /></td>

									<td><button type="submit" class="btn btn-primary">Actualizar
											km</button></td> <input type="hidden" name="idvehiculo"
										value="<%=v.getIdVehiculo()%>" />
								</form>
								<form name="f3" id="formElimina">
									<td><button
											onclick="confirmaEliminacion(<%=v.getIdVehiculo()%>)"
											type="button" class="btn btn-danger">Eliminar</button></td>
								</form>
								<%
								}
								%>
							
						</tbody>
					</table>
				</div>
		</div>
	</div>

	<%
	}
	%>



</body>

<script>
	function confirmaEliminacion(id){
		r = confirm("Desea eliminar vehiculo ID: " + id + "?");
		if(r==true){
			var f = document.getElementById('formElimina');
			f.method="post";
			f.action='eliminavehiculo?idvehiculo='+id;
			f.submit();
		}else{
			
		}
		}
	

 
</script>

</html>