package domain;

import java.util.ArrayList;
import java.util.Date;

public class AdminInfo {
	private int id=0; //���

	private String admin_name="";//����
	private String admin_pwd="";//����
	private String admin_tel="";//����Ա�绰
	private String admin_email="";//����Ա����
	private ArrayList<Date> loginTime; //��¼ʱ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	public String getAdmin_tel() {
		return admin_tel;
	}
	public void setAdmin_tel(String admin_tel) {
		this.admin_tel = admin_tel;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public ArrayList<Date> getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(ArrayList<Date> loginTime) {
		this.loginTime = loginTime;
	}
}
