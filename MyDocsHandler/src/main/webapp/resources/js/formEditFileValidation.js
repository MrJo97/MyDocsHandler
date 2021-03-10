import { checkFormatCf, checkFormatNameForFile } from './functions.js';
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
		$("#name").on('change', checkFormatNameForFile);

				$("#description").on('input', function( event ) {
					console.log($(this).val().length);	
					//console.log(!checkSimpleText($(this).val()));
					if($(this).val().length>625)
						{
							//$(this).val($(this).val().substr(0,625));
							$("#msgDescription").text("La descrizione deve contenere un massimo di 625 caratteri!");
							$("#msgDescription").css("color", "red");
							//return false;
							isTheDescriptionTyped=false;
							}
					else
					{
						$("#msgDescription").text("");
						isTheDescriptionTyped=true;
					}
				});
			
	 //form validation dei campi dati del committente
			
				//controllo cf
				$("#cf").on('change', checkFormatCf);
				
		$(document.body).on('input change', function()
		{
			//nel caso in cui si selezioni un committente già registrato
			//è necesario disabilitare i campi dati dell'utente
			console.log("è stato selezionato un committente registrato?: " + (document.getElementById("sru").value != "selectCustomer"));
			if(document.getElementById("sru").value != "selectCustomer")
			{
				$("#cf").prop('disabled', true);
			}
			else
			{
				$("#cf").prop('disabled', false);
			}
			
			//selezione completa della tipologia di file
			var type1 =	document.getElementById("nwElab").checked || 
						document.getElementById("pwElab").checked;
			
			console.log("type: " + type1)
						
			var type2 = document.getElementById("techRep").checked || 
						document.getElementById("dpgElab").checked		||				
						document.getElementById("siElab").checked		|| 
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
			//console.log("isTheDocselected : " + (case1 || case2 || case3) && isTheNameOk && isTheDescriptionTyped);
			console.log("(case1)"+ case1);
			console.log("(case2)"+ case2);
			console.log("(case3)"+ case3);
			console.log("isTheNameOk"+ isTheNameOk);
			console.log("isTheDescriptionTyped"+ isTheDescriptionTyped);
			
			/*if((case1 || case2 || case3) && isTheNameOk && isTheDescriptionTyped)
				{
					isTheDocSelected = true;
				}
			else
				{
					isTheDocSelected = false;
				}*/
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
			if($("#msgName").text() == "" && $("#name").val() != "" )
			{isTheNameOk=true;}
			else
			{isTheNameOk=false;}
			
			if($("#msgCf").text() == "" && $("#cf").val() != "" )
			{isTheCfOk=true;}
			else
			{isTheCfOk=false;}
			console.log("isTheCfOk" + isTheCfOk);
			console.log("isTheNameOk" + isTheNameOk);
			isTheDocSelected=(case1 || case2 || case3) && isTheNameOk && isTheDescriptionTyped;
			console.log("isTheDocSelected" + isTheDocSelected);
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