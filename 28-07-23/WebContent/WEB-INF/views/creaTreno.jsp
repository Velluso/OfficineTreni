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

	<div id="titolo" class="headBar text-center py-10">
            COMPONI TRENO    
    </div>
    
    <div class="navbar">
	    	<div id="greeting" class="greeting">${nomeUtente }</div>
	    	<form action="../public/home" method="get">
	    	<input type="submit" class="log" value="HOME">
	    	</form>
	</div>

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

</body>

<footer>

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

</footer>

</html>