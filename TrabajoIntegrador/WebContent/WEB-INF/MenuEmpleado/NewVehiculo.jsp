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
        
        <link rel="stylesheet" href="http://jqueryvalidation.org/files/demo/site-demos.css">

        
        </head>
<body>

<form name="f1" class="form-register" action="agregavehiculo" method="post">
    <div class="container register-form">
                    <div class="form">
       					 <div class="note">
                    		 <h1>Agregar nuevo vehiculo</h1>
        				  </div>

                        <div class="form-content">
                            <div class="row">
                                <div class="col">
                                  <div class="form-group">
   										 <label for="categoria">Cateoría     1)Chico - 2)Mediano - 3) Grande</label>
   										 <select class="form-control" id="inputCategoria" name="categoria">
    						  			     <option>1</option>
       										 <option>2</option>
       										 <option>3</option>
       									 </select>
   								</div>
                                  
                                  <div class="form-group">
                                        <input id="inputPatente" name="patente" type="text" class="form-control" placeholder="Patente *" autofocus="" required="" value=""/>
                                    </div>
                                    <div class="form-group">
                                        <input id="inputMarca" name="marca" type="text" class="form-control" placeholder="Marca *" required="" value=""/>
                                    </div>
                                     <div class="form-group">
                                        <input id="inputModelo" name="modelo" type="text" class="form-control" placeholder="Modelo *" required="" value=""/>
                                    </div>
                                    <div class="form-group">
   										 <label for="transmision">Transmision</label>
   										 <select class="form-control" id="Transmision" name="transmision">
    						  			     <option>Automático</option>
       										 <option>Manual</option>
       									 </select>
   								    </div>
   									<div class="form-group">
                                        <input id="inputAño" name="año" type="number" class="form-control" placeholder="Año *" required="" value=""/>
                                 	</div>
                                    <div class="form-group">
                                        <input id="inputKm" name="km" type="text" class="form-control" placeholder="Km *" required="" value=""/>
                                    </div>
                               
                          </div>
                          <button type="submit" class="btn btn-lg btn-success btn-block" name="botoncrear" >Agregar vehiculo</button>
                          </div>
                    </div>
                </div>
	</form>

</body>
</html>