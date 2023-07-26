<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>COSTRUZIONE TRENI</title>
	
	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>
	
</head>
<body>
	
	<div id="titolo" class="headBar">
            COSTRUZIONE TRENI
    </div>
    
    <div class="menu">
    	<ul>
    		<li>
    			<a>CREA TRENO</a>
    		</li>
    		<li>
    			<a href="viewTreno">VISUALIZZA TRENO</a>
    		</li>
    		<li>
    			<a>I MIEI ORDINI</a>
    		</li>
    	</ul>
    </div>

	<div class="inputUtente">

		<form action="costruisciTreno" class="formSigla" method="post">
			
			<input class="enumMarchio" list="marchi" name="marchio" placeholder="Seleziona">
			
				<datalist id="marchi">
				  <option value="Frecciarossa">
				  <option value="Italo">
				  <option value="TreNord">
				</datalist>
	
			<input type="text" class="inputSigla" name="siglaTreno" placeholder="Inserisci la sigla del treno">
			<input type="submit" class="creaButton" value="CREA">
		</form>
		
		<!--<form action="visualizzaTreno" method="get">
			<input type="text" name="idTreno" placeholder="Inserisci id treno">
			<input type="submit" class="creaButton" value="CREA">
		</form>
	-->
	</div>
	
	<div id="rislutato" class="result">
	
		
	
	</div>
	
</body>
</html>