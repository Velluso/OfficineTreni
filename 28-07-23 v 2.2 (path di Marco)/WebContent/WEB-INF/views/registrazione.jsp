<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/registrazione.css"> -->

</head>
<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card rounded">
          <div class="card-body">
            <h1 class="card-title" style="text-align:center;">Registrazione</h1>
             <form action="/springAutenticazione/public/registrazioneData" method="post">
              <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" required placeholder="Mario"  pattern="[a-zA-Z0-9]+" title="il nome utente deve contenere solo caratteri alfanumerici">
              </div>
              <div class="form-group">
                <label for="cognome">Cognome:</label>
                <input type="text" class="form-control" id="cognome" name="cognome" required placeholder="Rossi" pattern="[a-zA-Z0-9]+" title="il nome utente deve contenere solo caratteri alfanumerici">
              </div>
              <div class="form-group">
    			<label for="email">Email:</label>
    			<input type="email" class="form-control" id="email" name="email" required placeholder="mariorossi@gmail.com" pattern="[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" title="Inserisci un indirizzo email valido">
			 </div>
              <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required placeholder="rossimario71" pattern="[a-zA-Z0-9]+" title="L'username deve contenere solo caratteri alfanumerici">
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Inserisci la tua password" 
                pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$" 
                title="La password deve essere di almeno 6 caratteri e contenere almeno una lettera, un numero e un carattere speciale">
              </div>
              <button type="submit" class="btn btn-primary" style="margin-top: 10px">Registrati</button>
            </form>
            <p class="mt-3">Hai già un account? <a href="../public/login">Esegui l'accesso</a></p>
            <p class="mt-3">Torna alla <a href="../public/home">Home</a></p>
            
            
            <c:if test="${not empty errorMessage}">
              <p class="mt-3" style="color: red;">${errorMessage}</p>
              
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>