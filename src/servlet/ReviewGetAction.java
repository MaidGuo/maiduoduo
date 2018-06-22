package servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.*;
import common.WithPage;

import java.util.*;

/**
 * 处理评论查询请求
 * @author Administrator
 *
 */
public class ReviewGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		List<CommentInfo> reviewlist;
		String strid=request.getParameter("id"); //获得用户id		
		int id=MyTools.strToint(strid);
		reviewlist=suinfo.selectReview(id); //获得该用户所用的评论
		request.getSession().setAttribute("reviewlist", reviewlist); //查看详情会用到
		//获取当前是第几页    默认为第一页
		int pageNum=MyTools.strToint(request.getParameter("pageNum"));
		System.out.println("ReviewGetAction输出当前页：pageNum"+pageNum);
		//每页显示的记录数
		int pageSize=5;
		//调用service层方法，进行处理
		WithPage wp=new WithPage();
		//获取一个PageBean对象，pb中包含了该页所需要的内容
		PageBean pb=wp.findAllReview(pageNum,pageSize,reviewlist );
		//将pb放入request域中
		request.getSession().setAttribute("pagePB",pb);
		System.out.println("ReviewGetAction输出总页数："+pb.getTotalPage()+"输出末尾页数："+pb.getEnd());
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/review.jsp?id="+id );
	}
}
