package com.p.project.DTO;

public class Criteria {
	private int page;
	private int perPageNum;
	
	//������ constructer
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum=10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}

	//method for MyBatis SQL Mapper
	//limit �������� ������ġ ����. 10����, 3������ ����� ��� limit 20,10
	public int getPageStart() {
		return (this.page-1)*perPageNum;
		//���� ������ ��ȣ = (������ ��ȣ-1)*������ �� �������� ����
	}
	
	//limit ���� ���ڸ� �ǹ�. �� �������� �������� ����.
	//method for MyBatis SQL Mapper
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}