package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import common.DBConnection;
import common.MyTools;
import domain.*;


public class CartDao {
	
	/**
	 * 判断该用户是否创建了购物车 不需要
	 * @param id
	 * @return
	 */
	Connection con=null;
	public boolean isHasCart(int id){
		boolean b=false;
		PreparedStatement pStmt= null;
		ResultSet rs = null;
	
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM shopping_cart where user_id= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1,id); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){  
				 b=true; //存在购物车
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("查找购物车失败");
		}finally{
			DBConnection.closeConnection();
		}
		return b;
	}
/**
 * 为一个用户创建一个购物车 (在登录时创建)  不需要
 * @param id
 * @return
 */
	public int creatCart(int id){
		PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="Insert into shopping_cart (user_id) values(?) "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, id); 
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("创建购物车失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}

	
	 //不需要
	public ShoppingCartInfo getCartInfobyUser(int id){
		PreparedStatement pStmt= null;
		ShoppingCartInfo car=null;
		ResultSet rs=null;
		try {
			con = DBConnection.getConnection();
			 String sql ="SELECT * FROM shopping_cart where user_id= ? "; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, id); 
			 rs=pStmt.executeQuery();
			 if(rs.next()){
				 car=new ShoppingCartInfo();
				 car.setCart_id(rs.getInt("cart_id"));
				 car.setUser_id(id);
				 car.setStatus(rs.getInt("status"));
			 }
			
		rs.close();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("查找购物车失败");
		}finally{
			DBConnection.closeConnection();
		}
		return car;
	}
	
	
	/**
	 *向购物车中添加商品(根据商品id）
	 * @param goods
	 * @return
	 */
	public int addGoods(GoodsInfo goods,int userId){
		PreparedStatement pStmt= null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			st=con.createStatement();
			 String sql="select num from shopping_cart where user_id="+userId+" and goods_id="+goods.getGoods_id();
			 String sql2 ="update shopping_cart set num=?,created_time=? where user_id=? and goods_id=?";
			 rs=st.executeQuery(sql);
			 if(rs.next()){ //商品已存在时
				 int n=rs.getInt("num");
				pStmt=con.prepareStatement(sql2);
				pStmt.setInt(1,goods.getBuyNum()+n);
				pStmt.setString(2, MyTools.transDateToString(goods.getAddToCartTime()));
				pStmt.setInt(3, userId);
				pStmt.setInt(4, goods.getGoods_id());
				count=pStmt.executeUpdate();
			 }else{
			 sql="insert into shopping_cart (goods_id,created_time,num,user_id) values(?,?,?,?)"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, goods.getGoods_id()); 
			 pStmt.setString(2, MyTools.transDateToString(goods.getAddToCartTime()));
			 pStmt.setInt(3, goods.getBuyNum());
			 pStmt.setInt(4,userId);
			 count=pStmt.executeUpdate();
			 }
			 rs.close();
			 st.close();
			 pStmt.close();
			 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("添加商品失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}

/**
 * 根据购物车页面传来的值修改相应的商品购买数目
 * @param goods
 * @param userId
 * @return
 */
	public int UpdateNumsCar(GoodsInfo goods,int userId){
		PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="UPDATE shopping_cart set num =?,created_time=?  where user_id=? AND goods_id=?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, goods.getBuyNum());
			 pStmt.setString(2, MyTools.transDateToString(goods.getAddToCartTime()));
			 pStmt.setInt(3,userId);
			 pStmt.setInt(4,goods.getGoods_id());	
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("修改商品数量失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
	/**
	 * 删除购物车里的一件商品
	 * @param goodsId
	 * @return
	 */
	public int deleteGoodsbyId(int goodsId,int userId){
		PreparedStatement pStmt= null;
		int count=0;
		try {
			con = DBConnection.getConnection();
			 String sql ="delete from shopping_cart where goods_id=? and user_id=?"; 
			 pStmt=con.prepareStatement(sql);
			 pStmt.setInt(1, goodsId);
			 pStmt.setInt(2, userId);
			 
			 count=pStmt.executeUpdate();
		pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("删除商品失败");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
	/**
	 * 通过用户的id查询购物车 按照添加的时间 降序排列
	 * @param uid
	 * @return
	 */
	public ArrayList<GoodsInfo> getShoppingCar(int userId){
		ArrayList<GoodsInfo> al = new ArrayList<GoodsInfo>();
		PreparedStatement pStmt= null;
		ResultSet rs = null;
		GoodsInfo goods=null;
		try {
			con = DBConnection.getConnection();
			
			String sql = "SELECT c.goods_id,c.num,a.goods_name,a.goods_price,a.goods_num,a.status,b.img_path FROM shopping_cart c INNER JOIN goods a ON c.goods_id=a.goods_id INNER JOIN goods_img b ON c.goods_id=b.goods_id WHERE user_id=? ORDER BY c.created_time DESC";
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			rs = pStmt.executeQuery();
			while(rs.next()){
				goods=new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setBuyNum(rs.getInt("num"));
				System.out.println(rs.getInt("num")+" id: "+rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setGoods_price(rs.getDouble("goods_price"));
				goods.setGoods_num(rs.getInt("goods_num"));//库存量
				goods.setStatus(rs.getInt("status"));
				goods.setImg_path(rs.getString("img_path"));
				goods.setGoods_num(rs.getInt("goods_num"));
				al.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查找购物车商品失败!");
		}
		return al;
	}

	/**
	  * 清空购物车
	  * @param carList
	  * @return
	  */
	 public int deleteCartList(int userId){
		 PreparedStatement pStmt= null;
			int count=0;
			try {
				con = DBConnection.getConnection();
				 String sql ="delete from shopping_cart where user_id =?"; 
				 pStmt=con.prepareStatement(sql);
				 pStmt.setInt(1, userId);
				 count=pStmt.executeUpdate();
			pStmt.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("删除购物车失败");
			}finally{
				DBConnection.closeConnection();
			}
			return count;
	 }
	
}
