package com.p.project.DTO;

import java.util.Arrays;

public class ProductDTO {
	private int product_id;
	private String product_name;
	private int product_price;
	private String product_desc; //상품설명. description
	private String insert_regdate;
	private String update_regdate;
	
	private boolean group;
	
	//첨부파일
	private String[] files;

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

	public String getInsert_regdate() {
		return insert_regdate;
	}

	public void setInsert_regdate(String insert_regdate) {
		this.insert_regdate = insert_regdate;
	}

	public String getUpdate_regdate() {
		return update_regdate;
	}

	public void setUpdate_regdate(String update_regdate) {
		this.update_regdate = update_regdate;
	}

	public boolean isGroup(boolean group) {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "ProductDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_desc=" + product_desc + ", insert_regdate=" + insert_regdate
				+ ", update_regdate=" + update_regdate + ", group=" + group + ", files=" + Arrays.toString(files) + "]";
	}

	//private String product_url; //상품 이미지 경로
	//private MultipartFile product_Photo; //상품 이미지 파일
	
	
}
