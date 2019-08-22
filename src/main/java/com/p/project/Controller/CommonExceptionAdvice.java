package com.p.project.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//스프링 3.2이상에서 사용가능 pom.xml Spring framework 버전 4.x아래라면 4.2.4.RELEASE 로 수정
//@ControllerAdvice : 이를 사용하면 개별 컨트롤러 뿐만 아니라 전체 어플리케이션에서 exception 처리를 할 수 있다.
@ControllerAdvice
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception ex) {

		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/error_common");
		modelAndView.addObject("exception", ex);
		
		return modelAndView;
	}
}
