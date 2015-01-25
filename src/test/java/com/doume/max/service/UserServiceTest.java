package com.doume.max.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.dao.BusinessDao;
import com.doume.max.dao.CommentDao;
import com.doume.max.dao.DealDao;
import com.doume.max.dao.GoodsDao;
import com.doume.max.dao.LoginLogDao;
import com.doume.max.dao.MessageDao;
import com.doume.max.dao.UserDao;
import com.doume.max.entity.Business;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Deal;
import com.doume.max.entity.Goods;
import com.doume.max.entity.LoginLog;
import com.doume.max.entity.Message;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;

public class UserServiceTest extends BaseServiceTest{

	@SpringBean("userService")
	private static UserService userService;
	@SpringBean("userDao")
	private static UserDao userDao;
	@SpringBean("goodsDao")
	private static GoodsDao goodsDao;
	@SpringBean("loginLogDao")
	private static LoginLogDao loginLogDao;
	@SpringBean("dealDao")
	private static DealDao dealDao;
	@SpringBean("commentDao")
	private static CommentDao commentDao;
	@SpringBean("messageDao")
	private static MessageDao messageDao;
	@SpringBean("businessDao")
	private static BusinessDao businessDao;
	
	public static void initData(){
	}
	@AfterClass
	public static void clearData(){
		for(User usr:userDao.loadAll()){
			userDao.remove(usr);
		}
		for(LoginLog ll:loginLogDao.loadAll()){
			loginLogDao.remove(ll);
		}
		for(Deal d:dealDao.loadAll()){
			dealDao.remove(d);
		}
		for(Goods g:goodsDao.loadAll())
		{
			goodsDao.remove(g);
		}
		for(Comment c:commentDao.loadAll())
		{
			commentDao.remove(c);
		}
		for(Message msg:messageDao.loadAll())
		{
			messageDao.remove(msg);
		}
		for(Business b:businessDao.loadAll())
		{
			businessDao.remove(b);
		}
	}
	
	@Test(expected=UserExistException.class)
    @ExpectedDataSet("/dataSetXml/service/UserService/register.xml")
	public void register() throws UserExistException
	{
		User user = new User();
		user.setCustomer();
		user.setUserName("fanli");
		user.setPasswordMD5("123456");
		userService.register(user);
		userService.register(user);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/login.xml")
	public void login(){
		User usr = new User();
		usr.setUserName("fanli");
		usr.setOpenId("fanli");
		usr.setCustomer();
		usr.setPasswordMD5("123456");
		User user = userService.loginByUserName(usr.getUserName(), "123456");
		assertEquals(usr.getOpenId(),user.getOpenId());
		user = userService.loginByUserName("fanli", "123456xx");
		assertNull(user);
		user = userService.loginByUserName("fanlixx", "123456");
		assertNull(user);
	}
	@Test
	public void loginSuccess()
	{
		User user = userService.loginByUserName("fanli", "123456");
		LoginLog log = userService.loginSuccess(user);
		assertNotNull(log);
		loginLogDao.remove(log);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
    @ExpectedDataSet("/dataSetXml/service/UserService/ExpectedUpdate.xml")
	public void update() throws UserExistException{
		User user = userService.loginByUserName("fanli", "123456");
		assertNotNull(user);
		user.setUserName("FanLi2");
		user.setPasswordMD5("654321");
		userService.update(user);
		for(User u : userDao.loadAll()){
			System.out.println("userUpdate:-"+u);
		}
	}
	
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
    @ExpectedDataSet("/dataSetXml/service/UserService/ExpectedAddDeal.xml")
	public void addDeal()
	{
		Deal d = userService.addDeal(8000L, 9000L, 6000L, "dealMsg");
		assertNotNull(d);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
    @ExpectedDataSet("/dataSetXml/service/UserService/addFailedDeal.xml")
	public void addFailedDeal()
	{
		Deal d = userService.addFailedDeal(8000L, 9000L, 6000L, "dealMsg");
		assertNotNull(d);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
    @ExpectedDataSet("/dataSetXml/service/UserService/ExpectedAddBusinessComment.xml")
	public void addBusinessComment()
	{
		Comment c  = userService.addBusinessComment(9000L, 8000L, Comment.GOOD, "Good");
		assertNotNull(c);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
	@ExpectedDataSet("/dataSetXml/service/UserService/addGoodsComment.xml")
	public void addGoodsComment()
	{
		Comment c  = userService.addGoodsComment(9000L, 8000L, Comment.GOOD, "Good");
		assertNotNull(c);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
	@ExpectedDataSet("/dataSetXml/service/UserService/addNewsComment.xml")
	public void addNewsComment()
	{
		Comment c  = userService.addNewsComment(9000L, 8000L, Comment.GOOD, "Good");
		assertNotNull(c);
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
	@ExpectedDataSet("/dataSetXml/service/UserService/ExpectedSendMessage.xml")
	public void sendMessage()
	{
		Message msg = userService.sendMessage(9000L, 8000L, Message.ACTUAL, "content");
		assertNotNull(msg);
	}
	@Test
	@DataSet("/dataSetXml/service/UserService/getActualMessage.xml")
	public void getActualMessage()
	{
		List<Message> rs = userService.getActualMessage(9000L);
		for(Message m:rs)
		{
			assertTrue((m.getMsgType()&Message.ACTUAL)!=0);
		}
	}
	@Test
    @DataSet("/dataSetXml/service/UserService/datainit.xml")
	public void getAllUsers(){
		List<User> rs = userService.getAllUsers();
		assertEquals(2,rs.size());
	}

	@Test
	@DataSet("/dataSetXml/service/UserService/removeMessage.xml")
	@ExpectedDataSet("/dataSetXml/service/UserService/ExpectedRemoveMessage.xml")
	public void removeMessage()
	{
		userService.removeMessage(9000L, 1L);
		userService.removeMessage(8000L, 2L);
		userService.removeMessage(4000L, 3L);
	}
}
