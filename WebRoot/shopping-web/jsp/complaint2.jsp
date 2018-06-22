<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="common.*,dao.*,java.util.*,domain.*"%>
<%
	String path=request.getContextPath();
	List<MessageInfo> messagelist=(List<MessageInfo>)session.getAttribute("messagelist");
	int index=MyTools.strToint(request.getParameter("index"));
	MessageInfo message=messagelist.get(index);
	List<MessageReplyInfo> replylist=message.getReplylist();
	int length=replylist.size();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--a{text-decoration:none}-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="<%=path%>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<title>个人中心 - 我的留言</title>

</head>
<body>
	<div id="head">
  <div class="fldiv">
  <img src="<%=path%>/shopping-web/img/mdd_logo.png">
  </div>
  <%
  
  UserInfo user=(UserInfo)session.getAttribute("user");
	int id=0;
	if(user!=null)
		id=user.getId(); %>
  <div class="frdiv">
     <ul class="nav1">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
   </div>
	<div class="bottom" style="height:500px;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path%>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=message.getUserid() %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=message.getUserid() %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=message.getUserid() %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=message.getUserid() %>">地址管理</a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=message.getUserid() %>&pageNum=1"><span style="color:#FF9224">留言管理</span></a></dd>
	
		</dl>
		</div>
	 </aside>
	</div>  
	
		<div class="bottom_right">
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >留言详情</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	 	 <div style="margin-top: 30px;">
							<p style="margin-left: 55px; margin-top:18px ; font-size: 16px;color: #f88600; font-weight:bold;"><%=message.getTheme() %></p>
							<p style="margin-left: 55px; margin-top:18px ; font-size: 14px;color: #000000; "><%=message.getContent() %></p>
							<p style="margin-left: 55px; margin-top:18px ; font-size: 13px;color: #A0A0A0; font-weight:bold;"><%=message.getTime() %> 发布</p>
					<hr style=" border-style:solid;border-color:#EDEDED; width:998px;margin-top: 29px;"></hr>
       					<% 
       						if(message.getIsReply()==0){  //待回复
       							%>
       							<p style="margin-left: 55px; margin-top:30px ; font-size: 12px;color: #333333; ">回复：待回复</p>
       							<% 
       						}else{
       							for(int i=0;i<length;i++){
       							%>
       							<p style="margin-left: 55px; margin-top:30px ; font-size: 14px;color: #333333; ">	
       							<label style="margin-top:18px ; font-size: 13px;color: #A0A0A0; font-weight:bold;">[<%=replylist.get(i).getTime() %>] &nbsp;</label>
       							回复：<%=replylist.get(i).getContent() %></p>
       								<br>
       							<% 
       							}
       						}
       					%>					
					
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

