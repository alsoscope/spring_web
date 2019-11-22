package com.p.project.Service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ReplyVO;

public interface ReplyService {
	
	public void addReply(ReplyVO vo) throws Exception;
	public List<ReplyVO> listReply(int bno) throws Exception; 
	public void modifyReply(ReplyVO vo) throws Exception;
	public void removeReply(int rno) throws Exception;
	
	//페이징 처리
	public List<ReplyVO> listReplyPage(int bno, Criteria cri)throws Exception;
	public int count(int bno) throws Exception;
	
	//replyer 비밀번호 확인
	public boolean pwConfirm(String replyer, String replyPw) throws Exception;
	
}//interface
