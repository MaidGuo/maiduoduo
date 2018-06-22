<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,common.*,domain.*" %>
<% 
	String path=request.getContextPath();
	int flag=MyTools.strToint(request.getParameter("flag"));
	PageBean pb=(PageBean)session.getAttribute("pagePB");
	List<UserInfo> userlist=pb.getList();
	int length=userlist.size();
	String update=request.getParameter("update");
	String delete=request.getParameter("delete");
	System.out.println("后台用户管理页面：update="+update+"delete="+delete);
	int f1,f2;//f1:是否成功修改用户信息  f2:是否成功删除用户信息
	if(update==null){ //未进行地址新增操作
		f1=0;
	}else{
		if(!update.equals("1")){  //新增地址失败
			f1=1;
		}else{
			f1=2; //新增地址成功
		}
	}
	if(delete==null){
		f2=0;
	}else{
		if(!delete.equals("1")){
			f2=1;
		}else{
			f2=2;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>用户管理</title>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/style.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/index.css"/>
<link  type="text/css" rel="stylesheet"  href="<%=path %>/shopping-back/css/modalDB.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-web/css/shopping.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/shopping-back/css/sweetalert.css"> 
<script type="text/javascript" src="<%=path %>/shopping-back/js/sweetalert.min.js"></script> 

</head>
<body onload="load()">
	 <div class="con-header" style="align:center">
	 <form name="form" method="post" action="<%=path %>/servlet/BackUserInfoGetAction?flag=3&pageNum=1">
         <ul class="ui-inline">
          <li>
           <input type="text" name="selectcond" class="ui-kh" placeholder="请输入用户编号/用户昵称/电话查询">
          </li>
          <li class="chk-list" style="display:list-item;">
           <span class="chk over">
            <input type="checkbox" value=1 name="ischecked" class="chk-in" checked>显示未激活用户
           </span>
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
              <th scope="col">用户ID</th>
              <th>用户名</th>
              <th>用户密码</th>
              <th>用户性别</th>
              <th>用户真实姓名</th>
              <th>联系电话</th>
              <th>用户邮箱</th>
              <th>是否激活</th>
              <th>操作</th>
             </tr>
             <%
             	int i=0;
             	for(i=0;i<length;i++){
             		%>
             		 <tr>
              <td><%=i+1 %></td>
              <td><%=userlist.get(i).getId() %></td>
              <td><%=userlist.get(i).getName() %></td>
              <td><%=userlist.get(i).getPwd() %></td>
              <td><%=userlist.get(i).getGender()==0?"男":"女" %></td>
              <td><%=userlist.get(i).getTrueName() %></td>
              <td><%=userlist.get(i).getTel() %></td>
              <td><%=userlist.get(i).getEmail() %></td>
              <td><%=userlist.get(i).getStatus()==0?"未激活":"已激活" %></td>
              <td><a href="#" id="update" style="color:blue" onclick="showMessage('<%=userlist.get(i).getId() %>','<%=userlist.get(i).getName() %>','<%=userlist.get(i).getPwd() %>')">修改</a>/<a href="#" id="delete" onclick="showTips('<%=userlist.get(i).getId() %>')" style="color:blue">删除</a></td>
             </tr>
             		<% 
             	}
             %>
             </thead>
            </table>
            	 <form name="form1" id="form1" method="post" action="<%=path %>/servlet/BackUserUpdateAction?">
				<div id="modal" class="modal">  
	  				 <div class="modal-content">  
	      			 <header class="modal-header">  
	        		   <h4>修改用户信息</h4>
	          		 <span class="close">×</span>  
	      			 </header> 
	   			    <div class="modal-body">  
	          		 <p>用户ID：<input type="text" id="userid" size="6" name="userid" value=""   readonly="true">   
	          		 	 用户名：<input type="text" id="username" size="10" name="username" value=""  readonly="true">
	          		 	   <input id="passwd" name="passwd" type="hidden" value=""/>
					 
					  </p>
	          		  <p>
	          		   <input type="checkbox" value=1 name="reset" class="chk-in" checked>重置该用户密码为000000
	          		  </p>  
	          		  <p>
	          			  修改用户激活状态：
						<input name="status" type="radio" value="0" checked>未激活
						<input name="status" type="radio" value="1" >已激活
						</p>  
	       			</div>  
	    			   <footer class="modal-footer">  
	        			   <button id="cancel" type="button">取消</button>  
	        		     <button id="sure" onclick = "postdata();" type="button">确定</button>  
	        <!-- 		 <input type="submit" value="确定" onclick="return true;"> -->
	       				</footer>  
	  			 </div>  
				</div>  
				</form>
				   <div id="modal1" class="modal">  
	  				 <div class="modal-content">  
	      			 <header class="modal-header">  
	        		   <h4>删除用户信息</h4>
	          		 <span class="close">×</span>  
	      			 </header> 
	   			    <div class="modal-body">  
	   			      <input id="info" name="info" type="hidden" value=""/>
	          		  <p>确定删除该用户吗？</p>  
	       			</div>  
	    			   <footer class="modal-footer">  
	        			   <button id="cancel1" type="button">取消</button>  
	        		    <button id="sure1" type="button">确定</button>
	       				</footer>  
	  			 </div>  
				</div>  
           </div>
          <div class="pagin">
    	   <div class="message">共<i class="blue"><%=pb.getTotalRecord() %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pb.getPageNum() %>&nbsp;</i>页</div>
            <ul class="paginList">
            <%	
            	int j;
            	if((pb.getStart()<=1)&&(pb.getEnd()>=pb.getTotalPage())){ //若开始为第一页，就没有“上一页”超链接显示
            		for(j=1;j<=pb.getTotalPage();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>"><%=j %></a></li>
            			<% 
            		}
            	%>
            	   <% 
            	}else if(pb.getStart()<=1){
            		
       					for(j=1;j<=pb.getEnd();j++){
            			%>
            			 <li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>"><%=j %></a></li>
            			<% 
            		}
       			%>
       			<li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
       
       			<% 
            		
            	}else if(pb.getEnd()>=pb.getTotalPage()){ //当前页为最后一页，就没有“下一页”超链接显示
            		
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=pb.getStart()-1 %>&flag=<%=flag %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
                   <% 
                   for(j=pb.getStart();j<=pb.getTotalPage();j++){
                	   %>
                	   <li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>"><%=j %></a></li>
                	   <% 
                   }
            	}else{ //当前页既不是最后一页，也不是第一页，"上一页"，“下一页”链接都有
            		%>
            		 <li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=pb.getStart()-1 %>&flag=<%=flag %>"><span class="pagepre"><img src="<%=path %>/shopping-back/img/img1/jt1.png"></span></a></li>
           			<% 
           				for(j=pb.getStart();j<=pb.getEnd();j++){
           					%>
           					<li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=j %>&flag=<%=flag %>"><%=j %></a></li>
           					<% 
           				}
           			%>
           			<li class="paginItem"><a href="<%=path %>/servlet/BackUserInfoGetAction?pageNum=<%=pb.getEnd()+1 %>&flag=<%=flag %>"><span class="pagenxt"><img src="<%=path %>/shopping-back/img/img1/jt.png"></span></a></li>
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
function showMessage(id,name,pwd){
	document.getElementById("userid").value=id;
	document.getElementById("username").value=name;
	document.getElementById("passwd").value=pwd;
	modal.style.display = "block";
}
function showTips(id){
	modal1.style.display = "block";
	document.getElementById("info").value=id;
}
function postdata(){
	 document.getElementById("form1").submit();
	 modal.style.display = "none";  
}

      var close = document.getElementsByClassName('close')[0]; 
      var close1 = document.getElementsByClassName('close')[1]; 
      var cancel = document.getElementById('cancel');  
      var modal = document.getElementById('modal');  
      var cancel1 = document.getElementById('cancel1');  
      var modal1 = document.getElementById('modal1');  
      var sure1 = document.getElementById('sure1');  
     sure1.addEventListener('click', function(){  
        	var userid=document.getElementById("info").value;
        	window.location.href="<%=path %>/servlet/BackUserDeleteAction?userid="+userid; 
      });  
      close.addEventListener('click', function(){  
          modal.style.display = "none";  
      });  
      close1.addEventListener('click', function(){  
          modal1.style.display = "none";  
      });  
      cancel.addEventListener('click', function(){  
         modal.style.display = "none";  
      });  
      cancel1.addEventListener('click', function(){  
          modal1.style.display = "none";  
       }); 
      function load(){ 
    		var f1="<%=f1 %>";
    		var f2="<%=f2 %>";
    		if(f1==1){
    			swal({
    			    title:"修改用户信息失败",    
    			    text: "",
    			    type: "error",    
    			    confirmButtonText: "确定" 
    			})		
    		} 	
    		if(f2==1){
    			swal({
    			    title:"删除用户失败",    
    			    text: "该用户信息和数据库其他表有关联，删除该用户会损坏其他表",
    			    type: "error",    
    			    confirmButtonText: "确定" 
    			})	
    		}
    	}
</script>  