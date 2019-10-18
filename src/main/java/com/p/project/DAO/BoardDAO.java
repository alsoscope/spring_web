package com.p.project.DAO;

import java.util.List;
import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;

//인터페이스
public interface BoardDAO {
	//CRUD
	//글 작성
	public void create(BoardVO vo)throws Exception;
	//상세 보기
	public BoardVO read(int bno) throws Exception;
	//수정
	public void update(BoardVO vo)throws Exception;
	//삭제
	public void delete(int bno)throws Exception;
	
	//게시글 전체 목록 --> 검색옵션, 키워드 매개변수 추가
	public List<BoardVO> listAll(String searchOptin, String keyword)throws Exception;
	//게시글 조회수 증가
	public void increaseViewcnt(int bno)throws Exception;
	//총 게시물 개수 확인
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	//criteria 객체를 파라미터로 전달 받아, BoardDAO에 리스트를 출력 getPageStart(), getPerPageNum() 메소드 호출
	/*public List<BoardVO> listCriteria(Criteria cri)throws Exception;*/
	//totalCount 반환하여 실제 페이징 처리
	/*public int countPaging(Criteria cri)throws Exception;*/
	
	//검색처리와 동적SQL. 검색과 페이징 처리
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;

	/*//페이징 처리 public List<BoardVO> listPage(int page)throws Exception;*/
}
