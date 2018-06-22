package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.*;
import dao.UserDao;
import domain.*;


/**
 * 处理留言获取请求
 * @author Administrator
 *
 */
public class MessageGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		List<MessageInfo> messagelist;
		String strid=request.getParameter("userid"); //获得用户id		
		int id=MyTools.strToint(strid);
		messagelist=suinfo.selectMessage(id);  
		int length=messagelist.size();
		int replycount=0;
		for(int i=0;i<length;i++) {
			if(messagelist.get(i).getIsReply()==1) { //若该条留言被回复
				replycount++;
			}
		}
		UserInfo user=suinfo.selectUser(id); //获取用户信息
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("replycount", replycount);
		request.getSession().setAttribute("messagelist", messagelist); //查看详情用
		//获取当前是第几页    默认为第一页
		int pageNum=MyTools.strToint(request.getParameter("pageNum"));
		System.out.println("ReviewGetAction输出当前页：pageNum"+pageNum);
		//每页显示的记录数
		int pageSize=5;
		//调用service层方法，进行处理
		WithPage wp=new WithPage();
		//获取一个PageBean对象，pb中包含了该页所需要的内容
		PageBean pb=wp.findAllMessage(pageNum,pageSize,messagelist );
		//将pb放入request域中
		request.getSession().setAttribute("pagePB",pb);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/complaint.jsp");
	}
}
