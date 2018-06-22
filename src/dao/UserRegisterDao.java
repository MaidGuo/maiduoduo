package dao;

import java.sql.*;

import common.*;
import domain.ReceiveAddress;
import domain.UserInfo;
/**
 * �û�ע��Dao
 * @author Maid Guo
 * 
 */
public class UserRegisterDao {

	private Connection con = null;
	
	/**
	 * ���õ����û���Ϣע������ݿ�
	 * @param user �û���Ϣ
	 * @return 
	 */
	public int registerUser(UserInfo user){
		
		PreparedStatement pStmt= null;

		int count=0;
		
		try {
			con = DBConnection.getConnection();
String sql = "insert into user_info (user_name,user_pwd,user_gender,user_email,question,answer,user_truename,province,city,country,user_address,post_code,user_tel,status,created_time,active_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPwd());
			pStmt.setInt(3, user.getGender());
			pStmt.setString(4, user.getEmail());
			pStmt.setInt(5, user.getQuestion());
			pStmt.setString(6, user.getAnswer());
			pStmt.setString(7, user.getTrueName());
			pStmt.setString(8, user.getProvince());
			pStmt.setString(9, user.getCity());
			pStmt.setString(10, user.getCountry());
			pStmt.setString(11, user.getAddress());
			pStmt.setString(12, user.getPostCode());
			pStmt.setString(13, user.getTel());
			pStmt.setInt(14, user.getStatus());
			pStmt.setString(15, MyTools.transDateToString(user.getRegisterTime()));
			pStmt.setString(16, user.getActiveCode());
			count=pStmt.executeUpdate();
			 pStmt.close();	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ע���û�ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
	/**
	 * ����һ����ַ
	 * @param address
	 * @return
	 */
	public int insertAddress(ReceiveAddress address) {
		int count=0;
		try {
			if(address.getIsdefault()==1) { //����õ�ַ����ΪĬ�ϵ�ַ��������Ĭ�ϵ�ַ��Ϊ��Ĭ��
				String sql1="update delivery_address set isdefault=? where isdefault=?";
				PreparedStatement pStmt1=con.prepareStatement(sql1);
				pStmt1.setInt(1, 0); //��Ϊ��Ĭ�ϵ�ַ
				pStmt1.setInt(2, address.getIsdefault());
				pStmt1.executeUpdate();
			}
			String sql= "insert into delivery_address(user_id,receiver_name,receiver_tel,post_code,province,city,country,receiver_address,isdefault) values (?,?,?,?,?,?,?,?,?)";  
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, address.getUserId());
			pStmt.setString(2, address.getName());
			pStmt.setString(3, address.getTel());
			pStmt.setString(4, address.getPostcode());
			pStmt.setString(5, address.getProvince());
			pStmt.setString(6, address.getCity());
			pStmt.setString(7, address.getCounty());
			pStmt.setString(8, address.getAddress());
			pStmt.setInt(9, address.getIsdefault());
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("������ַʧ�ܣ�");
		}  
		return count;
	}

	/**
	 * ��ѯ�û����Ƿ��Ѵ���
	 * @param name
	 * @return
	 */
	public boolean validateName(String name){
		boolean flag=false;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM user_info where user_name= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, name); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  
				 flag=true;// �����û�
		
            } 
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ѯʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return flag;
	}
	/**
	 * �����û�����ȡ�û�id
	 * @param name �û���
	 * @return
	 */
	public int getUserid(String name){
		int id = 0;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT user_id FROM user_info where user_name= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, name); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  
				id=rs.getInt(1);	
            }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ȡ�û�idʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return id;
	}
	/**
	 * �����û�id�õ�������
	 * @param id �û�id
	 * @return
	 */
	public  UserInfo getActiveCodebyId(int id){
	UserInfo user=null;
		
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM user_info where user_id=?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, id); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  
				 user=new UserInfo();
				
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				user.setRegisterTime(rs.getTimestamp("created_time"));
				user.setActiveCode(rs.getString("active_code"));
	
            }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ȡ�û�������ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return user;
	}
	/**
	 * �����û�id�����û�����״̬
	 * @param id
	 * @return
	 */
	public int updateStatebyId(int id){
		
		PreparedStatement pStmt= null;
		int count=1;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="UPDATE user_info set status=1 where user_id=?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, id); 
			
			count=pStmt.executeUpdate();
			
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�޸��û�״̬ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
}
