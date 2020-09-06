<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,com.webapp.model.documento.*,com.webapp.model.utente.*,com.webapp.model.committente.*,com.webapp.controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<spring:url value="/resources/js/loadingFileScript.js" var="lfs" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${lfs}" ></script>

<!--  <script type="text/javascript" src="http://localhost:8080/WebAppTesi/assets/js/setConstraintsLoading.js"></script>-->


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>


<body>
		<form action="/MyDocsHandler/searchDocument/search" method="post">
			<table>
				<tr>
					<td align="center" colspan="2" id="titolo" style="font-size:125%;color:red;">Parametri di ricerca</td>
				</tr>
				<tr><td><br/></td></tr>
				<tr><td colspan="2" style="color:red;">Dati documento</td></tr>
				<tr>
					<td id="namefileSearch">Nome documento:</td>
					<td><input type="text" name="namefile"
						placeholder="Inserisci il nome del file"></input></td>
				</tr>
				<tr>
					<td colspan="2" id="fields">Fase di progetto:</td>
				</tr>
			
 			<% Documento document = (Documento)request.getAttribute("document"); 
 			   if(document == null)
 			   {
 				   document = new Documento();
 			   }%>
 			<tr>
 			<td>
 			<input type="radio" value="preliminary" id="preliminary" name="type" <%=JSPSupport.checkCategory(document, "Progetto preliminare", "radio")%>></input>Preliminare967
 			</td>
 			<td>
 			<select name="ppElaborate" id="ppElaborate" <%=JSPSupport.checkCategory(document, "Progetto preliminare", "select")%>>
			    <option  value="a" <%=JSPSupport.checkType(document, "Progetto preliminare", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkType(document, "Progetto preliminare", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkType(document, "Progetto preliminare", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkType(document, "Progetto preliminare", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkType(document, "Progetto preliminare", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkType(document, "Progetto preliminare", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkType(document, "Progetto preliminare", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkType(document, "Progetto preliminare", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkType(document, "Progetto preliminare", "i")%>> i</option>
			</select>
			</td>
			</tr>
			
		
			<tr id="pw" hidden="true"><td></td>
			<td><input type="radio" id="pwElab" name="type1" id="pwElab" <%=JSPSupport.checkSubCategory(document, "Opere e lavori puntuali", "radio")%>></input>Opere e lavori puntuali</td>
			<td><select name="pwElaborate" id="pwElaborate" <%=JSPSupport.checkSubCategory(document, "Opere e lavori puntuali", "select")%>>
			    <option  value="1" <%=JSPSupport.checkSubType(document, "Opere e lavori puntuali", "1")%>> 1</option>
			    <option  value="2" <%=JSPSupport.checkSubType(document, "Opere e lavori puntuali", "2")%>> 2</option>
			    <option  value="3" <%=JSPSupport.checkSubType(document, "Opere e lavori puntuali", "3")%>> 3</option>
			    <option  value="4" <%=JSPSupport.checkSubType(document, "Opere e lavori puntuali", "4")%>> 4</option>
			    </select></td>
			    
			</tr>
			
			
			<tr id="nw" hidden="true"><td></td>
			<td><input type="radio" id="nwElab" name="type1" value="nwElab" <%=JSPSupport.checkSubCategory(document, "Opere e lavori a rete", "radio")%>></input>Opere e lavori a rete</td>
			<td><select name="nwElaborate" id="nwElaborate" <%=JSPSupport.checkSubCategory(document, "Opere e lavori a rete", "select")%>>
			    <option  value="1" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "1")%>> 1</option>
			    <option  value="2" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "2")%>> 2</option>
			    <option  value="3" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "3")%>> 3</option>
			    <option  value="4" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "4")%>> 4</option>
			    <option  value="5" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "5")%>> 5</option>
			    <option  value="6" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "6")%>> 6</option>
			    <option  value="7" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "7")%>> 7</option>
			    <option  value="8" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "8")%>> 8</option>
			    <option  value="9" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "9")%>> 9</option>
			    <option  value="10" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "10")%>> 10</option>
			    <option  value="11" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "11")%>> 11</option>
			    <option  value="12" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "12")%>> 12</option>
			    <option  value="13" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "13")%>> 13</option>
			    <option  value="14" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "14")%>> 14</option>
			    <option  value="15" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "15")%>> 15</option>
			    <option  value="16" <%=JSPSupport.checkSubType(document, "Opere e lavori a rete", "16")%>> 16</option>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
 						
			<!-- progetto definitivo -->
 			<table>
 			<tr><td><input type="radio" value="definitive" id="definitive" name="type" <%=JSPSupport.checkCategory(document, "Progetto definitivo", "radio")%>></input>Definitivo</td>
 			<td><select name="dpElaborate" id="dpElaborate" <%=JSPSupport.checkCategory(document, "Progetto definitivo", "select")%>>
			    <option  value="a" <%=JSPSupport.checkType(document, "Progetto definitivo", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkType(document, "Progetto definitivo", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkType(document, "Progetto definitivo", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkType(document, "Progetto definitivo", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkType(document, "Progetto definitivo", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkType(document, "Progetto definitivo", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkType(document, "Progetto definitivo", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkType(document, "Progetto definitivo", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkType(document, "Progetto definitivo", "i")%>> i</option>
			    <option  value="l" <%=JSPSupport.checkType(document, "Progetto definitivo", "l")%>> l</option>
			    <option  value="m" <%=JSPSupport.checkType(document, "Progetto definitivo", "m")%>> m</option>
			    <option  value="n" <%=JSPSupport.checkType(document, "Progetto definitivo", "n")%>> n</option>
			    <option  value="o" <%=JSPSupport.checkType(document, "Progetto definitivo", "o")%>> o</option>
			    </select>
			</td>
			</tr>
			<tr id="tr" hidden="true"><td></td>
			<td><input type="radio" id="techRep" name="type1" value="techRep" <%=JSPSupport.checkSubCategory(document, "Relazioni tecniche", "radio")%>></input>Relazioni tecniche</td>
			<td><select name="technicalReport" id="technicalReport" <%=JSPSupport.checkSubCategory(document, "Relazioni tecniche", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "i")%>> i</option>
			    <option  value="l" <%=JSPSupport.checkSubType(document, "Relazioni tecniche", "l")%>> l</option></select></td>
			</tr>
			
			<tr id="dpg" hidden="true"><td></td>
			<td><input type="radio" id="dpgElab" name="type1" value="dpgElab" <%=JSPSupport.checkSubCategory(document, "Elaborati grafici", "radio")%>></input>Elaborati grafici</td>
			<td><select name="dpgElaborate" id="dpgElaborate" <%=JSPSupport.checkSubCategory(document, "Elaborati grafici", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "i")%>> i</option>
			    <option  value="l" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "l")%>> l</option>
			    </select>
			    </td>
			</tr>
			<tr id="si" hidden="true">
			<td></td>
			<td></td>
			<td><input type="radio" id="siElab" name="type2" value="siElab" <%=JSPSupport.checkSubCategory1(document, "Studi e indagini", "radio")%>></input>Studi e indagini</td>
 			<td><select name="siElaborate" id="siElaborate" <%=JSPSupport.checkSubCategory1(document, "Studi e indagini", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "i")%>> i</option>
			    <option  value="l" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "l")%>> l</option>
			    <option  value="m" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "m")%>> m</option>
			    <option  value="n" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "n")%>> n</option>
			    <option  value="o" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "o")%>> o</option>
			    <option  value="p" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "p")%>> p</option>
			    <option  value="q" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "q")%>> q</option>
			    <option  value="r" <%=JSPSupport.checkSubType1(document, "Studi e indagini", "r")%>> r</option>
			    </select>
			    </td>
			 </tr>
			 
			 
			 <tr id="artwork" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="artworkElab" name="type2" value="artworkElab" <%=JSPSupport.checkSubCategory1(document, "Opere d'arte", "radio")%>></input>Opere d'arte</td>
 				<td><select name="artworkElaborate" id="artworkElaborate" <%=JSPSupport.checkSubCategory1(document, "Opere d'arte", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType1(document, "Opere d'arte", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType1(document, "Opere d'arte", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType1(document, "Opere d'arte", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkSubType1(document, "Opere d'arte", "d")%>> d</option>
			    </select>
			    </td>
			</tr>
			
			<tr id="lei" hidden="true">
			<td></td>
			<td></td>
				<td><input type="radio" id="leiElab" name="type2" value="leiElab" <%=JSPSupport.checkSubCategory1(document, "Inserimenti paesaggistici ed ambientali", "radio")%>></input>Inserimenti paesaggistici ed ambientali</td>
 				<td><select name="leiElaborate" id="leiElaborate" <%=JSPSupport.checkSubCategory1(document, "Inserimenti paesaggistici ed ambientali", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType1(document, "Inserimenti paesaggistici ed ambientali", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType1(document, "Inserimenti paesaggistici ed ambientali", "b")%>> b</option>
			 </select>
			 </td>
			 </tr>
			 
			 
			 <tr id="implant" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="implantElab" name="type2" value="implantElab" <%=JSPSupport.checkSubCategory1(document, "Impianti", "radio")%>></input>Impianti</td>
 				<td><select name="implantElaborate" id="implantElaborate" <%=JSPSupport.checkSubCategory1(document, "Impianti", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType1(document, "Impianti", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType1(document, "Impianti", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType1(document, "Impianti", "c")%>> c</option>
			    </select>
			    </td>
			    </tr>
			    
			    
			 <tr id="qss" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="qssElab" name="type2" <%=JSPSupport.checkSubCategory1(document, "Siti di cava e deposito", "radio")%>></input>Siti di cava e deposito</td>
 				<td><select name="qssElaborate" id="qssElaborate" <%=JSPSupport.checkSubCategory1(document, "Siti di cava e deposito", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType1(document, "Siti di cava e deposito", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType1(document, "Siti di cava e deposito", "b")%>> b</option>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
			<!-- progetto esecutivo -->
 			<table>
 			<tr><td><input type="radio" value="executive" id="executive" name="type" <%=JSPSupport.checkCategory(document, "Progetto esecutivo", "radio")%>></input>Esecutivo</td>
 			<td><select name="epElaborate" id="epElaborate" <%=JSPSupport.checkCategory(document, "Progetto esecutivo", "select")%>>
			    <option  value="a" <%=JSPSupport.checkType(document, "Progetto esecutivo", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkType(document, "Progetto esecutivo", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkType(document, "Progetto esecutivo", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkType(document, "Progetto esecutivo", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkType(document, "Progetto esecutivo", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkType(document, "Progetto esecutivo", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkType(document, "Progetto esecutivo", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkType(document, "Progetto esecutivo", "h")%>> h</option>
			    <option  value="i" <%=JSPSupport.checkType(document, "Progetto esecutivo", "i")%>> i</option>
			    <option  value="l" <%=JSPSupport.checkType(document, "Progetto esecutivo", "l")%>> l</option>
			    <option  value="m" <%=JSPSupport.checkType(document, "Progetto esecutivo", "m")%>> m</option>
			    </select>
			</td>
			</tr>
			<tr id="epg" hidden="true"><td></td>
			<td><input type="radio" id="epgElab" name="type1" value="epgElab" <%=JSPSupport.checkSubCategory(document, "Elaborati grafici", "radio")%>></input>Elaborati grafici</td>
			<td><select name="epgElaborate" id="epgElaborate" <%=JSPSupport.checkSubCategory(document, "Elaborati grafici", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "c")%>> c</option>
			    <option  value="d" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "d")%>> d</option>
			    <option  value="e" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "e")%>> e</option>
			    <option  value="f" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "f")%>> f</option>
			    <option  value="g" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "g")%>> g</option>
			    <option  value="h" <%=JSPSupport.checkSubType(document, "Elaborati grafici", "h")%>> h</option>
			    </select></td>
			</tr>
			
			<tr id="sep" hidden="true"><td></td>
			<td><input type="radio" id="sepElab" name="type1" value="sepElab" <%=JSPSupport.checkSubCategory(document, "Progetto esecutivo delle strutture", "radio")%>></input>Progetto esecutivo delle strutture</td>
			<td><select name="sepElaborate"  id="sepElaborate" <%=JSPSupport.checkSubCategory(document, "Progetto esecutivo delle strutture", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Progetto esecutivo delle strutture", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Progetto esecutivo delle strutture", "b")%>> b</option>
			    </select></td>
			</tr>
			
			<tr id="iep" hidden="true"><td></td>
			<td><input type="radio" id="iepElab" name="type1" value="iepElab" <%=JSPSupport.checkSubCategory(document, "Progetto esecutivo degli impianti", "radio")%>></input>Progetto esecutivo degli impianti</td>
			<td><select name="iepElaborate" id="iepElaborate" <%=JSPSupport.checkSubCategory(document, "Progetto esecutivo degli impianti", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Progetto esecutivo degli impianti", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Progetto esecutivo degli impianti", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType(document, "Progetto esecutivo degli impianti", "c")%>> c</option>
			    </select></td>
			</tr>
			
			<tr id="mp" hidden="true"><td></td>
			<td><input type="radio" id="mpElab" name="type1" value="mpElab" <%=JSPSupport.checkSubCategory(document, "Piano di manutenzione", "radio")%>></input>Piano di manutenzione</td>
			<td><select name="mpElaborate" id="mpElaborate" <%=JSPSupport.checkSubCategory(document, "Piano di manutenzione", "select")%>>
			    <option  value="a" <%=JSPSupport.checkSubType(document, "Piano di manutenzione", "a")%>> a</option>
			    <option  value="b" <%=JSPSupport.checkSubType(document, "Piano di manutenzione", "b")%>> b</option>
			    <option  value="c" <%=JSPSupport.checkSubType(document, "Piano di manutenzione", "c")%>> c</option>
			    </select></td>
			</tr>
			<tr></tr>
 			</table>
 			
 			<table>
 			<tr><td style="color:red;">Committenti registrati</td></tr>
 			<tr><td><select name="selectRegisteredCustomer" id="sru">
			<option value="selectCustomer">Scegli committente</option>
			<%Utente user1 = (Utente) session.getAttribute("user");
				if(user1.getCommittenti() != null)
							{
								List<Committente> customers = user1.getCommittenti();
								for(int i=0;i<customers.size();i++)
								{
									String nameCustomer = customers.get(i).getNome();
									String surnCustomer = customers.get(i).getCognome();
									out.println("<option value='"+ customers.get(i).getIdCommittente() + "' "+JSPSupport.checkCustomer(document, customers.get(i))+">" +surnCustomer+ " " + nameCustomer + "</option>");
								}
							}
			%>
			</select></td></tr>			
					
				<tr>
					<td></td>
					<td><input type="reset" name="reset"
						value="Reset"></input><input type="submit" name="cerca"
						value="Cerca"></input></td>
				</tr>
			</table>
		</form>
<br/>
<br/>

		<form action="/MyDocsHandler/fileupload/checkFile" method="post" enctype="multipart/form-data">
			<table id="tabella1">
				<tr>
					<td align="center" colspan="2" id="titolo1">Caricamento file</td>
				</tr>
				<tr><td><br/></td></tr>
				<tr>
					<td id="fields">Percorso file:</td>
					<td><input id="fields" type="file" id="insertFile"
						name="file" placeholder="Inserisci il percorso" ></input></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="submit" name="carica"
						value="Carica" id="load"></input></td>
				</tr>
			</table>
			<div style="color:red;">${msg}</div>

		</form>
		<br/>
		
	<div id="logout">
	<form action="/MyDocsHandler/logout"
			method="post">
	  <table>
       <tr>
        <td><input type="submit" name="logout" value="Log Out" id="logout"></input></td>
       </tr>        
      </table>
	</form>
	</div>
	

 </body>
</html>