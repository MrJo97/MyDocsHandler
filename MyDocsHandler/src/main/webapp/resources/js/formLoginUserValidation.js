import { checkFormatEmail, checkFormatPasswordForLogin, checkAllowedCharacters } from './functions.js';
$(document).ready(function(email, password)
		{
			var isThePasswordOk;
			var isTheEmailOk;
			
				$("#password").on('keypress', function( event ) {
					return checkAllowedCharacters(event);
				});
				$("#password").on('input change', checkFormatPasswordForLogin);
				
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
				$("#table").on('input change', function(){
					if($("#msgEmail").text() == "")
						{isTheEmailOk=true;}
					else
						{isTheEmailOk=false;}
					console.log($("#msgEmail").text());
					if($("#msgPassword").text() == "" && $("#password").val() != "")
						{isThePasswordOk=true;}
					else
						{isThePasswordOk=false;}
					console.log($("#msgPassword").text());
					console.log("isTheEmailOK e isThePasswordOK:" + isTheEmailOk + isThePasswordOk );
				if(isThePasswordOk && isTheEmailOk)
					$("#login").prop('disabled', false);
				else
					$("#login").prop('disabled', true);
				});
			});