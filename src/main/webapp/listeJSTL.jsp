<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!-- On definit la configuration d'acces a  la base -->
<sql:setDataSource 
	driver="org.apache.derby.jdbc.ClientDriver"
	url="jdbc:derby://localhost:1527/sample"
	user="app" password="app"
/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Liste JSTL</title>
	</head>
	<body>
		<!-- On definit une requete SQL -->
		<sql:query var = "customer" >
			SELECT * FROM CUSTOMER  WHERE CUSTOMER_ID = ?
			<%-- La valeur du paramètre  SQL vient de la requête HTTP --%>
			<sql:param value="${param.customerID}" />
		</sql:query>		
		<sql:query var = "orders" >
			SELECT * FROM PURCHASE_ORDER O 
			INNER JOIN PRODUCT P ON O.PRODUCT_ID = P.PRODUCT_ID 
			WHERE CUSTOMER_ID = ?
			<sql:param value="${param.customerID}" />
		</sql:query>		
		<h1>Les bons de commande</h1>
		<!-- 1° enregistrement ( rows[0] ) , champ NAME -->
		Client : ${customer.rows[0].NAME}
		<table border="1">
			<!-- Les en-tetes de colonnes -->
			<tr><th>Numero</th><th>Produit</th><th>Quantite</th></tr>
			<!-- On parcourt les enregistrements (rows) de la requête "orders" -->
			<c:forEach var="record" items="${orders.rows}">
				<!-- On met une ligne dans la  table -->
				<tr><td>${record.ORDER_NUM}</td><td>${record.DESCRIPTION}</td><td>${record.QUANTITY}</td></tr>
			</c:forEach>		
		</table>

		<br>
		<%-- Equivalent de request.getContextPath() en java --%>
		<a href="${pageContext.request.contextPath}">Retour au menu</a><br>
		
	</body>
</html>
