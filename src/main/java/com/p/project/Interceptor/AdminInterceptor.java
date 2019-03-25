package com.p.project.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//AdminLogin Interceptor Ŭ����
public class AdminInterceptor extends HandlerInterceptorAdapter {

	//��û �� ó��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//���� ��ü ����
		HttpSession session=request.getSession();
		
		//session�� ������id�� null�̸�
		if(session.getAttribute("adminId")==null) {
			//�Ϲ� ����� �α��� ȭ������ �����̷�Ʈ
			response.sendRedirect(request.getContextPath()+ "/member/login.do?msg=nologin");
			return false; //��û �������� �ʴ´� false ����
		} else {
			//null�� �ƴϸ�
			return true; //��û �����Ѵ�. true ����
		}	
	}
	
	//��û ó�� ��
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

}