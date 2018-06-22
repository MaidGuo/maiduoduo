<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="common.*,dao.*,java.util.*,domain.*"%>
<%
	String path=request.getContextPath();
	PageBean pb=(PageBean)session.getAttribute("pagePB");
	List<MessageInfo> messagelist=pb.getList();
	int length=messagelist.size(); 
	int replycount=(Integer)session.getAttribute("replycount"); //已回复的留言
	UserInfo user=(UserInfo)session.getAttribute("user");

 
	int id=0;
	if(user!=null)
		id=user.getId();
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
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/style.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/index.css"/>
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script> 
<script type="text/javascript" src="<%=path %>/shopping-web/jquery/jquery-1.9.1.min.js" ></script>
<script src="<%=path %>/shopping-web/js/showText.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/json/json-minified.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
<title>个人中心 - 我的留言</title>
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
	<div class="bottom" style="float:left;width:100%;">
	<div class="bottom-left">  
	<aside style="text-align:center">		
		
		<a href="#"><img src="<%=path %>/shopping-web/img/image2.PNG"></img></a>
	
		<div style="color:#111111 ">
		<dl>
			<dd style="font-size:14px;font-weight:bold;">账号管理</dd>
			<dd id="userInfoSet" style="margin-top:10px;"><a href="<%=path %>/servlet/UserInfoGetAction?id=<%=user.getId() %>">个人资料</a></dd>
			<dd id="pwdManager" style="margin-top:10px;"><a href="<%=path %>/servlet/PasswordGetAction?id=<%=user.getId() %>">密码管理</a></dd>
			<dd id="reviewManager" style="margin-top:10px;"><a href="<%=path %>/servlet/ReviewGetAction?id=<%=user.getId() %>&pageNum=1">我的评价</a></dd>
			<dd id="addressManager" style="margin-top:10px;"><a href="<%=path %>/servlet/AddressGetAction?userid=<%=user.getId() %>">地址管理</dd>
			<dd id="messageManager" style="margin-top:10px;"><a href="<%=path %>/servlet/MessageGetAction?userid=<%=user.getId() %>&pageNum=1"><span style="color:#FF9224">留言管理</span></a></dd>
		</dl>
		</div>
	 </aside>
	</div>  
	<div class="bottom_right">
		<h3 style="margin-top: 24px;margin-bottom:20px;margin-left:20px;background-color: #fff;" >
	                        <font style="font-size: 16px;color: #333333; font-weight:bold;" >我的留言</font>
	                    </h3>
	                    <hr style=" border-top:1px #CFCFCF solid; width:100%;"></hr>
	                    <table style="margin-top:24px">
	                    <tr>
	                    <td width="100">
	                    <div style="margin-left:15px; margin-top: 21px;border: 1px #ccc solid;border-radius: 100%;width: 75px;height: 75px;">
								<img src="<%=path %>/shopping-web/img/image2.PNG" height="75" width="75" style="">
							</div>
	                    </td>
	                    <td align="left">
	                    <div style="margin-top:24px">
							<div>
									<span  style="margin-left:15px;margin-top:10px"><font style="font-size: 14px;color: #686868; font-weight:bold;" > <%=user.getName() %></font></span>
								<a href="<%=path %>/shopping-web/jsp/complaint1.jsp?userid=<%=user.getId() %>">	<input type="button" value="我要留言" style="border: 0px; color:#FFFFFE;height:34px;width:110px;background:#F37B1D;border-radius:6px;margin-left:700px;margin-top:1px;cursor:pointer;"></a>
									<div style="margin-top:1px;">
										<span style="margin-left:15px;"><font style="font-size: 12px;color: #333333; ">已提交的留言:  </font><font style="font-size: 12px;color: #F37B1D;font-weight:bold; "><%=pb.getTotalRecord() %></font></span>
										<span><font style="font-size: 12px;color: #333333;margin-left:40px; ">已收到的回复:</font><font style="font-size: 12px;color: #F37B1D;font-weight:bold; "><%=replycount %></font></span>
										<span><font style="font-size: 12px;color: #333333;margin-left:40px; ">待回复的留言:</font><font style="font-size: 12px;color: #F37B1D;font-weight:bold; "><%=pb.getTotalRecord()-replycount %></font></span>
									</div>
								</div>
						</div>
	                    </td>
	                    </tr>
	                    </table>
							
						<div>
							<table style="margin-top: 10px;width:100%;">
											<tr style="background:#f3f5fa;height:50px;">
												<th  style="line-height:50px;"><font style="font-size: 14px;color: #333333; ">标题</font></th>
												<th  style="line-height:50px;" ><font style="font-size: 14px; color: #333333;">提交时间</font></th>
												<th  style="line-height:50px; " ><font style="font-size: 14px;color: #333333; ">状态</font></th>
												<th class="lastTd" colspan="2" style="line-height:50px; "><font style="font-size: 14px;">查看</font></th>
											</tr>
											<tr style="height:20px">
											</tr>
											<%
												int i;
												for(i=0;i<length-1;i++){
													%>
													<tr style="height:20px">
												<td><font style="font-size: 14px;color: #333333;font-weight: normal; "><%=messagelist.get(i).getTheme() %></font></th>
												<td><font style="font-size: 14px;color: #333333;font-weight: normal;"><%=messagelist.get(i).getTime() %></font></th>
												<td><font style="font-size: 14px;color: #7A7A7A;font-weight: normal; "><%=messagelist.get(i).getIsReply()==0?"待回复":"已回复" %></font></th>
												<td><a href="<%=path %>/shopping-web/jsp/complaint2.jsp?index=<%=i %>"><font style="font-size: 12px;color: #4483d6; font-weight: normal;">查看详请
												</font></a></td>
											</tr>
												<tr style="height:30px">
												<td colspan="5">
												<hr style=" border-top:1px #CFCFCF solid; width:100%;">
												</td>
												</tr>
													<% 
												}
												if(i>=0&&length!=0){
													%>
														<tr style="height:20px">
												<td><font style="font-size: 14px;color: #333333;font-weight: normal; "><%=messagelist.get(i).getTheme() %></font></th>
												<td><font style="font-size: 14px;color: #333333;font-weight: normal;"><%=messagelist.get(i).getTime() %></font></th>
												<td><font style="font-size: 14px;color: #7A7A7A;font-weight: normal; "><%=messagelist.get(i).getIsReply()==0?"待回复":"已回复" %></font></th>
												<td><a href="<%=path %>/shopping-web/jsp/complaint2.jsp?index=<%=i %>"><font style="font-size: 12px;color: #4483d6; font-weight: normal;">查看详请
												</font></a></td>
											</tr>
													<% 
												}
											%>
											
											<tr style="height:20px;">
											<tr>
							</table>
						</div>
											  <div class="pagin">
    	   <div class="message">共<i class="blue"><%=pb.getTotalRecord() %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pb.getPageNum() %>&nbsp;</i>页</div>
            <ul class="paginList">
            <%	
            	int j;
            	if((pb.getStart()<=1)&&(pb.getEnd()>=pb.getTotalPage())){ //若开始为第一页，就没有“上一页”超链接显示
            		for(j=1;j<=pb.getEnd();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=j %>&userid=<%=user.getId() %>"><%=j %></a></li>
            			<% 
            		}
            	%>
            	   <% 
            	}else if(pb.getStart()<=1){
            		
       					for(j=1;j<=pb.getEnd();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=j %>&&userid=<%=user.getId() %>"><%=j %></a></li>
            			<% 
            		}
       			%>
       			<li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=j %>&&userid=<%=user.getId() %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
       
       			<% 
            		
            	}else if(pb.getEnd()>=pb.getTotalPage()){ //当前页为最后一页，就没有“下一页”超链接显示
            		
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=pb.getStart()-1 %>&&userid=<%=user.getId() %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
                   <% 
                   for(j=pb.getStart();j<=pb.getTotalPage();j++){
                	   %>
                	   <li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=j %>&&userid=<%=user.getId() %>"><%=j %></a></li>
                	   <% 
                   }
            	}else{ //当前页既不是最后一页，也不是第一页，"上一页"，“下一页”链接都有
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=pb.getStart()-1 %>&userid=<%=user.getId() %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
           			<% 
           				for(j=pb.getStart();j<=pb.getEnd();j++){
           					%>
           					<li class="paginItem"><a href="<%=path %>/MessageGetAction?pageNum=<%=j %>&&userid=<%=user.getId() %>"><%=j %></a></li>
           					<% 
           				}
           			%>
           			<li class="paginItem"><a href="<%=path %>/servlet/MessageGetAction?pageNum=<%=pb.getEnd()+1 %>&&userid=<%=user.getId() %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
            		<%
            	}
            %>
              </ul>
          </div>
	</div>
	</div>
	<div style="height:700px">
	&nbsp;
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