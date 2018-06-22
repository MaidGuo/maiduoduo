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

/**����
 * 1 δ����2 �Ѹ��� 3 δ���� 4 �ѷ��� 5���׳ɹ�
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
		//��֤�Ƿ��¼
		int userid ;//�û�ID
		UserInfo user=(UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			userid = user.getId();
		}else{
			userid=-1;
		}
	//	userid=1;//���ԣ�������
		if(userid!=-1){//��¼״̬
		OrderDao OrderDao =new OrderDao();
		//1 δ����2 δ���� 3 �ѷ��� 4���׳ɹ�
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
		//�ض��򵽶���
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/order.jsp");	
	}else{//δ��¼
		//�ض��򵽵�¼����
	    response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/homePage.jsp");//��¼.jsp	
	}
	}

}
