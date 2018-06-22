package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.*;

/**
 * 处理评论新增请求
 * @author Administrator
 *
 */
public class ReviewInsertAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		String struserid=request.getParameter("userid"); //获得用户id		
		GoodsInfo goods=(GoodsInfo)request.getSession().getAttribute("goods");
		UserInfo user=(UserInfo)request.getSession().getAttribute("user");
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time=df.format(day);
		CommentInfo review=new CommentInfo();
		review.setComment_content(MyTools.toChinese(request.getParameter("content")));;
		review.setGoods(goods);
		review.setComment_theme(MyTools.toChinese(new String(request.getParameter("theme"))));;
		review.setComment_time(time);;
		review.setUser(user);
		int count=suinfo.insertReview(review);
		
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/addReview.jsp?count="+count );
	}
}
