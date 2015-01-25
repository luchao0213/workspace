package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Orders;
import com.doume.max.cons.MUtils;
import com.doume.max.dao.OrdersDao;

@Repository("ordersDao")
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao{
	private static final String QUERY_BY_BUYER = "from Orders where buyerId = ?";
	private static final String QUERY_BY_SELLER = "from Orders where sellerId = ?";
	
	@Override
	public List<Orders> findByBuyer(Long buyerId) {
		return find(QUERY_BY_BUYER,buyerId);
	}

	@Override
	public List<Orders> findBySeller(Long sellerId) {
		return find(QUERY_BY_SELLER,sellerId);
	}

	@Override
	public List<Orders> findByIds(Long[] ids) {
		final String hql = "from Orders where ordersId in (" + MUtils.arrayJoin(ids, ",") +")";
		return find(hql);
	}

}
