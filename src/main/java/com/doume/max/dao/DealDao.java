package com.doume.max.dao;

import java.util.Date;
import java.util.List;

import com.doume.max.entity.Deal;

/*
 * TODO 交易记录等 应该按月周等时间进行清空，保证查询速度。
 * 记录着所有的交易记录，切忌删除记录时要备份。
 */
public interface DealDao extends BaseDao<Deal>{
	public List<Deal> findBySellerId(Long sellerId);
	public List<Deal> findByBuyerId(Long buyerId,Date begin);
}
