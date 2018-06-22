<%@ page language="java" import="java.util.*,domain.*,common.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买多多-购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
   <link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/bootstrap/css/bootstrap.min.css">
   <link  rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/carts.css">
   <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/shopping-web/img/little_logo.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
	
 <script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>

  </head>
  
  <body>
  <%
  	UserInfo user=(UserInfo)session.getAttribute("user");

 	ArrayList<GoodsInfo> carList=(ArrayList<GoodsInfo>)session.getAttribute("cart");
  //System.out.print("sda "+carList.get(0).getBuyNum());

  	if(user==null)
  		user=new UserInfo();
  	if (carList==null){
  		carList=new ArrayList<GoodsInfo>();
  }
  	int id=user.getId();
	String pwd=user.getPwd();
	System.out.print("---id:--pwd"+id+" "+pwd);

   %>
   <div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
   <ul class="nav1">
    <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a class="on" onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
 <div id="car">
    <div class="car-container">
        <ul class="nav nav-tabs">
            <li class="active"><a  href="##">待付款</a></li>
        </ul>
       <div class="cartMain_hd">
            <ul class="order_lists cartTop">
             <!--    <li class="list_chk">--> 
                    <!--所有商品全选-->
                 <!--    <input type="checkbox" id="all" class="whole_check"> 
                    <label for="all"></label>
                    全选
                </li>--> 
                <li class="list_con">商品信息</li>
                
                <li class="list_price">单价</li>
                <li class="list_amount">数量</li>
                <li class="list_sum">金额</li>
                <li class="list_op">操作</li>
            </ul>
        </div>
             <div class="cartBox">
            
            <div class="order_content">
            <%	GoodsInfo goods=null;
            int sumNum=0;
           
            double price=0;
            if(carList.size()==0){
            	out.print("<div class='shownull'>您的购物车为空，快去选购吧</div>");
            }
            int len=carList.size();	
            	for(int i=0;i<carList.size();i++){
            	goods=carList.get(i);
		//		System.out.print("zonge"+goods.getBuyNum()*goods.getGoods_price());
             %>
                      <ul class="order_lists">
       <input type="hidden" name="hidden" value="<%=goods.getGoods_id() %>" class="hidden">
         <input type="hidden" name="kucun" value="<%=goods.getGoods_num() %> " class="kucun">
           <!--          <li class="list_chk">
               
                        <input type="checkbox" id="checkbox_<%=i %>" class="son_check" value="<%=i %>" name="son_check">
                        <label for="checkbox_<%=i%>"></label>
                    </li> -->
                    <li class="list_con"><!-- <%=path %>/shopping-web/img/cartimg/6.jpg  -->
                        <div class="list_img"><a href="javascript:;"><img id="img"src="<%=path+goods.getImg_path() %>" alt=""></a></div>
                        <div class="list_text"><a href="javascript:;"><%=goods.getGoods_name() %></a></div>
                    </li>
                   
                    <li class="list_price">
                        <p class="price">￥<%=goods.getGoods_price() %></p>
                    </li>
                    <li class="list_amount">
                        <div class="amount_box">
                            <a href="javascript:;" class="reduce reSty">-</a>
                            <input type="text" value="<%=goods.getBuyNum() %>" class="sum" name="buyNum" readonly="readonly">
                            <a href="javascript:;" class="plus">+</a>
                        </div>
                    </li>
                    <li class="list_sum">
                        <p class="sum_price">￥<%=goods.getBuyNum()*goods.getGoods_price() %></p>
                    </li>
                    <li class="list_op">
                        <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                    </li>
                </ul>
                <%
               	sumNum+=goods.getBuyNum();
                price+=goods.getBuyNum()*goods.getGoods_price();
                } %>
              
            </div>
        </div>
            <!--底部-->
        <div class="bar-wrapper">
           <div class="bar-left">
           <div class="clearAll "><a href="javascript:;" class="delAll">清空</a></div>
          <div class="goBtn"> <a href="<%=path %>/servlet/WebGoodsManageAction" >继续购物</a> </div>
        
           </div>
            <div class="bar-right">
               
               <div class="piece">已选商品<strong class="piece_num"><%=sumNum %></strong>件</div>  
                <div class="totalMoney">共计: <strong class="total_text"><%=price %></strong></div>
            <div class="calBtn "><a href="javascript:;" class="btn_sty" >结算</a></div>
              
            </div>
             
        </div>
        
         
	 </div>
            <div class="model_bg"></div>
        <div class="my_model">
        <p class="title">删除宝贝<span class="closeModel">X</span></p>
        <p>您确认要删除该宝贝吗？</p>
        <div class="opBtn"><a href="javascript:;" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
             </div>
   <!-- <div class="model_bg2"></div>
        <div class="my_model2">
        <p class="title">清空购物车<span class="closeModel">X</span></p>
        <p>您确认要清空购物车吗？</p>
        <div class="opBtn"><a href="javascript:;" class="dialog2-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
    </div>
-->
        </div>

 <script src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=path %>/shopping-web/js/carts.js"></script>
    <script src="<%=path %>/shopping-web/js/checkCookie.js"></script>
  </body>
</html>
