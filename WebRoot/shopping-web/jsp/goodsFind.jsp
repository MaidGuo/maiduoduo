<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="domain.*,servlet.*,java.util.*,dao.*" %>
<!-- 美妆/个人护理等点击更多显示的大类别 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>买多多商品</title>
<% String path =request.getContextPath();//路径 %>
<link rel="shortcut icon" type="image/x-icon" href="<%=path%>/shopping-web/img/little_logo.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/goods2.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/goods.css">
<script type="text/javascript" src="<%=path%>/shopping-web/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/search.css">
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
  
</head>

<body>
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
  <li><a  class="on" href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
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
 
  <div id="searchBox"> <!-- 搜索框 -->
	<div class="search bar6"> 
	<form name="findfom" class="formf" method="post" action="<%=path %>/servlet/FindGoodsAction"> 
	<input name="findGoodsBox" type="text" placeholder="请输入您要搜索的内容..."> 
	<button type="submit"></button> </form>
    </div><!-- 搜索框 -->

    <hr>
    <% 
    String str=(String)session.getAttribute("FindGoodsStr");//查询字段
    ArrayList<GoodsInfo> goods=(ArrayList<GoodsInfo>)session.getAttribute("FindGoods");//3类别名
   if(goods==null||goods.size()==0){ //必须判断，可能取空，报错
	   %>
	   <div align="center" style="height:300px">
	   <h2><%=str %>还未上新，敬请期待......</h2></div>
	   <%
	}else{%>
	<div><!-- 美妆/个人护理等点击更多显示的大类别 -->
    <div class="goods1"></div>
    <ul class="goodsPic">
	
	<%
        for(int i=0; i<goods.size() ;i++){
        	int gId=goods.get(i).getGoods_id();
            String gName=goods.get(i).getGoods_name();
        	double  gPrice=goods.get(i).getGoods_price();
        	String gImgPath=goods.get(i).getImg_path();//商品图片
        	%>
   <li><input type="hidden" name="goodsId" value="<%=gId%>">
	<a href="<%=path%>/servlet/GoodsAppearAction?goodsId=<%=gId%>">
	<img src="<%=path+gImgPath%>"><br><%=gName%>
	<br>￥<%=gPrice%></a></li>
        	<%
   	 }
    }
   %>
   </ul>
  </div><!-- 内容 -->
  <hr>
    
    
  <!-- 尾 -->
 <div id="foot">
  <div><!-- 尾上 -->
   <div class="footldiv"><img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描关注微信买多多</div>
   </div>
   <div class="footldiv"><img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描查看更多信息</div>
   </div>
   <div class="footldiv"><img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
          <div>扫描下载买多多APP</div>
   </div>
   <div class="footldiv"><img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
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