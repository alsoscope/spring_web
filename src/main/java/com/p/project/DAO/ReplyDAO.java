package com.p.project.DAO;

import java.util.List;
import java.util.Map;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(int bno) throws Exception;
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(int rno) throws Exception;
	
	//페이징처리
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception;
	public int count(int bno) throws Exception;
	
	//replyer 비밀번호 확인
	public boolean pwConfirm(String replyer, String replyPw) throws Exception;
}
