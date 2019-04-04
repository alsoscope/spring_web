package com.p.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;

//@Repository 보관소 (Repository) : 퍼시스턴스 계층에서이 애노테이션을 사용해야합니다. 이는 데이터베이스 저장소와 같은 역할을합니다.
//저장된 모든 proc DB 연결에 @Repository 사용
//BoardDAO의 구현 클래스 BoardDAOImpl
//BoardDAOImpl은 내용상으로는 거의 SqlSessionTemplate을 이용해서 원하는 코드를 호출하는 수준
@Repository //현재 클래스를 dao bean으로 등록
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession SqlSession;
	
	private static final String namespace="board";
	
	//01 게시글 작성
	@Override
	public void create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.insert(namespace+".insert",vo);
	}

	//02 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return SqlSession.selectOne("board.view",bno);
	}

	//03 게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception {
		SqlSession.update("board.updateArticle",vo);
	}

	//04 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.delete("board.deleteArticle",bno);
	}

	//05 게시글 전체목록
	@Override
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception {
		//검색 옵션, 키워드 맵에 저장
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectList("board.listAll", map);
		//return SqlSession.selectList(namespace+".listAll", map);
	}

	//06 게시글 조회수 증가
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.update("board.increaseViewcnt",bno);
	}

	//07 게시글 레코드 개수
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		// TODO Auto-generated method stub
		//검색옵션, 키워드 맵에 저장
		Map<String, String> map=new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectOne("board.countArticle", map);
	}

/*	//페이징 처리
	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0) {
			page=1;
		}
		page = (page-1) * 10;
		return SqlSession.selectList(namespace + ".listPage" , page);
	}*/

	//페이징 처리 객체 Criteria 포함된 list 출력
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return SqlSession.selectList(namespace+".listCriteria", cri);
	}

	//totalCount 포함하여 실제 게시물로 페이징 처리
	@Override
	public int countPaging(Criteria cri) throws Exception {
		return SqlSession.selectOne(namespace+".countPaging",cri);
	}

	//검색처리-동적SQL, 페이징 처리
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return SqlSession.selectList("board.listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return SqlSession.selectOne("board.listSearchCount", cri);
	}
}
