function checkTheme(){
	var theme=document.getElementById("theme").value;
	if(theme==""){
		document.getElementById("tips2").innerText="主题不能为空!";
		form.theme.focus();
	}else{
		if(theme.length>15){
			document.getElementById("tips2").innerText="*15个字符以内";
			form.theme.focus();
		}else{
			document.getElementById("tips2").innerText="";
		}
	}
}
function checkContent(){
	var content=document.getElementById("content").value;
	if(content==""){
		document.getElementById("tips3").innerText="评价内容不能为空!";
		form.content.focus();
	}else{
		if(content.length>250){
			document.getElementById("tips3").innerText="*250个字符以内";
			form.content.focus();
		}else{
			document.getElementById("tips3").innerText="";
		}
	}
}
function check(){ 
	
	var theme=form.theme.value;
	var content=form.content.value;
	if(theme==""){
		document.getElementById("tips2").innerText="主题不能为空!";
		form.theme.focus();
		return false;
	}else{
		if(theme.length>15){
			document.getElementById("tips2").innerText="*15个字符以内";
			form.theme.focus();
			return false;
		}else{
			document.getElementById("tips2").innerText="";
		}
	}

	if(content==""){
		document.getElementById("tips3").innerText="评价内容不能为空!";
		form.content.focus();
		return false;
	}else{
		if(content.length>250){
			document.getElementById("tips3").innerText="*250个字符以内";
			form.content.focus();
		}else{
			document.getElementById("tips3").innerText="*250个字符以内";
		}
	}
	return true;
}