<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="domain.*,servlet.*,java.util.*,dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<% String path=request.getContextPath(); %>
<link rel="shortcut icon" type="image/x-icon" href="img/little_logo.ico"/>
<link href="<%=path %>/shopping-back/backcss/back_top.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/public.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/m_order.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/css.css" />
<script type="text/javascript" src="<%=path %>/shopping-back/js/backcommon.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-back/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/shopping-back/js/public.js"></script>
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
<form name="fom" id="fom" method="post" action="<%=path %>/servlet/OrderStatusAction" style="margin-left:10px">

<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 <tr>
               <td height="80">
               <input name="Submit" type="button" class="ui-bill" value="全选" onclick="selectAll();"/>
               <input name="Submit" type="button" class="ui-bill" value="反选" onclick="unselectAll();"/>
	           <input name="sendOrder" type="submit" class="ui-bill" value="发 货" />
                  <!--查询-->
                 <span style="float:right ; margin-bottom:2px">
                 <input name="text" type="text" style=" width:220px; height:26px" />
                 <input name="Submit3" type="button" class="xz" value="查 询" /></td><!--class="right-button02"跳转订单详情页面-->
                 </span>
                 </td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
                <table  width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="table_center"><!--改-->
                  
              <!--表内容 -->
              <div >
                  <tr>
				    <td width="4%" align="center" bgcolor="#EEEEEE">选择</td>
                    <td width="12%" height="20" align="center" bgcolor="#EEEEEE">订单编号</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">卖家ID</td>
                    <td width="8%" align="center" bgcolor="#EEEEEE">下单时间</td>
                    <td width="8%" align="center" bgcolor="#EEEEEE">交易完成时间</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">付款方式</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">实付金额</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">订单状态</td>
                    <!--<td width="6%" align="center" bgcolor="#EEEEEE">查看详情</td>  -->
                  </tr>
  <%
  ArrayList<OrderInfo> orderList = (ArrayList<OrderInfo>)session.getAttribute("orderAllList");
  UserDao userDao=new UserDao();//获得用户名
  UserInfo user=new UserInfo();
  if (orderList == null || orderList.size() == 0) { //必须判断，可能取空，报错
  } else {
	   for (int i = 0; i < orderList.size(); i++) {
		   int order_id=orderList.get(i).getOrder_id();
		   String order_no=orderList.get(i).getOrder_no();
		   Date created_time=orderList.get(i).getCreated_time();//生成订单点击确认时 获得下单时间
		   int user_id=orderList.get(i).getUser_id();
		   user=userDao.selectUser(user_id);
		   String userName=user.getName();//用户名
		   
		   double payment=orderList.get(i).getPayment();//金额
		   int payment_type=orderList.get(i).getPayment_type();//支付方式1 在线付款 0 货到付款
		   String payType="";
		   if(payment_type==1){
			   payType="在线付款";
		   }else if(payment_type==0){
			   payType="货到付款";
		   }
		   int send_time=orderList.get(i).getSend_time();//送货时间 
		   int status=orderList.get(i).getStatus();//1 未付款2 已付款 3 未发货 4 已发货 5交易成功
		   String sta="";
		   if(status==1){
			   sta="未付款";
		   }else if(status==2){
			   sta="未发货";
		   }else if(status==3){
			   sta="已发货";
		   }else if(status==4){
			   sta="交易成功";
		   }
		   Date end_time=orderList.get(i).getEnd_time();//交易完成时间
		   int iscomment=orderList.get(i).getIscomment();//0 否 1 是
		   %>
	<tr>
		 <td bgcolor="#FFFFFF">
		 <input name="orderStatus" type="hidden" value="<%=status%>">
		 <input type="checkbox" name="delid" value="<%=order_id%>"/></td>
         <td height="20" bgcolor="#FFFFFF"><%=order_no %></td>
         <td bgcolor="#FFFFFF"><%=userName %></td>
         <td bgcolor="#FFFFFF"><%=created_time %></td>
         <td bgcolor="#FFFFFF"><%=end_time %></td>
         <td bgcolor="#FFFFFF"><%=payType %></td>
         <td bgcolor="#FFFFFF"><%=payment %></td>
         <td bgcolor="#FFFFFF"><%=sta %></td>
         <!--  <td bgcolor="#FFFFFF"><a href="#">查看</a></td>-->
    </tr>
		   <%
	   }
  }
  %> 
                </table></td>
              </tr>
            </table></td>
        </tr>
        </div><!--表内容-->
      </table>
       <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <!--  <tr>
          <td height="6"><img src="back_img/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
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
                </table>-->
                
                
                </td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
  </table>
  </form>

  <div id="loadingmsg" style="width:100px; height:18px; top:0px; display:none;">
	
  </div>
  </div>
 <!--订单结束-->
</body>
</html>