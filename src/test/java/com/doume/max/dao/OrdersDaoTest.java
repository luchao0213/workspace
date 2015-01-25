package com.doume.max.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Goods;
import com.doume.max.entity.Orders;
import com.doume.max.dao.GoodsDao;
import com.doume.max.dao.OrdersDao;

public class OrdersDaoTest extends BaseDaoTest {
	@SpringBean("goodsDao")
	private static GoodsDao goodsDao;
	@SpringBean("ordersDao")
	private static OrdersDao ordersDao;
	private static Orders[] data;
	private static Goods[] goodsData;

	@BeforeClass
	public static void initData() {
		data = new Orders[4];
		goodsData = new Goods[4];
		for (int i = 0; i < goodsData.length; i++) {
			Goods goods = new Goods();
			goods.setCreditPrice(100);
			goods.setGoodsType(1L<<i);
			goods.setGoodsName("info" + i);
			goodsData[i] = goods;
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (Goods g : goodsData) {
			goodsDao.save(g);
			System.out.println("save-goods:" + g);
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = new Orders();
			for (int t = 0; t <= i; t++) {
				data[i].addGoods(goodsData[t], t + 1);
			}
			data[i].setBuyerId(i % 3 + 1L);
			data[i].setSellerId(i % 2 + 1L);
			data[i].setOrdersStatus(Orders.CONFIRM_CUS);
		}
		
		for (Orders d : data) {
			ordersDao.save(d);
			System.out.println("save-data" + d);
		}
	}

	@After
	public void removeData() {
		for (Orders d : data) {
			System.out.println("remove-data->" + d);
			ordersDao.remove(d);
		}
		for (Goods g : goodsData) {
			System.out.println("remove-Goods->" + g);
			goodsDao.remove(g);
		}
	}
	@Test
	public void findBy() {	
		List<Orders> rs;
		rs = ordersDao.findByBuyer(1L);
		assertTrue(rs.size() >= 2);
		rs = ordersDao.findByBuyer(2L);
		assertTrue(rs.size() >= 1);
		rs = ordersDao.findBySeller(1L);
		assertTrue(rs.size() >= 2);
	}

	@Test
	public void findByIds() {
		Long[] ids = new Long[1024];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = i + 1L;
		}
		for(int i = 0; i < data.length; i++)
		{
			ids[i] = data[i].getOrdersId();
		}
		List<Orders> rs = ordersDao.findByIds(ids);
		assertTrue(rs.size() >= data.length);
	}
}
