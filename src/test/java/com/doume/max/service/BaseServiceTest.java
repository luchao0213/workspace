package com.doume.max.service;

import org.junit.AfterClass;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext({"doume-dao.xml","doume-service.xml"})
public class BaseServiceTest extends UnitilsJUnit4{
	@Test
	public void begin()
	{
		System.out.println("ServiceTest beginning...");
	}
	@AfterClass
	public static void end()
	{
		System.out.println("ServiceTest finished!");
	}
}
