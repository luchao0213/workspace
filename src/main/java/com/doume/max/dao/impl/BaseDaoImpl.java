package com.doume.max.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.doume.max.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	protected static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
	private Class<T> entityClass;
	@Autowired
	public JdbcTemplate jdbcTemplate;
	// 通过反射获取子类确定的泛型类
	public BaseDaoImpl() {
		super();
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}
	@Autowired
	public void autowriedSessionFactory(SessionFactory sessionFactory)
	{
		this.setSessionFactory(sessionFactory);
	}
	// 根据 ID 加载 PO 实例
	public T load(Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	// 根据 ID 获取 PO 实例
	public T get(Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}
	public T get(Class<T> entityClazz,Serializable id){
		return getHibernateTemplate().get(entityClazz, id);
	}
	// 获取 PO 的所有对象
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	// 保存 PO
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	// 保存 PO
	public void save(String entityName,Object entity) {
		getHibernateTemplate().save(entityName, entity);
	}

	// 保存 PO
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// 删除 PO
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}

	// 更改 PO
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	// 执行 HQL 查询
	public List<T> find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	// 执行带参的 HQL 查询
	public List<T> find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}

	// 对延迟加载的实体 PO 执行初始化
	public void initialize(Object entity) {
		this.getHibernateTemplate().initialize(entity);
	}

	// 分页查询函数,使用 hql
	public List<T> findByPage(final String hql, final int pageNo,final int pageSize) {
		List<T> list = getHibernateTemplate().execute(new HibernateCallback<List<T>>(){
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<T> result = session.createQuery(hql)
						.setFirstResult(pageNo*pageSize)
						.setMaxResults(pageSize)
						.list();
				return result;
			}
		});
		return list;
	}
	// 分页查询函数,使用 hql
	public List<T> findByPage(final String hql, final int pageNo,final int pageSize,
			final Object... params) {
		
		List<T> list = getHibernateTemplate().execute(new HibernateCallback<List<T>>(){
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for(int i = 0,len = params.length; i < len;i++)
				{
					query.setParameter(i, params[i]);
				}
				List<T> result = query.setFirstResult(pageNo*pageSize)
						.setMaxResults(pageSize)
						.list();
				return result;
			}
		});
		return list;
	}
	
	// 创建 Query 对象
	public Query createQuery(String hql, Object... values) {
		return createQuery(getSession(),hql,values);
	}
	public Query createQuery(Session session,String hql,Object... values)
	{
		Assert.hasText(hql);
		Query query = session.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	@Override
	public void flush() {
		this.getHibernateTemplate().flush();
	}
}
