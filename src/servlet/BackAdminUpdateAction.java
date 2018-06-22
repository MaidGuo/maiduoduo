package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.AdminDao;
import domain.*;

/**
 * 处理后台管理员信息修改操作
 * @author Administrator
 *
 */
public class BackAdminUpdateAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("admin进入到修改操作");
		int adminid=MyTools.strToint(request.getParameter("adminid"));
		String adminname=MyTools.toChinese(request.getParameter("username"));
		String passwd=request.getParameter("passwd");
		String phone=request.getParameter("phone");
		String mail=request.getParameter("mail");
		System.out.println("BackAdminUpdateAction:输出用户id:"+adminid+",用户名："+adminname+",密码："+passwd+",电话："+phone+",邮箱："+mail);
		AdminInfo admin=new AdminInfo();
		admin.setId(adminid);
		admin.setAdmin_email(mail);
		admin.setAdmin_name(adminname);
		admin.setAdmin_pwd(passwd);
		admin.setAdmin_tel(phone);
		AdminDao suinfo=new AdminDao();
		int count=suinfo.updateAdminInfo(admin);
		if(count==1) { //修改成功
			response.sendRedirect(request.getContextPath()+"/servlet/BackAdminInfoGetAction?pageNum=1&flag=1&adminid="+adminid);
		}else {  //修改失败
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/adminManager_main.jsp?update="+count);
		}
	}
}
