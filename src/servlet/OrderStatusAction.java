package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import domain.OrderInfo;

/**
 * 订单发货
 */
//@WebServlet("/OrderStatusAction")
public class OrderStatusAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderStatusAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDao orderDao=new OrderDao();
		String orderIdAll[] = request.getParameterValues("delid");
		if (orderIdAll != null) {
			 //前端的使用者,如果没打勾的话 , request.getParameterValues("delid")会接收到null值
			int size = java.lang.reflect.Array.getLength(orderIdAll);//阵列大小
			String oS= request.getParameter("orderStatus");//状态
			int oid=0;
			for (int i = 0; i < size; i++) { 
				oid=Integer.parseInt(orderIdAll[i]);
				if("2".equals(oS)){//2 未发货
					orderDao.modifyOrderStatus(oid,3,"1970-01-01 00:00:00");
				}else{
					
				}
			}
			// 利用一个for将阵列资料取出
		}
		//订单重新加载
		ArrayList<OrderInfo> orderList = new ArrayList<OrderInfo>();
		orderList = orderDao.orderList();
		request.getSession().setAttribute("orderAllList", orderList);
		// 重定向
		response.sendRedirect(request.getContextPath() + "/shopping-back/jsp/manageOrder.jsp");
		
	}
}
