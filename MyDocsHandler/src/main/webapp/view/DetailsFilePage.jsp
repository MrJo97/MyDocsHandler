<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.webapp.model.utente.*,com.webapp.model.documento.*,com.webapp.model.recapito.*,java.util.List,com.webapp.model.committente.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<spring:url value="/resources/css3/DetailsAndEditPagesStyle.css" var="css" />
<link href="${css}" rel="stylesheet" />

<title>Insert title here</title>
</head>
<body>
<%@ include file="HomePage.jsp"%>
<%
	Documento document1 = (Documento) session.getAttribute("doc");
	Committente customer = document1.getCommittente();
%>
<div id="cssStyle">	
<table>
<tr><td colspan="2" style="color:red">Dati Committente</td></tr>
<tr><td>Nome: </td><td>${doc.committente.nome}</td></tr>
<tr><td>Cognome: </td><td>${doc.committente.cognome}</td></tr>
<tr><td>CF: </td><td>${doc.committente.cf}</td></tr>
<tr><td>Telefono(1): </td><td>${doc.committente.recapiti.get(0).telefono}</td></tr>
<tr><td>Telefono(2): </td><td>${doc.committente.recapiti.get(1).telefono}</td></tr>
<tr><td>Email(1): </td><td>${doc.committente.recapiti.get(0).email}</td></tr>
<tr><td>Email(2): </td><td>${doc.committente.recapiti.get(1).email}</td></tr>
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
<p style="font-size: 125%;"><b>Articoli descrittivi del document1o (DPR del 5 ottobre 2010, n.207) </b></p>
<ul>


<% 
if(document1.getCategoria().equals("Progetto preliminare"))
	{
	out.println("<li colspan='2'>Progetto preliminare</li>");
	out.println("<ul><li colspan='2'><b>Art 17, comma 1, lettera "+document1.getTipo()+"</b></li></ul>");
	  if(document1.getSottocategoria() != null)
	  {
		  switch(document1.getSottocategoria())
		  {
		  	case "Opere e lavori puntuali":
		  		out.println("<li colspan='2'>Opere e lavori puntuali</li>");
		 		out.println("<ul><li colspan='2'><b>Art.21, comma 1, lettera a, numero "+
				document1.getSottotipo()+"</b></li></ul>");
		 		break;
			
		  	case "Opere e lavori a rete":
		  		out.println("<li colspan='2'>Opere e lavori a rete</li>");
				out.println("<ul><li colspan='2'><b>Art.21, comma 1, lettera b, numero "+
				document1.getSottotipo()+"</b></li></ul>");
		
	  	  }
		}
	}
	else if (document1.getCategoria().equals("Progetto definitivo"))
	{
	  out.println("<li colspan='2'>Progetto definitivo</li>");
	  out.println("<ul><li colspan='2'><b>Art 24, comma 2, lettera "+document1.getTipo()+"</b></li></ul>");
	  System.out.println("Sottocategoria: " + document1.getSottocategoria());
	  if(document1.getSottocategoria() != null)
	  {
		  switch(document1.getSottocategoria())
		  {
		  	case "Relazioni tecniche":
		  		out.println("<li colspan='2'>Relazioni tecniche</li>");
	    		out.println("<ul><li colspan='2'><b>Art.26, comma 1, lettera "+document1.getSottotipo()+"</b></li></ul>");
				break;
		 	 case "Elaborati grafici":
		 		out.println("<li colspan='2'>Elaborati grafici</li>");
				if(document1.getSottocategoria1() != null)
				{
					out.println("<ul><li colspan='2'><b>Art.28, comma 2, lettera "+document1.getSottotipo()+"</b></li></ul>");
					out.println("<li colspan='2'>" + document1.getSottocategoria1()+"</li>");
					out.println("<ul><li colspan='2'><b>Art.28, comma 5, lettera "+ document1.getSottotipo1() +"</b></li></ul>");
				}
				else
				{
					out.println("<ul><li colspan='2'><b>Art.28, comma 2, lettera "+document1.getSottotipo()+"</b></li><ul>");
				}
				break;
	  	  }
	  }
	}
	else if (document1.getCategoria().equals("Progetto esecutivo"))
	{
		out.println("<li colspan='2'>Progetto esecutivo</li>");
		out.println("<ul><li colspan='2'><b>Art 33, comma 1, lettera "+document1.getTipo()+"</b></li></ul>");
		if(document1.getSottocategoria() != null)
		  {	switch(document1.getSottocategoria())
			{
		  		case "Elaborati grafici":
		  			out.println("<li colspan='2'>Elaborati grafici</li>");
					out.println("<ul><li colspan='2'><b>Art.36, comma 1, lettera "+document1.getSottotipo()+"</b></li></ul>");
					break;
		  		case "Progetto esecutivo delle strutture":
		  			out.println("<li colspan='2'>Progetto esecutivo delle strutture</li>");
					out.println("<ul><li colspan='2'><b>Art.37, comma 6, lettera "+document1.getSottotipo()+"</b></li></ul>");
					break;
		  		case "Progetto esecutivo degli impianti":
		  			out.println("<li colspan='2'>Progetto esecutivo degli impianti</li>");
					out.println("<ul><li colspan='2'><b>Art.37, comma 8, lettera "+document1.getSottotipo()+"</b></li></ul>");
					break;
		  		case "Piano di manutenzione":
		  			out.println("<li colspan='2'>Piano di manutenzione</li>");
					out.println("<ul><li colspan='2'><b>Art.38, comma 2, lettera "+document1.getSottotipo()+"</b></li></ul>");
					break;
			}
		  }
			
	}
	%>
</ul>
</div>
</div>
<br/>
<!-- <p>Per ritornare alla pagina principale clicca <a href="/MyDocsHandler/view/HomePage.jsp">qui</a>.</p>-->
</body>
</html>