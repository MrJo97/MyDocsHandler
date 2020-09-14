<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,webapp.model.documento.*,webapp.model.utente.*,webapp.model.committente.*,webapp.controller.*" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<spring:url value="/resources/js/loadingFileScript.js" var="lfs" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${lfs}" ></script>

<!--  <script type="text/javascript" src="http://localhost:8080/WebAppTesi/assets/js/setConstraintsLoading.js"></script>-->


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>


<body>
		<form action="/MyDocsHandlerNew/searchDocument/search" method="post">
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
			
					
 			<tr>			   
			<td><input type="radio" value="preliminary" id="preliminary" name="type"
			 <c:if test="${document.categoria eq 'Progetto preliminare'}">checked</c:if>></input>Preliminare</td>
				<td><select name="ppElaborate" id="ppElaborate" <c:if test="${!(document.categoria eq 'Progetto preliminare')}">disabled</c:if>>		   
			    <c:forTokens items="a,b,c,d,e,f,g,h,i" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto preliminare') && (document.tipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
		
			<tr id="pw" hidden="true"><td></td>
			<td><input type="radio" id="pwElab" name="type1" id="pwElab" <c:if test="${document.sottocategoria eq 'Opere e lavori puntuali'}">checked</c:if>></input>Opere e lavori puntuali</td>
			<td><select name="pwElaborate" id="pwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori puntuali')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori puntuali') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>			   
			    </select>
			</td>
			</tr>
			
			
			<tr id="nw" hidden="true"><td></td>
			<td><input type="radio" id="nwElab" name="type1" value="nwElab" <c:if test="${document.sottocategoria eq 'Opere e lavori a rete'}">checked</c:if>></input>Opere e lavori a rete</td>
			<td><select name="nwElaborate" id="nwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori a rete')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori a rete') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
 						
			<!-- progetto definitivo -->
 			<table>
 			<tr><td><input type="radio" value="definitive" id="definitive" name="type" <c:if test="${document.categoria eq 'Progetto definitivo'}">checked</c:if>></input>Definitivo</td>
 			<td><select name="dpElaborate" id="dpElaborate" <c:if test="${!(document.categoria eq 'Progetto definitivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto definitivo') && (document.tipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			
			<tr id="tr" hidden="true"><td></td>
			<td><input type="radio" id="techRep" name="type1" value="techRep" <c:if test="${document.sottocategoria eq 'Relazioni tecniche'}">checked</c:if>></input>Relazioni tecniche</td>
			<td><select name="technicalReport" id="technicalReport" <c:if test="${!(document.sottocategoria eq 'Relazioni tecniche')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Relazioni tecniche') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="dpg" hidden="true"><td></td>
			<td><input type="radio" id="dpgElab" name="type1" value="dpgElab" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input>Elaborati grafici</td>
			<td><select name="dpgElaborate" id="dpgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			<tr id="si" hidden="true">
			<td></td>
			<td></td>
			<td><input type="radio" id="siElab" name="type2" value="siElab" <c:if test="${document.sottocategoria1 eq 'Studi e indagini'}">checked</c:if>></input>Studi e indagini</td>
 			<td><select name="siElaborate" id="siElaborate" <c:if test="${!(document.sottocategoria1 eq 'Studi e indagini')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o,p,q,r" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Studi e indagini') && (document.sottotipo1 eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			 </tr>
			 
			 
			 <tr id="artwork" hidden="true">
			<td></td>
			<td></td>
                <td><input type="radio" id="artworkElab" name="type2" value="artworkElab" <c:if test="${document.sottocategoria1 eq 'Opere d&#39;arte'}">checked</c:if>></input>Opere d'arte</td>
 				<td><select name="artworkElaborate" id="artworkElaborate" <c:if test="${!(document.sottocategoria1 eq 'Opere d&#39;arte')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Opere d&#39;arte') && (document.sottotipo1 eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			
			<tr id="lei" hidden="true">
			<td></td>
			<td></td>
				<td><input type="radio" id="leiElab" name="type2" value="leiElab" <c:if test="${document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali'}">checked</c:if>></input>Inserimenti paesaggistici ed ambientali</td>
 				<td><select name="leiElaborate" id="leiElaborate" <c:if test="${!(document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali') && (document.sottotipo1 eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			 </td>
			 </tr>
			 
			 
			 <tr id="implant" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="implantElab" name="type2" value="implantElab" <c:if test="${document.sottocategoria1 eq 'Impianti'}">checked</c:if>></input>Impianti</td>
 				<td><select name="implantElaborate" id="implantElaborate" <c:if test="${!(document.sottocategoria1 eq 'Impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Impianti') && (document.sottotipo1 eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			    </tr>
			    
			    
			 <tr id="qss" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="qssElab" name="type2" <c:if test="${document.sottocategoria1 eq 'Siti di cava e deposito'}">checked</c:if>></input>Siti di cava e deposito</td>
 				<td><select name="qssElaborate" id="qssElaborate" <c:if test="${!(document.sottocategoria1 eq 'Siti di cava e deposito')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Siti di cava e deposito') && (document.sottotipo1 eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
			<!-- progetto esecutivo -->
 			<table>
 			<tr><td><input type="radio" value="executive" id="executive" name="type" <c:if test="${document.categoria eq 'Progetto esecutivo'}">checked</c:if>></input>Esecutivo</td>
 			<td><select name="epElaborate" id="epElaborate" <c:if test="${!(document.categoria eq 'Progetto esecutivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto esecutivo') && (document.tipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			<tr id="epg" hidden="true"><td></td>
			<td><input type="radio" id="epgElab" name="type1" value="epgElab" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input>Elaborati grafici</td>
			<td><select name="epgElaborate" id="epgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="sep" hidden="true"><td></td>
			<td><input type="radio" id="sepElab" name="type1" value="sepElab" <c:if test="${document.sottocategoria eq 'Progetto esecutivo delle strutture'}">checked</c:if>></input>Progetto esecutivo delle strutture</td>
			<td><select name="sepElaborate"  id="sepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo delle strutture')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo delle strutture') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="iep" hidden="true"><td></td>
			<td><input type="radio" id="iepElab" name="type1" value="iepElab" <c:if test="${document.sottocategoria eq 'Progetto esecutivo degli impianti'}">checked</c:if>></input>Progetto esecutivo degli impianti</td>
			<td><select name="iepElaborate" id="iepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo degli impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo degli impianti') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="mp" hidden="true"><td></td>
			<td><input type="radio" id="mpElab" name="type1" value="mpElab" <c:if test="${document.sottocategoria eq 'Piano di manutenzione'}">checked</c:if>></input>Piano di manutenzione</td>
			<td><select name="mpElaborate" id="mpElaborate" <c:if test="${!(document.sottocategoria eq 'Piano di manutenzione')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Piano di manutenzione') && (document.sottotipo eq character) }">selected</c:if>></option>
			    </c:forTokens>
			    </select></td>
			</tr>	
 			</table>
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			<table>
 			<tr><td style="color:red;">Committenti registrati</td></tr>
 			<tr><td><select name="selectRegisteredCustomer" id="sru">
			<option value="selectCustomer">Scegli committente</option>
			<c:forEach items="${user.committenti}" var="cust">
			    <option value="${cust.idCommittente}" <c:if test="${(document.committente.nome eq cust.nome) && (document.committente.cognome eq cust.cognome)}">selected</c:if>>${cust.cognome} ${surn.nome}</option>
			</c:forEach>
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

		<form action="/MyDocsHandlerNew/fileupload/checkFile" method="post" enctype="multipart/form-data">
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
	<form action="/MyDocsHandlerNew/logout"
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