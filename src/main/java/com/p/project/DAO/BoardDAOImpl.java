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

//@Repository ������ (Repository) : �۽ý��Ͻ� ���������� �ֳ����̼��� ����ؾ��մϴ�. �̴� �����ͺ��̽� ����ҿ� ���� �������մϴ�.
//����� ��� proc DB ���ῡ @Repository ���
//BoardDAO�� ���� Ŭ���� BoardDAOImpl
//BoardDAOImpl�� ��������δ� ���� SqlSessionTemplate�� �̿��ؼ� ���ϴ� �ڵ带 ȣ���ϴ� ����
@Repository //���� Ŭ������ dao bean���� ���
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession SqlSession;
	
	private static final String namespace="board";
	
	//01 �Խñ� �ۼ�
	@Override
	public void create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.insert(namespace+".insert",vo);
	}

	//02 �Խñ� �󼼺���
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return SqlSession.selectOne("board.view",bno);
	}

	//03 �Խñ� ����
	@Override
	public void update(BoardVO vo) throws Exception {
		SqlSession.update("board.updateArticle",vo);
	}

	//04 �Խñ� ����
	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.delete("board.deleteArticle",bno);
	}

	//05 �Խñ� ��ü���
	@Override
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception {
		//�˻� �ɼ�, Ű���� �ʿ� ����
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectList("board.listAll", map);
		//return SqlSession.selectList(namespace+".listAll", map);
	}

	//06 �Խñ� ��ȸ�� ����
	@Override
	public void increaseViewcnt(int bno) throws Exception {
		// TODO Auto-generated method stub
		SqlSession.update("board.increaseViewcnt",bno);
	}

	//07 �Խñ� ���ڵ� ����
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		// TODO Auto-generated method stub
		//�˻��ɼ�, Ű���� �ʿ� ����
		Map<String, String> map=new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return SqlSession.selectOne("board.countArticle", map);
	}

/*	//����¡ ó��
	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0) {
			page=1;
		}
		page = (page-1) * 10;
		return SqlSession.selectList(namespace + ".listPage" , page);
	}*/

	//����¡ ó�� ��ü Criteria ���Ե� list ���
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return SqlSession.selectList(namespace+".listCriteria", cri);
	}

	//totalCount �����Ͽ� ���� �Խù��� ����¡ ó��
	@Override
	public int countPaging(Criteria cri) throws Exception {
		return SqlSession.selectOne(namespace+".countPaging",cri);
	}

	//�˻�ó��-����SQL, ����¡ ó��
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return SqlSession.selectList("board.listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return SqlSession.selectOne("board.listSearchCount", cri);
	}
}
