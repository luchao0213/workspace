package com.doume.max.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.doume.max.cons.CommonConstant;
import com.doume.max.entity.User;
import com.doume.max.web.ResourcePathExposer;

public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";
	@Autowired
	@Qualifier("rpe")
	protected ResourcePathExposer rpe;
	protected User getSessionUser(HttpServletRequest request)
	{
		return (User) request.getSession().getAttribute(CommonConstant.USER_ME);
	}
	protected void setSessionUser(HttpServletRequest request,User user)
	{
		request.getSession().setAttribute(CommonConstant.USER_ME,user);
	}
	public final String getAppbaseUrl(HttpServletRequest request,String url){
		Assert.hasLength(url,"url can't be null!");
		Assert.isTrue(url.startsWith("/"),"url must be start with '/'");
		return request.getContextPath() + url;
	}
}
