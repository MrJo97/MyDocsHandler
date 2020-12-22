<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="webapp.model.*,java.util.List"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link
	href="http://localhost:8080/MyDocsHandler/resources/css2/HomePageStyle.css"
	rel="stylesheet" type="text/css" >-->
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<spring:url value="/resources/css2/HomePageStyle.css" var="css" />
<spring:url value="/resources/js/homePageFunctions.js" var="cic" />

<script src="${jquery}"></script>
<script src="${cic}"></script>
<link href="${css}" rel="stylesheet" />



</head>
<body>
	<!-- <div id="title" align="center" id="titolo"><div style="vertical-align:middle;">MyDocsHandler</div></div>-->
	<div >
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
    <div id="title" align="center">Lista documenti caricati</div>
	<div id="table11">
		<table>
			<tr>
				<!-- <td align="center" colspan="5" style="color: red;">Lista
					documenti caricati</td>-->
			</tr>
			<c:forEach items="${user.documenti}" var="doc">
				<tr class='ddld'><td><input type='radio' name='doc' value='${doc.nome}' onchange='enable(this)'></input></td>
				<td style='width:40%;'><a href='/MyDocsHandler/fileupload/download${doc.idDocumento}'>${doc.nome}</a></td>
				<td style='width:25%;'>${doc.data}</td>
				<td style='width:25%;'>${doc.ora}</td>
				<td><select style='width:65%' name='operations' id='${doc.nome}' onchange='redirect(this, ${doc.committente.documenti.size() eq 1})' disabled>
				<option value=''></option>
				<option value='/MyDocsHandler/editFile/delete${doc.idDocumento}'>Elimina</option>
				<option value='/MyDocsHandler/editFile/showDetails${doc.idDocumento}'>Mostra Dettagli</option>
				<option value='/MyDocsHandler/editFile/catchIdDoc${doc.idDocumento}'>Modifica</option>
				</select></td></tr>				
			</c:forEach>
		</table>
	</div>
	</div>
	
	
	<div id="table2">
    <div id="title" align="center">Lista committenti registrati</div>
	<div id="table22">
		<table>
			<!-- <tr>
				<td align="center" colspan="5" style="color: red;">Lista
					committenti registrati</td>
			</tr>-->
			<c:forEach items="${user.committenti}" var="cust">
			<tr class='ddld' ><td><input type='radio' name='customer' value="${cust.idCommittente}" onchange='enable(this)'></input></td>
			<td style='width:80%'>${cust.cognome} ${cust.nome}</td>
			<td><select style='width:40%' name='operations' id="${cust.idCommittente}" onchange='redirect(this, true)' disabled>
			<option value=''></option>
			<option value="/MyDocsHandler/editCustomer/showDetailsCustomerForUser${cust.idCommittente}">Mostra dettagli</option>
			</select>
			</tr>
			</c:forEach>
		</table>
	</div>
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