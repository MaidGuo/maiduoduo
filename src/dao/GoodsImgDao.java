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

//商品图片
public class GoodsImgDao {
	private Connection con = null;
	
	/* 获取一条商品图片信息 */
	public GoodsImgInfo getOneGoodsImg(int goods_id){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		GoodsImgInfo goodsImg = null;//商品图片
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM goods_img where goods_id=?");
    		pStmt.setInt(1,goods_id);	//由商品ID获得信息
    		// TODO 1 执行查询语句获取课程信息
    		rs = pStmt.executeQuery();
			//获取指定商品信息
			while(rs.next()) {
				goodsImg = new GoodsImgInfo();
				goodsImg.setImg_id(rs.getInt("img_id"));//图片id
				goodsImg.setGoods_id(rs.getInt("goods_id"));//商品编号
				goodsImg.setImg_path(rs.getString("img_path"));//图片路径
				
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取商品图片信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return goodsImg;
	}/* 获取一条商品图片信息 */
	
	////在goods_img添加一条图片
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
				System.out.println("在goods_img添加一条图片失败！");
			} finally {
				DBConnection.closeConnection();
			}
			return count;
		}//在goods_img添加一条图片
	
	//在goods_img添加一条图片
	public GoodsImgInfo insertGoodsImg(int gId){
		PreparedStatement pStmt = null;
		GoodsImgInfo gImg = new GoodsImgInfo();// 获得
		gImg = getOneGoodsImg(gId);
		GoodsImgInfo img = new GoodsImgInfo();// 添加
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
			System.out.println("在goods_img添加图片失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return img;
	}
	
	
	/** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public void copyFile(String oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //字节数 文件大小 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 

       } 

   } 

}
