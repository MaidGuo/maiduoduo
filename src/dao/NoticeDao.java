package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;
import domain.NoticeInfo;

public class NoticeDao {
	private Connection con = null;

	/* �õ�ȫ���Ĺ��� */
	public ArrayList<NoticeInfo> getAllNotice() {
		ArrayList<NoticeInfo> noticelist = new ArrayList<NoticeInfo>();
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from system_notice");
			while (rs.next()) {
				NoticeInfo noticeInfo = new NoticeInfo();
				noticeInfo.setNotice_id(rs.getInt("notice_id"));
				noticeInfo.setNotice_theme(rs.getString("notice_theme"));
				noticeInfo.setNotice_content(rs.getString("notice_content"));
				noticeInfo.setCreated_time(rs.getString("created_time"));
				noticeInfo.setUpdate_time(rs.getString("update_time"));
				noticelist.add(noticeInfo);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡȫ���������");
		} finally {
			DBConnection.closeConnection();
		}
		return noticelist;
	}

	/* �õ�ָ���Ĺ��� */
	public NoticeInfo getNotice(int notice_id) {
		NoticeInfo notice = new NoticeInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("select * from system_notice where notice_id=?");
			ps.setInt(1, notice_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_theme(rs.getString("notice_theme"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setCreated_time(rs.getString("created_time"));
				notice.setUpdate_time(rs.getString("update_time"));
			} else {
				System.out.println("��ӽ�Info����");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡָ���������");
		} finally {
			DBConnection.closeConnection();
		}
		return notice;
	}

	/* �޸�ָ���Ĺ��� */
	public int getEditNotice(NoticeInfo notice) {
		int count = 0;
		PreparedStatement ps = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("update system_notice set notice_theme=?, notice_content=?, created_time=?, update_time=? where notice_id=?");
			ps.setString(1, notice.getNotice_theme());
			ps.setString(2, notice.getNotice_content());
			ps.setString(3, notice.getCreated_time());
			ps.setString(4, notice.getUpdate_time());
			ps.setInt(5, notice.getNotice_id());
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�޸Ĺ���ʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}

		return count;
	}

	/* ����µĹ��� */
	public int getInsertNotice(NoticeInfo notice) {
		int count = 0;
		PreparedStatement ps = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("insert into system_notice (notice_id, notice_theme, notice_content, created_time, update_time) values (?, ?, ?, ?, ?)");
			ps.setInt(1, notice.getNotice_id());
			ps.setString(2, notice.getNotice_theme());
			ps.setString(3, notice.getNotice_content());
			ps.setString(4, notice.getCreated_time());
			ps.setString(5, notice.getUpdate_time());
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ӹ���ʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}

		return count;
	}
	
	/* ɾ��ָ���Ĺ���*/
	public void getDeletNotice(int notice_id) {
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("delete from system_notice where notice_id=?");
			ps.setInt(1, notice_id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
	}
}
