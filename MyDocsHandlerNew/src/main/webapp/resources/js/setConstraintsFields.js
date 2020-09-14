function disableAll(){
	document.getElementById("PublicAdministration").checked=false;
	document.getElementById("Company").checked=false;
	document.getElementById("Private").checked=false;
	document.getElementById("nC").disabled = true;
	document.getElementById("sC").disabled = true;
	document.getElementById("tel").disabled = true;
	document.getElementById("email").disabled = true;
	document.getElementById("cf").disabled = true;
	document.getElementById("pi").disabled = true;
}

/*function checkCommonsElements(name,tel,email)
{ console.log("esecuzione di checkCommonsElements");
	if($("#nC").val().length > 150)
		{$("#warning2").text("Il nome del committente deve contenere meno di 150 caratteri");
		document.getElementById('SM').disabled=true;
		name = false;}
	else if($("#nC").val().length <= 150)
		{$("#warning2").text("");
		name = true;}
	
	if($("#tel").val().length > 11)
		{$("#warning3").text("Il numero di telefono deve contenere un massimo di 11 cifre");
		document.getElementById('SM').disabled=true;
		tel = false;}
	
	else if($("#tel").val().length <= 11)
		{$("#warning3").text("");
		tel = true;}
	
	if($("#email").val().length > 254)
		{$("#warning4").text("L'indirizzo email deve essere composto da meno di 254 caratteri");
		document.getElementById('SM').disabled=true;
		email = false;}
	
	else if($("#email").val().length <= 254)
		{$("#warning4").text("");
		email = true;}
	
	console.log("name:" + name);
	console.log("tel:" + tel);
	console.log("email:" + email);
}*/


