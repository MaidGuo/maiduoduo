<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,domain.*,common.*" %>
<%
	String path=request.getContextPath();
	List<ReceiveAddress> addresslist=(List<ReceiveAddress>)session.getAttribute("addresslist");
	String strindex=request.getParameter("index");
	int index=MyTools.strToint(strindex);
	ReceiveAddress address=addresslist.get(index);
	System.out.println("modifyAddress:输出详细地址:"+address.getAddress());
	int addressid=address.getAddressId();
	String count=request.getParameter("count");
	if(count!=null){
		if(!count.equals("1")){
			out.println("<script>alert('修改地址失败！');</script>");
		}
	}
	UserInfo user=(UserInfo)session.getAttribute("user");
	int id=0;
	if(user==null)
		id=0;
	else id=user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--a{text-decoration:none}-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>

<script src="<%=path %>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<script type="text/javascript" src="<%=path %>/shopping-web/js/addressCheck.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>个人中心 - 地址管理</title>
</head>


<body>
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
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=address.getUserId() %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=address.getUserId() %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=address.getUserId() %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=address.getUserId() %>"><span style="color:#FF9224">地址管理</span></a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=address.getUserId() %>&pageNum=1">留言管理</a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
	
		<div class="bottom_right">
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >收货地址</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	 	<form name="form" method="post" action="<%=path%>/servlet/AddressUpdateAction?addid=<%=address.getAddressId() %>&userid=<%=address.getUserId() %>&index=<%=index %>">
						<div style="margin-left: 0px;margin-top:20px;width: 100%;border: 0px;">
							<div style="width: 100%;height: 500px;">
								<p style="margin-left: 70px;font-size: 14px;"><span style="color:#F88600;font-size: 14px;">修改收货地址</span> </p>
							<div style="margin-left: 70px;margin-top: 30px;height: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">所在地区:</span>
								<select style="padding:5px;margin-left: 14px;"> 
									<option selected="">中国大陆</option>
								</select>
								   <select  name="s_province" id="s_province" value="<%=address.getProvince() %>" onclick="checkaddr()">
					    	<option value="1">请选择省</option>
					    	
					    </select>
					    <select  name="s_city" id="s_city" value="<%=address.getCity() %>" onclick="checkaddr()">
					    	<option value="2">请选择市</option>
					    	
					    </select>
					    <select  name="s_county" id="s_county" value="<%=address.getCounty() %>" onclick="checkaddr()">
					    	<option value="3">请选择区</option>
					    	
					    </select>
					   
    <script class="resources library" src="<%=path %>/shopping-web/js/area.js" type="text/javascript"></script>
    <script type="text/javascript">_init_area();</script>
					    <input id="address" name="address" type="hidden" value=""/>
					 
					    	<label style="color:#FF9224;margin-left:10pt" id="tips5"></label>
								

							</div>
							<div style="margin-left: 70px;margin-top: 50px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">详细地址:</span>
							</div>
							<div style="margin-left: 150px;margin-top:-40px;">
								<textarea name="detaddress" id="detaddress" style="width:500px;height: 90px;padding: 5px;" placeholder="建议您如实填写详细收货地址，例如街道 名称，门牌号码，楼层和房间号等信息" onkeyup="checkAddress()" onfocus="checkAddress()"><%=address.getAddress() %></textarea>
								<label style="color:#FF9224;margin-left:10pt" id="tips1"></label>
							</div>
							
							<div style="margin-left: 70px;margin-top: 20px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">邮政编号:</span>
								<input type="text" name="postcode" id="postcode" placeholder="如您不清楚地区邮递号，请填写000000" style="padding: 5px;width: 300px;margin-left: 14px;" value="<%=address.getPostcode() %>" onkeyup="checkPostcode()" onfocus="checkPostcode()"/>
								<label style="color:#FF9224;margin-left:10pt" id="tips2">*邮政编码由6位数字组成</label>
							</div>
							
							<div style="margin-left: 60px;margin-top: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span>
								<span class="titles">收货人姓名:</span>
								<input type="text" name="customer" id="customer" style="padding: 5px;width: 300px;margin-left: 14px;" value="<%=address.getName() %>" onkeyup="checkCustomer()" onfocus="checkCustomer()"/>
								<label style="color:#FF9224;margin-left:10pt" id="tips3"></label>
							</div>
							<div style="margin-left: 70px;margin-top: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">手机号码:</span>
								<input type="text" name="phone" id="phone" placeholder="" style="padding: 5px;width: 300px;margin-left: 14px;" value="<%=address.getTel() %>" onkeyup="checkPhone()"  onfocus="checkPhone()" />
								<label style="color:#FF9224;margin-left:10pt" id="tips4"></label>
							</div>
							<div style="margin-left: 150px;margin-top: 10px;">
								
								<div class="" style="padding-right: 0rem;"> 
									<label class="">
										<input type="checkbox"  name="defaultuadd" id="defaultuadd" value=1 <%=address.getIsdefault()==1?"checked":"" %> >
										<span style="font-size: 12px;color: #878787;">
											<font style="font-size: 12px;color: #333333;">设为默认地址</font>
										</span> </label> </div>
							   
							</div>
							
							<input type="submit" style="margin-left:150px;margin-top:10px;background-color:#F37B1D ;color: #fff;width: 100px;height: 30px;border: 0px;border-radius: 5px;" value="确定" onclick="return check('<%=index %>');">
							</div>
							
						</div>
						</form>
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
  
 </div>

</body>
</html>

