<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<spring:url value="/resources/js/setConstraintFieldsCustomer.js" var="scf" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css3/DetailsAndEditPagesStyle.css" var="css" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script> 
<script src="${scf}" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />

</head>
<body>
<%@ include file="HomePage.jsp"%>

<div id="cssStyle">
<form action="/MyDocsHandlerNew/editCustomer/edit${customer.idCommittente}" method="POST">
<table>
			<tr><td>Nome </td><td><input value="${customer.nome}" type="text" name="nameCust" id="nC" /></td><td><div style="color:red;" id="warning2"></div></td></tr>
			<tr><td>Cognome </td><td><input value="${customer.cognome}" type="text" name="surname" id="sC" /></td><td><div style="color:red;" id="warning3"></div></td></tr>
			<tr><td>CF </td><td><input value="${customer.cf}" type="text" name="CF" id="cf" /></td><td></td></tr>
			<tr><td>Telefono (1)</td><td><input value="${contact.telefono}" type="text" name="tel1" id="tel1" /></td><td><div style="color:red;" id="warning4"></div></td></tr>
			<tr><td>Telefono (2)</td><td><input value="${contact1.telefono}" type="text" name="tel2" id="tel2" /></td><td></td></tr>
			<tr><td>Email (1)</td><td><input value="${contact.email}" type="text" name="email1" id="email1" /></td><td><div style="color:red;" id="warning5"></div></td></tr>
			<tr><td>Email (2)</td><td><input value="${contact1.email}" type="text" name="email2" id="email2" /></td><td></td></tr>
			
			
			
			<tr>
			<td><input type="reset" value="Reset" id="reset"/></td>
			<td><input type="submit" value="Salva Modifiche" name="save" id="SM"/></td>
			</tr>
			</table>
		</form>
		<div>${warning}</div>
</div>
</body>
</html>