package com.doume.max.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Deal;
import com.doume.max.entity.FailedDeal;
import com.doume.max.dao.DealDao;

public class FailedDealDaoTest extends BaseDaoTest{
	@SpringBean("dealDao")
	private static DealDao dao;
	private static FailedDeal[] data;
	public static FailedDeal createFailedDeal(Long FailedDealId,Long goodsId,Long sellerId,Long buyerId,String FailedDealMsg,Date date)
	{
		FailedDeal failedDeal = new FailedDeal();
		failedDeal.setDealId(FailedDealId);
		failedDeal.setBuyerId(buyerId);
		failedDeal.setDealMsg(FailedDealMsg);
		failedDeal.setGoodsId(goodsId);
		failedDeal.setSellerId(sellerId);
		failedDeal.setDealTime(date);
		return failedDeal;
	}
	@BeforeClass
	public static void initData() {
		data = new FailedDeal[3];
		data[0] = createFailedDeal(1L,1L,1L,3L,"Apple",new Date());
		data[1] = createFailedDeal(2L,2L,1L,3L,"Orange",new Date());
		data[2] = createFailedDeal(3L,1L,2L,4L,"Apple",new Date());
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (FailedDeal d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (FailedDeal d : data) {
			dao.remove(d);
		}
	}
	
	@Test
	public void findBySellerId()
	{
		List<Deal> rs = dao.findBySellerId(1L);
		Assert.assertTrue(rs.size()==2);
		rs = dao.findBySellerId(2L);
		Assert.assertTrue(rs.size()==1);
	}
}
