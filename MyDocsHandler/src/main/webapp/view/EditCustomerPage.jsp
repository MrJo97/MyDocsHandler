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

<spring:url value="/resources/js/setConstraintFieldsCustomer.js" var="scf" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css3/DetailsAndEditPagesStyle.css" var="css" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script> 
<script src="${scf}" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />

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

</head>
<body>

<div id="container1">
<div id="container2">
<form action="/MyDocsHandler/editCustomer/edit${customer.idCommittente}" method="POST">
<table>
			<tr><td>Nome </td><td><input value="${customer.nome}" type="text" name="nome" id="nC" /></td><td><div style="color:red;" id="warning2"></div></td></tr>
			<tr><td>Cognome </td><td><input value="${customer.cognome}" type="text" name="cognome" id="sC" /></td><td><div style="color:red;" id="warning3"></div></td></tr>
			<tr><td>CF </td><td><input value="${customer.cf}" type="text" name="cf" id="cf" /></td><td></td></tr>
			<tr><td>Telefono </td><td><input value="${customer.telefono}" type="text" name="telefono" id="telefono" /></td><td><div style="color:red;" id="warning4"></div></td></tr>
			<tr><td>Email </td><td><input value="${customer.email}" type="text" name="email" id="email" /></td><td><div style="color:red;" id="warning5"></div></td></tr>
						
			<tr>
			<td><input type="reset" value="Reset" id="reset"/></td>
			<td><input type="submit" value="Salva Modifiche" name="save" id="SM"/></td>
			</tr>
			</table>
		</form>
		<div>${warning}</div>
</div>
</div>
</body>
</html>