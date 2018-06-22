package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.ReceiveAddress;

/**
 * 处理地址修改请求
 * @author Administrator
 *
 */
public class AddressUpdateAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		ReceiveAddress address=new ReceiveAddress();
		address.setAddress(MyTools.toChinese(request.getParameter("detaddress")));
		address.setAddressId(MyTools.strToint(request.getParameter("addid")));
		address.setCity(MyTools.toChinese(request.getParameter("s_city")));
		address.setCounty(MyTools.toChinese(request.getParameter("s_county")));
		address.setIsdefault(MyTools.strToint(request.getParameter("defaultuadd")));
		address.setName(MyTools.toChinese(request.getParameter("customer")));
		address.setPostcode(request.getParameter("postcode"));
		address.setProvince(MyTools.toChinese(request.getParameter("s_province")));
		address.setTel(request.getParameter("phone"));
		address.setUserId(MyTools.strToint(request.getParameter("userid")));
		int index=MyTools.strToint(request.getParameter("index"));
		int count=suinfo.updateAddress(address);
		if(count!=1) {  //修改地址失败，返回修改页面
			response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/modifyAddress.jsp?count="+count+"&index="+index);
		}else {    //修改地址成功，直接返回地址显示页面，显示修改好的地址
			response.sendRedirect(request.getContextPath()+"/servlet/AddressGetAction?userid="+address.getUserId());
		}
		
	}
}
