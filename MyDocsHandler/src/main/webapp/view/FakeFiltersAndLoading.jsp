<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/HomePageStyle.css" var="css" />
<link href="${css}" rel="stylesheet" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="table0">
			<div id="title" align="center" >Parametri di ricerca</div>
			<div id="table00">
		<form action="/MyDocsHandler/searchDocument/search" method="post">
			<table>
				
				<tr><td><br/></td></tr>
				<tr style="height:50px;"><td align="center" colspan="2" style="font-size:125%;"><b>Dati documento</b></td></tr>
				<tr>
					<td id="">Nome documento:</td>
					<td><input type="text" name=""
						placeholder="Inserisci il nome del file" disabled></input></td>
				</tr>
				</table>
			<br/>
			<div>Fase di progetto:</div>
			<table>
			<!-- progetto preliminare -->
 			<!--  <table  id="pp">-->
 			<tr><td style="vertical-align:center;"><input type="radio" value="" id="" name="" disabled></input></td><td>Preliminare <br /> Art.17, comma1, lettera:</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			   </select>
			</td>
			</tr>
			
			
			
 			
 			
 						
 						
			<!-- progetto definitivo -->
 			
 			<tr><td style="vertical-align:center;"><input type="radio" value="" id="" name="" disabled></input></td><td>Definitivo <br /> Art.24, comma2, lettera:</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			    </select>
			</td>
			</tr>
 			
 			
 						
			<!-- progetto esecutivo -->
 			
 			<tr><td style="vertical-align:center;"><input type="radio" value="" id="" name="" disabled></input></td><td>Esecutivo <br /> Art.33, comma1, lettera:</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			    </select>
			</td>
			</tr>
			
			</table>
 			
 			
 			
 			<table>
 			
 			<tr><td><br /></td></tr>
 			<tr><td><br /></td></tr>
 			<tr style="height:50px;"><td align="center" colspan="2" style="font-size:125%;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Committenti registrati</b></td></tr>
 			<tr><td><select name="" id="" disabled>
			<option value="">Scegli committente</option>
			</select>
				</td><td></td></tr>			
				<tr><td><br /></td></tr>
				<tr><td><br /></td></tr>
				<tr>
					<td align="center"><input type="reset" name=""
						value="Reset" disabled></input></td>
					<td align="center"><input type="submit" name=""
						value="Cerca" disabled></input></td>
				</tr>
			</table>
		</form>
<br/>
<br/>

		<form action="/MyDocsHandlerNew/fileupload/checkFile" method="post" enctype="multipart/form-data">
		<div align="center" style="height:4vh;font-size:125%;color:white;background-color:rgba(0,51,153);" id="titolo1" >Caricamento file</div>	
			<table id="tabella1">
				<!--<tr>
					<td align="center" colspan="2" id="titolo1">Caricamento file</td>
				</tr>-->
				<tr><td><br/></td></tr>
				<tr>
					<td id="fields">Percorso file:</td>
					<td><input id="fields" type="text" id=""
						name="file" placeholder="Inserisci il percorso" disabled></input></td>
				</tr>
				<tr><td><br /></td></tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" name=""
						value="Carica" id="" disabled></input></td>
				</tr>
			</table>
			<div style="color:red;">${msg}</div>

		</form>
		<br/>
		
	<div id="logout">
	<form action="/MyDocsHandlerNew/Logout"
			method="post">
	  <table>
       <tr>
        <td><input type="submit" name="logout" value="Log Out" id="logout" disabled></input></td>
       </tr>        
      </table>
	</form>
	</div>
</div>
</div>
	
	
</body>
</html>