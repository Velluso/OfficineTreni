<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>COSTRUZIONE TRENI</title>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<style type="text/css">
  		<%@include file="../css/index.css" %>
	</style>
	
</head>
<body>
	
	<div id="titolo" class="headBarHome">
            COSTRUZIONE TRENI    
    </div>
    
    <div class="navbar">
	    	<div id="greeting" class="greeting">${nomeUtente }</div>
	    	<input type="submit" class="log" value=${logButton } onclick="">
	    </div>
    
    <div class="menu">
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
	    </div>
	
</body>

</html>