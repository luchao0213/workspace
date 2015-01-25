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

public class DealDaoTest extends BaseDaoTest{
	@SpringBean("dealDao")
	private static DealDao dao;
	private static Deal[] data;

	public static Deal createDeal(Long dealId,Long goodsId,Long sellerId,Long buyerId,String dealMsg,Date date)
	{
		Deal deal = new Deal();
		deal.setDealId(dealId);
		deal.setBuyerId(buyerId);
		deal.setDealMsg(dealMsg);
		deal.setGoodsId(goodsId);
		deal.setSellerId(sellerId);
		deal.setDealTime(date);
		return deal;
	}
	@BeforeClass
	public static void initData() {
		data = new Deal[3];
		Date date = new Date();
		data[0] = createDeal(1L,1L,1L,3L,"Apple",date);
		data[1] = createDeal(2L,2L,1L,3L,"Orange",date);
		data[2] = createDeal(3L,1L,2L,4L,"Apple",date);
	}

	@AfterClass
	public static void clearData() {
		for (Deal d : data) {
			dao.remove(d);
		}
	}
	private static boolean isInited = false;
	@Before
	public void saveData() {
		if(isInited)return ;
		isInited = true;
		for (Deal d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
	}
	
	@Test
	public void findBySellerId()
	{
		List<Deal> rs = dao.findBySellerId(1L);
		Assert.assertTrue(rs.size()==2);
		rs = dao.findBySellerId(2L);
		Assert.assertTrue(rs.size()==1);
	}
	
	@Test
	public void findByBuyerId()
	{
		List<Deal> rs = dao.findByBuyerId(1L,data[0].getDealTime());
		Assert.assertTrue(rs.size()==0);
		rs = dao.findByBuyerId(3L,data[0].getDealTime());
		Assert.assertEquals(2, rs.size());
		rs = dao.findByBuyerId(4L,data[0].getDealTime());
		Assert.assertEquals(1, rs.size());
	}
	@Test
	public void failedDeal()
	{
		FailedDeal fd = new FailedDeal();
		fd.setDealId(100L);
		fd.setBuyerId(1L);
		fd.setSellerId(2L);
		fd.setDealTime(new Date());
		fd.setGoodsId(1L);
		fd.setDealMsg("failed");
		dao.save(fd);
		FailedDeal dbfd = (FailedDeal)dao.get(fd.getDealId());
		assertBeanEquals(fd, dbfd);
		dao.remove(fd);
	}
}
