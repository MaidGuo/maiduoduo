$(document).ready(function(){

	// $.validator.addMethod("chcharacter", function(value, element) {
	// 	var tel = /^[u4e00-u9fa5]+$/;
	// 	return this.optional(element) || (tel.test(value));
	// }, "请输入汉字");
	$("#register-form").validate({
		
	    rules: {
	      name: {
	      	chcharacter:true,
	        required: true,
	        minlength: 2,
	        remote:{                                          //验证用户名是否存在
	               type:"POST",
	               url:"ValidateNameAction",             //servlet
	               data:{
	                 name:function(){return $("#name").val();}
	               },
	               dataType: "html",
	               dataFilter: function(data, type) {
	                 if (data == "false"){ //已经存在
	                	 console.log(data);
	                	 return true;
	                 
	                 }
	                 else
	                   return false;
	          	        }
	          	       }  
	      },
	      pwd: {
	        required: true,
	        minlength: 6
	      },
	      email: {
		        required: true,
		        email: true
		      },
		      mibao: {
		        required: true,
		        minlength:1,
		      },
		      mbanswer:{
					required:true,
					rangelength:[2,19]
				},
		      youbian: {
		      	isZipCode:true,
		        required: true,
		        minlength: 6,
		        maxlength: 6
		      },
		      tel: {
			      	isMobile:true,
			        required: true,
			        minlength: 11,
			        maxlength: 11
			      },
			   truename:{
				   required:true,
				   chcharacter:true  
			   },
			      city:{
			    	  required:true,
			      },
		   town:{
			   required:true,
			   address:true,
		   }
			      
	    },
	    messages: {
	    	name: {
	    		required:"用户名不能少于两位",
	    		chcharacter:"请输入汉字",
	    		remote:"用户名已存在"
	    	},
	    	pwd: "密码不能少于六位",
	    	 email: "请输入一个正确的邮箱",
	    	 mibao:{ required:"请选择",			  
				  },
			mbanswer:"请输入2-19个字符",
			        youbian:{
			        	required:"请输入六位邮政编码",
			        	isZipCode:"输入正确邮编",
			        },
			tel: {
						required:"请输入11位电话号码",
			        	isMobile:"请输入正确的电话号码",
			        },
			 truename: {required:"姓名最少两位",
						 chcharacter:"请输入汉字",
						  },
			city:{
					    	  required:"请选择",
					      },
			 town: {required:"请输入你的详细收货地址",
				 	address:"请输入汉字或数字",
			  }
						 
		},
	    errorPlacement: function(error, element) {
	    	//error是错误提示元素span对象  element是触发错误的input对象
        	error.appendTo(element.nextAll(".error-tip"));
		},
	    submitHandler: function(form) {

			var jsonData={
				"name":$("input[name='name']").val(),
				"pwd":$("input[name='pwd']").val(),	
				"gender":$("input[name='gender']:checked").val(),	
				"email":$("input[name='email']").val(),
				"mibao":$("select[name='mibao']").val(),
				"mbanswer":$("input[name='mbanswer']").val(),
				"youbian":$("input[name='youbian']").val(),
				"tel":$("input[name='tel']").val(),
				"truename":$("input[name='truename']").val(),
				"sex":$("input[name='gender']").val(),
				"city":$("input[name='city']").val(),
				"town":$("input[name='town']").val(),
			
			};
			$.ajax({
				type:"post", 
			 	url:"UserRegisterAction",
			 	dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify(jsonData),
				success:function(data){			
					var data = eval(data);
					if(data.success=="true"){
						swal({	
							title:"注册成功",
							text: "请查收激活邮件",
							type:"success",
							showConfirmButton: true,
						},function(){
							window.location.reload();
							location.href = "shopping-web/jsp/homePage.jsp";
						});
						
					} else {
						swal({
							title:"注册失败",
							text: "请检查信息填写",
							type:"error",
							showConfirmButton: true,
						})
					} 
				}
				
			}); //---ajax
		}//---submit
	});
})