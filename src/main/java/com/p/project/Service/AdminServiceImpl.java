package com.p.project.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.p.project.DAO.AdminDAO;
import com.p.project.DTO.AdminDTO;
import com.p.project.VO.MemberVO;

@Service
public class AdminServiceImpl implements AdminService{

	@Inject
	AdminDAO adminDao;
	
	//������ �α��� üũ. adminDao�� �α��� üũ �޼��� ȣ��
	@Override
	public String adminlogin(MemberVO vo) {
		return adminDao.adminlogin(vo);
	}

}
