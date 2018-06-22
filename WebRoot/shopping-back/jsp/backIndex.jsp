<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
</head>
<frameset rows="100,*" cols="*" scrolling="No" framespacing="0"
	frameborder="no" border="0"> <frame src="top.jsp"
	name="headmenu" id="mainFrame" title="mainFrame" scrolling="no" framespacing="0" frameborder="no" border="0"><!-- 引用头部 -->
<!-- 引用左边和主体部分 -->
 <frameset rows="100*" cols="220,*" scrolling="No"	framespacing="0" frameborder="no" border="0">
  <frame src="left.jsp" name="leftmenu" id="mainFrame" title="mainFrame">
	<frame src="main.jsp" name="main" scrolling="yes" noresize="noresize"
	id="rightFrame" title="rightFrame"></frameset></frameset><noframes></noframes>
</html>
