package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Credit;

public interface CreditDao extends BaseDao<Credit>{
	/*
	 * 追加一个积分，返回剩余积分积分。
	 */
	public Credit addCredit(Credit newCredit);
	/*
	 * 减少一个积分，返回剩余积分总和
	 */
	public Credit subCredit(Credit newCredit);
	public Credit find(Long sellerId,Long buyerId);
	public List<Credit> findBySellerId(Long sellerId);
	public List<Credit> findByBuyerId(Long buyerId);
}
