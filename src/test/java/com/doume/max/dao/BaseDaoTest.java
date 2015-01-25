package com.doume.max.dao;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext({ "classpath:/doume-dao.xml" })
public class BaseDaoTest extends UnitilsJUnit4 {

	public static void assertBeanEquals(Object a, Object b) {
		String sa = ToStringBuilder.reflectionToString(a).replaceAll("@[0-9a-f]*", "");
		String sb = ToStringBuilder.reflectionToString(b).replaceAll("@[0-9a-f]*", "");
		String awt1 =sa.replaceAll("[0-9]*-[0-9]*-[0-9]* [0-9]*:[0-9]*:[0-9]*.0", "");
		String bwt1 =sb.replaceAll("[0-9]*-[0-9]*-[0-9]* [0-9]*:[0-9]*:[0-9]*.0", "");
		String awt = awt1.replaceAll("[a-zA-Z]* [a-zA-Z]* [0-9]* [0-9]*:[0-9]*:[0-9]* [a-zA-Z]* [0-9]*", "");
		String bwt = bwt1.replaceAll("[a-zA-Z]* [a-zA-Z]* [0-9]* [0-9]*:[0-9]*:[0-9]* [a-zA-Z]* [0-9]*", "");
		assertEquals(awt, bwt);
	}
	@Test
	public void daoBeginning(){
		System.out.println("Dao Beginning...");
	}
}
