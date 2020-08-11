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
    <title>Cita</title>

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
	        <a class="nav-link" href="paciente.jsp">Regresar</a>
	      </li>
	    </ul>
	  </div>
	</nav>
    <div class="container">
  <div class="py-5 text-center">
    <h2>Cita</h2>
  </div>
 <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Agregar Cita</h4>
      <form class="needs-validation" novalidate>
          <div class="col-md-6 mb-3">
            <label for="email">Area de Aplicacion <span class="text-muted"></span></label>
            <select class="custom-select d-block w-100" id="estadoDeCitaSelect" name="estadoDeCitaSelect" required>
              <option value="">Choose...</option>
          </select>
          </div>
          <div class="mb-3">
          	<label for="address2">Fecha de cita <span class="text-muted"></span></label>
          <input type="text" class="form-control" id="FechaCita" name="FechaCita" placeholder="15/08/2020">
        </div>
        <div class="mb-3">
          	<label for="address2">Hora de cita <span class="text-muted"></span></label>
          <input type="text" class="form-control" id="HoraCita" name="HoraCita" placeholder="12:00">
        </div>
        <div class="mb-3">
          	<label for="address2">Observaciones <span class="text-muted"></span></label>
          <input type="text" class="form-control" id="observaciones" name="observaciones" placeholder="">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Cita</button>
      </form>
    </div>
  </div>
  

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; HospitalTEC 2020</p>
  </footer>
</div>
</html>