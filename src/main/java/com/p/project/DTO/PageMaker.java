package com.p.project.DTO;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum=10;
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri=cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount=totalCount;
	
		calcData();
	}
	
	private void calcData() {
		endPage=(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		
		startPage=(endPage-displayPageNum)+1;
		
		int tempEndPage=(int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage>tempEndPage) {
			endPage=tempEndPage;
		}
		
		prev = startPage==1 ? false:true;
		next = endPage*cri.getPerPageNum()>=totalCount ? false:true;
	}
	
	//검색 조건이 없는 상황에서 사용하는 메소드
	//스프링MVC의 UriComponentsBuilder를 이용하여 page(페이지 번호),perPageNum(보여지는 데이터의 수 전달)
	public String makeQuery(int page) {
		UriComponents uriComponents=UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	//검색처리
	//searchType, keyword 링크 처리. 페이징 처리와 조회 화면으로 이동해서 사용되는 링크의 정보 수정
	//적당한 URI에 사용할 문자열(query string)생성
	//UriComponents를 이용해 페이징 처리에 필요한 데이터를 생성
	public String makeSearch(int page) {
		UriComponents uriComponents=UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType",((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
				.build();
		return uriComponents.toUriString();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
