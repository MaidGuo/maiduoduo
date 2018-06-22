<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<% String path=request.getContextPath(); %>
<link rel="shortcut icon" type="image/x-icon" href="img/little_logo.ico"/>
<link href="<%=path %>/shopping-back/css/back_top.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/m_goods.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/goodsDetail.css" />
<script type="text/javascript" src="<--%=path %>/shopping-back/js/goodsDetail.js"></script>

</head>

<body>
<div style=" background-color:#e2e2e2; height:35; background:back_img/nav04.gif;"   class="ddfont">
   <span class="l-tab-links">
      <ul style="left:0;"> 
       <li class="l-select">
       <a href="#" style="padding:0 25px;">首页</a>
      </li>
      <li class="l-select">
       <a href="#">商品详情</a>
       <span class="close"></span>
      </li>
     </ul>
    </span>
 </div>
 <%
 ArrayList<FirstClassifyInfo> allFirstClassifyList = 
 (ArrayList<FirstClassifyInfo>) session.getAttribute("allFirstClassify");//一级类别
 ArrayList<SecondClassifyInfo> allSecondClassifyList = 
	(ArrayList<SecondClassifyInfo>) session.getAttribute("allSecondClassify");//2
 ArrayList<ThridClassifyInfo> allThreeClassifyList = 
 (ArrayList<ThridClassifyInfo>) session.getAttribute("allThreeClassify");//3
	
 %>
 <form name="goodsDetailsFrm"  action="<%=path%>/servlet/GoodsInsertAction" method="post" style="margin-left:10px">
 <table id="subtree1"  width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr  height="40" ><input name="judge" type="hidden" value="1"></tr>
         <tr class="inputNone">
           <td height="40" class="font42">
        <table  width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="table_center">
       <tr height="40" >
        <td width="8%" align="center" bgcolor="#EEEEEE">商品名称</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gname"/></td>
        <td width="7%" align="center" bgcolor="#EEEEEE">商品价格</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gprice"/></td>
        <td width="5%" align="center" bgcolor="#EEEEEE">库存量</td>
        <td bgcolor="#FFFFFF"><input type="text" name="gnum"/></td>
       </tr>
       <tr>
        <td width="5%" align="center" bgcolor="#EEEEEE">购买次数</td>
        <td bgcolor="#FFFFFF"><input type="text" name="purchase_times" readonly="readonly" unselectable="on" value="0"/></td>
        <td width="5%" align="center" bgcolor="#EEEEEE">评价次数</td>
        <td bgcolor="#FFFFFF"><input type="text" name="comment_times" readonly="readonly" unselectable="on" value="0"/></td>
        <td width="6%" align="center" bgcolor="#EEEEEE">状态</td>
        <td bgcolor="#FFFFFF">
        <label><input type="radio" name="status"  value="1" checked/>存在</label>
        <label><input type="radio" name="status" value="0" />下架</label>
        </td>
       </tr>
       
     
     <tr height="150">
     </td>
        <td width="10%" align="center" bgcolor="#EEEEEE">三级类别</td>
        <td bgcolor="#FFFFFF"><SELECT name="gtC">
        <%
        for(int i=0;i<allThreeClassifyList.size();i++){
        	String tname=allThreeClassifyList.get(i).getT_Classify_name();
        	int tid=allThreeClassifyList.get(i).getT_Classify_id();
           %>
        	<option value="<%=tid%>"><%=tname %></option>
        	<%
        }
        %>
      </SELECT></td>
      <td align="center" bgcolor="#EEEEEE">商品图片</td>
      <td bgcolor="#FFFFFF">
      <input type="file" name="imgfile" value=""/></td>
       <td align="center"  bgcolor="#EEEEEE">商品描述</td>
       <td  bgcolor="#FFFFFF"><input type="text" name="gtextarea" value="aaa">
       <!-- <textarea id="gtextarea" cols="44" rows="10"></textarea> --></td>
      </tr> 
    </table></td>
         </tr>
         <tr><td align="center" height=50px>
         <input type="button" style="float:left; margin-left:20px" class="xz" value="返回" onclick="returnLast();"/>
         <input type="submit" style="float:right; margin-right:20px" class="xz" value="提交" onclick="return check();"/>
         </td></tr>
       </table> <!--表内容-->
  </form>
 
</body>
</html>