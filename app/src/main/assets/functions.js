var s =[false,false,false,false,false];

function disableClick(quest) {
	document.getElementById(quest+'A').onclick=  null;
	document.getElementById(quest+'B').onclick=  null;
	document.getElementById(quest+'C').onclick=  null;
	document.getElementById(quest+'D').onclick=  null;

}
function right(id , quest) {
	document.getElementById(quest+id).style.backgroundColor = '#c8e6c9';
	disableClick(quest);
	document.getElementById('showAnsTag'+quest).style.display='block';	
}

function wrong(id,quest,rightId) {
	document.getElementById(quest+id).style.backgroundColor = '#ffcdd2';
	document.getElementById(quest+rightId).style.backgroundColor = '#c8e6c9';	
	disableClick(quest);
	document.getElementById('showAnsTag'+quest).style.display='block';
		
}

function show(quest) {
	var q = parseInt(quest)-1;
	s[q] = !s[q];

	if(s[q]==true) {
		document.getElementById('ans'+quest).style.display='block';
		document.getElementById('showAnsTag'+quest).innerHTML="Hide Answer";
	}
	else {
		document.getElementById('ans'+quest).style.display='none';
		document.getElementById('showAnsTag'+quest).innerHTML="Show Answer";
	}
}
