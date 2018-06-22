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

import dao.ConfirmOrderDao;
import domain.*;

public class ConfirmOrderAction extends HttpServlet {

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
		UserInfo user=(UserInfo)request.getSession().getAttribute("user");
	  int userId=user.getId();
	
	//  int userId=1;
		String pwd=user.getPwd();

	  //String pwd="123456";
	  ArrayList<GoodsInfo> carList=(ArrayList<GoodsInfo>)request.getSession().getAttribute("cart");
	if(carList==null) carList=new ArrayList<GoodsInfo>();
	  //	System.out.println("--"+id);
		ConfirmOrderDao order=new ConfirmOrderDao();
		if(method.equals("order")){
		String str=request.getParameter("nums"); //得到购买商品数量的数组
		String []nums=str.split(",");
		
		for(int i=0;i<carList.size();i++){
			GoodsInfo goods=carList.get(i);
			goods.setBuyNum(MyTools.strToint(nums[i]));
			
		}
	  	ArrayList<ReceiveAddress> addressList=order.getAddressById(userId);
	  	
	  	request.setAttribute("addressList", addressList); //地址
	
	  	request.getRequestDispatcher("/shopping-web/jsp/confirmOrder.jsp").forward(request, response);
	
	}else if(method.equals("pay")){ //判断支付密码
		  //读取json数据
			BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
				String line=null;
			StringBuilder sb=new StringBuilder();
			while((line=br.readLine())!=null ){
				sb.append(line);
			}
			br.close();
				JSONObject json=JSONObject.fromObject(sb.toString());
				String receiverName=(String)json.get("rname");
				String passwd=(String)json.get("pwd");
				String address=(String)json.get("address");
				System.out.println(address);
				String []str=address.split(" ");
				String province=str[0];
				String city=str[1];
				String country=str[2];
				String detailAddress=(String)json.get("diqu");
				String postCode=(String)json.get("youbian");
				String tel=(String)json.get("tel");
				String payment=(String)json.get("payment");//总额
				double totalPrice=Double.parseDouble(payment);
				System.out.println("￥"+totalPrice);
				String type=(String)json.get("paymentType"); //支付方式
				int paymentType=MyTools.strToint(type);
				String time=(String)json.get("sendtime");
				int sendtime=MyTools.strToint(time);//发货时间
				String message=(String)json.get("message");
				//System.out.println("pwd"+passwd+" rname:"+receiverName+" postcode:"+postCode+" payment："+payment+" time"+sendtime);
				boolean flag=true;
				int cnt=0;//判断成功与否的标记
					String success="";
					String s=MyTools.EncryptionStr(passwd,"MD5" ); //使用MD5加密
					
					if(s.equals(pwd)){ //密码匹配成功
					
						String orderNo=MyTools.buildOrderNo();//产生订单号
						OrderInfo orderList=new OrderInfo(); //订单表
						orderList.setOrder_no(orderNo);
						orderList.setCreated_time(new Date());
						orderList.setUser_id(userId);
						orderList.setPayment(totalPrice); //实付金额 
						orderList.setPayment_type(paymentType); //支付方式
						orderList.setSend_time(sendtime);
						orderList.setBuyer_message(message);
						int count =order.buildOrder(orderList);//创建订单表
						if(count!=1) {flag=false;
						cnt++;
						}
					
						int order_id=order.findOrder_idByno(orderNo);//获取订单的唯一编号
						orderList.setOrder_id(order_id); 
						/*
						 * 订单配送表的建立 
						 */
						OrderShippingInfo shipping =new OrderShippingInfo();
						shipping.setOrder_id(orderList.getOrder_id());
						shipping.setReceiver_name(receiverName);
						shipping.setReceiver_tel(tel);
						shipping.setProvince(province);
						shipping.setCity(city);
						shipping.setCountry(country);
						shipping.setReceiver_address(detailAddress);
						shipping.setPost_code(postCode);
						count=order.buildOrderShipping(shipping);
						if(count!=1)  {flag=false;
						cnt++;
						}
					
						/*
						 *订单详情表的建立 (有多条商品记录)
						 */
						ArrayList<OrderItemInfo> itemList=new ArrayList<OrderItemInfo>();
						
						for(int i=0;i<carList.size();i++){
							GoodsInfo goods=carList.get(i);
						//	System.out.println("goods name+ num: "+goods.getGoods_num()+" "+goods.getBuyNum()+" 钱"+goods.getBuyNum()*);
							OrderItemInfo item=new OrderItemInfo();
						//	item.setId(order_id);
							item.setGoods_id(goods.getGoods_id());
							item.setNum(goods.getBuyNum());
							item.setTotal_price(goods.getGoods_price()*goods.getBuyNum());
							
							item.setOrder_id(order_id);
							
							count=order.buildOrderItem(item);
							if(count!=1) {flag=false;
							cnt++;
							}
						}
						System.out.println("3-"+flag);
						/*
						 * 删除已购买的商品(购物车里的所有商品都会购买) 
						 */
						count=order.deleteCartList(userId);
					
						if(count!=1) {flag=false;
						cnt++;
						}
						System.out.println("4-"+flag);
						/*
						 *修改商品的购买次数并减少商品的库存量
						 */
						for(int i=0;i<carList.size();i++){
							count=order.updateTimesAndNums(carList.get(i).getGoods_id());
							if(count!=1)  {flag=false;
							cnt++;
							}
						}
						
						if(flag==true&&cnt==0)success="true"; //全部数据库操作成功 
						else success="payError"; //支付失败
					}else{
						success="false";
					}
					response.setContentType("application/json;charset=utf-8");
					response.setHeader("cache-control", "no-cache");
					JSONObject j=new JSONObject();
					j.put("success", success);
					response.getWriter().print(j);	
					response.getWriter().close();
					
	}
	}
}
