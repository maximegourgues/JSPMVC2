<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Show Client MVC Style</title>
	</head>
	<body>
		<h1>Display Client</h1>
		<c:choose>
			<%-- On n'a pas trouvé le client --%>
			<c:when test="${empty customer}">
				Client inconnu.			
			</c:when>
			<c:otherwise> <%-- On a trouvé --%>
				Customer n° ${customer.customerId} <br> name: ${customer.name}<br> address: ${customer.addressLine1}        
			</c:otherwise>
		</c:choose>
		<br>
		<%-- Equivalent de request.getContextPath() en java --%>
		<a href='${pageContext.request.contextPath}'>Retour au menu</a><br>
	</body>
</html>
