<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica dati profilo</title>

<spring:url value="/resources/js/formEditCustomerValidation.js" var="scf" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css/EditCustomerProfileStyle.css" var="css" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script> 
<script src="${scf}" type="module" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />
</head>
<body>

<div id="container1">
<div id="container2">
<form action="/MyDocsHandler/editCustomer/edit${customer.idCommittente}" method="POST">
<table id="table">
			<tr><td></td><td></td></tr>
			<tr><td>Nome </td><td><input value="${customer.nome}" type="text" name="nome" id="name" /></td><td><div style="color:red;" id="warning2"></div></td></tr>
			<tr><td>Cognome </td><td><input value="${customer.cognome}" type="text" name="cognome" id="surname" /></td><td><div style="color:red;" id="warning3"></div></td></tr>
			<tr><td>CF </td><td><input value="${customer.cf}" type="text" name="cf" id="cf" /></td><td></td></tr>
			<tr><td>Telefono </td><td><input value="${customer.telefono}" type="text" name="telefono" id="tel" /></td><td><div style="color:red;" id="warning4"></div></td></tr>
			<tr><td>Email </td><td><input value="${customer.email}" type="text" name="email" id="email" /></td><td><div style="color:red;" id="warning5"></div></td></tr>
						
			<tr>
			<td><input type="reset" value="Reset" id="reset"/></td>
			<td><input type="submit" value="Salva Modifiche" name="save" id="SM" disabled/></td>
			</tr>
			</table>
		</form>
		<div style="color:red;">${warning}</div>
<div id="msg1"></div>
</div>
</div>
<div id="container3">
<div id="container4">
<table id="table1">
<tr><td></td></tr>
<tr><td id="msgName"></td></tr>
<tr><td id="msgSurname"></td></tr>
<tr><td id="msgCf"></td></tr>
<tr><td id="msgTel"></td></tr>
<tr><td id="msgEmail"></td></tr>
</table>
<p><br/></p>
</div>
</div>
</body>
</html>