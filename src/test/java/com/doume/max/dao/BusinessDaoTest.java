package com.doume.max.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.BHomePage;
import com.doume.max.entity.Business;
import com.doume.max.entity.Location;
import com.doume.max.dao.BusinessDao;

public class BusinessDaoTest extends BaseDaoTest {

	@SpringBean("businessDao")
	private static BusinessDao businessDao;
	private static Business[] data;

	@BeforeClass
	public static void initData() {
		data = new Business[3];
		double[][] loc = new double[][]{
				{0.0, 0.0},
				{20.0, 0.0},
				{10.0, 17.32},
		};
		String[] bName = new String[]{"aabbc","aabbcc","ccbbaa"};
		for (int i = 0; i < data.length; i++) {
			Business bus = new Business();
			bus.unlock();
			bus.setUserId(i+1L);
			bus.setbName(bName[i]);
			bus.setbType(1L << i);
			bus.setInformation("infomation" + i);
			bus.setPhoneno("123-" + i);
			
			BHomePage home = new BHomePage();
			home.setMediaId("mediaId" + i);
			home.setUserId(bus.getUserId());
			bus.setHome(home);
			Location location = new Location();
			location.setUserId(bus.getUserId());
			location.setLng(loc[i][0]);
			location.setLat(loc[i][1]);
			bus.setLocation(location);
			data[i]= bus;
		}
	}
	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (Business bus : data) {
			businessDao.save(bus);
		}
	}

	@After
	public void removeData() {
		for (Business bus : data) {
			businessDao.remove(bus);
		}
	}
	@Test
	public void findByLocation()
	{
		Map<Business,Double> bl = null;
		Business[] buss = null;
		bl = businessDao.findByLocation(0.0, 0.0);
		buss = bl.keySet().toArray(new Business[0]);
		assertEquals(buss[0].getUserId(), new Long(1L));	
		bl = businessDao.findByLocation(2.0, 0.0);
		buss = bl.keySet().toArray(new Business[0]);
		assertEquals(data[0].getUserId(),buss[0].getUserId());
		assertEquals(data[1].getUserId(),buss[1].getUserId());
		assertEquals(data[2].getUserId(),buss[2].getUserId());
		
		bl = businessDao.findByLocation(21.0, 0.0);
		buss = bl.keySet().toArray(new Business[0]);
		// TODO 1,0,2
		assertEquals(data[1].getUserId(), buss[0].getUserId());
		assertEquals(data[2].getUserId(), buss[1].getUserId());
		assertEquals(data[0].getUserId(), buss[2].getUserId());
	}
	@Test
	public void findNameLike()
	{
		List<Business> business  = null;
		business = businessDao.findNameLike("%aa%");
		assertEquals(business.size(),3);
		business = businessDao.findNameLike("aa%");
		assertEquals(business.size(),2);
		business = businessDao.findNameLike("%bbc%");
		assertEquals(business.size(),2);
		business = businessDao.findNameLike("%aa");
		assertEquals(business.size(),1);
	}
	
	@Test
	public void findByUserIds()
	{
		List<Business> result = null;
		result  = businessDao.findByUserIds(new Long[]{});
		assertEquals(result.size(),0);
		result  = businessDao.findByUserIds(new Long[]{data[0].getUserId(),data[1].getUserId(),data[2].getUserId()});
		assertEquals(result.size(),3);
		result  = businessDao.findByUserIds(new Long[]{data[0].getUserId(),data[2].getUserId()});
		assertEquals(result.size(),2);
	}
	
	@Test
	public void findByType()
	{
		List<Business> result = null;
		result  = businessDao.findByType(0L);
		assertEquals(result.size(),0);
		result  = businessDao.findByType(1L);
		assertEquals(result.size(),1);
		result  = businessDao.findByType(3L);
		assertEquals(result.size(),2);
		result  = businessDao.findByType(Business.ALL);
		assertEquals(result.size(),3);
	}
	
	@Test
	public void findByWeight()
	{
		List<Business> result = businessDao.findByWeight();
		System.out.println(result.size());
	}
}
