package com.doume.max.dao;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.User;

public class UserDaoTest extends BaseDaoTest{
	@SpringBean("userDao")
	private static UserDao dao;
	private static User[] data;

	@BeforeClass
	public static void initData() {
		data = new User[3];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = new User();
			//data[i].setUserId(i + 1L);
			data[i].setOpenId("openId" + i);
			data[i].setUserName("userName" + i);
			data[i].setUserType(1L<<i);
			data[i].setPasswordMD5("password"+i);
		}
	}
	@AfterClass
	public static void clearData() {
		for (User d : data) {
			dao.remove(d);
		}
		System.out.println("\nAfter\n\n");
	}
	private static boolean isSaved = false;
	@Before
	public void saveData() {
		if(isSaved)return ;
		isSaved = true;
		System.out.println("\n\nbefore");
		for (User d : data) {
			dao.save(d);
		}
	}
	
	//TODO @Test(expected=Exception.class)
	public void saveTwiceUserName()
	{
		User usr =  new User();
		usr.setOpenId(data[0].getOpenId() + "uniq");
		usr.setUserName(data[0].getUserName());
		usr.setPasswordMD5(data[0].getPassword());
		dao.save(usr);
	}
	// TODO @Test(expected=Exception.class)
	public void saveTwiceOpenId()
	{
		User usr =  new User();
		usr.setOpenId(data[0].getOpenId());
		usr.setUserName(data[0].getUserName()+"1");
		usr.setPasswordMD5(data[0].getPassword());
		dao.save(usr);
	}
	@Test
	public void findUser()
	{
		User usr;
		usr = dao.get(data[0].getUserId());
		assertBeanEquals(data[0],usr);
		usr = dao.findByUserName(data[0].getUserName());
		assertBeanEquals(data[0],usr);
		usr = dao.findByOpenId(data[0].getOpenId());
		assertBeanEquals(data[0],usr);
	}
}
