package dao;

import common.*;
import domain.*;

import java.sql.*;
import java.util.*;

public class UserDao {
	private Connection con;
	public UserDao() {
		con=DBConnection.getConnection();
	}
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	public UserInfo selectUser(int id) {
		UserInfo user=null;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT * from user_info where user_id="+id;
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()) {
				user=new UserInfo();
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_pwd"));
				user.setAddress(rs.getString("user_address"));
				user.setCity(rs.getString("city"));
				user.setCountry(rs.getString("country"));
				user.setEmail(rs.getString("user_email"));
				user.setProvince(rs.getString("province"));
				user.setGender(rs.getInt("user_gender"));
				user.setTel(rs.getString("user_tel"));
				user.setTrueName(rs.getString("user_truename"));
				user.setId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据查询失败!");
		}
		return user;
	}
	
	
	/**
	 * 根据用户id找到用户登录密码，及密保问题
	 * @param id
	 * @return
	 */
	public UserInfo selectPassword(int id) {
		UserInfo user=null;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT user_pwd,question,answer from user_info where user_id="+id;
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()) {
				user=new UserInfo();
				user.setPwd(rs.getString("user_pwd"));
				user.setQuestion(rs.getInt("question"));
				user.setAnswer(rs.getString("answer"));
				user.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据查询失败!");
		}
		return user;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int UpdatePassword(UserInfo user) {
		int count=0;
		try {
			String sql="update user_info set user_pwd=? where user_id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, user.getPwd());
			pStmt.setInt(2, user.getId());
			count=pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改密码失败!");
		}
		return count;
	}
	/**
	 * 修改用户基本信息，并返回修改成功记录的条数
	 * @param user
	 * @return
	 */
	public int updateUserInfo(UserInfo user) {
		int count=0;
		
		try {
			String sql="update user_info set user_name=?,user_gender=?,user_email=?,user_truename=?,province=?,city=?,country=?,user_address=?,user_tel=? where user_id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setInt(2, user.getGender());
			pStmt.setString(3, user.getEmail());
			pStmt.setString(4, user.getTrueName());
			pStmt.setString(5, user.getProvince());
			pStmt.setString(6, user.getCity());
			pStmt.setString(7, user.getCountry());
			pStmt.setString(8, user.getAddress());
			pStmt.setString(9, user.getTel());
			pStmt.setInt(10, user.getId());
			count=pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改数据失败!");
		}
		return count;
	}
	
	/**
	 * 根据用户id查找用户所有的评论信息
	 * @param id
	 * @return
	 */
	public List<CommentInfo> selectReview(int id){
		List<CommentInfo> reviewlist=new ArrayList<CommentInfo>();
		CommentInfo review;
		try {
			String sql="select * from goods_comment where user_id="+id;
			PreparedStatement pStmt=con.prepareStatement(sql);
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				review=new CommentInfo();
				review.setComment_id(rs.getInt("comment_id"));;
				review.setComment_content(rs.getString("comment_content"));;
				review.setComment_theme(rs.getString("comment_theme"));;
				review.setComment_time(rs.getString("comment_time"));;
				int goodsid,userid;
				goodsid=rs.getInt("goods_id");
				userid=rs.getInt("user_id");	
				UserInfo user=selectUser(userid);
				GoodsInfo goods=selectGoods(goodsid);
				review.setUser(user);
				review.setGoods(goods);
				reviewlist.add(review);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找用户评论信息失败!");
		}
		return reviewlist;
	}
	
	/**
	 * 查找商品信息
	 * @param id
	 * @return
	 */
	public GoodsInfo selectGoods(int id) {
		GoodsInfo goods=null;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT * from goods where goods_id="+id;
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()) {
				goods=new GoodsInfo();
				goods.setComment_times(rs.getInt("comment_times"));
				goods.setGoods_description(rs.getString("goods_description"));
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setGoods_num(rs.getInt("goods_num"));
				goods.setGoods_price(rs.getDouble("goods_price"));
				goods.setPurchase_times(rs.getInt("purchase_times"));
				goods.setStatus(rs.getInt("status"));
				goods.setT_classify_id(rs.getInt("t_classify_id"));
				int goodsid=rs.getInt("goods_id");
				sql="select img_path from goods_img where goods_id="+goodsid;
				rs=stm.executeQuery(sql);
				if(rs.next()) {
					goods.setImg_path(rs.getString("img_path"));;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("商品信息查询失败!");
		}
		return goods;
	}
	/**
	 * 根据用户id查找用户收货地址
	 * @param id
	 * @return
	 */
	public List<ReceiveAddress> selectAddress(int id) {
		List<ReceiveAddress> addresslist=new ArrayList<ReceiveAddress>();
		ReceiveAddress address;
		try {
			//id错误
			String sql="select * from delivery_address where user_id="+id;
			PreparedStatement pStmt=con.prepareStatement(sql);
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				address=new ReceiveAddress();
				address.setAddressId(rs.getInt("address_id"));
				address.setAddress(rs.getString("receiver_address"));
				address.setCity(rs.getString("city"));
				address.setCounty(rs.getString("country"));
				address.setIsdefault(rs.getInt("isdefault"));
				address.setName(rs.getString("receiver_name"));
				address.setPostcode(rs.getString("post_code"));
				address.setProvince(rs.getString("province"));
				address.setTel(rs.getString("receiver_tel"));
				address.setUserId(rs.getInt("user_id"));
				addresslist.add(address);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找地址失败!");
		}
		return addresslist;
	}
	public int insertReview(CommentInfo review) {
		int count=0;
		try {
			String sql= "insert into goods_comment(goods_id,user_id,comment_theme,comment_content,comment_time) values (?,?,?,?,?)";  
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, review.getGoods().getGoods_id());
			pStmt.setInt(2, review.getUser().getId());
			pStmt.setString(3, review.getComment_theme());
			pStmt.setString(4, review.getComment_content());
			pStmt.setString(5, review.getComment_time());
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("新增评论失败！");
		}
		int flag=0;
		if(count==1) {
			flag=updateGoodsTimes(review.getGoods());
			if(flag==0) {
				count=0;
			}
		}
		
		return count;
	}
	
	public int updateGoodsTimes(GoodsInfo goods) {
		int count=0;
		try {
			String sql="update goods set comment_times=? where goods_id=?";
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, goods.getComment_times()+1);
			pStmt.setInt(2,goods.getGoods_id());
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("新增评论次数失败！");
		}
		return count;
	}
	/**
	 * 根据地址id更新指定地址
	 * @param addid
	 * @return
	 */
	public int updateAddress(ReceiveAddress address) {
		int count=0;
		try {
			if(address.getIsdefault()==1) { //如果该地址被设为默认地址，则将其他默认地址改为非默认
				String sql1="update delivery_address set isdefault=? where isdefault=?";
				PreparedStatement pStmt1=con.prepareStatement(sql1);
				pStmt1.setInt(1, 0); //设为非默认地址
				pStmt1.setInt(2, address.getIsdefault());
				pStmt1.executeUpdate();
			}
			String sql="update delivery_address set receiver_name=?,receiver_tel=?,post_code=?,province=?,city=?,country=?,receiver_address=?,isdefault=? where address_id=?";  
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setString(1, address.getName());
			pStmt.setString(2, address.getTel());
			pStmt.setString(3, address.getPostcode());
			pStmt.setString(4, address.getProvince());
			pStmt.setString(5, address.getCity());
			pStmt.setString(6, address.getCounty());
			pStmt.setString(7, address.getAddress());
			pStmt.setInt(8, address.getIsdefault());
			pStmt.setInt(9, address.getAddressId());
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("修改地址失败！");
		}
		return count;
	}
	
	/**
	 * 新增一个地址
	 * @param address
	 * @return
	 */
	public int insertAddress(ReceiveAddress address) {
		int count=0;
		try {
			if(address.getIsdefault()==1) { //如果该地址被设为默认地址，则将其他默认地址改为非默认
				String sql1="update delivery_address set isdefault=? where isdefault=?";
				PreparedStatement pStmt1=con.prepareStatement(sql1);
				pStmt1.setInt(1, 0); //设为非默认地址
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
			System.out.println("新增地址失败！");
		}  
		return count;
	}
	
	/**
	 * 根据地址id删除地址
	 * @param addressid
	 * @return
	 */
	public int deleteAddress(int addressid) {
		int count=0;
		try {
			String sql= "delete from delivery_address where address_id=?";  
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, addressid);
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("删除地址失败！");
		} 
		return count;
	}
	
	/**
	 * 根据用户id查找留言信息
	 * @param id
	 * @return
	 */
	public List<MessageInfo> selectMessage(int id){
		List<MessageInfo> messagelist=new ArrayList<MessageInfo>();
		MessageInfo message;
		List<MessageReplyInfo> replylist;
		UserInfo user;
		try {
			String sql="select * from message_info where user_id="+id;
			PreparedStatement pStmt=con.prepareStatement(sql);
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				message=new MessageInfo();
				message.setContent(rs.getString("message_content"));
				message.setIsReply(rs.getInt("isreply"));
				message.setMessageid(rs.getInt("message_id"));
				message.setTheme(rs.getString("message_theme"));
				message.setUserid(rs.getInt("user_id"));
				message.setTime(rs.getString("message_time"));
				replylist=selectMessageReply(rs.getInt("message_id"));
				message.setReplylist(replylist);;
				messagelist.add(message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找留言失败!");
		}
		return messagelist;
	}
	
	/**
	 * 根据留言id找到其回复信息
	 * @param id
	 * @return
	 */
	public List<MessageReplyInfo> selectMessageReply(int id) {
		List<MessageReplyInfo> replylist=new ArrayList<MessageReplyInfo>();
		MessageReplyInfo reply=null;;
		try {
			Statement stm=con.createStatement();
			String sql="SELECT * from message_reply where message_id="+id;
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()) {
				reply=new MessageReplyInfo();
				reply.setContent(rs.getString("reply_content"));
				reply.setId(rs.getInt("id"));
				reply.setTime(rs.getString("reply_time"));
				replylist.add(reply);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("留言回复信息查询失败!");
		}
		return replylist;
	}
	
	public int insertMessage(MessageInfo message) {
		int count=0;
		System.out.println("insertMessage:输出用户id:"+message.getUserid());
		try {
			String sql= "insert into message_info(user_id,message_theme,message_content,message_time,isReply) values (?,?,?,?,?)";  
			PreparedStatement pStmt=con.prepareStatement(sql);
			pStmt.setInt(1, message.getUserid());
			pStmt.setString(2, message.getTheme());
			pStmt.setString(3, message.getContent());
			pStmt.setString(4, message.getTime());
			pStmt.setInt(5, 0);
			count=pStmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("新增留言失败！");
		}
		return count;
	}
	
	public void close() {
		DBConnection.closeConnection();
	}
}
