<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
    <h2>Iniciar sesión</h2>

    Usuario: <input type="text" name="username" required><br>
    Contraseña: <input type="password" name="password" required><br>

    <input type="submit" value="Entrar">

</form>
</body>
</html>