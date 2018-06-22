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
 * 处理密码修改请求
 * @author Administrator
 *
 */
public class PasswordUpdateAction extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfo user=new UserInfo();
		UserDao suinfo=new UserDao();
		int id=(Integer)request.getSession().getAttribute("id"); //获得用户id
		request.getSession().removeAttribute("id");
		user.setId(id);
		String pwd=request.getParameter("newpwd");
		user.setPwd(pwd);
		int count=suinfo.UpdatePassword(user);
		if(count==1) {
			request.getSession().setAttribute("isSuccess", "1");
		}
		response.sendRedirect(request.getContextPath()+"/servlet/PasswordGetAction?id="+id);
	}
}
