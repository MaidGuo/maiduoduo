package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.ReceiveAddress;

/**
 * 处理地址新增请求
 * @author Administrator
 *
 */
public class AddressInsertAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		ReceiveAddress address=new ReceiveAddress();
		address.setAddress(MyTools.toChinese(request.getParameter("detaddress")));
		address.setCity(MyTools.toChinese(request.getParameter("s_city")));
		address.setCounty(MyTools.toChinese(request.getParameter("s_county")));
		address.setIsdefault(MyTools.strToint(request.getParameter("defaultuadd")));
		address.setName(MyTools.toChinese(request.getParameter("customer")));
		address.setPostcode(request.getParameter("postcode"));
		address.setProvince(MyTools.toChinese(request.getParameter("s_province")));
		address.setTel(request.getParameter("phone"));
		address.setUserId(MyTools.strToint(request.getParameter("userid")));
		int count=suinfo.insertAddress(address);
		if(count!=1) {  //新增地址失败，返回新增地址页面
			response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/addressManager.jsp?count="+count);
		}else {    //修改地址成功，直接返回地址显示页面，显示新增的地址
			response.sendRedirect(request.getContextPath()+"/servlet/AddressGetAction?userid="+address.getUserId());
		}
		
	}
}
