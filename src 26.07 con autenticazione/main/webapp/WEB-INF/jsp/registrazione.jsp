<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>
    <h1>Pagina Registrazione</h1>
    
    
    <form action="../public/registrazioneData" method="post">
    	<label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <br>
        <label for="cognome">cognome:</label>
        <input type="text" id="cognome" name="cognome" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Register">
    </form>
    
    
   <c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>
    
    
</body>
</html>