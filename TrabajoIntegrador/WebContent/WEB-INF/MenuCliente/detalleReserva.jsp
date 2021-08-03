<%@page import="entities.Usuario"%>
<%@page import="entities.Categoria"%>
<%@page import="entities.Vehiculo"%>
<%@page import="entities.Reserva"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
<link href="style/confirmaReserva.css" rel="stylesheet">

<%
Reserva r = (Reserva) session.getAttribute("reserva");
Boolean descuento = false;
String desc;
Double d;
Date date1 = r.getFechaRetiro();
Date date2 = r.getFechaDevolucion();
long diff = date2.getTime() - date1.getTime();
int dias = Math.round((diff / (1000 * 60 * 60 * 24)));
if (dias > 20) {
	descuento = true;
	desc = "15%";
	d = 0.85;
} else {
	descuento = false;
	desc = "0%";
	d = 1.0;
}
;
Categoria c = (Categoria) session.getAttribute("categoria");
Double precioxdia = c.getPrecioxDia();
Vehiculo v = (Vehiculo) session.getAttribute("vehiculo");
Usuario u = (Usuario) session.getAttribute("usuario");
LinkedList<Categoria> listaCategorias = (LinkedList<Categoria>) session.getAttribute("listaCategorias");
%>



<title>Detalles de reserva</title>
</head>

<body>
	<div class="container">
		<div class="note">
			Detalles reserva ID:
			<%=r.getIdReserva()%>
		</div>
		<div class="content">
			<div class="pricing py-5">



				<div class="card">

					<div class="card-body">
						<h5 class="card-title text-muted text-uppercase text-center"><%=v.getMarca()%></h5>
						<h6 class="card-price text-center"><%=v.getModelo()%></h6>

						<div class="anio" align="center">
							Año:
							<%=v.getAnio()%></div>
						<hr>
						<p class="text-left">
							<span class="font-weight-bold">Días: </span><%=dias%></p>
						<hr>
						<div class="text-left">
							<span class="font-weight-bold">Precio/día: </span>$<%=Math.round(precioxdia)%></div>
						<hr>
						<div class="text-left">
							<span class="font-weight-bold">Descuento: </span><%=desc%></div>
						<hr>
						<div class="text-left">
							<span class="font-weight-bold">Total: </span>$<%=Math.round((precioxdia * dias) * d)%></div>
					</div>
					<div class="card-footer">
						<img class="imagen responsive" src="<%=v.getFoto()%>">
					</div>
					<%
					if (r.getEstado().equals("Iniciada")) {
					%>
					<div class="card-footer" style="">
						<a href="cancelaReserva" class="btn btn-outline-danger">Ir a
							cancelar reserva</a>
					</div>
					<%
					}
					%>
				</div>
				<br> <br>
				<div class="container-botones">
					<a href="reservasCliente" class="btn btn-success btn-block">Volver
						a mis reservas</a><a href="home" class="btn btn-danger btn-block">Volver
						al men&uacute;</a>

				</div>



			</div>
		</div>
	</div>


</body>


</html>