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
import domain.UserInfo;

/**
 * 处理后台用户信息修改操作
 * @author Administrator
 *
 */
public class BackUserUpdateAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid=MyTools.strToint(request.getParameter("userid"));
		int status=MyTools.strToint(request.getParameter("status"));
		int reset=MyTools.strToint(request.getParameter("reset"));
		String pwd;
		if(reset==1) { //重置密码
			pwd="000000";
		}else {
			pwd=request.getParameter("passwd");
		}
		UserInfo user=new UserInfo();
		user.setId(userid);
		user.setStatus(status);
		user.setPwd(pwd);
		AdminDao suinfo=new AdminDao();
		int count=suinfo.backUpdateUserInfo(user);
		if(count==1) { //修改成功
			response.sendRedirect(request.getContextPath()+"/servlet/BackUserInfoGetAction?pageNum=1&flag=1");
		}else {  //修改失败
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/userManager_main.jsp?update="+count);
		}
	}
}
