<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,common.*,domain.*" %>
<% 
	String path=request.getContextPath();
	AdminInfo admin=(AdminInfo)session.getAttribute("admin");
	int flag=MyTools.strToint(request.getParameter("flag"));
	PageBean pb=(PageBean)session.getAttribute("pagePB");
	List<AdminInfo> adminlist=pb.getList();
	int length=adminlist.size();
	String update=request.getParameter("update");
	System.out.println("后台管理员管理页面：update="+update);
	int f1;//f1:是否成功修改管理员信息
	if(update==null){ //未进行地址新增操作
		f1=0;
	}else{
		if(!update.equals("1")){  //新增地址失败
			f1=1;
		}else{
			f1=2; //新增地址成功
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>管理员管理</title>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/style.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/index.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/modalDB.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-back/js/sweetalert.min.js"></script> 


</head>
<body onload="load()">
	 <div class="con-header" style="align:center">
         <form name="form" method="post" action="<%=path %>/servlet/BackAdminInfoGetAction?flag=3&pageNum=1&adminid=<%=admin.getId() %>">
         <ul class="ui-inline">
          <li>
           <input type="text" name="selectcond" class="ui-kh" placeholder="请输入编号/用户名/电话查询">
          </li>
          <li>
           <input type="submit" class="ui-btn11 ui-btn-search" value="查询" onclick="return true;">
          </li>
         </ul>
        </form>
        </div>
	
    <div class="yg-gl">
         
         <div class="yg-tab">
          <div class="grid">
          <div class="layoutitem" style="width:100%;border:none;">
            <table class="gridbar" border="0" cellpadding="0" cellspacing="0">
            <thead>
             <tr>
              <th scope="col">编号</th>
              <th scope="col">管理员ID</th>
              <th>管理员用户名</th>
              <th>管理员密码</th>
              <th>联系电话</th>
              <th>邮箱</th>
              <th>操作</th>
             </tr>
             <%
             	int i=0;
             	for(i=0;i<length;i++){
             		%>
             		 <tr>
              <td><%=i+1 %></td>
              <td><%=adminlist.get(i).getId() %></td>
              <td><%=adminlist.get(i).getAdmin_name() %></td>
              <td><%=adminlist.get(i).getAdmin_pwd() %></td>
              <td><%=adminlist.get(i).getAdmin_tel() %></td>
              <td><%=adminlist.get(i).getAdmin_email() %></td>
              <%
              	if(adminlist.get(i).getId()==admin.getId()){
              		%>
              		<td><a href="#" style="color:blue" onclick="showMessage()">修改</a></td>  </tr>
              		<% 
              	}
             }
             %>
             </thead>
            </table>
            <form name="form1" id="form1" method="post" action="<%=path %>/servlet/BackAdminUpdateAction?adminid=<%=admin.getId() %>">
        				<div id="modal" class="modal">  
	  				 <div class="modal-content">  
	      			 <header class="modal-header">  
	        		   <h4>修改管理员信息</h4>
	          		 <span class="close">×</span>  
	      			 </header> 
	      				    <div class="modal-body">  
	          		 <p>管理员ID：<input type="text" id="adminid" size="6" name="adminid" value="<%=admin.getId() %>"   readonly="true">  </p> 
	          		 <p> 管理员用户名：<input type="text" id="username" size="10" name="username" value="<%=admin.getAdmin_name() %>">
	          		 <label style="color:#FF9224;margin-left:10pt" id="tips1"></label>
	          		  </p>
	          		  <p>管理员密码：<input type="text" id="passwd" name="passwd" value="<%=admin.getAdmin_pwd() %>"> 
	          		  <label style="color:#FF9224;margin-left:10pt" id="tips2"></label>
	          		   </p> 
	          		  <p>联系电话：<input type="text" id="phone" name="phone" value="<%=admin.getAdmin_tel() %>">
	          		  <label style="color:#FF9224;margin-left:10pt" id="tip4"></label>
	          		  </p>
	          		   <p>邮箱：<input type="text" id="mail" name="mail" value="<%=admin.getAdmin_email() %>">
	          		   <label style="color:#FF9224;margin-left:10pt" id="tips3"></label>
	          		   </p>  
	       			</div>  
	    			   <footer class="modal-footer">  
	        			   <button id="cancel" type="button">取消</button>  
	         		     <button id="sure" onclick="postdata();" type="button">确定</button>  
	        	<!--	 <input type="submit" value="确定" onclick="return true;">   -->
	       				</footer>  
	  			 </div>  
				</div>  
            </form>
           </div>
          <div class="pagin">
    	   <div class="message">共<i class="blue"><%=pb.getTotalRecord() %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pb.getPageNum() %>&nbsp;</i>页</div>
             <ul class="paginList">
            <%	
            	int j;
            	if((pb.getStart()<=1)&&(pb.getEnd()>=pb.getTotalPage())){ //若开始为第一页，就没有“上一页”超链接显示
            		for(j=1;j<=pb.getTotalPage();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><%=j %></a></li>
            			<% 
            		}
            	%>
            	   <% 
            	}else if(pb.getStart()<=1){
            		
       					for(j=1;j<=pb.getEnd();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><%=j %></a></li>
            			<% 
            		}
       			%>
       			<li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
       
       			<% 
            		
            	}else if(pb.getEnd()>=pb.getTotalPage()){ //当前页为最后一页，就没有“下一页”超链接显示
            		
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=pb.getStart()-1 %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
                   <% 
                   for(j=pb.getStart();j<=pb.getTotalPage();j++){
                	   %>
                	   <li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><%=j %></a></li>
                	   <% 
                   }
            	}else{ //当前页既不是最后一页，也不是第一页，"上一页"，“下一页”链接都有
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=pb.getStart()-1 %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
           			<% 
           				for(j=pb.getStart();j<=pb.getEnd();j++){
           					%>
           					<li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><%=j %></a></li>
           					<% 
           				}
           			%>
           			<li class="paginItem"><a href="<%=path %>/servlet/BackAdminInfoGetAction?pageNum=<%=pb.getEnd()+1 %>&flag=<%=flag %>&adminid=<%=admin.getId() %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
            		<%
            	}
            %>
              </ul>
          </div>
          </div>
         </div>
        </div>


</body>
</html>
<script language="javascript">
function postdata(){
	document.getElementById('form1').submit();
}

function showMessage(){
	modal.style.display = "block";
} 
var close = document.getElementsByClassName('close')[0]; 
var cancel = document.getElementById('cancel');  
var modal = document.getElementById('modal');   
close.addEventListener('click', function(){  
    modal.style.display = "none";  
});    
cancel.addEventListener('click', function(){  
   modal.style.display = "none";  
});  
function load(){ 
	var f1="<%=f1 %>";
	if(f1==1){
		swal({
		    title:"修改管理员信息失败",    
		    text: "",
		    type: "error",    
		    confirmButtonText: "确定" 
		})		
	} 	
}
</script>