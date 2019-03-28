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
	
	//1.관지라 로그인 페이지
	@RequestMapping("adminlogin")
	public String adminLogin() {
		return "admin/adminLogin"; //로그인 페이지로 포워드
	}
	
	//2.관리자 로그인 체크
	@RequestMapping("logincheck")
	public String logincheck(Model model, HttpSession session, AdminDTO admin_vo) {
		String name=adminService.adminlogin(admin_vo);
		
		//로그인 성공
		if(name!=null) {
			session.setAttribute("adminId", admin_vo.getAdmin_id());
			session.setAttribute("adminPw", admin_vo.getAdmin_pw());
			/*session.setAttribute("adminName", adminName);
			session.setAttribute("userName", userName);*/
			model.addAttribute("msg", "success");
			//관리자 로그인 성공하면 success 문자열 리턴, 관리자 메인  페이지로 포워딩
		
			//로그인 실패하면 로그인 페이지로 포워딩, failure 문자열 리턴
		}else{
			model.addAttribute("msg","failure");
			
		}
		return "admin/adminHome"; //관리자 페이지로 이동
	}
	
	//3.관리자 로그아웃
	@RequestMapping("adminlogout")
	public String logout(HttpSession session,Model model) {
		session.invalidate(); //로그아웃 처리 후 로그인 페이지로 포워딩, logout문자열 리턴
		model.addAttribute("msg", "logout");
		return "admin/adminLogin";
	}
	
}//AdminController--------
