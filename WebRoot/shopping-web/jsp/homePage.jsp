<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>买多多</title>
<%
	String path = request.getContextPath();
%>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path%>/shopping-web/img/little_logo.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/shopping.css"> 
<link rel="stylesheet" href="<%=path%>/shopping-web/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/carousel.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/notice.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/goods.css">
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/shopping-web/css/search.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/sweetalert/sweetalert.css">
<script type="text/javascript"
	src="<%=path%>/shopping-web/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/shopping-web/js/style.js"></script>
<script type="text/javascript"
	src="<%=path%>/shopping-web/jquery/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/shopping-web/js/carousel.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-web/js/checkCookie.js"></script>

<script type="text/javascript" src="<%=path %>/shopping-web/sweetalert/sweetalert.min.js"></script>
  
</head>

</script>
<body class="bg">
	<!-- 头 -->
	<div id="head">
		<div class="fldiv">
			<img src="<%=path%>/shopping-web/img/mdd_logo.png">
		</div>
		 <%
   	UserInfo user=(UserInfo)session.getAttribute("user");
   	int id=0;
   	if(user==null)
   		user=new UserInfo();
   		else 
   		id=user.getId();
   %> 
		<div class="frdiv">
			<ul class="nav">
	<li><a  class="on" href="<%=path %>/servlet/WebGoodsManageAction"> 首　　页</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的订单</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">我的买多多</a></li>
    <li><a onclick="checkCookie(this,<%=id%>)">购物车</a></li>
    <li><a  href="<%=path %>/shopping-web/jsp/login.jsp">登　　录</a></li>
    <li><a href="<%=path %>/shopping-web/jsp/register.jsp">注　　册</a></li>
			</ul>
		</div>
		<div>
			<img src="<%=path%>/shopping-web/img/mdd_headline.jpg" width="100%">
		</div>
	</div>
	<!-- 头 -->

 <!--  <form name="homefom" method="post" action="<-%=path %>/GoodsThridListAction" >-->
	<div id="searchBox"> <!-- container -->
	<div class="search bar6"> 
	<form name="findfom" class="formf" method="post" action="<%=path %>/servlet/FindGoodsAction"> 
	<input name="findGoodsBox" type="text" placeholder="请输入您要搜索的内容..."> 
	<button type="submit"></button> </form>
    </div>

	<!-- 
	<div class="searchBox">
		<!-- 搜索框 --><!-- 
		<center>
			<input name="findGoodsBox" class="wen" results="s" type="search" value="" />
		</center>
	</div>
	<!-- 搜索框 -->
	<hr />
	<div class="leftnavi">
		<!-- 导航条，效果及公告 -->
		<div class="containor">
			<!--导航条  -->
			<div class="nav_left">
				<ul>
		<%
		ArrayList<FirstClassifyInfo> allFirstClassifyList = (ArrayList<FirstClassifyInfo>) session
								.getAttribute("allFirstClassify");//一级类别
		if (allFirstClassifyList == null || allFirstClassifyList.size() == 0) { //必须判断，可能取空，报错
		} else {
			for (int i = 0; i < allFirstClassifyList.size(); i++) {
				int aId = allFirstClassifyList.get(i).getF_Classify_id();//1
				String fName = allFirstClassifyList.get(i).getF_Classify_name();
				%>
					<li data-id="<%=(i + 1)%>"><span><%=fName%></span></li>
					<!--显示一级类别-->
				<%
				} //1for
				%>
				
			<%
				} //else
			%>
			</ul>
			</div>
		<div class="nav_right">
		<%
		//二级类别
		ArrayList<SecondClassifyInfo> allSecondClassifyList;//2
		ArrayList<ThridClassifyInfo> allThreeClassifyList;//3
		for (int i = 0; i < allFirstClassifyList.size(); i++) {//1循环开始
			int bId = allFirstClassifyList.get(i).getF_Classify_id();//1
			allSecondClassifyList = (ArrayList<SecondClassifyInfo>) session.getAttribute("allSecondClassify");
		%>
    <div class="sub hide" data-id="<%=i + 1%>">
			<!-- 2 start -->
	<%
		for (int j = 0; j < allSecondClassifyList.size(); j++) {
			if(allSecondClassifyList.get(j).getF_classify_id() == bId){//如果是属于类别1
		String secondName = allSecondClassifyList.get(j).getS_Classify_name();
	%>
		<dl>
		<dt><a><%=secondName%> <i> &gt;</i></a></dt>
		<dd>
	<%
	//3级类别
	int cId = allSecondClassifyList.get(j).getS_Classify_id();//2
	allThreeClassifyList = (ArrayList<ThridClassifyInfo>) session.getAttribute("allThreeClassify");
	for (int k = 0; k < allThreeClassifyList.size(); k++) {
		if(allThreeClassifyList.get(k).getS_classify_id()==cId){
		int tId= allThreeClassifyList.get(k).getT_Classify_id();
		String ThreeName = allThreeClassifyList.get(k).getT_Classify_name();
		
		%>
		<a href='<%=path %>/servlet/GoodsThridListAction?thridId=<%=tId%>'><%=ThreeName%></a>
		
		<%
	}}
		%>
		</dd>
	</dl>
	<!-- 2 over -->
	<%}
	}%>
	</div>
	<%}%>	
	</div><!-- 类别完成 -->
 </div><!--导航条  -->
