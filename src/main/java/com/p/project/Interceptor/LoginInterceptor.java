package com.p.project.Interceptor;

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
	
	//Controller�� ��û�� ���� �� �����. ��Ʈ�ѷ����� ���� �����.
	//preHandle() �޼ҵ带 �����ϰ� ����� ��Ʈ�ѷ� �޼��忡 ���� ������ ��� �ִ� �Ű����� handler
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		System.out.println("--------------preHandle--------------");

		if(logger.isDebugEnabled()) {
			logger.debug("Request URI \t:" + request.getRequestURI());
		}
		
		HttpSession session=request.getSession();//���� ��ü�� ������
		
		if(session.getAttribute(LOGIN)!=null) { //���� �α��� ��û�� ���� �� ������ ���ǿ� ����Ǿ� �ִ� ������ session LOGIN ����� ���� ����
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}		
		return true;
		//preHandle�� return�� ��Ʈ�ѷ� ��û uri�� ���� �ǳ�/�ȵǳĸ� �㰡�ϴ� �ǹ�, true�� ��Ʈ�ѷ� uri�� ���Ե�.
	}
		
	//��Ʈ�ѷ��� �޼��� ó���� ���� return �ǰ� ȭ���� ����ִ� ó��(view)�� �Ǳ� ������ �� �޼��尡 ����ȴ�
	//ModelAndView ��ü�� ��Ʈ�ѷ����� ������ �� Model ��ü�� ���޵����� ��Ʈ�ѷ����� �۾� �� postHandle()���� �۾��� ���� �ִٸ� ModelAndView �̿�
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("--------------postHandle--------------");
		
		HttpSession session = request.getSession(); //(true);���� ���� ����� ��ȯ��
		
		ModelMap modelMap=modelAndView.getModelMap();
		Object MemberVO = modelMap.get("MemberVO");
				
		if(MemberVO != null) { //�α��� ����
			logger.info("new login success");
			logger.info("MemberVO : " + MemberVO);
			session.setAttribute(LOGIN, MemberVO);
			
			logger.info("MemberVO != null");
			response.sendRedirect("/");
		} else {
			response.sendRedirect("/member/loginGET");
			logger.info("MemberVO == null");
		}
	}	
}
