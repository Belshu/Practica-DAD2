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
<dad2:listtit/>

<form action="<%= request.getContextPath() %>/crud/secured/adminIndex.jsp">
	<input type="submit" value="Ir a opciones de administrador">
</form>
</body>
</html>