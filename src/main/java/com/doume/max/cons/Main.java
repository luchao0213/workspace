package com.doume.max.cons;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		HibernateTemplate ht = ctx.getBean("hibernateTemplate",HibernateTemplate.class);
		System.out.println(ht);
		AppInfo app = ctx.getBean("appinfo",AppInfo.class);
		System.out.println(app.appName+"\n" + app.password +"\n" + app.url);
	}
}
