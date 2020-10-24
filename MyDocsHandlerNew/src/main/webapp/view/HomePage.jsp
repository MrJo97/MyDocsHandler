<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.hibernate.Session,org.hibernate.Transaction,webapp.controller.Support,webapp.dao.UtenteDao, webapp.model.utente.*,webapp.model.committente.*,webapp.model.documento.*,webapp.model.recapito.*,java.util.List"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="http://localhost:8080/MyDocsHandlerNew/src/main/webapp/resources/css3/HomePageStyle.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<spring:url value="/resources/css3/HomePageStyle.css" var="css" />
<spring:url value="/resources/js/jsHomePage.js" var="cic" />

<script src="${jquery}"></script>
<script src="${cic}"></script>
<link href="${css}" rel="stylesheet" />



</head>
<body>

	<div id="table0">
		<!-- includiamo le form per la ricerca e per il caricamento memorizzate 
		in un file jsp esterno-->
		<c:choose>
			<c:when test="${operation eq 'loading' || operation eq 'editing' }">
				<%@ include file="FakeFiltersAndLoading.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="FiltersAndLoading.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="table1">
		<table>
			<tr>
				<td align="center" colspan="5" style="color: red;">Lista
					documenti caricati</td>
			</tr>
			<c:forEach items="${user.documenti}" var="doc">
				<tr class='ddld'><td><input type='radio' name='doc' value='${doc.nomefile}' onchange='enable(this)'></input></td>
				<td style='width:40%;'><a href='/MyDocsHandlerNew/fileupload/download${doc.idDocumento}'>${doc.nomefile}</a></td>
				<td style='width:25%;'>${doc.data}</td>
				<td style='width:25%;'>${doc.ora}</td>
				<td><select style='width:65%' name='operations' id='${doc.nomefile}' onchange='redirect(this, ${doc.committente.documenti.size() eq 1})' disabled>
				<option value=''></option>
				<option value='/MyDocsHandlerNew/editFile/delete${doc.idDocumento}'>Elimina</option>
				<option value='/MyDocsHandlerNew/editFile/showDetails${doc.idDocumento}'>Mostra Dettagli</option>
				<option value='/MyDocsHandlerNew/editFile/catchIdDoc${doc.idDocumento}'>Modifica</option>
				</select></td></tr>				
			</c:forEach>
		</table>
	</div>
	
	
	<div id="table2">
		<table>
			<tr>
				<td align="center" colspan="5" style="color: red;">Lista
					committenti registrati</td>
			</tr>
			<c:forEach items="${user.committenti}" var="cust">
			<tr class='ddld' ><td><input type='radio' name='customer' value="${cust.idCommittente}" onchange='enable(this)'></input></td>
			<td style='width:80%'>${cust.cognome} ${cust.nome}</td>
			<td><select style='width:40%' name='operations' id="${cust.idCommittente}" onchange='redirect(this, true)' disabled>
			<option value=''></option>
			<option value="/MyDocsHandlerNew/editCustomer/showDetailsCustomer${cust.idCommittente}">Mostra dettagli</option>
			<option value="/MyDocsHandlerNew/editCustomer/catchIdCustomer${cust.idCommittente}">Modifica</option>
			</select>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<c:choose>
		<c:when test="${destination eq 'homepage'}">
			<div id="emptyBox"></div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>

</body>
</html>