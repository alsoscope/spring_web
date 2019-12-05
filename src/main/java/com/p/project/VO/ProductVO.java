package com.p.project.VO;

public class ProductVO {

	private int product_id;
	private String product_name;
	private String product_desc;
	private int product_price;
	private String fullName;
	private String getLink;
	private String regdate;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGetLink() {
		return getLink;
	}
	public void setGetLink(String getLink) {
		this.getLink = getLink;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", product_name=" + product_name + ", product_desc="
				+ product_desc + ", product_price=" + product_price + ", fullName=" + fullName + ", getLink=" + getLink
				+ ", regdate=" + regdate + "]";
	}

}
