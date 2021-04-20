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
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	<title>Rent Smart</title>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/asignarol.css" rel="stylesheet">
	
	<%
		Usuario us = new Usuario();
    	DataRol dr = new DataRol();
    	Login ctrlLogin = new Login();	
    	LinkedList<Reserva> reservasUsuario = (LinkedList<Reserva>) session.getAttribute("reservasUsuario");
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>
	<title>Cancelar Reserva</title>
</head>

<body>
	


<div class="container">	
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand" href="home">
		<img class="rounded mx-auto d-block" src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png" alt="" width="50" height="50"></a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" 
          data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
          aria-expanded="false" aria-label="Toggle navigation">
    	 <span class="navbar-toggler-icon"></span>
	    </button>

 	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item active ">
        		<a class="nav-link" href="home">Home<span class="sr-only">(current)</span></a>
	        </li>
      		<li class="nav-item">
        		<a class="nav-link" href="reservasCliente">Mis reservas<span class="sr-only">(current)</span></a>
     		 </li>
		</ul>
	     <span class="dropdown">
      			  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
          			ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%> <%=u.getApellido()%>
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
 
 	<hr>
 	<hr>
 	<hr>
 	<hr>
 	<hr>

<br>

<div class="container">
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
                   			</tr>
                   		</thead>
                   		<tbody>
                    		<% for (Reserva r : reservasUsuario) {if (r.getEstado().equals("iniciada") ) { %> 
                   			<tr>
                   				<td class="idvehiculo"><%=r.getIdReserva()%></td>
                   				<td><%=r.getFechaReserva()%></td>
                   				<td><%=r.getFechaRetiro()%></td>
                   				<td><%=r.getFechaDevolucion()%></td>
                   				<%if (r.getFechaCancelacion()==null){ %>
                   				<td>-</td>
                   				<% } else { %>
                   				<td><%=r.getFechaCancelacion()%></td>
                   				<% }%>
                   				<td><%=r.getEstado()%></td>
                   				<form name="f3" class="form-register" action="cancelarReserva" method="post">
                   					<td><button type="submit" class="btn btn-danger" >Cancelar</button>
                   						<input type="hidden" name="idreserva" value="<%=r.getIdReserva()%>" />
                   					</td>
                   				</form>
                   			</tr>
                    		<% } } %>
                	</tbody>	
     			</table>
     		</div>
    	</div>
	</div>	
</div>

	

</body>



</html>