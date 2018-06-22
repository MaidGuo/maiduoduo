<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*, dao.*, domain.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	ArrayList<NoticeInfo> noticelist = new ArrayList<NoticeInfo>();
	noticelist = (ArrayList<NoticeInfo>)session.getAttribute("noticelist");
	session.removeAttribute("noticelist");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>小黑板</title>
<link href="<%=path %>/shopping-web/css/notice.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css"> 

<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
</head>

<body>
<!-- 头 -->
 <div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
  <%
  	UserInfo user=(UserInfo)session.getAttribute("user");
	int id=0;
	if(user==null)
		id=0;
	else id=user.getId();
%>
    <ul class="nav">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
 

<br><br><br>
<div>
	<div style=" margin-top: 80px">
		<table style="width:300px; border:0; margin-left: 200px; ">
			<tr>
				<td><div style="background-color:#f23d4e; width:20px; height: 40px;"></div></td>
				<td><p style="font-size: 36px; font-family:Microsoft Yahei; color: #666666; width:150px">小黑板</p></td>
			</tr>
	  </table>	
		<hr  style="height:20px;border:none;border-top:5px groove #f23d4e;" />
  </div>
  <div style="height: 300px">
  	<ul style=" font-family:Microsoft Yahei; font-size:24px; margin-top: 150px; margin-left: 500px">
  	<%
		for (int i=0; i<noticelist.size(); i++) {
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo = noticelist.get(i);											
	%>
		<li><a href="<%=path%>/servlet/BeforeContentListAction?id=<%=noticeInfo.getNotice_id()%>"><%=noticeInfo.getNotice_theme()%></a></li>
	<%
		}
	%>
	</ul>
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