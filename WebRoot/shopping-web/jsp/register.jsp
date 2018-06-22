<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买多多-会员注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/register.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
	<link rel="shortcut icon" type="image/x-icon" href="<%=path %>/shopping-web/img/little_logo.ico"/>

<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery.js"></script>	
<script type="text/javascript" src="<%=path %>/shopping-web/js/Popt.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/city.json.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/citySet.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/register.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/validate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/validate/additional-methods.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/validate/localization/messages_zh.js"></script>
<style type="text/css">
	
* { -ms-word-wrap: break-word; word-wrap: break-word; }
html { -webkit-text-size-adjust: none; text-size-adjust: none; }


strike{background:url("<%=path%>/shopping-web/img/coin20.png>") no-repeat;}
._citys { width: 400px; display: inline-block; border: 2px solid #CCE5FD; padding: 5px; position: relative; }
._citys span { color:#FF0004; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 10px; top: 10px; border: 1px solid #FF0004; cursor: pointer; }
._citys0 { width: 95%; height: 34px; line-height: 34px; display: inline-block; border-bottom: 2px solid #FF0004; padding: 0px 5px; font-size:14px; font-weight:bold; margin-left:6px; }
._citys0 li { display: inline-block; line-height: 34px; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
._citys1 { width: 100%; display: inline-block; padding: 10px 0; }
._citys1 a { width: 83px; height: 35px; display: inline-block; background-color: #f5f5f5; color: #666; margin-left: 6px; margin-top: 3px; line-height: 35px; text-align: center; cursor: pointer; font-size: 12px; border-radius: 5px; overflow: hidden; }
._citys1 a:hover { color: #fff; background-color: #FF0004; }
.AreaS { background-color:#FF0004; !important; color: #CCE5FD !important; }
</style>
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
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a class="on" href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  
  <div class="himg">
   <img src="<%=path %>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
	
	<div class="title-wrap">
	
		<div class="register-title">
			——————————欢迎注册买多多购物网站——————————
		</div>
	</div>

  <div id="register">
  	<div class="register-container">
   <form id="register-form"  >
		<div class="input-wrap">
			<input type="text" id="name" name="name" placeholder="用户名" required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<input type="password" id="pwd" name="pwd" placeholder="密码" required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
				<div class="input-wrap">
			<input type="text" id="email" name="email" placeholder="邮箱" required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap"><span class="genderzi">性别：</span>
			<input type="radio" id="gender" name="gender" value="0"  checked>男
			<input type="radio" id="gender" name="gender"  value="1" >女
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<select id="mibao" name="mibao" required   >
 			<option value="" selected style="color: #BBB5C0">--密保问题--</option>
  			<option value="1">您母亲的姓名是</option>
  			<option value="2">您父亲的姓名是</option>
  			<option value="3">您配偶的姓名是</option>
  			<option value="4">您的出生地是</option>
  			<option value="5">您最爱的人是</option>
			</select>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<input type="text" id="mbanswer" name="mbanswer" placeholder="密保答案" required>
			<span class="glyphicon glyphicon-asterisk"></span>
		  <div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<input type="text" id="youbian" name="youbian" placeholder="邮政编码" maxlength="6" required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<input type="text" id="tel" name="tel" placeholder="联系电话" maxlength="11"  required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div>
		<div class="input-wrap">
			<input type="text" id="truename" name="truename" placeholder="真实姓名" required>
			<span class="glyphicon glyphicon-asterisk"></span>
			<div class="error-tip"></div>
		</div> 
		 <div class="input-wrap">
		
         <input class=" uneditable-input" name="city" id="city" type="text" placeholder="请选择" readonly="readonly" autocomplete="off" required><strike></strike>
          <span class="glyphicon glyphicon-asterisk"></span>
        <div class="error-tip"></div> 
   </div>
      <script type="text/javascript">
$("#city").click(function (e) {
	SelCity(this,e);
});
$("#cityselecticon").click(function (e) {
	SelCity(document.getElementById("city"),e);
});
		
	
</script>

		
	<div class="input-wrap">
 <input id="town" name="town" type="text" value="" placeholder="详细地址" required/>
 
 <span class="glyphicon glyphicon-asterisk"></span><div class="error-tip"></div></div>	

			
		<div class="btns">
			<input type="submit" class="btn btn-primary btn-submit" value="提交"   required>
			<input type="reset" class="btn btn-primary" value="重写">
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
  
 </div><!-- 尾 -->
</body>

</html>
