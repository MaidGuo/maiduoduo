package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.UserDao;
import domain.*;

/**
 * 处理留言新增请求
 * @author Administrator
 *
 */
public class MessageInsertAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao suinfo=new UserDao();
		int userid=MyTools.strToint(request.getParameter("userid")); //获得用户id		
		MessageInfo message=new MessageInfo();
		message.setContent(MyTools.toChinese(request.getParameter("descript")));
		message.setTheme(MyTools.toChinese(request.getParameter("title")));
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time=df.format(day);
		message.setTime(time);
		message.setUserid(userid);
		int count=suinfo.insertMessage(message);
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/complaint1.jsp?userid="+userid+"&count="+count);
	}
}
