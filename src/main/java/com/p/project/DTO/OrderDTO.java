package com.p.project.DTO;

import java.util.Date;

public class OrderDTO {
	private int cartId;
	private String userId;
	private int product_id;
	private int amount;
	private String insertDate;
	private String product_name;
	private int allSum;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getAllSum() {
		return allSum;
	}
	public void setAllSum(int allSum) {
		this.allSum = allSum;
	}
	@Override
	public String toString() {
		return "OrderDTO [cartId=" + cartId + ", userId=" + userId + ", product_id=" + product_id + ", amount=" + amount
				+ ", insertDate=" + insertDate + ", product_name=" + product_name + ", allSum=" + allSum + "]";
	}

	
}
