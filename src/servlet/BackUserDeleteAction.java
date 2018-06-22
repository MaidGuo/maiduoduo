package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.*;
import domain.UserInfo;

/**
 * 处理后台删除用户信息
 * @author Administrator
 *
 */
public class BackUserDeleteAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid=MyTools.strToint(request.getParameter("userid"));
		System.out.println("输出要删除的用户id:"+userid);
		AdminDao suinfo=new AdminDao();
		int count=suinfo.deleteUser(userid);
		if(count==1) { //修改成功
			response.sendRedirect(request.getContextPath()+"/servlet/BackUserInfoGetAction?pageNum=1&flag=1");
		}else {  //修改失败
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/userManager_main.jsp?delete="+count);
		}
	}
}
