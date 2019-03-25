package com.p.project.Service;

import java.util.List;

import com.p.project.DTO.MemberDTO;
import com.p.project.VO.MemberVO;

public interface MemberService {
	//ȸ�����
	public List<MemberVO> memberList();
	//ȸ���Է�
	public void insertMember(MemberVO vo);
	//ȸ������ �󼼺���
	public MemberVO viewMember(String userId);
	//ȸ������
	public void deleteMember(String userId);
	//ȸ������ ����
	public void updateMember(MemberVO vo);
	//ȸ������ ���� �� ������ ���� ��й�ȣ üũ
	public boolean checkPw(String userId, String userPw);
	//�α���
	public MemberVO login(MemberDTO dto) throws Exception;
}