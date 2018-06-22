package dao;

import common.*;
import domain.*;

import java.sql.*;
import java.util.*;

public class AdminDao {
	private static final long serialVersionUID = -2071565876962058344L;
	private Connection con;
	public AdminDao() {
		con=DBConnection.getConnection();
	}
	/**
	 * 查询数据库中所有的用户信息
	 * @return
	 */
	public List<UserInfo> selectUser(){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT * from user_info";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("用户信息查询失败!");
		}
		return userlist;
	}
	
	/**
	 * 查询数据库user表的start行到end行
	 * @param start
	 * @param end
	 * @return
	 */
	public List<UserInfo> selectUser(int start,int end){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		System.out.println("selectUser输出：start="+start+"end="+end);
		try {
			String sql="SELECT * from user_info limit ?,?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, start);
			pStmt.setInt(2, end);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("用户信息查询失败!");
		}
		return userlist;
	}
	/**
	 * 后台：根据用户电话号码和用户激活状态查询用户
	 * @param tel
	 * @param status
	 * @return
	 */
	public List<UserInfo> selectUserByTel(String tel,int status){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_tel=? and status=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, tel);
			pStmt.setInt(2, status);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户电话和激活状态查询用户信息失败!");
		}
		return userlist;
	}
	
	/**
	 * 后台：根据用户电话号码找到用户
	 * @param tel
	 * @param status
	 * @return
	 */
	public List<UserInfo> selectUserByTel(String tel){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_tel=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, tel);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户电话查询用户信息失败!");
		}
		return userlist;
	}
	/**
	 * 后台：根据用户id和激活状态查找用户
	 * @param id
	 * @param status
	 * @return
	 */
	public List<UserInfo> selectUserById(int id,int status){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_id=? and status=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setInt(2, status);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户id和用户激活状态查询用户信息失败!");
		}
		return userlist;
	}
	public List<UserInfo> selectUserById(int id){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户id查询用户信息失败!");
		}
		return userlist;
	}
	
	/**
	 * 后台：根据用户昵称和激活状态查询用户
	 * @param name
	 * @param status
	 * @return
	 */
	public List<UserInfo> selectUserByName(String name,int status){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_name=? and status=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setInt(2, status);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户昵称和用户激活状态查询用户信息失败!");
		}
		return userlist;
	}
	public List<UserInfo> selectUserByName(String name){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where user_name=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, name);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户昵称查询用户信息失败!");
		}
		return userlist;
	}
	
	/**
	 * 根据用户激活状态查询用户
	 * @param status
	 * @return
	 */
	public List<UserInfo> selectUserByStatus(int status){
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		try {
			String sql="SELECT * from user_info where status=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, status);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				user=new UserInfo();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setGender(rs.getInt("user_gender"));
				user.setTrueName(rs.getString("user_truename"));
				user.setTel(rs.getString("user_tel"));
				user.setEmail(rs.getString("user_email"));
				user.setStatus(rs.getInt("status"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户激活状态查询用户信息失败!");
		}
		return userlist;
	}
	/**
	 * 根据用户id删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteUser(int id) {
		int count=0;
		System.out.println("deleteUser:输出带删除用户id:"+id);
		try {
			Statement stm=con.createStatement();
			String sql="delete from user_info where user_id="+id;
			count=stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除用户失败!");
		}
		return count;
	}
	/**
	 * 管理员对用户的修改操作
	 * 修改用户密码，激活状态
	 * @param user
	 * @return
	 */
	public int backUpdateUserInfo(UserInfo user) {
		int count=0;
		try {
			String sql="update user_info set user_pwd=?,status=? where user_id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, user.getPwd());
			pStmt.setInt(2, user.getStatus());
			pStmt.setInt(3, user.getId());
			count=pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("管理员修改用户信息失败!");
		}
		return count;
	}
	
	/**
	 * 查询所有的管理员信息
	 * @return
	 */
	public List<AdminInfo> selectAdmin(){
		List<AdminInfo> adminlist=new ArrayList<AdminInfo>();
		AdminInfo admin=null;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT * from admin_info";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()) {
				admin=new AdminInfo();
				admin.setId(rs.getInt("id"));
				admin.setAdmin_name(rs.getString("admin_name"));
				admin.setAdmin_email(rs.getString("admin_email"));
				admin.setAdmin_pwd(rs.getString("admin_pwd"));
				admin.setAdmin_tel(rs.getString("admin_tel"));
				adminlist.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("管理员信息查询失败!");
		}
		return adminlist;

	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	public int updateAdminInfo(AdminInfo admin) {
		int count=0;
		try {
			String sql="update admin_info set admin_name=?,admin_pwd=?,admin_tel=?,admin_email=? where id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, admin.getAdmin_name());
			pStmt.setString(2, admin.getAdmin_pwd());
			pStmt.setString(3, admin.getAdmin_tel());
			pStmt.setString(4, admin.getAdmin_email());
			pStmt.setInt(5, admin.getId());
			count=pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改管理员信息失败!");
		}
		return count;
	}
	
	/**
	 * 根据电话号码查找管理员
	 * @param tel
	 * @return
	 */
	public List<AdminInfo> selectAdminByTel(String tel){
		List<AdminInfo> adminlist=new ArrayList<AdminInfo>();
		AdminInfo admin=null;
		try {
			String sql="SELECT * from admin_info where admin_tel=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, tel);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				admin=new AdminInfo();
				admin.setId(rs.getInt("id"));
				admin.setAdmin_name(rs.getString("admin_name"));
				admin.setAdmin_email(rs.getString("admin_email"));
				admin.setAdmin_pwd(rs.getString("admin_pwd"));
				admin.setAdmin_tel(rs.getString("admin_tel"));
				adminlist.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据用户电话查询管理员信息失败!");
		}
		return adminlist;
	}
	
	/**
	 * 根据管理员id查找管理员
	 * @param id
	 * @return
	 */
	public List<AdminInfo> selectAdminById(int id){
		List<AdminInfo> adminlist=new ArrayList<AdminInfo>();
		AdminInfo admin=null;
		try {
			String sql="SELECT * from admin_info where id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				admin=new AdminInfo();
				admin.setId(rs.getInt("id"));
				admin.setAdmin_name(rs.getString("admin_name"));
				admin.setAdmin_email(rs.getString("admin_email"));
				admin.setAdmin_pwd(rs.getString("admin_pwd"));
				admin.setAdmin_tel(rs.getString("admin_tel"));
				adminlist.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据id查询管理员信息失败!");
		}
		return adminlist;
	}
	
	/**
	 * 根据管理员姓名查询管理员
	 * @param name
	 * @return
	 */
	public List<AdminInfo> selectAdminByName(String name){
		List<AdminInfo> adminlist=new ArrayList<AdminInfo>();
		AdminInfo admin=null;
		try {
			String sql="SELECT * from admin_info where admin_name=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, name);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				admin=new AdminInfo();
				admin.setId(rs.getInt("id"));
				admin.setAdmin_name(rs.getString("admin_name"));
				admin.setAdmin_email(rs.getString("admin_email"));
				admin.setAdmin_pwd(rs.getString("admin_pwd"));
				admin.setAdmin_tel(rs.getString("admin_tel"));
				adminlist.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("根据id查询管理员信息失败!");
		}
		return adminlist;
	}
	public void close() {
		DBConnection.closeConnection();
	}
}
