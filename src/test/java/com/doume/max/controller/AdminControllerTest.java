package com.doume.max.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

import com.doume.max.cons.CommonConstant;
import com.doume.max.dao.AdministratorDao;
import com.doume.max.dao.LoginLogDao;
import com.doume.max.dao.UserDao;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.LoginLog;
import com.doume.max.entity.User;
import com.doume.max.web.controller.AdminController;

public class AdminControllerTest extends BaseControllerTest{
	@SpringBeanByType
	private AdminController adminController;
	@SpringBean("userDao")
	private static UserDao userDao;
	@SpringBean("administratorDao")
	private static AdministratorDao adminDao;
	@SpringBean("loginLogDao")
	private static LoginLogDao loginLogDao;
	@AfterClass
	public static void clearData(){
		for(Administrator a:adminDao.loadAll()){
			adminDao.remove(a);
		}
		for(LoginLog l:loginLogDao.loadAll()){
			loginLogDao.remove(l);
		}
		for(User u:userDao.loadAll()){
			userDao.remove(u);
		}
	}
	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/controller/AdminController/datainit.xml")
	public void home() throws Exception{
		request.setRequestURI("/admin/home");
		ModelAndView mav = this.handlerAdapter.handle(request, response, adminController);
		assertEquals("admin/admin",mav.getViewName());
	}
	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/controller/AdminController/datainit.xml")
	public void loginSuccess() throws Exception{
		request.setRequestURI("/admin/login.json");
		request.addParameter("userName", "admin");
		request.addParameter("password", "123456");
		ModelAndView mav = this.handlerAdapter.handle(request, response, adminController);
		User host = (User)request.getSession().getAttribute(CommonConstant.USER_ME);
		assertNotNull(host);
		assertTrue(loginLogDao.loadAll().size()>=2);
		User rs = (User)mav.getModel().get("ObjectModel");
		ReflectionAssert.assertLenientEquals(host, rs);
		ReflectionAssert.assertPropertyLenientEquals("userName", "admin", rs);	
	}
	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/controller/AdminController/datainit.xml")
	public void loginFailed() throws Exception{
		request.setRequestURI("/admin/login.json");
		request.addParameter("userName", "admin");
		request.addParameter("password", "123456x");
		ModelAndView mav = this.handlerAdapter.handle(request, response, adminController);
		assertEquals("admin/admin",mav.getViewName());
		User host = (User)request.getSession().getAttribute(CommonConstant.USER_ME);
		assertNull(host);
	}
	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/controller/AdminController/datainit.xml")
	public void logout() throws Exception{
		loginSuccess();
		request.setRequestURI("/admin/logout");
		request.setMethod("GET");
		ModelAndView mav = this.handlerAdapter.handle(request, response, adminController);
		assertEquals("forward:/index.jsp",mav.getViewName());
		User host = (User)request.getSession().getAttribute(CommonConstant.USER_ME);
		assertNull(host);
	}

	//TODO@Test
	@DataSet("/dataSetXml/controller/AdminController/datainit.xml")
	public void putUser() throws Exception{
		request.setRequestURI("/admin/putUser.json");
		request.addParameter("userName", "admin");
		request.addParameter("password", "123456");
		request.addParameter("locked", "0");
		//ModelAndView mav = this.handlerAdapter.handle(request, response, adminController);
		
	}
}
