<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/login.css">	
<script type="text/javascript">
function formValidator()
	{

	if(frm.adminName.value==""){
		alert("请输入用户名！");
		frm.adminName.focus();
		return false;
	}
	if(frm.adminPassword.value==""){
		alert("请输入密码！");
		frm.adminPassword.focus();
		return false;
	}
	
		return true;
		
}
</script>
  </head>

 <body bgcolor="#CCE5FD">

<div id="content" align="center" >
  <div id="userlogin">
  	<form name="frm" action="AdminLoginAction?method=login" method="post">
  	<dl>
	<dd id="user_top"><ul>
		<li class="user_top_z"><img  class="user_top_img" src="<%=path %>/shopping-back/img/userlogin.png" width="536" height="128" alt=""/></li></ul></dd>

	<dd id="user_main">
	<ul>
		<li class="user_main_l"></li>
		<li class="user_main_c">
			<div class="user_main_box">
				<ul>
					<li class="user_main_text"><img class="user_mian_img" src="<%=path %>/shopping-back/img/user.png" width="18" height="20" alt=""/>用户名：</li>
					<li class="user_main_input"><input type="text" name="adminName" id="adminName" Value="" >
				    </li></ul>
				
				<br><br>
				<ul>	
				<li class="user_main_text"><img class="user_mian_img" src="<%=path %>/shopping-back/img/mima.png" width="18" height="18" alt=""/>密　码：</li>
					<li class="user_main_input"><input  id="adminPassword" type="password" name="adminPassword" >
				  </li>
				</ul>
				<ul>	<br>
	
					<li class="uer_main_button_li">
						<input class="user_main_button"  type="submit" value="登录" onclick="return formValidator();">
						
					</li>
				
				</ul>
				
					</div>
		</li>
	</ul>
		</dd>
	  </dl>
    </form>
    <div class="err" id="err" style="color:#FF0000;font-weight:bold;font-size:11pt;display:none;">※ 用户名和密码均区分大小写，其中至少一项输入有误，请重新输入！
</div>
</div>

<%
String msg=(String)request.getAttribute("msg");
 	if(msg!=null){
		if(msg.equals("err")){%>
		<script type="text/javascript">
 document.getElementById("err").style.display="block";                  //显示
        
		</script>
	<% }
 }%> 
</div>
 <div class="foot">
	 	copyright@2018
	 </div> 
	</body>
	

	
 </html>
