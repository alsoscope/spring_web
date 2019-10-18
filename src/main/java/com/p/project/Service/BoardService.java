package com.p.project.Service;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;
import com.p.project.DAO.BoardDAO;;

//비즈니스 계층. 요구사항을 메소드로 정리해서 인터페이스 정의
//Service 비즈니스 로직, DB연동 이외의 작업
//인터페이스
public interface BoardService {
	//CRUD
	//글 작성
	public void create(BoardVO vo) throws Exception;
	//상세 보기
	public BoardVO read(int bno) throws Exception;
	// 수정
	public void update(BoardVO dto) throws Exception;
	//삭제
	public void delete(int bno) throws Exception;
	
	//게시글 전체 목록 --> 검색 옵션, 키워드 매개변수 추가
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception;
	//게시글 조회수 증가
	public void increaseViewcnt(int bno, HttpSession session)throws Exception;
	//총 게시물 개수 확인
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	//페이징 처리 메소드 추가
	/*public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	//실제 page를 count
	public int listCountCriteria(Criteria cri) throws Exception;*/
	
	//동적SQL로 검색 처리
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
}
