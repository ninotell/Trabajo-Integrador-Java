<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	

	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/nuevaReserva.css" rel="stylesheet">
	
	<%
		Usuario u = (Usuario)session.getAttribute("usuario");

	%>

	<title>Nueva reserva</title>

</head>


<body>

    <div class="container">
       <div class="note">
              <h1>Haga su reserva</h1>
        </div>
        <div class="form-newvehiculo">
                     <div class="form-content">
                            <div class="row">
                                <div class="col">
                                  <div class="form-group">
   										 <label for="categoria">Cateoría     1) Chico - 2) Mediano - 3) Grande</label>
   										 <select class="form-control" id="inputCategoria" name="categoria">
    						  			     <option>1</option>
       										 <option>2</option>
       										 <option>3</option>
       									 </select>
   								</div>
                                <div class="row">
                          			  <div class="col-md-6">
                          			  	<label for="fecha-desde">Fecha desde</label>
                                		<div class="form-group"> <input id="fecha-desde" class="form-control" type="date" required> </div>
		                              </div>
          			                  <div class="col-md-6">
          			                  	<label for="fecha-hasta">Fecha hasta</label>
                    		            <div class="form-group"> <input id="fecha-hasta" class="form-control" type="date" required data-date-end-date="0d"> </div>
                            		  </div>
                        		</div>  
                                <div class="form-group">
   										 <label for="transmision">Transmision</label>
   										 <select class="form-control" id="Transmision" name="transmision">
    						  			     <option>Automático</option>
       										 <option>Manual</option>
       									 </select>
   								    </div>
                               
                     		     </div>
                          <button type="submit" class="btn btn-lg btn-success btn-block" id="botonMostrar" >Mostrar vehiculos disponibles</button>
                          </div>
                    </div>
                </div>
	</form>
	<button onclick="alerta()" class="btn-success btn-block" id="botonnn" >Mostrar</button>


</body>
<script type="text/javascript">
var fechadesde = new Date($('#fecha-desde').val()),
	fechahasta = document.getElementById("fecha-hasta").value,
	boton = document.getElementById("botonnn"),
    validarFecha = function(date1, date2) {
		dt1 = new Date(date1);
		dt2 = new Date(date2);
		return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
	}
	

console.log(validarFecha('04/02/2014', '11/04/2014'));
console.log(boton);
function alerta(){
	alert((new Date()).toLocaleString());
}




// function validarFecha(){
// 	if (dateDiffInDays(fechadesde,fechahasta) < 3)
// 	{	
// 		fechahasta.setCustomValidity("Las reserva debe ser mayor a 2 días")
// 	}
// 	else { 
// 			fechahasta.setCustomValidity("");
// 	}
// }


</script>

</html>