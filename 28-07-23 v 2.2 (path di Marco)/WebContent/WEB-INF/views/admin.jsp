<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADMIN COSTRUZIONE TRENI</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>

</head>
<body>
   <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                
                
                    <c:if test="${not empty username}">
                        <li class="nav-item">
                            <span class="nav-link">Benvenuto, ${username}!</span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link">Budget: ${budget}</span>
                        </li>
                        <li class="nav-item">
                            <span class="nav-link">Ruolo: ${authorities}</span>
                        </li>
                        <li class="nav-item">
                            <!-- Form di logout -->
                            <form class="nav-link" action="/springAutenticazione/logout" method="post">
                                <button type="submit" class="btn btn-primary">Logout</button>
                            </form>
                        </li>
                    </c:if>
                    
                    
                    <c:if test="${empty username}">
                        <li class="nav-item">
                            <!-- Form di login -->
                            <form action="/springAutenticazione/public/login" method="get">
                                <button type="submit" class="btn btn-primary" style="margin-right:5px;">Login</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <!-- Form di registrazione -->
                            <form action="/springAutenticazione/public/registrazione" method="get">
                                <button type="submit" class="btn btn-primary">Registrati</button>
                            </form>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
    
   

   
    
    
    
  <!-- Header -->
    <div id="titolo" class="headBar text-center py-10">
        <h1>COSTRUZIONE TRENI</h1>
    </div>
    
    <!-- Body -->
    <div class="menu py-3">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="../user/viewCostruzione">CREA TRENO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../user/visualizzaTreno">VISUALIZZA TRENO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../user/cercaOrdini">I MIEI ORDINI</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../admin/ordini">TUTTI GLI ORDINI</a>
            </li>
        </ul>
    </div>
    
<footer class="footer">
        <div class="container">
            <p>&copy; 2023 Eugenio, Federico, Marco</p>
        </div>
    </footer>


     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>