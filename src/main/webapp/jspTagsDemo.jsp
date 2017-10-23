<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Test</title>
	</head>
	<body>
		<a href=".">Retour au menu</a><br>
		<c:choose>
			<%-- Si le paramètre yourname est présent, on l'affiche --%>
			<c:when test="${not empty param.yourname }">
				<h1>Hello ${param.yourname} !</h1>
			</c:when>
			<%-- Sinon on montre un forulaire de saisie pour yourname --%>
			<c:otherwise>
				<div>
					<form>
						<div>
							Please enter your name : <input name="yourname">
							<input type="submit">
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- Afficher tous les en-têtes de la requête HTTP (header est une Map)-->
		<!-- Dans l'itération on a accès à la clé et la valeur -->
		<h2>En-têtes de la requête HTTP</h2>
		<ul>
			<%-- header : une variable prédéfinie représentant les en-têtes de la requête --%>
			<c:forEach var="entry" items="${header}">
				<li>L'en-tête "${entry.key}" vaut "${entry.value}"</li>
			</c:forEach>
		</ul>

		<h2>Idem, comme une table</h2>
		<table border="1">
			<c:forEach var="entry" varStatus="status" items="${header}">
				<c:if test="${status.first}"> <!-- Si on est sur le 1° élément de l'itération -->
					<!-- On met l'en-tête de la table -->
					<tr><th>index</th><th>Key</th><th>Value</th></tr>
				</c:if>
				<%-- Pour chaque element on met une ligne dans la table HTML --%>
				<tr><td>${status.index}</td><td>${entry.key}</td><td>${entry.value}</td></tr>
			</c:forEach>
		</table>

		<h2>Liste des paramètres reçus</h2>
		<ul>
			<c:forEach var="entry" items="${param}">
				<li>Le paramètre "${entry.key}" vaut "${entry.value}".</li>
			</c:forEach>
		</ul>

		<h2>Liste des cookies reçus</h2>
		<ul>
			<c:forEach var="entry" items="${cookie}">
				<li>Le cookie "${entry.key}" vaut "${entry.value}".</li>
			</c:forEach>
		</ul>

		<h2>Liste des infos de requête</h2>
		<ul>
			<c:forEach var="entry" items="${requestScope}">
				<li>"${entry.key}" vaut "${entry.value}".</li>
			</c:forEach>
		</ul>
	</body>
</html>