<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cambio de contraseña</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>


	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/changePassword.css" rel="stylesheet">

</head>
	<%
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>


<body>

<div class="text-center" >
	<div class="col-auto">
		<h1 class="h3 mb-3 font-weight-normal">Cambio de contraseña para <%=u.getNombre()%> <%=u.getApellido()%></h1>
		<form class="form-register" action="changePassword" method="post">
			<input class="form-control" id="oldPassword" name="oldPassword" type="password" placeholder="Contraseña anterior"><br>
			<input class="form-control" id="newPassword" name="newPassword" type="password" placeholder="Nueva contraseña" minlength="6" maxlength="12" ><br>
			<input class="form-control" id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirmar contraseña"><br>
			<button id="updateButton" class="btn btn-success btn-block">Actualizar contraseña</button>
		</form>
	</div>
</div>

</body>

<script>
var password = document.getElementById("newPassword"),
	confirm_password = document.getElementById("confirmPassword"),
	update_button = document.getElementById("updateButton");

function validateOldPassword(){
	if (<%=request.getAttribute("errormsg")%>==true)
	{alert("La contraseña anterior no es correcta");}
	else {}
}

function validateConfirmPassword(){
	if(password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Las contraseñas no coinciden");
	} else {
		confirm_password.setCustomValidity('');
	}
}
password.onchange = validateConfirmPassword;
confirm_password.onkeyup = validateConfirmPassword;
window.onload = validateOldPassword;

</script>
</html>