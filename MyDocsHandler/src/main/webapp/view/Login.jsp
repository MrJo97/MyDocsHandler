<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link href="http://localhost:8080/MyDocsHandler/resources/css/LoginStyle.css" 
	rel="stylesheet" type="text/css" >-->
<spring:url value="/resources/js/formLoginUserValidation.js" var="cic" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css/LoginStyle.css" var="css" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${cic}" type="module" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />

<title>Insert title here</title>
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
</style>-->

</head>
<body>

<div id="container1">
<div id="container2">
<p>Se non sei ancora registrato clicca <a href="/MyDocsHandler/goToRegistrationForm.html">qui</a>.</p>
<form action="/MyDocsHandler/loginUser" method="post">
<table id="table">
<tr><td>Email: </td><td><input type="text" name="email" id="email" value="${user.email}"/></td></tr>
<tr><td>Password: </td><td><input type="password" name="password" id="password" value="${user.password}"/></td></tr>
<tr><td colspan="2"><input type="submit" id="login" value="Accedi" disabled/></td></tr>
</table>
<p style="color:red;">${msg}</p>
<div id="msgEmail"></div>
<div id="msgPassword"></div>
<div id="msg1"></div>
</form>
</div>
</div>

</body>
</html>