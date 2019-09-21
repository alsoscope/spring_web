package com.p.project.Interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.p.project.VO.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String LOGIN="login";
	private static final Logger logger=LoggerFactory.getLogger(LoginInterceptor.class);
	
	//Controller로 요청이 들어가기 전 수행됨. 컨트롤러보다 먼저 수행됨.
	//preHandle() 메소드를 수행하고 수행될 컨트롤러 메서드에 대한 정보를 담고 있는 매개변수 handler
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		System.out.println("--------------preHandle--------------");

		//isDebugEnabled 메소드를 사용하면 로깅 문자열을 생성하기 전, 로깅 레벨을 점검한다. 로깅을 실행할 것인지 점검.
		if(logger.isDebugEnabled()) {
			logger.debug("Request URI \t:" + request.getRequestURI());
			//debug 모드일 때는 logger의 실행 레벨 체크를 두 번 한다는 단점. isDebugEnabled에서 한 번 체크, debug메소드에서 다시 점검.
			//debug모드에서 로깅 레벨 점검 후 debug 상태이면 로깅을 한다.
		}
		
		HttpSession session=request.getSession();//세션 객체를 가져옴
		
		if(session.getAttribute(LOGIN)!=null) { //새로 로그인 요청이 들어올 때 기존에 세션에 저장되어 있던 이전의 session LOGIN 사용자 정보 제거
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}		
		return true;
		//preHandle의 return은 컨트롤러 요청 uri로 가도 되냐/안되냐를 허가하는 의미, true는 컨트롤러 uri로 가게됨.
	}
		
	//컨트롤러의 메서드 처리가 끝나 return 되고 화면을 띄워주는 처리(view)가 되기 직전에 이 메서드가 수행된다
	//ModelAndView 객체에 컨트롤러에서 전달해 온 Model 객체가 전달됨으로 컨트롤러에서 작업 후 postHandle()에서 작업할 것이 있다면 ModelAndView 이용
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("--------------postHandle--------------");
		
		HttpSession session = request.getSession(); //(true);값과 같은 결과를 반환함
		
		ModelMap modelMap=modelAndView.getModelMap();
		Object MemberVO = modelMap.get("MemberVO");
				
		if(MemberVO != null) { //로그인 성공
			logger.info("new login success");
			logger.info("MemberVO : " + MemberVO);
			session.setAttribute(LOGIN, MemberVO);
			
			logger.info("MemberVO != null");
			response.sendRedirect("/");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('등록된 회원이 아닙니다.'); location.href='/member/loginGET';</script>");
			out.flush();

			//response.sendRedirect("/member/loginGET");
			logger.info("MemberVO == null 가입되지 않은 회원");
			
		}
	}	
}
