<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Agregar Paciente</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/checkout/">

    <!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

    <style>
	    body{
		width: 100%;
		}
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      .container-form{
      margin: auto;
      max-width: 600px;
      width: 100%
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="css/form-validation.css" rel="stylesheet">
    <link href="css/navbar-top.css" rel="stylesheet">
  </head>
  <body class="bg-light">
	  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
	  <a class="navbar-brand" href="#">HospitalTEC :3</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarCollapse">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="index.jsp">Regresar</a>
	      </li>
	    </ul>
	  </div>
	</nav>
    <div class="container">
  <div class="py-5 text-center">
    <h2>Agregar Paciente</h2>
  </div>
  <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Completar forms</h4>
      <form class="needs-validation" novalidate>
      
      <div class="mb-3">
          <label for="email">Centro Medico <span class="text-muted"></span></label>
          <select class="custom-select d-block w-100" id="centroMedico" required>
              <option value="">Choose...</option>
          </select>
        </div>
        
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nombre</label>
            <input type="text" class="form-control" id="firstName" placeholder="Nombre del Paciente" value="" required>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Cedula</label>
            <input type="text" class="form-control" id="Cedula" placeholder="Cedula" value="" required>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="address">Tipos de Sangre</label>
          <input type="text" class="form-control" id="TipoSangre" placeholder="Tipo de Sangre" required>
          <div class="invalid-feedback">
            Please enter your shipping address.
          </div>
        </div>

        <div class="mb-3">
          <label for="address2">Fecha de Nacimiento <span class="text-muted"></span></label>
          <input type="text" class="form-control" id="FechaNacimiento" placeholder="Fecha de nacimiento">
        </div>
        <div class="mb-3">
          <label for="address2">Nacionalidad<span class="text-muted"></span></label>
          <input type="text" class="form-control" id="Nacionalidad" placeholder="Nacionalidad">
        </div>

        <div class="row">
          <div class="col-md-5 mb-3">
            <label for="country">Provincia</label>
            <input type="text" class="form-control" id="Provincia" placeholder="Provincia">
            <div class="invalid-feedback">
              Please select a valid country.
            </div>
          </div>


        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Paciente</button>
      </form>
    </div>
  </div>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; HospitalTEC 2020</p>
  </footer>
</div>
</html>