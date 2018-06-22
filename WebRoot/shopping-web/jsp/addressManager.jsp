<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,domain.*,common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path=request.getContextPath();
	List<ReceiveAddress> addresslist=(List<ReceiveAddress>)session.getAttribute("addresslist");
	int length=addresslist.size();
	String count=request.getParameter("count");
	String flag=request.getParameter("flag");
	int f1,f2;//f1:新增地址是否成功标记  f2:删除地址是否成功标记
	if(count==null){ //未进行地址新增操作
		f1=0;
	}else{
		if(!count.equals("1")){  //新增地址失败
			f1=1;
		}else{
			f1=2; //新增地址成功
		}
	}
	if(flag==null){
		f2=0;
	}else{
		if(!flag.equals("1")){
			f2=1;
		}else{
			f2=2;
		}
	}
	String struserid=request.getParameter("userid");
	System.out.println("addressManager:输出用户id:"+struserid);
	int userid=MyTools.strToint(struserid);//得到用户id
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
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="<%=path %>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/addressCheck.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>个人中心 - 地址管理</title>
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
   <!-- 中间 -->
	<div class="bottom" style="height:700px;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path %>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=userid %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=userid %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=userid %>&pageNum=1">我的评价</dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=userid %>"><span style="color:#FF9224">地址管理</span></a></dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=userid %>&pageNum=1">留言管理</a></dd>
			
		</dl>
		</div>
	 </aside>
	</div>  
	
		<div class="bottom_right"  >
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >收货地址</font>
	    </h3>
	 	<hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	 	<form name="form" method="post" action="<%=path %>/servlet/AddressInsertAction?userid=<%=userid %>">
						<div style="margin-left: 0px;margin-top:20px;width: 100%;border: 0px;">
							<div style="width: 100%;height: 500px;">
								<p style="margin-left: 70px;font-size: 14px;"><span style="color:#F88600;font-size: 14px;">新增收货地址</span> </p>
							<div style="margin-left: 70px;margin-top: 30px;height: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">所在地区:</span>
								<select style="padding:5px;margin-left: 14px;"> 
									<option selected="">中国大陆</option>
								</select>
								   <select  name="s_province" id="s_province" onclick="checkaddr()">
					    	<option value="1">请选择省</option>
					    	
					    </select>
					    <select  name="s_city" id="s_city" onclick="checkaddr()">
					    	<option value="2">请选择市</option>
					    	
					    </select>
					    <select  name="s_county" id="s_county" onclick="checkaddr()">
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
								<textarea name="detaddress" id="detaddress" style="width:500px;height: 90px;padding: 5px;" placeholder="建议您如实填写详细收货地址，例如街道 名称，门牌号码，楼层和房间号等信息" onkeyup="checkAddress()" onfocus="checkAddress()"></textarea>
								<label style="color:#FF9224;margin-left:10pt" id="tips1"></label>
							</div>
							
							<div style="margin-left: 70px;margin-top: 20px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">邮政编号:</span>
								<input type="text" name="postcode" maxlength="6" id="postcode" placeholder="如您不清楚地区邮递号，请填写000000" style="padding: 5px;width: 300px;margin-left: 14px;" onkeyup="checkPostcode()" onfocus="checkPostcode()"/>
								<label style="color:#FF9224;margin-left:10pt" id="tips2">*邮政编码由6位数字组成</label>
							</div>
							
							<div style="margin-left: 60px;margin-top: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span>
								<span class="titles">收货人姓名:</span>
								<input type="text" name="customer" id="customer" style="padding: 5px;width: 300px;margin-left: 14px;" onkeyup="checkCustomer()" onfocus="checkCustomer()"/>
								<label style="color:#FF9224;margin-left:10pt" id="tips3"></label>
							</div>
							<div style="margin-left: 70px;margin-top: 30px;">
								<span style="color: #F2873B;">*&nbsp;</span><span class="titles">手机号码:</span>
								<input type="text" name="phone" id="phone" placeholder=""  maxlength="11" style="padding: 5px;width: 300px;margin-left: 14px;" onkeyup="checkPhone()" onfocus="checkPhone()"/>
								<label style="color:#FF9224;margin-left:10pt" id="tips4"></label>
							</div>
							<div style="margin-left: 150px;margin-top: 10px;">
								
								<div class="" style="padding-right: 0rem;"> 
									<label class="">
											<input type="checkbox"  name="defaultuadd" value=1 checked>
										<span style="font-size: 12px;color: #878787;">
											<font style="font-size: 12px;color: #333333;">设为默认地址</font>
										</span> </label> </div>
							   
							</div>
							
							<input type="submit" style="margin-left:150px;margin-top:10px;background-color:#F37B1D ;color: #fff;width: 100px;height: 30px;border: 0px;border-radius: 5px;" value="确定" onclick="return check('<%=length %>')">
							</div>
							
						</div>
						</form>
					</div>
			
</div>							
			<div style="margin-top: 30px;width: 1068px;  margin: 15px 0 30px 170px;">
				<div>
		
								<dl style="color: #F88600;font-size: 16px;margin-top: 69px;">您已添加<%=length %>个地址，你还可以添加<%=20-length %>个地址</dl>
				</div>				
								<table  style="margin-top: 20px;" rules=rows frame="box" width=100% frame=hsides class="table" border="1px">
								  <thead>
								    <tr  bordercolor="#000000" height="50px">
								      <th>收货人</th>
								      <th>所在地区</th>
								      <th>收货地址</th>
								      <th>邮编</th>
								      <th>电话/手机</th>
								      <th>操作</th>
								      <th></th>
								    </tr>
								  </thead>
								  <tbody>
								  <%
								  	for(int i=0;i<length;i++){
								  		%>
								  		<tr height="30px">
								      <td><%=addresslist.get(i).getName() %></td>
								      <td><%=addresslist.get(i).getProvince() %><%=addresslist.get(i).getCity() %><%=addresslist.get(i).getCounty() %></td>
								      <td><%=addresslist.get(i).getAddress() %></td>
								      <td><%=addresslist.get(i).getPostcode() %></td>
								      <td><%=addresslist.get(i).getTel() %></td>
								      <td style="color: #007AFF;"><a href="<%=path%>/shopping-web/jsp/modifyAddress.jsp?index=<%=i %>">修改</a>&nbsp;|&nbsp;<a href="<%=path %>/servlet/AddressDeleteAction?index=<%=i %>&userid=<%=userid %>">删除</a></td>
								      <%
								      	if(addresslist.get(i).getIsdefault()==1){
								      		%>
								      		    <td ><span style="padding: 2px;font-size: 10px;color: #EC5937;border-radius:5px;background-color: #fad5d0;border: 1px #C85E0B solid;">默认地址</span></td>
								      		<% 
								      	}else{
								      		%>
								      		<td><span></span></td>
								      		<%
								      	}
								      %>
								    </tr>
								  		<% 
								  	}
								  %>
					
								  </tbody>
								</table> 	
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
	var f1="<%=f1 %>";
	var f2="<%=f2 %>";
	if(f1==1){
		swal({
		    title:"新增地址失败",    
		    text: "",
		    type: "error",    
		    confirmButtonText: "确定" 
		})		
	} 	
	
	if(f2==1){
		swal({
		    title:"删除地址失败",    
		    text: "",
		    type: "error",    
		    confirmButtonText: "确定" 
		})	
	}
}
</script>


