package com.doume.max.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForumHandlerExceptionResolver implements HandlerExceptionResolver{

	protected static final Logger logger = Logger.getLogger(ForumHandlerExceptionResolver.class);
	private String defaultErrorView;
	private Map<String,String> exceptionMappings;
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		ModelAndView mav = new ModelAndView();
		logger.warn("maxian "+arg2+ " Exception:" + arg3);
		System.out.println(arg3);
		return mav;
	}
	public String getDefaultErrorView() {
		return defaultErrorView;
	}
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
	public Map<String, String> getExceptionMappings() {
		return exceptionMappings;
	}
	public void setExceptionMappings(Map<String, String> exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}
	
}
