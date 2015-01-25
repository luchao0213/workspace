package com.doume.max.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Goods;
import com.doume.max.cons.MUtils;
import com.doume.max.dao.GoodsDao;

@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao{

	private class SqlCon_Objs{
		public String sql_con = "";
		public List<Object> objs = new ArrayList<Object>();
	}
	
	private SqlCon_Objs getConditionJudge(Long goodsType,Integer minPrice,Integer maxPrice)
	{
		if(goodsType==null)goodsType = Goods.ALL;
		SqlCon_Objs result = new SqlCon_Objs();
		result.sql_con = "(bitand(goodsType,?) != 0)";
		result.objs.add(goodsType);
		if(minPrice!=null && minPrice !=0)
		{
			if(result.sql_con.equals("")){
				result.sql_con = "(price >= ?)";
			}else
			{
				result.sql_con += " and (price >= ?)";
			}
			result.objs.add(minPrice);
		}
		if(maxPrice!=null&&maxPrice!=0)
		{
			if(result.sql_con.equals("")){
				result.sql_con = "(price <= ?)";
			}else
			{
				result.sql_con += " and (price <= ?)";
			}
			result.objs.add(maxPrice);
		}
		return result;
	}
	
	/*
	 * TODO S 数据量很大时 可能造成系统瘫痪［内存不够，查询超时］。
	 * 
	 */
	@Override
	public List<Goods> find(Long[] sellerIds, Long goodsType,
			Integer minPrice, Integer maxPrice) {
		String sql=null;
		String sql_set="from Goods ";
		SqlCon_Objs sqlCon_Objs=getConditionJudge(goodsType,minPrice,maxPrice);
		String sql_sellers = MUtils.arrayJoin(sellerIds, ",");
		if(!sql_sellers.equals(""))
		{
			sql = sql_set + " where  sellerId in (" + sql_sellers + ") and " + sqlCon_Objs.sql_con;
		}else
		{
			sql = sql_set + " where " +sqlCon_Objs.sql_con;
		}
		return  find(sql, sqlCon_Objs.objs.toArray());
	}

	@Override
	public List<Goods> findByNameLike(String goodsName) {
		final String hql = "from Goods where goodsType!=0 and goodsName like ? "
				+ " order by length(replace(goodsName,?,''))";
		return find(hql,goodsName,goodsName);
	}

	@Override
	public List<Goods> findBySelledCount(Long goodsType) {
		final String hql = "from Goods where bitand(goodsType,?)!=0 "
				+ " order by selledCount desc";
		return find(hql,goodsType);
	}

	@Override
	public List<Goods> findByScore(Long goodsType) {
		final String hql = "from Goods where bitand(goodsType,?)!=0 "
				+ " order by score/scoreCount";
		return find(hql,goodsType);
	}

	@Override
	public List<Goods> findByCredit(Long sellerId, Integer creditValue) {
		final String hql = "from Goods where goodsType!=0" 
				+ " sellerId=? and ? >= creditPrice"
				+ " order by ?-creditPrice";
		return find(hql,sellerId,creditValue,creditValue);
	}

	@Override
	public void lockSellerGoods(Long sellerId) {
		final String hql = "from Goods where sellerId = ?";
		List<Goods> rs = find(hql,sellerId);
		for(Goods g : rs)
		{
			g.lock();
			update(g);
		}
	}

	@Override
	public void unlockSellerGoods(Long sellerId) {
		final String hql = "from Goods where sellerId = ?";
		List<Goods> rs = find(hql,sellerId);
		for(Goods g : rs)
		{
			g.unlock();
			update(g);
		}
	}
}
