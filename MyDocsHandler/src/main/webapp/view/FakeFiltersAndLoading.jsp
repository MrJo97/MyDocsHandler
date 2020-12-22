<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="table0">
			<div id="title" align="center" >Parametri di ricerca</div>
			<div id="table00">
		<form action="/MyDocsHandler/searchDocument/search" method="post">
			<table>
				<tr>
					<td align="center" colspan="2" id="titolo" style="font-size:125%;color:red;">Parametri di ricerca</td>
				</tr>
				<tr><td><br/></td></tr>
				<tr><td colspan="2" style="color:red;">Dati documento</td></tr>
				<tr>
					<td id="">Nome documento:</td>
					<td><input type="text" name=""
						placeholder="Inserisci il nome del file" disabled></input></td>
				</tr>
				<tr>
					<td colspan="2" id="fields">Fase di progetto:</td>
				</tr>
			<!-- progetto preliminare -->
 			<!--  <table  id="pp">-->
 			<tr><td><input type="radio" value="" id="" name="" disabled></input>Preliminare</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			   </select>
			</td>
			</tr>
			
			
			
 			</table>
 			
 						
 						
			<!-- progetto definitivo -->
 			<table>
 			<tr><td><input type="radio" value="" id="" name="" disabled></input>Definitivo</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			    </select>
			</td>
			</tr>
 			</table>
 			
 						
			<!-- progetto esecutivo -->
 			<table>
 			<tr><td><input type="radio" value="" id="" name="" disabled></input>Esecutivo</td>
 			<td><select name="" id="" disabled>
			    <option  value=""> a</option>
			    </select>
			</td>
			</tr>
			
			<tr></tr>
 			</table>
 			
 			<table>
 			<tr><td style="color:red;">Committenti registrati</td></tr>
 			<tr><td><select name="" id="" disabled>
			<option value="">Scegli committente</option>
			</select></td></tr>			
					
				<tr>
					<td></td>
					<td><input type="reset" name=""
						value="Reset" disabled></input><input type="submit" name=""
						value="Cerca" disabled></input></td>
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
					<td><input id="fields" type="text" id=""
						name="file" placeholder="Inserisci il percorso" disabled></input></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="submit" name=""
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