package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBConnection;
import domain.UserInfo;
/**
 * ���û���¼�����ݿ����
 * @author Maid Guo
 *
 */
public class UserLoginDao {
	/**
	 * �ж��û��Ƿ��¼�ɹ�
	 * @param user
	 * @return
	 */
	public int login(UserInfo user){
		int flag=0;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM user_info where user_name= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, user.getName()); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  	 
				if(user.getPwd().equals(rs.getString("user_pwd"))){
					if(rs.getInt("status")==1)
					{
						/*
						 * �õ�user�������Ϣ
						 */
						user.setId(rs.getInt("user_id"));
						 user.setName(rs.getString("user_name"));
						 user.setGender(rs.getInt("user_gender"));
						 user.setEmail(rs.getString("user_email"));
						 user.setQuestion(rs.getInt("question"));
						 user.setAnswer(rs.getString("answer"));
						 user.setTrueName(rs.getString("user_truename"));
						 user.setProvince(rs.getString("province"));
						 user.setCity(rs.getString("city"));
						 user.setCountry(rs.getString("country"));
						 user.setAddress(rs.getString("user_address"));
						 user.setPostCode(rs.getString("post_code"));
						 user.setTel(rs.getString("user_tel"));
						 user.setStatus(rs.getInt("status"));
						flag=1; //��¼�ɹ� ���˺��Ѽ��
					}else{
						flag=2;//�˺�δ����
					}	
				}else{
					flag=3;//�����������
				}
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�û���¼ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return flag;
	}
	/*
	public UserInfo getUserbyName(String name){
		UserInfo user=null;
	
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
				 user=new UserInfo();
				 user.setId(rs.getInt("user_id"));
				 user.setName(rs.getString("user_name"));
				 user.setPwd(rs.getString("user_pwd"));
				 user.setGender(rs.getInt("user_gender"));
				 user.setEmail(rs.getString("user_email"));
				 user.setQuestion(rs.getInt("question"));
				 user.setAnswer(rs.getString("answer"));
				 user.setTrueName(rs.getString("user_truename"));
				 user.setProvince(rs.getString("province"));
				 user.setCity(rs.getString("city"));
				 user.setCountry(rs.getString("country"));
				 user.setAddress(rs.getString("user_address"));
				 user.setPostCode(rs.getString("post_code"));
				 user.setTel(rs.getString("user_tel"));
				 user.setStatus(rs.getInt("status"));
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�û���¼ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return user;
	}*/
}
