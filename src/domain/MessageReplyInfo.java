package domain;

/**
 * 封装留言回复信息
 * @author Administrator
 *
 */
public class MessageReplyInfo {
	private int id;  //回复id
	private int message_id;  
	private String content;  //回复内容
	private String time;  //回复时间
	
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
