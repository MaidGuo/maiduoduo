<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*, dao.*, domain.*"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	ArrayList<NoticeInfo> noticelist = new ArrayList<NoticeInfo>();
	noticelist = (ArrayList<NoticeInfo>)request.getSession().getAttribute("noticelist");
	request.getSession().removeAttribute("noticelist");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告管理</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/style.css" />
 <link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/index.css" />
 <script src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="<%=path %>/shopping-back/js/menu.js"></script>
</head>

<body>
	<!--  <div class="container">
		<div class="right-menu">-->
			<div class="main-hd">
				<div class="page-teb">
					<div class="l-tab-links">
						<ul style="left: 0;">
							<li class="l-select"><a href="#">首页</a></li>
							<li class="l-select"><a href="#">公告管理</a></li>
						</ul>
					</div>

					<div class="l-tab-content" style="background: #fff;">
						<div class="tab-content-item">
							<div class="home">
								<!--公告管理   开始-->
								<div class="yg-gl">
									<div class="yg-add">
										<button class="ui-xz" data-method="post"
											onClick="window.location.href='notice-add.jsp'" name="add">新增</button>
										<button class="ui-xz" data-method="post" onClick="selectOne()" name="delet">删除</button>
										<button class="ui-xz" data-method="post"
											onClick="selectOne_1()" name="edit">编辑</button>
										<!-- <button  class="ui-xz2" data-method="post" onClick="window.location.href='gg-bj.html'">查看</button> -->
									</div>
									<div class="yg-tab">
										<div class="grid">
											<div class="layoutitem" style="width: 100%; border: none;">
												<form method="post">
													<table class="gridbar" border="0" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th width="10%">选择</th>
																<th scope="col" width="10%">编号</th>
																<th scope="col">公告标题</th>
																<th>发布时间</th>
																<th>截止时间</th>
															</tr>
															<%
																for (int i=0; i<noticelist.size(); i++) {
																	NoticeInfo noticeInfo = new NoticeInfo();
																	noticeInfo = noticelist.get(i);											
															%>
															<tr>
																<td><input type="checkbox" name="radio_tj" value="<%=noticeInfo.getNotice_id()%>"></td>
																<td><%=noticeInfo.getNotice_id()%></td>
																<td><%=noticeInfo.getNotice_theme()%></td>
																<td><%=noticeInfo.getCreated_time()%></td>
																<td><%=noticeInfo.getUpdate_time() %></td>
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
													共<i class="blue"><%=noticelist.size()%></i>条记录，当前显示第&nbsp;<i class="blue">1&nbsp;</i>页
												</div>
												<ul class="paginList">
													<li class="paginItem"><a href="#"><span
															class="pagepre"><img src="img/jt1.png"></span></a></li>
													<li class="paginItem"><a href="#">1</a></li>
													<!--
													<li class="paginItem current"><a href="#">2</a></li>
													<li class="paginItem"><a href="#">3</a></li>
													<li class="paginItem"><a href="#">4</a></li>
													<li class="paginItem"><a href="#">5</a></li>
													<li class="paginItem more"><a href="#">...</a></li>
													<li class="paginItem"><a href="#">10</a></li>
													<li class="paginItem"><a href="#"><span
															class="pagenxt"><img src="img/jt.png"></span></a></li>  -->
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"></script>
	<script>
		function selectOne () {
			var name = document.getElementsByName("radio_tj");
			var flag = false;
			for (var i=0; i<name.length; i++) {
				if (name[i].checked)
				{
					var id = name[i].value;
					flag = true;
					break
				}
			}
			if (!flag) {
				alert("请选择一个！");
				return false;
			} else {
				window.location = "<%=path%>/servlet/NoticeDeleteAction?id="+id;
			}
		}
		function selectOne_1 () {
			var name = document.getElementsByName("radio_tj");
			var flag = 0;
			for (var i=0; i<name.length; i++) {
				if (name[i].checked)
				{
					var id = name[i].value;
					flag++;
				}
			}
			if (flag == 0) {
				alert("您还没有选择，请选择一个！");
				return false;
			} else if (flag == 1){
				window.location = "<%=path%>/servlet/NoticeGetId?id="+id;
			} else {
				alert("您不能选择多个，请只选择一个！");
			}
		}
	</script>
</body>
</html>