package domain;

import java.util.List;

/**
 * 封装用户留言信息
 * @author Administrator
 *
 */
public class MessageInfo {
	private int messageid; //留言编号
	private int userid;  //用户id
	private String theme;  //留言主题
	private String content;  //留言内容
	private String time;  //留言时间
	private int isReply; //是否回复
	private List<MessageReplyInfo> replylist;  //回复信息
	
	public List<MessageReplyInfo> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<MessageReplyInfo> replylist) {
		this.replylist = replylist;
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
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
	public int getIsReply() {
		return isReply;
	}
	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}
	
}
