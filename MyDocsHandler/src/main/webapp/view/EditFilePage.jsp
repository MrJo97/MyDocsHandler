<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="webapp.model.*,java.util.List" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!--<spring:url value="/resources/js/setConstraintFields1.js" var="scf" />-->
<spring:url value="/resources/js/dynamicBlocksForDocumentCategories.js" var="lfs" />
<spring:url value="/resources/js/formEditFileValidation.js" var="scf" />
<spring:url value="/resources/js/jquery-3.5.0.min.js" var="jquery" />
<spring:url value="/resources/css2/DetailsAndEditPagesStyle.css" var="css" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.0.min.js'/>"></script> 
<!-- <script src="${scf}" ></script>-->
<script src="${lfs}" ></script>
<script src="${scf}" type="module" ></script>
<script src="${jquery}" ></script>
<link href="${css}" rel="stylesheet" />


<title>Insert title here</title>

</head>

<body>

<%@ include file="HomePage.jsp"%>

<div id="cssStyle">
<div style="margin-left: 10px;margin-top: 10px;">		

		<form <c:choose>
			<c:when test="${operation eq 'editing'}">action="/MyDocsHandler/editFile/edit${idDoc}"</c:when>
			<c:otherwise>action="/MyDocsHandler/fileupload/upload"</c:otherwise>
		</c:choose>  method="post" enctype="multipart/form-data">
			
			<table id="tabella3">
			<tr style="height:50px;"><td style="font-size:125%;" colspan="2"><b>Dati documento</b></td></tr>
			<tr><td>Nome file: </td><td><input type="text" name="nome" id="name" value="${document.nome}"/></td><td><div id="msgName"></div></td></tr>
			<tr><td>Descrizione:</td><td ><textarea name="descrizione" id="description" rows="6" cols="50">${document.descrizione}</textarea></td><td><div id="msgDescription"></div></td></tr>
			<tr><td colspan="2" style="color:#800000;">			
			</td></tr>
			<tr><td></td></tr>
			</table>
			 			
 			<div>Fase di progetto:</div>
 			
 			<!-- progetto preliminare -->
 			<table  id="pp">
 			
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
			<td><input type="radio" id="pwElab" name="sottocategoria" value="Opere e lavori puntuali" id="pwElab" <c:if test="${document.sottocategoria eq 'Opere e lavori puntuali'}">checked</c:if>></input>Opere e lavori puntuali</td>
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
			<tr id="si" hidden="true"><td></td>
			<td><input type="radio" id="siElab" name="sottocategoria" value="Studi e indagini" <c:if test="${document.sottocategoria eq 'Studi e indagini'}">checked</c:if>></input>Studi e indagini</td>
 			<td><select name="sottotipo" id="siElaborate" <c:if test="${!(document.sottocategoria eq 'Studi e indagini')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d,e,f,g,h,i,l,m,n,o,p,q,r" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Studi e indagini') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			 </tr>
			 
			 
			 <tr id="artwork" hidden="true"><td></td>
			    <td><input type="radio" id="artworkElab" name="sottocategoria" value="Opere d&#39;arte" <c:if test="${document.sottocategoria eq 'Opere d&#39;arte'}">checked</c:if>></input>Opere d'arte</td>
 				<td><select name="sottotipo" id="artworkElaborate" <c:if test="${!(document.sottocategoria eq 'Opere d&#39;arte')}">disabled</c:if>>
			    <c:forTokens items="a,b,c,d" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Opere d&#39;arte') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
			
			<tr id="lei" hidden="true"><td></td>
				<td><input type="radio" id="leiElab" name="sottocategoria" value="Inserimenti paesaggistici ed ambientali" <c:if test="${document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali'}">checked</c:if>></input>Inserimenti paesaggistici ed ambientali</td>
 				<td><select name="sottotipo" id="leiElaborate" <c:if test="${!(document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Inserimenti paesaggistici ed ambientali') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			 </td>
			 </tr>
			 
			 
			 <tr id="implant" hidden="true"><td></td>
			    <td><input type="radio" id="implantElab" name="sottocategoria" value="Impianti" <c:if test="${document.sottocategoria eq 'Impianti'}">checked</c:if>></input>Impianti</td>
 				<td><select name="sottotipo" id="implantElaborate" <c:if test="${!(document.sottocategoria eq 'Impianti')}">disabled</c:if>>
			    <c:forTokens items="a,b,c" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Impianti') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			    </tr>
			    
			    
			 <tr id="qss" hidden="true"><td></td>
			    <td><input type="radio" id="qssElab" name="sottocategoria" value="Siti di cava e deposito" <c:if test="${document.sottocategoria eq 'Siti di cava e deposito'}">checked</c:if>></input>Siti di cava e deposito</td>
 				<td><select name="sottotipo" id="qssElaborate" <c:if test="${!(document.sottocategoria eq 'Siti di cava e deposito')}">disabled</c:if>>
			    <c:forTokens items="a,b" delims="," var="character">
			    <option value="${character}" <c:if test="${(document.sottocategoria eq 'Siti di cava e deposito') && (document.sottotipo eq character) }">selected</c:if>>${character}</option>
			    </c:forTokens>
			    </select>
			    </td>
			</tr>
 			</table>
 			
 			
 			
 			
 						
			<!-- progetto esecutivo -->
 			<table>
 			<tr><td><input type="radio" value="Progetto esecutivo" id="executive" name="categoria" <c:if test="${document.categoria eq 'Progetto esecutivo'}">checked</c:if>></input>Esecutivo</td>
 			<td><select name="epElaborate" id="epElaborate" <c:if test="${!(document.categoria eq 'Progetto esecutivo')}">disabled</c:if>>
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

			<br />
			<!-- lista committenti registrati -->
			<table>			
			<tr style="height:50px;"><td style="font-size:125%;"><b>Committenti registrati</b></td></tr>
			<tr><td><select name="selectRegisteredCustomer" id="sru">
			<option value="selectCustomer">Scegli committente</option>
			<c:forEach items="${user.committenti}" var="cust">
			    <option value="${cust.idCommittente}" <c:if test="${(registeredCustomer.nome eq cust.nome) && (registeredCustomer.cognome eq cust.cognome)}">selected</c:if>>${cust.cognome} ${cust.nome}</option>
			</c:forEach>
			</select></td></tr>
			</table>
			
			<br />
			<!-- campi dati committente -->
			<table>
			<tr style="height:50px;"><td style="font-size:125%;" colspan="2"><b>Nuovo committente</b></td></tr>
			<!-- <tr><td>Nome </td><td><input value="${newCustomer.nome}" type="text" name="nome" id="nC" /></td><td><div style="color:red;" id="warning2"></div></td></tr>
			<tr><td>Cognome </td><td><input value="${newCustomer.cognome}" type="text" name="cognome" id="sC" /></td><td><div style="color:red;" id="warning3"></div></td></tr>-->
			<tr><td>CF </td><td><input value="${newCustomer.cf}" type="text" name="cf" id="cf" /></td><td><div id="msgCf"></div></td></tr>
			<!-- <tr><td>Telefono (1)</td><td><input value="${newCustomer.recapiti.get(0).telefono}" type="text" name="telefono" id="tel1" /></td><td><div style="color:red;" id="warning4"></div></td></tr>
			<tr><td>Telefono (2)</td><td><input value="${newCustomer.recapiti.get(1).telefono}" type="text" name="telefono1" id="tel2" /></td><td></td></tr>
			<tr><td>Email (1)</td><td><input value="${newCustomer.recapiti.get(0).email}" type="text" name="email" id="email1" /></td><td><div style="color:red;" id="warning5"></div></td></tr>
			<tr><td>Email (2)</td><td><input value="${newCustomer.recapiti.get(1).email}" type="text" name="email1" id="email2" /></td><td></td></tr>-->
			
			
			
			<tr>
			<td><input type="reset" value="Reset" id="reset"/></td>
			<td><input type="submit" value="Salva Modifiche" name="save" id="SM" disabled/></td><!-- disabled -->
			</tr>
			</table>
		</form>
		
		<p style="color:red;">${msg1}</p><!-- file già presente -->
		<p style="color:red;">${msg2}</p><!-- committente già presente -->
		<p style="color:red;">${msg3}</p><!-- il committente associato a questo cf non si è ancora registrato -->
</div>
</div>
</body>
</html>