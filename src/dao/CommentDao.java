package dao;

import java.sql.*;
import java.util.ArrayList;

import common.DBConnection;
import domain.CommentInfo;
import domain.GoodsImgInfo;
import domain.UserInfo;

public class CommentDao {
	private Connection con = null;

	/* 鑾峰緱鎵�鏈夎瘎璁轰俊鎭� */
	public ArrayList<CommentInfo> getAllComment() {
		ArrayList<CommentInfo> commentlist = new ArrayList<CommentInfo>();
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from goods_comment");
			while (rs.next()) {
				CommentInfo commentInfo = new CommentInfo();
				commentInfo.setComment_id(rs.getInt("comment_id"));
				commentInfo.setGoods_id(rs.getInt("goods_id"));
				commentInfo.setUser_id(rs.getInt("user_id"));
				commentInfo.setComment_theme(rs.getString("comment_theme"));
				commentInfo.setComment_content(rs.getString("comment_content"));
				commentInfo.setComment_time(rs.getString("comment_time"));
				commentlist.add(commentInfo);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("鑾峰彇鍏ㄩ儴璇勮閿欒锛�");
		} finally {
			DBConnection.closeConnection();
		}
		return commentlist;
	}

	/*鑾峰緱鎸囧畾鍟嗗搧鐨勫浘鐗囪矾寰�*/
	public GoodsImgInfo getGoodsImag(int good_id) {
		String str = null;
		GoodsImgInfo goodsImagInfo = new GoodsImgInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("select * from goods_img where goods_id=?");
			ps.setInt(1, good_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				goodsImagInfo.setGoods_id(rs.getInt("goods_id"));
				goodsImagInfo.setImg_id(rs.getInt("img_id"));
				goodsImagInfo.setImg_path(rs.getString("img_path"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
		
		return goodsImagInfo;
	}
	
	/*鑾峰緱鎸囧畾鐢ㄦ埛id鐨勭敤鎴峰悕*/
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
				//userInfo.setRegisterTime(new Date (rs.getString("created_time")));
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
	
	/* 鏍规嵁鎸囧畾瑕佹眰鏌ユ壘璇勮*/
	public ArrayList<CommentInfo> getComment(String begin_date, String end_date, String style) {
		ArrayList<CommentInfo> commentlist = new ArrayList<CommentInfo>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("select * from goods_comment where comment_time>=? and comment_time<=? and comment_theme=?");
			ps.setString(1, begin_date);
			ps.setString(2, end_date);
			ps.setString(3, style);
			rs = ps.executeQuery();
			while (rs.next()) {
				CommentInfo commentInfo = new CommentInfo();
				commentInfo.setComment_id(rs.getInt("comment_id"));
				commentInfo.setGoods_id(rs.getInt("goods_id"));
				commentInfo.setUser_id(rs.getInt("user_id"));
				commentInfo.setComment_theme(rs.getString("comment_theme"));
				commentInfo.setComment_content(rs.getString("comment_content"));
				commentInfo.setComment_time(rs.getString("comment_time"));
				commentlist.add(commentInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentlist;
	}
	
	/* 鍒犻櫎鎸囧畾鐨勮瘎璁�*/
	public void getDeletComment(int comment_id) {
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("delete from goods_comment where comment_id=?");
			ps.setInt(1, comment_id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
	}
}
