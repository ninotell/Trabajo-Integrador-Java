<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html lang="es"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Rent Smart">
        <meta name="author" content="Santiago Blanco y Nino Tell">
        <link rel="icon" href="https://static.thenounproject.com/png/1516448-200.png">

        <title>Rent Smart</title>
                <!-- Bootstrap core CSS -->
        <link href="style/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="style/register.css" rel="stylesheet">
        
      
      </head>

<body>
    <div class="container register-form">
                    <div class="form">
       					 <div class="note">
                    		 <h1>Crear nuevo usuario</h1>
        				  </div>

                        <div class="form-content">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input id="inputName" name="nombre" type="text" class="form-control" placeholder="Nombre *" value=""/>
                                    </div>
                                     <div class="form-group">
                                        <input id="inputApellido" name="apellido" type="text" class="form-control" placeholder="Apellido *" value=""/>
                                    </div>
                                    <div class="form-group">
   										 <label for="tipoDoc">Tipo de documento</label>
   										 <select class="form-control" id="tipoDoc">
    						  			     <option>DNI</option>
       										 <option>CUIT/CUIL</option>
       									 </select>
   									</div>
   									 <div class="form-group">
                                        <input id="inputDoc" name="doc" type="text" class="form-control" placeholder="Teléfono *" value=""/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input id="inputTel" name="telefono" type="text" class="form-control" placeholder="Teléfono *" value=""/>
                                    </div>
                                    <div class="form-group">
                                        <input id="inputDireccion" name="direccion" type="text" class="form-control" placeholder="Direccion *" value=""/>
                                    </div>
                                    <div class="form-group">
                                        <input id="inputCorreo" name="correo" type="text" class="form-control" placeholder="Correo electrónico *" required="" autofocus="" type="email"/>
                                    </div>
                                    <div class="form-group">
                                        <input id="inputPassword" name="password" type="text" class="form-control" placeholder="Contraseña *" required="" type="password"/>
                                    </div>
                                    <div class="form-group">
                                        <input id="inputConfPassword" name="confpassword" type="text" class="form-control" placeholder="Confirmar contraseña *" required="" type="password"/>
                                    </div>
                                </div>
                            </div>
                            <button type="button" class="btnSubmit">Submit</button>
                        </div>
                    </div>
                </div>

</body>

</html>
