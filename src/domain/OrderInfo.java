package domain;

import java.util.Date;

//����
public class OrderInfo {
	private int order_id;
	private String order_no;//ϵͳ���ɣ�ʱ���+����룩
	private Date created_time;//���ɶ������ȷ��ʱ ����µ�ʱ��
	private int user_id;//�� t_user �е�user_id����
	private double payment;//1 ���߸��� 0 ��������
	private int payment_type;//֧����ʽ
	private int send_time;//�ͻ�ʱ�� 1 �����ͻ�ʱ�䣨��һ�����գ�	2�������ͻ�����һ�����壩	3 �ڼ����ͻ������������գ�
	private int status=2;//1 δ���� 2 δ���� 3 �ѷ��� 4���׳ɹ�
	private Date end_time;//�������ʱ��
	private String buyer_message="";//�������
	private int iscomment=0;//0 �� 1 ��	ֻ�н��׳ɹ���ſ�������
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public int getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
	public int getSend_time() {
		return send_time;
	}
	public void setSend_time(int send_time) {
		this.send_time = send_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	public int getIscomment() {
		return iscomment;
	}
	public void setIscomment(int iscomment) {
		this.iscomment = iscomment;
	}
	
}
