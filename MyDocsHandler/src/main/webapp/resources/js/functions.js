/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/
/*function check_specialChars(password)
{var regExp = /[\@\!\#\$\%\'\-\/\=\^\\\_\`\{\}\~\+\xE0\xE8\xE9\xF9\xF2\xEC\x27]{1,}/;//mi verifica se c'è almeno un carattere speciale
	return regExp.test(password);
}
function check_upperCaseChars(password)
{var regExp = /[A-Z]{1,}/;//mi verifica se c'è almeno una lettera maiuscola
	return regExp.test(password);
}

function check_number(password)
{var regExp = /[0-9]{1,}/;//mi verifica se c'è un numero da 0 a 9
	return regExp.test(password);
}*/

//funzione per la verifica della password
export function checkSecurityLevelPassword(password)
{
	console.log("invocato!");
	//var isThePasswordOk;
	var regExp = /[\@\!\#\$\%\'\-\/\=\^\\\_\`\{\}\~\+\xE0\xE8\xE9\xF9\xF2\xEC\x27]{1,}/;//mi verifica se c'è almeno un carattere speciale
	var regExp1 = /[A-Z]{1,}/;//mi verifica se c'è almeno una lettera maiuscola
	var regExp2 = /[0-9]{1,}/;//mi verifica se c'è un numero da 0 a 9
	
	var isThereANumber = regExp2.test(password);
	var isThereASpecialChar = regExp.test(password);
	var isThereAnUpperCaseChar = regExp1.test(password);
    
	//controllo della password
    if((password.length > 12 && password.length <= 60) && 
    		(isThereANumber==true && isThereASpecialChar==true && isThereAnUpperCaseChar==true))
		{       
    	$("#msgPassword").text("livello di sicurezza: ottimo");
    	$("#msgPassword").css("color", "#1C39BB");
    	//isThePasswordOk=true;
		}
    else if(password.length <= 8 || (password.length > 8 && password.length <= 12 &&
    		((isThereANumber==false && isThereASpecialChar==false && isThereAnUpperCaseChar==false)||
    		(isThereANumber==true && isThereASpecialChar==false && isThereAnUpperCaseChar==false)||
    		(isThereANumber==false && isThereASpecialChar==true && isThereAnUpperCaseChar==false)||
    		(isThereANumber==false && isThereASpecialChar==false && isThereAnUpperCaseChar==true))))
    	
    	{$("#msgPassword").text("livello di sicurezza: scarso");
    	$("#msgPassword").css("color", "#B20000"); 
    	$("#registration").prop('disabled', true);
    	//isThePasswordOk=false;
    	}
    else if(password.length>60)
    	{        	
    	$("#msgPassword").text("La password non può superare i 60 caratteri!");
    	$("#msgPassword").css("color", "red"); 
    	//isThePasswordOk=true;
    	}
    else 
    	{        	
    	$("#msgPassword").text("livello di sicurezza: buono");
    	$("#msgPassword").css("color", "#008000"); 
    	//isThePasswordOk=true;
    	}
   // return isThePasswordOk;
}
//funzione per la verifica della mail 
/*export function checkEmail(email)
{	
	var regExp = /^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	return regExp.test(email);
}*/
/*-export function checkSimpleText(text)
{//var regExp = /[a-zA-Z]{3,50}/;//mi verifica se c'è almeno una lettera maiuscola o minuscola
 var regExp = /[^a-zA-Z]/;//regExp1.test(text) restituisce true se è stato inserito almeno un carattere
 							//che non sia una lettera
return !regExp.test(text);
}*/

/*export function checkTel(tel)
{//var regExp = /[a-zA-Z]{3,50}/;//mi verifica se c'è almeno una lettera maiuscola o minuscola
 var regExp = /[^0-9]/;//regExp1.test(text) restituisce true se è stato inserito almeno un carattere
 							//che non sia una lettera
 return !regExp.test(tel);
}*/
/*export function checkCf(cf)
{	
	var regExp = /^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$/;
	return regExp.test(cf);
}*/
/*export function checkUsername(username)
{	
	var regExp = /[^[a-zA-Z0-9@!#$%'\-/=^_`{}~+\xE0\xE8\xE9\xF9\xF2\xEC\x27]]/;
	//regExp.test(username) è true quando username contiene anche uno solo dei caratteri non consentiti
	return regExp.test(username);
}*/


export function checkAllowedCharacters(event)
{
	var charCode=(event.which)?event.which:event.keyCode;
	if(charCode===34 || charCode===38 || (charCode>=40 && charCode<=46 && charCode!=43 && charCode!=45)
			|| (charCode>=58 && charCode<=63 && charCode!=61) || charCode===91 || charCode===93 
			|| charCode===124 || charCode>126)
		{$("#msg1").text("Sono consentite le lettere accentate ed i simboli:  @ ! # $ % ' - / = ^ \ _ ` { } ~ +  ) ");
		$("#msg1").css("color", "#991199");
		return false;
		}
	else
		return true;
}


export function checkFormatEmail()
{	
	var regExp = /^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	//return regExp.test($(this).val());
	if(!regExp.test($(this).val()))
	{
		$("#msgEmail").text("Formato dell'email non valido!");
		$("#msgEmail").css("color", "red");
	}
	else if($(this).val().length<3 || $(this).val().length>254 )
	{
		$("#msgEmail").text("L'email deve contenere un minimo di 3 caratteri ed un massimo di 254!");
		$("#msgEmail").css("color", "red");
	}
	else
	{	
		$("#msgEmail").text("");
	}
}
	

export function checkFormatPasswordForLogin(){
 if(($("#password").val().length)>60)
{console.log("La password non può essere più lunga di 60 caratteri!");
$("#msgPassword").text("La password non può essere più lunga di 60 caratteri!");
$("#msgPassword").css("color", "red");
}
else
	{console.log("dovrebbe sparire");
	$("#msg1").text("");
	$("#msgPassword").text("");
	}
}

export function checkFormatUsername()
{
	if($(this).val().length<3 || $(this).val().length>15)
	{
		$("#msgUsername").text("Lo username deve contenere un minimo di 3 caratteri ed un massimo di 15!");
		$("#msgUsername").css("color", "red");
		$("#msg1").text("");
	}
	else
	{	
		$("#msgUsername").text("");
		$("#msg1").text("");
	}
}
export function checkFormatCf()
{
	console.log("Lunghezza cf:" + $(this).val());
	var regExp = /^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$/;
	//return regExp.test($(this).val());
	if(!regExp.test($(this).val()))
	{
		$("#msgCf").text("Formato non valido!");
		$("#msgCf").css("color", "red");
	}
	//questo else if non viene mai eseguito se il formato non è corretto
	else if($(this).val().length!=16)
	{
		$("#msgCf").text("Il codice fiscale deve contenere esattamente 16 caratteri alfanumerici!");
		$("#msgCf").css("color", "red");
	}
	else
	{	
		$("#msgCf").text("");
	}
}

export function checkFormatTel()
{
	console.log("Lunghezza telefono:" + $(this).val());
	 var regExp = /[^0-9]/;//regExp1.test(text) restituisce true se è stato inserito almeno un carattere
		//che non sia una lettera
//return !regExp.test($(this).val());
	if(regExp.test($(this).val()))
	{
		$("#msgTel").text("Formato non valido!");
		$("#msgTel").css("color", "red");
	}
	else if($(this).val().length<9 || $(this).val().length>15)
	{
		$("#msgTel").text("Il numero di telefono deve contenere un minimo di 9 numeri ed un massimo di 15!");
		$("#msgTel").css("color", "red");
	}
	else
	{	
		$("#msgTel").text("");
	}
}


export function checkFormatSurname()
{
console.log("Lunghezza cognome:" + $(this).val());
var regExp = /[^a-zA-Z]/;
//regExp.test($(this).val())
if(regExp.test($(this).val()))
{
	$("#msgSurname").text("Formato cognome non valido!");
	$("#msgSurname").css("color", "red");
}
else if($(this).val().length<3 || $(this).val().length>50)
{
	$("#msgSurname").text("Il cognome deve contenere un minimo di 3 caratteri ed un massimo di 50!");
	$("#msgSurname").css("color", "red");
}
else
{	
	$("#msgSurname").text("");
}
}

export function checkFormatName(number)
{
	console.log("Lunghezza nome:" + $("#name").val());
	 var regExp = /[^a-zA-Z]/;
//return !regExp.test($("#name").val());
	if(regExp.test($("#name").val()))
	{
		$("#msgName").text("Formato nome non valido!");
		$("#msgName").css("color", "red");
	}
	else if($("#name").val().length<3 || $("#name").val().length>number)
	{
		$("#msgName").text("Il nome deve contenere un minimo di 3 caratteri ed un massimo di "+number+"!");
		$("#msgName").css("color", "red");
	}
	else
	{	
		$("#msgName").text("");
	}
}

export function checkFormatNameForFile()
{
	console.log("Lunghezza nome:" + $(this).val());
	 var regExp = /[^a-zA-Z0-9\@\(\)\s\.\!\#\$\%\'\-\/\=\^\\\_\`\{\}\~\+\xE0\xE8\xE9\xF9\xF2\xEC\x27]/;
//return !regExp.test($("#name").val());
	if(regExp.test($(this).val()))
	{
		$("#msgName").text("Formato nome non valido!");
		$("#msgName").css("color", "red");
	}
	else if($("#name").val().length<3 || $("#name").val().length>100)
	{
		$("#msgName").text("Il nome deve contenere un minimo di 3 caratteri ed un massimo di "+number+"!");
		$("#msgName").css("color", "red");
	}
	else
	{	
		$("#msgName").text("");
	}
}