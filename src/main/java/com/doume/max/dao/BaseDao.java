package com.doume.max.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface BaseDao<T> {

	// 根据 ID 加载 PO 实例
	public T load(Serializable id);

	// 根据 ID 获取 PO 实例
	public T get(Serializable id);

	// 获取 PO 的所有对象
	public List<T> loadAll();

	// 保存 PO
	public void save(T entity) ;

	public void save(String entityName,Object entity);
	
	// 保存 PO
	public void saveOrUpdate(T entity) ;

	// 删除 PO
	public void remove(T entity) ;

	// 更改 PO
	public void update(T entity);

	// 执行 HQL 查询
	public List<T> find(String hql);

	// 执行带参的 HQL 查询
	public List<T> find(String hql, Object... params);

	// 对延迟加载的实体 PO 执行初始化
	public void initialize(Object entity) ;

	// 分页查询函数,使用 hql
	public List<T> findByPage(final String hql, final int pageNo,final int pageSize);
	
	// 分页查询函数,使用 hql
	public List<T> findByPage(final String hql, final int pageNo,final int pageSize,
			final Object... params);
	
	// 创建 Query 对象
	public Query createQuery(String hql, Object... values);
	
	public void flush();
}
