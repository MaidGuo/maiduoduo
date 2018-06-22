package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;
import domain.AdImagInfo;
/* 获取广告图片信息 */
public class AdDao {
	private Connection con = null;
	
	public ArrayList<AdImagInfo> AdImage(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		AdImagInfo AdImg=new AdImagInfo();
		ArrayList<AdImagInfo> AdImglist = new ArrayList<AdImagInfo>();//广告图片
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM ad_img");
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				AdImg = new AdImagInfo();
				AdImg.setAd_id(rs.getInt("ad_id"));
				AdImg.setImg_path(rs.getString("img_path"));
				AdImglist.add(AdImg);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取广告图片信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return AdImglist;
	}/* 获取广告图片信息 */
	
	/* 获取公告信息 */
	
	/* 获取公告信息 */
}
