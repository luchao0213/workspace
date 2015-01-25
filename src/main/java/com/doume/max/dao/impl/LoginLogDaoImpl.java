package com.doume.max.dao.impl;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.LoginLog;
import com.doume.max.dao.LoginLogDao;

@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLog> implements LoginLogDao{
}
