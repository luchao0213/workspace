package com.doume.max.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.doume.max.entity.Business;

/*
 * 用于记录所有商家的基本信息。
 */
public interface BusinessDao extends BaseDao<Business>{
	public List<Business> findByUserIds(Long[] userIds);
	public List<Business> findNameLike(String bName);
	public List<Business> findByType(Long bType);
	public List<Business> findByWeight();
	public LinkedHashMap<Business, Double> findByLocation(Double lng,Double lat);
}
