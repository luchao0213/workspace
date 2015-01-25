package com.doume.max.dao.impl;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Administrator;
import com.doume.max.dao.AdministratorDao;

@Repository("administratorDao")
public class AdministratorDaoImpl extends BaseDaoImpl<Administrator> implements AdministratorDao {
}
