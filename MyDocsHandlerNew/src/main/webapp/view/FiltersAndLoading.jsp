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
					<td><input type="text" name="nomefile"
						placeholder="Inserisci il nome del file" value="${document.nomefile}"></input></td>
				</tr>
				<tr>
					<td colspan="2" id="fields">Fase di progetto:</td>
				</tr>
			
				
 			<tr>			   
			<td><input type="radio" value="Progetto preliminare" id="preliminary" name="categoria"
			 <c:if test="${document.categoria eq 'Progetto preliminare'}">checked</c:if>></input>Preliminare</td>
				<td><select name="tipo" id="ppElaborate" <c:if test="${!(document.categoria eq 'Progetto preliminare')}">disabled</c:if>>		   
			    <c:forTokens items="a,b,c,d,e,f,g,h,i" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto preliminare') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
		
			<tr id="pw" hidden="true"><td></td>
			<td><input type="radio" value="Opere e lavori puntuali" name="sottocategoria" id="pwElab" <c:if test="${document.sottocategoria eq 'Opere e lavori puntuali'}">checked</c:if>></input>Opere e lavori puntuali</td>
			<td><select name="sottotipo" id="pwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori puntuali')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori puntuali') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>			   
			    </select>
			</td>
			</tr>
			
			
			<tr id="nw" hidden="true"><td></td>
			<td><input type="radio" id="nwElab" name="sottocategoria" value="Opere e lavori a rete" <c:if test="${document.sottocategoria eq 'Opere e lavori a rete'}">checked</c:if>></input>Opere e lavori a rete</td>
			<td><select name="sottotipo" id="nwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori a rete')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori a rete') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
 						
			<!-- progetto definitivo -->
 			<table>
 			<tr><td><input type="radio" value="Progetto definitivo" id="definitive" name="categoria" <c:if test="${document.categoria eq 'Progetto definitivo'}">checked</c:if>></input>Definitivo</td>
 			<td><select name="tipo" id="dpElaborate" <c:if test="${!(document.categoria eq 'Progetto definitivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto definitivo') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			
			<tr id="tr" hidden="true"><td></td>
			<td><input type="radio" id="techRep" name="sottocategoria" value="Relazioni tecniche" <c:if test="${document.sottocategoria eq 'Relazioni tecniche'}">checked</c:if>></input>Relazioni tecniche</td>
			<td><select name="sottotipo" id="technicalReport" <c:if test="${!(document.sottocategoria eq 'Relazioni tecniche')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Relazioni tecniche') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="dpg" hidden="true"><td></td>
			<td><input type="radio" id="dpgElab" name="sottocategoria" value="Elaborati grafici" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input>Elaborati grafici</td>
			<td><select name="sottotipo" id="dpgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			<tr id="si" hidden="true">
			<td></td>
			<td></td>
			<td><input type="radio" id="siElab" name="sottocategoria1" value="Studi e indagini" <c:if test="${document.sottocategoria1 eq 'Studi e indagini'}">checked</c:if>></input>Studi e indagini</td>
 			<td><select name="sottotipo1" id="siElaborate" <c:if test="${!(document.sottocategoria1 eq 'Studi e indagini')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o,p,q,r" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Studi e indagini') && (document.sottotipo1 eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			 </tr>
			 
			 
			 <tr id="artwork" hidden="true">
			<td></td>
			<td></td>
                <td><input type="radio" id="artworkElab" name="sottocategoria1" value="Opere d&#39;arte" <c:if test="${document.sottocategoria1 eq 'Opere d&#39;arte'}">checked</c:if>></input>Opere d'arte</td>
 				<td><select name="sottotipo1" id="artworkElaborate" <c:if test="${!(document.sottocategoria1 eq 'Opere d&#39;arte')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Opere d&#39;arte') && (document.sottotipo1 eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			
			<tr id="lei" hidden="true">
			<td></td>
			<td></td>
				<td><input type="radio" id="leiElab" name="sottocategoria1" value="Inserimenti paesaggistici ed ambientali" <c:if test="${document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali'}">checked</c:if>></input>Inserimenti paesaggistici ed ambientali</td>
 				<td><select name="sottotipo1" id="leiElaborate" <c:if test="${!(document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Inserimenti paesaggistici ed ambientali') && (document.sottotipo1 eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			 </td>
			 </tr>
			 
			 
			 <tr id="implant" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="implantElab" name="sottocategoria1" value="Impianti" <c:if test="${document.sottocategoria1 eq 'Impianti'}">checked</c:if>></input>Impianti</td>
 				<td><select name="sottotipo1" id="implantElaborate" <c:if test="${!(document.sottocategoria1 eq 'Impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Impianti') && (document.sottotipo1 eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			    </tr>
			    
			    
			 <tr id="qss" hidden="true">
			<td></td>
			<td></td>
			    <td><input type="radio" id="qssElab" name="sottocategoria1" value="Siti di cava e deposito" <c:if test="${document.sottocategoria1 eq 'Siti di cava e deposito'}">checked</c:if>></input>Siti di cava e deposito</td>
 				<td><select name="sottotipo1" id="qssElaborate" <c:if test="${!(document.sottocategoria1 eq 'Siti di cava e deposito')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria1 eq 'Siti di cava e deposito') && (document.sottotipo1 eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 						
			<!-- progetto esecutivo -->
 			<table>
 			<tr><td><input type="radio" value="Progetto esecutivo" id="executive" name="categoria" <c:if test="${document.categoria eq 'Progetto esecutivo'}">checked</c:if>></input>Esecutivo</td>
 			<td><select name="tipo" id="epElaborate" <c:if test="${!(document.categoria eq 'Progetto esecutivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto esecutivo') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			<tr id="epg" hidden="true"><td></td>
			<td><input type="radio" id="epgElab" name="sottocategoria" value="Elaborati grafici" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input>Elaborati grafici</td>
			<td><select name="sottotipo" id="epgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="sep" hidden="true"><td></td>
			<td><input type="radio" id="sepElab" name="sottocategoria" value="Progetto esecutivo delle strutture" <c:if test="${document.sottocategoria eq 'Progetto esecutivo delle strutture'}">checked</c:if>></input>Progetto esecutivo delle strutture</td>
			<td><select name="sottotipo"  id="sepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo delle strutture')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo delle strutture') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="iep" hidden="true"><td></td>
			<td><input type="radio" id="iepElab" name="sottocategoria" value="Progetto esecutivo degli impianti" <c:if test="${document.sottocategoria eq 'Progetto esecutivo degli impianti'}">checked</c:if>></input>Progetto esecutivo degli impianti</td>
			<td><select name="sottotipo" id="iepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo degli impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo degli impianti') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="mp" hidden="true"><td></td>
			<td><input type="radio" id="mpElab" name="sottocategoria" value="Piano di manutenzione" <c:if test="${document.sottocategoria eq 'Piano di manutenzione'}">checked</c:if>></input>Piano di manutenzione</td>
			<td><select name="sottotipo" id="mpElaborate" <c:if test="${!(document.sottocategoria eq 'Piano di manutenzione')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Piano di manutenzione') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>	
 			</table>
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			<table>
 			<tr><td style="color:red;">Committenti registrati</td></tr>
 			<tr><td><select name="selectRegisteredCustomer" id="sru">
			<option value="selectCustomer">Scegli committente</option>
			<c:forEach items="${user.committenti}" var="cust">
			    <option value="${cust.idCommittente}" <c:if test="${!(idCust eq 'selectCustomer') && (idCust eq cust.idCommittente)}">selected</c:if>>${cust.cognome} ${surn.nome}</option>
			</c:forEach>
			</select></td></tr>			
					
				<tr>
					<td><div>${document.committente.cf}</div></td>
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