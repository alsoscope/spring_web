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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.mysql.cj.xdevapi.JsonParser;
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
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	//MemberService ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
	@Inject
	MemberService memberService;

	//���� ������ mapping ����
	@RequestMapping(value = "loginGET", method = RequestMethod.GET)
	public String loginGET(Model model, HttpSession session) {
		
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		/*example
		https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125*/
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=CcHDOhNulVa7_LfwCQeM&redirect_uri=http%3A%2F%2Flocalhost%3A9000%2Fmember%2Fcallback&state=5c52aaf8-2789-4483-8219-832f107e181a
		//redirect_uri=http://localhost:9000/member/callback?code=VxOZw70VRZk5ih2wvt&state=3ae3e30b-83b5-47a8-b6b2-85154490778d
		System.out.println("���̹�:" + naverAuthUrl);
		
		//���̹� 
		model.addAttribute("url", naverAuthUrl);
		
		return "member/loginGET";
	}
	
	//���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("------------- ���̹� �α��� callback ���� -------------");
		
		//���̷� ������ ���������� �Ϸ�Ǹ� code�Ķ���Ͱ� ���޵Ǹ� �̸� ���� access token �߱�
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
        //�α��� ����� ������ �о�´�.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

		/*apiResult json����
		{"resultcode":"00","message":"success",
			"response":{"id":"77718494","email":"j_adore825@naver.com","name":"\uae40\ubbf8\uc120"}}*/
		
		//String������ apiResult�� json���·� �ٲ�. Object�ȿ� Object�� �ִ� ���.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj=(JSONObject)obj;
		
		//������ �Ľ�
		//Top ���� �ܰ�. response �Ľ�
		JSONObject responseObj=(JSONObject)jsonObj.get("response");

		//response�ȿ� �ִ� ��ü �Ľ�
		System.out.println(responseObj);
		System.out.println("email : " + (String)responseObj.get("email"));
		System.out.println("name : " + (String)responseObj.get("name"));
		
		String naverEmail=(String)responseObj.get("email");
		String naverName=(String)responseObj.get("name");
		
		//�Ľ��� ��ü�� ���ǿ� ����
		session.setAttribute("naverEmail", naverEmail);
		session.setAttribute("naverName", naverName);
		
        /* ���̹� �α��� ���� ������ View ȣ�� */
		return "member/register_confirm";
	}
	
	//�α��� ó��
	@RequestMapping(value="loginPOST", method=RequestMethod.POST )
	public void loginPOST(Model model, HttpSession session, MemberDTO dto) throws Exception {
		//String returnURL = "";
		
		logger.info("�α��� �õ��� ���� : " + dto);
		
		/*if(session.getAttribute("login")!=null) { //���� login�̶� ���ǰ� �����ϸ� ������ ����
			session.removeAttribute("login");
			//returnURL = "redirect:/"; //return false; , return undefined;
		}*/
		
		try{
			MemberVO vo=memberService.login(dto); //�α����� �����ϸ� MemberVO ��ü ��ȯ

			/*if(vo == null) {
				return;
			}*/

			session.setAttribute("userId", dto.getUserId());
			
			model.addAttribute("MemberVO",vo);
			logger.info("�α��� ������ ���� : " + vo);	
			
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
	public String logout(HttpSession session) throws Exception {
		Object obj=session.getAttribute("login");
		
		if(obj!=null) {
			MemberVO vo=(MemberVO)obj;
			
			session.removeAttribute("login");
			session.invalidate();
		}
		logger.info("Logout Success");
		return "redirect:/";
	}
	
	@RequestMapping(value="naverLogout")
	public String naverLogout(HttpSession session) {
		session.invalidate();
		logger.info("-----Naver Login Logout-----");
		return "redirect:/";
	}
	
	//ȸ������ ������
	@RequestMapping(value="membership",method=RequestMethod.GET)
	public String memberWrite() {
		return "member/membership";
	}
	
	//ȸ������ ó��POST
	//@ModelAttribute�� ������ �Է��� �����Ͱ� ����ȴ�
	//*������ �Է��� �����͸� �޾ƿ��� �� 3����
	//public String memberInsert(HttpServlet request){}
	//public String memberInsert(String userId, String userPw, String userName){}
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute MemberVO vo, Model model) {
		logger.info("���ο� ȸ������ : "+vo.toString());
		//���̺� ���ڵ� �Է�
		memberService.insertMember(vo);
		model.addAttribute("vo", vo);
		//* (/)������ ����
		// /member/list.do : ��Ʈ ���丮�� ����
		// member/list.do : ���� ���丮�� ����
		// member_list.jsp �� �����̷�Ʈ
		return "member/register_confirm";
	}
	
	//������---------------------------------------------------------------------
	//������ - ȸ����� URL Pattern Mapping
	@RequestMapping("member_list")
	public String memberList(Model model) {
		//controller->service->dao ��û
		List<MemberVO> list=memberService.memberList();
		model.addAttribute("list",list);
		return "member/member_list";
	}
	
	//������ - ȸ�� ���� ��ȸ
	@RequestMapping("adminView")
	public String adminView(String userId, Model model) {
		//ȸ�� ������ model�� ����
		model.addAttribute("dto", memberService.viewMember(userId));
		//System.out.println("Ŭ���� ���̵� Ȯ��:"+userId);
		logger.info("Ŭ���� ���̵� : "+userId);
		return "member/adminView";
	}//memberView
	
	//������ - ȸ�� ���� ����
	@RequestMapping(value="adminUpdate", method=RequestMethod.POST)
	public String adminUpdate(@ModelAttribute MemberVO vo, Model model) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		if(result) {//��й�ȣ�� ��ġ�ϸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.updateMember(vo);
			return "redirect:/member/list";			
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ�, div�� ����ġ ���� ���, view,jsp�� ������
			//�ٽ� ������ ȭ���� ����ϱ� ���ؼ� �������ڿ� �������� �׸��� ����ġ ������ model�� ����, ��ȭ������ ������
			//��������, �������� ����
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			return "member/adminView";
		}
	}//memberUpdate
	
	//������ - ȸ�� ����
	//@RequestMapping:url mapping
	//@RequestParam:get or post ������� ���޵� ������
	@RequestMapping("adminDelete")
	public String adminDelete(@RequestParam String userId, @RequestParam String userPw, Model model) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //��й�ȣ�� �´ٸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.deleteMember(userId);
			return "redirect:/member/list";
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ� div�� ����ġ �������, view.jsp�� ������
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "redirect:/member/member_list";
		}
	}//memberDelete
	//������---------------------------------------------------------------------
	
	//ȸ��----------------------------------------------------------------------
	//ȸ�� - ȸ������ ��ȸ, ����
	@RequestMapping("member_view")
	public String memberView(Model model, HttpSession session) {
		//���� �α��� ���� ���̵��� ������ �����ͼ� ȸ������ ��ȸ�� ó���Ѵ�.
		String userId=(String)session.getAttribute("userId");
		//ȸ�� ������ model�� ����
		model.addAttribute("dto", memberService.viewMember(userId));
		logger.info("��� ���̵� : "+userId);
		return "member/member_view";
	}//memberView
	
	//ȸ�� - ȸ������ ���� ó��
	@RequestMapping(value="member_update", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		
		if(result) {//��й�ȣ�� ��ġ�ϸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.updateMember(vo);
			logger.info("���� ���� : " + vo);
			return "redirect:/";			
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ�, div�� ����ġ ���� ���, view,jsp�� ������
			//�ٽ� ������ ȭ���� ����ϱ� ���ؼ� �������ڿ� �������� �׸��� ����ġ ������ model�� ����, ��ȭ������ ������
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			return "member/member_view";
		}
	}//memberUpdate
	
	//ȸ�� - ȸ��Ż��
	@RequestMapping("member_delete")
	public String memberDelete(@RequestParam String userId, String userPw, Model model, HttpSession session) {
		//��й�ȣ üũ
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //��й�ȣ�� �´ٸ� ���� ó�� ��, ��ü ȸ�� ������� �����̷�Ʈ
			memberService.deleteMember(userId);
			logger.info("���� ���� : " + userId);
			session.invalidate();
			return "redirect:/";
		}else {//��й�ȣ�� ��ġ���� �ʴ´ٸ� div�� ����ġ �������, view.jsp�� ������
			model.addAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/member_view";
		}
	}//memberDelete
	//ȸ��----------------------------------------------------------------------
	
}//MemberController
