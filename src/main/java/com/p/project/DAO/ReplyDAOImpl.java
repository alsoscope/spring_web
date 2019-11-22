package com.p.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace ="reply";
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert("reply.create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	//페이징 처리
	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listPage", paramMap);
	}

	//페이징 처리를 위한 전체 댓글 목록 개수
	@Override
	public int count(int bno) throws Exception {
		return session.selectOne("reply.count", bno);
	}

	//replyer 비밀번호 확인
	@Override
	public boolean pwConfirm(String replyer, String replyPw) throws Exception {
		boolean result=false;
		
		Map<String, String> hashMap=new HashMap<String, String>();
		hashMap.put("replyer", replyer);
		hashMap.put("replyPw", replyPw);
		
		int count=session.selectOne("reply.pwConfirm", hashMap);
		
		if(count==1) {
			result=true;
		}
		return result;
		
		/*int result=session.selectOne("reply.pwConfirm", hashMap);

		if(result>0) {
			return true; 
		}else {
			return false;
		}*/
	}

}
