package com.doume.max.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.dao.AdministratorDao;
import com.doume.max.dao.BusinessDao;
import com.doume.max.dao.DealDao;
import com.doume.max.dao.GoodsDao;
import com.doume.max.dao.LoginLogDao;
import com.doume.max.dao.UserDao;
import com.doume.max.entity.Administrator;
import com.doume.max.entity.Business;
import com.doume.max.entity.Deal;
import com.doume.max.entity.Goods;
import com.doume.max.entity.User;
import com.doume.max.exception.UserExistException;


public class AdministratorServiceTest extends BaseServiceTest{
	@SpringBean("administratorService")
	private static AdministratorService adminService;
	@SpringBean("userDao")
	private static UserDao userDao;
	@SpringBean("businessDao")
	private static BusinessDao businessDao;
	@SpringBean("administratorDao")
	private static AdministratorDao adminDao;
	@SpringBean("loginLogDao")
	private static LoginLogDao loginLogDao;
	@SpringBean("dealDao")
	private static DealDao dealDao;
	@SpringBean("goodsDao")
	private static GoodsDao goodsDao;
	@AfterClass
	public static void clearData(){
		for(User u:userDao.loadAll()){
			userDao.remove(u);
		}
		for(Administrator a:adminDao.loadAll()){
			adminDao.remove(a);
		}
		for(Business business:businessDao.loadAll()){
			businessDao.remove(business);
		}
		for(Deal d:dealDao.loadAll()){
			dealDao.remove(d);
		}
		for(Goods g:goodsDao.loadAll())
		{
			goodsDao.remove(g);
		}
	}
	@Test
	@ExpectedDataSet("/dataSetXml/service/AdministratorService/ExpectedRegister.xml")
	public void register() throws UserExistException
	{
		User user = new User();
		user.setAdmin();
		user.setUserName("admin");
		user.setOpenId("admin");
		user.setPasswordMD5("123456");
		user.unlock();
		Administrator admin = new Administrator();
		adminService.register(user,admin);
	}
	
	//TODO @Test
    @DataSet("/dataSetXml/service/AdministratorService/addBusiness.xml")
	public void addBusiness()
	{
		Administrator admin = adminService.getAdmin(7000L);
		assertNotNull(admin);
		Business business = businessDao.get(8000L);
		assertNotNull(business);
		adminService.addBusiness(admin, business);
		Business dbb = admin.getBlist().iterator().next();
		ReflectionAssert.assertReflectionEquals(business, dbb);

		List<Deal> deals = dealDao.findByBuyerId(admin.getUserId(),new Date());
		Deal deal = deals.get(0);
		assertEquals(admin.getUserId(),deal.getBuyerId());
		assertEquals(new Long(0L),deal.getSellerId());
	}

	@SuppressWarnings("deprecation")
	@Test
    @DataSet("/dataSetXml/service/AdministratorService/datainit.xml")
	public void lock_unlock_Business()
	{
    	Administrator admin = adminService.getAdmin(7000L);
		adminService.lockBusiness(admin, 8000L, "Money");
		Business business = businessDao.get(8000L);	
		assertTrue(business.isLocked());

		List<Deal> deals = dealDao.findBySellerId(admin.getUserId());
		Deal deal = deals.get(0);
		System.out.println("deal:" + deal);
		assertEquals(business.getUserId(),deal.getBuyerId());
		assertEquals(admin.getUserId(),deal.getSellerId());
		dealDao.remove(deal);
		adminService.unlockBusiness(admin, 8000L, "Money");
		business = businessDao.get(8000L);
		assertTrue(!business.isLocked());
		Date date =new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		deals = dealDao.findByBuyerId(business.getUserId(),date);
		deal = deals.get(0);
		assertEquals(business.getUserId(),deal.getBuyerId());
		assertEquals(admin.getUserId(),deal.getSellerId());
	}
	
	@SuppressWarnings("deprecation")
	@Test
    @DataSet("/dataSetXml/service/AdministratorService/datainit.xml")
	public void payBusiness()
	{
    	Administrator admin = adminService.getAdmin(7000L);
		adminService.payBusiness(admin, 8000L, 100);
		Business business = businessDao.get(8000L);
		assertEquals(new Integer(130),business.getBalance());
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		List<Deal> deals = dealDao.findByBuyerId(8000L,date);
		Deal deal = deals.get(0);
		assertEquals(admin.getUserId(),deal.getSellerId());
	}
}
