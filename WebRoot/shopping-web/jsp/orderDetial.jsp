<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="common.*,domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/orders.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/mall.css">
</head>

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
		id=0;
	else id=user.getId();
   %>
  <div class="frdiv">
     <ul class="nav">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
 </div><!-- 头 -->
 
 <div class="orderCenter" style="margin-top:50px; border:thin solid;">
   <div class="all_orders">
   <div class="order_list">
   <div class="wait_pay">
   <%
    GoodsDao goodsDao=new GoodsDao();
    ArrayList<GoodsInfo> goodslist=new ArrayList<GoodsInfo>();//商品
    OrderDao orderDao=new OrderDao();
    //订单
    OrderInfo order= (OrderInfo)session.getAttribute("OrderDetials");
    //订单详情
  	ArrayList<OrderItemInfo> orderItemList =new ArrayList<OrderItemInfo>();
    if(order == null){//订单为空
    }else{
		int order_id=order.getOrder_id();
    	String order_no=order.getOrder_no();//订单号
    	Date created_time=order.getCreated_time();//下单时间
    	int user_id=order.getUser_id();//与 t_user 中的user_id关联
    	double payment=order.getPayment();//金额
    	int payment_type=order.getPayment_type();//支付方式 1 在线付款 0 货到付款
    	String pType="";
    	if(payment_type==1){
    		pType="在线付款";
    	}else if(payment_type==0){
    		pType="货到付款";
    	}
    	Date end_time=order.getEnd_time();//交易完成时间
    	int iscomment=order.getIscomment();//0 否 1 是	只有交易成功后才可以评价
    	ArrayList<Integer> numall=new ArrayList<Integer>();//一个商品总数量
     //商品详情
      orderItemList=orderDao.orderItem(order.getOrder_id());
  	  for(int i=0;i<orderItemList.size();i++){
  		  int itemId=orderItemList.get(i).getId();//商品详情表自增ID
      	  int Goods_id=orderItemList.get(i).getGoods_id();//一种商品的编号
      	  int num=orderItemList.get(i).getNum();//商品数量
      	numall.add(num);
      	  double total_price=orderItemList.get(i).getTotal_price();//商品总金额
      	  //商品
      	  goodslist.add(goodsDao.getOneGoods(Goods_id));
  	  }//商品详情
      %>
      <p>订单号： <%=order_no %></p>
      <div class="goods_info">
       
       <%
         //商品
         String imgPath="";
         for(int q=0;q<goodslist.size();q++){
        	 imgPath=goodslist.get(q).getImg_path();
        	 int nu=numall.get(q);//商品数量
        	 String gname=goodslist.get(q).getGoods_name();
        	 %>
       <div class="left_goods">
        <a href="goods.html" class="goods_img"><img src="<%=path+imgPath%>"></a>
       <div class="orderDetail" >商品名：<%=gname %><br>商品数：<%=nu %></div>  
       </div>
       <%
         }//商品q
         %>
        </div>
        <div class="price_count"><time><%=created_time %></time> 
        <span>合计：<%=payment %></span>
        <span>共<%=goodslist.size() %>件商品</span></div>
   <%
    }//else
    %>
  </div>
  
  </div>
  </div>
  </div>
 
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