<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买多多-会员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/login.css">
	<link rel="shortcut icon" type="image/x-icon" href="<%=path %>/shopping-web/img/little_logo.ico"/>

  </head>
  
  <body>
    <div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
	 <%
   	UserInfo user=(UserInfo)session.getAttribute("user");
   	int id=0;
   	if(user==null)
   		user=new UserInfo();
   		else 
   		id=user.getId();
   %> 
   <ul class="nav1">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a class="on" href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
 <div class="title-wrap">
		<div class="login-title">
			——————————欢迎登录买多多购物网站——————————
		</div>
	</div>
 <div id="login">
	<div class="login-container">
		<div class="login-introduction">
			<img src="<%=path %>/shopping-web/img/login-buy.png">
		</div>
		<div class="line-wrap">
			
		</div>
		<div class="login-wrap">
			<input type="hidden" name="userid" value="">
			<div class="login-text">
				<input type="text" name="username" placeholder="用户名">
			</div>
			<div class="login-text">
				<input type="password" name="password" placeholder="密码">
			</div>
			<div class="login-text">
				<input type="text" id="code-input" name="code" class="code" placeholder="验证码">
			</div>
			<div class="codearea" id="codearea">

			</div>
			<span class="code-tip glyphicon">
				
			</span>
			<button class="btn btn-danger login-btn">登录</button>
			<div class="register-forget">
				你还不是会员，戳这里>><a class="register-now" href="<%=path %>/shopping-web/jsp/register.jsp">注册</a>
				<a class="forget-pwd" href="#">忘了密码？？？</a>
			</div>
		</div>
		
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
  <script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-3.2.0.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/gVerify.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/login.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
</html>
