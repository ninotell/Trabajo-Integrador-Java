<%@page import="entities.Usuario"%>
<%@page import="entities.Categoria"%>
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


<!-- Bootstrap core CSS -->
<link href="style/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="style/MenuCliente.css" rel="stylesheet">



<%
String errormsg = (String) request.getAttribute("errormsg");
String okmsg = (String) request.getAttribute("okmsg");
Usuario u = (Usuario) session.getAttribute("usuario");
LinkedList<Categoria> listaCategorias = (LinkedList<Categoria>) session.getAttribute("listaCategorias");
%>



<title><%=u.getNombre()%> <%=u.getApellido()%></title>
</head>




<body class="body">
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="home"> <img
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
				<li class="nav-item active "><a class="nav-link" href="home">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="reservasCliente">Mis
						reservas</a></li>
			</ul>
			<span class="dropdown"> <a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#"> ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%>
					<%=u.getApellido()%>
			</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="contacto">Contacto</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="Logout">Cerrar sesi&oacute;n</a>
				</div>
			</span>
		</div>
		</nav>
	</div>
	<br>
	<br>
	<%
	if (errormsg != null) {
	%>
	<br>
	<div class="alert alert-danger" role="alert">
		<%=errormsg%>
	</div>
	<%
	}
	%>

	<%
	if (okmsg != null) {
	%>
	<br>
	<div class="alert alert-success" role="alert">
		<%=okmsg%>
	</div>
	<%
	}
	%>

	<div class="container-botones">
		<div class="col-auto p-5  text-center">
			<a href="nuevaReserva" class="btnRes btn btn-success btn-block shadow ">Realizar
				reserva</a> <br> <a href="cancelaReserva"
				class="btn btn-danger btn-block shadow ">Cancelar reserva</a> <br>
			<a href="changePassword"
				class="btn btn-outline-primary btn-block shadow ">Cambiar
				contrase�a</a>
		</div>
	</div>

	<div class="pricing py-5">
		<div class="container-cards">
			<div class="card" onclick="vehiculosCategoria(1)">
				<div class="card-body">
					<h5 class="card-title text-muted text-uppercase text-center">Peque�o</h5>
					<h6 class="card-price text-center">
						$<%=Math.round(listaCategorias.get(0).getPrecioxDia())%><span
							class="period">/d&iacute;a</span>
					</h6>
					<hr>
					<ul class="fa-ul">

						<li><span class="fa-li"><i class="fas fa-check"></i></span>4
							Asientos</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>5
							Puertas</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>2
							Bolsos Peque�os</li>

						<li class="text-muted"><span class="fa-li"><i
								class="fas fa-times"></i></span>Recomendado para uso en la ciudad</li>
					</ul>
				</div>
			</div>


			<div class="card" onclick="vehiculosCategoria(2)">
				<div class="card-body">
					<h5 class="card-title text-muted text-uppercase text-center">Mediano</h5>
					<h6 class="card-price text-center">
						$<%=Math.round(listaCategorias.get(1).getPrecioxDia())%><span
							class="period">/d&iacute;a</span>
					</h6>
					<hr>
					<ul class="fa-ul">
						<li><span class="fa-li"><i class="fas fa-check"></i></span>5
							Asientos</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>4/5
							Puertas</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>2
							Bolsos Grandes</li>
						<li class="text-muted"><span class="fa-li"><i
								class="fas fa-times"></i></span>Recomendado para uso empresarial</li>
					</ul>
				</div>
			</div>
			<div class="card" onclick="vehiculosCategoria(3)">
				<div class="card-body">
					<h5 class="card-title text-muted text-uppercase text-center">Grande</h5>
					<h6 class="card-price text-center">
						$<%=Math.round(listaCategorias.get(2).getPrecioxDia())%><span
							class="period">/d&iacute;a</span>
					</h6>
					<hr>
					<ul class="fa-ul">
						<li><span class="fa-li"><i class="fas fa-check"></i></span>5/7
							Asientos | Camioneta</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>4
							Puertas</li>
						<li><span class="fa-li"><i class="fas fa-check"></i></span>2
							Bolsos grandes y 1 bolso chico</li>
						<li class="text-muted"><span class="fa-li"><i
								class="fas fa-check"></i></span>Recomendado para viajes largos</li>

					</ul>
				</div>
			</div>
		</div>
	</div>

	<form id="formCategoria"></form>
</body>

<div class="footer">
	<p>Nota: recuerde que reservas mayores a 20 d&iacute;as tienen un
		descuento de 15%</p>
</div>

<script>
	function vehiculosCategoria(idCat) {
		var f = document.getElementById('formCategoria');
		f.method = "post";
		f.action = 'VehiculosCategoria?idcategoria=' + idCat;
		f.submit();
	}
</script>
</html>