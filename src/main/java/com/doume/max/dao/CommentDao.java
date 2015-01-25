package com.doume.max.dao;

import java.util.List;

import com.doume.max.entity.Comment;
/*
 * 用于记录所有评论信息。目标类型一般是表结构，Id为在该表结构中的位置。
 * 注意商品删除时 应该更新 该ID
 */
public interface CommentDao extends BaseDao<Comment>{
	public List<Comment> findByUserId(Long userId);
	public List<Comment> findByUserIdEmpty(Long userId);
	public List<Comment> findByTarget(String tblType,Long targetId);
	public void removeByTarget(String tblType,Long targetId);
}