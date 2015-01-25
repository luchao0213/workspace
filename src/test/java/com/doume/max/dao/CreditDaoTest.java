package com.doume.max.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Credit;
import com.doume.max.dao.CreditDao;

public class CreditDaoTest extends BaseDaoTest{
	@SpringBean("creditDao")
	private static CreditDao dao;
	private static Credit[] data;

	private static Credit createCredit(Long sellerId,Long buyerId,Integer value)
	{
		Credit c = new Credit();
		c.setSellerId(sellerId);
		c.setBuyerId(buyerId);
		c.setValue(value);
		return c;
	}
	@BeforeClass
	public static void initData() {
		data = new Credit[3];
		data[0] = createCredit(1L,1L,10);
		data[1] = createCredit(1L,2L,10);
		data[2] = createCredit(2L,1L,10);
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
	}

	@After
	public void removeData() {
		for (Credit d : data) {
			dao.remove(d);
		}
	}

	@Test
	public void addCredit()
	{

		for(int i = 0; i < data.length; i++)
		{
			Credit rs = dao.addCredit(data[i]);
			assertBeanEquals(rs,data[i]);
			data[i]= rs;
		}

		for(int i = 0; i < data.length; i++)
		{
			Credit ex = data[i].clone();
			Credit rs = dao.addCredit(data[i]);
			ex.setValue(ex.getValue() *2);
			System.out.println("\n\n\n test:\n"+data[i]+"\n" + rs);
			assertBeanEquals(rs,ex);
		}
	}
	@Test
	public void subCredit()
	{
		for(int i = 0; i < data.length; i++)
		{
			Credit rs = dao.addCredit(data[i]);
			assertBeanEquals(rs,data[i]);
			data[i] = rs; /*由于被持久化 保证数据清除时是通过一个对象 */
		}
		for(int i = 0; i < data.length; i++)
		{
			Credit ex = data[i].clone();/*由于持久化 执行subCredit时data[i]被修改*/
			ex.setValue(0);
			Credit rs = dao.subCredit(data[i]);
			assertBeanEquals(rs,ex);
		}
		for(int i = 0; i < data.length; i++)
		{
			Credit sub = data[i].clone();
			sub.setValue(sub.getValue()+1);
			Credit rs = dao.subCredit(sub);
			assertTrue(rs==null);
		}
	}
	
	@Test
	public void findBySeller()
	{
		for(int i = 0; i < data.length; i++)
		{
			Credit rs = dao.addCredit(data[i]);
			data[i] = rs;
		}
		List<Credit> rs = dao.findBySellerId(1L);
		assertTrue(rs.size()==2);
		rs = dao.findBySellerId(2L);
		assertTrue(rs.size()==1);
	}
	@Test
	public void findByBuyer()
	{
		for(int i = 0; i < data.length; i++)
		{
			Credit rs = dao.addCredit(data[i]);
			data[i] = rs;
		}
		List<Credit> rs = dao.findByBuyerId(1L);
		assertTrue(rs.size()==2);
		rs = dao.findByBuyerId(2L);
		assertTrue(rs.size()==1);
	}
	
}
