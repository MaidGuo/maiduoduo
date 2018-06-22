package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.DBConnection;
import domain.OrderInfo;
import domain.OrderItemInfo;

//订单
public class OrderDao {
	private Connection con = null;
	
	// 所有订单OrderInfo 后台
	public ArrayList<OrderInfo> orderList() {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// 订单
		ArrayList<OrderInfo> orderlist = new ArrayList<OrderInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("select * from orders");
			rs = pStmt.executeQuery();
			while (rs.next()) {
				aorder = new OrderInfo();
				aorder.setOrder_id(rs.getInt("order_id"));
				aorder.setOrder_no(rs.getString("order_no"));
				
				 Timestamp created_time=rs.getTimestamp("created_time");
				 //new Date(created_time.getTime());
				 aorder.setCreated_time(created_time);// 转化
				//aorder.setCreated_time(rs.getDate("created_time"));// 转化
				
				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// 状态，1 未付款2 已付款 3 未发货 4已发货 5交易成功
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time=rs.getTimestamp("end_time");
				 //new Date(created_time.getTime());
				 aorder.setEnd_time(end_time);// 转化
				//aorder.setEnd_time(rs.getDate("end_time"));
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));
				orderlist.add(aorder);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取所有订单信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return orderlist;
	}/* 获取所有订单信息 */
	
	// 一个用户所有 的订单OrderInfo
	public ArrayList<OrderInfo> orderAllList(int userid) {
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			OrderInfo aorder = null;// 订单
			ArrayList<OrderInfo> orderlist = new ArrayList<OrderInfo>();
			try {
				con = DBConnection.getConnection();
				pStmt = con.prepareStatement("select * from orders where user_id=?");
				pStmt.setInt(1, userid);
				rs = pStmt.executeQuery();
				while (rs.next()) {
					aorder = new OrderInfo();
					aorder.setOrder_id(rs.getInt("order_id"));
					aorder.setOrder_no(rs.getString("order_no"));

					Timestamp created_time = rs.getTimestamp("created_time");
					aorder.setCreated_time(created_time);// 转化

					aorder.setUser_id(rs.getInt("user_id"));
					aorder.setPayment(rs.getDouble("payment"));
					aorder.setPayment_type(rs.getInt("payment_type"));
					aorder.setSend_time(rs.getInt("send_time"));
					// 状态，1 未付款2 未发货3已发货 4交易成功
					aorder.setStatus(rs.getInt("status"));
					Timestamp end_time = rs.getTimestamp("end_time");
					aorder.setEnd_time(end_time);// 转化
					aorder.setBuyer_message(rs.getString("buyer_message"));
					aorder.setIscomment(rs.getInt("iscomment"));

					orderlist.add(aorder);
				}
				rs.close();
				pStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("获取所有订单信息失败！");
			} finally {
				DBConnection.closeConnection();
			}
			return orderlist;
		}/* 获取一个用户所有订单信息 */
	
	// 一个用户所有 XX状态的订单OrderInfo
	public ArrayList<OrderInfo> orderStatusList(int userid,int status) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// 订单
		ArrayList<OrderInfo> orderlist = new ArrayList<OrderInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("select * from orders where user_id=? and status=?");
			pStmt.setInt(1, userid);
			pStmt.setInt(2, status);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				aorder = new OrderInfo();
				aorder.setOrder_id(rs.getInt("order_id"));
				aorder.setOrder_no(rs.getString("order_no"));

				Timestamp created_time = rs.getTimestamp("created_time");
				// new Date(created_time.getTime());
				aorder.setCreated_time(created_time);// 转化

				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// 状态，1 未付款2 已付款 3 未发货 4已发货 5交易成功
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time = rs.getTimestamp("end_time");
				// new Date(created_time.getTime());
				aorder.setEnd_time(end_time);// 转化
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));

				orderlist.add(aorder);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取指定订单信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return orderlist;
	}/* 获取一个用户指定订单信息 */
	
	// 一个订单OrderInfo
	public OrderInfo oneOrder(int orderid) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// 订单
		
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("select * from orders where order_id=?");
			pStmt.setInt(1, orderid);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				aorder = new OrderInfo();
				aorder.setOrder_id(rs.getInt("order_id"));
				aorder.setOrder_no(rs.getString("order_no"));
				Timestamp created_time = rs.getTimestamp("created_time");
				aorder.setCreated_time(created_time);// 转化
				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// 状态，1 未付款2  未发货 3已发货 4交易成功
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time = rs.getTimestamp("end_time");
				aorder.setEnd_time(end_time);// 转化
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));

			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取一个订单信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return aorder;
	}/* 获取一个订单信息 */
	
	// 一个订单所有详情
	public ArrayList<OrderItemInfo> orderItem(int orderId) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderItemInfo orderItem = null;// 订单详情
		ArrayList<OrderItemInfo> orderItemlist = new ArrayList<OrderItemInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM order_item where order_id=?");
			pStmt.setInt(1, orderId);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				orderItem = new OrderItemInfo();
				orderItem.setGoods_id(rs.getInt("goods_id"));
				orderItem.setId(rs.getInt("id"));
				orderItem.setNum(rs.getInt("num"));
				orderItem.setOrder_id(orderId);
				orderItem.setTotal_price(rs.getDouble("total_price"));

				orderItemlist.add(orderItem);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取一个订单所有详情信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return orderItemlist;
	}/* 获取一个订单所有详情信息 */
	
	// 发货,收货
	public int modifyOrderStatus(int id,int changetoS,String endTime) {
		PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
        	// 状态，1 未付款2  未发货 3已发货 4交易成功
    		pStmt = con.prepareStatement("update orders set status=?,end_time=? where order_id=?"); 		
    		pStmt.setInt(1,changetoS);	
    		pStmt.setString(2,endTime);	
    		pStmt.setInt(3,id);		
    		count=pStmt.executeUpdate();
			pStmt.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("修改订单状态失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
		}/* 发货订单信息 */
	
	
}
