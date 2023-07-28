<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>

</head>
<body id="ordiniBody">

	<div id="titolo" class="headBar">
            LISTA ORDINI   
    </div>
    
    <div class="navbar">
	    	<div id="greeting" class="greeting">${nomeUtente }</div>
	    	<form action="home" method="get">
	    	<input type="submit" class="log" value="HOME">
	    	</form>
	</div>
	
	<div id="listaOrdini" class="ordiniResult">
	</div>
	    

</body>

<footer>

	<script>
	
	
	    $.ajax({ 
	        type: 'GET', 
	        url: 'findOrdini', 
	        data : 'username=federico.mascali',
	        dataType: 'JSON',
            contentType: "application/json",
	        
            success : function(response) {
            	if(response[0].idTreno !== undefined){
	            	$('#listaOrdini').html('INFORMAZIONI SUGLI ORDINI DI username'+
	            			 '<tr>'+
	            				'<th>ID ORDINE</th>'+
	            				'<th>ID TRENO</th>'+
	            				'<th>USERNAME</th>'+
	            				'<th>CREAZIONE</th>'+
	            				'<th>ULTIMAZIONE</th>'+
	            				'<th>STATO</th>'+
	            			'</tr>');
	            	for (i=0; i < response.length; i++)
	                {
	            	$('#listaOrdini').append(
	            		'<tr>'+
	            			'<td  class="listaInfo"> '+response[i].idOrdine+'</td>'+
	            			'<td  class="listaInfo"> '+response[i].idTreno+'</td>'+
	            			'<td  class="listaInfo"> '+response[i].username+'</td>'+
	            			'<td  class="listaInfo"> '+response[i].dataCreazione+'</td>'+
	            			'<td  class="listaInfo"> '+response[i].dataConclusione+'</td>'+
	            			'<td  class="listaInfo"> '+response[i].stato+'</td>'+
	            			
	            		'</tr>');
	            	}
            	}
            	else {
            			$('#listaOrdini').html('ERRORE '+response.errore);
            	}
            		
            },
            error : function(xhr, status, error) {
            	$('#listaOrdini').html('NESSUN ORDINE DA VISUALIZZARE');
            }
	    });
	
	</script>

</footer>

</html>