<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Hello World!</h1>
                Nous sommes le : <i><%= new Date() %></i><br>
		<% for (int i =0; i < 10; i++) { %>
                Hello (<%= i %>) ! <br>
		<% } %>
		<hr>
		<%-- Equivalent de request.getContextPath() en java --%>
		<a href="${pageContext.request.contextPath}">Retour au menu</a>
	</body>
</html>
