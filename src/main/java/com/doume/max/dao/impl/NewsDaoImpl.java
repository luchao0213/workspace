package com.doume.max.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.News;
import com.doume.max.dao.NewsDao;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao{

	private static final String QUERY_NEWSTYPE = "from News where bitand(newsType,?)!=0";
	private static final String QUERY_NEWSTYPE_BEGIN = "from News where bitand(newsType,?)!=0 and unix_timestamp(newsDate)>=unix_timestamp(?)";
	private static final String QUERY_NEWSTYPE_END = "from News where bitand(newsType,?)!=0 and unix_timestamp(?)>=unix_timestamp(newsDate)";
	private static final String QUERY_NEWSTYPE_BEGIN_END = "from News where bitand(newsType,?)!=0 and unix_timestamp(newsDate)>=unix_timestamp(?) and unix_timestamp(?)>=unix_timestamp(newsDate)";
	
	@Override
	public List<News> find(Long newsType, Date begin, Date end) {
		if(newsType ==null ) {
			newsType = News.ALL;
		}
		if(begin==null&&end==null)
		{
			return findByType(newsType);
		}else if(begin==null)
		{
			return findByTypeEnd(newsType,end);
		}else if(end==null)
		{
			return findByTypeBegin(newsType,begin);
		}else
		{
			return find(QUERY_NEWSTYPE_BEGIN_END,newsType,begin,end);
		}
	}
	public List<News> findByType(Long newsType)
	{
		return find(QUERY_NEWSTYPE,newsType);
	}
	public List<News> findByTypeBegin(Long newsType,Date begin)
	{
		return find(QUERY_NEWSTYPE_BEGIN,newsType,begin);
	}
	public List<News> findByTypeEnd(Long newsType,Date end)
	{
		return find(QUERY_NEWSTYPE_END,newsType,end);
	}
}
