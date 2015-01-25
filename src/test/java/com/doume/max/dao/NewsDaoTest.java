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

import com.doume.max.entity.News;
import com.doume.max.dao.NewsDao;

public class NewsDaoTest extends BaseDaoTest{


	@SpringBean("newsDao")
	private static NewsDao newsDao;
	private static News[] data;
	private static Date[] date;
	@BeforeClass
	public static void initData() {
		data = new News[4];
		date = new Date[]{
				new Date(1000000),
				new Date(2000000),
				new Date(3000000),
				new Date(4000000)
		};
		for (int i = 0; i < data.length; i++) {
			News news = new News();
			news.setNewsId(i + 1L);
			news.setContent("newContent" + i);
			news.setMediaId("mediaId"+i);
			news.setNewsDate(date[i]);
			news.setNewsType(1L<<i);
			data[i] = news;
		}
	}

	@AfterClass
	public static void clearData() {
	}

	@Before
	public void saveData() {
		for (News news : data) {
			newsDao.save(news);
		}
	}

	@After
	public void removeData() {
		for (News news : data) {
			newsDao.remove(news);
		}
	}

	@Test
	public void findByName()
	{
		List<News> rs;
		rs = newsDao.findByType(1L);
		rs = newsDao.findByType(News.ALL);
		Assert.assertEquals(data.length, rs.size());
	}
	@Test
	public void findByTypeBegin()
	{
		List<News> rs;
		rs = newsDao.findByTypeBegin(News.ALL, date[0]);
		Assert.assertEquals(4, rs.size());
		rs = newsDao.findByTypeBegin(News.ALL, date[2]);
		Assert.assertEquals(2, rs.size());
	}
	@Test
	public void findByTypeEnd()
	{
		List<News> rs;
		rs = newsDao.findByTypeEnd(News.ALL, date[0]);
		Assert.assertEquals(1, rs.size());
		rs = newsDao.findByTypeEnd(News.ALL, date[2]);
		Assert.assertEquals(3, rs.size());
	}
	@Test
	public void findByCondition() {
		List<News> rs;
		rs = newsDao.find(News.ALL, date[0], date[3]);
		Assert.assertEquals(rs.size() ,4);
		rs = newsDao.find(News.ALL, date[1], date[3]);
		System.out.println(rs);
		Assert.assertEquals(3,rs.size());
		
		rs = newsDao.find(News.ALL, null, date[2]);
		Assert.assertEquals(3,rs.size());
		
		rs = newsDao.find(News.ALL, date[1], null);
		Assert.assertEquals(3,rs.size());
		
		rs = newsDao.find(3L, date[1], null);
		Assert.assertEquals(1,rs.size());

	}
}
