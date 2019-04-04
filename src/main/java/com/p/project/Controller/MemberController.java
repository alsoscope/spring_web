package com.p.project.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.type.Alias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.p.project.DTO.MemberDTO;
import com.p.project.NaverLogin.NaverLoginBO;
import com.p.project.Service.MemberService;
import com.p.project.VO.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//���� Ŭ������ controller bean�� ��Ͻ�Ŵ
@RequestMapping("member/*")
@Controller
public class MemberController {

	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	//MemberService ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	MemberService memberService;

	//�α��� ȭ��
	/*@RequestMapping(value="member/loginGET", method=RequestMethod.GET)
	public String loginGET() {
		return "member/loginGET";
	}*/
	
	//�α��� ó��
	@RequestMapping(value="loginPost", method=RequestMethod.POST )
	public void loginPOST(Model model, HttpSession session, MemberDTO dto) throws Exception {
		//String returnURL = "";
		
		logger.info("LoginPost");
		
		/*if(session.getAttribute("login")!=null) { //���� login�̶� ���ǰ� �����ϸ� ������ ����
			session.removeAttribute("login");
			//returnURL = "redirect:/"; //return false; , return undefined;
		}*/
		
		try{
			MemberVO vo=memberService.login(dto); //�α����� �����ϸ� MemberVO ��ü ��ȯ

			if(vo == null) {
				return;
			}

			model.addAttribute("MemberVO",vo);
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		/*if(vo != null) { //�α��� ����
			session.setAttribute("login", vo);
			returnURL="redirect:/member/member_list";
		} else {
			returnURL="redirect:/";
		}*/
		
		/*else {
			session.setAttribute("MemberVO", vo);
			returnURL = "redirect:member_list";
		}*/
		
/*		if (dto.isUsecookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionlimit = new Date(System.currentTimeMillis() + (1000 * amount));
			
			memberService.updateForCookie(vo.getuserId(), session.getId(), sessionlimit);
		}*/
		
		//return returnURL;
	}
	
	//�α׾ƿ�
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Object obj=session.getAttribute("login");
		
		if(obj!=null) {
			MemberVO vo=(MemberVO)obj;
			
			session.removeAttribute("login");
			session.invalidate();
		}
		logger.info("Logout Success");
		return "redirect:/";
	}
	
	//01 ȸ�����
	//url pattern mapping
	@RequestMapping("list")
	public String memberList(Model model) {
		//controller->service->dao ��û
		List<MemberVO> list=memberService.memberList();
		model.addAttribute("list",list);
		return "member/member_list";
	}
	
	//02_01 ȸ�� ��� �������� �̵�
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String memberWrite() {
		return "member/register";
	}
	
	//02_02 ȸ�� ��� ó��
	//@ModelAttribute�� ������ �Է��� �����Ͱ� ����ȴ�
	//*������ �Է��� �����͸� �޾ƿ��� �� 3����
	//public String memberInsert(HttpServlet request){}
	//public String memberInsert(String userId, String userPw, String userName){}
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute MemberVO vo, Model model) {
		logger.info("current join member : "+vo.toString());
		//���̺� ���ڵ� �Է�
		memberService.insertMember(vo);
		model.addAttribute("vo", vo);
		//* (/)������ ����
		// /member/list.do : ��Ʈ ���丮�� ����
		// member/list.do : ���� ���丮�� ����
		// member_list.jsp �� �����̷�Ʈ
		return "member/register_confirm";
	}
	
	//03 ȸ�� ������ ��ȸ
	@RequestMapping("view")
	public String memberView(String userId, Model model) {
		//ȸ�� ������ model�� ����
		model.addAttribute("dto", memberService.viewMember(userId));
		//System.out.println("Ŭ���� ���̵� Ȯ��:"+userId);
		logger.info("Ŭ���� ���̵� : "+userId);
		return "member/member_view";
	}
	
	//04 ȸ�� ���� ���� ó��
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		if(result) {//��й�ȣ�� ��ġ�ϸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.updateMember(vo);
			return "redirect:/member/list.do";			
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ�, div�� ����ġ ���� ���, view,jsp�� ������
			//�ٽ� ������ ȭ���� ����ϱ� ���ؼ� �������ڿ� �������� �׸��� ����ġ ������ model�� ����, ��ȭ������ ������
			//��������, �������� ����
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			return "member/member_view";
		}
	}
	
	//05 ȸ������ ���� ó��
	//@RequestMapping:url mapping
	//@RequestParam:get or post ������� ���޵� ������
	@RequestMapping("delete")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //��й�ȣ�� �´ٸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ� div�� ����ġ �������, view.jsp�� ������
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/member_view";
		}
	}
}//MemberController
