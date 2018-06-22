package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.*;
import domain.*;
import common.*;

/**
 * 处理后台用户信息查询请求
 * @author Administrator
 *
 */
public class BackUserInfoGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//flag=1:userlist保存数据库所有的用户信息     flag=2:userlist保存数据库中满足条件的用户信息
		int flag=MyTools.strToint(request.getParameter("flag"));
		List<UserInfo> userlist;
		if(flag==1) {  //从数据库中获取所有的用户信息
			AdminDao suinfo=new AdminDao();
			userlist=suinfo.selectUser();
		}else if(flag==3){  //从数据库中获取所有满足条件的用户信息
			userlist=selectUser(request,response);
			flag=2;
		}else {
			userlist=(List<UserInfo>)request.getSession().getAttribute("userlist");
		}
		//获取当前是第几页
		int pageNum=MyTools.strToint(request.getParameter("pageNum"));
		System.out.println("BackUserInfoGetAction:输出当前页数：pageNum="+pageNum);
		//每页显示的记录数
		int pageSize=5;
		//调用service层方法，进行处理
		WithPage wp=new WithPage();
		//获取一个PageBean对象，pb中包含了该页所需要的内容
		PageBean pb=wp.findAllUser(pageNum, pageSize,userlist);
		//将pb放入request域中
		request.getSession().setAttribute("pagePB",pb);
		System.out.println("输出总页数："+pb.getTotalPage()+"输出末尾页数："+pb.getEnd());
		response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/userManager_main.jsp?flag="+flag);
	}
	
	public List<UserInfo> selectUser(HttpServletRequest request, HttpServletResponse response){
		List<UserInfo> userlist;
		int status=MyTools.strToint(request.getParameter("ischecked"));
		String condition=MyTools.toChinese(request.getParameter("selectcond"));
		AdminDao suinfo=new AdminDao();
		
		if(condition!=null&&!condition.equals("")) {
			//先判断查询条件是否全为数字
			  Pattern pat1 = Pattern.compile("[0-9]*"); 
			  Matcher m=pat1.matcher(condition);
			  if(m.matches()) { //如果全为数字，则需判断该串数字是否为电话号码，若不是，则为用户id
				  String reg="^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
				  pat1 = Pattern.compile(reg); 
				  m=pat1.matcher(condition);
				  if(m.matches()) { //如果查询条件为电话号码
					  if(status==1) {
						  userlist=suinfo.selectUserByTel(condition,0);
					  }else {
						  userlist=suinfo.selectUserByTel(condition);
					  }
					 
				  }else {
					  int id=MyTools.strToint(condition);
					  if(status==1) {
						  userlist=suinfo.selectUserById(id,0);
					  }else {
						  userlist=suinfo.selectUserById(id);
					  }
					 
				  }
			  }else {
				  if(status==1) {
					  userlist=suinfo.selectUserByName(condition,0);
				  }else {
					  userlist=suinfo.selectUserByName(condition);
				  }
				 
			  }

		}else if(status==1) { //查询条件只为用户激活状态
			userlist=suinfo.selectUserByStatus(0);
		}else {  //没有任何查询条件
			userlist=suinfo.selectUser();
		}
		request.getSession().setAttribute("userlist", userlist);
		return userlist;
	}
}
