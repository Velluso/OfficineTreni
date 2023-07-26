<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
    <h1>Home Page</h1>
    <p>
         <h1>Benvenuto, ${username}!</h1>
    <p>Ruoli: ${authorities}</p>
    <p> Budget: ${budget}</p>
    
    </p>

    <!-- Form di logout -->
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>

   
</body>
</html>