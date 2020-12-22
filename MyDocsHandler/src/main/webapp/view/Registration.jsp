<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/css/RegistrationStyle.css" var="css" />
<!--<spring:url value="/resources/js/checkSecurityLevel.js" var="csl" />-->
<spring:url value="/resources/js/formRegistrationUserValidation.js" var="csl" />
<spring:url value="/resources/js/checkInsertChars.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<link href="${css}" rel="stylesheet" />
<script src="${csl}" type="module" ></script>
<script src="${cic}" ></script>
<script src="${jquery}" ></script>
<meta charset="UTF-8">
<title>Registration</title>
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
<p>Se sei già registrato clicca <a href="/MyDocsHandler/goToLoginForm.html">qui</a>.</p>
<form action="/MyDocsHandler/checkCredentials" method="post">
<table id="table" >
<tr><td>Email: </td><td><input type="text" name="email" id="email" value="${user.email}"/></td></tr>
<tr><td>Password: </td><td><input type="password" id="password" name="password" value="${user.password}" /></td></tr>
<tr><td colspan="2"><input type="submit" id="registration" value="Registrati" disabled/></td></tr>
<!--onkeypress="return checkChars(event);"-->
</table>
<p style="color:red;">${msg}</p>
</form>

<!--<div id="result2"> </div>-->
<div id="msgEmail"></div>
<div id="msgPassword"></div>
<div id="msg1"></div>
</div>
</div>


</body>
</html>