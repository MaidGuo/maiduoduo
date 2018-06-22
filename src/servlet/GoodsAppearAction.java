package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageGoodsDao;

/**
 * 显示一个商品
 */
//@WebServlet("/GoodsAppearAction")
public class GoodsAppearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GoodsAppearAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取客户端提交数据
		int id = Integer.parseInt(request.getParameter("goodsId"));
		ManageGoodsDao goodsInfo = new ManageGoodsDao();
		// 获得一条商品所有显示信息
		request.getSession().setAttribute("oneGoodsInfo", goodsInfo.goodsDetails(id));
		// 重定向到查询显示课程信息列表的servlet
		response.sendRedirect(request.getContextPath() + "/shopping-web/jsp/goods2.jsp");
	}

}
