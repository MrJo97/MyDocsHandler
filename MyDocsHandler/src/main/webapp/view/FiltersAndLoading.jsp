<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,webapp.model.*" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<spring:url value="/resources/js/dynamicBlocksForDocumentCategories.js" var="lfs" />
<spring:url value="/resources/css/HomePageStyle.css" var="css" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script>
<script src="${jquery}"></script>
<script src="${lfs}" ></script>
<link href="${css}" rel="stylesheet" />


<!--  <script type="text/javascript" src="http://localhost:8080/WebAppTesi/assets/js/setConstraintsLoading.js"></script>-->


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>


<body>
			<div id="table0">
			<div id="title" align="center" >Parametri di ricerca</div>
			<div id="table00">
		<form action="/MyDocsHandler/searchDocument/search" method="post">
			
			<table>
			<!-- 	<tr>
					<td align="center" colspan="2" id="titolo" style="font-size:125%;color:white;background-color:rgba(0,51,153);">Parametri di ricerca</td>
				</tr>-->
				<tr><td><br/></td></tr>
				<tr style="height:50px;"><td align="center" colspan="2" style="font-size:125%;"><b>Dati documento</b></td></tr>
				<tr>
					<td id="namefileSearch">Nome documento:</td>
					<td><input type="text" name="nome"
						placeholder="Inserisci il nome del file" value="${document.nome}"></input></td>
				</tr>
			</table>
			<br/>
			<div>Fase di progetto:</div>
			<table>
 			<tr>			   
			<td style="vertical-align=:center;"><input  type="radio" value="Progetto preliminare" id="preliminary" name="categoria"
			 <c:if test="${document.categoria eq 'Progetto preliminare'}">checked</c:if>></input></td><td>Preliminare <br /> Art.17, comma1, lettera:</td>
				<td><select name="tipo" id="ppElaborate" <c:if test="${!(document.categoria eq 'Progetto preliminare')}">disabled</c:if>>		   
			    <c:forTokens items="a,b,c,d,e,f,g,h,i" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto preliminare') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
		
			<tr id="pw" hidden="true"><td></td><td></td>
			<td style="vertical-align=:center;"><input type="radio" id="pwElab" name="sottocategoria" value="Opere e lavori puntuali" id="pwElab" <c:if test="${document.sottocategoria eq 'Opere e lavori puntuali'}">checked</c:if>></input></td><td>Opere e lavori puntuali <br /> Art.21, comma1, lettera a, numero:</td>
			<td><select name="sottotipo" id="pwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori puntuali')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori puntuali') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>			   
			    </select>
			</td>
			</tr>
			
			
			<tr id="nw" hidden="true"><td></td><td></td>
			<td style="vertical-align:center;"><input type="radio" id="nwElab" name="sottocategoria" value="Opere e lavori a rete" <c:if test="${document.sottocategoria eq 'Opere e lavori a rete'}">checked</c:if>></input></td><td>Opere e lavori a rete <br /> Art.21, comma1, lettera b, numero:</td>
			<td><select name="sottotipo" id="nwElaborate" <c:if test="${!(document.sottocategoria eq 'Opere e lavori a rete')}">disabled</c:if>>
			    <c:forTokens items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere e lavori a rete') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			<!--  </table> -->
 			
 						
 						
			<!-- progetto definitivo -->
 			<!-- <table>-->
 			<tr><td style="verical-align:center;"><input type="radio" value="Progetto definitivo" id="definitive" name="categoria" <c:if test="${document.categoria eq 'Progetto definitivo'}">checked</c:if>></input></td><td>Definitivo <br /> Art.24, comma2, lettera:</td>
 			<td><select name="tipo" id="dpElaborate" <c:if test="${!(document.categoria eq 'Progetto definitivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto definitivo') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			
			<tr id="tr" hidden="true"><td></td><td></td>
			<td><input type="radio" id="techRep" name="sottocategoria" value="Relazioni tecniche" <c:if test="${document.sottocategoria eq 'Relazioni tecniche'}">checked</c:if>></input></td><td>Relazioni tecniche <br /> Art.26, comma1, lettera:</td>
			<td><select name="sottotipo" id="technicalReport" <c:if test="${!(document.sottocategoria eq 'Relazioni tecniche')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Relazioni tecniche') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="dpg" hidden="true"><td></td><td></td>
			<td><input type="radio" id="dpgElab" name="sottocategoria" value="Elaborati grafici" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input></td><td>Elaborati grafici <br /> Art.28, comma2, lettera:</td>
			<td><select name="sottotipo" id="dpgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			
			<tr id="si" hidden="true"><td></td><td></td>
			<td><input type="radio" id="siElab" name="sottocategoria" value="Studi e indagini" <c:if test="${document.sottocategoria eq 'Studi e indagini'}">checked</c:if>></input></td><td>Studi e indagini <br /> Art.28, comma5, lettera:</td>
 			<td><select name="sottotipo" id="siElaborate" <c:if test="${!(document.sottocategoria eq 'Studi e indagini')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o,p,q,r" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Studi e indagini') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			 </tr>
			 
			 
			 <tr id="artwork" hidden="true"><td></td><td></td>
			    <td><input type="radio" id="artworkElab" name="sottocategoria" value="Opere d&#39;arte" <c:if test="${document.sottocategoria eq 'Opere d&#39;arte'}">checked</c:if>></input></td><td>Opere d'arte <br /> Art.28, comma5, lettera:</td>
 				<td><select name="sottotipo" id="artworkElaborate" <c:if test="${!(document.sottocategoria eq 'Opere d&#39;arte')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere d&#39;arte') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			
			<tr id="lei" hidden="true"><td></td><td></td>
				<td><input type="radio" id="leiElab" name="sottocategoria" value="Inserimenti paesaggistici ed ambientali" <c:if test="${document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali'}">checked</c:if>></input></td><td>Inserimenti paesaggistici ed ambientali  <br /> Art.28, comma5, lettera:</td>
 				<td><select name="sottotipo" id="leiElaborate" <c:if test="${!(document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			 </td>
			 </tr>
			 
			 
			  <tr id="implant" hidden="true"><td></td><td></td>
			    <td><input type="radio" id="implantElab" name="sottocategoria" value="Impianti" <c:if test="${document.sottocategoria eq 'Impianti'}">checked</c:if>></input></td><td>Impianti <br /> Art.28, comma5, lettera:</td>
 				<td><select name="sottotipo" id="implantElaborate" <c:if test="${!(document.sottocategoria eq 'Impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Impianti') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			    </tr>
			    
			    
			 <tr id="qss" hidden="true"><td></td><td></td>
			    <td><input type="radio" id="qssElab" name="sottocategoria" value="Siti di cava e deposito" <c:if test="${document.sottocategoria eq 'Siti di cava e deposito'}">checked</c:if>></input></td><td>Siti di cava e deposito <br /> Art.28, comma5, lettera:</td>
 				<td><select name="sottotipo" id="qssElaborate" <c:if test="${!(document.sottocategoria eq 'Siti di cava e deposito')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Siti di cava e deposito') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			<!-- </table>-->
 			
 						
			<!-- progetto esecutivo -->
 			<!-- <table>-->
 			<tr><td style="verical-align:center;"><input type="radio" value="Progetto esecutivo" id="executive" name="categoria" <c:if test="${document.categoria eq 'Progetto esecutivo'}">checked</c:if>></input></td><td>Esecutivo <br /> Art.33, comma1, lettera:</td>
 			<td><select name="epElaborate" id="epElaborate" <c:if test="${!(document.categoria eq 'Progetto esecutivo')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.categoria eq 'Progetto esecutivo') && (document.tipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			</td>
			</tr>
			<tr id="epg" hidden="true"><td></td><td></td>
			<td><input type="radio" id="epgElab" name="sottocategoria" value="Elaborati grafici" <c:if test="${document.sottocategoria eq 'Elaborati grafici'}">checked</c:if>></input></td><td>Elaborati grafici<br /> Art.36, comma1, lettera:</td>
			<td><select name="sottotipo" id="epgElaborate" <c:if test="${!(document.sottocategoria eq 'Elaborati grafici')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Elaborati grafici') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="sep" hidden="true"><td></td><td></td>
			<td><input type="radio" id="sepElab" name="sottocategoria" value="Progetto esecutivo delle strutture" <c:if test="${document.sottocategoria eq 'Progetto esecutivo delle strutture'}">checked</c:if>></input></td><td>Progetto esecutivo delle strutture<br /> Art.37, comma6, lettera:</td>
			<td><select name="sottotipo"  id="sepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo delle strutture')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo delle strutture') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="iep" hidden="true"><td></td><td></td>
			<td><input type="radio" id="iepElab" name="sottocategoria" value="Progetto esecutivo degli impianti" <c:if test="${document.sottocategoria eq 'Progetto esecutivo degli impianti'}">checked</c:if>></input></td><td>Progetto esecutivo degli impianti<br /> Art.37, comma8, lettera:</td>
			<td><select name="sottotipo" id="iepElaborate" <c:if test="${!(document.sottocategoria eq 'Progetto esecutivo degli impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Progetto esecutivo degli impianti') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>
			
			<tr id="mp" hidden="true"><td></td><td></td>
			<td><input type="radio" id="mpElab" name="sottocategoria" value="Piano di manutenzione" <c:if test="${document.sottocategoria eq 'Piano di manutenzione'}">checked</c:if>></input></td><td>Piano di manutenzione<br /> Art.38, comma2, lettera:</td>
			<td><select name="sottotipo" id="mpElaborate" <c:if test="${!(document.sottocategoria eq 'Piano di manutenzione')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Piano di manutenzione') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select></td>
			</tr>	
 			</table>
 			
 			
 			
 			<table>
 			<tr><td><br /></td></tr>
 			<tr><td><br /></td></tr>
 			<tr style="height:50px;"><td align="center" colspan="2" style="font-size:125%;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Committenti registrati</b></td></tr>
 			<tr><td><select name="selectRegisteredCustomer" id="sru">
			<option value="selectCustomer">Scegli committente</option>
			<c:forEach items="${user.committenti}" var="cust">
			    <option value="${cust.idCommittente}" <c:if test="${!(idCust eq 'selectCustomer') && (idCust eq cust.idCommittente)}">selected</c:if>>${cust.cognome} ${surn.nome}</option>
			</c:forEach>
			</select>
				</td><td></td></tr>			
				<tr><td><br /></td></tr>
				<tr><td><br /></td></tr>
				<tr>
					<td align="center"><input type="reset" name="reset"
						value="Reset"></input></td>
					<td align="center"><input type="submit" name="cerca"
						value="Cerca"></input></td>
				</tr>
		 </table>

		</form>

<br/>
<br/>

		<form action="/MyDocsHandler/fileupload/checkFile" method="post" enctype="multipart/form-data">
		<div align="center" style="height:4vh;font-size:125%;color:white;background-color:rgba(0,51,153);" id="titolo1" >Caricamento file</div>
			<table id="tabella1">
				<!-- <tr>
					<td align="center" colspan="2" style="font-size:125%;color:red;" id="titolo1">Caricamento file</td>
				</tr>-->
				<tr><td><br/></td></tr>
				<tr>
					<td id="fields">Percorso file:</td>
					<td id="reference"><input type="file" id="insertFile"
						name="file" placeholder="Inserisci il percorso" ></input></td>
				</tr>
				<tr><td><br /></td></tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" name="carica"
						value="Carica" id="load"></input></td>
				</tr>
			</table>
			<div style="color:red;">${msg}</div>

		</form>
		<br/>
		
	<div id="logout">
	<!--<form action="/MyDocsHandler/logout" method="post"> -->
	  <table>
       <tr>
        <td><input type="submit" name="logout" value="Log Out" id="logout" onclick='logoutRedirect("user")'></input></td>
       </tr>        
      </table>
	<!-- </form> -->
	</div>
	
</div>
</div>
 </body>
</html>