//funzione che controlla se c'� almeno un numero nella stringa
function check_number(passw)
{var j = 0; //tiene conto della quantit� di numeri presenti nella password
 for(var k=0 ; k <= 9; k++)
	 {
	 if(passw.search(k) !== -1)
	 {
		 j++;}
	 }
 if(j==0)
	 {return false;}
 else
	 {return true;}
}



//funzione che controlla se c'� un carattere speciale tra quelli consentiti
function check_specialChars(passw)
{var j = 0; //tiene conto della presenza di uno o piu caratteri speciali
 for(var k=0 ; k < passw.length ; k++)
	 {
	 if(passw.search("!") !== -1 || passw.search("#") !== -1 || passw.search("%") !== -1)
	 {
		 j++;}
	 }
 if(j==0)
	 {return false;}
 else
	 {return true;}
}




//funzione che controlla se c'� almeno un carattere maiuscolo
function check_upperCaseChars(passw)
{var j = 0; //tiene conto della presenza di uno o piu caratteri maiuscoli
 for(var k=0 ; k < passw.length ; k++)
	 {var carattere = passw.charAt(k);
	 if(check_specialChars(carattere) == false && check_number(carattere) == false && carattere == carattere.toUpperCase())
	 {j++;}
	 }
 if(j==0)
	 {return false;}
 else
	 {return true;}
}


$(document).ready(function(email, password)
		{	
    
    $("#myInput1").on("input cut paste keyup delete change undo", function(){
    	var stringa = $(this).val();
    	if(stringa.length < 3 || stringa.length > 254)
    		{
    		$("#result2").text("L'indirizzo email deve essere compreso tra 3 e 254 caratteri");
        	$("#result2").css("color", "red");
    		document.getElementById('cmd1').disabled=true;
    		email=false;}
    	else{
    		email=true;
    		$("#result2").text("");
    		}
    	if(email && password)
		{
		document.getElementById('cmd1').disabled=false;
		}
    });
	
	
	
    $("#myInput").on("input cut paste keyup delete change undo", function(){
    	
    	var stringa = $(this).val();
    	var num = check_number(stringa);
    	var special = check_specialChars(stringa);
    	var upperCase = check_upperCaseChars(stringa);
        
        if((stringa.length > 12 && stringa.length <= 60) && (num==true && special==true && upperCase==true))
    		{        	
        	$("#result").text("livello di sicurezza: ottimo");
        	$("#result").css("color", "#1C39BB");
        	
        	password=true;
    		}
        else if(stringa.length <= 8 || (stringa.length > 8 && stringa.length <= 12 && ((num==false && special==false && upperCase==false)||(num==true && special==false && upperCase==false)||(num==false && special==true && upperCase==false)||(num==false && special==false && upperCase==true))))
        	{$("#result").text("livello di sicurezza: scarso");
        	$("#result").css("color", "#B20000");
        	
        	document.getElementById('cmd1').disabled=true; 
        	password=false;
        	}
        else if(stringa.length > 60)
        	{$("#result").text("La password non può superare i 60 caratteri");
        	$("#result").css("color", "red");
    	
        	document.getElementById('cmd1').disabled=true; 
        	password=false;
    	}
        else 
        	{        	
        	$("#result").text("livello di sicurezza: buono");
        	$("#result").css("color", "#008000"); 
        	password=true;
        	}
        if(email && password)
		{
			document.getElementById('cmd1').disabled=false;
		}
    });
    

});
