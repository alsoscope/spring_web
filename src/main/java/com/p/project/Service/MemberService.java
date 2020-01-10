package com.p.project.Service;

import java.util.List;

import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.OrderDTO;
import com.p.project.VO.MemberVO;

public interface MemberService {
	//회원목록
	public List<MemberVO> memberList();
	//회원입력
	public void insertMember(MemberVO vo);
	//회원정보 상세보기
	public MemberVO viewMember(String userId);
	//회원삭제
	public void deleteMember(String userId);
	//회원정보 수정
	public void updateMember(MemberVO vo);
	//회원정보 수정 및 삭제를 위한 비밀번호 체크
	public boolean checkPw(String userId, String userPw);
	//로그인
	public MemberVO login(MemberDTO dto) throws Exception;
	
	//주문 테이블 확인
		public List<OrderDTO> selectOrder(OrderDTO vo) throws Exception;
}
