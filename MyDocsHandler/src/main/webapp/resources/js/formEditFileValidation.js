import { checkSimpleText, checkCf } from './functions.js';

function disableAll(){
	document.getElementById("cf").disabled = true;
}
function enableAll(){
	document.getElementById("cf").disabled = false;
}

$(document).ready(function()
{
	var isTheDocSelected=false; //=true se il tipo di documento è stato scelto
	var isTheNameOk=false; //=true se il nome del documento è stato inserito
	var isTheDescriptionTyped=true;
	var isTheCfOk = false;
	
		//nel caso di reset è necessario disabilitare il bottone di 
		//salva modifiche
		$("#reset").on('click', function()
		{		
			document.getElementById('SM').disabled=true;
		});
			
	//form validation dei campi dati del documento
		//nome file 
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
				if($(this).val().length==101)
					{
						$(this).val($(this).val().substr(0,625));
						$("#msgName").text("Il nome del file deve contenere un massimo di 100 caratteri!");
						$("#msgName").css("color", "red");
						return false;}
				else
				{
					$("#msgName").text("");
					return true;
				}
			});
	/*	$("#nf").on('change', function()
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
		console.log("nome: " + isTheNameTyped);*/
		
		//descrizione
		/*	$("#description").on('input change', function(){
				isTheNameTyped = checkSimpleText($(this).val());
				console.log(isTheNameTyped);
				console.log("Lunghezza nome:" + $(this).val());
				if(!checkSimpleText($(this).val()))
				{
					$("#msgDescription").text("Formato nome non valido!");
					$("#msgDescription").css("color", "red");
					isTheNameOk=false;
				}
				else if($(this).val().length<3)
				{
					$("#msgDescription").text("Il nome del file deve contenere almeno 3 caratteri!");
					$("#msgDescription").css("color", "red");
					isTheNameOk=false;
				}
				else
				{	
					$("#msgDescription").text("");
					isTheNameOk=true;
				}
				console.log("isTheNameOk: " + isTheNameOk);

			});*/
				$("#description").on('input keypress', function( event ) {
					console.log($(this).val().length);	
					console.log(!checkSimpleText($(this).val()));
					if($(this).val().length==626)
						{
							$(this).val($(this).val().substr(0,625));
							$("#msgDescription").text("La descrizione deve contenere un massimo di 625 caratteri!");
							$("#msgDescription").css("color", "red");
							return false;}
					else
					{
						$("#msgDescription").text("");
						return true;
					}
				});
		/*$("#description").on('change', function()
		{
			if($("#description").val().length > 250)
			{
				//console.log("Lunghezza della descrizione: " + $("#description").val().length);
				$("#warning1").text("La descrizione deve contenere meno di 625 caratteri");
				document.getElementById('SM').disabled=true;
				isTheDescriptionTyped=false;
			}
	
			else 
			{
				$("#warning1").text("");
				isTheDescriptionTyped=true;
			}
		});*/
		
			
	 //form validation dei campi dati del committente
			
			//nome committente
			//console.log("lunghezza del nome del committente: " + $("#nC").val().length)
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
			/*$("#cf").on('change', function()
			{
				if($("#cf").val().length != 16)
				{
					//console.log("lunghezza nome eccessiva o eccessivamente corta!!")
					$("#warning2").text("Il cf deve essere esattamente di 16 caratteri");
					document.getElementById('SM').disabled=true;
					isTheCustomerCfTyped = false;
				}
				else 
				{
					$("#warning2").text("");
					isTheCustomerCfTyped = true;
				}
			});*/
			
			
			//cognome committente
			/*$("#sC").on('change', function()
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
			});*/
			
			
			//console.log("lunghezza del numero di telefono del committente: " + $("#tel1").val().length)
			//numero di telefono
			/*$("#tel1").on('change', function()
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
			});*/
		
			
			
			//email
			/*$("#email1").on('change', function()
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
			});*/
			
			
			
		$(document.body).on('input change', function()
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
						document.getElementById("dpgElab").checked		||				
	   /*var type21 =*/ document.getElementById("siElab").checked		|| 
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
													type2))
									/*(document.getElementById("dpgElab").checked)) && 
									((document.getElementById("dpgElaborate").value !="h") || type21))	*/
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
			
			/*console.log("isTheNameTyped  " + isTheNameTyped);
			console.log("isTheDescriptionTyped  " + isTheDescriptionTyped);*/

			if((case1 || case2 || case3) && isTheNameOk)
				{
					isTheDocSelected = true;
				}
			else
				{
					isTheDocSelected = false;
				}
			console.log("descrizione: " + isTheDescriptionTyped);
			console.log("nome: " + isTheNameOk);
			console.log("selection: " + isTheDocSelected);
			console.log("caso: " + (case1 || case2 || case3));
			
				
			
			
			console.log("è selezionato un committente registrato: " + $("#sru").val()); 
			console.log("isTheDocSelected  " + isTheDocSelected);
			console.log("isTheCustomerCfTyped  " + isTheCfOk);
			/*console.log("isTheCustomerSurnameTyped  " + isTheCustomerSurnameTyped);
			console.log("isTheCustomerTelTyped  " + isTheCustomerTelTyped);
			console.log("isTheCustomerEmailTyped  " + isTheCustomerEmailTyped);*/
			console.log("non ho selezionato nessun committente registrato  " + ($("#sru").val() != "selectCustomer"));
			
			console.log("abilito il salvataggio: "+ (isTheDocSelected && (isTheCfOk || $("#sru").value != "selectCustomer")));
			
		//abilitazione del bottone "salva modifiche"
			if(isTheDocSelected && (isTheCfOk || $("#sru").val() != "selectCustomer"))
			{
				$("#SM").prop('disabled', false);
			}
			else
			{
				$("#SM").prop('disabled', true);
			}
				
			
		});
				
});