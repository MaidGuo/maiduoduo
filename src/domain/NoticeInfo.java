package domain;

/**
 * 公告表的值Bean
 * 
 * */

public class NoticeInfo {
	
	private int notice_id;
	private String notice_theme;
	private String notice_content;
	private String created_time;
	private String update_time;
	
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_theme() {
		return notice_theme;
	}
	public void setNotice_theme(String notice_theme) {
		this.notice_theme = notice_theme;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
