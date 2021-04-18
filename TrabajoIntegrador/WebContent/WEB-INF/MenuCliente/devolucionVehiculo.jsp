<%@page import="entities.Usuario"%>
<%@page import="entities.Reserva"%>
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
	<title>Rent Smart</title>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/vehiculosDisponibles.css" rel="stylesheet">
	
	<%
		LinkedList<Reserva> reservasRetiradas = (LinkedList<Reserva>)session.getAttribute("reservasRetiradas");	
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>
	<title>Alquileres retirados</title>
	
</head>

<body>

 
<div class="container text-center">
	<div class="note">
		<h4>Reservas retiradas de <%=u.getNombre()%> <%=u.getApellido()%></h4>
	</div>
		<%if ((reservasRetiradas.isEmpty())==true) { %>
		<strong><h8>No hay alquileres para mostrar</h8></strong><br><br>
		<button onclick="window.history.back()" class="btn btn-danger">Volver al men&uacute; principal</button>
		<% } else { %>
		
		<div class="row">
        	<div class="col-lg-12">
                	<div class="table-responsive">
                    	<table class="table table-striped table-hover" id="vehicles">
                    		<thead>
                    			<tr>
                    				<th>N&uacute;mero</th>
                        			<th>Fecha de reserva</th>
                        			<th>Fecha desde</th>
                        			<th>Fecha hasta</th>
                        			<th>Estado</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Reserva r : reservasRetiradas) { %>
                    			<tr>
                    				<td><%=r.getIdReserva()%></td>
                    				<td><%=r.getFechaReserva()%></td>
                    				<td><%=r.getFechaRetiro()%></td>
                    				<td><%=r.getFechaDevolucion()%></td>
                    				<%if (r.getEstado().equals("retirado")) { %>
                    				<td>Retirado</td>
                    				<%} %>
                    				
                    				<form name="f2" class="form-reserva" action="confirmaReserva" method="post">
                    					<input type="hidden" name="idvehiculo" value="<%=r.getIdReserva()%>" />
                    					<td><button type="submit" class="btn btn-success">Confirmar devolucion</button></td>
                    					
                    				</form>
                    			</tr>
                    		<% } %>
                    		</tbody>
                    		</table>
       			</div>
       		</div>
       	</div>
</div>
<div align="center">       
	<div class="botonVolver">
		<a href="home" class="btn btn-danger btn-block text-center">Volver al men&uacute; principal</a>
	</div>
</div>
       	<% } %>

</body>
	

</html>