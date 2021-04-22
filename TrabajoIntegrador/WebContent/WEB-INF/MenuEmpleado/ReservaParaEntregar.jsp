<%@page import="entities.Usuario"%>
<%@page import="entities.Reserva"%>
<%@page import="entities.Rol"%>
<%@page import="data.DataRol"%>
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
    <link rel="icon" href="https://static.thenounproject.com/png/1516448-200.png">
	<title>Rent Smart</title>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/agregavehiculo.css" rel="stylesheet">
	
	<%
		Usuario us = new Usuario();
    	DataRol dr = new DataRol();
    	Login ctrlLogin = new Login();	
    	Reserva re = (Reserva)session.getAttribute("reserva");
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>
	<title>Retiro/Devoluci&oacute;n</title>
</head>

<body>
	



<div class="container">
<div class="note">
              <h1>Retiro</h1>
        </div>
        <div class="form-content">
	<div class="row">
       	<h4 class="text-center">Reservas</h4>
           	<div class="col-12 col-sm-12 col-lg-12">
               	<div class="table-responsive">
                   	<table class="table order-table table-striped table-hover" id="vehicles">
                   		<thead>
                   			<tr>
                   			    <th>ID</th>
                   				<th>Fecha de reserva</th>
                   		    	<th>Fecha de retiro</th>
                       			<th>Fecha de devoluci&oacute;n</th>
                       			<th>Fecha de cancelaci&oacute;n</th>
                       			<th>Estado</th>
                       			<th>Acci&oacute;n</th>
                   			</tr>
                   		</thead>
                   		<tbody>
                   			<tr>
                   				<td><%=re.getIdReserva()%></td>
                   				<td><%=re.getFechaReserva()%></td>
                   				<td><%=re.getFechaRetiro()%></td>
                   				<td><%=re.getFechaDevolucion()%></td>
                   				<%if (re.getFechaCancelacion()==null){ %>
                   				<td>-</td>
                   				<% } else { %>
                   				<td><%=re.getFechaCancelacion()%></td>
                   				<% }%>
                   				<td><%=re.getEstado()%></td>
                   				<%if (re.getEstado().equals("Iniciada")){ %>
                   				<form name="f3" class="form-register" action="entregarVehiculo" method="post">
                   					<td><button type="submit" class="btn btn-success" >Marcar retiro</button>
                   						<input type="hidden" name="idreserva" value="<%=re.getIdReserva()%>" />
                   					</td>
                   				</form>
                   				<% } else { if (re.getEstado().equals("Retirada")){ %>
                   				<form name="f4" class="form-register" action="devolucionVehiculo" method="post">
                   					<td><button type="submit" class="btn btn-warning" >Marcar devolucion</button>
                   						<input type="hidden" name="idreserva" value="<%=re.getIdReserva()%>" />
                   					</td>
                   				</form>
                   				<% } else { %>
                   					<td><button type="submit" class="btn btn-warning" disabled="true">Sin accion</button></td>
                   					<% }} %>
                   				
                   			</tr>
                	</tbody>	
     			</table>
     		</div>
    	</div>
    	
	</div>
	<div align="center">
		<a href="home" class="btn btn-lg btn-danger">Volver al men&uacute;</a>
	</div>	
	</div>
</div>

	

</body>



</html>