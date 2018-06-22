package domain;

import java.util.Date;

/**
 * 商品信息
 * @author Maid Guo
 *
 */
public class GoodsInfo {
	private int goods_id=0;//商品编号
	private String goods_name="";//商品名称
	private double goods_price=0;//商品价格(2位小数)
	private String goods_description="";//商品描述
	private int goods_num=0;//库存量
	private int purchase_times=0;//购买次数
	private int comment_times=0;//评价数
	private int t_classify_id=0;//三级分类编号
	private int status=1;//状态，0 下架 1 存在
	//图片
	private int img_id=0;//图片ID
	private String img_path="";//图片路径
	//级别分类
	private int f_Classify_id=0;//一级分类ID
	private String f_Classify_name="";//一级分类名称
	private int s_Classify_id=0;//二级ID
	private String s_Classify_name="";//二级分类名
	private String t_Classify_name="";//三级分类名称
	
	private Date addToCartTime; //加入购物车的时间
	private int buyNum=0;//添加商品数量
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
	
	//图片部分
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
	
	//类别分级
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
