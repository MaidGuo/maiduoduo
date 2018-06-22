package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;

import net.sf.json.JSONObject;

import dao.UserLoginDao;
import domain.UserInfo;

public class UserLoginAction extends HttpServlet {

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
		  final String MD5="MD5";
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line=null;
		StringBuilder sb=new StringBuilder();
		while((line=br.readLine())!=null){
			sb.append(line);
		}
			JSONObject json=JSONObject.fromObject(sb.toString());
			UserInfo user=new UserInfo();
			String name=(String)json.get("name");
			String pwd=(String)json.get("pwd");
			String s=MyTools.EncryptionStr(pwd,MD5 ); // π”√MD5º”√‹
			user.setName(name);
			user.setPwd(s);	
		
		UserLoginDao ld=new UserLoginDao();
		int data=ld.login(user);
		String success="";
		switch (data) {
		case 0:
			success="unregister";break;
		case 1:
			{
				success="true";
			//	System.out.println("user:"+user.getId());
				//user=ld.getUserbyName(user.getName());
				//System.out.println("mima:"+user.getPwd());
				request.getSession().setAttribute("user", user);
				break;
			
			}case 2:
			success="notActive";break;
		case 3:success="false";break;
		default:
			success="false";break;
	}
		System.out.println("dneglu "+user.getId());
		
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		JSONObject j=new JSONObject();
		j.put("success", success);
		j.put("uid",user.getId());
		response.getWriter().print(j);
		
	}

}
