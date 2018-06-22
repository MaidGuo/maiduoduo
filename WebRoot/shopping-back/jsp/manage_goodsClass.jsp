<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品类别管理</title>
<% String path=request.getContextPath(); %>
<link rel="shortcut icon" type="image/x-icon" href="img/little_logo.ico"/>
<link href="<%=path %>/shopping-back/css/back_top.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/m_goods.css" />
<script type="text/javascript" src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-back/js/public.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-back/js/backcommon.js"></script>
</head>

<body onload="on_load()" >
 <!--商品类别管理开始-->
 <div style=" background-color:#e2e2e2; height:35; background:back_img/nav04.gif;"   class="ddfont">
   <span class="l-tab-links">
      <ul style="left:0;"> 
       <li class="l-select">
       <a href="#" style="padding:0 25px;">首页</a>
      </li>
      <li class="l-select">
       <a href="#">商品类别管理</a>
       <span class="close"></span>
      </li>
     </ul>
    </span>
  
 </div>
<form name="fom" id="fom" method="post" action="" style="margin-left:10px">
<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td><table id="subtree1"  width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 <tr>
             <td height="80">
               <input name="Submit" type="button" class="ui-bill" value="全选" onclick="selectAll();"/>
               <input name="Submit" type="button" class="ui-bill" value="反选" onclick="unselectAll();"/>
	            <!--  <input name="Submit" type="button" class="ui-bill" value="添加" onclick="addOneGoods();"/> -->
               
                  <!--查询-->
                 <span style="float:right ; margin-bottom:2px">
                 <input name="text" type="text" style=" width:220px; height:26px" />
                 <input name="Submit3" type="button" class="xz" value="查 询" /></td><!--跳转订单详情页面-->
                 </span>
                 </td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
                <table  width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="table_center">
                  <tr>
				    <td width="3%" align="center" bgcolor="#EEEEEE">选择 </td>
                    <td width="10%" height="20" align="center" bgcolor="#EEEEEE">商品图片</td>
                    <td width="8%" align="center" bgcolor="#EEEEEE">商品名称</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">一级类别</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">二级类别</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">三级类别</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">查看及修改详细信息</td>
                  </tr>
             
         <%
			ArrayList<ManageGoodsInfo> allmGoodsList = (ArrayList<ManageGoodsInfo>) session.getAttribute("backAllmGoodsList");
			if (allmGoodsList == null || allmGoodsList.size() == 0) { //必须判断，可能取空，报错
			} else {
				for (int i = 0; i < allmGoodsList.size(); i++) {
				int id = allmGoodsList.get(i).getmId();
				String goodsName=allmGoodsList.get(i).getmName();
				String goodsImgPath=allmGoodsList.get(i).getmPicPath();//图片路径
				String fName=allmGoodsList.get(i).getmFCname();//1级类别
				String sName=allmGoodsList.get(i).getmSCname();//2
				String tName=allmGoodsList.get(i).getmTCname();//3
				
				%> 
				 <tr>
				    <td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>
                    <td bgcolor="#FFFFFF"><img src="<%=path+goodsImgPath %>" height="60"/></td>
                    <td bgcolor="#FFFFFF"><%=goodsName %></td>
                    <td bgcolor="#FFFFFF"><%=fName %></td>
                    <td bgcolor="#FFFFFF"><%=sName %></td>
                    <td bgcolor="#FFFFFF"><%=tName %></td>
                    <td bgcolor="#FFFFFF"><a href="<%=path %>/servlet/GoodsModifyAction?goodsId=<%=id%>&goodsOrClass=2">查看</a></td>
                  </tr>
				
				<%}
			}
		%>
                </table></td>
              </tr>
            </table></td>
        </tr>
        <!--表内容-->
      </table>
    <!--   <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="<%=path %>/shopping-back/img/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33">
          
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
              <div class="pagin">
    	   <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
            <ul class="paginList">
             <li class="paginItem"><a href="#"><span class="pagepre"><img src="back_img/jt1.png"></span></a></li>
             <li class="paginItem"><a href="#">1</a></li>
             <li class="paginItem current"><a href="#">2</a></li>
             <li class="paginItem"><a href="#">3</a></li>
             <li class="paginItem"><a href="#">4</a></li>
             <li class="paginItem"><a href="#">5</a></li>
             <li class="paginItem more"><a href="#">...</a></li>
             <li class="paginItem"><a href="#">10</a></li>
             <li class="paginItem"><a href="#"><span class="pagenxt"><img src="back_img/jt.png"></span></a></li>
           </ul>
          </div>
          </tr>
                </table>
                </td>
              </tr>
          </table>-->
          
          </td>
        </tr>
      </table>
  </form>

  <div id="loadingmsg" style="width:100px; height:18px; top:0px; display:none;">
	<img src="back_img/loadon.gif" />
  </div>
 <!--商品类别结束-->
 
</body>
</html>