<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买多多-确认订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
  <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/shopping-web/img/little_logo.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
  
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/base.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/checkOut.css" />
 <script src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js"></script>

 <script src="<%=path %>/shopping-web/js/option.js"></script>
  <script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
  </head>
  
  <body>

 <div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
         <%
   
  		ArrayList<ReceiveAddress> addressList=(ArrayList<ReceiveAddress>)request.getAttribute("addressList");
  		if(addressList==null){
  		addressList=new ArrayList<ReceiveAddress>();
  		}
  		UserInfo user=(UserInfo)session.getAttribute("user");
  		if(user==null){
  		user=new UserInfo();
  		}
  		int id=user.getId();
  		System.out.println(addressList.size()+"--111");
  		%>
  <div class="frdiv">
   <ul class="nav1">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a class="on" href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
   <div class="border_top_cart">
  
<div class="container">
    <div class="checkout-box">
        <div class="checkout-box-bd">
              <!-- 收货地址 -->
                <div class="xm-box">
                    <div class="box-hd ">
    <h2 class="title">收货地址</h2>
  
</div>
 <div class="box-bd">
    <div class="clearfix xm-address-list" id="checkoutAddrList">
  <% 
          for(int i=0;i<addressList.size();i++){
           		ReceiveAddress address=addressList.get(i);
        
           %>
          <ul class="item" >
         <li class="item-check"><input type="radio" id="radio<%=i %>" name="radio" value="<%=address.getIsdefault()%>"> </li>
           <label for="radio<%=i%>">
           <li> <p class="itemRegion"><%=address.getProvince() %> <%=address.getCity() %> <%=address.getCounty() %> </p></li>
            <li> <p class="itemStreet">&nbsp;<%=address.getAddress() %></p></li>
            <li>
                <p class="itemConsignee">(<strong class="rname"><%=address.getName() %> </strong> 收)</p>
             </li>
            <input type="hidden" class="postCode" value="<%=address.getPostcode() %>">
            <li>   
				<strong class="tel itemTel"><%=address.getTel() %></strong>
             </li>
               </label>
               <!--  <li>  <span class="edit-btn J_editAddr">编辑</span></li>
          
            <li style="display:none">
                <input type="radio" name="Checkout[address]" class="addressId"  value="">
            </li>-->
        </ul>
     
     <%} System.out.print("------"+id);%>
       
          <div class="item use-new-addr"  id="J_useNewAddr" >
            
             <span class="iconfont icon-add"><!--  <img src="<%=path %>/shopping-web/img/+.png" />--></span> 
			 <a href="<%=path %>/servlet//AddressGetAction?userid=<%=id %>" target="_Blank">管理收货地址</a>
        </div>
        
          
              <script type="text/javascript">
        var radio = document.getElementsByName('radio');
        	
        for(var i=0;i<radio.length;i++){
        	if(radio[i].value==1){
        	
        		 radio[i].checked ='checked';
        	}
        }
        </script>
