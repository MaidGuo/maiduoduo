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
function checkAddress(){
	var detaddress=document.getElementById("detaddress").value;
	var reg = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+$");
	if(detaddress==""){
//		alert("详细地址不能为空！");
		document.getElementById("tips1").innerText="详细地址不能为空!";
		form.detaddress.focus();
	}else{
		if(!reg.test(detaddress)){
			document.getElementById("tips1").innerText="只能输入中文和,英文，数字";
			form.detaddress.focus();
		}else{
//		alert("输入正确！");
			document.getElementById("tips1").innerText="";
		}	
	}
	
}

function checkPostcode(){
	var postcode=document.getElementById("postcode").value;
	var checkpcode=/[0-9]{6}$/;
	if(postcode==""){
		document.getElementById("tips2").innerText="邮政编码不能为空!";
		form.postcode.focus();
	}else{
		if(!checkpcode.test(postcode)||postcode.length!=6){
			document.getElementById("tips2").innerText="*邮政编码由6位数字组成";
			form.postcode.focus();
		}else{
			document.getElementById("tips2").innerText="";
		}
	}
		
}
function checkCustomer(){
	var customer=document.getElementById("customer").value;
	var reg = /^[\u0391-\uFFE5]+$/;
	if(customer==""){
		document.getElementById("tips3").innerText="收货人姓名不能为空!";
		form.customer.focus();
	}else if(!reg.test(customer)){
		//	alert("只能输入中文！");
		document.getElementById("tips3").innerText="*只能输入中文!";
	}else{
			document.getElementById("tips3").innerText="";
	}
}
function checkPhone(){
	var phone=document.getElementById("phone").value;
	var mobile = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;  
	if(!mobile.test(phone)){           
		 document.getElementById("tips4").innerText="请输入正确的手机号码!";
		 form.phone.focus();
		 return false;  
	}else{
	     document.getElementById("tips4").innerText="";
	}  
}
function check(size){ 
	var detaddress=form.detaddress.value;
	var postcode=form.postcode.value;
	var customer=form.customer.value;
	var phone=form.phone.value;
	var checkpcode=/[0-9]{6}$/;
	var province=document.getElementById("s_province").value;
	var city=document.getElementById("s_city").value;
	var county=document.getElementById("s_county").value;
	var reg = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+$");
	if(size>=20){
		swal({
		    title:"新增地址失败",    
		    text: "你已添加20个地址，还可添加的地址数量为0",
		    type: "error",    
		    confirmButtonText: "确定" 
		})
		return false;
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
	if(detaddress==""){
		document.getElementById("tips1").innerText="详细地址不能为空!";
		form.detaddress.focus();
		return false;
	}else{
		if(!reg.test(detaddress)){
			document.getElementById("tips1").innerText="只能输入中文和,英文，数字";
			form.detaddress.focus();
			return false;
		}else{
			document.getElementById("tips1").innerText="";
		}	
	}
	
	if(postcode==""){
		document.getElementById("tips2").innerText="邮政编码不能为空!";
		form.postcode.focus();
		return false;
	}else{
		if(!checkpcode.test(postcode)||postcode.length!=6){
			document.getElementById("tips2").innerText="*邮政编码由6位数字组成";
			form.postcode.focus();
			return false;
		}else{
			document.getElementById("tips2").innerText="";
		}
	}
	var reg1 = /^[\u0391-\uFFE5]+$/;
	if(customer==""){
		document.getElementById("tips3").innerText="收货人姓名不能为空!";
		form.customer.focus();
		return false;
	}else if(!reg1.test(customer)){
		//	alert("只能输入中文！");
		document.getElementById("tips3").innerText="*只能输入中文!";
	}else {
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
//	return false;
	return true;
}