package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import common.DBConnection;
import common.MyTools;

import domain.AdminInfo;
import domain.UserInfo;

public class AdminLoginDao {
	/**
	 * 判断管理员是否登录成功
	 * @param user
	 * @return
	 */
	public int login(AdminInfo admin){
		int flag=0;
		Statement st=null;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM admin_info where admin_name='"+admin.getAdmin_name()+"'"; 
			 st=con.createStatement();
			 rs=st.executeQuery(sql);
			
			 if(rs.next()){
				 int id=rs.getInt("id");	
				if(admin.getAdmin_pwd().equals(rs.getString("admin_pwd"))){
					admin.setId(id);
					admin.setAdmin_tel(rs.getString("admin_tel"));
					admin.setAdmin_email(rs.getString("admin_email"));
					sql="insert into admin_login (login_time,admin_id) values(?,?) ";//插入登录时间记录
					pStmt=con.prepareStatement(sql);
					pStmt.setString(1,MyTools.transDateToString(new Date()));  
					pStmt.setInt(2,id);
					pStmt.executeUpdate();
					flag=1;//登录成功
					
				}
			 }
			 rs.close();
			 pStmt.close();
			 st.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("用户登录失败");
		}finally{
			DBConnection.closeConnection();
		}
		return flag;
	}
	/*
	public AdminInfo getUserbyName(String name){
		AdminInfo admin=null;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM admin_info where admin_name= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setString(1, name); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  
				 admin=new AdminInfo();
				 admin.setId(rs.getInt("id"));
				 admin.setAdmin_name(rs.getString("admin_name"));
				 admin.setAdmin_pwd(rs.getString("admin_pwd"));
				 admin.setAdmin_tel(rs.getString("admin_tel"));
				 admin.setAdmin_email(rs.getString("admin_email"));	
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("管理员登录失败");
		}finally{
			DBConnection.closeConnection();
		}
		return admin;
	}*/
	/**
	 * 获取该管理员的所有登录情况
	 * @param adminId
	 * @return
	 */
	public ArrayList<Date> getLoginTimebyId(int adminId){
		ArrayList<Date> loginTime=new ArrayList<Date>();
		Date lt=null;
		
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		Timestamp timestamp=null;
		Connection con=null;
		try {
			con = DBConnection.getConnection();
			
			 String sql ="SELECT * FROM admin_login where admin_id= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, adminId); 
			 rs=pStmt.executeQuery();
			while(rs.next()){  	
				timestamp=rs.getTimestamp("login_time");
				lt=new Date(timestamp.getTime());
				loginTime.add(lt);
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("获取管理员登录时间失败");
		}finally{
			DBConnection.closeConnection();
		}
		return loginTime;
	}
}
