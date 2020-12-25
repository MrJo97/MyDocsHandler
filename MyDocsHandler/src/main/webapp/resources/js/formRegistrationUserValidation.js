/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/

import { checkSecurityLevelPassword, checkFormatEmail } from './functions.js';
$(document).ready(function()
		{
			var isThePasswordOk;
			var isTheEmailOk;
						
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
					
			//abilitazione bottone di registrazione
			$("#table").bind('change', function(){
				if($("#msgPassword").text() == "livello di sicurezza: ottimo" || $("#msgPassword").text() == "livello di sicurezza: buono"  || ($("#msgPassword").text() == "" && $("#password").val() != ""))
				{isThePasswordOk=true;}
			else
				{isThePasswordOk=false;}
			
			if($("#msgEmail").text() == "" && $("#email").val() != "")
				{isTheEmailOk=true;}
			else
				{isTheEmailOk=false;}
				console.log("isTheEmailOK e isThePasswordOK:" + isTheEmailOk + isThePasswordOk );
			if(isThePasswordOk && isTheEmailOk)
				$("#registration").prop('disabled', false);
			else
				$("#registration").prop('disabled', true);
			});
});