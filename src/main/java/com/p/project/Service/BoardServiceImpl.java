package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;
import com.p.project.DAO.BoardDAO;

//인터페이스 구현 클래스. 구현객체.
//게시글 쓰기 처리(태그문자, 공백문자, 줄바꿈 문자 처리)
//게시글 조회수 증가 처리(일정 시간동안 조회수 증가하지 않도록 처리)

//@Service : 주석 된 클래스가 비즈니스 계층의 서비스 구성 요소임을 나타냅니다.
//모든 웹 서비스 유형의 DB 연결에 @Service 사용
//클래스 선언부에는 스프링의 빈으로 인식되기 위해 @Service 어노테이션 적용
//간단한 CRUD 작업중, DAO를 이용해서 작업을 실행
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	//01 게시글 쓰기
	@Override
	public void create(BoardVO vo) throws Exception {
		String title=vo.getTitle();
		String content=vo.getContent();
		String writer=vo.getWriter();
		//*태그문자처리  (< == > &lt; > == > &gt;)
		//replace(A,B) A를 B로 변경
		title=title.replace("<", "&lt;");
		title=title.replace("<", "&gt;");
		writer=writer.replace("<", "&lt;");
		writer=writer.replace("<", "&gt;");
		
		//*공백문자 처리
		title=title.replace(" ","&nbsp;&nbsp;");
		writer=writer.replace(" ","&nbsp;&nbsp;");
		
		//*줄바꿈 문자 처리
		content=content.replace("\n", "<br>");
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		boardDao.create(vo);
	}

	//02 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	//03 게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
	}

	//04 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	//05 게시글 전체 목록 boardDAO.listAll 메소드 호출
	@Override
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception {
		return boardDao.listAll(searchOption, keyword);
	}

	//06 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time=0;
		//세션에 저장된 조회시간 검색
		//최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행x
		if(session.getAttribute("update_time_"+bno)!=null) {
			//세션에서 읽어오기
			update_time=(long)session.getAttribute("update_time_"+bno);
		}
		//시스템의 현재시간을 current_time에 저장
		long current_time=System.currentTimeMillis();
		//일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
		//시스템 현재시간-열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if(current_time-update_time>5*1000) {
			boardDao.increaseViewcnt(bno);
			//세션에 시간을 저장:"update_time_"+bno는 다른 변수와 중복되지 않게 명명한 것
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	//총 게시물 개수 확인
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		return boardDao.countArticle(searchOption, keyword);
	}

	//검색처리, 페이징 처리 (동적 SQL문, SearchCriteria 적용)
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return boardDao.listSearch(cri);
	}
	
	//검색처리, 페이징 처리 (동적 SQL문, SearchCriteria 적용)
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return boardDao.listSearchCount(cri);
	}

	//페이징 처리
	/*@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardDao.listCriteria(cri);
	}*/

	/*@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return boardDao.countPaging(cri);
	}*/


}