$(document).ready(function()
		{console.log("totti");
		    
		var namefile=false;
		var descritpion=false;
		var pi=false;
		var name=false;
		var surname=false;
		var cf=false;
		var tel = false;
		var email=false;
		var selection=false;
		
		$(document.body).on('change', function(){
		
		document.getElementById("reset").onclick = function(){document.getElementById('SM').disabled=true;};
		
		if(document.getElementById("sru").value != "selectCustomer")
		{
			disableAll();
		}
		
		
		if(document.getElementById("PublicAdministration").checked)
		{	document.getElementById("nC").disabled = false;
			document.getElementById("sC").disabled = true;
			document.getElementById("tel").disabled = false;
			document.getElementById("email").disabled = false;
			document.getElementById("cf").disabled = true;
			document.getElementById("pi").disabled = true;
		}
		if(document.getElementById("Company").checked)
		{	
			document.getElementById("nC").disabled = false;
			document.getElementById("sC").disabled = true;
			document.getElementById("tel").disabled = false;
			document.getElementById("email").disabled = false;
			document.getElementById("cf").disabled = true;
			document.getElementById("pi").disabled = false;
		}
		if(document.getElementById("Private").checked)
		{	
			document.getElementById("nC").disabled = false;
			document.getElementById("sC").disabled = false;
			document.getElementById("tel").disabled = false;
			document.getElementById("email").disabled = false;
			document.getElementById("cf").disabled = false;
			document.getElementById("pi").disabled = true;
		}
		
		
	});
	
	
	$(document.body).on('input cut paste keyup delete undo', function(){
		
		
		
		//selezione completadella tipologia di file
		var type1 = document.getElementById("nwElaborate").selected || 
					document.getElementById("pwElaborate").selected
					
		var type3 = document.getElementById("epgElaborate").selected || 
					document.getElementById("sepElaborate").selected || 
					document.getElementById("iepElaborate").selected || 
					document.getElementById("mpElaborate").selected
					
		var type2 = document.getElementById("technicalReport").selected || 
					document.getElementById("dpgElaborate").selected
					
		var type21 = document.getElementById("siElaborate").selected		|| 
					document.getElementById("artworkElaborate").selected 	|| 
					document.getElementById("leiElaborate").selected 		|| 
					document.getElementById("implantElaborate").selected	||
					document.getElementById("qssElaborate").selected
					
		/*var valuetype1 = document.getElementsByName("type1").value;
		var valuetype21 = document.getElementsByName("type21").value;
		var valuetype3 = document.getElementsByName("type2").value;
					
		var valueType = document.getElementsByName("type").value; //preliminary, definitive o executive
		if((valueType == "preliminary"  && type1) || (valueType == "definitive"  && type21) || (valueType == "executive"  && type3))
			{selection = true;}*/
		
		var case1 = (document.getElementById("preliminary").checked && 
					(document.getElementById("ppElaborate").value !="e" ||				
					type1));
		console.log("caso 1:" + case1);
					
		var case2 = (document.getElementById("definitive").checked && 
					 (document.getElementById("dpElaborate").value !="b" ||				
					 ((type2 && document.getElementById("dpgElaborate").value !="h") || type21
					  )));
		console.log("caso 2:" + case2);
		
		var case3 = (document.getElementById("executive").checked && 
					 ( document.getElementById("epElaborate").value !="c" || 
					  type3));
		console.log("caso 3:" + case3);
					
			if(case1 || case2 || case3)
			{selection = true;}
		
			console.log("selection: " + selection);
			
		//form validation in nome file e descrizione
		if($("#nf").val().length < 1 || $("#nf").val().length > 100)
			{$("#warning").text("Il nome del file deve essere compreso tra 0 e 100 caratteri");
			document.getElementById('SM').disabled=true;
			namefile=false;}
		else if($("#nf").val().length > 0 && $("#nf").val().length <= 100)
			{$("#warning").text("");
			namefile=true;}
		
		if($("#description").val().length > 250)
			{$("#warning1").text("La descrizione deve contenere meno di 250 caratteri");
			document.getElementById('SM').disabled=true;
			description=false;}
		
		else if($("#description").val().length <= 250)
			{$("#warning1").text("");
			description=true;}
		
		console.log("nome file: " + namefile);
		console.log("descrizione: " + description);
		
		
		
		
		//form validation dei campi comuni dei vari committenti
		if($("#nC").val().length > 150)
		{$("#warning2").text("Il nome del committente deve contenere meno di 150 caratteri");
		document.getElementById('SM').disabled=true;
		name = false;}
	else if($("#nC").val().length <= 150)
		{$("#warning2").text("");
		name = true;}
	
	if($("#tel1").val().length > 11)
		{$("#warning3").text("Il numero di telefono deve contenere un massimo di 11 cifre");
		document.getElementById('SM').disabled=true;
		tel = false;}
	
	else if($("#tel1").val().length <= 11)
		{$("#warning3").text("");
		tel = true;}
	
	if($("#email1").val().length > 254)
		{$("#warning4").text("L'indirizzo email deve essere composto da meno di 254 caratteri");
		document.getElementById('SM').disabled=true;
		email = false;}
	
	else if($("#email1").val().length <= 254)
		{$("#warning4").text("");
		email = true;}
	
		
		//
		console.log("----dovrebbe abilitare il bottone di salvataggio: " + ($("#sru").val() != "selectCustomer"));
		console.log("----controprova: " + !($("#sru").val() != "selectCustomer"));
	if($("#sru").val() != "selectCustomer")
		{
		if(namefile && description && selection)
			{
			document.getElementById('SM').disabled=false;
			}
		}
	else
		{document.getElementById('SM').disabled=true;
		console.log("----disabilita il salvataggio");
		//form validation dei campi esclusivi dei vari committenti
		/*if(document.getElementById("PublicAdministration").checked)
		{
			//checkCommonsElements(name, tel, email);
			console.log("abilitazione del bottone: ");
			console.log(namefile);
			console.log(description);
			console.log(name);
			console.log(tel);
			console.log(email);
			console.log(selection);

			//abilitazione del bottone "salva modifiche"
			if(namefile && description && name && tel && email && selection)
				{console.log("dovrebbe abilitare il bottone di salvataggio");
				document.getElementById('SM').disabled=false;}
		}
		
		if(document.getElementById("Company").checked)
		{
			//checkCommonsElements(name, tel, email);
			if($("#pi").val().length > 11)
				{$("#warning5").text("La partita iva deve contenere esattamente 11 cifre");
				document.getElementById('SM').disabled=true;
				pi = false;}
			
			else if($("#pi").val().length <= 11)
				{$("#warning5").text("");
				pi=true;}
			
			console.log("pi:" + pi);
			
			//abilitazione del bottone "salva modifiche"
			if(namefile && description && name && pi && tel && email && selection)
				{
				document.getElementById('SM').disabled=false;}
			
		}*/
		
			//checkCommonsElements(name, tel, email);
			if($("#sC").val().length > 150)
			{$("#warning6").text("Il nome del committente deve contenere meno di 150 caratteri");
			document.getElementById('SM').disabled=true;
			surname=false;}
			
			else if($("#sC").val().length <= 150)
			{$("#warning6").text("");
			surname=true;}
			
			if($("#cf").val().length > 16)
			{$("#warning7").text("Il codice fiscale deve contenere esattamente 16 caratteri alfanumerici");
			document.getElementById('SM').disabled=true;
			cf=false;
			}
			else if($("#cf").val().length <= 16)
			{$("#warning7").text("");
			cf=true;
			}
			console.log("surname:" + surname);
			console.log("cf:" + cf);
			
					//abilitazione del bottone "salva modifiche"
			if(namefile && description && name && surname && cf && tel && email && selection)
				{document.getElementById('SM').disabled=false;}
			
		
		}
		
		
		
		
		
	});
	
	});


					


