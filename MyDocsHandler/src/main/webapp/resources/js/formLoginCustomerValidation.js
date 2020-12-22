import { checkUsername, checkFormatUsername, checkFormatPasswordForLogin } from './functions.js';
$(function(email, password)
		{
			var isThePasswordOk;
			var isTheNickOk;
			
			//controllo password
			//$("#password").on('input paste',function(){
				$("#password").on('input keypress', function( event ) {
					return checkFormatPasswordForLogin(event);
			/*
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
						return false;
					else if(($(this).val().length) == 0)
					{$("#msgPassword").text("Digitare la password.");
					$("#msgPassword").css("color", "red");
					isThePasswordOk=false;
					}
					else
						{$("#msg1").text("");
						$("#msgPassword").text("");
						isThePasswordOk=true;
						return true;
						}
				});*/
			});
			
			
			//controllo username
			$("#username").on('input change', checkFormatUsername);
					/*function(){
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
				else if($(this).val().length<1)
				{
					$("#msgUsername").text("Digitare il proprio username!");
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

			});*/
				/*$("#username").on('input keypress', function( event ) {
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
				});*/
				
				//abilitazione bottone di login
				$("#table").on('input change', function(){
				//	console.log("isTheEmailOK e isThePasswordOK:" + isTheEmailOk + isThePasswordOk+ isTheNameOk + isTheSurnameOk + isTheTelOk + isTheCfOk + isTheNickOk );
					if($("#msgUsername").text() == "")
					{isTheNickOk=true;}
				else
					{isTheNickOk=false;}
				console.log($("#msgEmail").text());
				if($("#msgPassword").text() == "")
					{isThePasswordOk=true;}
				else
					{isThePasswordOk=false;}
				console.log($("#msgPassword").text());
				
					if(isTheNickOk && isThePasswordOk)
					{console.log("abilitazione!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					$("#login").prop('disabled', false);
				}
				else
					$("#login").prop('disabled', true);
				});
				
		});				