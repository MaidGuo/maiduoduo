package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.DBConnection;
import domain.OrderInfo;
import domain.OrderItemInfo;

//����
public class OrderDao {
	private Connection con = null;
	
	// ���ж���OrderInfo ��̨
	public ArrayList<OrderInfo> orderList() {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// ����
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
				 aorder.setCreated_time(created_time);// ת��
				//aorder.setCreated_time(rs.getDate("created_time"));// ת��
				
				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// ״̬��1 δ����2 �Ѹ��� 3 δ���� 4�ѷ��� 5���׳ɹ�
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time=rs.getTimestamp("end_time");
				 //new Date(created_time.getTime());
				 aorder.setEnd_time(end_time);// ת��
				//aorder.setEnd_time(rs.getDate("end_time"));
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));
				orderlist.add(aorder);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡ���ж�����Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return orderlist;
	}/* ��ȡ���ж�����Ϣ */
	
	// һ���û����� �Ķ���OrderInfo
	public ArrayList<OrderInfo> orderAllList(int userid) {
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			OrderInfo aorder = null;// ����
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
					aorder.setCreated_time(created_time);// ת��

					aorder.setUser_id(rs.getInt("user_id"));
					aorder.setPayment(rs.getDouble("payment"));
					aorder.setPayment_type(rs.getInt("payment_type"));
					aorder.setSend_time(rs.getInt("send_time"));
					// ״̬��1 δ����2 δ����3�ѷ��� 4���׳ɹ�
					aorder.setStatus(rs.getInt("status"));
					Timestamp end_time = rs.getTimestamp("end_time");
					aorder.setEnd_time(end_time);// ת��
					aorder.setBuyer_message(rs.getString("buyer_message"));
					aorder.setIscomment(rs.getInt("iscomment"));

					orderlist.add(aorder);
				}
				rs.close();
				pStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��ȡ���ж�����Ϣʧ�ܣ�");
			} finally {
				DBConnection.closeConnection();
			}
			return orderlist;
		}/* ��ȡһ���û����ж�����Ϣ */
	
	// һ���û����� XX״̬�Ķ���OrderInfo
	public ArrayList<OrderInfo> orderStatusList(int userid,int status) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// ����
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
				aorder.setCreated_time(created_time);// ת��

				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// ״̬��1 δ����2 �Ѹ��� 3 δ���� 4�ѷ��� 5���׳ɹ�
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time = rs.getTimestamp("end_time");
				// new Date(created_time.getTime());
				aorder.setEnd_time(end_time);// ת��
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));

				orderlist.add(aorder);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡָ��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return orderlist;
	}/* ��ȡһ���û�ָ��������Ϣ */
	
	// һ������OrderInfo
	public OrderInfo oneOrder(int orderid) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderInfo aorder = null;// ����
		
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
				aorder.setCreated_time(created_time);// ת��
				aorder.setUser_id(rs.getInt("user_id"));
				aorder.setPayment(rs.getDouble("payment"));
				aorder.setPayment_type(rs.getInt("payment_type"));
				aorder.setSend_time(rs.getInt("send_time"));
				// ״̬��1 δ����2  δ���� 3�ѷ��� 4���׳ɹ�
				aorder.setStatus(rs.getInt("status"));
				Timestamp end_time = rs.getTimestamp("end_time");
				aorder.setEnd_time(end_time);// ת��
				aorder.setBuyer_message(rs.getString("buyer_message"));
				aorder.setIscomment(rs.getInt("iscomment"));

			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡһ��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return aorder;
	}/* ��ȡһ��������Ϣ */
	
	// һ��������������
	public ArrayList<OrderItemInfo> orderItem(int orderId) {
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		OrderItemInfo orderItem = null;// ��������
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
			System.out.println("��ȡһ����������������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return orderItemlist;
	}/* ��ȡһ����������������Ϣ */
	
	// ����,�ջ�
	public int modifyOrderStatus(int id,int changetoS,String endTime) {
		PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
        	// ״̬��1 δ����2  δ���� 3�ѷ��� 4���׳ɹ�
    		pStmt = con.prepareStatement("update orders set status=?,end_time=? where order_id=?"); 		
    		pStmt.setInt(1,changetoS);	
    		pStmt.setString(2,endTime);	
    		pStmt.setInt(3,id);		
    		count=pStmt.executeUpdate();
			pStmt.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("�޸Ķ���״̬ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
		}/* ����������Ϣ */
	
	
}
