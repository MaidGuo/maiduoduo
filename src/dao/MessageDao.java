package dao;

import domain.MessageReplyInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;
import domain.MessageInfo;
import domain.UserInfo;

public class MessageDao {
	private Connection con = null;

	/* 寰楀埌鍏ㄩ儴鐨勭暀瑷� */
	public ArrayList<MessageInfo> getAllMessage() {
		ArrayList<MessageInfo> messagelist = new ArrayList<MessageInfo>();
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from message_info");
			while (rs.next()) {
				MessageInfo messageInfo = new MessageInfo();
				messageInfo.setMessageid(rs.getInt("message_id"));
				messageInfo.setUserid(rs.getInt("user_id"));
				messageInfo.setTheme(rs.getString("message_theme"));
				messageInfo.setContent(rs.getString("message_content"));
				messageInfo.setTime(rs.getString("message_time"));
				messageInfo.setIsReply(rs.getInt("isreply"));
				messagelist.add(messageInfo);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("鑾峰彇鍏ㄩ儴鐣欒█閿欒锛�");
		} finally {
			DBConnection.closeConnection();
		}
		return messagelist;
	}
	
	//寰楀埌鎸囧畾鐨勭暀瑷�
	public MessageInfo getMessage(int message_id) {
		MessageInfo messageInfo = new MessageInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("select * from message_info where message_id=?");
			ps.setInt(1, message_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				messageInfo.setMessageid(rs.getInt("message_id"));
				messageInfo.setUserid(rs.getInt("user_id"));
				messageInfo.setTheme(rs.getString("message_theme"));
				messageInfo.setContent(rs.getString("message_content"));
				messageInfo.setTime(rs.getString("message_time"));
				messageInfo.setIsReply(rs.getInt("isreply"));
			} else {
				System.out.println("娣诲姞杩汭nfo閿欒锛�");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("鑾峰彇鎸囧畾鐣欒█閿欒锛�");
		} finally {
			DBConnection.closeConnection();
		}
		return messageInfo;
	}

	//寰楀埌鐣欒█浜轰俊鎭�
	public UserInfo getUser(int user_id) {
		UserInfo userInfo = new UserInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("select * from user_info where user_id=?");
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				userInfo.setId(rs.getInt("user_id"));
				userInfo.setAnswer(rs.getString("answer"));
				userInfo.setCity(rs.getString("city"));
				userInfo.setCountry(rs.getString("country"));
				//userInfo.setRegisterTime(rs.getString("created_time"));
				userInfo.setPostCode(rs.getString("post_code"));
				userInfo.setProvince(rs.getString("province"));
				userInfo.setQuestion(rs.getInt("question"));
				userInfo.setStatus(rs.getInt("status"));
				userInfo.setAddress(rs.getString("user_address"));
				userInfo.setEmail(rs.getString("user_email"));
				userInfo.setGender(rs.getInt("user_gender"));
				userInfo.setName(rs.getString("user_name"));
				userInfo.setPwd(rs.getString("user_pwd"));
				userInfo.setTel(rs.getString("user_tel"));
				userInfo.setTrueName(rs.getString("user_truename"));
			} else {
				System.out.println("娣诲姞杩汭nfo閿欒锛�");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("鑾峰彇鎸囧畾鐣欒█浜轰俊鎭敊璇紒");
		} finally {
			DBConnection.closeConnection();
		}
		return userInfo;
	}
	
	//寰楀埌鐣欒█浜虹殑鏄电О
		public String getUser_Name(int user_id) {
			PreparedStatement ps = null;
			String user_name = "";
			ResultSet rs = null;
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement("select * from user_info where user_id=?");
				ps.setInt(1, user_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					user_name = rs.getString("user_name");
				} else {
					System.out.println("鑾峰彇user_name閿欒锛�");
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("鑾峰彇鎸囧畾鐣欒█浜烘樀绉伴敊璇紒");
			} finally {
				DBConnection.closeConnection();
			}
			return user_name;
		}

	// 淇敼鎸囧畾鐨勭暀瑷�鍥炲鎯呭喌
	public int getEditReply(MessageInfo messageInfo) {
		int count = 0;
		PreparedStatement ps = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("update message_info set user_id=?, message_theme=?, message_content=?, message_time=?, isreply=? where message_id=?");
			ps.setInt(1, messageInfo.getUserid());
			ps.setString(2, messageInfo.getTheme());
			ps.setString(3, messageInfo.getContent());
			ps.setString(4, messageInfo.getTime());
			ps.setInt(5, messageInfo.getIsReply());
			ps.setInt(6, messageInfo.getMessageid());
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("淇敼鐣欒█鍥炲澶辫触锛�");
		} finally {
			DBConnection.closeConnection();
		}

		return count;
	}
	
	//鏌ョ湅鍥炲琛ㄧ殑澶у皬
		public ArrayList<MessageReplyInfo> getAllReply() {
			ArrayList<MessageReplyInfo> messagelist = new ArrayList<MessageReplyInfo>();
			Statement st = null;
			ResultSet rs = null;
			
			try {
				con = DBConnection.getConnection();
				st = con.createStatement();
				rs = st.executeQuery("select * from message_reply");
				while (rs.next()) {
					MessageReplyInfo message_reply = new MessageReplyInfo();
					message_reply.setId(rs.getInt("id"));
					message_reply.setMessage_id(rs.getInt("message_id"));
					message_reply.setContent(rs.getString("reply_content"));
					message_reply.setTime(rs.getString("reply_time"));
					messagelist.add(message_reply);
				}
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("鏌ョ湅鍥炲澶辫触锛�");
			} finally {
				DBConnection.closeConnection();
			}

			return messagelist;
		}

	 //娣诲姞鏂扮殑鍥炲
	public int getInsertReply(MessageReplyInfo messageReply) {
		int count = 0;
		PreparedStatement ps = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("insert into message_reply (id, message_id, reply_content, reply_time) values (?, ?, ?, ?)");
			ps.setInt(1, messageReply.getId());
			ps.setInt(2, messageReply.getMessage_id());
			ps.setString(3, messageReply.getContent());
			ps.setString(4, messageReply.getTime());
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("娣诲姞鍥炲澶辫触锛�");
		} finally {
			DBConnection.closeConnection();
		}

		return count;
	}
	
	// 鍒犻櫎鎸囧畾鐨勫叕鍛�
	public void getDeletNotice(int notice_id) {
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("delete from system_notice where notice_id=?");
			ps.setInt(1, notice_id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
	}
	
	//寰楀埌鍥炲鐨勫唴瀹�
			public String getReplyContent(int message_id) {
				PreparedStatement ps = null;
				String reply_content = "";
				ResultSet rs = null;
				try {
					con = DBConnection.getConnection();
					ps = con.prepareStatement("select * from message_reply where message_id=?");
					ps.setInt(1, message_id);
					rs = ps.executeQuery();
					if (rs.next()) {
						reply_content = rs.getString("reply_content");
					} else {
						System.out.println("鑾峰彇reply_content閿欒锛�");
					}
					rs.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("鑾峰彇鍥炲閿欒锛�");
				} finally {
					DBConnection.closeConnection();
				}
				return reply_content;
			}
}
