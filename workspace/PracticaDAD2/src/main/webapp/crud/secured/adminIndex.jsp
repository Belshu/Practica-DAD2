<%@ taglib uri="mytags" prefix="dad2" %>
<%@ page import="edu.ucam.config.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión administrador</title>
</head>
<body>
<h1>Lista de usuarios</h1>
<form action="<%= request.getContextPath() %>/crud/index.jsp">
	<input type="submit" value="Volver a opciones de usuario">
</form>

<!-- HACER FORMULARIO PARA AÑADIR NUEVOS USUARIOS -->

<dad2:listusers/>

<% 
	String error = (String) request.getAttribute(edu.ucam.config.Attributes.ERROR_MSG);
        
    if(error != null) {
        %><p style="color:red;">Error: <%= error %></p><%
    }
%>

</body>
</html>