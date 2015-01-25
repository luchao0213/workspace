package com.doume.max.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class ResourcePathExposer implements ServletContextAware{
	public final String version = "1.0";
	
	private ServletContext servletContext;
	public String resourceRoot;
	public void init()
	{
		resourceRoot = "/resources-" + version;
		if(getServletContext()!=null)
			getServletContext().setAttribute("resourceRoot", getServletContext().getContextPath()+resourceRoot);
	}
	public String getResource(){
		return resourceRoot;
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public ServletContext getServletContext(){
		return servletContext;
	}
	public String getRealPath(String lpath){
		return getServletContext().getRealPath(lpath);
	}
}
