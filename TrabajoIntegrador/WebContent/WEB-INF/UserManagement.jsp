<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>BlanTell Rental Cars</title>
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/start.css" rel="stylesheet">
	
	<%
		Usuario u = (Usuario)session.getAttribute("usuario");
    	LinkedList<Usuario> lu = (LinkedList<Usuario>)request.getAttribute("listaUsuarios");
	%>
	
</head>
<body>
	<div class="container">
		<div class="row">
        	<h4>Personas</h4>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>id</th>
                    		    	<th>nombre</th>
                        			<th>apellido</th>
                        			<th>email</th>
                        			<th>tel</th>
                        			<th></th>
                        			<th></th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Usuario usu : lu) { %>
                    			<tr>
                    				<td><%=usu.getIdUsuario()%></td>
                    				<td><%=usu.getNombre()%></td>
                    				<td><%=usu.getApellido()%></td>
                    				<td><%=usu.getEmail()%></td>
                    				<td><%=usu.getTel()%></td>
                    				<td></td><!-- editar -->
                    				<td></td><!-- borrar -->
                    			</tr>
                    		<% } %>
                    		</tbody>	
	</div> <!-- /container -->
</body>
</html>