package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Customer;

public interface CustomerDao extends BaseDao<Customer>{
	public List<Customer> findByIds(Long[] ids);
}
