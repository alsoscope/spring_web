package com.p.project.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Temp extends HandlerInterceptorAdapter {
	
	private static final Logger logger=LoggerFactory.getLogger(Temp.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("userId")==null) {
			//logger.info("userId : " + userId);
			logger.info("userId problem");
			return false;
		} else {
			return true;
		}

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

/*		HttpSession session = request.getSession();
		
		ModelMap modelMap=modelAndView.getModelMap();
		Object MemberVO = modelMap.get("userId");
		
		if(MemberVO != null) { //로그인 성공
			logger.info("MemberVO : " + MemberVO);
			session.setAttribute("userId", MemberVO);
			//request.getSession().setAttribute("userId", userId);
			
			response.sendRedirect("/");
		} else {
			response.sendRedirect("/member/loginGET");
	}*/
		}
	
}
