package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.p.project.DTO.BoardVO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.SearchCriteria;
import com.p.project.DAO.BoardDAO;

//�������̽� ���� Ŭ����. ������ü.
//�Խñ� ���� ó��(�±׹���, ���鹮��, �ٹٲ� ���� ó��)
//�Խñ� ��ȸ�� ���� ó��(���� �ð����� ��ȸ�� �������� �ʵ��� ó��)

//@Service : �ּ� �� Ŭ������ ����Ͻ� ������ ���� ���� ������� ��Ÿ���ϴ�.
//��� �� ���� ������ DB ���ῡ @Service ���
//Ŭ���� ����ο��� �������� ������ �νĵǱ� ���� @Service ������̼� ����
//������ CRUD �۾���, DAO�� �̿��ؼ� �۾��� ����
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	//01 �Խñ� ����
	@Override
	public void create(BoardVO vo) throws Exception {
		String title=vo.getTitle();
		String content=vo.getContent();
		String writer=vo.getWriter();
		//*�±׹���ó��  (< == > &lt; > == > &gt;)
		//replace(A,B) A�� B�� ����
		title=title.replace("<", "&lt;");
		title=title.replace("<", "&gt;");
		writer=writer.replace("<", "&lt;");
		writer=writer.replace("<", "&gt;");
		
		//*���鹮�� ó��
		title=title.replace(" ","&nbsp;&nbsp;");
		writer=writer.replace(" ","&nbsp;&nbsp;");
		
		//*�ٹٲ� ���� ó��
		content=content.replace("\n", "<br>");
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		boardDao.create(vo);
	}

	//02 �Խñ� �󼼺���
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	//03 �Խñ� ����
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
	}

	//04 �Խñ� ����
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	//05 �Խñ� ��ü ��� boardDAO.listAll �޼ҵ� ȣ��
	@Override
	public List<BoardVO> listAll(String searchOption, String keyword) throws Exception {
		return boardDao.listAll(searchOption, keyword);
	}

	//06 �Խñ� ��ȸ�� ����
	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time=0;
		//���ǿ� ����� ��ȸ�ð� �˻�
		//���ʷ� ��ȸ�� ��� ���ǿ� ����� ���� ���� ������ if���� ����x
		if(session.getAttribute("update_time_"+bno)!=null) {
			//���ǿ��� �о����
			update_time=(long)session.getAttribute("update_time_"+bno);
		}
		//�ý����� ����ð��� current_time�� ����
		long current_time=System.currentTimeMillis();
		//�����ð��� ��� �� ��ȸ�� ���� ó�� 24*60*60*1000(24�ð�)
		//�ý��� ����ð�-�����ð� > �����ð�(��ȸ�� ������ �����ϵ��� ������ �ð�)
		if(current_time-update_time>5*1000) {
			boardDao.increaseViewcnt(bno);
			//���ǿ� �ð��� ����:"update_time_"+bno�� �ٸ� ������ �ߺ����� �ʰ� ����� ��
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	//�� �Խù� ���� Ȯ��
	@Override
	public int countArticle(String searchOption, String keyword) throws Exception {
		return boardDao.countArticle(searchOption, keyword);
	}

	//�˻�ó��, ����¡ ó�� (���� SQL��, SearchCriteria ����)
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return boardDao.listSearch(cri);
	}
	
	//�˻�ó��, ����¡ ó�� (���� SQL��, SearchCriteria ����)
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return boardDao.listSearchCount(cri);
	}

	//����¡ ó��
	/*@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardDao.listCriteria(cri);
	}*/

	/*@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return boardDao.countPaging(cri);
	}*/


}
