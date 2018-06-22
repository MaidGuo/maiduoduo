package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.*;
/**
 * 验证邮箱是否成功激活
 * @author Maid Guo
 * @date 2018-06-11
 */
public class ValidateEmailAction extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=MyTools.strToint((String)request.getParameter("id"));
		String activeCode=(String)request.getParameter("activeCode");
		int flag=sendMail.processActivate(id, activeCode);
		
	//	request.setAttribute("flag",flag+"");
		request.getRequestDispatcher("/shopping-web/jsp/activeSuccess.jsp").forward(request, response);
	}
		
		
	
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doGet(request, response);
	}
	
	

}
