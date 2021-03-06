/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/

import { checkSecurityLevelPassword, checkAllowedCharacters, checkFormatEmail, checkFormatSurname, checkFormatName, checkFormatTel, checkFormatCf, checkFormatUsername } from './functions.js';
/*import { eventHandlerPassword } from './eventHandlers.js';*/
$(document).ready(function()
		{
			var isThePasswordOk;
			var isTheEmailOk;
			var isTheNameOk;
			var isTheSurnameOk;
			var isTheTelOk;
			var isTheCfOk;
			var isTheNickOk;
			
			//controllo password
		
			$("#password").on('keypress', function( event ) {
				return checkAllowedCharacters(event);
			});
			$("#password").on('input',function(){
				checkSecurityLevelPassword($("#password").val());
			});
			
			
			//controllo email (con 'change' l'evento viene triggerato quando l'utente clicca al di fuori del campo di testo)
			$("#email").on('change', checkFormatEmail);
				
			/*onkeyup e onkeydown sono gli eventi che rilevano rispettivamente la pressione
			 *e depressione del tasto, inoltre rilevano anche l'incollatura del testo, 
			cosa che invece keypress non fa. key press rileva semplicemente la pressione dei tasti
			e permette all'utente di non digitare mediante la restituzione di false, cosa che keyup
			e keydown on fanno. Al che in questo caso sarebbe possibile per un utente bypassare il controllo 
			della lunghezza del testo semplicemente incollandolo anziche digitandolo e questo è un problema.
			per ovviare al problema utilizziamo semplicemente la combonazione di eventi keypress e input*/
					
			//controllo nome
			$("#name").on('change', function(){checkFormatName(50)});
				
			//controllo cognome
			$("#surname").on('change', checkFormatSurname);
		
			//controllo telefono
			$("#tel").on('change', checkFormatTel);
						
			//controllo cf
			$("#cf").on('change', checkFormatCf);
						
			//controllo username
			$("#username").on('keypress', function( event ) {
				return checkAllowedCharacters(event);
			});
			$("#username").on('change', checkFormatUsername);
								
			//abilitazione bottone di salvataggio
			$("#table").bind('input change', function(){
				
				if($("#msgUsername").text() == "" && $("#username").val() != "")
					{isTheNickOk=true;}
				else
					{isTheNickOk=false;}
				console.log($("#msgPassword").text());
				if($("#msgPassword").text() == "livello di sicurezza: ottimo" || $("#msgPassword").text() == "livello di sicurezza: buono"  || ($("#msgPassword").text() == "" && $("#password").val() != ""))
					{isThePasswordOk=true;}
				else
					{isThePasswordOk=false;}
				
				if($("#msgEmail").text() == "" && $("#email").val() != "")
					{isTheEmailOk=true;}
				else
					{isTheEmailOk=false;}
				
				if($("#msgName").text() == "" && $("#name").val() != "")
					{isTheNameOk=true;}
				else
					{isTheNameOk=false;}
				
				if($("#msgSurname").text() == "" && $("#surname").val() != "")
					{isTheSurnameOk=true;}
				else
					{isTheSurnameOk=false;}
				
				if($("#msgTel").text() == "" && $("#tel").val() != "")
					{isTheTelOk=true;}
				else
					{isTheTelOk=false;}
				
				if($("#msgCf").text() == "" && $("#cf").val() != "" )
					{isTheCfOk=true;}
				else
					{isTheCfOk=false;}
				
			//console.log($("#msgPassword").text());
				console.log("isThe...Ok: " + isTheEmailOk + isThePasswordOk+ isTheNameOk + isTheSurnameOk + isTheTelOk + isTheCfOk + isTheNickOk );
			if(isThePasswordOk && isTheEmailOk && isTheNameOk && isTheSurnameOk && isTheTelOk && isTheCfOk && isTheNickOk)
				{console.log("abilitazione!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				$("#registration").prop('disabled', false);
			}
			else
				$("#registration").prop('disabled', true);
			});
});