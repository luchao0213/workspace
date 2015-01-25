package com.doume.max.dao.hqlfunction;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class BitAndFunction implements SQLFunction{
	@SuppressWarnings("deprecation")
	@Override
	public Type getReturnType(Type arg0, Mapping arg1) throws QueryException {
		return Hibernate.LONG;
	}

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}
	
	@Override
	public String render(Type type,@SuppressWarnings("rawtypes") List args, SessionFactoryImplementor arg1)
			throws QueryException {
		return args.get(0).toString() + "&" + args.get(1).toString();
	}
}
