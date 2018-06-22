package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dao.OrderDao;
import domain.GoodsInfo;
import domain.OrderInfo;
import domain.OrderItemInfo;
import domain.UserInfo;

/**订单
 * 1 未付款2 已付款 3 未发货 4 已发货 5交易成功
 */
//@WebServlet("/OrderAction")
public class OrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public OrderAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证是否登录
		int userid ;//用户ID
		UserInfo user=(UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			userid = user.getId();
		}else{
			userid=-1;
		}
	//	userid=1;//测试！！！！
		if(userid!=-1){//登录状态
		OrderDao OrderDao =new OrderDao();
		//1 未付款2 未发货 3 已发货 4交易成功
		ArrayList<ArrayList<OrderInfo>> order=new ArrayList<ArrayList<OrderInfo>>();
		ArrayList<OrderInfo> orders=new ArrayList<OrderInfo>();
		for(int j=0;j<4;j++){
			orders = new ArrayList<OrderInfo>();
			orders=OrderDao.orderStatusList(userid, (j+1));
			order.add(orders);
		}
		request.getSession().setAttribute("orderList1", order.get(0));// 1
		request.getSession().setAttribute("orderList2", order.get(1));// 2
		request.getSession().setAttribute("orderList3", order.get(2));// 3
		request.getSession().setAttribute("orderList4", order.get(3));// 4
		//重定向到订单
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/order.jsp");	
	}else{//未登录
		//重定向到登录界面
	    response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/homePage.jsp");//登录.jsp	
	}
	}

}
