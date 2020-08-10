<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<!--BOOTSTRAP-->
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Administrador</title>

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
    <h2>Administrador</h2>
  </div>
 <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Agregar Tipo de Centro</h4>
      	<form action="Admin" method="POST">
          <div class="mb-3">
            <label for="firstName">Nombre de Tipo de Centro</label>
            <input type="text" class="form-control" id="TipoCentro" placeholder="CCSS/EBAIS/Clinica" value="" required>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Tipo</button>
        </form>
    </div>
  </div>
  .
  .
  .
  <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Agregar Area de Especialidad</h4>
      <form class="needs-validation" novalidate>
          <div class="mb-3">
            <label for="firstName">Nombre de Area de Especialidad</label>
            <input type="text" class="form-control" id="AreaEspecialidad" placeholder="Oncologia" value="" required>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Especialidad</button>
      </form>
    </div>

  .
  .
  .
  <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Agregar Centro De Atencion</h4>
      <form class="needs-validation" novalidate>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nombre de Centro de Atencion</label>
            <input type="text" class="form-control" id="firstName" placeholder="Susana Distancia" value="" required>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Ubicacion</label>
            <input type="text" class="form-control" id="Ubicacion" placeholder="Ubicacion" value="" required>
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">Tipo de Centro <span class="text-muted"></span></label>
          <select class="custom-select d-block w-100" id="tipoCentroSelect" required>
              <option value="">Choose...</option>
          </select>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Centro de Atencion</button>
      </form>
    </div>
 

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; HospitalTEC 2020</p>
  </footer>

</html>