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
<<<<<<< HEAD
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
=======
<form action="LoginServlet" method="post">
	
    <h2>Iniciar sesión</h2>

    Usuario:
    <input type="text" name="<%= edu.ucam.config.Parameters.USERNAME %>" required><br>

    Contraseña:
    <input type="password" name="<%= edu.ucam.config.Parameters.PASSWORD %>" required><br>

    <input type="submit" value="Entrar">
	<br>
	<br>
	Error:
    <p style="color:red;">
        <%= request.getAttribute(edu.ucam.config.Attributes.ERROR_MSG) %>
    </p>
    <p>
    ¿No tienes cuenta?
    <a href="<%= request.getContextPath() %>/registry.jsp">Regístrate aquí</a>
	</p>
    
</form>
>>>>>>> main
</body>
</html>