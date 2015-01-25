package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Media;
import com.doume.max.dao.MediaDao;

@Repository("mediaDao")
public class MediaDaoImpl extends BaseDaoImpl<Media> implements MediaDao{
	private static final String QUERY_BY_USERID = "from Media where ownerId = ?";
	private static final String QUERY_BY_MEDIATYPE = "from Media where mediaType = ?";
	@Override
	public List<Media> findByUserId(Long userId) {
		return find(QUERY_BY_USERID,userId);
	}

	@Override
	public List<Media> findByMediaType(String mediaType) {
		return find(QUERY_BY_MEDIATYPE,mediaType);
	}

}
