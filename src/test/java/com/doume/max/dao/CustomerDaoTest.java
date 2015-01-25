package com.doume.max.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Customer;

public class CustomerDaoTest extends BaseDaoTest{
	@SpringBean("customerDao")
	private static CustomerDao dao;
	private static Customer[] data;

	@BeforeClass
	public static void initData() {
		data = new Customer[4];
		for(int i = 0; i < data.length; i++)
		{
			data[i]  = new Customer();
			data[i].setUserId(i + 1L);
			data[i].setBirthday(new Date());
			data[i].setMediaId("mediaId" + i);
			data[i].setSex("Female");
		}
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for (Customer d : data) {
			dao.save(d);
		}
	}

	@After
	public void removeData() {
		for (Customer d : data) {
			dao.remove(d);
		}
	}
	@Test(expected=Exception.class)
	public void save()
	{
		Customer c = new Customer();
		c.setUserId(data[0].getUserId());
		c.setName(data[0].getName());
		dao.save(c);
		
	}
	@Test
	public void findByIds()
	{
		Long[] ids = new Long[1024];
		for(int i = 0; i < ids.length;i++)
		{
			ids[i] = i + 0L;
		}
		List<Customer> rs = dao.findByIds(ids);	
		assertThat(rs.size(),greaterThanOrEqualTo(data.length));
	}

	@Test
	public void findByIdOneByOne()
	{
		List<Customer> rs = new LinkedList<Customer>();
		for(int i = 0; i < 1024;i++)
		{
			Customer c = dao.get(i+0L);
			if(c!=null)
			{
				rs.add(c);
			}
		}
		assertThat(rs.size(),greaterThanOrEqualTo(data.length));
	}
}
