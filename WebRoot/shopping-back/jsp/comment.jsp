<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.*,dao.*,domain.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	ArrayList<CommentInfo> commentlist = new ArrayList<CommentInfo>();
	CommentDao commentDB = new CommentDao();
	commentlist = (ArrayList<CommentInfo>)request.getSession().getAttribute("commentlist");
	request.getSession().removeAttribute("commentlist");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>评论管理</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/index.css" />
<script src="js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="<%=path %>/shopping-back/js/menu.js"></script>
</head>
<body>
	<form name="frm" action="<%=path%>/servlet/CommentDeleteAction" method="post">
		<!-- <div class="container">
			<div class="right-menu"> -->
				<div class="main-hd">
					<div class="page-teb">
						<div class="l-tab-links">
							<ul style="left: 0;">
								<li class="l-select"><a href="#" style="padding: 0 25px;">首页</a>
								</li>
								<li class="l-select"><a href="#">评论管理</a> <span
									class="close"></span></li>
							</ul>
						</div>

						<div class="l-tab-content" style="background: #fff;">
							<div class="tab-content-item">
								<div class="home">
									<!--评论管理   开始-->
									<div class="yg-gl">
										<div class="yg-add" style="height: 48px;">
											<ul class="inline-rr">
												<li class="frr"><input type="text"
													class="ui-rr ui-rr-img" name="beijingdate"> <span>至</span>
													<input type="text" class="ui-rr ui-rr-img" name="enddate">
												</li>
												<li class="frr"><select class="ui-combo" name="select">
														<option value="0">好</option>
														<option value="1">一般</option>
														<option value="2">差</option>
												</select></li>
												<li><button formmethod="post"
														formaction="<%=path%>/servlet/SelectCommentListAction"
														style="height: 40px; background-color: #4dc2e6; border: 0px; color: #FFFFFF;">查找</button></li>
												<li><button formmethod="post"
														formaction="<%=path%>/servlet/CommentDeleteAction"
														style="height: 40px; background-color: #4dc2e6; border: 0px; color: #FFFFFF;"
														onClick="selectOne()">删除</button></li>
											</ul>
										</div>

										<div class="yg-tab">
											<div class="grid">
												<div class="layoutitem" style="width: 100%; border: none;">
													<form method="post">
														<table class="gridbar" border="0" cellpadding="0"
															cellspacing="0">
															<thead>
																<tr>
																	<th>选择</th>
																	<th>编号</th>
																	<th>商品编号</th>
																	<th>商品图片</th>
																	<th>评论人</th>
																	<th width="30%">评论内容</th>
																	<th>评论时间</th>

																</tr>
																<%
																	for (int i=0; i<commentlist.size(); i++) {
																		CommentInfo commentInfo = new CommentInfo();
																		commentInfo = commentlist.get(i);
																		GoodsImgInfo goodsImagInfo = new GoodsImgInfo();
																		UserInfo userInfo = new UserInfo();
																        goodsImagInfo = commentDB.getGoodsImag(commentInfo.getGoods_id());
																		userInfo = commentDB.getUser(commentInfo.getUser_id());
																																
																		if (i%2 == 0) {
																%>
																<tr>
																	<%
																		} else {
																%>
																
																<tr class="odd-l">
																	<%
																		}
																%>
																	<td><input type="checkbox" name="check_s"
																		value="<%=commentInfo.getComment_id()%>"></td>
																	<td><%=commentInfo.getComment_id()%></td>
																	<td><%=commentInfo.getGoods_id()%></td>
																	<td><img src="<%=goodsImagInfo.getImg_path()%>"></td>
																	<td><%=userInfo.getName()%></td>
																	<td><%=commentInfo.getComment_content()%></td>
																	<td><%=commentInfo.getComment_time()%></td>
																</tr>
																<%
																	}
																%>
															</thead>
														</table>
													</form>
												</div>
												<div class="pagin">
													<div class="message">
														共<i class="blue"><%=commentlist.size()%></i>条记录，当前显示第&nbsp;<i
															class="blue">1&nbsp;</i>页
													</div>
												</div>

											</div>
										</div>
									</div>
									<!--评论管理   结束-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	<script type="text/javascript"></script>
	<script>
		function selectOne() {
			var name = document.getElementsByName("check_s");
			var flag = false;
			for (var i = 0; i < name.length; i++) {
				if (name[i].checked) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				alert("请最少选择一个！");
				return false;
			}
		}
	</script>
</body>
</html>