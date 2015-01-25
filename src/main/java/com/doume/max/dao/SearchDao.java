package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.SearchItem;

public interface SearchDao extends BaseDao<SearchItem>{
	List<SearchItem> search(String key);
}
