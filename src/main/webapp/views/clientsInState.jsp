<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Clients in ${selectedState}</title>
	</head>
	<body>
		<form>
			<select name='state' onchange='this.form.submit()'>
				<c:forEach var="state" items="${states}">
					<option value='${state}'
						<c:if test="${state eq selectedState}">
							selected
						</c:if>	
					>${state}</option>
				</c:forEach>
			</select>
		<input type='submit'>
	</form>
	<table border=2>
		<tr> <th>Id</th> <th>Name</th> <th>Address</th> </tr>
		<c:forEach var="customer" items="${customers}">
			<tr> <td>${customer.customerId}</td> <td>${customer.name}</td> <td>${customer.addressLine1}</td> </tr>
		</c:forEach>
	</table>
	<hr>
	<a href='${pageContext.request.contextPath}'>Retour au menu</a><br>
</html>
