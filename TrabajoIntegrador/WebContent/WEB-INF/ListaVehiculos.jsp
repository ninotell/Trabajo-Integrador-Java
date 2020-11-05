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
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
<div class="container">	
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand" href="#">
		<img class="rounded mx-auto d-block" src="https://i.pinimg.com/originals/cd/ba/7a/cdba7ad02665c51892c4860f6fc201af.png" alt="" width="50" height="50"></a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" 
          data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
          aria-expanded="false" aria-label="Toggle navigation">
    	 <span class="navbar-toggler-icon"></span>
	    </button>

 	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item active">
        		<a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
	        </li>
      		<li class="nav-item">
        		<a class="nav-link" href="WEB-INF/ListaVehiculos.jsp">Vehiculos</a>
	        </li>
	        <li class="nav-item dropdown">
      			  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
           			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          			<%=u.getNombre()%> <%=u.getApellido()%>
       			 </a>
    		    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          			<a class="dropdown-item" href="#">Action</a>
          			<a class="dropdown-item" href="#">Another action</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item" href="#">Something else here</a>
			     </div>
	        </li>
	        <li>
	        
	        </li>
	    </ul>
     </div>
  </nav>
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