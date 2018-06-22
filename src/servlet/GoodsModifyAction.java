package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageGoodsDao;
import domain.GoodsInfo;

/**
 * 后台一个商品的具体信息展示
 * Servlet implementation class GoodsModifyAction
 */
//@WebServlet("/GoodsModifyAction")
public class GoodsModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GoodsModifyAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response); //调用doPost方法
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取客户端提交数据
		int pd=Integer.parseInt(request.getParameter("goodsOrClass"));//判断是类别2还是商品1
		int id=Integer.parseInt(request.getParameter("goodsId"));
		ManageGoodsDao goodsInfo = new ManageGoodsDao();
		// 获得一条商品所有显示信息
		request.getSession().setAttribute("oneGoodsInfo", goodsInfo.goodsDetails(id));
		if(pd==1){
		// 重定向
		response.sendRedirect(request.getContextPath() + "/shopping-back/jsp/goodsUpdate.jsp");
	
		}else{
			// 重定向
			response.sendRedirect(request.getContextPath() + "/shopping-back/jsp/goodsClassUpdate.jsp");
		
		}
	}

}
