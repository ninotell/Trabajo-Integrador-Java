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
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/MenuCliente.css" rel="stylesheet">
	
	<%
		Vehiculo ve = new Vehiculo();
    	Login ctrlLogin = new Login();	
		ve.setAnio(2000);
    	LinkedList<Vehiculo> lv = ctrlLogin.getByAnio(ve);	
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>
	<title><%=u.getNombre()%> <%=u.getApellido()%></title>
	
</head>

<body>
	<div id="header">
			<ul class="nav">
				<li><a href="">Inicio</a></li>
				<li><a href="">Servicios</a>
					<ul>
						<li><a href="">Submenu1</a></li>
						<li><a href="">Submenu2</a></li>
						<li><a href="">Submenu3</a></li>
						<li><a href="">Submenu4</a>
							<ul>
								<li><a href="">Submenu1</a></li>
								<li><a href="">Submenu2</a></li>
								<li><a href="">Submenu3</a></li>
								<li><a href="">Submenu4</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="">Acerca de</a>
					<ul>
						<li><a href="">Submenu1</a></li>
						<li><a href="">Submenu2</a></li>
						<li><a href="">Submenu3</a></li>
						<li><a href="">Submenu4</a></li>
					</ul>
				</li>
				<li><a href="">Contacto</a></li>
			</ul>
		</div>
	
<!--  	<div class="container"> -->
<!-- 		<div class="row"> -->
<!--         	<h4>Vehiculos</h4> -->
<!--             	<div class="col-12 col-sm-12 col-lg-12"> -->
<!--                 	<div class="table-responsive"> -->
<!--                     	<table class="table"> -->
<!--                     		<thead> -->
<!--                     			<tr> -->
<!--                     				<th>ID</th> -->
<!--                     				<th>Patente</th> -->
<!--                     		    	<th>Marca</th> -->
<!--                         			<th>Modelo</th> -->
<!--                         			<th>Transmision</th> -->
<!--                         			<th>KM</th> -->
<!--                         			<th>Año</th> -->
<!--                         			<th></th> -->
<!--                         			<th></th> -->
<!--                       			</tr> -->
<!--                       		</thead> -->
<!--                     		<tbody> -->
<%--                     		<% for (Vehiculo v : lv) { %> --%>
<!--                     			<tr> -->
<%--                     				<td><%=v.getIdVehiculo()%></td> --%>
<%--                     				<td><%=v.getPatente()%></td> --%>
<%--                     				<td><%=v.getMarca()%></td> --%>
<%--                     				<td><%=v.getTransmision()%></td> --%>
<%--                     				<td><%=v.getModelo()%></td> --%>
<%--                     				<td><%=v.getKm()%></td> --%>
<%--                     				<td><%=v.getAnio()%></td> --%>
<!--                     				<td></td>editar -->
<!--                     				<td></td>borrar -->
<!--                     			</tr> -->
<%--                     		<% } %> --%>
<!--                     		</tbody>	 -->
<!-- 	</div> /container -->
 	
</body>
</html>