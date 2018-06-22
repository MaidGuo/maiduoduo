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
 * 处理用户信息查询请求
 * @author Administrator
 *
 */
public class UserInfoGetAction extends HttpServlet{
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
		//System.out.println("id="+id);
		user=suinfo.selectUser(id); //根据用户id查询用户信息
		String count=request.getParameter("count");
		request.getSession().setAttribute("count", count);
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/personCenter.jsp");
	}
}
