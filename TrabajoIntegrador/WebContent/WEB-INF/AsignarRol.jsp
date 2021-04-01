<%@page import="entities.Usuario"%>
<%@page import="entities.Vehiculo"%>
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
	<title>BlanTell Rental Cars</title>
	
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
    	Rol rr = new Rol();
		rr.setIdRol(1);
		rr = dr.getById(rr);
    	Login ctrlLogin = new Login();	
		LinkedList<Usuario> lu = ctrlLogin.listaUsuarios();	
		Usuario u = (Usuario)session.getAttribute("usuario");
// 		Integer idLogueado = u.getIdUsuario;
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
      		<li class="nav-item ">
        		<a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
	        </li>
      		<li class="nav-item">
        		<a class="nav-link" href="listavehiculos">Vehiculos</a>
	        </li>
	        <li class="nav-item active">
        		<a class="nav-link" href="" onclick="location.reload(true)">Asignar rol</a>
	        </li>
	    </ul>
	     <span class="dropdown">
      			  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
          			ID: <%=u.getIdUsuario()%> - <%=u.getNombre()%> <%=u.getApellido()%>
       			  </a>
    		     <div class="dropdown-menu">
          			<a class="dropdown-item" href="#">Cambiar contraseña</a>
          			<a class="dropdown-item" href="index.html">Cerrar sesión</a>
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
	
 	<div class="container">
		<div class="row">
        	<caption><h5>Empleados</h5></caption>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table table-success" id="usuarios">
                    		<thead>
                    			<tr>
                    				<th onclick="sortTable(0, 'int')">ID</th>
                    				<th onclick="sortTable(1, 'str')">Apellido</th>
                    		    	<th onclick="sortTable(2, 'str')">Nombre</th>
                    		    	<th onclick="sortTable(3, 'str')">Documento</th>
                    		    	<th onclick="sortTable(4, 'int')">Dirección de correo</th>
                        			<th onclick="sortTable(5, 'int')">Contraseña</th>
                        			<th onclick="sortTable(6, 'int')">Rol</th>
                        			<th>Actualizar</th>
                        			<th>Eliminar</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Usuario uss : lu) { if (u.getIdUsuario() == uss.getIdUsuario()) {} else {if (uss.hasRol(rr)) {%>
                    			<tr>
                    				<td><%=uss.getIdUsuario()%></td>
                    				<td><%=uss.getApellido()%></td>
                    				<td><%=uss.getNombre()%></td>
                    				<td><%=uss.getDocumento().getNro()%></td> 
                    				<td><%=uss.getEmail()%></td> 
                    				<td><%=uss.getPassword()%></td> 
                    				<td>Empleado</td>
                    				<form name="f2" class="form-register" action="editarolusuario" method="post">
                    				<td><button type="submit" class="btn btn-primary">Actualizar Rol</button></td>
                    					<input type="hidden" name="idusuario" value="<%=uss.getIdUsuario()%>" />
                    				</form>
                    				
                    				<form name="f3" class="form-register" action="eliminausuario" method="post">
                    						<td><button type="submit" class="btn btn-danger" >Eliminar</button>
                    						 <input type="hidden" name="idusuario" value="<%=uss.getIdUsuario()%>" />
                    					    </td>
                    				</form>
                    			</tr>
                    		<% }}} %>
                    		</tbody>	
       	</div>
       	
       	

       	<div class="container">
		<div class="row">
        	<caption><h5>Clientes</h5></caption>
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table table-secondary table-hover" id="usuarios">
                    		<thead>
                    			<tr>
                    				<th onclick="sortTable(0, 'int')">ID</th>
                    				<th onclick="sortTable(1, 'str')">Apellido</th>
                    		    	<th onclick="sortTable(2, 'str')">Nombre</th>
                    		    	<th onclick="sortTable(3, 'str')">Documento</th>
                    		    	<th onclick="sortTable(4, 'int')">Dirección de correo</th>
                        			<th onclick="sortTable(5, 'int')">Contraseña</th>
                        			<th onclick="sortTable(6, 'int')">Rol</th>
                        			<th>Actualizar</th>
                        			<th>Eliminar</th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Usuario uss : lu) { if (uss.hasRol(rr)) {} else {%>
                    			<tr>
                    				<td><%=uss.getIdUsuario()%></td>
                    				<td><%=uss.getApellido()%></td>
                    				<td><%=uss.getNombre()%></td>
                    				<td><%=uss.getDocumento().getNro()%></td> 
                    				<td><%=uss.getEmail()%></td> 
                    				<td><%=uss.getPassword()%></td> 
                    				<td>Cliente</td>
									<form name="f4" class="form-register" action="editarolusuario" method="post">
                    				<td><button type="submit" class="btn btn-primary" action="eliminausuario">Actualizar Rol</button></td>
                    					<input type="hidden" name="idusuario" value="<%=uss.getIdUsuario()%>" />
                    				</form>
                    				                   				
                    				<form name="f5" class="form-register" action="eliminausuario" method="post">
                    						<td><button type="submit" class="btn btn-danger" >Eliminar</button>
                    						 <input type="hidden" name="idusuario" value="<%=uss.getIdUsuario()%>" />
                    					    </td>
                    				</form>
                    			</tr>
                    		<% }} %>
                    		</tbody>	
       	</div>
	
<script>
//        function mostrarOcultarTablas(id){
// 		mostrado=0;
// 			elem = document.getElementById(id);
// 			if(elem.style.display=='block')mostrado=1;
// 			elem.style.display='none';
// 			if(mostrado!=1)elem.style.display='block';
// 	}

       function sortTable(n,type) {
    	   var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    	  
    	   table = document.getElementById("usuarios");
    	   switching = true;
    	   //Set the sorting direction to ascending:
    	   dir = "asc";
    	  
    	   /*Make a loop that will continue until no switching has been done:*/
    	   while (switching) {
    	     //start by saying: no switching is done:
    	     switching = false;
    	     rows = table.rows;
    	     /*Loop through all table rows (except the first, which contains table headers):*/
    	     for (i = 1; i < (rows.length - 1); i++) {
    	       //start by saying there should be no switching:
    	       shouldSwitch = false;
    	       /*Get the two elements you want to compare, one from current row and one from the next:*/
    	       x = rows[i].getElementsByTagName("TD")[n];
    	       y = rows[i + 1].getElementsByTagName("TD")[n];
    	       /*check if the two rows should switch place, based on the direction, asc or desc:*/
    	       if (dir == "asc") {
    	         if ((type=="str" && x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) || (type=="int" && parseFloat(x.innerHTML) > parseFloat(y.innerHTML))) {
    	           //if so, mark as a switch and break the loop:
    	           shouldSwitch= true;
    	           break;
    	         }
    	       } else if (dir == "desc") {
    	         if ((type=="str" && x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) || (type=="int" && parseFloat(x.innerHTML) < parseFloat(y.innerHTML))) {
    	           //if so, mark as a switch and break the loop:
    	           shouldSwitch = true;
    	           break;
    	         }
    	       }
    	     }
    	     if (shouldSwitch) {
    	       /*If a switch has been marked, make the switch and mark that a switch has been done:*/
    	       rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
    	       switching = true;
    	       //Each time a switch is done, increase this count by 1:
    	       switchcount ++;
    	     } else {
    	       /*If no switching has been done AND the direction is "asc", set the direction to "desc" and run the while loop again.*/
    	       if (switchcount == 0 && dir == "asc") {
    	         dir = "desc";
    	         switching = true;
    	       }
    	     }
    	   }
    	 }
       
</script>
 	
</body>
</html>