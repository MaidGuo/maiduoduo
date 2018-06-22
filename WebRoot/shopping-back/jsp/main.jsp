<%@ page language="java" import="java.util.*,domain.*,common.*" pageEncoding="utf-8"%>
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
	<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/style.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/index.css"/>
  </head>
  <%
  		AdminInfo admin=(AdminInfo)session.getAttribute("admin");
  			ArrayList<Date> loginTime=null;
  		if(admin==null){ //如果session中为空
  			admin=new AdminInfo();
  			loginTime=new ArrayList<Date>();
  		
  		}else{
  		
  		loginTime=admin.getLoginTime();
  			
  				
  		}
   %>
  <body>
<div class="l-tab-content" >
  
      
      <!--成交金额-->
        <div class="space-style">
        <h1><%=admin.getAdmin_name() %> 欢迎进入买多多购物商城后台管理 </h1>
     <!--   <div class="col-xs">
         <a  href="#" class="title-button bg-deep">
          <div class="carousel">
           <div class="left-img">
            <i><img src="<%=path %>/shopping-back/img/left-img1.png"></i>
            <p>成交金额</p>
            <p>&nbsp;</p>
           </div>
           <div class="right-info">4532226.25元</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="#" class="title-button bg-red">
          <div class="carousel">
           <div class="left-img bg-color-red">
            <i><img src="<%=path %>/shopping-back/img/left-img2.png"></i>
            <p>订单</p>
            
           </div>
           <div class="right-info">4589笔</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="#" class="title-button bg-green">
          <div class="carousel">
           <div class="left-img bg-color-green">
            <i><img src="<%=path %>/shopping-back/img/left-img3.png"></i>
            <p>通知</p>
           </div>
           <div class="right-info">125条</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="#" class="title-button bg-orange">
          <div class="carousel">
           <div class="left-img bg-color-orange">
            <i><img src="<%=path %>/shopping-back/img/left-img4.png"></i>
            <p>待处理</p>
           </div>
           <div class="right-info">10条</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="#" class="title-button bg-purple">
          <div class="carousel">
           <div class="left-img bg-color-purple">
            <i><img src="<%=path %>/shopping-back/img/left-img5.png"></i>
            <p>留言</p>
           </div>
           <div class="right-info">48条</div>
          </div>
         </a>
        </div>
        
        -->
       </div>
		
            <!--中部-->
       <div class="order-form">
       <!-- 登录情况 -->
        <div class="col-xs-3 ">
         <div class="admin-info">
          <div class="title-name">
           <i></i>
            登录记录
           <a href="#">+更多</a>
          </div>
          <table class="record-list">
           <%
           int cnt=0;
         	
          	for(int i=loginTime.size()-1;i >=0 ;i--){
          		Date d=loginTime.get(i);
          		String date=MyTools.transDateToString(d);
            %>
            <tr>
             <td><%=admin.getAdmin_name() %></td>
             <td><%=date%></td>
            </tr>
            <%if(cnt>=9) break;
            cnt++;
            } %> 
          </table>
         </div>
        </div>
        <!--销售排行  -->
        
        <div class="col-xs-6 ranking-style">
         <div class="frame">
          <div class="title-name">
            <i></i>
            商品销售排行
           <a href="#">+更多</a>
          </div>
          <table class="table table-list">
           <thead>
            <tr>
             <th width="50">排名</th>
             <th>商品编号</th>
             <th>商品名称</th>
             <th width="80">销售数量</th>
            </tr>
           </thead>
           <tbody>
           <%      
	ArrayList<GoodsInfo>gl=(ArrayList<GoodsInfo>)session.getAttribute("gl");
	if(gl==null){
	gl=new ArrayList<GoodsInfo>();
	}
	for(int i=0;i<gl.size();i++){
		GoodsInfo goods=gl.get(i);
            %>
            <tr>
             <td>
              <em><%=i+1 %></em>
             </td>
             <td><%=goods.getGoods_id() %></td>
             <td><a href="#"><%=goods.getGoods_name() %></a></td>
             <td><%=goods.getPurchase_times() %></td>
            </tr>
            <%} %>
             <tr>
          <!--   <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
            
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
            
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
            
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>
            </tr>
             <tr>
             <td>
              <em>1</em>
             </td>
             <td>2355456</td>
             <td><a href="#">景观灯系列</a></td>
             <td>26</td>  -->
            </tr>
           </tbody>
          </table>
         </div>
		   </div>
		  </div>

	  
	  </div>
	 <div class="foot">
	 	copyright@2018
	 </div> 
	 
</body>
</html>
