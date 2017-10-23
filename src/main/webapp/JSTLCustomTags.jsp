<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Import des "tag libraries" de JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSTL Custom Tags</title>
	</head>
	<body>
		<%-- variables utilisées dans la page --%>
		<%-- Normalement, ces variables devraient être définies par une servlet --%>
		<c:set var="today" value="<%=new java.util.Date()%>" />
		<c:set var="balance" value="120000.2309" />

		<h1>JSTL Custom Tags</h1>
                <a href=".">Retour au menu</a><br>

		<c:if test="${empty param.score}">
			Le paramètre "score" est vide (if)<hr>
		</c:if>

		<%-- On suppose que la variable "score" est numérique, que se passe t'il dans le cas contraire ? --%>	
		<c:choose>
			<c:when test="${empty param.score}">
				Le paramètre "score" est vide (when)				
			</c:when>
			<c:when test="${param.score < 50}">
				Score sous la moyenne : ${param.score}				
			</c:when>
			<c:when test="${param.score < 60}">
				Encore un effort, score : ${param.score}				
			</c:when>
			<c:otherwise>
				Bravo, joli score : ${param.score}							
			</c:otherwise>
		</c:choose>
		<hr>

		<%-- Boucle "for" --%>
		<c:forEach var="size" begin="0" end="8" step="2">
			<font size="${size}">Police taille ${size}</font><br>
		</c:forEach>
		<hr>

		<%-- Itération sur une liste --%>
		<c:forEach var="prenom" items="Lia, Manon, Aurore, Stella" varStatus="status">
			Prénom n° ${status.count} = ${prenom}, first : ${status.first}, last : ${status.last}<br>
		</c:forEach>

		<%-- Exemples de formatage de valeurs --%>
		<h3>Number Format:</h3>
		<p>Formatted Number (1): <fmt:formatNumber value="${balance}" type="currency"/></p>
		<p>Formatted Number (2): <fmt:formatNumber type="number" 
				  maxIntegerDigits="3" value="${balance}" /></p>
		<p>Formatted Number (3): <fmt:formatNumber type="number" 
				  maxFractionDigits="3" value="${balance}" /></p>
		<p>Formatted Number (4): <fmt:formatNumber type="number" 
				  groupingUsed="false" value="${balance}" /></p>
		<p>Formatted Number (5): <fmt:formatNumber type="percent" 
				  maxIntegerDigits="3" value="${balance}" /></p>
		<p>Formatted Number (6): <fmt:formatNumber type="percent" 
				  minFractionDigits="10" value="${balance}" /></p>
		<p>Formatted Number (7): <fmt:formatNumber type="percent" 
				  maxIntegerDigits="3" value="${balance}" /></p>
		<p>Formatted Number (8): <fmt:formatNumber type="number" 
				  pattern="###.###E0" value="${balance}" /></p>
		<p>Currency in USA :
			<fmt:setLocale value="en_US"/>
			<fmt:formatNumber value="${balance}" type="currency"/></p>

		<h1>&lt;fmt:formatDate&gt; Demo</h1>
		<p>Time:  <fmt:formatDate type="time" value="${today}" /> </p>
		<p>Date:  <fmt:formatDate type="date" value="${today}" /> </p>
		<p>Date & Time:  <fmt:formatDate type="both" value="${today}" /> </p>
		<p>Date & Time Short: <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${today}" /></p>
		<p>Date & Time Medium: <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${today}" />		</p>
		<p>Date & Time Long: <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${today}" /></p>
		<p>Date (yyyy-MM-dd): <fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />	</p>		
	</body>
</html>
