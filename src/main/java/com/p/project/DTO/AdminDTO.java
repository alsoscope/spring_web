package com.p.project.DTO;

import java.util.Date;

public class AdminDTO {
	private String admin_id;
	private String admin_pw;
	private String admin_name;
	private Date admin_regdate;
	private Date admin_updatedate;
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_email(String admin_name) {
		this.admin_name = admin_name;
	}
	public Date getAdmin_regdate() {
		return admin_regdate;
	}
	public void setAdmin_regdate(Date admin_regdate) {
		this.admin_regdate = admin_regdate;
	}
	public Date getAdmin_updatedate() {
		return admin_updatedate;
	}
	public void setAdmin_updatedate(Date admin_updatedate) {
		this.admin_updatedate = admin_updatedate;
	}
	@Override
	public String toString() {
		return "AdminDTO_VO [admin_id=" + admin_id + ", admin_pw=" + admin_pw + ", admin_name=" + admin_name
				+ ", admin_regdate=" + admin_regdate + ", admin_updatedate=" + admin_updatedate + "]";
	}
	
	
}
