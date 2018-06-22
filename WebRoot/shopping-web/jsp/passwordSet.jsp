<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.*,common.*" %>
<%
	String path=request.getContextPath();
	//得到用户密码信息
	UserInfo user=(UserInfo)session.getAttribute("user");
	String question="";
	String pwd="",answer="";
	int ques;
	int id=0;
	if(user!=null){
		pwd=user.getPwd();
		answer=user.getAnswer();
		ques=user.getQuestion();
		id=user.getId();
		session.setAttribute("id",id);
		if(ques==1){
			question="您母亲的姓名是：";
		}else if(ques==2){
			question="您父亲的姓名是：";
		}else if(ques==3){
			question="您配偶的名字是：";
		}else if(ques==4){
			question="您的出身地是：";
		}else{
			question="您最爱的人是：";
		}
	}
	String isSuccess=(String)session.getAttribute("isSuccess");
	session.removeAttribute("isSuccess");
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
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="<%=path %>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/pwdCheck.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>个人中心 - 密码管理</title>
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
    <li><a class="on" onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
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
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=user.getId() %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=user.getId() %>"><span style="color:#FF9224">密码管理</span></a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=user.getId() %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=user.getId() %>">地址管理</a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=user.getId() %>&pageNum=1">留言管理</a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
	
		<div class="bottom_right">
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >密码管理</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
		<div style="width: 100%;    display: inline-block; margin-top: 20px;">
									<h3 style="font-size: 14px;color: #333333;width: 1000px;"><span style="float: left;background-color: #F2873B;height: 15px;width: 4px;margin-top: 10px;margin-right: 10px;"></span>身份验证</h3>
		</div>	
		<form name="form" method="post" action="<%=path %>/servlet/PasswordUpdateAction">		
			<table style="margin-left:20px;margin-top:20px;margin-bottom:25px;" align="left" width="90%">
			<tr height="75px">
			<td style="width:170px">
			<span >请输入当前登录密码：</span>
			</td>
			<td align="left">
			<input type="password" style="width: 285px;height: 40px;margin-left: 22px;" name="nowpwd" id="nowpwd" onkeyup="checkPWD('<%=pwd %>')"/>
			<input type="hidden" value="">
			<label style="color:#FF9224;margin-left:10pt" id="tips1"></label>
			</td>
			</tr>
			<tr>
			<td>
			<span ><%=question %></span>
			</td>
			<td align="left">
			<!-- 调用js方法，带参数必须加引号，否则调用方法不成功 -->
			<input type="text" style="width: 285px;height: 40px;margin-left: 22px;" name="checkanswer" id="checkanswer" onkeyup="checkAnswer('<%=answer %>')"/>
			<label style="color:#FF9224;margin-left:10pt" id="tips2"></label>
			</td>
			</tr>
			</table>
		<div style="width: 100%;    display: inline-block; margin-top: 20px;">
									<h3 style="font-size: 14px;color: #333333;width: 1000px;"><span style="float: left;background-color: #F2873B;height: 15px;width: 4px;margin-top: 10px;margin-right: 10px;"></span>密码修改</h3>
		</div>	
		<table style="margin-bottom:25px;width:100%;">
			<tr height="75px">
			<td style="width:170px">
			<span >新的登录密码：</span>
			</td>
			<td align="left" colspan="7">
			<input type="password" style="width: 285px;height: 40px;margin-left: 22px;" name="newpwd"  id="newpwd"  onKeyUp=pwStrength(this.value),checkPWDlength() onBlur=pwStrength(this.value)/>
			<label style="color:#FF9224;margin-left:10pt" id="tips3"></label>
			</td>
			</tr>
			<tr height="25px">  
            <td style="width:170px">
			<span >密码强度：</span>
			</td> 
			<td width="20px">
			</td>
            <td width="60px" id="strength_L" bgcolor="#eeeeee">弱</td>  
            <td width="3px">
			</td>
            <td width="60px" id="strength_M" bgcolor="#eeeeee">中</td>    
            <td width="3px">
			</td>
            <td width="60px" id="strength_H" bgcolor="#eeeeee ">强</td> 
            <td width="auto">
			</td>   
       		 </tr>  
			<tr height="75px" >
			<td>
			<span >确认新的登录密码:</span>
			</td>
			<td align="left" colspan="7">
			<input type="password" style="width: 285px;height: 40px;margin-left: 22px;" name="checknewpwd" id="checknewpwd" onkeyup="checkNewPwd()"/>
			<label style="color:#FF9224;margin-left:10pt" id="tips4"></label>
			</td>
			</tr>
			
			<tr height=100px>
			<td>
			</td>
			<td align="left" colspan="7">
			<input type="submit" style="margin-left:50px;width: 200px;height: 50px;font-size: 16px;border: 1px rgba(187, 187, 187, 0.5)B solid;border-radius: 5px;background:#F37B1D;color:#FFFFFE;" value="确定" onclick="return check('<%=pwd %>','<%=answer %>')" > 
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
<script language="javascript">
function load(){ 
	var isSuccess="<%=isSuccess %>";
	if(isSuccess=="1"){
		swal({
		    title:" 密码修改成功",    
		    text: "",
		    type: "success",    
		    confirmButtonText: "确定" 
		});		
	} 
}
/*function tans(){
 var oldpass=document.getElementById("nowpwd").value;
 var s=;
 var type="MD5";*/
 <%
 	
 	//	MyTools.EncryptionStr(s,"MD5");
 %>
//}
</script>
