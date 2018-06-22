function checkTitle(){
	var title=document.getElementById("title").value;
	if( title==""){//手机  
		document.getElementById("tips1").innerText="标题不能为空!";
		form.title.focus();
	 }else{
		 if(title.length>15){
				document.getElementById("tips1").innerText="*15个字符以内";
				form.title.focus();
				return false;
			}else{
				document.getElementById("tips1").innerText="";
			}
	 }  
		
}

function checkDescription(){
	var descript=document.getElementById("descript").value;
	if(descript==""){
		document.getElementById("tips2").innerText="问题描述不能为空!";
		form.descript.focus();
	}else{
		if(descript.length>250){
			document.getElementById("tips2").innerText="*250个字符以内";
			form.descript.focus();
		}else{
			document.getElementById("tips2").innerText="";
		}
	}
}

function check(){ 
	var title=form.title.value;
	var descript=form.descript.value;
	if(title==""){
		document.getElementById("tips1").innerText="标题不能为空!";
		form.title.focus();
		return false;
	}else{
		if(title.length>15){
			document.getElementById("tips1").innerText="*15个字符以内";
			form.title.focus();
			return false;
		}else{
			document.getElementById("tips1").innerText="";
		}
	}
	
	if(descript==""){
		document.getElementById("tips2").innerText="问题描述不能为空!";
		form.descript.focus();
		return false;
	}else{
		if(descript.length>250){
			document.getElementById("tips2").innerText="*250个字符以内";
			form.descript.focus();
		}else{
			document.getElementById("tips2").innerText="";
		}
	}
	
	
	return true;
}