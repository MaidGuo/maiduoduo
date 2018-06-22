package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import common.DBConnection;
import common.MyTools;

import domain.GoodsInfo;
import domain.OrderInfo;
import domain.OrderItemInfo;
import domain.OrderShippingInfo;
import domain.ReceiveAddress;

public class ConfirmOrderDao {
	/**
	 * 根据用户id查找用户收货地址
	 * @param id
	 * @return
	 */
	Connection con=null;
	public ArrayList<ReceiveAddress> getAddressById(int id) {
		ArrayList<ReceiveAddress> addressList=new ArrayList<ReceiveAddress>();
		ReceiveAddress address=null;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql="select * from delivery_address where user_id=?";
			pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, id);
			 rs=pStmt.executeQuery();
			while(rs.next()) {
				address=new ReceiveAddress();
				address.setAddressId(rs.getInt("address_id"));
				address.setUserId(rs.getInt("user_id"));
				address.setName(rs.getString("receiver_name"));
				address.setTel(rs.getString("receiver_tel"));
				address.setPostcode(rs.getString("post_code"));
				address.setAddress(rs.getString("receiver_address"));
				address.setProvince(rs.getString("province"));
				address.setCity(rs.getString("city"));
				address.setCounty(rs.getString("country"));
				address.setIsdefault(rs.getInt("isdefault"));
				addressList.add(address);
			}
			rs.close();
			 pStmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找地址失败!");
		}finally{
			DBConnection.closeConnection();
		}
		return addressList;
	}

	/**
	 * 创建一条订单信息
	 * @param oder
	 * @return
	 */
 public int buildOrder(OrderInfo order){
	 PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="Insert into orders(order_no,created_time,user_id,payment,payment_type,send_time,buyer_message)  values(?,?,?,?,?,?,?)"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, order.getOrder_no()); 
			 pStmt.setString(2, MyTools.transDateToString(order.getCreated_time()));
			 pStmt.setInt(3, order.getUser_id());
			 pStmt.setDouble(4,order.getPayment());
			 pStmt.setInt(5, order.getPayment_type());
			 pStmt.setInt(6, order.getSend_time());
			 pStmt.setString(7, order.getBuyer_message());
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("创建订单失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
 }
 /**
  * 创建订单详情（商品）
  * @param item
  * @return
  */
  public int buildOrderItem(OrderItemInfo item){
	  System.out.println("钱"+item.getTotal_price());
		 PreparedStatement pStmt= null;
			int count=0;
			try {
				con = DBConnection.getConnection();
				 String sql ="Insert into order_item(goods_id,order_id,num,total_price)  values(?,?,?,?)"; 
				 pStmt=con.prepareStatement(sql);
				 pStmt.setInt(1,item.getGoods_id()); 
				 pStmt.setInt(2, item.getOrder_id());
				 pStmt.setInt(3, item.getNum());
				 pStmt.setDouble(4,item.getTotal_price());
				 count=pStmt.executeUpdate();
			pStmt.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("创建订单详情失败");
			}finally{
				DBConnection.closeConnection();
			}
			return count;
  }
  /**
   * 增加购买次数
   * @return
   */
  public int updateTimesAndNums(int goodsId){
	  PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			String sql2="select purchase_times,goods_num from goods where goods_id="+goodsId;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql2);
			
			if(rs.next()){
				int times=rs.getInt("purchase_times");
				times=times+1;
				int nums=rs.getInt("goods_num");
				nums=nums-1;
				 String sql ="update goods set purchase_times=?,goods_num=? where goods_id=?"; 
				 pStmt=con.prepareStatement(sql);
				pStmt.setInt(1, times);
				pStmt.setInt(2, nums);
				pStmt.setInt(3, goodsId);
				count=pStmt.executeUpdate();
			}
			rs.close();
		pStmt.close();
		st.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("修改库存失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
  }
  /**
   * 创建订单配送表
   * @param send
   * @return
   */
 public int buildOrderShipping(OrderShippingInfo send){
	 PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="Insert into order_shipping(order_id,receiver_name,receiver_tel,post_code,receiver_address,province,city,country)  values(?,?,?,?,?,?,?,?)"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, send.getOrder_id()); 
			 pStmt.setString(2,send.getReceiver_name() );
			 pStmt.setString(3, send.getReceiver_tel());
			 pStmt.setString(4,send.getPost_code());
			 pStmt.setString(5, send.getReceiver_address());
			 pStmt.setString(6, send.getProvince());
			 pStmt.setString(7, send.getCity());
			 pStmt.setString(8,send.getCountry());
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("创建订单配送失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
 }
 /**
  * 通过订单号找到订单序号
  * @param orderNo
  * @return
  */
 public int findOrder_idByno(String orderNo){
	 int orderId=0;
	 PreparedStatement pStmt= null;
	 ResultSet rs=null;
	 try {
			con = DBConnection.getConnection();
			 String sql ="Select order_id from orders where order_no=?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, orderNo);
			 rs=pStmt.executeQuery();
			 if(rs.next()){
				  orderId=rs.getInt("order_id");
			 }
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("创建订单失败");
		}finally{
			DBConnection.closeConnection();
		}
	 return orderId;
 }
 /**
  * 删除已购买中的商品
  * @param carList
  * @return
  */
 public int deleteCartList(int userId){
	 PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="delete from shopping_cart where user_id =?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, userId);
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("删除购物车失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
 }
}