</div>
 
            <!-- 收货地址 END-->
       
                  <div id="checkoutPayment">
                    <!-- 支付方式 -->
                 <div class="xm-box">
                <div class="box-hd ">
                    <h2 class="title">支付方式</h2>
                </div>
                <div class="box-bd">
                    <ul id="checkoutPaymentList" class="checkout-option-list clearfix ">
                          <li class="item selected" >  <a href="javascript:;" class="pay" value="1">在线支付</a>   <span></span>
                        </li>
                             <li class="item">
                           
                          <a href="javascript:;" class="pay" value="2">货到付款</a><span></span>
                        </li>
                          </ul>
                          	
                  
                </div>
            </div>
            
				</div>
            <!-- 支付方式 END-->
           
            
                <!-- 送货时间 -->
                <div class="xm-box">
                    <div class="box-hd">
                        <h2 class="title">送货时间</h2>
                    </div>
                    <div class="box-bd">
                        <ul class="checkout-option-list clearfix J_optionList" id="checkoutPayTime">
                                                        <li class="item selected"><a href="javascript:;" class="time" value="1">  不限送货时间<span>周一至周日</span></a></li>
                                                        <li class="item "><a href="javascript:;" class="time" value="2">  工作日送货<span>周一至周五</span></a></li>
                                                        <li class="item "><a href="javascript:;" class="time" value="3">  双休日、假日送货<span>周六至周日</span></a></li>
                           </ul>
                    </div>
                </div>
                <!-- 送货时间 END-->

            </div>
          
          <div class="checkout-box-ft">
                 <!-- 商品清单 -->
                <div id="checkoutGoodsList" class="checkout-goods-box">
                                    <div class="xm-box">
                    <div class="box-hd">
                        <h2 class="title">确认订单信息</h2>
                    </div>
                    <div class="box-bd box_list">
                        <dl class="checkout-goods-list">
                            <dt class="clearfix">
                                <span class="col col-1">商品名称</span>
                                <span class="col col-2">单价</span>
                                <span class="col col-3">数量</span>
                                <span class="col col-4">小计（元）</span>
                            </dt>
                            <%
                            ArrayList<GoodsInfo> carList=(ArrayList<GoodsInfo>)session.getAttribute("cart");
                            if(carList==null){
                            	carList=new ArrayList<GoodsInfo>();
                            }
                            for(int i=0;i<carList.size();i++){
                            	GoodsInfo goods=carList.get(i);
                             %>
                                <dd class="item clearfix">
                                <div class="item-row">
                                    <div class="col col-1">
                                        <div class="g-pic">
                                          <a href="#">  <img src="<%=path+goods.getImg_path() %>" width="40" height="40" /></a>
                                        </div>
                                        <div class="g-info">
                                            <a href="#"> <%=goods.getGoods_name() %>  </a>
                                       </div>
                                    </div>
                
                                    <div class="col col-2 price">¥<%=goods.getGoods_price() %></div>
                                    <div class="col col-3"><%=goods.getBuyNum() %></div>
                                    <div class="col col-4 sum_price">¥<%=goods.getGoods_price()*goods.getBuyNum() %></div>
                                </div>
                            </dd>
                            <% }%>
                            
                        <div class="checkout-count clearfix">
                            <div class="checkout-count-extend xm-add-buy">
                                <h3 class="title">会员留言</h3></br>
                                <input type="text"  id="message" value=""/>

                            </div>
                            <!-- checkout-count-extend -->
                            <div class="checkout-price">
                                <ul>
                                    <li >
                                       订单总额：<span class="total_maoney"></span>
                                    </li>
                                    <li>
                                        活动优惠：<span>-0元</span>
                                        
                                    </li>
                                    <li>
                                        优惠券抵扣：<span id="couponDesc">-0元</span>
                                    </li>
                                    <li>
                                        运费：<span id="postageDesc">0元</span>
                                    </li>
                                </ul>
                                <p class="checkout-total ">应付总额：<span><strong id="totalPrice"></strong>元</span></p>
                            </div>
                            <!--  -->
                        </div>
                    </div>
                </div>
            
                </div>
             <!-- 商品清单 END -->
                <input type="hidden"  id="couponType" name="Checkout[couponsType]">
                <input type="hidden" id="couponValue" name="Checkout[couponsValue]">
                <div class="checkout-confirm">
                    
                     <a href="<%=path %>/shopping-web/jsp/shoppingCart.jsp" class="btn btn-lineDakeLight btn-back-cart">返回购物车</a>
                  <a href="javascript:;" class="btn btn-primary"id="wancheng"> 立即下单</a>

                </div>
            </div>
        </div>

  

</div>     
           
   
</div>
 </div>
 </div>
 <!--收货地址body部分结束-->
         <div class="model_bg"></div>
        <div class="my_model">
        <p class="title">请输入支付密码<span class="closeModel">X</span></p>
        <!-- <p class="m_img"> <img src="img/cartimg/yes_ok.png" ></p> -->
        <p class="m_text"><input type="password" name="password"></p>
       <!--   <p class="m_text"> 请耐心等待~</p>-->
        <div class="opBtn"><a href="javascript:;" class="dialog-sure">取消</a><a href="javascript:;" class="dialog-order">支付</a></div>
             </div>
      
      
   <script src="orderjs/option.js"></script>
  </body>
</html>
