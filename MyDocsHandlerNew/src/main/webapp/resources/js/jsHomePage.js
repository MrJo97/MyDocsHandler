//script jQuery:   https://stackoverflow.com/questions/2000656/using-href-links-inside-option-tag
function redirect(operation, flag)
	{
		if(operation.value !=  "" && operation.value.substring(24,30) != "delete")
		window.location = operation.value;
		else
			{
			
			if(flag)
				{
					if(confirm("Attenzione: eliminando questo documento, verranno eliminati " +
							"anche i dati del committente a cui si riferisce, essendo l'ultimo " +
							"documento ad esso associato. Sei sicuro di voler comunque procedere " +
							"all'eliminazione del documento?"))
						{
						window.location = operation.value;
						}
				}
			else{log.console("e quindi??" + flag);
					if(confirm("Sei sicuro di voler eliminare il documento?"))
						{
						window.location = operation.value;
						}
				}
			}
			
		console.log("selezione abilitata");
	}
function enable(element)
{
	var idDoc = element.value;
	document.getElementById(idDoc).disabled = false;
	console.log("abilito la dropdown list");
	
	var list = document.getElementsByName(element.name);
	console.log(list.length);
	for(var i=0; i < list.length; i++)
		{var k = list[i].value;
		 console.log(k);
		if((k !== idDoc) && (document.getElementById(k).disabled == false))
			{console.log("disabilito la dropdown list");
			document.getElementById(k).disabled = true;
			}
			
		}
}
/*$(document).ready(function()
		{console.log("riconosciuto");
			$(document.body).on('change', function(){
				console.log("riconosciuto2");
		if(document.getElementByName('doc').checked)
			{var idDoc = document.getElementByName('doc').value;
			document.getElementById(idDoc).disabled = false;
			 }
			});
		});*/