<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="ISO-8859-1">
	<title>COSTRUZIONE TRENI</title>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>

</head>
<body>

	<div id="titolo" class="headBar">
            CERCA TRENO    
    </div>
    
    <div class="navbar">
	    	<div id="greeting" class="greeting">${nomeUtente }</div>
	    	<form action="home" method="get">
	    	<input type="submit" class="log" value="HOME">
	    	</form>
	</div>

	<div class="inputUtente">

		<form id="cercaForm" action="findTreno" class="formSigla" method="post">
				
			<div class="formLabel">INSERISCI ID DEL TRENO DA CERCARE</div>		
			
			<input type="text" id="idTreno" class="inputSigla" name="siglaTreno" placeholder="Inserisci ID del treno">
			<input type="reset" class="creaButton" value="RESET" id="reset">
			<input type="submit" class="creaButton" value="CERCA">
		</form>
		
	</div>
	
	<div id="risultato" class="resultViewTreno">
	</div>
	
	<div id="risultato" class="viewVagoni">
	</div>

</body>

<footer>

	<script>
	
	$(document).ready(function() {
	    $('#cercaForm').submit(
	        function(event) {
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
								'<ul>');
	                	}
	                	else {
                			$('#risultato').html('ERRORE '+response.errore);
                		}
	                },
	                error : function(xhr, status, error) {
	                	$('#risultato').html('Connessione non riuscita con il database');
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