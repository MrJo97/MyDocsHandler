<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/css/NewCustomerProfileStyle.css" var="css" />
<!--<spring:url value="/resources/js/checkSecurityLevel.js" var="csl" />-->
<spring:url value="/resources/js/formRegistrationCustomerValidation.js" var="csl" />
<spring:url value="/resources/js/checkInsertChars.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<link href="${css}" rel="stylesheet" />
<script src="${csl}" type="module" ></script>
<script src="${cic}" ></script>
<script src="${jquery}" ></script>
<meta charset="UTF-8">
<title>Crea profilo committente</title>

<!-- style2.css 
<style>
#container1
		{
		background-color: rgba(0,0,255,0.3);
		position:absolute;
		width: 25%;
		height:60vh;
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
</style>-->


</head>
<body>

<div id="container1">
<div id="container2">
<p>Se sei già registrato clicca <a href="/MyDocsHandler/goToLoginProfileForm.html">qui</a>.</p>
<form action="/MyDocsHandler/checkExistingCustomer" method="post">
<table id="table">
<tr><td>Username*: </td><td><input type="text" name="username" id="username" value="${customer.username}"/></td></tr>
<tr><td>Password*: </td><td><input type="password" id="password" name="password" value="${customer.password}"/></tr>
<tr><td>Nome*: </td><td><input type="text" name="nome" id="name" value="${customer.nome}"/></td></tr>
<tr><td>Cognome*: </td><td><input type="text" name="cognome" id="surname" value="${customer.cognome}"/></td></tr>
<tr><td>CF*: </td><td><input type="text" name="cf" id="cf" value="${customer.cf}"/></td></tr>
<tr><td>Email*: </td><td><input type="text" name="email" id="email" value="${customer.email}"/></td></tr>
<tr><td>Telefono*: </td><td><input type="text" name="telefono" id="tel" value="${customer.telefono}"/></td></tr>
<tr><td colspan="2"><input type="submit" id="registration" value="Crea Profilo" disabled/></td></tr>

</table>
<p style="color:red;">${msg}</p>
</form>

<div id="msg1"></div>
</div>
</div>
<!-- style="overflow: auto;float:right;width:25%;height:60vh;top:20%;left:37.5%;"
 	 style="position:absolute;width:80%;left:10%;"-->
<div id="container3">
<div id="container4">
<p><br/></p>
<table id="table1">
<tr><td id="msgUsername"></td></tr>
<tr><td id="msgPassword"></td></tr>
<tr><td id="msgName"></td></tr>
<tr><td id="msgSurname"></td></tr>
<tr><td id="msgCf"></td></tr>
<tr><td id="msgEmail"></td></tr>
<tr><td id="msgTel"></td></tr>
</table>
<p><br/></p>
</div>
</div>

</body>
</html>