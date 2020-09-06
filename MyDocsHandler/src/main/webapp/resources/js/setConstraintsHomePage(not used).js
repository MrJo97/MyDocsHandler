//queste prime 4 righe mi consentono di importare la libreria jQuery in questo script javascript
//se non la importassi, la sintassi jquery di questo script non funzionerebbe
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);



//script che utilizza le quattro funzioni qui sopra
/*$(document).ready(function()
		{
	    document.getElementById('load').disabled=true;
		
		
	$("#row").on("input keyup", function(){
	var path = $(this).val();
	console.log("Questo è il percorso :" + path);
	 
	if(path !== "")
		 {document.getElementById('load').disabled=false;	 
		 }
	});
});*/


/*$(document).ready(function()
		{
			document.getElementById('details').disabled=true;
			document.getElementById('changeData').disabled=true;
			document.getElementById('shareFile').disabled=true;
			document.getElementById('delete').disabled=true;

			 let rates = document.getElementsByName('row');
			    rates.forEach((rate) => 
			    {
			    	rate.addEventListener('click',() => {
			    		document.write(rate.checked);
			         if(rate.checked){
			        	 
			          //  alert(`You rated: ${rate.value}`);
			        	document.getElementById('details').disabled=false;
			     		document.getElementById('changeData').disabled=false;
			     		document.getElementById('shareFile').disabled=false;
			     		document.getElementById('delete').disabled=false;
			        }
			    });
			    })
			    
		});*/

function disableAll(){
	document.getElementById('details').disabled=true;
	document.getElementById('changeData').disabled=true;
	document.getElementById('shareFile').disabled=true;
	document.getElementById('delete').disabled=true;
}

$(document).ready(function(){
			disableAll();

			//btn.addEventListener('click',() => {
			let rates = document.getElementsByName('row');

			rates.forEach((rate) => {
			//rate.checked=false;
				rate.addEventListener('click',() => {

					if(rate.checked){
					//alert(`You rated: ${rate.value}`);
						document.getElementById('details').disabled=false;
						document.getElementById('changeData').disabled=false;
						document.getElementById('shareFile').disabled=false;
						document.getElementById('delete').disabled=false;
					}		
				});
			
			$('#reset').click(disableAll);
			
 })
})
					


