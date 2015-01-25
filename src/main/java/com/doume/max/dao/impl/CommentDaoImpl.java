package com.doume.max.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doume.max.entity.Comment;
import com.doume.max.dao.CommentDao;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

	private static final String QUERY_BY_TARGETID = "from ? where targetId=?";
	private static final String QUERY_BY_USERID = "from Comment where userId=?";
	private static final String QUERY_BY_USERID_EMPTY = "from Comment where userId=? and score is null and content is null";

	@Override
	public List<Comment> findByTarget(String tblType,Long targetId) {
		return find(QUERY_BY_TARGETID,tblType,targetId);
	}
	@Override
	public List<Comment> findByUserId(Long userId) {
		return find(QUERY_BY_USERID,userId);
	}
	@Override
	public List<Comment> findByUserIdEmpty(Long userId) {
		return find(QUERY_BY_USERID_EMPTY,userId);
	}
	@Override
	public void removeByTarget(String tblType, Long targetId) {
		List<Comment> entities = findByTarget(tblType,targetId);
		this.getHibernateTemplate().deleteAll(entities);
	}

}
