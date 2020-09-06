<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<spring:url value="/resources/js/setContraintFieldsAutentication.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css3/LoginRegistrationPagesStyle.css" var="css" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${cic}" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />

<title>Insert title here</title>
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
<p>Se non sei ancora registrato clicca <a href="/MyDocsHandler/goToRegistrationForm.html">qui</a>.</p>
<form action="/MyDocsHandler/LoginForm.html" method="post">
<table id="table">
<tr><td>Email: </td><td><input type="text" name="email" id="loginEmail" value="${user.email}"/></td></tr>
<tr><td>Password: </td><td><input type="password" name="password" id="loginPassword" value="${user.password}"/></td></tr>
<tr><td colspan="2"><input type="submit" id="cmd" value="Accedi" disabled="true"/></td></tr>
</table>
<p style="color:red;">${msg}</p>
<div id="result1"></div>
<div id="result2"></div>
</form>
</div>
</div>

</body>
</html>