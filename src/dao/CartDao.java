package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import common.DBConnection;
import common.MyTools;
import domain.*;


public class CartDao {
	
	/**
	 * �жϸ��û��Ƿ񴴽��˹��ﳵ ����Ҫ
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
				 b=true; //���ڹ��ﳵ
			 }
			 rs.close();
			 pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("���ҹ��ﳵʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return b;
	}
/**
 * Ϊһ���û�����һ�����ﳵ (�ڵ�¼ʱ����)  ����Ҫ
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
			System.out.println("�������ﳵʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}

	
	 //����Ҫ
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
			System.out.println("���ҹ��ﳵʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return car;
	}
	
	
	/**
	 *���ﳵ�������Ʒ(������Ʒid��
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
			 if(rs.next()){ //��Ʒ�Ѵ���ʱ
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
			System.out.println("�����Ʒʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}

/**
 * ���ݹ��ﳵҳ�洫����ֵ�޸���Ӧ����Ʒ������Ŀ
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
			System.out.println("�޸���Ʒ����ʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
	/**
	 * ɾ�����ﳵ���һ����Ʒ
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
			System.out.println("ɾ����Ʒʧ��");
		}finally{
			DBConnection.closeConnection();
		}
		return count;
	}
	/**
	 * ͨ���û���id��ѯ���ﳵ ������ӵ�ʱ�� ��������
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
				goods.setGoods_num(rs.getInt("goods_num"));//�����
				goods.setStatus(rs.getInt("status"));
				goods.setImg_path(rs.getString("img_path"));
				goods.setGoods_num(rs.getInt("goods_num"));
				al.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ҹ��ﳵ��Ʒʧ��!");
		}
		return al;
	}

	/**
	  * ��չ��ﳵ
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
				System.out.println("ɾ�����ﳵʧ��");
			}finally{
				DBConnection.closeConnection();
			}
			return count;
	 }
	
}
