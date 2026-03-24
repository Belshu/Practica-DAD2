<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    User u = (User) session.getAttribute("LOGGED_USER");
%>

<h1>Bienvenido, <%= u.getUsername() %></h1>

<p>Tu rol es: <%= u.getRol() %></p>

<a href="../logout">Cerrar sesión</a>
</body>
</html>