package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Media;

public interface MediaDao extends BaseDao<Media>{
	public List<Media> findByUserId(Long userId);
	public List<Media> findByMediaType(String mediaType);
}
