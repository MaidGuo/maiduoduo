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
 * 处理用户信息更改请求
 * @author Administrator
 *
 */
public class UserInfoUpdate extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfo user=new UserInfo();
		UserDao suinfo=new UserDao();
		int id=(Integer) request.getSession().getAttribute("id"); //获得用户id
		request.getSession().removeAttribute("id");
		user.setId(id);
		String username=request.getParameter("nickname");
		user.setName(MyTools.toChinese(username));
		String truename=request.getParameter("realname");
		user.setTrueName(MyTools.toChinese(truename));
		String gender=request.getParameter("sex");
		int sex=MyTools.strToint(gender); //用户性别
		user.setGender(sex);
		String province=request.getParameter("province");
		user.setProvince(MyTools.toChinese(province));
		String city=request.getParameter("city");
		user.setCity(MyTools.toChinese(city));
		String county=request.getParameter("county");
		user.setCountry(MyTools.toChinese(county));
		String address=request.getParameter("address");
		user.setAddress(MyTools.toChinese(address));
		String mail=request.getParameter("mail");
		user.setEmail(mail);
		String tel=request.getParameter("phone");
		user.setTel(tel);
		int count=suinfo.updateUserInfo(user);  //跟新成功的记录数
		System.out.println("UserInfoUpdate调用UserInfoGetAction");
		response.sendRedirect(request.getContextPath()+"/servlet/UserInfoGetAction?count="+count+"&id="+id );
	}
}
