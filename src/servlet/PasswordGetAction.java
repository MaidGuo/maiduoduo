package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.UserInfo;

/**
 * 处理密码及密保问题查询请求
 * @author Administrator
 *
 */
public class PasswordGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfo user;
		UserDao suinfo=new UserDao();
		String strid=request.getParameter("id"); //获得用户id
		int id=MyTools.strToint(strid);
		user=suinfo.selectPassword(id); //根据用户id查询用户密码，密保问题
//		String count=request.getParameter("count");
		System.out.println("PasswordGetAction:输出user对象:id:" +user.getId()+"密码："+user.getAnswer());
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/passwordSet.jsp?id="+id);
	}
}
