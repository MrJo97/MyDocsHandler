function disableAll(){
	document.getElementById("nC").disabled = true;
	document.getElementById("sC").disabled = true;
	document.getElementById("cf").disabled = true;
	document.getElementById("tel1").disabled = true;
	document.getElementById("email1").disabled = true;
	document.getElementById("tel2").disabled = true;
	document.getElementById("email2").disabled = true;

}
function enableAll(){
	document.getElementById("nC").disabled = false;
	document.getElementById("sC").disabled = false;
	document.getElementById("cf").disabled = false;
	document.getElementById("tel1").disabled = false;
	document.getElementById("email1").disabled = false;
	document.getElementById("tel2").disabled = false;
	document.getElementById("email2").disabled = false;

}
/*function checkForChars(string)
{
	var isThereAtLeastAChar=false;
	for(var i = 0; i < string.length; i++)
		{
			if(typeof string.substring(i,i+1) == "string")
				isThereAtLeastAChar = true;
		}
	return isThereAtLeastAChar;
}*/

//window.onload=function()
//{

$(document).ready(function()
{
	var isTheDocSelected=false; //=true se il tipo di documento è stato scelto
	var isTheNameTyped=false; //=true se il nome del documento è stato inserito
	var isTheDescriptionTyped=true;
	var isTheCustomerNameTyped=false;
	var isTheCustomerSurnameTyped=false;
	var isTheCustomerTelTyped=false;
	var isTheCustomerEmailTyped=false;

	
		//nel caso di reset è necessario disabilitare il bottone di 
		//salva modifiche
		$("#reset").on('click', function()
		{		
			document.getElementById('SM').disabled=true;
		});
			
	//form validation dei campi dati del documento
		//nome file 
		$("#nf").on('change', function()
		{
			if($("#nf").val().length < 1 || $("#nf").val().length > 100)
			{
				$("#warning").text("Il nome del file deve contenere meno di 100 caratteri e non essere vuoto");
				document.getElementById('SM').disabled=true;
				isTheNameTyped=false;
			}
			else
			{
				$("#warning").text("");
				isTheNameTyped=true;
			}
		});
		console.log("nome: " + isTheNameTyped);
		
		//descrizione
		$("#description").on('change', function()
		{
			if($("#description").val().length > 250)
			{
				//console.log("Lunghezza della descrizione: " + $("#description").val().length);
				$("#warning1").text("La descrizione deve contenere meno di 250 caratteri");
				document.getElementById('SM').disabled=true;
				isTheDescriptionTyped=false;
			}
	
			else 
			{
				$("#warning1").text("");
				isTheDescriptionTyped=true;
			}
		});
		
			
	 //form validation dei campi dati del committente
			
			//nome committente
			//console.log("lunghezza del nome del committente: " + $("#nC").val().length)
			$("#nC").on('change', function()
			{
				if($("#nC").val().length > 50 || $("#nC").val().length < 1)
				{
					//console.log("lunghezza nome eccessiva o eccessivamente corta!!")
					$("#warning2").text("Il nome deve essere minore di 50 caratteri e non vuoto");
					document.getElementById('SM').disabled=true;
					isTheCustomerNameTyped = false;
				}
				else 
				{
					$("#warning2").text("");
					isTheCustomerNameTyped = true;
				}
			});
			
			
			//cognome committente
			$("#sC").on('change', function()
			{
				if($("#sC").val().length > 50 || $("#sC").val().length < 1)
				{
					$("#warning3").text("Il cognome deve essere minore di 50 caratteri e non vuoto");
					document.getElementById('SM').disabled=true;
					isTheCustomerSurnameTyped = false;
				}
				else 
				{
					$("#warning3").text("");
					isTheCustomerSurnameTyped = true;
				}
			});
			
			
			//console.log("lunghezza del numero di telefono del committente: " + $("#tel1").val().length)
			//numero di telefono
			$("#tel1").on('change', function()
			{
				if($("#tel1").val().length > 11 || $("#tel1").val().length < 9)
				{
					$("#warning4").text("Sono consentite un massimo di 15 cifre ed un minimo di 9");
					document.getElementById('SM').disabled=true;
					isTheCustomerTelTyped = false;
				}
				else 
				{
					$("#warning4").text("");
					isTheCustomerTelTyped = true;
				}
			});
		
			
			
			//email
			$("#email1").on('change', function()
			{
				if($("#email1").val().length > 254 || $("#email1").val().length < 3)
				{
					$("#warning5").text("L'email deve essere compresa tra 3 e 254 caratteri");
					document.getElementById('SM').disabled = true;
					isTheCustomerEmailTyped = false;
				}
				else
				{
					$("#warning5").text("");
					isTheCustomerEmailTyped = true;
				}
			});
			
			
			
		$(document.body).on('change', function()
		{
			//nel caso in cui si selezioni un committente già registrato
			//è necesario disabilitare i campi dati dell'utente
			console.log("è stato selezionato un committente registrato?: " + (document.getElementById("sru").value != "selectCustomer"));
			if(document.getElementById("sru").value != "selectCustomer")
			{
				disableAll();
			}
			else
			{
				enableAll();
			}
			
			
			
			//selezione completa della tipologia di file
			var type1 = /*$('#nwElaborate option:selected') || $('#pwElaborate option:selected')*/
						document.getElementById("nwElab").checked || 
						document.getElementById("pwElab").checked;
			
			console.log("type: " + type1)
						
			/*var type3 = document.getElementById("epgElaborate").selected || 
						document.getElementById("sepElaborate").selected || 
						document.getElementById("iepElaborate").selected || 
						document.getElementById("mpElaborate").selected*/
						
			var type2 = document.getElementById("techRep").checked || 
						document.getElementById("dpgElab").checked
						
			var type21 = document.getElementById("siElab").checked		|| 
						 document.getElementById("artworkElab").checked 	|| 
						 document.getElementById("leiElab").checked 		|| 
						 document.getElementById("implantElab").checked	||
						 document.getElementById("qssElab").checked
			
						//selezione del tipo di documento
			var case1 = (document.getElementById("preliminary").checked && (
						(document.getElementById("ppElaborate").value !="e" ||
						type1)));
			console.log("caso 1:" + case1);
					
			var case2 = (document.getElementById("definitive").checked && 
							((document.getElementById("dpElaborate").value !="d" 
													||
									(document.getElementById("dpgElab").checked)) && 
									((document.getElementById("dpgElaborate").value !="h") || type21))	
												&&
							(document.getElementById("dpElaborate").value !="b" ||
						    document.getElementById("techRep").checked));
			console.log("caso 2:" + case2);
		
			var case3 = (document.getElementById("executive").checked && 
					
						((document.getElementById("epElaborate").value !="c" || 
						document.getElementById("epgElab").checked)
						&&
						(document.getElementById("epElaborate").value !="d" || 
						document.getElementById("sepElab").checked || 
						document.getElementById("iepElab").checked)
						&&
						(document.getElementById("epElaborate").value !="e") ||
						document.getElementById("mpElab").checked));
			
			console.log("caso 3:" + case3);
					
			console.log("-------------------");
			console.log("-------------------");
			console.log("-------------------");
			
			
			

			if((case1 || case2 || case3) && isTheDescriptionTyped && isTheNameTyped)
				{
					isTheDocSelected = true;
				}
			else
				{
					isTheDocSelected = false;
				}
			console.log("descrizione: " + isTheDescriptionTyped);
			console.log("nome: " + isTheNameTyped);
			console.log("selection: " + isTheDocSelected);
			console.log("caso: " + (case1 || case2 || case3));
			
				
			
			
			console.log("è selezionato un committente registrato: " + $("#sru").val()); 
		//abilitazione del bottone "salva modifiche"
			if(isTheDocSelected && ((isTheCustomerNameTyped && isTheCustomerSurnameTyped && isTheCustomerTelTyped 
				&& isTheCustomerEmailTyped) || $("#sru").val() != "selectCustomer"))
			{
				document.getElementById('SM').disabled=false;
			}
			else
			{
				document.getElementById('SM').disabled=true;
			}
				
			
		});
				
});