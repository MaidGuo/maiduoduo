$(document).ready(function(){

	var codeflag = 0;
	//验证码
	var verifyCode = new GVerify("codearea");
	$("#code-input").blur(function(){

		var res = verifyCode.validate($("#code-input").val());
		if(res){

			$(".code-tip").css("color","green");
			$(".code-tip").removeClass('glyphicon-remove-sign');
			$(".code-tip").addClass('glyphicon-ok-sign');
			codeflag = 1;
			console.log("IN_FLAG"+codeflag);
		}else{
			codeflag = 0;
			$(".code-tip").css("color","red");
			$(".code-tip").removeClass('glyphicon-ok-sign');
			$(".code-tip").addClass('glyphicon-remove-sign');
			
		}
	});

	$(".login-btn").bind("click",function(){
		
		if(codeflag != 1){
			swal({
					title:"验证码错误",
					type:"error",
					showConfirmButton: true,
				});

			return;
		}
		if($("input[name='username']").val() == "" || $("input[name='password']").val() == ""){
			swal({
					title:"用户名或密码不能为空",
					type:"error",
					showConfirmButton: true,
				});

			return;
		}else{
			  var curWwwPath=window.document.location.href;  
		      //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp  
		      var pathName=window.document.location.pathname;  
		      var pos=curWwwPath.indexOf(pathName);  
		      //获取主机地址，如： http://localhost:8083  
		      var localhostPaht=curWwwPath.substring(0,pos);  
		      //获取带"/"的项目名，如：/uimcardprj  
		      var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
		   
			var jsonData={
					"name":$("input[name='username']").val(),
					"pwd":$("input[name='password']").val()
				};
				$.ajax({
					type:"post", 
				 	url:"UserLoginAction",
				    dataType:"json",
					contentType:"application/json;charset=utf-8",
					data:JSON.stringify(jsonData),
					success:function(data){
						console.log(data);
						  alert(data.uid);
						if(data.success=="true"){
							//setCookie('userid',data.uid);
						  
							swal({
								title:"登录成功,祝您购物愉快",
								text:"正在前往购物页面",
								type:"success",
								showConfirmButton: true
								},function(){
								
								location.href = projectName+"/servlet/WebGoodsManageAction";	
							});
							
						}else if (data.success == "unregister"){

							swal({
								title: "你还没有注册，请先注册再登录",
								confirmButtonText: "前往",
								type: "info",
								showCancelButton: true,
								closeOnConfirm: false,
								showLoaderOnConfirm: true,
							}, function(){
							   swal({
									title:"正在前往注册页面",
									type:"success",
									timer:2000
								},function(){
									location.href = projectName+"shopping-web/jsp/register.jsp";
									
								});
							});
						}
						else if(data.success == "notActive"){
							swal({
								title:"该用户未激活，请前往邮箱验证激活",
								type:"error",
								showConfirmButton: true,
							});
						}
						else{
							swal({
								title:"用户名或密码输入错误",
								type:"error",
								showConfirmButton: true,
							});
							
						}
					} 
				}); 
			
		}
})		
		
	
})

