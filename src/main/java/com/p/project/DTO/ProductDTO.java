package com.p.project.DTO;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
	private int product_id;
	private String product_name;
	private int product_price;
	private String product_desc; //상품설명. description
	private String product_url; //상품 이미지 경로
	private MultipartFile product_Photo; //상품 이미지 파일
	
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
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public String getProduct_url() {
		return product_url;
	}
	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}
	public MultipartFile getProduct_Photo() {
		return product_Photo;
	}
	public void setProduct_Photo(MultipartFile product_Photo) {
		this.product_Photo = product_Photo;
	}
	
	@Override
	public String toString() {
		return "ProductDTO_VO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_desc=" + product_desc + ", product_url=" + product_url + ", product_Photo="
				+ product_Photo + "]";
	}
	
}
