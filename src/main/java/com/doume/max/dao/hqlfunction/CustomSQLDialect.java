package com.doume.max.dao.hqlfunction;

import org.hibernate.dialect.MySQLDialect;

public class CustomSQLDialect extends MySQLDialect{
	public CustomSQLDialect()
	{
		super();
		registerFunction("bitand",new BitAndFunction());
	}
}
