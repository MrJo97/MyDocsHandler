/*
	i simboli accettati per la password saranno
	 @ ! # $ % ' - / = ^ \ _ ` { } ~ + e tutte le lettere accentate
*/
function check_specialChars(password)
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
}

//funzione per la verifica della password
export function checkSecurityLevelPassword(password)
{
	var isThePasswordOk;
	var isThereANumber = check_number(password);
	var isThereASpecialChar = check_specialChars(password);
	var isThereAnUpperCaseChar = check_upperCaseChars(password);
    
	//controllo della password
    if((password.length > 12 && password.length <= 60) && 
    		(isThereANumber==true && isThereASpecialChar==true && isThereAnUpperCaseChar==true))
		{       
    	$("#msgPassword").text("livello di sicurezza: ottimo");
    	$("#msgPassword").css("color", "#1C39BB");
    	isThePasswordOk=true;
		}
    else if(password.length <= 8 || (password.length > 8 && password.length <= 12 &&
    		((isThereANumber==false && isThereASpecialChar==false && isThereAnUpperCaseChar==false)||
    		(isThereANumber==true && isThereASpecialChar==false && isThereAnUpperCaseChar==false)||
    		(isThereANumber==false && isThereASpecialChar==true && isThereAnUpperCaseChar==false)||
    		(isThereANumber==false && isThereASpecialChar==false && isThereAnUpperCaseChar==true))))
    	
    	{$("#msgPassword").text("livello di sicurezza: scarso");
    	$("#msgPassword").css("color", "#B20000"); 
    	$("#registration").prop('disabled', true);
    	isThePasswordOk=false;
    	}
    else 
    	{        	
    	$("#msgPassword").text("livello di sicurezza: buono");
    	$("#msgPassword").css("color", "#008000"); 
    	isThePasswordOk=true;
    	}
    return isThePasswordOk;
}
//funzione per la verifica della mail 
export function checkEmail(email)
{	
	var regExp = /^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	return regExp.test(email);
}
export function checkSimpleText(text)
{//var regExp = /[a-zA-Z]{3,50}/;//mi verifica se c'è almeno una lettera maiuscola o minuscola
 var regExp = /[^a-zA-Z]/;//regExp1.test(text) restituisce true se è stato inserito almeno un carattere
 							//che non sia una lettera
return !regExp.test(text);
}

export function checkTel(tel)
{//var regExp = /[a-zA-Z]{3,50}/;//mi verifica se c'è almeno una lettera maiuscola o minuscola
 var regExp = /[^0-9]/;//regExp1.test(text) restituisce true se è stato inserito almeno un carattere
 							//che non sia una lettera
 return !regExp.test(tel);
}
export function checkCf(cf)
{	
	var regExp = /^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$/;
	return regExp.test(cf);
}
export function checkUsername(username)
{	
	var regExp = /[^[a-zA-Z0-9\@\!\#\$\%\'\-\/\=\^\\\_\`\{\}\~\+\xE0\xE8\xE9\xF9\xF2\xEC\x27]]/;
	return !regExp.test(username);
}

export function checkFormatEmail()
{
	var isTheEmailOk = checkEmail($(this).val());
	
	//console.log($(this).val().length+1);	
	if(!isTheEmailOk)
	{
		$("#msgEmail").text("Formato dell'email non valido!");
		$("#msgEmail").css("color", "red");
		isTheEmailOk=false;
	}
	else if($(this).val().length<3 || $(this).val().length>254 )
	{
		$("#msgEmail").text("L'email deve contenere un minimo di 3 caratteri ed un massimo di 254!");
		$("#msgEmail").css("color", "red");
		isTheEmailOk=false;
	}
	else
	{	
		$("#msgEmail").text("");
		isTheEmailOk=true;
	}
	console.log("isTheEmailOK inside function: " + isTheEmailOk);
	//return isTheEmailOk;
}
	

export function checkFormatPasswordForLogin(event){
var charCode=(event.which)?event.which:event.keyCode;
console.log($("#password").val().length +1);
if(charCode===34 || charCode===38 || (charCode>=40 && charCode<=46 && charCode!=43 && charCode!=45)
		|| (charCode>=58 && charCode<=63 && charCode!=61) || charCode===91 || charCode===93 
		|| charCode===124 || charCode>126)
	{$("#msg1").text("Sono consentite le lettere accentate ed i simboli:  @ ! # $ % ' - / = ^ \ _ ` { } ~ +  ) ");
	$("#msg1").css("color", "#991199");
	return false;
	}
else if(($("#password").val().length+1) == 60)//devo impedire di digitare dopo il 60 esimo
		return false;
	
else if(($("#password").val().length) == 0)
{$("#msgPassword").text("Digitare la password.");
$("#msgPassword").css("color", "red");
//isThePasswordOk=false;
}
else
	{$("#msg1").text("");
	$("#msgPassword").text("");
	//isThePasswordOk=true;
	return true;
	}
}

export function checkFormatUsername()
{
	console.log("yyy");
	//isTheNickOk = checkUsername($(this).val());
	//console.log(isTheNickOk);
	console.log("Lunghezza username:" + $(this).val());
	if(!checkUsername($(this).val()))
	{
		$("#msgUsername").text("Formato username non valido!");
		$("#msgUsername").css("color", "red");
		$("#msg1").text("Sono consentite le lettere accentate ed i simboli:  @ ! # $ % ' - / = ^ \ _ ` { } ~ +  ) ");
		$("#msg1").css("color", "#991199");
		//isTheNickOk=false;
		
	}
	else if($(this).val().length<3 || $(this).val().length>15)
	{
		$("#msgUsername").text("Lo username un minimo di 3 caratteri ed un massimo di 15!");
		$("#msgUsername").css("color", "red");
		$("#msg1").text("");
		//isTheNickOk=false;
	}
	else
	{	
		$("#msgUsername").text("");
		$("#msg1").text("");
		//isTheNickOk=true;
	}
	//console.log("isTheNickOk: " + isTheNickOk);
}
