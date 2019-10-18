package com.p.project.Service;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;
import com.p.project.DAO.BoardDAO;;

//����Ͻ� ����. �䱸������ �޼ҵ�� �����ؼ� �������̽� ����
//Service ����Ͻ� ����, DB���� �̿��� �۾�
//�������̽�
public interface BoardService {
	//CRUD
	//�� �ۼ�
	public void create(BoardVO vo) throws Exception;
	//�� ����
	public BoardVO read(int bno) throws Exception;
	// ����
	public void update(BoardVO dto) throws Exception;
	//����
	public void delete(int bno) throws Exception;
	
	//�Խñ� ��ü ��� --> �˻� �ɼ�, Ű���� �Ű����� �߰�
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception;
	//�Խñ� ��ȸ�� ����
	public void increaseViewcnt(int bno, HttpSession session)throws Exception;
	//�� �Խù� ���� Ȯ��
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	//����¡ ó�� �޼ҵ� �߰�
	/*public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	//���� page�� count
	public int listCountCriteria(Criteria cri) throws Exception;*/
	
	//����SQL�� �˻� ó��
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
}
