package com.p.project.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.p.project.DAO.AdminDAO;
import com.p.project.DTO.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Inject
	AdminDAO adminDao;
	
	//������ �α��� üũ. adminDao�� �α��� üũ �޼��� ȣ��
	@Override
	public String adminlogin(AdminDTO admin_vo) {
		return adminDao.adminlogin(admin_vo);
	}

}
