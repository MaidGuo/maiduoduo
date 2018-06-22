package dao;

import java.sql.*;
import java.util.ArrayList;

import common.DBConnection;

import domain.*;

public class GoodsDao {
//�����Ʒ
	Connection con=null;
/**
 * �õ��������Ӵ�С�����ȫ����Ʒ
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
			System.out.println("��ȡȫ����Ʒ����");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsInfolist;
	}
	
	

	/* ��ȡһ����Ʒ��Ϣ */
	public GoodsInfo getGoodsbyId(int id){
		ResultSet rs = null;
		PreparedStatement pStmt=null;
		GoodsInfo goods = null;//��Ʒ
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("Select * from goods a,goods_img b where a.goods_id=b.goods_id and a.goods_id=?");
    		pStmt.setInt(1,id);	
    		rs = pStmt.executeQuery();
			//��ȡָ����Ʒ��Ϣ
			if(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//��Ʒ����
				goods.setGoods_price(rs.getDouble("goods_price"));//��Ʒ�۸�(2λС��)
				goods.setGoods_description(rs.getString("goods_description"));//��Ʒ����
				goods.setGoods_num(rs.getInt("goods_num"));//�����
				goods.setPurchase_times(rs.getInt("purchase_times"));//�������
				goods.setComment_times(rs.getInt("comment_times"));//�������
				goods.setT_classify_id(rs.getInt("t_classify_id"));//����������
				goods.setStatus(rs.getInt("status"));//״̬��0 �¼� 1 ����
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			}
			
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡָ����Ʒ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return goods;
	}
	
