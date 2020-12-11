<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/css3/LoginRegistrationPagesStyle.css" var="css" />
<spring:url value="/resources/js/checkSecurityLevel.js" var="csl" />
<spring:url value="/resources/js/checkInsertChars.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<link href="${css}" rel="stylesheet" />
<script src="${csl}" ></script>
<script src="${cic}" ></script>
<script src="${jquery}" ></script>
<meta charset="UTF-8">
<title>Registration</title>

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
<p>Se sei già registrato clicca <a href="/MyDocsHandlerNew/goToLoginForm.html">qui</a>.</p>
<form action="/MyDocsHandler/checkCredentials" method="post">
<table id="table">
<tr><td>Email: </td><td><input type="text" name="email" id="myInput1" value="${user.email}"/></td></tr>
<tr><td>Password: </td><td><input type="password" id="myInput" name="password" value="${user.password}" onkeypress="return checkChars(event);"/></td></tr>
<tr><td colspan="2"><input type="submit" id="cmd1" value="Registrati" disabled/></td></tr>
</table>
<p style="color:red;">${msg}</p>
</form>

<div id="result2"></div>
<div id="result"></div>
<div id="result1"></div>
</div>
</div>


</body>
</html>