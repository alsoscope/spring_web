package com.p.project.Controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p.project.DTO.AdminDTO;
import com.p.project.Service.AdminService;

@Controller
@RequestMapping("admin/*")
public class AdminController {

	@Inject
	AdminService adminService;
	
	//1.������ �α��� ������
	@RequestMapping("adminlogin")
	public String adminLogin() {
		return "admin/adminLogin"; //�α��� �������� ������
	}
	
	//2.������ �α��� üũ
	@RequestMapping("logincheck")
	public String logincheck(Model model, HttpSession session, AdminDTO admin_vo) {
		String name=adminService.adminlogin(admin_vo);
		
		//�α��� ����
		if(name!=null) {
			session.setAttribute("adminId", admin_vo.getAdmin_id());
			session.setAttribute("adminPw", admin_vo.getAdmin_pw());
			/*session.setAttribute("adminName", adminName);
			session.setAttribute("userName", userName);*/
			model.addAttribute("msg", "success");
			//������ �α��� �����ϸ� success ���ڿ� ����, ������ ����  �������� ������
		
			//�α��� �����ϸ� �α��� �������� ������, failure ���ڿ� ����
		}else{
			model.addAttribute("msg","failure");
			
		}
		return "admin/adminHome"; //������ �������� �̵�
	}
	
	//3.������ �α׾ƿ�
	@RequestMapping("adminlogout")
	public String logout(HttpSession session,Model model) {
		session.invalidate(); //�α׾ƿ� ó�� �� �α��� �������� ������, logout���ڿ� ����
		model.addAttribute("msg", "logout");
		return "admin/adminLogin";
	}
	
}//AdminController--------
