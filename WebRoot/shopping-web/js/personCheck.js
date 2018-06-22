function checkaddr(){
	var province=document.getElementById("s_province").value;
	var city=document.getElementById("s_city").value;
	var county=document.getElementById("s_county").value;
	if(province=="省份"){
			document.getElementById("tips5").innerText="请选择省";
	}else if(city=="地级市"){
			document.getElementById("tips5").innerText="请选择市";
	}else if(county=="市、县级市"){
			document.getElementById("tips5").innerText="请选择区";
	}else{
			document.getElementById("tips5").innerText="";
	}
}
function checkNickname(){
	var nname=document.getElementById("nickname").value;
	var reg = /^[\u0391-\uFFE5]+$/;
	if(nname.length<2){
		//	alert("昵称至少应为两个汉字!");
			document.getElementById("tips1").innerText="昵称至少应为两个汉字!";
			form.nickname.focus();
		}else if(!reg.test(nname)){
		//	alert("只能输入中文！");
			document.getElementById("tips1").innerText="*只能输入中文!";
		}else {
			document.getElementById("tips1").innerText="";	
		}
}
function checkRealname(){
	var rname=document.getElementById("realname").value;
	var reg = /^[\u0391-\uFFE5]+$/;
	if(rname.length<2){ 
		//	alert("姓名不能少于两个字符!");
			document.getElementById("tips2").innerText="姓名不能少于两个字符!";
			form.realname.focus();
		}else if(!reg.test(rname)){
			document.getElementById("tips2").innerText="*只能输入中文!";
		}else{
			document.getElementById("tips2").innerText="";	
		}
}
function checkMail(){
	var mail=document.getElementById("mail").value;
	var mailcheck = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	if(!mailcheck.test(mail)){
			document.getElementById("tips3").innerText="请输入正确的邮箱!";
			form.mail.focus();
		}else{
			document.getElementById("tips3").innerText="";
		}
}
function checkTel(){
	var phone=document.getElementById("phone").value;
	var mobile = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;  
	if(!mobile.test(phone)){           
	    document.getElementById("tips4").innerText="请输入正确的手机号码!";
	    form.phone.focus();
    }else{
    	document.getElementById("tips4").innerText="";
    }  
}
function check(){ 
	
	var nname=form.nickname.value;
	var rname=form.realname.value;
	var mail=form.mail.value;
	var phone=form.phone.value;
	var province=document.getElementById("s_province").value;
	var city=document.getElementById("s_city").value;
	var county=document.getElementById("s_county").value;
	var mailcheck = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	var reg = /^[\u0391-\uFFE5]+$/;
	if(nname.length<2){
	//	alert("昵称至少应为两个汉字!");
		document.getElementById("tips1").innerText="昵称至少应为两个汉字!";
		form.nickname.focus();
		return false;
	}else if(!reg.test(nname)){
		document.getElementById("tips1").innerText="*只能输入中文!";
		return false;
	}else {
		document.getElementById("tips1").innerText="";	
	}
	if(rname.length<2){ 
		//	alert("姓名不能少于两个字符!");
			document.getElementById("tips2").innerText="姓名不能少于两个字符!";
			form.realname.focus();
			return false;
		}else if(!reg.test(rname)){
			document.getElementById("tips2").innerText="*只能输入中文!";
			form.realname.focus();
			return false;
		}else{
			document.getElementById("tips2").innerText="";	
		}
	if(province=="省份"){
		document.getElementById("tips5").innerText="请选择省";
		return false;
	}else if(city=="地级市"){
		document.getElementById("tips5").innerText="请选择市";
		return false;
	}else if(county=="市、县级市"){
		document.getElementById("tips5").innerText="请选择区";
		return false;
	}else{
		document.getElementById("tips5").innerText="";
	}
	if(!mailcheck.test(mail)){
	//	alert("请输入正确的邮箱!");
		document.getElementById("tips3").innerText="请输入正确的邮箱!";
		form.mail.focus();
		return false;
	}else{
		document.getElementById("tips3").innerText="";
	}
	var mobile = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;  
	if(!mobile.test(phone)){           
	    document.getElementById("tips4").innerText="请输入正确的手机号码!";
	    form.phone.focus();
		return false;  
    }else{
    	document.getElementById("tips4").innerText="";
    }  
	 
	return true;
}