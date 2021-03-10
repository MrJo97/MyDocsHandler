import { /*checkUsername,*/ checkFormatUsername, checkFormatPasswordForLogin, checkAllowedCharacters } from './functions.js';
$(document).ready(function()
		{
			var isThePasswordOk;
			var isTheNickOk;
			
			//controllo password
			//$("#password").on('input paste',function(){
			$("#password").on('keypress', function( event ) {
				return checkAllowedCharacters(event);
			});
			$("#password").on('input change', checkFormatPasswordForLogin);
					
			//controllo username
			$("#username").on('keypress', function( event ) {
				return checkAllowedCharacters(event);
			});
			$("#username").on('change', checkFormatUsername);
								
			//abilitazione bottone di login
			$("#table").on('input change', function(){
			//	console.log("isTheEmailOK e isThePasswordOK:" + isTheEmailOk + isThePasswordOk+ isTheNameOk + isTheSurnameOk + isTheTelOk + isTheCfOk + isTheNickOk );
				if($("#msgUsername").text() == "")
					{isTheNickOk=true;}
				else
					{isTheNickOk=false;}
				console.log($("#msgEmail").text());
				if($("#msgPassword").text() == "" && $("#password").val() != "")
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