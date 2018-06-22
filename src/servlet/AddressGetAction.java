package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.*;

import java.util.*;

/**
 * 处理地址查询请求
 * @author Administrator
 *
 */
public class AddressGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String strid=request.getParameter("userid"); //得到用户id
		int id=MyTools.strToint(strid);
		UserDao suinfo=new UserDao();
		List<ReceiveAddress> addresslist=suinfo.selectAddress(id);
		request.getSession().setAttribute("addresslist", addresslist);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/addressManager.jsp?userid="+id);
	}
}
