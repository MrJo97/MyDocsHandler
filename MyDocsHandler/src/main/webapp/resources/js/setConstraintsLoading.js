//queste prime 4 righe mi consentono di importare la libreria jQuery in questo script javascript
//se non la importassi, la sintassi jquery di questo script non funzionerebbe
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

//window.onload = function () {
//attenzione a cosa si utilizza negli script javascript: 
//1) window.onload = function () {
//2)$(document).ready(function(){
//3)document.addEventListener('DOMContentLoaded', function() {
document.addEventListener('DOMContentLoaded', function() {
//$(document).ready(function(){
		
	    document.getElementById('load').disabled=true;

	$("#insertFile").on("input keyup", function(){
	document.getElementById('load').disabled=true;
	var path = $(this).val();
	console.log("Questo è il percorso :" + path);
	 
	if(path !== "")
		 {document.getElementById('load').disabled=false;	 
		 }
	});
	
	/*$("#load").on("click", function(){
		this.disabled=true;
	});*/
});
		//}



					


