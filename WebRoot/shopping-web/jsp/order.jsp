<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="common.*,domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<%
	String path = request.getContextPath();
%>
<link rel="shortcut icon" type="image/x-icon" href="<%=path%>/shopping-web/img/little_logo.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/orders.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/mall.css" />
<script src="<%=path%>/shopping-web/jquery/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>

<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
</head>
<script type="text/javascript">
    jQuery(function($){
        $('.all_orders ul li').click(function(){
            var index = $('.all_orders ul li').index(this);
            $(this).addClass('current').siblings('li').removeClass('current');
            $('.all_orders .wait_pay:eq('+index+')').show().siblings('.wait_pay').hide();
        })
    });
 </script>
<body class="bg">
<!-- 头 -->
 <div id="head">
  <div class="fldiv">
  <img src="<%=path%>/shopping-web/img/mdd_logo.png">
  </div>
  <%
   	UserInfo user=(UserInfo)session.getAttribute("user");
   	int id=0;
   	if(user==null)
   		user=new UserInfo();
   		else 
   		id=user.getId();
   %> 
  <div class="frdiv">
  <ul class="nav">
	<li><a href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a  class="on"  onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
 
  
 <div class="searchBox" style=" width:800px; float:right; margin-right:40px"><!-- 搜索框 -->
     <span class="orderFont1">我的全部订单</span>
<!--  <input  class="wen"  results="s" type="search"  value="搜索你的订单" /> --> 
 </div><!-- 搜索框 -->
 
 <hr/>

  <!--订单内容-->
  <div class="orderCenter">
  
   <div class="all_orders">
    <ul class="order_nav">
        <li class="current"><a href="#">未付款</a></li>
        <li><a href="#">待发货</a></li>
        <li><a href="#">已发货</a></li>
        <li><a href="#">已完成</a></li>
    </ul>
    
    <%
    String[] orderStatus={"orderList1","orderList2","orderList3","orderList4"};
    for(int y=0;y<orderStatus.length;y++){
    %>
    <!--待付款订单-->
   	 <div class="wait_pay">
   	<%
    GoodsDao goodsDao=new GoodsDao();
    ArrayList<GoodsInfo> goodslist=new ArrayList<GoodsInfo>();//商品
    OrderDao orderDao=new OrderDao();
    //订单
    ArrayList<OrderInfo> orderlist1 = (ArrayList<OrderInfo>) session.getAttribute(orderStatus[y]);//1
   
    //订单详情
  	ArrayList<OrderItemInfo> orderItemList =new ArrayList<OrderItemInfo>();
    if(orderlist1 == null|| orderlist1.size() == 0){//订单为空
    }else{
    	int userID=orderlist1.get(0).getUser_id();//用户ID
    	
      for (int k = 0; k < orderlist1.size(); k++) {//第k个订单
		int order_id=orderlist1.get(k).getOrder_id();
    	String order_no=orderlist1.get(k).getOrder_no();//订单号
    	Date created_time=orderlist1.get(k).getCreated_time();//下单时间
    	int user_id=orderlist1.get(k).getUser_id();//与 t_user 中的user_id关联
    	double payment=orderlist1.get(k).getPayment();//金额
    	int payment_type=orderlist1.get(k).getPayment_type();//支付方式 1 在线付款 0 货到付款
    	String pType="";
    	if(payment_type==1){
    		pType="在线付款";
    	}else if(payment_type==0){
    		pType="货到付款";
    	}
    	Date end_time=orderlist1.get(k).getEnd_time();//交易完成时间
    	int iscomment=orderlist1.get(k).getIscomment();//0 否 1 是	只有交易成功后才可以评价
    	int numall=0;//一个订单商品总数量
     //商品详情
      orderItemList=orderDao.orderItem(orderlist1.get(k).getOrder_id());
      goodslist=new ArrayList<GoodsInfo>();//商品
      
  	  for(int i=0;i<orderItemList.size();i++){
  		  int itemId=orderItemList.get(i).getId();//商品详情表自增ID
      	  int Goods_id=orderItemList.get(i).getGoods_id();//一种商品的编号
      	  int num=orderItemList.get(i).getNum();//商品数量
      	  numall=numall+num;
      	  double total_price=orderItemList.get(i).getTotal_price();//商品总金额
      	  //商品
      	  goodslist.add(goodsDao.getOneGoods(Goods_id));
  	  }//商品详情
    	%>
    	<div class="order_list">
            <p>订单号： <%=order_no %></p>
            <div class="goods_info">
            <div class="left_goods">
         <%
         //商品
         String imgPath="";
       //  System.out.println("\n goodslist.size(): "+goodslist.size());
         for(int q=0;q<goodslist.size();q++){
        	 int gOneid=goodslist.get(q).getGoods_id();
        	
        	 imgPath="";
        	 imgPath=goodslist.get(q).getImg_path();
        //	 System.out.println("path+imgPath: "+path+imgPath);
        	 %>
        	  <a href="<%=path %>/servlet/ReviewInsertGetAction?goodsid=<%=gOneid %>&userid=<%=userID %>" class="goods_img"><img src="<%=path+imgPath %>"></a>
        	 <%
         }//商品q
         %>
            </div>
     <%
     if(y==0){
    	 %> <div class="right_operate"> <a href="#">支付</a>
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=1">查看</a></div><%
     }else if(y==1){
    	 %> <div class="right_operate">
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=1">查看</a></div><%
     }else if(y==2){
    	 %> <div class="right_operate">
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=2">确认收货</a>
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=1">查看</a></div><%
     }else if(y==3 && iscomment==0){//是否评价0 否 	
    	 %>  <div class="right_operate">
    	
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=1">查看</a></div><%
     }else if(y==3 && iscomment==1){//是否评价 1 是	
    	 %> <div class="right_operate">
    	 <a href="<%=path %>/servlet/OrderDetialAction?orderID=<%=order_id%>&buttonNum=1">查看</a></div><%
     }
     %>    
            </div>
            <!-- 显示时间的时候：String date=MyTools.transDateToString(date); -->
            <div class="price_count"><time><%=created_time %></time> 
            <span>合计：<%=payment %></span><span>共<%=goodslist.size() %>件商品</span></div>
        </div>
    	<%
      }
     }
    %>
    </div>
    <%
    }//订单状态循环y
    %>
    </div><!-- all_orders -->
     </div>  <!--订单内容-->
  
  <!-- 尾 -->
 <div id="foot">
  <div><!-- 尾上 -->
   <div class="footldiv"><img src="<%=path %>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描关注微信买多多</div>
   </div>
   <div class="footldiv"><img src="<%=path %>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描查看更多信息</div>
   </div>
   <div class="footldiv"><img src="<%=path %>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描下载买多多APP</div>
   </div>
   <div class="footldiv"><img src="<%=path %>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描领取新人优惠</div>
   </div>
  
  <div class="footrdiv">
  <table class="ftstyle">
   <tr ><td>帮助中心</td></tr>
   <tr><td>联系与合作</td></tr>
   <tr><td>知识产权</td></tr>
   <tr><td>许可证明</td></tr>
  </table>
  </div>
  </div><!-- 尾上 -->
  
  <div class="bfoot"><!-- 尾下 -->
  <br>
  Copyright 2015-2018 pinduoduo.com 版权所有 (增值电信业务经营许可证：沪B2-20170215)
  <br>
  路公网备号 2084843827483号
  </div><!-- 尾下 -->
 </div><!-- 尾 -->
 
</body>
</html>