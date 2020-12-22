//funzioni per il primo livello di selezione
function handleChange1()
{	//console.log("abilito preliminary");
	document.getElementById("ppElaborate").disabled = false;
	document.getElementById("dpElaborate").disabled = true;
	document.getElementById("epElaborate").disabled = true;
	hideList2();
	hideList2_1();
	//hideList21();
	hideList3();
	hideList3_1();
	hideList3_2();
	
}
function handleChange2()
{	
	//console.log("abilito definitive");
	document.getElementById("ppElaborate").disabled = true;
	document.getElementById("dpElaborate").disabled = false;
	document.getElementById("epElaborate").disabled = true;
	hideList1();
	hideList3();
	hideList3_1();
	hideList3_2();
	//hideList21();
}
function handleChange3()
{	
	//console.log("abilito executive");
	document.getElementById("ppElaborate").disabled = true;
	document.getElementById("dpElaborate").disabled = true;
	document.getElementById("epElaborate").disabled = false;
	hideList1();
	hideList2();
	hideList2_1();
	//hideList21();
}



function showList1()
{console.log("mostro la lista1");
	document.getElementById("pw").hidden=false;
document.getElementById("nw").hidden=false;
	}
function hideList1()
{document.getElementById("pw").hidden=true;
document.getElementById("nw").hidden=true;}

function showList2()
{document.getElementById("tr").hidden=false;
	}
function showList2_1()
{//document.getElementById("tr").hidden=false;
document.getElementById("dpg").hidden=false;
document.getElementById("si").hidden=false;
document.getElementById("artwork").hidden=false;
document.getElementById("lei").hidden=false;
document.getElementById("implant").hidden=false;
document.getElementById("qss").hidden=false;
	}

function hideList2()
{document.getElementById("tr").hidden=true;}

function hideList2_1()
{document.getElementById("dpg").hidden=true;
document.getElementById("si").hidden=true;
document.getElementById("artwork").hidden=true;
document.getElementById("lei").hidden=true;
document.getElementById("implant").hidden=true;
document.getElementById("qss").hidden=true;}

function showList3()
{document.getElementById("epg").hidden=false;	}
function showList3_1()
{document.getElementById("sep").hidden=false;
document.getElementById("iep").hidden=false;}
function showList3_2()
{document.getElementById("mp").hidden=false;}


function hideList3()
{document.getElementById("epg").hidden=true;
	}
function hideList3_1()
{document.getElementById("sep").hidden=true;
document.getElementById("iep").hidden=true;	}

function hideList3_2()
{document.getElementById("mp").hidden=true;	}






//funzioni per il secondo livello di selezione.
function handleChange11()
{	//console.log("abilito ---");
	document.getElementById("nwElaborate").disabled = true;
	document.getElementById("pwElaborate").disabled = false;
}
function handleChange12()
{	//console.log("abilito ...");
	document.getElementById("nwElaborate").disabled = false;
	document.getElementById("pwElaborate").disabled = true;
}



function handleChange21()
{document.getElementById("technicalReport").disabled = false;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=true;

//hideList21();
}
function handleChange22()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = false;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=true;
}

function handleChange23()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=false;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=true;
}
function handleChange24()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=false;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=true;
}
function handleChange25()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=false;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=true;
}
function handleChange26()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=false;
document.getElementById("qssElaborate").disabled=true;
}
function handleChange27()
{document.getElementById("technicalReport").disabled = true;
document.getElementById("dpgElaborate").disabled = true;
document.getElementById("siElaborate").disabled=true;
document.getElementById("artworkElaborate").disabled=true;
document.getElementById("leiElaborate").disabled=true;
document.getElementById("implantElaborate").disabled=true;
document.getElementById("qssElaborate").disabled=false;
}



function handleChange31()
{document.getElementById("epgElaborate").disabled = false;
document.getElementById("sepElaborate").disabled = true;
document.getElementById("iepElaborate").disabled = true;
document.getElementById("mpElaborate").disabled = true;
	}
function handleChange32()
{document.getElementById("epgElaborate").disabled = true;
document.getElementById("sepElaborate").disabled = false;
document.getElementById("iepElaborate").disabled = true;
document.getElementById("mpElaborate").disabled = true;
	}
function handleChange33()
{document.getElementById("epgElaborate").disabled = true;
document.getElementById("sepElaborate").disabled = true;
document.getElementById("iepElaborate").disabled = false;
document.getElementById("mpElaborate").disabled = true;
	}
function handleChange34()
{document.getElementById("epgElaborate").disabled = true;
document.getElementById("sepElaborate").disabled = true;
document.getElementById("iepElaborate").disabled = true;
document.getElementById("mpElaborate").disabled = false;
	}




