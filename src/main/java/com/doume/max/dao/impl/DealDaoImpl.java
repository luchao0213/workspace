package com.doume.max.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.dao.DealDao;
import com.doume.max.entity.Deal;

@Repository("dealDao")
public class DealDaoImpl extends BaseDaoImpl<Deal> implements DealDao{
	private final String QUERY_BY_SELLER = " from Deal where sellerId = ?";
	private final String QUERY_BY_BUYER = " from Deal where buyerId = ? and unix_timestamp(dealTime)>=unix_timestamp(?) and unix_timestamp(dealTime) < unix_timestamp(?) ";
	@Override
	public List<Deal> findBySellerId(Long sellerId) {
		return find(QUERY_BY_SELLER,sellerId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Deal> findByBuyerId(Long buyerId,Date date) {
		Date begin = new Date(date.getTime());
		begin.setDate(1);
		begin.setHours(0);
		begin.setMinutes(0);
		begin.setSeconds(0);
		Date end = new Date(begin.getTime());
		end.setMonth(end.getMonth() + 1);
		return find(QUERY_BY_BUYER,buyerId,date,end);
	}

}
