package com.p.project.DTO;

import java.util.Arrays;

public class ProductDTO {
	private int product_id;
	private String product_name;
	private int product_price;
	private String product_desc; //��ǰ����. description
	
	//private String product_url; //��ǰ �̹��� ���
	//private MultipartFile product_Photo; //��ǰ �̹��� ����
	
	//÷������
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

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "ProductDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_desc=" + product_desc + ", files=" + Arrays.toString(files) + "]";
	}
	
}
