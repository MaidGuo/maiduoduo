package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBConnection;
import domain.UserInfo;
/**
 * 对用户登录的数据库操作
 * @author Maid Guo
 *
 */
public class UserLoginDao {
	/**
	 * 判断用户是否登录成功
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
						 * 得到user的相关信息
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
						flag=1; //登录成功 （账号已激活）
					}else{
						flag=2;//账号未激活
					}	
				}else{
					flag=3;//密码输入错误
				}
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("用户登录失败");
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
			System.out.println("用户登录失败");
		}finally{
			DBConnection.closeConnection();
		}
		return user;
	}*/
}
