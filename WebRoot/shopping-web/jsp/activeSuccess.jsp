<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买多多-会员激活</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
  
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
	<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
	<style type="text/css">
	.center{
	padding:40px 30px 0;width:465px;height:200px;
	margin-left:130px;
	}
	
	.m_img{
	float:left;
	margin-right:10px;
	}
	.center .m_text{font-size:22px;padding-left:50px;margin-top:40px;margin-bottom:30px;}
	</style>

  </head>
  
  <body>
  <%
  	String flag=(String)request.getAttribute("flag");
  	String msg=null;
  
  	if(flag!=null){
 
  	if(flag.equals("0")){//失败
   msg="对不起，您的激活码不正确";
  }	else if(flag.equals("1")){
  msg="您的邮箱已成功激活！";
  	}else if(flag.equals("2")){
  	msg="错误！激活码已过期！";
  	}else if(flag.equals("3")){
  	msg="邮箱已激活，请登录！";
  	}else if(flag.equals("4")){
  	msg="该邮箱未注册";
  }
	
  }
  UserInfo user=(UserInfo)session.getAttribute("user");
  if(user==null)
  user =new UserInfo();
  int userid=user.getId();
  %>
    <div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
   <ul class="nav1">
    <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=userid%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=userid%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=userid%>)">购物车</a></li>
    <li><a class="on" href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul> 
  </div>
</div>
<div>
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
    <div class="center">
  <div class="m_img"> <img src="<%=path %>/shopping-web/img/emailpic.png" ></div>
        <div class="m_text"><p><%=msg %></p>
        	<p>请点击<%
        	if(flag.equals("1")||flag.equals("3")){
				%>
        	 <a href="<%=path %>/shopping-web/jsp/login.jsp">立即登录</a>
        	 <%}else{      	 
        	  System.out.println("!");
        	  %>
        	   <a href="<%=path %>/shopping-web/jsp/homePage.jsp">返回首页</a>
        	   <%}%>
        	   </p>
        </div>
  </div>

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
