$(document).ready(function()
		{
	 
    $("#loginEmail").on("input cut paste keyup delete change undo", function(){
    	var email = $(this).val();
    	var password=$("#loginPassword").val();
    	if(email.length < 3 || email.length > 254)
    		{
    			$("#result1").text("L'indirizzo email deve essere compreso tra 3 e 254 caratteri");
    			$("#result1").css("color", "red");
    			document.getElementById('cmd').disabled=true;
    		}
    	else
    	{ 
    		$("#result1").text("");
    		if((password.length > 0) && (password.length < 61))
    			{
        			document.getElementById('cmd').disabled=false;
    			}
    		}
    	
    });
    
    $("#loginPassword").on("input cut paste keyup delete change undo", function(){
    	var password = $(this).val();
    	var email = $("#loginEmail").val();
    	if(password.length < 1 || password.length > 60)
    		{
    		$("#result2").text("La password non può essere vuota e deve essere inferiore ai 60 caratteri");
        	$("#result2").css("color", "red");
    		document.getElementById('cmd').disabled=true;
    		
    		}
    	else
    	 {
    		$("#result2").text("");
    		if((email.length > 2) && (email.length < 255))   			
    		{
        		document.getElementById('cmd').disabled=false;
    		}
    	}
 
    });
});

    