package domain;

import java.util.Date;

//订单
public class OrderInfo {
	private int order_id;
	private String order_no;//系统生成（时间戳+随机码）
	private Date created_time;//生成订单点击确认时 获得下单时间
	private int user_id;//与 t_user 中的user_id关联
	private double payment;//1 在线付款 0 货到付款
	private int payment_type;//支付方式
	private int send_time;//送货时间 1 不限送货时间（周一至周日）	2工作日送货（周一至周五）	3 节假日送货（周六、周日）
	private int status=2;//1 未付款 2 未发货 3 已发货 4交易成功
	private Date end_time;//交易完成时间
	private String buyer_message="";//买家留言
	private int iscomment=0;//0 否 1 是	只有交易成功后才可以评价
	
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
