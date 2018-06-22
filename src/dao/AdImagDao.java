package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;
import domain.AdImagInfo;
import domain.NoticeInfo;

public class AdImagDao {
	
	private Connection con = null;

	/* 閼惧嘲绶遍幍锟介張澶庣槑鐠佽桨淇婇幁锟�*/
	public ArrayList<AdImagInfo> getAllAdImag() {
		ArrayList<AdImagInfo> adImaglist = new ArrayList<AdImagInfo>();
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from ad_img");
			while (rs.next()) {
				AdImagInfo adImagInfo = new AdImagInfo();
				adImagInfo.setAd_id(rs.getInt("ad_id"));
				adImagInfo.setImg_path(rs.getString("img_path"));
				adImaglist.add(adImagInfo);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("閼惧嘲褰囬崗銊╁劥楠炲灝鎲￠柨娆掝嚖閿涳拷");
		} finally {
			DBConnection.closeConnection();
		}
		return adImaglist;
	}

	/* 濞ｈ濮為弬鎵畱楠炲灝鎲￠崶鍓у */
	public int getInsertAdImag(AdImagInfo adImagInfo) {
		int count = 0;
		PreparedStatement ps = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("insert into ad_img (ad_id, img_path) values (?, ?)");
			ps.setInt(1, adImagInfo.getAd_id());
			ps.setString(2, adImagInfo.getImg_path());
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("濞ｈ濮為獮鍨啞閸ュ墽澧栨径杈Е閿涳拷");
		} finally {
			DBConnection.closeConnection();
		}

		return count;
	}
	
	/* 閸掔娀娅庨幐鍥х暰閻ㄥ嫬鍙曢崨锟*/
	public void getDeletAdImag(int adImag_id) {
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("delete from ad_img where ad_id=?");
			ps.setInt(1, adImag_id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
	}
}
