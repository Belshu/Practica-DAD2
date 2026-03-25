<%@ page import="edu.ucam.config.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INICIAR SESION</title>
</head>
<body>
<form action="LoginServlet" method="POST">
	<input type="text" name="<%= Parameters.USERNAME %>" value="admin">
	<input type="text" name="<%= Parameters.PASSWORD %>" value="admin">
	<input type="submit">
</form>

HACER FORMULARIO PARA EL LOGIN:
	- form: action="request.getContextPath() + /LoginServlet"
	- input type "text" name="Parameters.USERNAME"
	- input type "text" name="Parameters.PASSWORD"
	etc 
	(Hay que poner para que el usuario seleccione entre admin y alumno, qué tipo de usuario es)
</body>
</html>