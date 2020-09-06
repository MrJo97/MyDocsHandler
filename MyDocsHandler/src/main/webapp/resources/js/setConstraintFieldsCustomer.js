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

$(document).ready(function()
{
	var isTheCustomerNameTyped=true;
	var isTheCustomerSurnameTyped=true;
	var isTheCustomerTelTyped=true;
	var isTheCustomerEmailTyped=true;

	
		//nel caso di reset è necessario disabilitare il bottone di 
		//salva modifiche
		$("#reset").on('click', function()
		{		
			document.getElementById('SM').disabled=true;
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
			console.log(isTheCustomerNameTyped);
			console.log(isTheCustomerSurnameTyped);
			console.log(isTheCustomerTelTyped);
			console.log(isTheCustomerEmailTyped);
		//abilitazione del bottone "salva modifiche"
			if(isTheCustomerNameTyped && isTheCustomerSurnameTyped && isTheCustomerTelTyped 
				&& isTheCustomerEmailTyped)
			{
				document.getElementById('SM').disabled=false;
			}
			else
			{
				document.getElementById('SM').disabled=true;
			}
				
			
		});
				
});