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
	
	//1.관지라 로그인 페이지
	@RequestMapping("adminlogin")
	public String adminLogin() {
		return "admin/adminLogin"; //로그인 페이지로 포워드
	}
	
	//2.관리자 로그인 체크
	@RequestMapping(value="logincheck")
	/*public String logincheck(Model model, HttpSession session, MemberVO vo, ModelAndView mav) {*/
	public ModelAndView logincheck(Model model, HttpSession session, MemberVO vo, ModelAndView mav, HttpServletResponse response) throws Exception {
		String name=adminService.adminlogin(vo);
		
		//로그인 성공
		if(name!=null) {
			//session.setAttribute("userId", vo.getUserId());
			session.setAttribute("adminId", vo.getUserId());
			
			//session.setAttribute("userName", name);
			//session.setAttribute("adminName", name);
			
			session.setMaxInactiveInterval(86400); //특정 세션만 타임아웃 설정. 초 단위.
			
			mav.setViewName("admin/adminHome");
			mav.addObject("msg" , "success");
			
			/*model.addAttribute("msg", "success");*/
			//관리자 로그인 성공하면 success 문자열 리턴, 관리자 메인  페이지로 포워딩
		}else{
			//로그인 실패하면 로그인 페이지로 포워딩, failure 문자열 리턴
			/*model.addAttribute("msg","failure");*/
			mav.setViewName("admin/adminLogin");
			mav.addObject("msg" , "failure");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자 로그인 정보가 아닙니다.');</script>");
			out.flush();
		}
		return mav;
		//return "admin/adminHome"; //관리자 페이지로 이동
	}
	
	//3.관리자 로그아웃
	@RequestMapping("adminlogout")
	public String logout(HttpSession session,Model model) {
		session.invalidate(); //로그아웃 처리 후 로그인 페이지로 포워딩, logout문자열 리턴
		model.addAttribute("msg", "logout");
		return "admin/adminLogin";
	}
	
}//AdminController--------