//funzioni per il terzo livello di selezione.
/*
function handleChange211()
{document.getElementById("siElaborate").disabled = false;
document.getElementById("artworkElaborate").disabled = true;
document.getElementById("leiElaborate").disabled = true;
document.getElementById("implantElaborate").disabled = true;
document.getElementById("qssElaborate").disabled = true;
}

function handleChange212()
{document.getElementById("siElaborate").disabled = true;
document.getElementById("artworkElaborate").disabled = false;
document.getElementById("leiElaborate").disabled = true;
document.getElementById("implantElaborate").disabled = true;
document.getElementById("qssElaborate").disabled = true;
}

function handleChange213()
{document.getElementById("siElaborate").disabled = true;
document.getElementById("artworkElaborate").disabled = true;
document.getElementById("leiElaborate").disabled = false;
document.getElementById("implantElaborate").disabled = true;
document.getElementById("qssElaborate").disabled = true;
}

function handleChange214()
{document.getElementById("siElaborate").disabled = true;
document.getElementById("artworkElaborate").disabled = true;
document.getElementById("leiElaborate").disabled = true;
document.getElementById("implantElaborate").disabled = false;
document.getElementById("qssElaborate").disabled = true;
}

function handleChange215()
{document.getElementById("siElaborate").disabled = true;
document.getElementById("artworkElaborate").disabled = true;
document.getElementById("leiElaborate").disabled = true;
document.getElementById("implantElaborate").disabled = true;
document.getElementById("qssElaborate").disabled = false;
}
*/

/*function showList21()
{document.getElementById("si").hidden=false;
document.getElementById("artwork").hidden=false;
document.getElementById("lei").hidden=false;
document.getElementById("implant").hidden=false;
document.getElementById("qss").hidden=false;
	}
function hideList21()
{document.getElementById("si").hidden=true;
document.getElementById("artwork").hidden=true;
document.getElementById("lei").hidden=true;
document.getElementById("implant").hidden=true;
document.getElementById("qss").hidden=true;
	}*/



//document.addEventListener('DOMContentLoaded', function() 
$(document).ready(function()
		{
		// console.log("eilà");
		// console.log("ciao" + document.getElementById('ppElaborate').value=="e");
    
	$(document.body).on('change', function(){
		//console.log("cliccato");
		//console.log(document.getElementById('preliminary').checked);
		//console.log(document.getElementById('definitive').checked);
		//console.log(document.getElementById('executive').checked);
		
		//abilita e disabilita i checkbox
		if(document.getElementById('preliminary').checked)
		{handleChange1();
		//console.log("--------------------esecuzione di handlechange1");
		
			if(document.getElementById('ppElaborate').value == "e")
				{showList1();
				 if(document.getElementById('pwElab').checked)
					 {handleChange11();} 
				 if(document.getElementById('nwElab').checked)
					 {handleChange12();}
				}
			
			else{hideList1();}
			}
		
		
		if(document.getElementById('definitive').checked)
		{handleChange2();
		//console.log("--------------------esecuzione di handlechange2");
			if(document.getElementById('dpElaborate').value == "b")
				{showList2();
				if(document.getElementById('techRep').checked)
					{handleChange21();} 
				}
			else
			{hideList2();}
			
			if(document.getElementById('dpElaborate').value == "d")
			{showList2_1();
				if(document.getElementById('dpgElab').checked)
					{handleChange22();}
				if(document.getElementById('siElab').checked)
					{handleChange23();}
				if(document.getElementById('artworkElab').checked)
					{handleChange24();}
				if(document.getElementById('leiElab').checked)
					{handleChange25();}
				if(document.getElementById('implantElab').checked)
					{handleChange26();}
				if(document.getElementById('qssElab').checked)
					{handleChange27();}
			}
			else{hideList2_1();
			}
		}
		
		if(document.getElementById('executive').checked)
		{handleChange3();
		//console.log("--------------------esecuzione di handlechange3");
			if(document.getElementById('epElaborate').value == "c")
				{showList3();
				if(document.getElementById('epgElab').checked)
				 {handleChange31();}
				}
			else
				{hideList3();}
			if(document.getElementById('epElaborate').value == "d")
			{showList3_1();
				if(document.getElementById('sepElab').checked)
				 {handleChange32();}
				if(document.getElementById('iepElab').checked)
				 {handleChange33();}
			}
			else
				{hideList3_1();}
			if(document.getElementById('epElaborate').value == "e")
			{showList3_2();
				if(document.getElementById('mpElab').checked)
				 {handleChange34();}
			 }
			else{hideList3_2();}
		}
		
		//console.log(document.getElementById('ppElaborate').value);
		
	});
	});