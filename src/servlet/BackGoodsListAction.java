package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageGoodsDao;
import dao.OrderDao;
import domain.GoodsInfo;
import domain.OrderInfo;

/**
 * Servlet implementation class BackGoodsListAction
 */
//@WebServlet("/BackGoodsListAction")
public class BackGoodsListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BackGoodsListAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response); //调用doPost方法
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是去1 goods，2 goodsClass，3 order
		int dp=Integer.parseInt(request.getParameter("leftPD"));
		ManageGoodsDao mgoodsDB=new ManageGoodsDao();
		if(dp==1){
			//所有商品List
			ArrayList<GoodsInfo> AllGoodsList=new ArrayList<GoodsInfo>();
			AllGoodsList=mgoodsDB.AllGoodsList();
			request.getSession().setAttribute("AllGoodsList",AllGoodsList);//显示所有商品List
			//重定向到goods
		    response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/manage_goods.jsp");
		
		}else if(dp==2){
			request.getSession().setAttribute("backAllmGoodsList",mgoodsDB.mAppear());//显示goodsClass
			//重定向到goodsClass
		    response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/manage_goodsClass.jsp");
		
		}else if(dp==3){
			//订单
			OrderDao orderDao=new OrderDao();
			ArrayList<OrderInfo> orderList=new ArrayList<OrderInfo>();
			orderList=orderDao.orderList();
			request.getSession().setAttribute("orderAllList",orderList);
			//重定向到主页面。
		    response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/manageOrder.jsp");
		
		}
	}

}
