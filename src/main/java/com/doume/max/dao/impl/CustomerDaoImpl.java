package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Customer;
import com.doume.max.cons.MUtils;
import com.doume.max.dao.CustomerDao;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
	@Override
	public List<Customer> findByIds(Long[] ids) {
		final String hql = " from Customer where userId in (" + MUtils.arrayJoin(ids, ",") +")";
		List<Customer> rs = find(hql);
		return rs;
	}
}
