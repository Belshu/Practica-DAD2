<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.config.*, edu.ucam.domain.User" %>
<%@ page import="java.util.Hashtable;" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar usuario</title>
</head>
<body>
<%
	String username = request.getParameter(Parameters.USERNAME);
	Hashtable<String, User> users =
		(Hashtable<String, User>) application.getAttribute(Attributes.USUARIOS);

	User u = users.get(username);
%>

<h1>Modificar usuario</h1>

<form action="Control" method="post">
	<!-- Acción -->
	<input type="hidden" name="<%= Parameters.ACTION_ID %>" value="<%= ActionID.MODIFY %>">

	<!-- Username (solo lectura) -->
	Usuario:
	<input type="text" value="<%= u.getUsername() %>" disabled>
	<input type="hidden" name="<%= Parameters.USERNAME %>" value="<%= u.getUsername() %>">

	<br><br>
	
	<!-- Contraseña editable -->
	Nueva contraseña:
	<input type="text" name="<%= Parameters.PASSWORD %>" value="<%= u.getPassword() %>" required>

	<br><br>
	
	<input type="submit" value="Guardar cambios">
</form>

</body>
</html>