<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo committente</title>
<spring:url value="/resources/css/CustomerProfileStyle.css" var="css" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/js/jsHomePage.js" var="cic" />
<script src="${jquery}"></script>
<script src="${cic}"></script>
<link href="${css}" rel="stylesheet" />
<!-- 
<style>
#container1
		{
		background-color: rgba(0,0,255,0.3);
		position:absolute;
		width: 25%;
		height:30vh;
		top:20%;
		left:37.5%;
		overflow: auto;
		border-radius: 20px;
		}
#container2
		{
		position:absolute;
		width:80%;
		left:10%;
		}
</style>
-->
</head>
<body>
<div id="container1">
<div id="container2">
<form action="/MyDocsHandler/editCustomer/catchIdCustomer${customer.idCommittente}" method="post">
<table>
<tr><td colspan="2" style="color:red">Dati Committente</td></tr>
<tr><td>Nome: </td><td>${customer.nome}</td></tr>
<tr><td>Cognome: </td><td>${customer.cognome}</td></tr>
<tr><td>CF: </td><td>${customer.cf}</td></tr>
<tr><td>Telefono: </td><td>${customer.telefono}</td></tr>
<tr><td>Email: </td><td>${customer.email}</td></tr>
<tr><td><input type="submit" value="Modifica dati" /></td><td><input type="button" value="Logout" onclick='logoutRedirect("customer")'/></td></tr>
<!-- per il bottone di logout faremo una funzione JavaScript per l'indirizzamento (potremmo farlo anche per il bottone di logout della homepage) -->
</table>
</form>
</div>
</div>
</body>
</html>