package com.p.project.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//AdminLogin Interceptor 클래스
public class AdminInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger=LoggerFactory.getLogger(AdminInterceptor.class);
	//요청 전 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("-----------AdminInterceptor preHandle-----------");
		
		//세션 객체 생성
		HttpSession session=request.getSession();
		
		//session에 관리자id가 null이면
		if(session.getAttribute("adminId")==null) {
			//일반 사용자 로그인 화면으로 리다이렉트
			response.sendRedirect(request.getContextPath()+ "/member/loginGET?msg=nologin");
			return false; //요청 실행하지 않는다 false 리턴
		} else {
			//null이 아니면
			return true; //요청 실행한다. true 리턴
		}	
	}
	
	//요청 처리 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

}