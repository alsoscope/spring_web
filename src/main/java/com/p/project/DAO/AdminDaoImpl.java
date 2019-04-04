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
	
	//관리자 로그인 체크. 관리자 테이블을 select조회한 결과 리턴
	@Override
	public String adminlogin(MemberVO vo) {
		return sqlSession.selectOne("admin.adminlogin", vo);
	}

}
