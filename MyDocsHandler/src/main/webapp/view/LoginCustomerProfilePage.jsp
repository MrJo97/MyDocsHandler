<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<spring:url value="/resources/js/formLoginCustomerValidation.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css/LoginCustomerStyle.css" var="css" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${cic}" type="module"></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />

<title>Accedi al profilo</title>
<!-- 
<style>
#container1
		{
		background-color: rgba(0,0,255,0.3);
		position:absolute;
		width: 25%;
		height:38vh;
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
<p>Se non sei ancora registrato clicca <a href="/MyDocsHandler/goToCreationForm.html">qui</a>.</p>
<form action="/MyDocsHandler/loginCustomer" method="post">
<table id="table">
<tr><td>Username: </td><td><input type="text" name="username" id="username" value="${customer.username}"/></td></tr>
<tr><td>Password: </td><td><input type="password" name="password" id="password" value="${customer.password}"/></td></tr>
<tr><td colspan="2"><input type="submit" id="login" value="Accedi" disabled/></td></tr>
</table>
<p style="color:red;">${msg}</p>
<div id="msgUsername"></div>
<div id="msgPassword"></div>
<div id="msg1"></div>
</form>
</div>
</div>

</body>
</html>