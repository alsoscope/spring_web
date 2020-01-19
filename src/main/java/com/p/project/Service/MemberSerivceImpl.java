package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;

import com.p.project.DAO.MemberDAOImpl;
import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.OrderDTO;
import com.p.project.VO.MemberVO;

import org.springframework.stereotype.Service;

//현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberSerivceImpl implements MemberService {

	//MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
	@Inject
	MemberDAOImpl memberDao;
	
	//회원 목록
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}

	//회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	//회원 정보 상세 조회
	@Override
	public MemberVO viewMember(String userId) {
		return memberDao.viewMember(userId);
	}

	//회원 정보 삭제 처리
	@Override
	public void deleteMember(String userId) {
		memberDao.deleteMember(userId);
	}
	
	//회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}

	//회원정보 수정, 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		return memberDao.checkPw(userId, userPw);
	}

	//로그인
	@Override
	public MemberVO login(MemberDTO dto) throws Exception {
		return memberDao.login(dto);
	}
	
	@Override
	public List<OrderDTO> selectOrder(OrderDTO vo) throws Exception{
		return memberDao.selectOrder(vo);
	}

	@Override
	public List<OrderDTO> selectExprDate(OrderDTO vo) throws Exception {
		return memberDao.selectExprDate(vo);
	}

}
