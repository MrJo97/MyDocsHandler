/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/

import { checkSecurityLevelPassword, checkEmail } from './functions.js';
$(function(email, password)
		{
			var isThePasswordOk;
			var isTheEmailOk;
						
			//controllo password
			$("#password").on('input',function(){
				$(this).on('keypress', function( event ) {
			
					var charCode=(event.which)?event.which:event.keyCode;
					console.log($(this).val().length +1);
					if(charCode===34 || charCode===38 || (charCode>=40 && charCode<=46 && charCode!=43 && charCode!=45)
							|| (charCode>=58 && charCode<=63 && charCode!=61) || charCode===91 || charCode===93 
							|| charCode===124 || charCode>126)
						{$("#msg1").text("Sono consentite le lettere accentate ed i simboli:  @ ! # $ % ' - / = ^ \ _ ` { } ~ +  ) ");
						$("#msg1").css("color", "#991199");
						return false;
						}
					else if(($(this).val().length+1) == 60)//devo impedire di digitare dopo il 60 esimo
						return false
					else
						return true;
				});
				isThePasswordOk = checkSecurityLevelPassword($(this).val());
				console.log("isThePasswordOK: " + isThePasswordOk);
			});
			
			
			//controllo email (con 'change' l'evento viene triggerato quando l'utente clicca al di fuori del campo di testo)
			$("#email").on('input change', function(){
				isTheEmailOk = checkEmail($(this).val());
				console.log("isTheEmailOK:" + isTheEmailOk);
				//console.log($(this).val().length+1);	
				if(!isTheEmailOk)
				{
					$("#msgEmail").text("Formato dell'email non valido!");
					$("#msgEmail").css("color", "red");
					isTheEmailOk=false;
				}
				else if($(this).val().length<3)
				{
					$("#msgEmail").text("L'email deve contenere almeno 3 caratteri!");
					$("#msgEmail").css("color", "red");
					isTheEmailOk=false;
				}
				else
				{	
					$("#msgEmail").text("");
					isTheEmailOk=true;
				}
				console.log("isTheEmailOk: " + isTheEmailOk);
			});
			/*onkeyup e onkeydown sono gli eventi che rilevano rispettivamente la pressione
			 *e depressione del tasto, inoltre rilevano anche l'incollatura del testo, 
			cosa che invece keypress non fa. key press rileva semplicemente la pressione dei tasti
			e permette all'utente di non digitare mediante la restituzione di false, cosa che keyup
			e keydown on fanno. Al che in questo caso sarebbe possibile per un utente bypassare il controllo 
			della lunghezza del testo semplicemente incollandolo anziche digitandolo e questo è un problema.
			per ovviare al problema utilizziamo semplicemente la combonazione di eventi keypress e input*/
			$("#email").on('input keypress', function( event ) {
			    console.log($(this).val().length);		
				if($(this).val().length==255)
					{
					$("#email").text($(this).val().substr(0,254));
					$("#msgEmail").text("L'email deve contenere un massimo di 254 caratteri!");
					$("#msgEmail").css("color", "red");
					return false;
					}
				else
				{
					$("#msgEmail").text("");
					return true;
				}
				});
							
			
			
			//abilitazione bottone di registrazione
			$("#table").bind('input', function(){
				console.log("isTheEmailOK e isThePasswordOK:" + isTheEmailOk + isThePasswordOk );
			if(isThePasswordOk && isTheEmailOk)
				$("#registration").prop('disabled', false);
			else
				$("#registration").prop('disabled', true);
			});
});