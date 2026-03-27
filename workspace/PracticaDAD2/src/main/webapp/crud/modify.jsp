<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.config.*, edu.ucam.domain.Titulation" %>
<%@ page import="java.util.Hashtable;" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Titulacion</title>
</head>
<body>
	
<%
	String id = request.getParameter(Parameters.ID_TIT);
	Hashtable<String, Titulation> titu =
		(Hashtable<String, Titulation>) application.getAttribute(Attributes.TITULACIONES);

	Titulation t = titu.get(id);
%>

<h1>Modificar titulación</h1>

<form action="Control" method="post">

	<!-- Acción -->
	<input type="hidden" name="<%= Parameters.ACTION_ID %>" value="<%= ActionID.MODIFY %>">
	
	<!-- ID (solo lectura) -->
	ID:
	<input type="text" value="<%= t.getId() %>" disabled>
	<input type="hidden" name="<%= Parameters.ID_TIT %>" value="<%= t.getId() %>">
	
	<br><br>

	<!-- Nombre editable -->
	Nombre:
	<input type="text" name="<%= Parameters.NAME_TIT %>" value="<%= t.getNombre() %>" required>

	<br><br>
	

	<input type="submit" value="Guardar cambios">
</form>
	
</body>
</html>