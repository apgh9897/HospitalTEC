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
    <h2>Agregar Funcionario</h2>
  </div>
  <div class="col-md-8 order-md-1 container-form">
      <h4 class="mb-3">Completar forms</h4>
      <form class="needs-validation" novalidate>
      
       <div class="mb-3">
          <label for="email">Centro de Atencion <span class="text-muted"></span></label>
          <select class="custom-select d-block w-100" id="tipoCentroSelect" name="tipoCentroSelect" required>
              <option value="">Choose...</option>
               <option value="">Clinica</option>
                <option value="">EBAIS</option>
                 <option value="">Centro Medico</option>
                  <option value="">Hospital Privado</option>
                   <option value="">Hospital de la Caja</option>
          </select>
        </div>
        
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nombre</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required>
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="email">Tipo de Funcionario <span class="text-muted"></span></label>
            <select class="custom-select d-block w-100" id="tipoFuncionarioSelect" name="tipoFuncionarioSelect" required>
              <option value="">Choose...</option>
              <option value="">Medico</option>
              <option value="">Enfermero</option>
              <option value="">Secretario</option>
          </select>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">Email <span class="text-muted"></span></label>
          <input type="email" class="form-control" id="email" name="email" placeholder="">
          <div class="invalid-feedback">
            Please enter a valid email address for shipping updates.
          </div>
        </div>

        <div class="mb-3">
          <label for="password">Password <span class="text-muted"></span></label>
		  <input type="password" id="inputPassword" class="form-control" placeholder="" required>
          <div class="invalid-feedback">
            Please enter your shipping address.
          </div>
        </div>

        <div class="mb-3">
          <label for="address2">Fecha de ingreso <span class="text-muted"></span></label>
          <input type="text" class="form-control" id="FechaIngreso" name="FechaIngreso" placeholder="">
        </div>
        
        <div class="mb-3">
        	<label for="email">Area de Especialidad <span class="text-muted"></span></label>
             <select class="custom-select d-block w-100" id="areaEspecialidad" name="areaEspecialidad" required>
	              <option value="">Choose...</option>
	               <option value="">Oncologia</option>
	                <option value="">Medicina Genera</option>
	                 <option value="">Pediatria</option>
	                 <option value="">Gastroenterología</option>
	                 
            </select>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Crear Funcionario</button>
      </form>
    </div>
  </div>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; HospitalTEC 2020</p>
  </footer>
</div>
</html>