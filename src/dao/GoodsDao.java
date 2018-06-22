package dao;

import java.sql.*;
import java.util.ArrayList;

import common.DBConnection;

import domain.*;

public class GoodsDao {
//添加商品
	Connection con=null;
/**
 * 得到按销量从大到小排序的全部商品
 * @return
 */

	public ArrayList<GoodsInfo> getAllGoodsInfo() {
		ArrayList<GoodsInfo> goodsInfolist = new ArrayList<GoodsInfo>();
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from goods order by purchase_times desc");
			while (rs.next()) {
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setGoods_id(rs.getInt("goods_id"));
				goodsInfo.setGoods_name(rs.getString("goods_name"));
				goodsInfo.setPurchase_times(rs.getInt("purchase_times"));
				goodsInfolist.add(goodsInfo);
			}
			
			for (int i=0; i<goodsInfolist.size(); i++) {
				System.out.println(goodsInfolist.get(i).getPurchase_times());
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取全部商品错误！");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsInfolist;
	}
	
	

	/* 获取一条商品信息 */
	public GoodsInfo getGoodsbyId(int id){
		ResultSet rs = null;
		PreparedStatement pStmt=null;
		GoodsInfo goods = null;//商品
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("Select * from goods a,goods_img b where a.goods_id=b.goods_id and a.goods_id=?");
    		pStmt.setInt(1,id);	
    		rs = pStmt.executeQuery();
			//获取指定商品信息
			if(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//商品名称
				goods.setGoods_price(rs.getDouble("goods_price"));//商品价格(2位小数)
				goods.setGoods_description(rs.getString("goods_description"));//商品描述
				goods.setGoods_num(rs.getInt("goods_num"));//库存量
				goods.setPurchase_times(rs.getInt("purchase_times"));//购买次数
				goods.setComment_times(rs.getInt("comment_times"));//购买次数
				goods.setT_classify_id(rs.getInt("t_classify_id"));//三级分类编号
				goods.setStatus(rs.getInt("status"));//状态，0 下架 1 存在
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			}
			
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取指定商品信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return goods;
	}
	
	/* 获取所有商品信息*/
	public ArrayList<GoodsInfo> getAllGoods() {
		ResultSet rs = null;
		Statement sql = null;
		GoodsInfo goods = null;// 商品
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		
		try {
			con = DBConnection.getConnection();
			sql = con.createStatement();
			String s="select *"+
"from goods,thrid_classify,second_classify,first_classify,goods_img "+
"where goods.t_classify_id=thrid_classify.t_classify_id "+
"and thrid_classify.s_classify_id=second_classify.s_classify_id "+
"and second_classify.f_classify_id=first_classify.f_Classify_id "+
"and goods.goods_id=goods_img.goods_id";
			rs = sql.executeQuery(s);

			while(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//商品名称
				goods.setGoods_price(rs.getDouble("goods_price"));//商品价格(2位小数)
				goods.setGoods_description(rs.getString("goods_description"));//商品描述
				goods.setGoods_num(rs.getInt("goods_num"));//库存量
				goods.setPurchase_times(rs.getInt("purchase_times"));//购买次数
				goods.setComment_times(rs.getInt("comment_times"));//购买次数
				goods.setT_classify_id(rs.getInt("t_classify_id"));//三级分类编号
				goods.setStatus(rs.getInt("status"));//状态，0 下架 1 存在
				//classify
				goods.setT_Classify_name(rs.getString("t_Classify_name"));
				goods.setS_Classify_id(rs.getInt("s_Classify_id"));
				goods.setS_Classify_name(rs.getString("s_Classify_name"));
				goods.setF_Classify_id(rs.getInt("f_Classify_id"));
				goods.setF_Classify_name(rs.getString("f_Classify_name"));
				//img
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			
				goodsList.add(goods);// 添加一条记录
			}
			rs.close();
			sql.close();
		} catch (Exception e) {
			System.out.println("获取所有商品信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsList;
	}/* 获取所有商品信息 */
	
	
	/* 获取一条商品信息 */
	public GoodsInfo getOneGoods(int id){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsInfo goods = null;//商品
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM goods,goods_img "
+"where goods.goods_id=? and goods.goods_id=goods_img.goods_id;");
    		pStmt.setInt(1,id);	
    		rs = pStmt.executeQuery();
			//获取指定商品信息
			while(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//商品名称
				goods.setGoods_price(rs.getFloat("goods_price"));//商品价格(2位小数)
				goods.setGoods_description(rs.getString("goods_description"));//商品描述
				goods.setGoods_num(rs.getInt("goods_num"));//库存量
				goods.setPurchase_times(rs.getInt("purchase_times"));//购买次数
				goods.setComment_times(rs.getInt("comment_times"));//购买次数
				goods.setT_classify_id(rs.getInt("t_classify_id"));//三级分类编号
				goods.setStatus(rs.getInt("status"));//状态，0 下架 1 存在
				//图片
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取指定商品信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return goods;
	}/* 获取一条商品信息 */
	
	//获取3级类别所有商品
	public ArrayList<GoodsInfo> getGoodsByTid(int tid) {
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsInfo goods = new GoodsInfo();//商品
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("select *"+
					"from goods,thrid_classify,second_classify,first_classify,goods_img "+
					"where goods.t_classify_id=? "+
					"and goods.t_classify_id=thrid_classify.t_classify_id "+
					"and thrid_classify.s_classify_id=second_classify.s_classify_id "+
					"and second_classify.f_classify_id=first_classify.f_Classify_id "+
					"and goods.goods_id=goods_img.goods_id");
    		pStmt.setInt(1,tid);	
    		rs = pStmt.executeQuery();
			while(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//商品名称
				goods.setGoods_price(rs.getFloat("goods_price"));//商品价格(2位小数)
				goods.setGoods_description(rs.getString("goods_description"));//商品描述
				goods.setGoods_num(rs.getInt("goods_num"));//库存量
				goods.setPurchase_times(rs.getInt("purchase_times"));//购买次数
				goods.setComment_times(rs.getInt("comment_times"));//购买次数
				goods.setT_classify_id(rs.getInt("t_classify_id"));//三级分类编号
				goods.setStatus(rs.getInt("status"));//状态，0 下架 1 存在
				//classify
				goods.setT_Classify_name(rs.getString("t_Classify_name"));
				goods.setS_Classify_id(rs.getInt("s_Classify_id"));
				goods.setS_Classify_name(rs.getString("s_Classify_name"));
				goods.setF_Classify_id(rs.getInt("f_Classify_id"));
				goods.setF_Classify_name(rs.getString("f_Classify_name"));
				//img
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			
				goodsList.add(goods);// 添加一条记录
			}
		
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取3级类别所有商品信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsList;
	}/* 获取3级类别所有商品信息 */

	//编辑状态
	public int modifyGoodsStatus(int id,int status) {
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement( "update goods set status=? where goods_id=?");
    		pStmt.setInt(1,status);			    		
    		pStmt.setInt(2,id);		
    		count=pStmt.executeUpdate();
			pStmt.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("修改商品状态失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
	}
	
	//查找商品
	public ArrayList<GoodsInfo> findGoods(String str) {
			ResultSet rs = null;
			PreparedStatement pStmt=null; 
			GoodsInfo goods = new GoodsInfo();//商品
			ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
			try {
				
				con = DBConnection.getConnection();
				pStmt = con.prepareStatement("select * from goods,thrid_classify,second_classify,first_classify,"+
"goods_img where concat(thrid_classify.t_classify_name,goods.goods_name,second_classify.s_classify_name,first_classify.f_classify_name) LIKE '%%"+str+"%%'"+ 
"and goods.t_classify_id=thrid_classify.t_classify_id "+
"and thrid_classify.s_classify_id=second_classify.s_classify_id "+
"and second_classify.f_classify_id=first_classify.f_Classify_id "+
"and goods.goods_id=goods_img.goods_id");
				
	    		rs = pStmt.executeQuery();
				while(rs.next()) {
					goods = new GoodsInfo();
					goods.setGoods_id(rs.getInt("goods_id"));
					goods.setGoods_name(rs.getString("goods_name"));//商品名称
					goods.setGoods_price(rs.getFloat("goods_price"));//商品价格(2位小数)
					goods.setGoods_description(rs.getString("goods_description"));//商品描述
					goods.setGoods_num(rs.getInt("goods_num"));//库存量
					goods.setPurchase_times(rs.getInt("purchase_times"));//购买次数
					goods.setComment_times(rs.getInt("comment_times"));//购买次数
					goods.setT_classify_id(rs.getInt("t_classify_id"));//三级分类编号
					goods.setStatus(rs.getInt("status"));//状态，0 下架 1 存在
					//classify
					goods.setT_Classify_name(rs.getString("t_Classify_name"));
					goods.setS_Classify_id(rs.getInt("s_Classify_id"));
					goods.setS_Classify_name(rs.getString("s_Classify_name"));
					goods.setF_Classify_id(rs.getInt("f_Classify_id"));
					goods.setF_Classify_name(rs.getString("f_Classify_name"));
					//img
					goods.setImg_id(rs.getInt("img_id"));
					goods.setImg_path(rs.getString("img_path"));
				
					goodsList.add(goods);// 添加一条记录
				}
				rs.close();
				pStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("获取查找商品信息失败！");
			} finally {
				DBConnection.closeConnection();
			}
			return goodsList;
		}/* 获取查找商品信息 */
	
	// 添加一个商品
	public int insertGoods(GoodsInfo goods) {
		PreparedStatement pStmt = null;//goods
		int count = 0;
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("insert into goods(goods_name,goods_price,"
			+ "goods_description,goods_num,purchase_times,comment_times,t_classify_id,"
			+ "status) values(?,?,?,?,?,?,?,?)");
			
			pStmt.setString(1, goods.getGoods_name());
			pStmt.setDouble(2, goods.getGoods_price());
			pStmt.setString(3, goods.getGoods_description());
			pStmt.setInt(4, goods.getGoods_num());
			pStmt.setInt(5, goods.getPurchase_times());
			pStmt.setInt(6, goods.getComment_times());
			pStmt.setInt(7, goods.getT_classify_id());// 、、、、、、、、、
			pStmt.setInt(8, goods.getStatus());

			count = pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("在添加商品失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	//获取最后一个ID
	public int getNewGoodsID(){
		ResultSet rs = null;
		Statement sql = null;
		int id=0;
		
		try {
			con = DBConnection.getConnection();
			sql = con.createStatement();
			String s="select * from goods";
			rs = sql.executeQuery(s);

			while(rs.next()) {
				id=rs.getInt("goods_id");
			}
			rs.close();
			sql.close();
		} catch (Exception e) {
			System.out.println("获取最近id信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return id;
		}/* 获取一条商品信息 */
	
	//在goods添加一个商品
	public GoodsInfo insertOneGoods(GoodsInfo goods){
	PreparedStatement pStmt=null;
	GoodsInfo g=new GoodsInfo();//添加
    try {
    	con=DBConnection.getConnection();
		pStmt = con.prepareStatement("insert into goods(goods_name,goods_price,"
				+ "goods_description,goods_num,purchase_times,comment_times,t_classify_id,"
				+ "status) values(?,?,?,?,?,?,?,?)");
        
		pStmt.setString(1, goods.getGoods_name());
		pStmt.setDouble(2, goods.getGoods_price());
		pStmt.setString(3, goods.getGoods_description());
		pStmt.setInt(4, goods.getGoods_num());
		pStmt.setInt(5, goods.getPurchase_times());
		pStmt.setInt(6, goods.getComment_times());
		pStmt.setInt(7, goods.getT_classify_id());// 、、、、、、、、、
		pStmt.setInt(8, goods.getStatus());

		g.setGoods_id(goods.getGoods_id());
		System.out.println("zz id:"+goods.getGoods_id());
		g.setGoods_name(goods.getGoods_name());
		g.setGoods_price(goods.getGoods_price());
		g.setGoods_description(goods.getGoods_description());
		g.setGoods_num(goods.getGoods_num());
		g.setPurchase_times(goods.getPurchase_times());
		g.setComment_times(goods.getComment_times());
		g.setT_classify_id(goods.getT_classify_id());
		g.setStatus(goods.getStatus());
		
		pStmt.close();
    } catch (Exception e) {
       	e.printStackTrace();
        System.out.println("在goods中添加商品失败！");
    } finally{
    	DBConnection.closeConnection();
	}		
    return g;
 }    
	//修改商品信息
    public int updateGoods(GoodsInfo goods){
    	PreparedStatement pStmt=null; 
    	int count=0;
        try {
        	con=DBConnection.getConnection();
    		pStmt = con.prepareStatement( "update t_course set goods_name=?,"
    				+ "goods_price=?,goods_num=? where goods_id=?");
    		pStmt.setString(1,goods.getGoods_name());		
    		pStmt.setDouble(2,goods.getGoods_price());		
    		//pStmt.setString(3,goods.getGoods_description());		
    		pStmt.setInt(3,goods.getGoods_num());		
    		pStmt.setInt(4,goods.getGoods_id());	
    		count=pStmt.executeUpdate();
			pStmt.close();

        } catch (Exception e) {
           	e.printStackTrace();
            System.out.println("修改商品信息失败！");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    
    }
	
}
