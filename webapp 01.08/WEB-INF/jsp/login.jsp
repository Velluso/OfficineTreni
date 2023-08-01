<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Login</title>
<!-- Add the Bootstrap CSS link here -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/login.css">
</head>


<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card rounded">
          <div class="card-body">
            <h1 class="card-title" style="text-align:center;">Login</h1>
            <form action="../public/loginData" method="post">
              <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required placeholder="Inserisci il tuo nome utente"
                 pattern="[a-zA-Z0-9]+" 
                 title="L'username deve contenere solo caratteri alfanumerici">
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Inserisci la tua password">
               <!--   pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$" 
                title="La password deve essere di almeno 6 caratteri e contenere almeno una lettera, un numero e un carattere speciale" -->  
                
                 
              </div>
              <button type="submit" class="btn btn-primary" style="margin-top: 10px">Login</button>
            </form>
            <p class="mt-3">Non hai ancora un account? <a href="../public/registrazione">Registrati</a></p>
            <p class="mt-3">Torna alla <a href="../public/home">Home</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>