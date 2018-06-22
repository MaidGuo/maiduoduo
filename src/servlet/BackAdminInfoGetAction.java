package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.AdminDao;
import domain.*;
import common.WithPage;

public class BackAdminInfoGetAction extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int id=MyTools.strToint(request.getParameter("adminid")); //鑾峰緱鐢ㄦ埛id
			//flag=1:adminlist淇濆瓨鏁版嵁搴撴墍鏈夌殑绠＄悊鍛樹俊鎭�     flag!=1:adminlist淇濆瓨鏁版嵁搴撲腑婊¤冻鏉′欢鐨勭鐞嗗憳淇℃伅
			int flag=MyTools.strToint(request.getParameter("flag"));
			List<AdminInfo> adminlist;
			AdminDao suinfo=new AdminDao();
			List<AdminInfo> admins=suinfo.selectAdminById(id);
			AdminInfo admin=null;
			if(admins!=null) {
				if(admins.size()!=0){
					admin=admins.get(0);
				}else{
					admin=new AdminInfo();
				}
				
			}else{
				admin=new AdminInfo();
			}
			
			request.getSession().setAttribute("admin", admin);
			if(flag==1) {  //浠庢暟鎹簱涓幏鍙栨墍鏈夌殑鐢ㄦ埛淇℃伅
				adminlist=suinfo.selectAdmin();
			}else if(flag==3){  //浠庢暟鎹簱涓幏鍙栨墍鏈夋弧瓒虫潯浠剁殑鐢ㄦ埛淇℃伅
				adminlist=selectAdmin(request,response);
				flag=2;
			}else {  //flag=2鏃讹紝璇存槑session閲屽凡缁忓瓨鏈塧dminlist
				adminlist=(List<AdminInfo>)request.getSession().getAttribute("adminlist");
			}
			//鑾峰彇褰撳墠鏄鍑犻〉
			int pageNum=MyTools.strToint(request.getParameter("pageNum"));
			//姣忛〉鏄剧ず鐨勮褰曟暟
			int pageSize=5;
			//璋冪敤service灞傛柟娉曪紝杩涜澶勭悊
			WithPage wp=new WithPage();
			//鑾峰彇涓�涓狿ageBean瀵硅薄锛宲b涓寘鍚簡璇ラ〉鎵�闇�瑕佺殑鍐呭
			PageBean pb=wp.findAllAdmin(pageNum, pageSize,adminlist);
			//灏唒b鏀惧叆request鍩熶腑
			request.getSession().setAttribute("pagePB",pb);
			System.out.println("杈撳嚭鎬婚〉鏁帮細"+pb.getTotalPage()+"杈撳嚭鏈熬椤垫暟锛�"+pb.getEnd());
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/adminManager_main.jsp?flag="+flag);
		}
		
		public List<AdminInfo> selectAdmin(HttpServletRequest request, HttpServletResponse response){
			String condition=MyTools.toChinese(request.getParameter("selectcond"));
			AdminDao suinfo=new AdminDao();
			List<AdminInfo> adminlist;
			if(condition!=null&&!condition.equals("")) {
				//鍏堝垽鏂煡璇㈡潯浠舵槸鍚﹀叏涓烘暟瀛�
				  Pattern pat1 = Pattern.compile("[0-9]*"); 
				  Matcher m=pat1.matcher(condition);
				  if(m.matches()) { //濡傛灉鍏ㄤ负鏁板瓧锛屽垯闇�鍒ゆ柇璇ヤ覆鏁板瓧鏄惁涓虹數璇濆彿鐮侊紝鑻ヤ笉鏄紝鍒欎负鐢ㄦ埛id
					  String reg="^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
					  pat1 = Pattern.compile(reg); 
					  m=pat1.matcher(condition);
					  if(m.matches()) { //閫氳繃鐢佃瘽鍙风爜鏌ヨ
						  adminlist=suinfo.selectAdminByTel(condition);
					  }else { //閫氳繃绠＄悊鍛榠d鏌ヨ
						  int id=MyTools.strToint(condition);
						  adminlist=suinfo.selectAdminById(id);
					  }
				  }else { //閫氳繃绠＄悊鍛樺鍚嶆煡璇� 
					  System.out.println("鏍规嵁濮撳悕鏌ヨ锛岃緭鍑烘煡璇㈡潯浠� condition="+condition);
					  adminlist=suinfo.selectAdminByName(condition);
				  }

			}else {  //娌℃湁浠讳綍鏌ヨ鏉′欢
				adminlist=suinfo.selectAdmin();
			}
			request.getSession().setAttribute("adminlist", adminlist);
			return adminlist;
		}
}
