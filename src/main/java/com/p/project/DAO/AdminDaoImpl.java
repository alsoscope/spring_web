package com.p.project.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.AdminDTO;
import com.p.project.VO.MemberVO;

@Repository
public class AdminDaoImpl implements AdminDAO{

	@Inject
	SqlSession sqlSession;
	
	//������ �α��� üũ. ������ ���̺��� select��ȸ�� ��� ����
	@Override
	public String adminlogin(MemberVO vo) {
		return sqlSession.selectOne("admin.adminlogin", vo);
	}

}
