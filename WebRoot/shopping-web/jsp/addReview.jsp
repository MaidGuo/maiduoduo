<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="common.*,domain.*" %>
<% 
	String path=request.getContextPath();
	UserInfo user=(UserInfo)session.getAttribute("user");
	int id=0;
	if(user!=null)
		id=user.getId();
	GoodsInfo goods=(GoodsInfo)session.getAttribute("goods");
	String strcount=request.getParameter("count");
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--a{text-decoration:none}-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path%>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="<%=path%>/shopping-web/js/showText.js"></script>
<script src="<%=path%>/shopping-web/sweetalert/sweetalert.min.js"></script>
<script src="<%=path%>/shopping-web/js/login.js"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/reviewCheck.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/toReview.js" charset="utf-8"></script>

<title>个人中心 - 我的评价</title>
</head>
<body onload="load('<%=strcount %>','<%=user.getId() %>')">
	<div id="head">
  <div class="fldiv">
  <img src="<%=path%>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
   <ul class="nav">
 	<li><a href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a    onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
   </div>
	<div class="bottom" style="height:600px;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path%>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=user.getId() %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=user.getId() %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=user.getId() %>&pageNum=1"><span style="color:#FF9224">我的评价</span></a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=user.getId() %>">地址管理</a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=user.getId() %>&pageNum=1">留言管理</a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
	
		<div class="bottom_right">
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
					<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >我的评价</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	 	<form name="form" method="post" action="<%=path %>/servlet/ReviewInsertAction">
		<div style="margin-left: 0px;margin-top:20px;margin-bottom:0px;width: 100%;border: 0px;">
							<div style="width: 100%;height: 500px;">
								<p style="margin-left: 70px;font-size: 14px;"><span style="color:#F88600;font-size: 14px;">新增评价</span> </p>
								
							<div style="margin-left: 70px;margin-top: 50px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">商品信息:</span>
							</div>
							<div style="margin-left: 150px;margin-top:-25px;">
										                      
						<img style="margin-left: 55px; color:#AAAAAA" src="<%=path+goods.getImg_path() %>" height="55" width="55">
						<br>
							<a href=""><font style="margin-left: 55px; margin-top:18px ; font-size: 15px;color: #A0A0A0; "><%=goods.getGoods_name() %></font></a>
							</div>
							<div style="margin-left: 70px;margin-top: 50px;">
								<span style="color: #F2873B;">&nbsp;&nbsp;&nbsp;*</span><span class="titles">主题:</span>
							</div>
							<div style="margin-left: 150px;margin-top:-25px;">
								<input type="text" name="theme" id="theme" onkeyup="checkTheme()" onfocus="checkTheme()" placeholder="请填写你要评价的主题" style="padding: 5px;width: 300px;margin-left: 14px;" />
								<label style="color:#FF9224;margin-left:10pt" id="tips2">*15个字符以内</label>
							</div>
							<div style="margin-left: 70px;margin-top:50px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">评价内容：</span>
							</div>
							<div style="margin-left: 165px;margin-top:-25px;">
								<textarea name="content" id="content" onkeyup="checkContent()" onfocus="checkContent()" style="width:500px;height: 90px;padding: 5px;" placeholder="请填写你对该商品的评价"></textarea>
								<label style="color:#FF9224;margin-left:10pt" id="tips3">*250个字符以内</label>
							</div>					
							<input type="submit" style="margin-left:170px;margin-top:20px;background-color:#F37B1D ;color: #fff;width: 100px;height: 30px;border: 0px;border-radius: 5px;" value="确定" onclick="return check();">
							</div>
							
						</div>	
		</form>					
		</div>
	</div>
				
		</div>
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
  
 </div>

</body>
</html>
