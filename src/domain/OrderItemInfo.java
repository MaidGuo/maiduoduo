package domain;
//��Ʒ����
public class OrderItemInfo {
	private int id;//��Ʒ���������ID
	private int Goods_id;//��Ʒ���
	private int order_id;//�������
	private int num;//��Ʒ����
	private double total_price;//��Ʒ�ܽ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return Goods_id;
	}
	public void setGoods_id(int goods_id) {
		Goods_id = goods_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
}
