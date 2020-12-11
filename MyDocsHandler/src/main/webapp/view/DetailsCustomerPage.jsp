<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="webapp.model.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<spring:url value="/resources/css3/DetailsAndEditPagesStyle.css" var="css" />
<link href="${css}" rel="stylesheet" />

<title>Insert title here</title>
</head>
<body>
<%@ include file="HomePage.jsp"%>

<% Committente customer = (Committente) request.getAttribute("customer"); %>
<div id="cssStyle">
<table>
<tr><td colspan="2" style="color:red">Dati Committente</td></tr>
<tr><td>Nome: </td><td>${customer.nome}</td></tr>
<tr><td>Cognome: </td><td>${customer.cognome}</td></tr>
<tr><td>CF: </td><td>${customer.cf}</td></tr>
<tr><td>Telefono: </td><td>${customer.telefono}</td></tr>
<tr><td>Email: </td><td>${customer.email}</td></tr>
</table>
</div>
</body>
</html>