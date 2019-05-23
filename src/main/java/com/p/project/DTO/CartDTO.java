package com.p.project.DTO;

public class CartDTO {
	private int cartId; //��ٱ��� ��ȣ
	private String userId; //����� ���̵�
	private int product_id; //��ǰ��ȣ
	private int amount; //���� ����
	
	private String user_name; //����� �̸�
	private String product_name; //��ǰ �̸�
	private int product_price; //��ǰ �ܰ�
	private int money; //��ǰ ����
	
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", userId=" + userId + ", product_id=" + product_id + ", amount=" + amount
				+ ", user_name=" + user_name + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", money=" + money + "]";
	}
	
	
}
