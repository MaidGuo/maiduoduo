/**
 * Created by Administrator on 2017/5/24.
 */

$(function () {
	 checkedId = new Array();//如果这样定义var //goods_id
	 checkedNum= new Array(); //goods_num

	
	 var k=0;
	 
	 var obj = document.getElementsByName("hidden");//选择所有name="hidden"的对象，返回数组  (得到goodsId)
	 var obj1 = document.getElementsByName("buyNum");//
	 for(var i=0;i<obj.length;i++){
		// alert(obj[i].value+" "+obj1[i].value);
		 checkedId[i]=obj[i].value;
		 checkedNum[i]=obj1[i].value;

     }
	 var obj2=document.getElementsByName("kucun");
	 var $allSize = $('input[name="hidden"]');
	
    //全局的checkbox选中和未选中的样式
    var $allCheckbox = $('input[type="checkbox"]'),     //全局的全部checkbox
        $wholeChexbox = $('.whole_check'),
        $cartBox = $('.cartBox'),                       //每个商铺盒子
        $shopCheckbox = $('.shopChoice'),               //每个商铺的checkbox
        $sonCheckBox = $('.son_check');                 //每个商铺下的商品的checkbox
    $allCheckbox.click(function () {
        if ($(this).is(':checked')) {
            $(this).next('label').addClass('mark');
        } else {
            $(this).next('label').removeClass('mark');
        }
    });

    //===============================================全局全选与单个商品的关系================================
    $wholeChexbox.click(function () {
        var $checkboxs = $cartBox.find('input[type="checkbox"]');
        if ($(this).is(':checked')) {
            $checkboxs.prop("checked", true);
     //得到全选后的id   
        //不为空时
          if(checkedId){	
      	  checkedId.length=0;
          }
        // alert("length "+checkedId.length);
        	   $sonCheckBox.each(function () {
                 // alert(this.value);
                   	checkedId.push(this.value);
                 
               });

            $checkboxs.next('label').addClass('mark');
        } else {
        	 $sonCheckBox.each(function () {
             //    alert(this.value);
                 //单个商品取消勾选，全局全选取消勾选
             	var index = checkedId.indexOf(this.value);
              //删除指定数组元素
             	checkedId.splice(index,1);
                 
              });
            $checkboxs.prop("checked", false);
            $checkboxs.next('label').removeClass('mark');
        }
        totalMoney();
       // alert(checkedId);
    });


    $sonCheckBox.each(function () {
    
        $(this).click(function () {
        
            if ($(this).is(':checked')) {
                //判断：所有单个商品是否勾选
                var len = $sonCheckBox.length;
                var num = 0;
                $sonCheckBox.each(function () {
                    if ($(this).is(':checked')) {
                    	checkedId[k]=this.value;
                    	
                    	var value = $("[name='buyNum']").val();
                       // var index = checkedId.indexOf(this.value);
                    	checkedNum[k]=value;
                    	//alert("in "+k+" "+value);
                      
                    	k++;
                    	num++;
                    }
                });
                if (num == len) {
                    $wholeChexbox.prop("checked", true);
                    $wholeChexbox.next('label').addClass('mark');
                }
            } else {
            	
                //单个商品取消勾选，全局全选取消勾选
            	var index = checkedId.indexOf(this.value);
            	alert("quxiao "+index);
            	checkedId[index]=-1;
            	//checkedId.splice(index,1);
                $wholeChexbox.prop("checked", false);
                $wholeChexbox.next('label').removeClass('mark');
            }
            totalMoney();
          //  alert("ss "+checkedId);;
        });
    });

    //=======================================每个店铺checkbox与全选checkbox的关系/每个店铺与其下商品样式的变化===================================================

    //店铺有一个未选中，全局全选按钮取消对勾，若店铺全选中，则全局全选按钮打对勾。
    $shopCheckbox.each(function () {
        $(this).click(function () {
            if ($(this).is(':checked')) {
            	
                //判断：店铺全选中，则全局全选按钮打对勾。
                var len = $shopCheckbox.length;
                var num = 0;
                $shopCheckbox.each(function () {
                    if ($(this).is(':checked')) {
                        num++;
                    }
                });
                if (num == len) {
                    $wholeChexbox.prop("checked", true);
                    $wholeChexbox.next('label').addClass('mark');
                }

                //店铺下的checkbox选中状态
                $(this).parents('.cartBox').find('.son_check').prop("checked", true);
                $(this).parents('.cartBox').find('.son_check').next('label').addClass('mark');
            } else {
            	
                //否则，全局全选按钮取消对勾
                $wholeChexbox.prop("checked", false);
                $wholeChexbox.next('label').removeClass('mark');

                //店铺下的checkbox选中状态
                $(this).parents('.cartBox').find('.son_check').prop("checked", false);
                $(this).parents('.cartBox').find('.son_check').next('label').removeClass('mark');
            }
            totalMoney();
           
        });
    });


    //========================================每个店铺checkbox与其下商品的checkbox的关系======================================================

    //店铺$sonChecks有一个未选中，店铺全选按钮取消选中，若全都选中，则全选打对勾
    $cartBox.each(function () {
        var $this = $(this);
        var $sonChecks = $this.find('.son_check');
        $sonChecks.each(function () {
            $(this).click(function () {
                if ($(this).is(':checked')) {
                    //判断：如果所有的$sonChecks都选中则店铺全选打对勾！
                    var len = $sonChecks.length;
                    var num = 0;
                    $sonChecks.each(function () {
                        if ($(this).is(':checked')) {
                            num++;
                        }
                    });
                    if (num == len) {
                        $(this).parents('.cartBox').find('.shopChoice').prop("checked", true);
                        $(this).parents('.cartBox').find('.shopChoice').next('label').addClass('mark');
                    }

                } else {
                    //否则，店铺全选取消
                    $(this).parents('.cartBox').find('.shopChoice').prop("checked", false);
                    $(this).parents('.cartBox').find('.shopChoice').next('label').removeClass('mark');
                }
                totalMoney();
             
            });
        });
    });


    //=================================================商品数量==============================================
    var $plus = $('.plus'),
        $reduce = $('.reduce'),
        $all_sum = $('.sum');
    var $a='';
    $plus.click(function () {
        var $inputVal = $(this).prev('input'),
            $count = parseInt($inputVal.val())+1,
            $obj = $(this).parents('.amount_box').find('.reduce'),
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = $count*parseFloat($price.substring(1));
        $inputVal.val($count);
        $a=$(this).parents('.order_lists').find('.hidden'); //获取goods——id
     //  alert($a.val());
        var index = checkedId.indexOf($a.val());

		var jsonData={
			"goodsId":$a.val(), //id
			"goodsNum":$inputVal.val(),	 //数量
		};
        $.ajax({
        	type:"post", 
		 	url:"ShoppingCartAction?flag=update",
		 	dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(jsonData),
            success: function (data) {            	          
            if(data.success=="true"){
            	checkedNum[index]=$inputVal.val();
            	click();
            
            	//  alert($priceTotalObj.html());
            }
            }, error: function (msg) {//ajax请求失败后触发的方法
                alert("错误");//弹出错误信息
            }
        });
    
   
    if($inputVal.val()>1 && $obj.hasClass('reSty')){ //可以减了
        $obj.removeClass('reSty');
    }
	  $priceTotalObj.html('￥'+$priceTotal.toFixed(2));
    totalMoney();
    });
	function click(){
		//window.location.reload();
		location.href = "ShoppingCartAction?flag=show";
	};
    $reduce.click(function () {
        var $inputVal = $(this).next('input'),
            $count = parseInt($inputVal.val())-1,
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = $count*parseFloat($price.substring(1));
        if($inputVal.val()>1){
            $inputVal.val($count);
             $a=$(this).parents('.order_lists').find('.hidden'); //goods——id
          //  alert($inputVal.val()) ;
            var index = checkedId.indexOf($a.val());
            var jsonData={
        			"goodsId":$a.val(),
        			"goodsNum":$inputVal.val(),	
        		};
            $.ajax({
            	type:"post", 
    		 	url:"ShoppingCartAction?flag=update",
    		 	dataType:"json",
    			contentType:"application/json;charset=utf-8",
    			data:JSON.stringify(jsonData),
                success: function (data) {
              //  	alert(data.success)
                   	checkedNum[index]=$inputVal.val();
                   	click();
                	
                }
            });
            
            $priceTotalObj.html('￥'+$priceTotal.toFixed(2));
         
        }
        if($inputVal.val()==1 && !$(this).hasClass('reSty')){
            $(this).addClass('reSty');
        }
        totalMoney();
    	
    });

    $all_sum.keyup(function () {
        var $count = 0,
            $priceTotalObj = $(this).parents('.order_lists').find('.sum_price'),
            $price = $(this).parents('.order_lists').find('.price').html(),  //单价
            $priceTotal = 0.00;
        if($(this).val()==''){
            $(this).val('1');
        }
        $(this).val($(this).val().replace(/\D|^0/g,''));
        $count = $(this).val();
      //  alert($count);
        $priceTotal = $count*parseFloat($price.substring(1));
        $(this).attr('value',$count);
        $priceTotalObj.html('￥'+$priceTotal.toFixed(2));
        totalMoney();
    })

    //======================================移除商品========================================

    var $order_lists = null;
    var $order_content = '';
    var $orderId='';
    $('.delBtn ').click(function () {
        $order_lists = $(this).parents('.order_lists');
        $order_content = $order_lists.parents('.order_content');
        $orderId=$order_lists.find('.hidden');
        swal({  
            title:"您确认要删除该宝贝吗？",  
     
            type:"warning",  
            showCancelButton:"true",  
            showConfirmButton:"true",  
            confirmButtonColor: '#EE5C42',
            cancelButtonColor: '#71C671',
            confirmButtonText: '确定删除',
            cancelButtonText: '取消'
         
          }, function() {    
          $.ajax({  
              type:"post",  
              url:"ShoppingCartAction?flag=delete",  
              dataType:"text",  
              data:{"goodsId":$orderId.val(),},  
              success: function (data) {  
        	
               if(data=="true"){
                swal({	
						title:"删除成功",
  						type:"success",
  						timer:30,
  						showConfirmButton: true,
  					},function(){
  						//window.location.reload();
  						location.href = "ShoppingCartAction?flag=show";
  					});
              }
              }
         
           });  
          });
    });

       // $('.model_bg').fadeIn(300);
        //$('.my_model').fadeIn(300);

    $('.delAll ').click(function () {
    	
        swal({ 
        	  title: '您确认要清空购物车吗？', 
        	  type: 'warning',
        	  showCancelButton: true, 
        	  confirmButtonColor: '#3085d6',
        	  cancelButtonText: '取消',
        	  confirmButtonText: '确定',
        	},function(){
                $.ajax({
                	type:"post", 
        		 	url:"ShoppingCartAction?flag=deleteAll",
        		 	dataType:"text",        		
                    success: function (data) {
                    	
                    	if(data=="true"){
                    		// alert("xxx");
                    		 swal({	
         						title:"删除成功",
         						type:"success",
         						timer:30,
         						showConfirmButton: true,
         					},function(){
         						//window.location.reload();
         						location.href = "ShoppingCartAction?flag=show";
         					});
                    	}
                   
                     },error:function(msg){
                    	 alert("xxx");
                     }
                    	
                });
        	});
     //   alert($orderId.val());
       // $('.model_bg').fadeIn(300);
        //$('.my_model').fadeIn(300);
    });
    //关闭模态框
    $('.closeModel').click(function () {
        closeM();
    });
    $('.dialog-close').click(function () {
        closeM();
    });
    function closeM() {
        $('.model_bg').fadeOut(300);
        $('.my_model').fadeOut(300);
    }
    //确定按钮，移除商品
    $('.dialog-sure').click(function () {
    	alert("xxx");
    		var jsonData={
    			"goodsId":$orderId.val(),
    		};
        $.ajax({
        	type:"post", 
		 	url:"ShoppingCartAction?flag=delete",
		 	dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(jsonData),
            success: function (data) {
            	if(data.success=="true"){
            		 alert(data.success);
                     
            		swal({	
						title:"删除成功",
						type:"success",
						timer:2000,
						showConfirmButton: true,
					},function(){
						//window.location.reload();
						location.href = "ShoppingCartAction?flag=show";
					});
            	}
           
             }
            	
        });
   
    	 /*    if($order_content.html().trim() == null || $order_content.html().trim().length == 0){
            $order_content.parents('.cartBox').remove();
        }*/
       
     //   $sonCheckBox = $('.son_check');
       
    });

    //======================================总计==========================================
  var calBtn = $('.calBtn a');
    function totalMoney() {
        var total_money = 0;
        var total_count = 0;
      
        $allSize.each(function () {
          //  if ($(this).is(':checked')) {
                var goods = parseFloat($(this).parents('.order_lists').find('.sum_price').html().substring(1));
                var num =  parseInt($(this).parents('.order_lists').find('.sum').val());
                total_money += goods;
                total_count += num;
        //    }
        });
        $('.total_text').html('￥'+total_money.toFixed(2));
        $('.piece_num').html(total_count);

        // console.log(total_money,total_count);
//如果有商品
        if(total_money==0 && total_count==0){
        	alert("wu");
        	calBtn.removeClass('btn_sty');
        }
      /*  if(total_money!=0 && total_count!=0){
        	alert("bbb");
        	if(!calBtn.hasClass('btn_sty')){ //在没有这个类的情况下添加这个类
            	alert("aaa");
            	calBtn.addClass('btn_sty');
				
            }

        }else{ //如果没有商品
            if(calBtn.hasClass('btn_sty')){
                calBtn.removeClass('btn_sty');
               calBtn.removeAttr('href');
            }
           
        }*/

    }
	

	 $('.calBtn a').click(function () {
		
		 var str =checkedNum.toString();
		 //alert(str);
			 	//alert("sss "+checkedNum);
			 window.location.href="ConfirmOrderAction?method=order&nums="+str;
			 return false;

});
	
	

});