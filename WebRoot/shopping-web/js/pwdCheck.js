function CharMode(iN){    
    if (iN>=48 && iN <=57) //数字    
        return 1;    
    if (iN>=65 && iN <=90) //大写    
        return 2;    
    if (iN>=97 && iN <=122) //小写    
        return 4;    
    else    
        return 8;     
}  
//bitTotal函数    
//计算密码模式    
function bitTotal(num){    
   modes=0;    
   for (i=0;i<4;i++){    
        if (num & 1) modes++;    
        num>>>=1;    
    }  
    return modes;    
}  
//返回强度级别    
function checkStrong(sPW){    
   if (sPW.length<6)    
        return 0; //密码太短，不检测级别  
    Modes=0;    
    for (i=0;i<sPW.length;i++){    
        //密码模式    
        Modes|=CharMode(sPW.charCodeAt(i));    
    }  
    return bitTotal(Modes);    
}  

//显示颜色    
function pwStrength(pwd){    
    Dfault_color="#eeeeee";     //默认颜色  
    L_color="#FF0000";      //低强度的颜色，且只显示在最左边的单元格中  
    M_color="#FF9900";      //中等强度的颜色，且只显示在左边两个单元格中  
    H_color="#33CC00";      //高强度的颜色，三个单元格都显示  
    if (pwd==null||pwd==''){    
        Lcolor=Mcolor=Hcolor=Dfault_color;  
    }    
    else{    
        S_level=checkStrong(pwd);    
        switch(S_level) {    
        case 0:    
            Lcolor=Mcolor=Hcolor=Dfault_color;  
            break;  
        case 1:    
            Lcolor=L_color;  
            Mcolor=Hcolor=Dfault_color;  
            break;    
        case 2:    
            Lcolor=Mcolor=M_color;    
            Hcolor=Dfault_color;    
            break;    
        default:    
            Lcolor=Mcolor=Hcolor=H_color;    
    }    
}    
document.getElementById("strength_L").style.background=Lcolor;    
document.getElementById("strength_M").style.background=Mcolor;    
document.getElementById("strength_H").style.background=Hcolor;    
return;  
}  
function checkPWD(pwd){
	var oldpwd=document.getElementById("nowpwd").value;
	if(oldpwd!=pwd){
			document.getElementById("tips1").innerText="当前登录密码错误，身份验证失败!";
			form.nowpwd.focus();
	}else{
			document.getElementById("tips1").innerText="密码正确！身份验证成功！";
	}
}
function checkAnswer(answer){
	var checkansw=document.getElementById("checkanswer").value;
	if(checkansw!=answer){ 
		//	alert("答案错误，身份验证失败!");
			document.getElementById("tips2").innerText="答案错误，身份验证失败!";
			form.checkanswer.focus();
		}else{
			document.getElementById("tips2").innerText="答案正确！";
	}
		
}
function checkPWDlength(){
	var newpwd=document.getElementById("newpwd").value;
	if(newpwd.length<6){
		//	alert("新密码不能为空!");
			document.getElementById("tips3").innerText="新密码不能少于6位!";
			form.newpwd.focus();
		}else{
			document.getElementById("tips3").innerText="";
		}
}

function checkNewPwd(){
	var newpwd=document.getElementById("newpwd").value;
	var checknewpwd=document.getElementById("checknewpwd").value;
	if( checknewpwd != newpwd){//手机  
		 //	alert("确认密码应和新密码相同!");
			document.getElementById("tips4").innerText="确认密码应和新密码相同!";
			form.checknewpwd.focus();
		 }else{
			 document.getElementById("tips4").innerText="";
		 }  
		
}

function check(pwd,answer){ 
	
	var oldpwd=form.nowpwd.value;
	var checkansw=form.checkanswer.value;
	var newpwd=form.newpwd.value;
	var checknewpwd=form.checknewpwd.value;
	if(oldpwd!=pwd){
	//	alert("当前登录密码错误，身份验证失败!");
		document.getElementById("tips1").innerText="当前登录密码错误，身份验证失败!";
		form.nowpwd.focus();
		return false;
	}else{
		document.getElementById("tips1").innerText="";
	}
	if(checkansw!=answer){ 
	//	alert("答案错误，身份验证失败!");
		document.getElementById("tips2").innerText="答案错误，身份验证失败!";
		form.checkanswer.focus();
		return false;
	}else{
		document.getElementById("tips2").innerText="";
	}
	
	if(newpwd.length<6){
	//	alert("新密码不能为空!");
		document.getElementById("tips3").innerText="新密码不能少于6位!";
		form.newpwd.focus();
		return false;
	}else{
		document.getElementById("tips3").innerText="";
	}
	if( checknewpwd != newpwd){//手机  
	 //	alert("确认密码应和新密码相同!");
		document.getElementById("tips4").innerText="确认密码应和新密码相同!";
		form.checknewpwd.focus();
		return false;
	 }else{
		 document.getElementById("tips4").innerText="";
	 }  
	
	return true;
}