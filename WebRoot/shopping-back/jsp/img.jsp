<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*, dao.*, domain.*,common.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	ArrayList<AdImagInfo> adImaglist = new ArrayList<AdImagInfo>();
	adImaglist = (ArrayList<AdImagInfo>) request.getSession().getAttribute("adImagList");
	request.getSession().removeAttribute("adImagList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片管理</title>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/style.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/index.css"/>
<script  src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript"  src="<%=path %>/shopping-back/js/menu.js"></script>
</head>

<body>
<!--  <div class="container">
 <div class="right-menu">-->
  <div class="main-hd">
   <div class="page-teb" >
    <div class="l-tab-links">
     <ul style="left:0;">
      <li class="l-select">
       <a href="#" style="padding:0px 25px;">首页</a>
      </li>
       <li class="l-select">
       <a href="#">图片</a>
       <span class="close"></span>
      </li>
     </ul>
    </div>

    <div class="l-tab-content" style="background:#fff;">
     <div class="tab-content-item">
      <div class="home">
       <!--图片管理  开始-->
       <div class="rightinfo">
        <div class="tools">
           <button onClick="select()" class="ui-xz">           
            删除
           </button>
        </div>
        <ul class="imglist">
        <%
        	for (int i=0; i<adImaglist.size(); i++) {
                        		AdImagInfo adImagInfo = new AdImagInfo();
                        		adImagInfo = adImaglist.get(i);
        %>
        	<li>
        	<%System.out.println("img-path-img.jsp: "+adImagInfo.getImg_path()); %>
        		<span><img src="<%=path+adImagInfo.getImg_path()%>" style="width:150px; height:150px;"></span>
          		<h2><input type="checkbox" name="check_s" value="<%=adImagInfo.getAd_id()%>"></h2>
        	</li>
        <%
        	}
        %>
          <li>
          <span><a href="img-add.jsp"><img src="<%=path %>/shopping-back/img/kf-add.png" style="width:150px; height:150px;"></a></span>
          <h2>添加图片</h2>
         </li>
        </ul>
       </div>
       <!--图片管理  结束-->
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
</div>
<script type="text/javascript"></script>
<script>
	function select() {
		var name = document.getElementsByName("check_s");
		var flag = false;
		for (var i = 0; i < name.length; i++) {
			if (name[i].checked) {
				var ad_id = name[i].value;
				flag = true;
				break
			}
		}
		if (!flag) {
			alert("请最少选择一个！");
			return false;
		} else {
			window.location = "/BuyMore/servlet/AdImagDeleteAction?ad_id="+ad_id;
		}
	}
</script>
</body>
</html>