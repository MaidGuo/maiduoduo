package servlet;

import java.io.*;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONObject;



import common.*;

import dao.*;
import domain.*;

public class UserRegisterAction extends HttpServlet {
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * ���ܣ���ȡҳ�洫����ע���û���Ϣ��������DAO��ע�������ע��
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  final String MD5="MD5";
		  //��ȡjson����
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String line=null;
		StringBuilder sb=new StringBuilder();
		while((line=br.readLine())!=null ){
			sb.append(line);
		}
		
			JSONObject json=JSONObject.fromObject(sb.toString());
			String name=(String)json.get("name");
			String pwd=(String)json.get("pwd");
			String passWord=MyTools.EncryptionStr(pwd,MD5); //����
		/*
		 *����user����
		 */
			UserInfo user=new UserInfo();
			user.setName(name);
			user.setPwd(passWord);
			user.setGender(MyTools.strToint((json.get("gender").toString())));
			user.setEmail((String)json.get("email"));
			user.setQuestion(MyTools.strToint((json.get("mibao").toString())));
			user.setAnswer((String)json.get("mbanswer"));
			user.setTrueName((String)json.get("truename"));
			user.setAddress((String)json.get("town"));
			user.setPostCode((String)json.get("youbian"));
			user.setTel((String)json.get("tel"));	
			user.setRegisterTime(new Date());
			System.out.println(user.getRegisterTime());
		//	user.setCreatedTime(MyTools.transDateToString(new Date()));
			String activeCode=name + System.currentTimeMillis();//��õ�����1970-1-01 00:00:00.000 ����ǰʱ�̵�ʱ�����+username
			user.setActiveCode(MyTools.EncryptionStr(activeCode,MD5)); //���ü�����
			String str=(String)json.get("city");
			String []str1=str.split("/");
			if(str1.length==2){ //ֱϽ�� 
				
				user.setProvince(str1[0]);
				user.setCity(str1[0]);
				user.setCountry(str1[1]);
			}else{
			user.setProvince(str1[0]);
			user.setCity(str1[1]);
			user.setCountry(str1[2]);
			}
			UserRegisterDao rDB=new UserRegisterDao();
			int count=rDB.registerUser(user); //ע���û�
			String success="";
			if(count==1){
				//�ɹ�
				
				System.out.println("�ɹ�"); 
				int id=rDB.getUserid(name);
				user.setId(id);
				/*
				 * cun dizhi
				 */
				ReceiveAddress address=new ReceiveAddress();
				address.setAddress(user.getAddress());
				address.setCity(user.getCity());
				address.setCounty(user.getCountry());
				address.setIsdefault(1);
				address.setName(user.getName());
				address.setPostcode(user.getPostCode());
				address.setProvince(user.getProvince());
				address.setTel(user.getTel());
				address.setUserId(user.getId());
				int count2=rDB.insertAddress(address);
		
				rDB.getActiveCodebyId(id);
				System.out.println("�����ʼ�");
				 //�����ʼ���һ���ǳ���ʱ�����飬������￪������һ���߳���ר�ŷ����ʼ�
				sendMail send = new sendMail(user);
				 //�����̣߳��߳�����֮��ͻ�ִ��run�����������ʼ�
			      send.start();
				success="true";
				//ע��ɹ����ͼ����ʼ�
			
			}else{
				//System.out.println("ʧ��");
				success="false";
			}
			
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			JSONObject j=new JSONObject();
			j.put("success", success);
			response.getWriter().print(j);

	}
	/**
	 * ���ܣ���ȡҳ�洫����ע���û���Ϣ��������DAO��ע�������ע��
	 * @param user
	 * @return ע����
	 * @throws JsonProcessingException 
	 */

	
	
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
