<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String imag = "";
	imag = (String)request.getSession().getAttribute("imag");
	request.getSession().setAttribute("path", imag);
	request.getSession().removeAttribute("imag");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>弹窗-图片添加</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/shopping-back/css/notice.css" />
<style>
html,body,div,span,ul,li,h1,h2,h3,h4,p,a,img,input,form{margin:0;padding:0;border:0;list-style:none;outline:0;}
div,td,ul,li,dl,dt,dd,h2,h3,h4{list-style:none;margin:0;padding:0;}
div{display:block;}
body{font-size:12px;font-family:"微软雅黑", "宋体", Arial;color:#666;}
li{display:list-item;text-align:-webkit-match-praent;}
img{vertical-align:middle;}
em{font-style:normal;color:#fff;}
input, textarea, select, button { font-family: inherit; font-size: 100%; outline:0;}
a img{border:0}
body{color:#333;font:12px "微软雅黑";}
ul, ol, li{list-style-type:none;vertical-align:0}
a{outline-style:none;color:#535353;text-decoration:none;cursor:pointer}
a:hover{text-decoration:none;}
.clear{height:0;overflow:hidden;clear:both}
i,s{text-decoration:none;font-style:normal;}
.sp-wj,.sp-txt{width:94%;height:30px;line-height:30px;margin:8px 5px;border:0px;}
.file-ed,.txt-in{padding:2px 3px;}


.formoverlay{cursor:default;width:1419px;height:949px;z-index:5;opacity:0.5;background:#999;position:absolute;left:0;top:0;width:100%;height:100%;display: block;}
.jsgl{width:440px;border:none;height:auto;}
.lay-list{width:420px;margin:15px auto;border:1px solid #ddd;}
.lay-list ul{padding:5px;}
.lay-list li{height:40px;line-height:40px;margin-bottom:5px;display:block;overflow:hidden;}
.lay-js{float:left;overflow:hidden;display:inline-block;width:50%;}
.iten-js{width:68px;line-height:40px;display:inline-block;text-align:right;}
.jsedit{width:120px;background:#fff;height:24px;line-height:24px;border:1px solid #ddd;text-indent:5px;margin-left:5px;}
.lay-js1{float:left;overflow:hidden;display:inline-block;width:100%;}
.text-js{border:0px;width:80%;height:80px;text-indent:10px;}

.layoutitem-add{float:right;padding-right:15px;}
.layoutitem-add a{width:45px;height:24px;line-height:24px;margin:12px 8px ;display:inline-block;padding:2px 3px;}
.add{background:#ff0303;color:#fff;border-radius:3px;text-align:center;}
.bc{background:#f23d4e;border-radius:3px;text-align:center;color:#fff;border:0px;height:30px;width:50px;}
.tc{background:#333;border-radius:3px;text-align:center;color:#fff;}
</style>
</head>

<body>
<form name="frm" action="<%=path %>/servlet/ShowImagAction" method="post">
<div class="formoverlay"></div>
 <table cellpadding="0" cellspacing="0" border="0" class="formborder" style="z-index:99;position:absolute;left:500px;top:150px;width:440px; display: block;" >
  <tbody> 
   <tr>
   <td align="left" valign="top" style="width:440px;height: auto;background:#fff;overflow:hidden;">
    <div class="formcontentsize" style="width:auto;height:auto;">
     <!--仓库基本信息   开始-->
     <div class="layoutitem jsgl" style="width:280px;">
      <div class="lay-list">
       <ul>
        <li>
         <div class="lay-js">
           <div class="sp-wj">
             <input type="file" class="file-ed" name="file_img">
             <button class="button_4" >上传</button></li>
           </div>
         </div>
        </li>
         <li style="height:auto;">
         <div class="lay-js1">
          <label class="iten-js" > 图片:</label>
         <div class="text-js" colspan="3" style="border-color:#006699;width:240px; height:100px;">
         <%
         	if (imag != "") {
         %>
         	<img src="<%=imag%>" style="width:240px; height:100px;" name="img">
         <%
         	}
         %>
         </div>
         </div>         
        </li>
       </ul>
      </div>
     </div>

     <div class="layoutitem-add">
    <button  class="bc" formmethod="post" formaction="<%=path%>/servlet/AdImagAddAction">保存</button>
      <a href="<%=path%>/servlet/AdImagListAction" class="tc">退出</a>               
     </div>
    </div>
   </td>
   </tr>
  </tbody>
 </table>
 </form>     
</body>
</html>