package com.doume.max.dao;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Media;
import com.doume.max.dao.MediaDao;

public class MediaDaoTest extends BaseDaoTest{
	@SpringBean("mediaDao")
	private static MediaDao dao;
	private static Media[] data;

	@BeforeClass
	public static void initData() {
		data = new Media[4];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = new Media();
			Media m  = data[i];
			m.setMediaId("resource"+ i + "/image.jpg");
			m.setMediaType("mediaType"+i%3);
			m.setOwnerId(i%3 + 1L);
			m.setSize(i+10L);
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (Media d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (Media d : data) {
			dao.remove(d);
		}
	}
	@Test
	public void findByUserId()
	{
		List<Media> rs = dao.findByUserId(data[0].getOwnerId());
		Assert.assertTrue(rs.size()==2);
		rs = dao.findByUserId(10000L);
		Assert.assertTrue(rs.size()==0);
	}
	@Test
	public void findByMediaType()
	{
		List<Media> rs ;
		rs = dao.findByMediaType("mediaType0");
		Assert.assertTrue(rs.size()==2);
		rs = dao.findByMediaType("mediaType1");
		Assert.assertTrue(rs.size()==1);
		rs = dao.findByMediaType("null");
		Assert.assertTrue(rs.size()==0);
	}
}
