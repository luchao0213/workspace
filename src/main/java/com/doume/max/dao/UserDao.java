package com.doume.max.dao;

import com.doume.max.entity.User;


public interface UserDao extends BaseDao<User>{
	public User findByUser(String user);
	public User findByOpenId(String openId);
	public User findByUserName(String userName);
}
