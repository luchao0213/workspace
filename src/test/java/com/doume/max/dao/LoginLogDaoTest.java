package com.doume.max.dao;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.LoginLog;
import com.doume.max.dao.LoginLogDao;

public class LoginLogDaoTest extends BaseDaoTest{
	@SpringBean("loginLogDao")
	private static LoginLogDao dao;
	private static LoginLog[] data;

	@BeforeClass
	public static void initData() {
		data = new LoginLog[3];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = new LoginLog();
			LoginLog ll = data[i];
			ll.setIp("192.168.1." + i);
			ll.setLoginId(i + 1L);
			ll.setLoginDate(new Date());
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (LoginLog d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (LoginLog d : data) {
			dao.remove(d);
		}
	}
	@Test
	public void get()
	{

		for (LoginLog d : data) {
			LoginLog ll = dao.get(d.getLoginId());
			assertBeanEquals(d,ll);
		}
	}
}
