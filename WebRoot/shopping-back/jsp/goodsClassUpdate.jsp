<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% String path=request.getContextPath(); %>
<link rel="shortcut icon" type="image/x-icon" href="img/little_logo.ico"/>
<link href="<%=path %>/shopping-back/css/back_top.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/public.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/m_goods.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/goodsDetail.css"/>
</head>

<body>

<%
  GoodsInfo goods = (GoodsInfo)session.getAttribute("oneGoodsInfo");
  int gId=goods.getGoods_id();
  	  String gName=goods.getGoods_name();
  	  double gPrice=goods.getGoods_price();
  	  int gNum=goods.getGoods_num();//库存量
  	  int gPurchase=goods.getPurchase_times();//购买次数
  	  int gComment=goods.getComment_times();//评价次数
  	  int gStatus=goods.getStatus();//状态
  	  String gdesc=goods.getGoods_description();//描述
  	  String imgPath=goods.getImg_path();//图片路径
  	  String fNClassify=goods.getF_Classify_name();//1级类别名称
  	  String sNClassify=goods.getS_Classify_name();//2级类别名称
  	  String tNClassify=goods.getT_Classify_name();//3级类别名称
%>

<div style=" background-color:#e2e2e2; height:35; background:back_img/nav04.gif;"   class="ddfont">
   <span class="l-tab-links">
      <ul style="left:0;"> 
       <li class="l-select">
       <a href="#" style="padding:0 25px;">首页</a>
      </li>
      <li class="l-select">
       <a href="#">修改产品信息</a>
       <span class="close"></span>
      </li>
     </ul>
    </span>
 </div>
 
 <form name="goodsDetailsFrm"  action="<%=path %>/servlet/GoodsClassUpdateAction" method="post" style="margin-left:10px">
 <input type="hidden" name="gid" value="<%=gId %>">
 <table id="subtree1"  width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr  height="40" ><td><input name="judge" type="hidden" value="2">
        <input name="goodsID" type="hidden" value="<%=gId%>"></td></tr>
         <tr class="inputNone">
           <td height="40" class="font42">
           <table  width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="table_center">
    <tr height="150">
      <td align="center" bgcolor="#EEEEEE">商品图片</td>
      <td bgcolor="#FFFFFF">
      <img src="<%=path+imgPath%>" height="100"/></td>
    <!--  </tr> 
       <tr>
        <td width="10%" align="center" bgcolor="#EEEEEE">一级类别</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gfC" value="<%=fNClassify%>"/></td>
        <td width="10%" align="center" bgcolor="#EEEEEE" >二级类别</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gsC" value="<%=sNClassify%>"/></td>
         --> <td width="10%" align="center" bgcolor="#EEEEEE">三级类别</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gtC" value="<%=tNClassify%>"/></td>
      </tr> 
     
     
           </table></td>
         </tr>
         <tr><td align="center" height=50px>
         <input type="button" style="float:left; margin-left:20px" class="xz" value="返回" onclick="window.location.href('<%=path%>/shopping-back/jsp/manage_goodsClass.jsp')"/>
         <input type="submit" style="float:right; margin-right:20px" class="xz" value="提交" />
         </td></tr>
       </table> <!--表内容-->
  </form>
</body>
</html>