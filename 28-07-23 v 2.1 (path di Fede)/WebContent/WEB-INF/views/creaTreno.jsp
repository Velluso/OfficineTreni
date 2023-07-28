<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	
	<meta charset="ISO-8859-1">
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
                            <a class="nav-link" href="/springAutenticazione/admin/administrator">Pannello Admin</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <!-- Form di logout -->
                        <form class="nav-link" action="/springAutenticazione/logout" method="post">
                            <button type="submit" class="btn btn-primary">Logout</button>
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
    
    
    <div class="container">
		<div class="inputUtente">
	
			<form id="costruzioneForm" action="../user/viewCostruzione/creaTreno" class="formSigla" method="post">
			
				<div class="formLabel">COMPAGNIA</div>	
						
				<input id="marchio" class="enumMarchio" list="marchi" name="marchio" placeholder="Seleziona" required>
				
					<datalist id="marchi">
					  <option value="Freccia Rossa">
					  <option value="Italo">
					  <option value="TreNord">
					</datalist>
					
				<div class="formLabel">COMPOSIZIONE DEL TRENO</div>		
				
				<input type="text" id="siglaTreno" class="inputSigla" name="siglaTreno" placeholder="Inserisci la sigla del treno" required>
				
				<div class="formLabel">NOME DEL TRENO</div>	
				
				<input type="text" id="nomeTreno" class="inputSigla" name="nomeTreno" placeholder="Inserisci il nome del treno" required>
				
				<input type="reset" class="creaButton" value="RESET" onclick="reset()">
				<input type="submit" class="creaButton" value="CREA">
			</form>
			
		</div>
	
		<div id="risultato" class="result">
		
		</div>
		
	</div>

</body>

<footer class="footer">

	<script>
	
	$(document).ready(function() {
	    $('#costruzioneForm').submit(
	        function(event) {
	        	$('#risultato').html('STIAMO ESEGUENDO LA TUA RICHIESTA');
	            var siglaTreno = $('#siglaTreno').val();
	            var marchio = $('#marchio').val();
	            var nomeTreno = $('#nomeTreno').val();
	            var data = 'marchio='
	                    + encodeURIComponent(marchio)+
	                    '&siglaTreno='+encodeURIComponent(siglaTreno)+
	                    '&nomeTreno='+encodeURIComponent(nomeTreno);
	            reset();
		            $.ajax({
		                url : $("#costruzioneForm").attr("action"),
		                data : data,
		                type : "GET",
		                contentType: "application/json",
		                dataType: 'JSON',
		 
		                success : function(response) {
		                	
		                		if(response.idTreno !== undefined){
		                	
			                	$('#risultato').html('CREATO IL TRENO '+response.idTreno+
			                			'<ul>'+
			                			'<li class="listaInfo"> ID TRENO: '+response.idTreno+'</li>'+
			                			'<li class="listaInfo"> COMPAGNIA: '+response.compagnia+'</li>'+
			                			'<li class="listaInfo"> COMPOSIZIONE: '+response.sigla+'</li>'+
										'<li class="listaInfo"> STATO: '+response.stato+'</li>'+
										'<li class="listaInfo"> NOME: '+response.nome+'</li>'+
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
		document.getElementById('siglaTreno').value = '';
		document.getElementById('marchio').value = '';
		document.getElementById('nomeTreno').value = '';
	}
	
	</script>

	<div class="container">
    	<p>&copy; 2023 Eugenio, Federico, Marco</p>
	</div>

</footer>

</html>