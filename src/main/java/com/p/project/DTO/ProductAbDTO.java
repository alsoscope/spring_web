package com.p.project.DTO;

import java.util.Arrays;

public class ProductAbDTO {
	private int ab_item_id;
	private String ab_item_name;
	private int ab_item_price;
	private String ab_item_desc;
	private String ab_insert;
	private String ab_update;
	//Ã·ºÎÆÄÀÏ
	private String[] files;
	
	public int getAb_item_id() {
		return ab_item_id;
	}
	public void setAb_item_id(int ab_item_id) {
		this.ab_item_id = ab_item_id;
	}
	public String getAb_item_name() {
		return ab_item_name;
	}
	public void setAb_item_name(String ab_item_name) {
		this.ab_item_name = ab_item_name;
	}
	public int getAb_item_price() {
		return ab_item_price;
	}
	public void setAb_item_price(int ab_item_price) {
		this.ab_item_price = ab_item_price;
	}
	public String getAb_item_desc() {
		return ab_item_desc;
	}
	public void setAb_item_desc(String ab_item_desc) {
		this.ab_item_desc = ab_item_desc;
	}
	public String getAb_insert() {
		return ab_insert;
	}
	public void setAb_insert(String ab_insert) {
		this.ab_insert = ab_insert;
	}
	public String getAb_update() {
		return ab_update;
	}
	public void setAb_update(String ab_update) {
		this.ab_update = ab_update;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "ProductAbDTO [ab_item_id=" + ab_item_id + ", ab_item_name=" + ab_item_name + ", ab_item_price="
				+ ab_item_price + ", ab_item_desc=" + ab_item_desc + ", ab_insert=" + ab_insert + ", ab_update="
				+ ab_update + ", files=" + Arrays.toString(files) + "]";
	}
	
}
