package com.p.project.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.AdminDTO;

@Repository
public class AdminDaoImpl implements AdminDAO{

	@Inject
	SqlSession sqlSession;
	
	//������ �α��� üũ. ������ ���̺��� select��ȸ�� ��� ����
	@Override
	public String adminlogin(AdminDTO admin_vo) {
		return sqlSession.selectOne("admin.adminlogin", admin_vo);
	}

}
