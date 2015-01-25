package com.doume.max.dao.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.cons.MUtils;
import com.doume.max.dao.BusinessDao;
import com.doume.max.entity.Business;

@Repository("businessDao")
public class BusinessDaoImpl extends BaseDaoImpl<Business> implements BusinessDao{
	@Override
	public List<Business> findByUserIds(Long[] userIds) {
		if(userIds!=null && userIds.length>0)
		{
			final String hql = "from Business where userId in (" + MUtils.arrayJoin(userIds, ",") +")";
			return find(hql);
		}else
		{
			return new LinkedList<Business>();
		}
	}

	@Override
	public List<Business> findNameLike(String bName) {
		final String hql = "from Business where bType!=0 and bName like ? "
						+ "order by length(replace(bName,?,''))";;
		return find(hql,bName,bName);
	}

	@Override
	public List<Business> findByType(Long bType) {
		final String hql = "from Business where (bitand(bType,?) != 0)";
		return find(hql,bType);
	}

	@Override
	public List<Business> findByWeight() {
		final String hql = "from Business order by weight desc";
		return findByPage(hql,0,10);
	}

	@Override
	public LinkedHashMap<Business, Double> findByLocation(Double lng, Double lat) {
		final String sql = "select userId,"
				+ "(lng-?)*(lng-?)+(lat-?)*(lat-?) as distance "
				+ " from Location"
				+ " order by distance ";
		@SuppressWarnings("unchecked")
		List<Object[]> locations = getHibernateTemplate().find(sql,lng,lng,lat,lat);
		final LinkedHashMap<Business, Double> result = new LinkedHashMap<Business, Double>();
		for(Object[] obj:locations)
		{
			Business business = get((Long) obj[0]);
			if(!business.isLocked()){
				result.put(business, (Double)obj[1]);
			}
		}
		return result;
	}
}
