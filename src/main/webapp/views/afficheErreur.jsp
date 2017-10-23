<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Erreur</title>
	</head>
	<body>
		<h1>Erreur !</h1>
		<!-- La variable "errorMessage" est initialisée dans la servlet -->
		Erreur: ${errorMessage}
		<br>
		<%-- Equivalent de request.getContextPath() en java --%>
		<a href='${pageContext.request.contextPath}'>Retour au menu</a><br>	
	</body>
</html>
