package com.doume.max.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.dao.AdministratorDao;
import com.doume.max.dao.BusinessDao;
import com.doume.max.dao.CommentDao;
import com.doume.max.dao.CreditDao;
import com.doume.max.dao.CustomerDao;
import com.doume.max.dao.DealDao;
import com.doume.max.dao.GoodsDao;
import com.doume.max.dao.LoginLogDao;
import com.doume.max.dao.MessageDao;
import com.doume.max.dao.OrdersDao;
import com.doume.max.dao.UserDao;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.Business;
import com.doume.max.entity.Comment;
import com.doume.max.entity.Credit;
import com.doume.max.entity.Customer;
import com.doume.max.entity.Deal;
import com.doume.max.entity.FailedDeal;
import com.doume.max.entity.Goods;
import com.doume.max.entity.Message;
import com.doume.max.entity.Orders;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;

public class CustomerServiceTest extends BaseServiceTest {

	@SpringBean("customerService")
	private static CustomerService customerService;
	@SpringBean("businessService")
	private static BusinessService businessService;
	@SpringBean("userDao")
	private static UserDao userDao;
	@SpringBean("dealDao")
	private static DealDao dealDao;
	@SpringBean("ordersDao")
	private static OrdersDao ordersDao;
	@SpringBean("creditDao")
	private static CreditDao creditDao;
	@SpringBean("goodsDao")
	private static GoodsDao goodsDao;
	@SpringBean("customerDao")
	private static CustomerDao customerDao;
	@SpringBean("businessDao")
	private static BusinessDao businessDao;
	@SpringBean("administratorDao")
	private static AdministratorDao adminDao;
	@SpringBean("loginLogDao")
	private static LoginLogDao loginLogDao;
	@SpringBean("messageDao")
	private static MessageDao messageDao;
	@SpringBean("commentDao")
	private static CommentDao commentDao;
	
	@AfterClass
	public static void clearData() {
		/*block begin*/
		for(Orders o:ordersDao.loadAll()){
			ordersDao.remove(o);
		}
		for(Goods g:goodsDao.loadAll()){
			goodsDao.remove(g);
		}
		/*block end*/
		for(Comment cmm:commentDao.loadAll())
		{
			commentDao.remove(cmm);
		}
		for(Customer c:customerDao.loadAll())
		{
			customerDao.remove(c);
		}
		for(Business b:businessDao.loadAll()){
			businessDao.remove(b);
		}
		for(Administrator a:adminDao.loadAll()){
			adminDao.remove(a);
		}
		for(User u:userDao.loadAll()){
			userDao.remove(u);
		}
	}
	@Test
    @ExpectedDataSet("/dataSetXml/service/CustomerService/ExpectedRegister.xml")
	public void register() throws UserExistException {
		User user = new User();
		user.setCustomer();
		user.setUserName("customer");
		user.setOpenId("customer");
		user.setPasswordMD5("123456");
		user.unlock();
		Customer customer  = new Customer();
		customer.setSex("M");
		customer.setMediaId("customer_0.jpg");
		customer.setGrade(new Date());
		customerService.register(user, customer);
	}
    
	@Test
    @DataSet("/dataSetXml/service/CustomerService/datainit.xml")
	public void addOrders() {
		System.out.println("addOrders....");
		Goods g1 = goodsDao.get(6000L);
		Goods g2 = goodsDao.get(6001L);
		Orders o = new Orders();
		o.addGoods(g1, 1);
		o.addGoods(g2, 2);
		o.setBuyerId(9000L);
		o.setSellerId(8000L);
		customerService.addOrders(9000L, o);
		Orders dborders = ordersDao.get(o.getOrdersId());
		assertEquals(dborders.getOrdersId(),o.getOrdersId());
		assertEquals(dborders.getOrdersStatus()	,o.getOrdersStatus());
		ordersDao.remove(dborders);
		goodsDao.remove(g1);
		goodsDao.remove(g2);
		goodsDao.remove(goodsDao.get(6002L));
		List<Message> msges = messageDao.findByReceiver(8000L);
		assertTrue(1<=msges.size());
		for(Message m:msges)
		{
			messageDao.remove(m);
		}
	}

	@Test
	@DataSet({"/dataSetXml/service/CustomerService/cancelOrders.xml"})
	public void cancelOrders() {
		Orders orders = customerService.cancelOrders(9000L, 1L);
		assertNotNull(orders);
		ordersDao.remove(orders);
	}
	
	@Test
    @DataSet("/dataSetXml/service/CustomerService/cancelOrders_success.xml")
	public void cancelOrders_success() {
		Orders orders = customerService.cancelOrders(9000L, 1L);
		assertNull(orders);
		orders = ordersDao.get(1L);
		assertNull(orders);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/service/CustomerService/confirmOrders.xml")
	public void confirmOrders() {
		Credit c = customerService.confirmOrders(9000L, 1L);
		assertEquals(new Integer(0),c.getValue());
		Orders orders = ordersDao.get(1L);
		assertNull(orders);
		List<Message> msges = messageDao.findByReceiver(8000L);
		assertEquals(1,msges.size());
		for(Message m: msges){
			messageDao.remove(m);
		}
		creditDao.remove(c);
		Date date = new Date();
		//date.setDate(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		List<Deal> deals = dealDao.findByBuyerId(9000L,date);
		assertTrue(1<deals.size());
		for(Deal d:deals) {
			dealDao.remove(d);
		}
		List<Comment> cmms = customerService.getEmptyComment(9000L);
		assertEquals(2,cmms.size());
		for(Comment cmm:cmms)
		{
			commentDao.remove(cmm);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	@DataSet("/dataSetXml/service/CustomerService/confirmOrders.xml")
	public void refuseOrders(){
		customerService.refuseOrders(9000L, 1L);
		Orders orders = ordersDao.get(1L);
		assertNull(orders);
		List<Message> msges = messageDao.findByReceiver(8000L);
		assertEquals(1,msges.size());
		for(Message m: msges){
			messageDao.remove(m);
		}
		Credit c = creditDao.find(8000L, 9000L);
		assertEquals(new Integer(300), c.getValue());
		creditDao.remove(c);
		Date date = new Date();
		//date.setDate(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		List<Deal> deals = dealDao.findByBuyerId(9000L,date);
		assertTrue(1 < deals.size());
		for(Deal d:deals) {
			assertTrue(d instanceof FailedDeal);
			dealDao.remove(d);
		}
	}
	
	/*
	 * TODO
	 */
	public void emptyComment() {
	}
	public void updateComment(){}
	public void removeComment(){}
	public void getCredits(){}
	public void getDeals(){
	}
}
