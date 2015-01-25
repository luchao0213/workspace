package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Goods;

/*
 * 商品
 */
public interface GoodsDao extends BaseDao<Goods>{
	public void lockSellerGoods(Long sellerId);
	public void unlockSellerGoods(Long sellerId);
	public List<Goods> find(Long[] sellerIds,Long goodsType,Integer minPrice,Integer maxPrice);
	public List<Goods> findByNameLike(String goodsName);
	public List<Goods> findBySelledCount(Long goodsType);
	public List<Goods> findByScore(Long goodsType);
	public List<Goods> findByCredit(Long sellerId,Integer creditValue);
}