<!--</div>
</form> ++ -->

		<div class="left_effect">
			<!-- 效果 -->
			<div class="J_Poster poster-main"
				data-setting='{"width":750,"height":270,
   "posterWidth":600,"posterHeight":270,"scale":0.8,"autoPlay":true,"delay":2000,"speed":300}'>
				<div class="poster-btn poster-prev-btn"></div>
				<ul class="poster-list">
<%
 AdDao adImgDao=new AdDao();
 ArrayList<AdImagInfo> adImglist=adImgDao.AdImage();
for (int k = 0; k < adImglist.size(); k++) {
	String adPath=adImglist.get(k).getImg_path();
	int adId = adImglist.get(k).getAd_id();
	%>
	<li class="poster-item">
	<a href="#"><img src="<%=path+adPath%>" width="100%" height="100%"></a></li>
	<%
}
%>	
				</ul>
				<div class="poster-btn poster-next-btn"></div>
			</div>
			<p style="height: 50px;"></p>
			<script>
				$(function() {
					Carousel.init($(".J_Poster"));
				});
			</script>
		</div>
		<!-- 效果 -->


		<div id="mddNotice">
			<!--公告  -->
			<!--  头部 -->
			<h3 id="mddTitle"><!-- target="_self" -->
				公 告<a href="<%=path %>/servlet/BeforNoticeListAction" >更多>></a>
			</h3>
			<!--  头部结束 -->
			
			<!--  中间 -->
			<div id="mddBox">
				<ul id="con1">
<%
 ArrayList<NoticeInfo> noticeList = (ArrayList<NoticeInfo>) session.getAttribute("allNoticeList");//公告list
 if(noticeList.size()==0 || noticeList==null){
 }else{
	 for(int i=0;i<noticeList.size();i++){
		 int noticeId=noticeList.get(i).getNotice_id();
		 String notice_content=noticeList.get(i).getNotice_content();
		 %>
		 <li><a href="<%=path%>/servlet/BeforeContentListAction?id=<%=noticeId%>"><%=i+1 %>.<%=notice_content %></a></li>
		 <%
	 }
 }
%>
				</ul>
				<ul id="con2">
				</ul>
			</div>
			<!--  中间结束 -->
		</div>
		<!--  公告结束 -->

		<script type="text/javascript">
		
			var area = document.getElementById('mddBox');
			var con1 = document.getElementById('con1');
			var con2 = document.getElementById('con2');
			var speed = 50;
			area.scrollTop = 0;
			con2.innerHTML = con1.innerHTML;
			function scrollUp() {
				if (area.scrollTop >= con1.scrollHeight) {
				alert("---");
					area.scrollTop = 0;
				} else {
			
					area.scrollTop++;
				}
			}
			var myScroll = setInterval("scrollUp()", speed);
			
			area.onmouseover = function() {
				clearInterval(myScroll);
			}
			area.onmouseout = function() {
				myScroll = setInterval("scrollUp()", speed);
			}
		</script>
		<!--公告  -->
