package com.doume.max.dao;

import java.util.Date;
import java.util.List;

import com.doume.max.entity.News;

public interface NewsDao extends BaseDao<News>{
	public List<News> find(Long newsType,Date begin,Date end);
	public List<News> findByType(Long newsType);
	public List<News> findByTypeBegin(Long newsType,Date begin);
	public List<News> findByTypeEnd(Long newsType,Date end);
}
