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
 * 处理地址删除请求
 * @author Administrator
 *
 */
public class AddressDeleteAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AddressDeleteAction:输出");
		String index=request.getParameter("index"); //获取用户要删除的地址索引
		int i=MyTools.strToint(index);
		String struserid=request.getParameter("userid");  //获取用户id
		int userid=MyTools.strToint(struserid);
		UserDao suinfo=new UserDao();
		List<ReceiveAddress> addresslist=(List<ReceiveAddress>)request.getSession().getAttribute("addresslist");
		int addressid=addresslist.get(i).getAddressId();
		int flag=suinfo.deleteAddress(addressid);
		if(flag==1) { //删除成功
			response.sendRedirect(request.getContextPath()+"/servlet/AddressGetAction?userid="+userid);
			
		}else {  //删除失败
			response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/addressManager.jsp?flag="+flag);
		}
	}
}
