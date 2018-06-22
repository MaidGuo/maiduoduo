<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑公告</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/index.css" />

<script src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="<%=path %>/shopping-back/js/menu.js"></script>
</head>

<body>
	<form name="frm" action="<%=path %>/servlet/NoticeAddAction" method="post">
		<!--  <div class="container">
			<div class="right-menu">-->
				<div class="main-hd">
					<div class="page-teb">
						<div class="l-tab-links">
							<ul style="left: 0;">
								<li class="l-select"><a href="#">首页</a></li>
								<li class="l-select"><a href="#">编辑</a></li>
							</ul>
						</div>
						<!-- 公告标题 -->
						<div class="l-tab-content" style="background: #fff;">
							<div class="tab-content-item">
								<div class="home">
									
									<div class="gys-list1">
										<div class="layoutgr">
											<ul>
												<li><label style="foot-size: 24px; align: center;">公告标题</label>
													<input type="text" class="buedit111" name="title"
													id="title"></li>
												<li><label style="foot-size: 24px; align: center;">公布时间</label>
													<input type="text" class="buedit111" name="created_time"
													id="created_time"></li>
												<li><label style="foot-size: 24px; align: center;">结束时间</label>
													<input type="text" class="buedit111" name="update_time"
													id="update_time"></li>
											</ul>
											<div class="cellspacing">
												<div class="cell-je">
													<button class="rz" style="border: 0px;"
														onClick="not_Null()">确定保存</button>
												</div>
											</div>
										</div>
										<!-- 公告内容 -->
										<div class="grid">
											<div class="layoutitem" style="width: 100%; border: none;">
												<form method="post">
													<table class="gridbar" border="0" cellpadding="0"
														cellspacing="0">
														<thead>
															<tr>
																<th scope="col">公告内容</th>
															</tr>
															<tr>
																<td><textarea
																		style="border: 0px; width: 100%; height: 700px;"
																		name="content" id="content"></textarea></td>
															</tr>
														</thead>
													</table>
												</form>
											</div>
										</div>

									</div>
								</div>
								<!-- 编辑公告结束 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript"></script>
	<script>
		function not_Null() {
			var text1 = document.getElementById("title");
			var text2 = document.getElementById("created_time");
			var text3 = document.getElementById("update_time");
			var text4 = document.getElementById("content");
			if (text1.value == "") {
				alert("公告标题不能为空");
				return false;
			}
			if (text2.value == "") {
				alert("公告开始时间不能为空！");
				return false;
			}
			if (text3.value == "") {
				alert("公告结束时间不能为空！");
				return false;
			}
			if (text4.value == "") {
				alert("公告内容不能为空！");
				return false;
			}
			
		}
	</script>
</body>
</html>