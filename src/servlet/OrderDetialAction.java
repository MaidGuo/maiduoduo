package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import domain.OrderInfo;
import domain.UserInfo;

/**
 * Servlet implementation class OrderDetialAction
 */
//@WebServlet("/OrderDetialAction")
public class OrderDetialAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public OrderDetialAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderid=Integer.parseInt(request.getParameter("orderID"));
		int buttonNum=Integer.parseInt(request.getParameter("buttonNum"));
		OrderDao orderDao =new OrderDao();
		if(buttonNum==1){//查看
		OrderInfo order=orderDao.oneOrder(orderid);
		request.getSession().setAttribute("OrderDetials",order);
		//重定向到订单
	  response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/orderDetial.jsp");	
		}else if(buttonNum==2){//收货,4：交易成功
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			orderDao.modifyOrderStatus(orderid,4,date);
			//重新加载订单
			//验证是否登录
			int userid ;//用户ID
			UserInfo user=(UserInfo)request.getSession().getAttribute("user");
			if(user!=null){
				userid = user.getId();
			}else{
				userid=-1;
			}
			//userid=1;//测试！！！！
			//1 未付款2 未发货 3 已发货 4交易成功
			ArrayList<ArrayList<OrderInfo>> order=new ArrayList<ArrayList<OrderInfo>>();
			ArrayList<OrderInfo> orders=new ArrayList<OrderInfo>();
			for(int j=0;j<4;j++){
				orders = new ArrayList<OrderInfo>();
				orders=orderDao.orderStatusList(userid, (j+1));
				order.add(orders);
			}
			request.getSession().setAttribute("orderList1", order.get(0));// 1
			request.getSession().setAttribute("orderList2", order.get(1));// 2
			request.getSession().setAttribute("orderList3", order.get(2));// 3
			request.getSession().setAttribute("orderList4", order.get(3));// 4
			//重定向到订单
			response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/order.jsp");	
				
		}else if(buttonNum==3){//评价
			
		}
	}

}
