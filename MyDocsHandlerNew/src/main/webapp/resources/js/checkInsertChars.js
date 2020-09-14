function checkChars(evt)
{
var charCode=(evt.which)?evt.which:event.keyCode;
if(charCode<33 || charCode==34 || charCode==36 || (charCode<48 && charCode>37) || (charCode<65 && charCode>57) || (charCode<97 && charCode>90) || charCode>122)
	{$("#result1").text("Caratteri speciali consentiti:   !, #, %   ) ");
	$("#result1").css("color", "#991199");
	return false;}
else
	{return true;}
}