	/* ��ȡ������Ʒ��Ϣ*/
	public ArrayList<GoodsInfo> getAllGoods() {
		ResultSet rs = null;
		Statement sql = null;
		GoodsInfo goods = null;// ��Ʒ
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
				goods.setGoods_name(rs.getString("goods_name"));//��Ʒ����
				goods.setGoods_price(rs.getDouble("goods_price"));//��Ʒ�۸�(2λС��)
				goods.setGoods_description(rs.getString("goods_description"));//��Ʒ����
				goods.setGoods_num(rs.getInt("goods_num"));//�����
				goods.setPurchase_times(rs.getInt("purchase_times"));//�������
				goods.setComment_times(rs.getInt("comment_times"));//�������
				goods.setT_classify_id(rs.getInt("t_classify_id"));//����������
				goods.setStatus(rs.getInt("status"));//״̬��0 �¼� 1 ����
				//classify
				goods.setT_Classify_name(rs.getString("t_Classify_name"));
				goods.setS_Classify_id(rs.getInt("s_Classify_id"));
				goods.setS_Classify_name(rs.getString("s_Classify_name"));
				goods.setF_Classify_id(rs.getInt("f_Classify_id"));
				goods.setF_Classify_name(rs.getString("f_Classify_name"));
				//img
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			
				goodsList.add(goods);// ���һ����¼
			}
			rs.close();
			sql.close();
		} catch (Exception e) {
			System.out.println("��ȡ������Ʒ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsList;
	}/* ��ȡ������Ʒ��Ϣ */
	
	
	/* ��ȡһ����Ʒ��Ϣ */
	public GoodsInfo getOneGoods(int id){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsInfo goods = null;//��Ʒ
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM goods,goods_img "
+"where goods.goods_id=? and goods.goods_id=goods_img.goods_id;");
    		pStmt.setInt(1,id);	
    		rs = pStmt.executeQuery();
			//��ȡָ����Ʒ��Ϣ
			while(rs.next()) {
				goods = new GoodsInfo();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_name(rs.getString("goods_name"));//��Ʒ����
				goods.setGoods_price(rs.getFloat("goods_price"));//��Ʒ�۸�(2λС��)
				goods.setGoods_description(rs.getString("goods_description"));//��Ʒ����
				goods.setGoods_num(rs.getInt("goods_num"));//�����
				goods.setPurchase_times(rs.getInt("purchase_times"));//�������
				goods.setComment_times(rs.getInt("comment_times"));//�������
				goods.setT_classify_id(rs.getInt("t_classify_id"));//����������
				goods.setStatus(rs.getInt("status"));//״̬��0 �¼� 1 ����
				//ͼƬ
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡָ����Ʒ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return goods;
	}/* ��ȡһ����Ʒ��Ϣ */
	
	//��ȡ3�����������Ʒ
	public ArrayList<GoodsInfo> getGoodsByTid(int tid) {
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsInfo goods = new GoodsInfo();//��Ʒ
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
				goods.setGoods_name(rs.getString("goods_name"));//��Ʒ����
				goods.setGoods_price(rs.getFloat("goods_price"));//��Ʒ�۸�(2λС��)
				goods.setGoods_description(rs.getString("goods_description"));//��Ʒ����
				goods.setGoods_num(rs.getInt("goods_num"));//�����
				goods.setPurchase_times(rs.getInt("purchase_times"));//�������
				goods.setComment_times(rs.getInt("comment_times"));//�������
				goods.setT_classify_id(rs.getInt("t_classify_id"));//����������
				goods.setStatus(rs.getInt("status"));//״̬��0 �¼� 1 ����
				//classify
				goods.setT_Classify_name(rs.getString("t_Classify_name"));
				goods.setS_Classify_id(rs.getInt("s_Classify_id"));
				goods.setS_Classify_name(rs.getString("s_Classify_name"));
				goods.setF_Classify_id(rs.getInt("f_Classify_id"));
				goods.setF_Classify_name(rs.getString("f_Classify_name"));
				//img
				goods.setImg_id(rs.getInt("img_id"));
				goods.setImg_path(rs.getString("img_path"));
			
				goodsList.add(goods);// ���һ����¼
			}
		
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ3�����������Ʒ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsList;
	}/* ��ȡ3�����������Ʒ��Ϣ */

	//�༭״̬
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
            System.out.println("�޸���Ʒ״̬ʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
	}
	
	//������Ʒ
	public ArrayList<GoodsInfo> findGoods(String str) {
			ResultSet rs = null;
			PreparedStatement pStmt=null; 
			GoodsInfo goods = new GoodsInfo();//��Ʒ
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
					goods.setGoods_name(rs.getString("goods_name"));//��Ʒ����
					goods.setGoods_price(rs.getFloat("goods_price"));//��Ʒ�۸�(2λС��)
					goods.setGoods_description(rs.getString("goods_description"));//��Ʒ����
					goods.setGoods_num(rs.getInt("goods_num"));//�����
					goods.setPurchase_times(rs.getInt("purchase_times"));//�������
					goods.setComment_times(rs.getInt("comment_times"));//�������
					goods.setT_classify_id(rs.getInt("t_classify_id"));//����������
					goods.setStatus(rs.getInt("status"));//״̬��0 �¼� 1 ����
					//classify
					goods.setT_Classify_name(rs.getString("t_Classify_name"));
					goods.setS_Classify_id(rs.getInt("s_Classify_id"));
					goods.setS_Classify_name(rs.getString("s_Classify_name"));
					goods.setF_Classify_id(rs.getInt("f_Classify_id"));
					goods.setF_Classify_name(rs.getString("f_Classify_name"));
					//img
					goods.setImg_id(rs.getInt("img_id"));
					goods.setImg_path(rs.getString("img_path"));
				
					goodsList.add(goods);// ���һ����¼
				}
				rs.close();
				pStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��ȡ������Ʒ��Ϣʧ�ܣ�");
			} finally {
				DBConnection.closeConnection();
			}
			return goodsList;
		}/* ��ȡ������Ʒ��Ϣ */
	
	// ���һ����Ʒ
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
			pStmt.setInt(7, goods.getT_classify_id());// ������������������
			pStmt.setInt(8, goods.getStatus());

			count = pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�������Ʒʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return count;
	}
	
	//��ȡ���һ��ID
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
			System.out.println("��ȡ���id��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return id;
		}/* ��ȡһ����Ʒ��Ϣ */
	
	//��goods���һ����Ʒ
	public GoodsInfo insertOneGoods(GoodsInfo goods){
	PreparedStatement pStmt=null;
	GoodsInfo g=new GoodsInfo();//���
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
		pStmt.setInt(7, goods.getT_classify_id());// ������������������
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
        System.out.println("��goods�������Ʒʧ�ܣ�");
    } finally{
    	DBConnection.closeConnection();
	}		
    return g;
 }    
	//�޸���Ʒ��Ϣ
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
            System.out.println("�޸���Ʒ��Ϣʧ�ܣ�");
        } finally{
        	DBConnection.closeConnection();
		}		
        return count;
    
    }
	
}
