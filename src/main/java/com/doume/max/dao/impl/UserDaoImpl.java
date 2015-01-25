package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.User;
import com.doume.max.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	private final String QUERY_BY_USER_NAME = "from User where userName = ?";
	private final String QUERY_BY_OPENID = "from User where openId = ?";

	@Override
	public User findByUser(String user) {
		User u = findByUserName(user);
		if(u!=null){return u;}
		else {
			return findByOpenId(user);
		}
	}

	@Override
	public User findByOpenId(String openId) {
		List<User> users = super.find(QUERY_BY_OPENID, openId);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User findByUserName(String userName) {
		List<User> users = super.find(QUERY_BY_USER_NAME, userName);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}
}
