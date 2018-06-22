package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;
import domain.GoodsImgInfo;
import domain.GoodsInfo;
import domain.SecondClassifyInfo;

//��ƷͼƬ
public class GoodsImgDao {
	private Connection con = null;
	
	/* ��ȡһ����ƷͼƬ��Ϣ */
	public GoodsImgInfo getOneGoodsImg(int goods_id){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsImgInfo goodsImg = null;//��ƷͼƬ
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM goods_img where goods_id=?");
    		pStmt.setInt(1,goods_id);	//����ƷID�����Ϣ
    		// TODO 1 ִ�в�ѯ����ȡ�γ���Ϣ
    		rs = pStmt.executeQuery();
			//��ȡָ����Ʒ��Ϣ
			while(rs.next()) {
				goodsImg = new GoodsImgInfo();
				goodsImg.setImg_id(rs.getInt("img_id"));//ͼƬid
				goodsImg.setGoods_id(rs.getInt("goods_id"));//��Ʒ���
				goodsImg.setImg_path(rs.getString("img_path"));//ͼƬ·��
				
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ��ƷͼƬ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsImg;
	}/* ��ȡһ����ƷͼƬ��Ϣ */
	
	////��goods_img���һ��ͼƬ
	public int insertOneImg(int gid,String p) {
			PreparedStatement pStmt = null;
			int count = 0;
			try {
				con = DBConnection.getConnection();
				pStmt = con.prepareStatement("insert into goods_img(goods_id,img_path) "
						+ "values(?,?)");
				
				pStmt.setInt(1, gid);
				pStmt.setString(2,p);

				count = pStmt.executeUpdate();
				pStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��goods_img���һ��ͼƬʧ�ܣ�");
			} finally {
				DBConnection.closeConnection();
			}
			return count;
		}//��goods_img���һ��ͼƬ
	
	//��goods_img���һ��ͼƬ
	public GoodsImgInfo insertGoodsImg(int gId){
		PreparedStatement pStmt = null;
		GoodsImgInfo gImg = new GoodsImgInfo();// ���
		gImg = getOneGoodsImg(gId);
		GoodsImgInfo img = new GoodsImgInfo();// ���
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("insert into goods_img values(?,?,?)");

			pStmt.setInt(1,gImg.getImg_id());
			pStmt.setInt(2, gId);
			pStmt.setString(3,gImg.getImg_path());
			
			img.setImg_id(gImg.getImg_id());
			img.setGoods_id(gId);
			img.setImg_path(gImg.getImg_path());

			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��goods_img���ͼƬʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return img;
	}
	
	
	/** 
     * ���Ƶ����ļ� 
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt 
     * @param newPath String ���ƺ�·�� �磺f:/fqf.txt 
     * @return boolean 
     */ 
   public void copyFile(String oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //�ļ�����ʱ 
               InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //�ֽ��� �ļ���С 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("���Ƶ����ļ���������"); 
           e.printStackTrace(); 

       } 

   } 

}
