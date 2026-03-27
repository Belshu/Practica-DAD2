<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.config.*, edu.ucam.domain.Titulation" %>
<%@ page import="java.util.Hashtable" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Titulacion</title>
</head>
<body>

<%
    System.out.println("-------- modify.jsp -> Nombre esperado del parámetro: " + Parameters.ID_TIT + "<br>");
    System.out.println("-------- modify.jsp -> Valor recibido: " + request.getParameter(Parameters.ID_TIT) + "<br>");
%>

<%
	String id = request.getParameter(Parameters.ID_TIT);
	Hashtable<String, Titulation> titu = (Hashtable<String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);

	Titulation t = titu.get(id);
%>

<h1>Modificar titulación</h1>

<form action="<%= request.getContextPath() %>/Control" method="post">

	<!-- Acción -->
	<input type="hidden" name="<%= Parameters.ACTION_ID %>" value="<%= ActionID.MODIFY %>">
	
	<!-- ID (solo lectura) -->
	ID:
	<input type="hidden" name="<%= Parameters.ID_TIT %>" value="<%= t.getId() %>">
	<input type="text" name="<%= Parameters.ID_TIT %>" value="<%= t.getId() %>" disabled>
	
	<br><br>

	<!-- Nombre editable -->
	Nombre:
	<input type="text" name="<%= Parameters.NAME_TIT %>" value="<%= t.getNombre() %>" required>

	<br><br>
	

	<input type="submit" value="Guardar cambios">
</form>

<% 
	String error = (String) request.getAttribute(edu.ucam.config.Attributes.ERROR_MSG);
        
    if(error != null) {
        %><p style="color:red;">Error: <%= error %></p><%
    }
%>
	
</body>
</html>