package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;
import domain.AdImagInfo;
/* ��ȡ���ͼƬ��Ϣ */
public class AdDao {
	private Connection con = null;
	
	public ArrayList<AdImagInfo> AdImage(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		AdImagInfo AdImg=new AdImagInfo();
		ArrayList<AdImagInfo> AdImglist = new ArrayList<AdImagInfo>();//���ͼƬ
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
			System.out.println("��ȡ���ͼƬ��Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return AdImglist;
	}/* ��ȡ���ͼƬ��Ϣ */
	
	/* ��ȡ������Ϣ */
	
	/* ��ȡ������Ϣ */
}
