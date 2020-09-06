<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.webapp.model.committente.*" %>
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
<tr><td>Nome: </td><td><% out.print(customer.getNome()); %></td></tr>
<tr><td>Cognome: </td><td><% out.print(customer.getCognome()); %></td></tr>
<tr><td>CF: </td><td><% out.print(customer.getCf()); %></td></tr>
<tr><td>Telefono(1): </td><td><% out.print(customer.getRecapiti().get(0).getTelefono()); %></td></tr>
<% if(customer.getRecapiti().get(1).getTelefono() != "")
	{out.print("<tr><td>Telefono(2): </td><td>" + customer.getRecapiti().get(1).getTelefono() + "</td></tr>");}
%>
<tr><td>Email(1): </td><td><% out.print(customer.getRecapiti().get(0).getEmail()); %></td></tr>
<% if(customer.getRecapiti().get(1).getEmail() != "")
	{out.print("<tr><td>Email(2): </td><td>" + customer.getRecapiti().get(1).getEmail() + "</td></tr>");}
%>
</table>
</div>
<br/>
<!-- <p>Per ritornare alla pagina principale clicca <a href="/MyDocsHandler/view/HomePage.jsp">qui</a>.</p>-->
</body>
</html>