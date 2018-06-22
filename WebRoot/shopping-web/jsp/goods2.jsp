<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<%String path = request.getContextPath();%>
<link rel="shortcut icon" type="image/x-icon" href="<%=path%>/shopping-web/img/little_logo.ico"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/common.css">
 <link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/goods2.css">
	<script type="text/javascript" src="<%=path%>/shopping-web/jquery/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="<%=path%>/shopping-web/js/jquery.imagezoom.min.js"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/goods2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/shopping-web/css/search.css">   
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>

<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>
</head>
 <%UserInfo user= (UserInfo)session.getAttribute("user");
if(user==null){

	 	user=new UserInfo();
}
int id=user.getId();
  %>
<body>
<!-- 头 -->
 <div id="head">
  <div class="fldiv">
  <img src="<%=path%>/shopping-web/img/mdd_logo.png">
  </div>
  <div class="frdiv">
   <ul class="nav1">

	<li><a class="on" href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a  onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
  </ul>
  </div>
  <div >
   <img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
  </div>
  
 </div><!-- 头 -->
 <%
   GoodsInfo goods = (GoodsInfo)session.getAttribute("oneGoodsInfo");
      int gId=goods.getGoods_id();
 	  String gName=goods.getGoods_name();
 	  double gPrice=goods.getGoods_price();
 	  int gNum=goods.getGoods_num();//库存量
 	  int gPurchase=goods.getPurchase_times();//购买次数
 	  int gComment=goods.getComment_times();//评价次数
 	  int gStatus=goods.getStatus();//状态
 	  String gdesc=goods.getGoods_description();//描述
 	  String imgPath=goods.getImg_path();//图片路径
 %>
  <div id="searchBox"> <!-- 搜索框 -->
	<div class="search bar6"> 
	<form name="findfom" class="formf" method="post" action="<%=path %>/servlet/FindGoodsAction"> 
	<input name="findGoodsBox" type="text" placeholder="请输入您要搜索的内容..."> 
	<button type="submit"></button> </form>
    </div><!-- 搜索框 -->

    <hr>
    
  <div class=""><!--主体-->
  <div class="goodsC"><!--上-->
  <div class="goodsL"><!--商品图片-->
    <div class="tb-booth tb-pic tb-s310"> <a href="<%=path+imgPath%>"><img src="<%=path+imgPath%>" alt="展品细节展示" rel="<%=path+imgPath%>" class="jqzoom" /></a> </div>
  </div><!--商品图片结束-->
  
  <div class="goodsR"><!--价格等-->
  <form name="sfom" id="sfom" method="post" ><!-- +++++ -->
  <ul>
     <li>商品名：<%=gName %></li>
     <li>销量：<%=gPurchase %></li>
     <li>价格：<span class="redSet">￥<%=gPrice %></span></li>
     <li>运费：<span class="redSet">包邮</span></li>
     <li>数量：
       <ul class="numBuy">
            <li><input type="button" name="minus" value="-" onclick="jian()">
            <input type="text" name="amount" value="1" id="shang" readonly="readonly">
            <input type="button" name="plus" value="+" onclick="jia(<%=gNum%>)"></li>
        </ul>
     </li>  
     <li> 库存：<%=gNum %>件</li>
     
     <li><input type="button" class="buttonBuy" value="加入购物车" onclick="addGoods(<%=gId%>,<%=id%>)">
     </li>
  </ul>
  </form><!-- +++++ -->
  </div><!--价格等结束-->
  <div class="goodsR"><!--商品描述-->
   <ul>
    <li class="littleHead" >商品详情</li>
    <li><%=gdesc %></li>
   <li class="little"><br>服务承诺：  正品保证 极速退款<br>&nbsp;&nbsp;&nbsp;&nbsp; 材质保真险 七天无理由退换</li>
   </ul>
  </div><!--商品描述结束-->
  
  </div><!--上结束-->
  
  <div class="viewPart"><!--商品评价-->
   <ul>
    <li class="viewHead">商品评价&nbsp; | &nbsp;累计评价：<%=gComment %></li>
    <li>评价1<br><span style="color:#CDCDCD; font-size:14px;">2018.5.5</span></li>
    <li>评价2<br><span style="color:#CDCDCD; font-size:14px;">2018.5.5</span></li>
    <li>评价3<br><span style="color:#CDCDCD; font-size:14px;">2018.5.5</span></li>
   </ul>
   
  <!--显示页数-->
  页数显示
  
  </div><!--商品评价结束-->
  
  </div>


 <!-- 尾 -->
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
  
 </div><!-- 尾 -->

</body>

<script type="text/javascript">
	function addGoods(goodsId,id){

	if(id==0){
	swal({
			title:"你还没有登录，请登录后再查看",
			text:"正在前往登录页面",
			type:"warning",
			showConfirmButton: true
			},function(){

			location.href = "login.jsp";

		});
	}else{
	
	 var $a=goodsId;
	var $b=document.getElementById("shang").value;

			 $.ajax({
			  	type:"post", 
		    	url:"${pageContext.request.contextPath}/ShoppingCartAction?flag=add",
		    	dataType:"text",
		    	data:{"goodsId":$a,"goodsNum":$b},
		    	success: function (data) {
		    	
		    	if(data=="true"){
		    	
		    		swal({	
		    				title:"添加商品成功",
		        			type:"success",
		    				showConfirmButton: true,
		    			});
		    	
		    				}else if(data=="false"){
								swal({
									title:"加购失败",
									type:"error",
									showConfirmButton: true,
								});
								
							}
		    			 },error: function(data){
		    			 	alert("cuowu");
		    			 }
		      
		      });
	
	}
	
	}
</script>
</html>