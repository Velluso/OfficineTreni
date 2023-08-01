<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>VISUALIZZA VAGONI</title>

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
            LISTA VAGONI   
    </div>
	
	<div id="listaVagoni" class="ordiniResult">
		STO CARICANDO I DATI
	</div>
	    
	    
	    

</body>

	<script>
	
	
	    $.ajax({ 
	        type: 'GET', 
	        url: '../user/viewVagoni/findVagoni', 
	        data : '',
	        dataType: 'JSON',
            contentType: "application/json",
            success : function(response) {
            	if(response[0].id !== undefined){
            		var table = $('<table>').addClass('table table-striped table-hover table-light');
                    var thead = $('<thead>').addClass('thead-dark');
                    var tr = $('<tr>');
                    tr.append('<th>ID TRENO</th>');
                    tr.append('<th>ID VAGONE</th>');
                    tr.append('<th>PESO</th>');
                    tr.append('<th>LUNGHEZZA</th>');
                    tr.append('<th>TIPO</th>');
                    tr.append('<th>COMPAGNIA</th>');
                    tr.append('<th>POSIZIONE</th>');
                    thead.append(tr);
                    table.append(thead);
                    var tbody = $('<tbody>');
	            	for (i=0; i < response.length; i++){
	            		var row = $('<tr>');
	            		row.append('<td class="fw-bold">' + response[i].idTreno + '</td>');
                        row.append('<td class="fw-bold">' + response[i].id + '</td>');
                        row.append('<td class="fw-bold">' + response[i].peso + '</td>');
                        row.append('<td class="fw-bold">' + response[i].lunghezza + '</td>');
                        row.append('<td class="fw-bold">' + response[i].tipo + '</td>');
                        row.append('<td class="fw-bold">' + response[i].compagnia + '</td>');
                        row.append('<td class="fw-bold">' + response[i].posizione + '</td>');
                        tbody.append(row);
	            	}
	            	table.append(tbody);
                    $('#listaVagoni').html(table);
            	}
            	else {
            			$('#listaVagoni').html('ERRORE '+response.errore);
            	}
            		
            },
            error : function(xhr, status, error) {
            	alert(xhr.responseText);
            	switch(xhr.status){
	        		case 500:
	        			$('#listaVagoni').html('ERROR 500: puoi inserire solo numeri nel form');
	        		break;
	        		case 404:
	        			$('#listaVagoni').html('ERROR 404: pagina cercata non trovata');
	        		break;
	        		default:
	        			$('#listaVagoni').html('Connessione non riuscita con il database');
	        		break;
	        	}
            }
	    });
	
	</script>
	<footer class="footer">
		<div class="container">
			<p>&copy; 2023 Eugenio, Federico, Marco</p>
		</div>
	</footer>
</html>