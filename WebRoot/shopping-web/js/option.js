	$(document).ready(function(){
	
    $("#checkoutPaymentList li").click(function() {
						$(this).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
						$(this).addClass('selected'); // 添加当前元素的样式
					
    });
		$("#checkoutPayTime li").click(function() {
						$(this).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
						$(this).addClass('selected');                            // 添加当前元素的样式

    });
		
		    var total_money = 0;       
   
        $('.item-row').each(function () {
                var goods = parseFloat($(this).find('.sum_price').html().substring(1));  
                total_money += goods;
              
            
        });
        $('.total_maoney').html('￥'+total_money.toFixed(2));
	 $('#totalPrice').html('￥'+total_money.toFixed(2));
		
	 var $itemRegion="";
	 var $sendtime=0;
	 var $itemStreet="";
	 var $itemConsignee="";	
	 var $tel="";
	 var $postCode="";
	 var $payment=0.00;
	 var $paymentType="";
		$('#wancheng').click(function(){
			var $addressList =$('input[type="radio"]');
			var len =$addressList.length;
			if(len==0){
				swal({	
					title:"请选择地址",
					text:"正在前往地址管理页面",
					type:"error",
					showConfirmButton: true,
					timer:2000
				},function(){
					window.open = "shopping-web/jsp/register.jsp";
					
				});
			}else{
				 for(var i=0;i<len;i++){
			        	if(radio[i].checked ==true){
			        	
			        		$itemRegion = $(radio[i]).parents('.item').find('.itemRegion').text();//省市区
			        	//	alert($itemRegion);
			        		$itemStreet=$(radio[i]).parents('.item').find('.itemStreet').text();//详细地址
			        		$itemConsignee=$(radio[i]).parents('.item').find('.rname').text();//收货人姓名
			        		//alert($itemConsignee);
			        		$tel=$(radio[i]).parents('.item').find('.tel').text();//收货人电话
			        		$postCode=$(radio[i]).parents('.item').find('.postCode').val();//邮编
			        	
			        	}
			        }
				 $paymentObj = $('#checkoutPaymentList').find('.selected'),	
				 $paymentType=$paymentObj.find('.pay');			//支付方式	 
				 
				 $paytimeObj = $('#checkoutPayTime').find('.selected'),	
				 $sendtime=$paytimeObj.find('.time');//支付时间			
				 $message=$("#message").val(); //会员留言
				 $payment=total_money.toFixed(2);
			}

       $('.model_bg').fadeIn(300);
        $('.my_model').fadeIn(300);
        var v='';
        //判断密码是否为空
        $(":password").blur(function(){
            v= $(this).val();
            $(this).next().remove();  
            if(v==""){
          
                $(this).parent().append("<font color='red'>"+" 密码不能为空");
            }
		});
    //关闭模态框
    $('.closeModel').click(function () {
        closeM();
    });
    //支付
    $('.dialog-order').click(function () {
 // alert("xxx+"+v);
  if(v!=""){
	  var jsonData={		
			  			"pwd":$("input[name='password']").val(),
						"address":$itemRegion,	
						"diqu":$itemStreet,
						"rname":$itemConsignee,
						"youbian":$postCode,
						"tel":$tel,
						"paymentType":$paymentType.attr("value"),
						"sendtime":$sendtime.attr("value"),		
						"message":$message,
						"payment":$payment,
			};
    	  $.ajax({
            	type:"post", 
    		 	url:"ConfirmOrderAction?method=pay",
    		 	dataType:"json",
    			contentType:"application/json;charset=utf-8",
    			data:JSON.stringify(jsonData),
    			 success: function (data) {
    				
    				 if(data.success=="true"){
    					swal({	
    						title:"支付成功",
    						text:"正在前往订单页面",
    						type:"success",
    						timer:2000,
    						showConfirmButton: true,
    					},function(){
    						//window.location.reload();
    					    closeM();
    					    var curWwwPath=window.document.location.href;  
    					      //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp  
    					      var pathName=window.document.location.pathname;  
    					      var pos=curWwwPath.indexOf(pathName);  
    					      //获取主机地址，如： http://localhost:8083  
    					      var localhostPaht=curWwwPath.substring(0,pos);  
    					      //获取带"/"的项目名，如：/uimcardprj  
    					      var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    						location.href =projectName+"/servlet/OrderAction";
    					});
    				}else if(data.success=="false"){
						swal({
							title:"支付密码错误",
							type:"error",
							showConfirmButton: true,
						});
						
					}else if(data.success=="payError"){
						swal({
							title:"支付失败",
							type:"error",
							showConfirmButton: true,
						});
					}
    			 }
      
      }); // --AJAx结束
    	  closeM();
  }
 
    
    
    });
    function closeM() {
    	
        $('.model_bg').fadeOut(300);
        $('.my_model').fadeOut(300);
    }
    //取消
    $('.dialog-sure').click(function () { 
        closeM(); 
    });	
});
});

 //======================================总计==========================================


	
    
      

     
	
		
	
		
		
