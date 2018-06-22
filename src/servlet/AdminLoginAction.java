package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminLoginDao;
import domain.AdminInfo;

public class AdminLoginAction extends HttpServlet {

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
		this.doPost(request, response);
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
		String method=request.getParameter("method");
		if(method.equals("login")){
		String name=request.getParameter("adminName");
		String pwd=request.getParameter("adminPassword");

		AdminLoginDao adminDB=new AdminLoginDao(); //调用Dao层验证用户名和密码
		AdminInfo admin=new AdminInfo();
		admin.setAdmin_name(name);
		admin.setAdmin_pwd(pwd);
		/*ArrayList<Date> al=new ArrayList<Date>();
		al.add(new Date()); //存入当前时间
		admin.setLoginTime(al);*/
		int flag=adminDB.login(admin);
		System.out.println("----!"+admin.getId());
		System.out.println(admin.getAdmin_email());
		System.out.println(admin.getAdmin_tel());
		if(flag==1){
			admin.setLoginTime(adminDB.getLoginTimebyId(admin.getId()));
			request.getSession().setAttribute("admin", admin);
	
			response.sendRedirect("BackIndexAction");
		}else{
			request.setAttribute("msg", "err");
			request.getRequestDispatcher("/shopping-back/jsp/login.jsp").forward(request, response);

		}
	}
		 else if(method.equals("logout")){
			 request.getSession().removeAttribute("admin");
			 System.out.println("退出成功");
				response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/login.jsp");

		 }
	}

}
