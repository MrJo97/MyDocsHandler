<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.hibernate.Session,org.hibernate.Transaction,com.webapp.controller.Support,com.webapp.dao.UtenteDao, com.webapp.model.utente.*,com.webapp.model.committente.*,com.webapp.model.documento.*,com.webapp.model.recapito.*,java.util.List" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://localhost:8080/MyDocsHandler/src/main/webapp/resources/css3/HomePageStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>

<spring:url value="/resources/css3/HomePageStyle.css" var="css" />
<spring:url value="/resources/js/jsHomePage.js" var="cic" />

<script src="${jquery}" ></script>
<script src="${cic}" ></script>
<link href="${css}" rel="stylesheet" />


	
</head>
<body>
<%System.out.println( request.getRequestURI()); %>
	<div id="table0">
	<!-- includiamo le form per la ricerca e per il caricamento memorizzate 
		in un file jsp esterno-->
		<c:choose>
    	<c:when test="${operation eq 'loading' || operation eq 'editing' }">
        <%@ include file="FakeFiltersAndLoading.jsp" %>
    	</c:when>
   		<c:otherwise>
       	 <%@ include file="FiltersAndLoading.jsp" %>
   		</c:otherwise>
		</c:choose>
	</div>

	
	
<div id="table1">	
<table>
	<tr><td align="center" colspan="5" style="color:red;">Lista documenti caricati</td></tr>
	<%	Utente user = (Utente) session.getAttribute("user");
		List<Documento> docs = user.getDocumenti();
		if(docs != null)
		{
		for(int i=0;i<docs.size();i++)
		{
			Documento doc = docs.get(i); 
			System.out.println(doc.getCommittente().getDocumenti().size() == 1);
			out.println("<tr class='ddld'><td><input type='radio' name='doc' value='"+doc.getNome()+"' onchange='enable(this)'></input>"
			+"</td><td style='width:40%;'><a href='/MyDocsHandler/fileupload/download"+doc.getIdDocumento()+"'>"+doc.getNome()+"</a>"
			+"</td><td style='width:25%;'>"+doc.getData()
			+"</td><td style='width:25%;'>"+doc.getOra()
			+"</td><td><select style='width:65%' name='operations' id='"+doc.getNome()+"' onchange='redirect(this, "+(doc.getCommittente().getDocumenti().size() == 1)+")'"
			+ " disabled>"
			+"<option value=''></option>"
			+"<option value='/MyDocsHandler/editFile/delete"+doc.getIdDocumento()+"'>Elimina</option>"
			+"<option value='/MyDocsHandler/editFile/showDetails"+doc.getIdDocumento()+"'>Mostra dettagli</option>"
			+"<option value='/MyDocsHandler/editFile/catchIdDoc"+doc.getIdDocumento()+"'>Modifica</option>"
			+"</td></tr></select>");
		}
			}
	%>
</table>
</div>
<div id="table2">
<table>
<tr><td align="center" colspan="5" style="color:red;">Lista committenti registrati</td></tr>
	<% 	List<Committente> customers = user.getCommittenti();
		for(int i=0;i<customers.size();i++)
	{
	 	Committente customer = customers.get(i);
	 	out.println("<tr class='ddld' ><td><input type='radio' name='customer' value='"+customer.getIdCommittente()+"' onchange='enable(this)'></input>"
				+"</td><td style='width:80%'>"+customer.getCognome()+ " " + customer.getNome()
				+"</td><td><select style='width:40%' name='operations' id='"+customer.getIdCommittente()+"' onchange='redirect(this)' disabled>"
				+"<option value=''></option>"
				+"<option value='/MyDocsHandler/editCustomer/showDetailsCustomer"+customer.getIdCommittente()+"'>Mostra dettagli</option>"
				+"<option value='/MyDocsHandler/editCustomer/catchIdCustomer"+customer.getIdCommittente()+"'>Modifica</option>"
				+"</select></td></tr>");
						
	}
	%>
		
</table>
</div>

	<% System.out.println(request.getAttribute("destination")); %>
	<c:choose>
    	<c:when test="${destination eq 'homepage'}">
        <div id="emptyBox"></div>
    	</c:when>
   		<c:otherwise>
   		</c:otherwise>
	</c:choose>
	
</body>
</html>