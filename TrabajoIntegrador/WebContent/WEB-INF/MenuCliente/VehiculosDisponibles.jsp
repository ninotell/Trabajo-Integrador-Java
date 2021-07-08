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
<title>Rent Smart</title>

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
<link href="style/vehiculosDisponibles.css" rel="stylesheet">

<%
	String fechadesde = (String) session.getAttribute("fechadesde");
	String fechahasta = (String) session.getAttribute("fechahasta");
	LinkedList<Vehiculo> vDisponibles = (LinkedList<Vehiculo>) session.getAttribute("vDisponibles");
	Usuario u = (Usuario) session.getAttribute("usuario");
%>
<title><%=u.getNombre()%> <%=u.getApellido()%></title>

</head>

<body>


	<div class="container text-center">
		<div class="note">
			<h4>
				Veh&iacute;culos disponibles desde el
				<%=fechadesde%>
				hasta el
				<%=fechahasta%></h4>
		</div>
		<%
			if ((vDisponibles.isEmpty()) == true) {
		%>
		<h8>No hay veh&iacute;culos para mostrar</h8>
		<button onclick="window.history.back()" class="btn btn-danger">Volver
			a selecci&oacute;n de fechas</button>
		<%
			} else {
		%>

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover" id="vehicles">
						<thead>
							<tr>
								<th><a href="#" onclick="sortTable(0, 'str')">Marca</a></th>
								<th><a href="#" onclick="sortTable(1, 'str')">Modelo</a></th>
								<th><a href="#" onclick="sortTable(2, 'int')">Año</a></th>
								<th><a href="#" onclick="sortTable(2, 'int')">Transmisi&oacute;n</a></th>
								<th><a href="#">Foto</a></th>

							</tr>
						</thead>
						<tbody>
							<%
								for (Vehiculo v : vDisponibles) {
							%>
							<tr>
								<td><%=v.getMarca()%></td>
								<td><%=v.getModelo()%></td>
								<td><%=v.getAnio()%></td>
								<td><img class="rounded" src="imagenVehiculo?idv=<%=v.getIdVehiculo()%>" width="150"></td>
								<form name="f2" class="form-reserva" action="confirmaReserva"
									method="get">
									<input type="hidden" name="idvehiculo"
										value="<%=v.getIdVehiculo()%>" />
									<td><button type="submit" class="btn btn-success">Reservar!</button></td>

								</form>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div align="center">
		<div class="botonVolver">
			<button onclick="window.history.back()"
				class="btn btn-danger btn-block text-center">Volver a
				selecci&oacute;n de fechas</button>
		</div>
	</div>
	<%
		}
	%>

</body>
<script>
	function sortTable(n, type) {
		var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

		table = document.getElementById("vehicles");
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
					if ((type == "str" && x.innerHTML.toLowerCase() > y.innerHTML
							.toLowerCase())
							|| (type == "int" && parseFloat(x.innerHTML) > parseFloat(y.innerHTML))) {
						//if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (dir == "desc") {
					if ((type == "str" && x.innerHTML.toLowerCase() < y.innerHTML
							.toLowerCase())
							|| (type == "int" && parseFloat(x.innerHTML) < parseFloat(y.innerHTML))) {
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
				switchcount++;
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


</html>