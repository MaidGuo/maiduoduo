<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.*, dao.*, domain.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	ArrayList<MessageInfo> messagelist = new ArrayList<MessageInfo>();
	MessageDao messageDB = new MessageDao();
	messagelist = (ArrayList<MessageInfo>)request.getSession().getAttribute("messagelist");
	request.getSession().removeAttribute("messagelist");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>留言管理</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/index.css" />
<script src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="<%=path %>/shopping-back/js/menu.js"></script>
</head>

<body>
	<form name="frm" method="post"
		action="<%=path%>/servlet/MessageReplyAction">
		<!--  <div class="container">
			<div class="right-menu">-->
				<div class="main-hd">
					<div class="page-teb">
						<div class="l-tab-links">
							<ul style="left: 0;">
								<li class="l-select"><a href="#" style="padding: 0 25px;">首页</a>
								</li>
								<li class="l-select"><a href="#">留言管理</a> <span
									class="close"></span></li>
							</ul>
						</div>

						<div class="l-tab-content" style="background: #fff;">
							<div class="tab-content-item">
								<div class="home">
									<!--留言   开始-->
									<div class="yg-gl">

										<div class="yg-tab">
											<div class="grid">
												<div class="layoutitem" style="width: 100%; border: none;">
													<form method="post">
														<table class="gridbar" border="0" cellpadding="0"
															cellspacing="0">
															<thead>
																<tr>
																	<th width="10%">选择</th>
																	<th width="2%">编号</th>
																	<th width="10%">留言人昵称</th>
																	<th width="37%">留言内容</th>
																	<th width="8%">是否回复</th>
																	<th width="23%">回复内容</th>
																	<th width="10%">留言时间</th>
																</tr>
																<%
																	for (int i=0; i<messagelist.size(); i++) {
																		MessageInfo messageInfo = new MessageInfo();
																		messageInfo = messagelist.get(i);
																		String user_name = messageDB.getUser_Name(messageInfo.getUserid());
																		String reply_content = messageDB.getReplyContent(messageInfo.getMessageid());
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
																		value="<%=messageInfo.getMessageid()%>"></td>
																	<td><%=messageInfo.getMessageid()%></td>
																	<td><%=user_name%></td>
																	<td><%=messageInfo.getContent()%></td>
																	<td><%=messageInfo.getIsReply() == 0 ? "否" : "是"%></td>
																	<td><%=messageInfo.getIsReply() == 0 ? "无" : reply_content%></td>
																	<td><%=messageInfo.getTime()%></td>
																</tr>
																<%
																	}
																%>
															</thead>
														</table>
													</form>
												</div>
												<div class="odd-l">
													<button class="ui-btn-js" data-method="post" class="yg-add"
														onClick="selectOne_notNull()">回复</button>
													<input type="text"
														style="width: 400px; border: 1px; border-color: #006699;"
														id="reply" name="reply"></input>
												</div>

												<div class="pagin">
													<div class="message">
														共<i class="blue"><%=messagelist.size()%></i>条记录，当前显示第&nbsp;<i
															class="blue">1&nbsp;</i>页
													</div>
									
												</div>
											</div>
										</div>
									</div>
									<!--留言管理   结束-->
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
		function selectOne_notNull() {
			var name = document.getElementsByName("check_s");
			var flag = 0;
			for (var i = 0; i < name.length; i++) {
				if (name[i].checked) {
					flag++;
					break
				}
			}
			if (flag == 0) {
				alert("请选择一个！");
				return false;
			}
			if (flag != 1) {
				alert("请最少选择一个！");
				return false;
			}
		}
	</script>
</body>
</html>