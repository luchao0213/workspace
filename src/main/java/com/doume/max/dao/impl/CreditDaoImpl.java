package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Credit;
import com.doume.max.dao.CreditDao;

@Repository("creditDao")
public class CreditDaoImpl extends BaseDaoImpl<Credit> implements CreditDao{
	private final String QUERY_BY_BUYER = "from Credit where buyerId = ?";
	private final String QUERY_BY_SELLER = "from Credit where sellerId = ?";
	private final String QUERY_BY_SELLER_BUYER = "from Credit where sellerId = ? and buyerId = ?";
	
	@Override
	public Credit addCredit(Credit newCredit) {
		Credit credit = find(newCredit.getSellerId(),newCredit.getBuyerId());
		if(credit!=null)
		{
			credit.setValue(credit.getValue() + newCredit.getValue());
			update(credit);
			return credit;
		}else
		{
			Credit result = newCredit.clone();
			save(result);
			return result;
		}
	}

	@Override
	public Credit subCredit(Credit newCredit) {
		Credit credit = find(newCredit.getSellerId(),newCredit.getBuyerId());
		if(credit!=null)
		{
			Integer val = credit.getValue()-newCredit.getValue();
			if(val>=0){
				credit.setValue(val);
				update(credit);
				return credit;
			}else
			{
				return null;
			}
		}else
		{
			return null;
		}
	}

	@Override
	public Credit find(Long sellerId, Long buyerId) {
		List<Credit> result = find(QUERY_BY_SELLER_BUYER,sellerId,buyerId);
		if(result.isEmpty()){
			return null;
		}else
		{
			return result.get(0);
		}
	}

	@Override
	public List<Credit> findBySellerId(Long sellerId) {
		return find(QUERY_BY_SELLER,sellerId);
	}

	@Override
	public List<Credit> findByBuyerId(Long buyerId) {
		return find(QUERY_BY_BUYER,buyerId);
	}

}
