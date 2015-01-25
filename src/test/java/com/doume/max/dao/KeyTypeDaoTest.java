package com.doume.max.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.KeyType;
import com.doume.max.dao.KeyTypeDao;

public class KeyTypeDaoTest extends BaseDaoTest{
	@SpringBean("keyTypeDao")
	private static KeyTypeDao dao;
	private static KeyType[] data;

	@BeforeClass
	public static void initData() {
		data = new KeyType[4];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = new KeyType();
			KeyType d = data[i];
			d.setOid(i + 1L);
			d.setKeyClass("Class" + i%3);
			d.setKeyName("Name" + i);
			d.setKeyValue(1L<<((i+1)%3));
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (KeyType d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (KeyType d : data) {
			dao.remove(d);
		}
	}
	@Test
	public void findByClass()
	{
		List<KeyType> rs;
		rs = dao.findByClass("Class0");
		assertTrue(rs.size()==2);
		rs = dao.findByClass("Class1");
		assertTrue(rs.size()==1);
		rs = dao.findByClass("null");
		assertTrue(rs.size()==0);
	}
}
