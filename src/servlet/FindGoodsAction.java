package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import domain.GoodsInfo;

/**查找商品
 * Servlet implementation class FindGoodsAction
 */
//@WebServlet("/FindGoodsAction")
public class FindGoodsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindGoodsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GoodsDao goodsDao=new GoodsDao();
		String find=request.getParameter("findGoodsBox");
		System.out.println("findstr: "+find);
		ArrayList<GoodsInfo> goods=goodsDao.findGoods(find);//查询到的商品
		request.getSession().setAttribute("FindGoodsStr",find);//查询字段
		request.getSession().setAttribute("FindGoods",goods);
		//重定向
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/goodsFind.jsp");
	}

}
