package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.KeyType;

public interface KeyTypeDao extends BaseDao<KeyType>{
	public List<KeyType> findByClass(String keyClass);
}
