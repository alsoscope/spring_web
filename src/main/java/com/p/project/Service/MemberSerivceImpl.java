package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;

import com.p.project.DAO.MemberDAOImpl;
import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.OrderDTO;
import com.p.project.VO.MemberVO;

import org.springframework.stereotype.Service;

//���� Ŭ������ ���������� �����ϴ� service bean���� ���
@Service
public class MemberSerivceImpl implements MemberService {

	//MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	MemberDAOImpl memberDao;
	
	//ȸ�� ���
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}

	//ȸ�� ���
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	//ȸ�� ���� �� ��ȸ
	@Override
	public MemberVO viewMember(String userId) {
		return memberDao.viewMember(userId);
	}

	//ȸ�� ���� ���� ó��
	@Override
	public void deleteMember(String userId) {
		memberDao.deleteMember(userId);
	}
	
	//ȸ�� ���� ���� ó��
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}

	//ȸ������ ����, ������ ���� ��й�ȣ üũ
	@Override
	public boolean checkPw(String userId, String userPw) {
		return memberDao.checkPw(userId, userPw);
	}

	//�α���
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
