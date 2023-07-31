<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>VISUALIZZA TRENI</title>

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
            <a class="navbar-brand" href="../public/home">TORNA ALLA HOME</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
           <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <c:if test="${not empty username}">
                    <li class="nav-item">
                        <span class="nav-link">Utente: ${username}</span>
                    </li>
                    <li class="nav-item">
                        <span class="nav-link">Budget: ${budget}</span>
                    </li>
                     <li class="nav-item">
                        <span class="nav-link">Ruolo: ${authorities}</span>
                    </li>
                    
                    <c:if test="${authorities.contains('ADMIN')}">
                        <li class="nav-item">
                            <a class="nav-link" href="../admin/administrator">Pannello Admin</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <!-- Form di logout -->
                        <form class="nav-link" action="../logout" method="post">
                            <button type="submit" class="btn btn-primary">Logout</button>
                        </form>
                    </li>
                    
                </c:if>
            </ul>
        </div>
    </div>
    </nav>

	<div id="titolo" class="headBar">
            CERCA TRENO    
    </div>

	<div class="inputUtente">

		<form id="cercaForm" action="../user/viewTreno/findTreno" class="formSigla" method="post">
				
			<div class="formLabel">INSERISCI ID DEL TRENO DA CERCARE</div>		
			
			<input type="text" id="idTreno" class="inputSigla" name="siglaTreno" placeholder="Inserisci ID del treno" required>
			<input type="reset" class="creaButton" value="RESET" id="reset">
			<input type="submit" class="creaButton" value="CERCA">
		</form>
		
	</div>
	
	<div id="risultato" class="resultViewTreno">
	</div>
	

</body>

<footer>

	<script>
	
	$(document).ready(function() {
	    $('#cercaForm').submit(
	        function(event) {
	        	$('#risultato').html('STIAMO ESEGUENDO LA TUA RICHIESTA');
	            var idTreno = $('#idTreno').val();
	            var data = 'idTreno='
	                    + encodeURIComponent(idTreno);
	            reset();
	            $.ajax({
	                url : $("#cercaForm").attr("action"),
	                data : data,
	                type : "GET",
	                contentType: "application/json",
	                dataType: 'JSON',
	 
	                success : function(response) {
	                	
	                	if(response.idTreno !== undefined){
	                	
	                	$('#risultato').html('INFORMAZIONI SUL TRENO '+response.idTreno+
	                			'<ul>'+
	                			'<li class="listaInfo"> ID TRENO: '+response.idTreno+'</li>'+
	                			'<li class="listaInfo"> COMPOSIZIONE: '+response.sigla+'</li>'+
								'<li class="listaInfo"> STATO: '+response.stato+'</li>'+
								'<li class="listaInfo"> NOME: '+response.nome+'</li>'+
								'<li class="listaInfo"> <a class="nav-link" href="../user/viewVagoni">INFO VAGONI</a></li>'+
								'<ul>');

	                	}
	                	else {
                			$('#risultato').html('ERRORE '+response.errore);
                			
                		}
	                },
	                error : function(xhr, status, error) {
	                	switch(xhr.status){
	                		case 500:
	                			$('#risultato').html('ERROR 500: puoi inserire solo numeri nel form');
	                		break;
	                		case 404:
	                			$('#risultato').html('ERROR 404: pagina cercata non trovata');
	                		break;
	                		default:
	                			$('#risultato').html('Connessione non riuscita con il database');
	                		break;
	                	}
	                	
	                }
	            });
	            return false;
	        });
	    });
	
	function reset(){
		document.getElementById('idTreno').value = '';
	}
	
	</script>

</footer>

</html>
