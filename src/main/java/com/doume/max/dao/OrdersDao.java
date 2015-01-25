package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Orders;

public interface OrdersDao extends BaseDao<Orders>{
	public List<Orders> findByBuyer(Long buyerId);
	public List<Orders> findBySeller(Long sellerId);
	public List<Orders> findByIds(Long[] ids);
}
