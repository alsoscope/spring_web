package com.p.project.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.p.project.DTO.AdminDTO;
import com.p.project.Service.AdminService;
import com.p.project.VO.MemberVO;

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
	@RequestMapping(value="logincheck")
	/*public String logincheck(Model model, HttpSession session, MemberVO vo, ModelAndView mav) {*/
	public ModelAndView logincheck(Model model, HttpSession session, MemberVO vo, ModelAndView mav, HttpServletResponse response) throws Exception {
		String name=adminService.adminlogin(vo);
		
		//�α��� ����
		if(name!=null) {
			//session.setAttribute("userId", vo.getUserId());
			session.setAttribute("adminId", vo.getUserId());
			
			//session.setAttribute("userName", name);
			//session.setAttribute("adminName", name);
			
			session.setMaxInactiveInterval(86400); //Ư�� ���Ǹ� Ÿ�Ӿƿ� ����. �� ����.
			
			mav.setViewName("admin/adminHome");
			mav.addObject("msg" , "success");
			
			/*model.addAttribute("msg", "success");*/
			//������ �α��� �����ϸ� success ���ڿ� ����, ������ ����  �������� ������
		}else{
			//�α��� �����ϸ� �α��� �������� ������, failure ���ڿ� ����
			/*model.addAttribute("msg","failure");*/
			mav.setViewName("admin/adminLogin");
			mav.addObject("msg" , "failure");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �α��� ������ �ƴմϴ�.');</script>");
			out.flush();
		}
		return mav;
		//return "admin/adminHome"; //������ �������� �̵�
	}
	
	//3.������ �α׾ƿ�
	@RequestMapping("adminlogout")
	public String logout(HttpSession session,Model model) {
		session.invalidate(); //�α׾ƿ� ó�� �� �α��� �������� ������, logout���ڿ� ����
		model.addAttribute("msg", "logout");
		return "admin/adminLogin";
	}
	
}//AdminController--------
