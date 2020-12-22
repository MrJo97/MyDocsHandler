<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="webapp.model.*,java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<spring:url value="/resources/css2/DetailsAndEditPagesStyle.css" var="css" />
<link href="${css}" rel="stylesheet" />

<title>Insert title here</title>
</head>
<body>
<%@ include file="HomePage.jsp"%>
<div id="cssStyle">	
<table>
<tr><td colspan="2" style="color:red">Dati Committente</td></tr>
<tr><td>Nome: </td><td>${doc.committente.nome}</td></tr>
<tr><td>Cognome: </td><td>${doc.committente.cognome}</td></tr>
<tr><td>CF: </td><td>${doc.committente.cf}</td></tr>
<tr><td>Telefono: </td><td>${doc.committente.telefono}</td></tr>
<tr><td>Email: </td><td>${doc.committente.email}</td></tr>
</table>

<br />
<br />

<table>
<tr><td colspan="2" style="color:red">Dati Documento</td></tr>
<tr><td>Nome: </td><td>${doc.nome}</td></tr>
<tr><td>Descrizione: </td><td>${doc.descrizione}</td></tr>
<tr><td>Dimensione: </td><td>${doc.dimensione} byte</td></tr>
</table>


<div>
<p style="font-size: 125%;"><b>Articoli descrittivi del documento (DPR del 5 ottobre 2010, n.207) </b></p>
<ul>

		<c:choose>
		
			<c:when test="${doc.categoria eq 'Progetto preliminare'}">
				<li>Progetto preliminare</li>
				<ul><li><b>Art 17, comma 1, lettera ${doc.tipo}</b></li></ul>
				<c:choose>
					<c:when test="${doc.sottocategoria eq 'Opere e lavori puntuali'}">
						<li>Opere e lavori puntuali</li>
						<ul><li><b>Art.21, comma 1, lettera a, numero ${doc.sottotipo}</b></li></ul> 
					</c:when>
					<c:when test="${doc.sottocategoria eq 'Opere e lavori a rete'}">
						<li>Opere e lavori a rete</li>
						<ul><li><b>Art.21, comma 1, lettera b, numero ${doc.sottotipo}</b></li></ul>
					</c:when>
				</c:choose>
			</c:when>
		
		
			<c:when test="${doc.categoria eq 'Progetto definitivo'}">
				<li>Progetto definitivo</li>
				<ul><li><b>Art 24, comma 2, lettera ${doc.tipo}</b></li></ul>
				<c:choose>
					<c:when test="${doc.sottocategoria eq 'Relazioni tecniche'}">
						<li>Relazioni tecniche</li>
						<ul><li><b>Art.26, comma 1, lettera ${doc.sottotipo}</b></li></ul>
					</c:when>
					<c:when test="${doc.sottocategoria eq 'Elaborati grafici'}">
						<li>Elaborati grafici</li>
						<ul><li><b>Art.28, comma 2, lettera ${doc.sottotipo}</b></li></ul>
						<li>${doc.sottocategoria1}</li>
						<ul><li><b>Art.28, comma 5, lettera ${doc.sottotipo1}</b></li></ul>
					
					</c:when>
				</c:choose>
			</c:when>
			
		<c:when test="${doc.categoria eq 'Progetto esecutivo'}">
			<li>Progetto esecutivo</li>
			<ul><li><b>Art 33, comma 1, lettera ${doc.tipo}</b></li></ul>
			<c:choose>
				<c:when test="${doc.sottocategoria eq 'Elaborati grafici'}">
					<li>Elaborati grafici</li>
					<ul><li><b>Art.36, comma 1, lettera ${doc.sottotipo}</b></li></ul>
				</c:when>
				<c:when test="${doc.sottocategoria eq 'Progetto esecutivo delle strutture'}">
				<li>Progetto esecutivo delle strutture</li>
					<ul><li><b>Art.37, comma 6, lettera ${doc.sottotipo}</b></li></ul>
				</c:when>
				<c:when test="${doc.sottocategoria eq 'Progetto esecutivo degli impianti'}">
				<li>Progetto esecutivo degli impianti</li>
					<ul><li><b>Art.37, comma 8, lettera ${doc.sottotipo}</b></li></ul>
				</c:when>
				<c:when test="${doc.sottocategoria eq 'Piano di manutenzione'}">
				<li>Piano di manutenzione</li>
					<ul><li><b>Art.38, comma 2, lettera ${doc.sottotipo}</b></li></ul>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>

</ul>
</div>
</div>
</body>
</html>