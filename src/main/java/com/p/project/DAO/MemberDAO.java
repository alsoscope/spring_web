package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.OrderDTO;
import com.p.project.VO.MemberVO;

//�⺻ �޼ҵ� ����
public interface MemberDAO {
	//ȸ�����
	public List<MemberVO> memberList();
	//ȸ���Է�
	public void insertMember(MemberVO vo);
	
	//ȸ�� -----------------------------------------
	//ȸ�� ���� �󼼺���
	public MemberVO viewMember(String userId);
	//ȸ�� ����
	public void deleteMember(String userId);
	//ȸ������ ����
	public void updateMember(MemberVO vo);
	//ȸ�� -----------------------------------------
	
	//ȸ������ ����, ������ ���� ��й�ȣ üũ
	public boolean checkPw(String userId, String userPw);
	//�α���
	public MemberVO login(MemberDTO dto) throws Exception;

	//�ֹ� ���̺� Ȯ��
	public List<OrderDTO> selectOrder(OrderDTO vo) throws Exception;
	
	//�ֹ� ���̺� Ȯ��
	public List<OrderDTO> selectExprDate(OrderDTO vo) throws Exception;
}
