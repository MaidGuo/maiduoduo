package domain;

import java.util.Date;

/**
 * ��Ʒ��Ϣ
 * @author Maid Guo
 *
 */
public class GoodsInfo {
	private int goods_id=0;//��Ʒ���
	private String goods_name="";//��Ʒ����
	private double goods_price=0;//��Ʒ�۸�(2λС��)
	private String goods_description="";//��Ʒ����
	private int goods_num=0;//�����
	private int purchase_times=0;//�������
	private int comment_times=0;//������
	private int t_classify_id=0;//����������
	private int status=1;//״̬��0 �¼� 1 ����
	//ͼƬ
	private int img_id=0;//ͼƬID
	private String img_path="";//ͼƬ·��
	//�������
	private int f_Classify_id=0;//һ������ID
	private String f_Classify_name="";//һ����������
	private int s_Classify_id=0;//����ID
	private String s_Classify_name="";//����������
	private String t_Classify_name="";//������������
	
	private Date addToCartTime; //���빺�ﳵ��ʱ��
	private int buyNum=0;//�����Ʒ����
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public Date getAddToCartTime() {
		return addToCartTime;
	}
	public void setAddToCartTime(Date addToCartTime) {
		this.addToCartTime = addToCartTime;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public int getPurchase_times() {
		return purchase_times;
	}
	public void setPurchase_times(int purchase_times) {
		this.purchase_times = purchase_times;
	}
	public int getComment_times() {
		return comment_times;
	}
	public void setComment_times(int comment_times) {
		this.comment_times = comment_times;
	}
	public int getT_classify_id() {
		return t_classify_id;
	}
	public void setT_classify_id(int t_classify_id) {
		this.t_classify_id = t_classify_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	//ͼƬ����
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	//���ּ�
	public int getF_Classify_id() {
		return f_Classify_id;
	}
	public void setF_Classify_id(int f_Classify_id) {
		this.f_Classify_id = f_Classify_id;
	}
	public String getF_Classify_name() {
		return f_Classify_name;
	}
	public void setF_Classify_name(String f_Classify_name) {
		this.f_Classify_name = f_Classify_name;
	}
	public int getS_Classify_id() {
		return s_Classify_id;
	}
	public void setS_Classify_id(int s_Classify_id) {
		this.s_Classify_id = s_Classify_id;
	}
	public String getS_Classify_name() {
		return s_Classify_name;
	}
	public void setS_Classify_name(String s_Classify_name) {
		this.s_Classify_name = s_Classify_name;
	}
	
	public String getT_Classify_name() {
		return t_Classify_name;
	}
	public void setT_Classify_name(String t_Classify_name) {
		this.t_Classify_name = t_Classify_name;
	}
}
