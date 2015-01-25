package com.doume.max.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@SuppressWarnings("deprecation")
@SpringApplicationContext({ "classpath:/applicationContext.xml","file:src/main/webapp/WEB-INF/doume-servlet.xml" })
public class BaseControllerTest extends UnitilsJUnit4 {
	@SpringBeanByType
	protected AnnotationMethodHandlerAdapter handlerAdapter;
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	@Before
	public void before(){
		System.out.println("base before...");
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	@Test
	public void begin(){
		System.out.println("ControllerTest beginning...");
	}
}
