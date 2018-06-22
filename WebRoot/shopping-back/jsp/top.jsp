<%@ page language="java" import="java.util.*,domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/top.css" >
 	
  </head>
  <%
	AdminInfo admin=(AdminInfo)session.getAttribute("admin");
	if(admin==null){
		admin=new AdminInfo();
	}
	String name=admin.getAdmin_name();
 %>
<body onLoad="getTime()">

<div class="head">
	<div class="fldiv">
  <img src="<%=path %>/shopping-back/img/back_font.png">
  </div>
	 <div class="frdiv">
   <ul class="nav" >
    <li>你好，<%=name %> &nbsp;&nbsp;</li>
    <li id="date1" ></li>
    <li><a href="<%=path %>/AdminLoginAction?method=logout" target="_top">[退出]</a></li>
  </ul>
  </div>
	
</div>
</body>  
  <script type="text/javascript">
function getTime() {

    var dateObj = new Date();

    var year = dateObj.getFullYear();//年
    var month = dateObj.getMonth()+1;//月  (注意：月份+1)
    var date = dateObj.getDate();//日
    var day = dateObj.getDay();
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day];//根据day值，获取星期数组中的星期数。
    

    if(month<10){
        month = "0"+month;
    }
    if(date<10){
        date = "0"+date;
    }
   
    var newDate = year+"-"+month+"-"+date+"&nbsp &nbsp"+week;
    document.getElementById("date1").innerHTML =  newDate;//在div中写入时间
    setTimeout('getTime()', 500);//每隔500ms执行一次getTime()
   
							
}
</script>
</html>
