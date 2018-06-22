<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="common.*" %>
<%
	String path=request.getContextPath();
	int userid=MyTools.strToint(request.getParameter("userid"));
	String count=request.getParameter("count");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--a{text-decoration:none}-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<script type="text/javascript" src="jquery-1.8.3.min.js" ></script>
<script src="<%=path %>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/messageCheck.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>我的留言</title>
</head>
<body onload="load()">
	<div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
     <ul class="nav1">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=userid%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,<%=userid%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=userid%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
   </div>
	<div class="bottom" style="height:400px;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path %>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=userid %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=userid %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=userid %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=userid %>">地址管理</a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=userid %>&pageNum=1"><span style="color:#FF9224">留言管理</span></a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
	<div class="bottom_right">
			<h3 style="margin-top: 24px;margin-left:20px;background-color: #fff;">
	                     <font style="font-size: 16px;color: #333333; font-weight:bold;  " >我要留言</font>
	                    </h3>
	                    <hr style=" border-style:solid;border-color:#EDEDED; width:100%;margin-top: 14px;"></hr>
                        <form name="form" method="post" action="<%=path %>/servlet/MessageInsertAction?userid=<%=userid %>">
                        <div style="margin-top: 30px;margin-bottom:30px;">
                   
                     <font style="font-size: 12px;color: #333333; margin-left:105px;  ">标题:</font> 
                    <input type="text" style="color: #000000; height:35px; width:286px;margin-left:14px;margin-top: 20px;" name="title" id="title" onkeyup="checkTitle()" onfocus="checkTitle()"/>
                   	<label style="color:#FF9224;margin-left:10pt" id="tips1">*15个字符以内</label>
                    <br/>
                   
                     <font style="font-size: 12px;color: #333333; margin-left:80px;">问题描述:</font> 
                    <textarea style="padding: 5px;color: #000000; height:85px; width:459px;margin-left:14px;margin-top: 20px;" name="descript" id="descript" onkeyup="checkDescription()" onfocus="checkDescription()"></textarea> 
             		<label style="color:#FF9224;margin-left:10pt" id="tips2">*250个字符以内</label>
             		<br/>                           
                    <input type="submit" value="提交" onclick="return check();"style=" color:#FFFFFE;height:34px;width:110px;background:#F37B1D;margin-left:140px;margin-top:20px;border: 0px;border-radius: 6px;"></input>

                        </div>
	</form>
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
  
 </div>

</body>
</html>
<script language="JavaScript">
function load(){ 
	var count="<%=count %>";
	if(count=="1"){
		swal({
		    title:" 留言新增成功",    
		    text: "",
		    type: "success",    
		    confirmButtonText: "确定" 
		},function(){
		
			window.location.href = "<%=path%>/servlet/MessageGetAction?userid=<%=userid %>&pageNum=1";
		});		
	} 
}
</script>


