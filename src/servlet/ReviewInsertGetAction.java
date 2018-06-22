package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.*;
/**
 * 处理商品信息查询请求
 * @author Administrator
 *
 */
public class ReviewInsertGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		String struserid=request.getParameter("userid"); //获得用户id		
		int userid=MyTools.strToint(struserid);
		UserInfo user=suinfo.selectUser(userid);
		request.getSession().setAttribute("user", user);
		String strgoodsid=request.getParameter("goodsid"); //获得商品id
		int goodsid=MyTools.strToint(strgoodsid);
		GoodsInfo goods=suinfo.selectGoods(goodsid);
		request.getSession().setAttribute("goods", goods);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/addReview.jsp");
	}
}
