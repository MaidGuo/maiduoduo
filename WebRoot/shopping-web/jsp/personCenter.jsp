<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.UserInfo,common.*" %>
<%
	String path=request.getContextPath();
//	String count=request.getParameter("count");
	UserInfo user=(UserInfo)session.getAttribute("user");
	int id=0;
	if(user==null)
		id=0;
	else id=user.getId();
	session.setAttribute("id", id);
	String count=(String)session.getAttribute("count");
	session.removeAttribute("count");
%>
<html>
<head>
<style>
<!--a{text-decoration:none}-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="/maiduoduo/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<script type="text/javascript" src="<%=path %>/shopping-web/js/personCheck.js" charset="utf-8"></script> 
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>个人中心 - 个人资料</title>
</head>
<body onload="load()">
	<div id="head">
  <div class="fldiv">
  <img src="<%=path %>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
     <ul class="nav1">
   <li><a  href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a class="on" onclick="checkCookie(this,,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
   </div>
	<div class="bottom" style="height:700px;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path %>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=id %>"><span style="color:#FF9224">个人资料</span></a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=id %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=id %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=id %>">地址管理</a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=id %>&pageNum=1">留言管理</a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
		<div class="bottom_right">
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >个人资料</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	 	<form name="form" method="post" action="<%=path %>/servlet/UserInfoUpdate">
		<table style="margin-left:20px;margin-top:20px;margin-bottom:25px;" align="left" width="90%">
			<tr>
			<td style="margin-left:10px;margin-top:15px;"  colspan="2" align="left">
			亲爱的<b><%=user.getName() %></b>,填写真实的资料，有助于好友找到你哦。
			</td>
			</tr>
			
			<tr height="50">
			<td width="90">
				当前头像：
			</td>
			<td>
			
			<div class="content">
				<div class="img_container">
					<img src="<%=path %>/shopping-web/img/image2.PNG">
					<div class="mask">编辑头像 </div>
					<script type="text/javascript">
					$(document).ready(function(){
						$(".img_container").mouseover(function(){
							$(".img_container .mask").show();
						})
						$(".img_container").mouseout(function(){
							$(".img_container .mask").hide();
						})
					})	
					</script>
				</div>
			</div>
			
			</td>
			</tr>
			<tr height="50">
				<td>昵称：</td>
				<td align="left">
				
				<input id="nickname" name="nickname" type="text" class="inputstyle" style="width:300px" value="<%=user.getName() %>"
				 onblur="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" 
				onkeyup="checkNickname()" onfocus="checkNickname()"><label style="color:#FF9224;margin-left:10pt" id="tips1">*只能输入中文</label></td>
			</tr>
			<tr height="50">
				<td>真实姓名：</td>
				<td align="left"><input name="realname" id="realname" type="text" onkeyup="checkRealname()" onfocus="checkRealname()" class="inputstyle" value="<%=user.getTrueName() %>">
				<label style="color:#FF9224;margin-left:10pt" id="tips2">*只能输入中文</label>
				</td>
			</tr>
			<tr height="50">
				<td>性别：</td>
				<td align="left">
				<input name="sex" type="radio" value="0" <%=user.getGender()==0?"checked":"" %>>男
				<input name="sex" type="radio" value="1" <%=user.getGender()==1?"checked":"" %>>女
				</td>
			</tr>	
			<tr height="50">
                  <td>居住地：</td>
                  <td align="left">
      	           <div class=""><select  name="province" id="s_province" onclick="checkaddr()">
					    	<option value="1"></option>
					    	
					    </select>
					    <select  name="city" id="s_city" onclick="checkaddr()">
					    	<option value="2" ></option>
					    	
					    </select>
					    <select  name="county" id="s_county" onclick="checkaddr()">
					    	<option value="3" ></option>
					    	
					    </select>
					   
    <script class="resources library" src="<%=path %>/shopping-web/js/area.js" type="text/javascript"></script>
<!--  	 <script type="text/javascript">
	 
	 var province="<%=user.getProvince() %>";
	 var city="<%=user.getCity() %>";
	 var county="<%=user.getCountry() %>";
	 _init_opt0(province,city,county)
	</script> -->
	<script type="text/javascript">_init_area()</script>
				    <input type="text" name="address" value="<%=user.getAddress() %>"/>
					   <label style="color:#FF9224;margin-left:10pt" id="tips5"></label>
					    </div>

                  </td>
              </tr>
              <tr height="50">
				<td>邮箱：</td>
				<td align="left"><input name="mail" id="mail" type="text" onkeyup="checkMail()" onfocus="checkMail()" value="<%=user.getEmail() %>" class="inputstyle" required>
				<label style="color:#FF9224;margin-left:10pt" id="tips3"></label>
				</td>
			</tr>
			<tr height="50">
				<td>电话：</td>
				<td align="left"><input name="phone" id="phone" type="text" onkeyup="checkTel()" onfocus="checkTel()" value="<%=user.getTel() %>" class="inputstyle" required>
				<label style="color:#FF9224;margin-left:10pt" id="tips4"></label></td>
				
			</tr>
      			<tr height="50">
      			<td colspan="2">
             	<hr style="height:1px;border:none;border-top:1px solid #D0D0D0;line-height:70">
             	</td>
             	</tr>
             	
             	<tr height="30">
             	<td></td>
             	<td align="left">
                  <span style="margin-top:10px;">
             	     <input type="submit" style="margin-top:10px;background-color:#F37B1D ;color: #fff;width: 100px;height: 30px;border: 0px;border-radius: 5px;" value="保存" onclick="return check();" required>
                  </span>
                  </td>
             </tr>
				
		
		 </table>  
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
   <div class="footldiv"><img src="/maiduoduo/shopping-web/img/mdd_foot_eechat1.jpg"><!-- 二维码 -->
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
		    title:" 修改成功",    
		    text: "",
		    type: "success",    
		    confirmButtonText: "确定" 
		})		
		} 
	}
	
</script>
