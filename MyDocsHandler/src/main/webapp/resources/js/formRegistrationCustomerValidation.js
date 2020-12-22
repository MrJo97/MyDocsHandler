/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/

import { checkSecurityLevelPassword, checkEmail, checkSimpleText, checkTel, checkCf, checkUsername } from './functions.js';
/*import { eventHandlerPassword } from './eventHandlers.js';*/
$(function(email, password)
		{
			var isThePasswordOk;
			var isTheEmailOk;
			var isTheNameOk;
			var isTheSurnameOk;
			var isTheTelOk;
			var isTheCfOk;
			var isTheNickOk;
			
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
					$(this).val($(this).val().substr(0,254));
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
			
			
			
			//controllo nome
			$("#name").on('input change', function(){
				isTheNameOk = checkSimpleText($(this).val());
				console.log(isTheNameOk);
				console.log("Lunghezza nome:" + $(this).val());
				if(!checkSimpleText($(this).val()))
				{
					$("#msgName").text("Formato nome non valido!");
					$("#msgName").css("color", "red");
					isTheNameOk=false;
				}
				else if($(this).val().length<3)
				{
					$("#msgName").text("Il nome deve contenere almeno 3 caratteri!");
					$("#msgName").css("color", "red");
					isTheNameOk=false;
				}
				else
				{	
					$("#msgName").text("");
					isTheNameOk=true;
				}
				console.log("isTheNameOk: " + isTheNameOk);

			});
				$("#name").on('input keypress', function( event ) {
					console.log($(this).val().length);	
					console.log(!checkSimpleText($(this).val()));
					if($(this).val().length==51)
						{
							$(this).val($(this).val().substr(0,50));
							$("#msgName").text("Il nome deve contenere un massimo di 50 caratteri!");
							$("#msgName").css("color", "red");
							return false;}
					else
					{
						$("#msgName").text("");
						return true;
					}
				});
				
				//controllo cognome
				$("#surname").on('input change', function(){
					isTheSurnameOk = checkSimpleText($(this).val());
					console.log(isTheSurnameOk);
					console.log("Lunghezza cognome:" + $(this).val());
					if(!checkSimpleText($(this).val()))
					{
						$("#msgSurname").text("Formato cognome non valido!");
						$("#msgSurname").css("color", "red");
						isTheSurnameOk=false;
					}
					else if($(this).val().length<3)
					{
						$("#msgSurname").text("Il cognome deve contenere almeno 3 caratteri!");
						$("#msgSurname").css("color", "red");
						isTheSurnameOk=false;
					}
					else
					{	
						$("#msgSurname").text("");
						isTheSurnameOk=true;
					}
					console.log("isTheSurnameOk: " + isTheSurnameOk);

				});
					$("#surname").on('input keypress', function( event ) {
						console.log($(this).val().length);	
						console.log(!checkSimpleText($(this).val()));
						if($(this).val().length==51)
							{
								$(this).val($(this).val().substr(0,50));
								$("#msgSurname").text("Il cognome deve contenere un massimo di 50 caratteri!");
								$("#msgSurname").css("color", "red");
								return false;
							}
						else
							{
								$("#msgSurname").text("");
								return true;
							}
					});
					
					//controllo telefono
					$("#tel").on('input change', function(){
						isTheTelOk = checkTel($(this).val());
						console.log(isTheTelOk);
						console.log("Lunghezza telefono:" + $(this).val());
						if(!checkTel($(this).val()))
						{
							$("#msgTel").text("Formato non valido!");
							$("#msgTel").css("color", "red");
							isTheTelOk=false;
						}
						else if($(this).val().length<9)
						{
							$("#msgTel").text("Il numero di telefono deve contenere almeno 9 numeri!");
							$("#msgTel").css("color", "red");
							isTheTelOk=false;
						}
						else
						{	
							$("#msgTel").text("");
							isTheTelOk=true;
						}
						console.log("isTheTelOk: " + isTheTelOk);

					});
						$("#tel").on('input keypress', function( event ) {
							console.log($(this).val().length);	
							console.log(!checkTel($(this).val()));
							if($(this).val().length==16)
								{
									$(this).val($(this).val().substr(0,15));
									$("#msgTel").text("Il numero di telefono deve contenere un massimo di 15 caratteri!");
									$("#msgTel").css("color", "red");
									return false;}
							else
								{
									$("#msgTel").text("");
									return true;
								}
						});
						
						//controllo cf
						$("#cf").on('input change', function(){
							isTheCfOk = checkCf($(this).val());
							console.log(isTheCfOk);
							console.log("Lunghezza cf:" + $(this).val());
							if(!checkCf($(this).val()))
							{
								$("#msgCf").text("Formato non valido!");
								$("#msgCf").css("color", "red");
								isTheCfOk=false;
							}
							else if($(this).val().length!=16)
							{
								$("#msgCf").text("Il codice fiscale deve contenere esattamente 16 caratteri alfanumerici!");
								$("#msgCf").css("color", "red");
								isTheCfOk=false;
							}
							else
							{	
								$("#msgCf").text("");
								isTheCfOk=true;
							}
							console.log("isTheCfOk: " + isTheCfOk);

						});
							$("#cf").on('input keypress', function( event ) {
								console.log($(this).val().length);	
								console.log(checkCf($(this).val()));
								if($(this).val().length==17)
									{
									//var str = $(this).val().substr(0,16);
									$(this).val($(this).val().substr(0,16));
									//$("#cf").text(sub);
									/*console.log($("#cf").val().substring(0,16));
									console.log($("#cf").val());
									console.log(typeof $(this).val());*/
								     $("#msgCf").text("Il codice fiscale non può contenere più di 16 caratteri alfanumerici!");
									 $("#msgCf").css("color", "red");
									 return false;
									}
								else
									{
										$("#msgCf").text("");
										return true;
									}									
							});
							
							//controllo username
							$("#username").on('input change', function(){
								isTheNickOk = checkUsername($(this).val());
								console.log(isTheNickOk);
								console.log("Lunghezza username:" + $(this).val());
								if(!checkUsername($(this).val()))
								{
									$("#msgUsername").text("Formato username non valido!");
									$("#msgUsername").css("color", "red");
									$("#msg1").text("Sono consentite le lettere accentate ed i simboli:  @ ! # $ % ' - / = ^ \ _ ` { } ~ +  ) ");
									$("#msg1").css("color", "#991199");
									isTheNickOk=false;
									
								}
								else if($(this).val().length<3)
								{
									$("#msgUsername").text("Lo username deve contenere almeno 3 caratteri!");
									$("#msgUsername").css("color", "red");
									$("#msg1").text("");
									isTheNickOk=false;
								}
								else
								{	
									$("#msgUsername").text("");
									$("#msg1").text("");
									isTheNickOk=true;
								}
								console.log("isTheNickOk: " + isTheNickOk);

							});
								$("#username").on('input keypress', function( event ) {
									console.log($(this).val().length);	
									console.log(!checkUsername($(this).val()));
									if($(this).val().length==16)
										{
											$(this).val($(this).val().substr(0,15));
											$("#msgUsername").text("Lo username deve contenere un massimo di 15 caratteri!");
											$("#msgUsername").css("color", "red");
											return false;
										}
									else
										{
											$("#msgUsername").text("");
											return true;
										}
								});
								
			//abilitazione bottone di salvataggio
			$("#table").bind('input change', function(){
				
				if($("#msgUsername").text() == "")
					{isTheNickOk=true;}
				else
					{isTheNickOk=false;}
				
				if($("#msgPassword").text() == "")
					{isThePasswordOk=true;}
				else
					{isThePasswordOk=false;}
				
				if($("#msgEmail").text() == "")
					{isTheEmailOk=true;}
				else
					{isTheEmailOk=false;}
				
				if($("#msgName").text() == "")
					{isTheNameOk=true;}
				else
					{isTheNameOk=false;}
				
				if($("#msgSurname").text() == "")
					{isTheSurnameOk=true;}
				else
					{isTheSurnameOk=false;}
				
				if($("#msgTel").text() == "")
					{isTheTelOk=true;}
				else
					{isTheTelOk=false;}
				
				if($("#msgCf").text() == "")
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