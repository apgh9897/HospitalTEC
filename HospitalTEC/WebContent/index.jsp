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
    
    <title>LogInUsuario</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sign-in/">

    <!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
	<style>

	body{
	width: 100%;
	top: 0;
	padding:0;
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
    </style>
    
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <link href="css/navbar-top.css" rel="stylesheet">
  </head>
  <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
	  <a class="navbar-brand" href="#">HospitalTEC :3</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarCollapse">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="agregarPaciente.jsp">Agregar Paciente</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="agregarFuncionario.jsp">Agregar Funcionario</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="admin.jsp">Administrador</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="doctor.jsp">Doctor</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="paciente.jsp">Paciente</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="enfermero.jsp">Enfermero</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="secretario.jsp">Secretario</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	<main role="main" class="container">
	  <body class="text-center">
	    <form class="form-signin">
		  <h1 class="h3 mb-3 font-weight-normal">Log In Usuario</h1>
		  <label for="inputUsuario" class="sr-only">Usuario</label>
		  <input type="email" id="inputEmail" class="form-control" placeholder="Usuario" required autofocus>
		  <label for="inputPassword" class="sr-only">Password</label>
		  <input type="password" id="inputPassword" class="form-control" placeholder="Contraseña" required>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
		  <p class="mt-5 mb-3 text-muted">&copy; HospitalTEC-2020</p>
		</form>
	</main>
</body>
	
</html>
