package com.p.project.DAO;

import java.util.List;
import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;

//�������̽�
public interface BoardDAO {
	//CRUD
	//�� �ۼ�
	public void create(BoardVO vo)throws Exception;
	//�� ����
	public BoardVO read(int bno) throws Exception;
	//����
	public void update(BoardVO vo)throws Exception;
	//����
	public void delete(int bno)throws Exception;
	
	//�Խñ� ��ü ��� --> �˻��ɼ�, Ű���� �Ű����� �߰�
	public List<BoardVO> listAll(String searchOptin, String keyword)throws Exception;
	//�Խñ� ��ȸ�� ����
	public void increaseViewcnt(int bno)throws Exception;
	//�� �Խù� ���� Ȯ��
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	//criteria ��ü�� �Ķ���ͷ� ���� �޾�, BoardDAO�� ����Ʈ�� ��� getPageStart(), getPerPageNum() �޼ҵ� ȣ��
	/*public List<BoardVO> listCriteria(Criteria cri)throws Exception;*/
	//totalCount ��ȯ�Ͽ� ���� ����¡ ó��
	/*public int countPaging(Criteria cri)throws Exception;*/
	
	//�˻�ó���� ����SQL. �˻��� ����¡ ó��
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;

	/*//����¡ ó�� public List<BoardVO> listPage(int page)throws Exception;*/
}
