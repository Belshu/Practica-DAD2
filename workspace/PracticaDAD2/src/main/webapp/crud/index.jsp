<%@ taglib uri="mytags" prefix="dad2" %>
<%@ page import="edu.ucam.config.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu principal</title>
</head>
<body>
<h1>Lista de titulaciones</h1>
<form action="<%= request.getContextPath() %>/crud/secured/adminIndex.jsp">
	<input type="submit" value="Ir a opciones de administrador">
</form>

<!-- HACER FORMULARIO PARA AÑADIR NUEVAS TITULACIONES -->


<dad2:listtit/>

<% 
	String error = (String) request.getAttribute(edu.ucam.config.Attributes.ERROR_MSG);
        
    if(error != null) {
        %><p style="color:red;">Error: <%= error %></p><%
    }
%>

</body>
</html>