package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.KeyType;
import com.doume.max.dao.KeyTypeDao;

@Repository("keyTypeDao")
public class KeyTypeDaoImpl extends BaseDaoImpl<KeyType> implements KeyTypeDao{
	private final String QUERY_BY_CLASS = "from KeyType where keyClass = ?";
	
	@Override
	public List<KeyType> findByClass(String keyClass) {
		return find(QUERY_BY_CLASS,keyClass);
	}

}
