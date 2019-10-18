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

//현재 클래스를 controller bean에 등록시킴
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
	
	//MemberService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	MemberService memberService;

	//시작 페이지 mapping 변경
	@RequestMapping(value = "loginGET", method = RequestMethod.GET)
	public String loginGET(Model model, HttpSession session) {
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		/*example
		https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125*/
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=CcHDOhNulVa7_LfwCQeM&redirect_uri=http%3A%2F%2Flocalhost%3A9000%2Fmember%2Fcallback&state=5c52aaf8-2789-4483-8219-832f107e181a
		//redirect_uri=http://localhost:9000/member/callback?code=VxOZw70VRZk5ih2wvt&state=3ae3e30b-83b5-47a8-b6b2-85154490778d
		System.out.println("네이버:" + naverAuthUrl);
		
		//네이버 
		model.addAttribute("url", naverAuthUrl);
		
		return "member/loginGET";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("------------- 네이버 로그인 callback 실행 -------------");
		
		//네이로 인증이 성공적으로 완료되면 code파라미터가 전달되며 이를 통해 access token 발급
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);

		/*apiResult json구조
		{"resultcode":"00","message":"success",
			"response":{"id":"77718494","email":"j_adore825@naver.com","name":"\uae40\ubbf8\uc120"}}*/
		
		//String형식인 apiResult를 json형태로 바꿈. Object안에 Object가 있는 경우.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj=(JSONObject)obj;
		
		//데이터 파싱
		//Top 레벨 단계. response 파싱
		JSONObject responseObj=(JSONObject)jsonObj.get("response");

		//response안에 있는 객체 파싱
		System.out.println(responseObj);
		System.out.println("email : " + (String)responseObj.get("email"));
		System.out.println("name : " + (String)responseObj.get("name"));
		
		String naverEmail=(String)responseObj.get("email");
		String naverName=(String)responseObj.get("name");
		
		//파싱한 객체를 세션에 저장
		session.setAttribute("naverEmail", naverEmail);
		session.setAttribute("naverName", naverName);
		
        /* 네이버 로그인 성공 페이지 View 호출 */
		return "member/register_confirm";
	}
	
	//로그인 처리
	@RequestMapping(value="loginPOST", method=RequestMethod.POST )
	public void loginPOST(Model model, HttpSession session, MemberDTO dto) throws Exception {
		//String returnURL = "";
		
		logger.info("로그인 시도한 유저 : " + dto);
		
		/*if(session.getAttribute("login")!=null) { //기존 login이란 세션값 존재하면 기존값 제거
			session.removeAttribute("login");
			//returnURL = "redirect:/"; //return false; , return undefined;
		}*/
		
		try{
			MemberVO vo=memberService.login(dto); //로그인이 성공하면 MemberVO 객체 반환

			/*if(vo == null) {
				return;
			}*/

			session.setAttribute("userId", dto.getUserId());
			
			model.addAttribute("MemberVO",vo);
			logger.info("로그인 성공한 유저 : " + vo);	
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		/*if(vo != null) { //로그인 성공
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
	
	//로그아웃
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
	
	//회원가입 페이지
	@RequestMapping(value="membership",method=RequestMethod.GET)
	public String memberWrite() {
		return "member/membership";
	}
	
	//회원가입 처리POST
	//@ModelAttribute에 폼에서 입력한 데이터가 저장된다
	//*폼에서 입력한 데이터를 받아오는 법 3가지
	//public String memberInsert(HttpServlet request){}
	//public String memberInsert(String userId, String userPw, String userName){}
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute MemberVO vo, Model model) {
		logger.info("새로운 회원가입 : "+vo.toString());
		//테이블에 레코드 입력
		memberService.insertMember(vo);
		model.addAttribute("vo", vo);
		//* (/)유무의 차이
		// /member/list.do : 루트 디렉토리를 기준
		// member/list.do : 현재 디렉토리를 기준
		// member_list.jsp 로 리다이렉트
		return "member/register_confirm";
	}
	
	//관리자---------------------------------------------------------------------
	//관리자 - 회원목록 URL Pattern Mapping
	@RequestMapping("member_list")
	public String memberList(Model model) {
		//controller->service->dao 요청
		List<MemberVO> list=memberService.memberList();
		model.addAttribute("list",list);
		return "member/member_list";
	}
	
	//관리자 - 회원 정보 조회
	@RequestMapping("adminView")
	public String adminView(String userId, Model model) {
		//회원 정보를 model에 저장
		model.addAttribute("dto", memberService.viewMember(userId));
		//System.out.println("클릭한 아이디 확인:"+userId);
		logger.info("클릭한 아이디 : "+userId);
		return "member/adminView";
	}//memberView
	
	//관리자 - 회원 정보 수정
	@RequestMapping(value="adminUpdate", method=RequestMethod.POST)
	public String adminUpdate(@ModelAttribute MemberVO vo, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		if(result) {//비밀번호가 일치하면 수정 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.updateMember(vo);
			return "redirect:/member/list";			
		}else {//비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, view,jsp로 포워드
			//다시 동일한 화면을 출력하기 위해서 가입일자와 수정일자 그리고 불일치 문구를 model에 저장, 상세화면으로 포워드
			//가입일자, 수정일자 저장
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			return "member/adminView";
		}
	}//memberUpdate
	
	//관리자 - 회원 삭제
	//@RequestMapping:url mapping
	//@RequestParam:get or post 방식으로 전달된 변수값
	@RequestMapping("adminDelete")
	public String adminDelete(@RequestParam String userId, @RequestParam String userPw, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //비밀번호가 맞다면 삭제 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.deleteMember(userId);
			return "redirect:/member/list";
		}else {//비밀번호가 일치하지 않는다면 div에 불일치 문구출력, view.jsp로 포워드
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "redirect:/member/member_list";
		}
	}//memberDelete
	//관리자---------------------------------------------------------------------
	
	//회원----------------------------------------------------------------------
	//회원 - 회원정보 조회, 수정
	@RequestMapping("member_view")
	public String memberView(Model model, HttpSession session) {
		//현재 로그인 중인 아이디의 세션을 가져와서 회원정보 조회를 처리한다.
		String userId=(String)session.getAttribute("userId");
		//회원 정보를 model에 저장
		model.addAttribute("dto", memberService.viewMember(userId));
		logger.info("출력 아이디 : "+userId);
		return "member/member_view";
	}//memberView
	
	//회원 - 회원정보 수정 처리
	@RequestMapping(value="member_update", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		
		if(result) {//비밀번호가 일치하면 수정 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.updateMember(vo);
			logger.info("수정 성공 : " + vo);
			return "redirect:/";			
		}else {//비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, view,jsp로 포워드
			//다시 동일한 화면을 출력하기 위해서 가입일자와 수정일자 그리고 불일치 문구를 model에 저장, 상세화면으로 포워드
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			return "member/member_view";
		}
	}//memberUpdate
	
	//회원 - 회원탈퇴
	@RequestMapping("member_delete")
	public String memberDelete(@RequestParam String userId, String userPw, Model model, HttpSession session) {
		//비밀번호 체크
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //비밀번호가 맞다면 삭제 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.deleteMember(userId);
			logger.info("삭제 계정 : " + userId);
			session.invalidate();
			return "redirect:/";
		}else {//비밀번호가 일치하지 않는다면 div에 불일치 문구출력, view.jsp로 포워드
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/member_view";
		}
	}//memberDelete
	//회원----------------------------------------------------------------------
	
}//MemberController
