<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bonjour</title>
	</head>
	<body>
		<!-- La variable "message" est initialisée dans la servlet -->
		<h1>Bonjour ${message}</h1>
		<br>
		<a href="bonjour">Recommencer</a><br>
		<%-- Equivalent de request.getContextPath() en java --%>
		<a href="${pageContext.request.contextPath}">Retour au menu</a><br>
	</body>
</html>
