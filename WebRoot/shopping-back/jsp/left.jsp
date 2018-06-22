<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页左侧导航</title>
<%String path =request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/public.css" />
<script type="text/javascript" src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-back/js/public.js"></script>
<head></head>

<body id="bg">
	<!-- 左边节点 -->
	<div class="container">

		<div class="leftsidebar_box">
			<a href="<%=path %>/BackIndexAction" target="_top"><div class="line">
					<img src="<%=path %>/shopping-back/img/coin01.png" />&nbsp;&nbsp;首页
				</div></a>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coin07.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coin08.png" /> 用户管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=1&flag=1" target="main"
						class="cks">会员信息管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=1&flag=1&adminid=1" target="main"
						class="cks">管理员信息管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
			</dl>
			
			
			
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coin14.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coin13.png" /> 商品管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" />
						<a class="cks" href="<%=path%>/servlet/BackGoodsListAction?leftPD=1" target="main">商品信息管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
				
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" />
						<a class="cks" href="<%=path%>/servlet/BackGoodsListAction?leftPD=2" target="main">商品分类管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
			</dl>
			
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coin17.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coin18.png" /> 订单管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" />
						<a class="cks" href="<%=path%>/servlet/BackGoodsListAction?leftPD=3" target="main">订单管理</a><img class="icon5"
						src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
				
			</dl>
			
			
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coin11.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coin12.png" /> 信息管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a class="cks" href="<%=path%>/servlet/NoticeListAction"
						target="main">公告管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" />
						<a class="cks" href="<%=path%>/servlet/MessageListAction" target="main">用户留言管理</a>
						<img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a class="cks" href="<%=path%>/servlet/CommentListAction" target="main">用户评价管理</a>
						<img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
			
			</dl>
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coin03.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coin04.png" /> 图片面板管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a class="cks" href="<%=path%>/servlet/AdImagListAction"
						target="main">广告图片管理</a><img class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
			</dl>
			
			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path %>/shopping-back/img/coinL1.png" /><img class="icon2"
						src="<%=path %>/shopping-back/img/coinL2.png" /> 系统管理<img class="icon3"
						src="<%=path %>/shopping-back/img/coin19.png" /><img class="icon4"
						src="<%=path %>/shopping-back/img/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="<%=path %>/shopping-back/img/coin111.png" /><img class="coin22"
						src="<%=path %>/shopping-back/img/coin222.png" /><a class="cks">退出</a><img
						class="icon5" src="<%=path %>/shopping-back/img/coin21.png" />
				</dd>
			</dl>

		</div>

	</div>
</body>
</html>