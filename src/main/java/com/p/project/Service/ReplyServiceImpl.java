package com.p.project.Service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ReplyVO;
import com.p.project.DAO.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
	
		boolean result=dao.pwConfirm(vo.getReplyer(), vo.getReplyPw());
		if(result) {
			/*int rno = vo.getRno();
			vo.setRno(rno);*/
			dao.update(vo);
		}else {
			//model.addAttribute("message", "비밀번호가 맞지 않습니다.");
		}
	}

	@Override
	public void removeReply(int rno) throws Exception {
		dao.delete(rno);
	}

	//페이징 처리
	@Override
	public List<ReplyVO> listReplyPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(int bno) throws Exception {
		return dao.count(bno);
	}

	//replyer 비밀번호 확인
	@Override
	public boolean pwConfirm(String replyer, String replyPw) throws Exception {
		return dao.pwConfirm(replyer, replyPw);
	}

}
