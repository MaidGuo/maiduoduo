package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import common.MyTools;

import dao.CartDao;
import dao.GoodsDao;
import domain.GoodsInfo;
import domain.ShoppingCartInfo;
import domain.UserInfo;

public class ShoppingCartAction extends HttpServlet {

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
		UserInfo user=(UserInfo)request.getSession().getAttribute("user");
		String flag=request.getParameter("flag"); 
		int userId=0;
	if(user!=null){
			userId=user.getId();
		//	int userId=1;
		System.out.println(flag+"uid"+userId);}
		CartDao carDB=new CartDao();
	
		//判断网页传的flag值 
		//如果为add （添加商品）
		if(flag.equals("add")){
			
			int id=MyTools.strToint(request.getParameter("goodsId"));
			int num=MyTools.strToint(request.getParameter("goodsNum"));
			System.out.println("id:"+id+" num:"+num);
			GoodsDao gDB=new GoodsDao();
			GoodsInfo goods=gDB.getGoodsbyId(id); //得到这条商品信息
			 //添加商品到购物车
			goods.setBuyNum(num);//加购数量
			goods.setAddToCartTime(new Date());
			String msg="";
				int count=carDB.addGoods(goods,userId);
				if(count==1){
					System.out.println("chenggong");
					msg="true";
				}else{
					msg="false";
				}
			
				response.getWriter().print(msg);	
			//response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/goods2")
		}else if(flag.equals("show")){
			
			ArrayList<GoodsInfo> al=carDB.getShoppingCar(userId);
		

			request.getSession().setAttribute("cart", al);
			response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/shoppingCart.jsp");
		}else if(flag.equals("update")){
			  //读取json数据
			BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
				String line=null;
			StringBuilder sb=new StringBuilder();
			while((line=br.readLine())!=null ){
				sb.append(line);
			}
		br.close();
				JSONObject json=JSONObject.fromObject(sb.toString());
				String str1=(String)json.get("goodsId");
				String str2=(String)json.get("goodsNum");		
				int goodsId=MyTools.strToint(str1);
				int buyNum=MyTools.strToint(str2);
				GoodsInfo goods=new GoodsInfo();
				goods.setGoods_id(goodsId);
				goods.setBuyNum(buyNum);
				goods.setAddToCartTime(new Date());
				int count=carDB.UpdateNumsCar(goods,userId);
				String success="";
				if(count==1){
				
					success="true";
				//	request.getSession().removeAttribute("cart");
				}else{
					success="false";
				}
			
				response.setContentType("application/json;charset=utf-8");
				response.setHeader("cache-control", "no-cache");
				JSONObject j=new JSONObject();
				j.put("success", success);
				response.getWriter().print(j);	
				response.getWriter().close();
		}else if(flag.equals("delete")){
	
				int goodsId=MyTools.strToint(request.getParameter("goodsId"));
				
			int count=carDB.deleteGoodsbyId(goodsId,userId);
			
			String msg="";
			if(count==1){
				msg="true";
			}else{
				msg="false";
			}
			response.getWriter().print(msg);	
			System.out.println("success :"+msg);
	

		}else if(flag.equals("deleteAll")){
			System.out.println("xxxx");
			int count=carDB.deleteCartList(userId);
			String msg="";
			System.out.println("----"+count);
			if(count==1){
				msg="true";
			}else{
				msg="false";
			}
			System.out.println("msg: sd"+msg);
			response.getWriter().print(msg);	
		}
		
	
	}

	

}
