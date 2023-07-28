<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<html>
<head>
	<title>COSTRUZIONE TRENI</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
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
                            <!-- Form di logout -->
                            <form class="nav-link" action="../logout" method="post">
                                <button type="submit" class="btn btn-primary">Logout</button>
                            </form>
                        </li>
                    </c:if>
                    <c:if test="${empty username}">
                        <li class="nav-item">
                            <!-- Form di login -->
                            <form action="../public/login" method="get">
                                <button type="submit" class="btn btn-primary" style="margin-right:5px;">Login</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <!-- Form di registrazione -->
                            <form action="../public/registrazione" method="get">
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
            COSTRUZIONE TRENI    
    </div>
    
    <!-- <div class="navbar">
    	<form method="get" action="viewLogin">
	    	<div id="greeting" class="greeting">${nomeUtente }</div>
	    	<input type="submit" class="log" value= ${logButton }>
	    </form>
	</div>-->
    
    <!-- div class="menu">
	    	<ul>
	    		<li>
	    			<a href="viewCostruzione">CREA TRENO</a>
	    		</li>
	    		<li>
	    			<a href="viewTreno">VISUALIZZA TRENO</a>
	    		</li>
	    		<li>
	    			<a href="viewOrdini">I MIEI ORDINI</a>
	    		</li>
	    	</ul>
	    </div>-->
	    
	    
	    <!-- Body -->
    <div class="menu py-3">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="../user/viewCostruzione">CREA TRENO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../user/viewTreno">VISUALIZZA TRENO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../user/viewOrdini">I MIEI ORDINI</a>
            </li>
        </ul>
    </div>
	
</body>

</html>