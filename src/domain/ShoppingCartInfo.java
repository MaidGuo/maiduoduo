package domain;

import java.util.*;

public class ShoppingCartInfo {
	private int cart_id=0; // 购物车编号
	private GoodsInfo goods;
	//private int good_id=0;//商品id
	private int user_id=0;//用户id
	private int nums=0;//商品数量
	private Date add_time;//创建时间
	private int status=0;//购物车状态（有无商品）
	public GoodsInfo getGoods() {
		return goods;
	}
	public void setGoods(GoodsInfo goods) {
		this.goods = goods;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
/*	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}*/
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public Date getCreated_time() {
		return add_time;
	}
	public void setCreated_time(Date add_time) {
		this.add_time = add_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
