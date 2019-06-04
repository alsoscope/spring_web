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
		
		if(request.getSession().getAttribute("userId")==null) {
			logger.info("userId is null");
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		
		ModelMap modelMap=modelAndView.getModelMap();
		Object userId = modelMap.get("userId");
		
		if(userId != null) { //로그인 성공
			logger.info("userId : " + userId);
			session.setAttribute("userId", userId);
			//request.getSession().setAttribute("userId", userId);
			
			response.sendRedirect("/product/cart_list");
		} else {
			response.sendRedirect("/product/product_detail");
	}
		
		//String userId=request.getParameter("userId");
		
		//session.getAttribute("userId");
		
		//session.setAttribute("userId", userId);
		
		logger.info("userId : " + userId);
		
		
		}
}