</div> 
	<!-- 导航条，效果及公告 -->

	<hr />
	
	<form name="goodfrm" action="<%=path%>/servlet/GoodsListAction" method="post">
		<div class="goodsAll">
	<!-- 商品 -->
	<%
	if (allFirstClassifyList == null || allFirstClassifyList.size() == 0) { 
		%><li><span>空</span></li>
	<%} else {
		 for (int i = 0; i < allFirstClassifyList.size(); i++) {//1级循环
			int fId = allFirstClassifyList.get(i).getF_Classify_id();//1
			String fName = allFirstClassifyList.get(i).getF_Classify_name();
	%>
		<div>
		<!-- 美妆/个人护理等 -->
		<div class="goods1"><%=fName%>
		&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">更多>></a>
		</div>
		<ul class="goodsPic">
<!-- ************************************************* -->
	<%
	int goodsNum=0;//最多显示4个图片
	ClassifyDao classifyDao=new ClassifyDao();//整个分类
	AllClassifyInfo classify=new AllClassifyInfo();//整个分类
	ArrayList<GoodsInfo> goodsList = (ArrayList<GoodsInfo>) session.getAttribute("allGoodsList");//作用域是整个会话期间，在所有的页面都使用这些数据的时候使用。
	if (goodsList == null || goodsList.size() == 0) { //判断，可能取空，报错
	} else {
		for (int j = 0; j < goodsList.size(); j++) {
			int gId = goodsList.get(j).getGoods_id();
			int gtId=goodsList.get(j).getT_classify_id();//3
			classify=classifyDao.getAllClassifyOfOne(gtId);//整个分类
			if(classify.getF_Classify_id()==fId && goodsNum<4){//如果是这一大类的
				goodsNum++;
				String gName = goodsList.get(j).getGoods_name();
				double gPrice = goodsList.get(j).getGoods_price();
				String gImgPath = goodsList.get(j).getImg_path();//商品图片
		%>
	<li><input type="hidden" name="goodsId" value="<%=gId%>">
	<a href="<%=path%>/servlet/GoodsAppearAction?goodsId=<%=gId%>">
	<img src="<%=path+gImgPath%>"><br><%=gName%>
	<br>￥<%=gPrice%></a></li>

	<%  }//if
	 } //for j
	} //else小
					%>
<!-- ************************************************* -->
	</ul>
	</div>
	<!-- 美妆/个人护理等 -->
	<hr>
	<%
	 } //for i
	} //else大
	%>
	<!-- 商品 -->
	</form>

	<!-- 尾 -->
	<div id="foot">
		<div>
			<!-- 尾上 -->
			<div class="footldiv">
				<img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg">
				<!-- 二维码 -->
				<div>扫描关注微信买多多</div>
			</div>
			<div class="footldiv">
				<img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg">
				<!-- 二维码 -->
				<div>扫描查看更多信息</div>
			</div>
			<div class="footldiv">
				<img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg">
				<!-- 二维码 -->
				<div>扫描下载买多多APP</div>
			</div>
			<div class="footldiv">
				<img src="<%=path%>/shopping-web/img/mdd_foot_eechat1.jpg">
				<!-- 二维码 -->
				<div>扫描领取新人优惠</div>
			</div>

			<div class="footrdiv">
				<table class="ftstyle">
					<tr>
						<td>帮助中心</td>
					</tr>
					<tr>
						<td>联系与合作</td>
					</tr>
					<tr>
						<td>知识产权</td>
					</tr>
					<tr>
						<td>许可证明</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 尾上 -->

		<div class="bfoot">
			<!-- 尾下 -->
			<br> Copyright 2015-2018 Maiduoduo.com <br> 路公网备号
			2084843827483号
		</div>
		<!-- 尾下 -->

	</div>
	<!-- 尾 -->
</body>
</html>