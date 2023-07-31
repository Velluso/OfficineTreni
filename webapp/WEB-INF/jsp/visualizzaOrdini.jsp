<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini Utente</title>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>

</head>
<body id="ordiniBody">

 
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
            LISTA ORDINI   
    </div>
	
	<div id="listaOrdini" class="ordiniResult">
		STO CARICANDO I DATI
	</div>
	    

</body>


<!-- il footer è da togliere, il </body> è da spostare in basso, ho aspettato a spostarlo per vedere se non causasse problemi a voi -->
<footer>


 <script>
            $.ajax({
                type: 'GET',
                url: '../user/cercaOrdini/findOrdini',
                data: 'username=+${username}',
                dataType: 'JSON',
                contentType: "application/json",

                success: function (response) {
                    if (response[0].idTreno !== undefined) {
                        var table = $('<table>').addClass('table table-striped table-hover table-light');
                        var thead = $('<thead>').addClass('thead-dark');
                        var tr = $('<tr>');
                        tr.append('<th>ID ORDINE</th>');
                        tr.append('<th>ID TRENO</th>');
                        tr.append('<th>USERNAME</th>');
                        tr.append('<th>CREAZIONE</th>');
                        tr.append('<th>ULTIMAZIONE</th>');
                        tr.append('<th>STATO</th>');
                        thead.append(tr);
                        table.append(thead);
                        var tbody = $('<tbody>');
                        for (i = 0; i < response.length; i++) {
                            var row = $('<tr>');
                            row.append('<td class="fw-bold">' + response[i].idOrdine + '</td>');
                            row.append('<td class="fw-bold">' + response[i].idTreno + '</td>');
                            row.append('<td class="fw-bold">' + response[i].username + '</td>');
                            row.append('<td class="fw-bold">' + response[i].dataCreazione + '</td>');
                            row.append('<td class="fw-bold">' + response[i].dataConclusione + '</td>');
                            row.append('<td class="fw-bold">' + response[i].stato + '</td>');
                            tbody.append(row);
                        }
                        table.append(tbody);
                        $('#listaOrdini').text('');
                        $('#listaOrdini').append(table);
                    } else {
                        $('#listaOrdini').html('ERRORE ' + response.errore);
                    }
                },
                error: function (xhr, status, error) {
                	$('#listaOrdini').html('ERRORE');
	        	}
        	});
        </script>



<!--  
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
	
	-->

</footer>

</html>