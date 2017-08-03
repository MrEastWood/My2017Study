package com.test.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.common.Pager;

public abstract class BaseDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * 在Dao层面不要有业务的判断，因此如果没有数据库操作异常，就代表成功 取消更新方法返回代表结果的int
	 * 疑问：为何通过hibernate操作数据库，不需要捕获会抛出异常？写try catch语句是可以捕获到异常的
	 */

	/**
	 * @param t
	 *            需要插入的对象
	 */
	public void insertRecord(T t) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(t);

	}

	/**
	 * @param t
	 *            需要删除的实例对象 - 已给主键赋值
	 */
	public void deleteRecordById(T t) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	/**
	 * @param queryParms
	 *            - 需要删除的条件集合
	 */
	public void deleteRecords(Map<String, Object> queryParms) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param queryParms
	 *            查询条件的集合
	 * @param 查询对象的class类型
	 * @return 返回记录列表(不分页)
	 */
	public List<T> getRecordList(Class c, List<Criterion> criList) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// 设置多条件查询的条件
		Criteria criteria = session.createCriteria(c, "t");
		for (Criterion q : criList) {

			criteria.add(q);
		}
		return criteria.list();
	}

	/**
	 * @param hql
	 *            在业务层组装好的hql语句
	 * @return 返回记录列表(不分页)
	 */
	public List<T> getRecordList(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param queryParms
	 *            查询条件的集合
	 * @param page
	 *            页面对象(包含当前页码，每页记录数)
	 * @return 填充后的页面(记录数据，总记录数)
	 */
	public Pager<T> getPageRecords(Class c, List<Criterion> criList,
			Pager<T> page) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// 设置多条件查询的条件
		Criteria criteria = session.createCriteria(c, "t");
		for (Criterion q : criList) {

			criteria.add(q);
		}
		// 获取总记录数
		Long total = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		// 设置分页信息
		int firstIndex = (page.getPageNumber() - 1) * page.getPageSize();
		criteria.setFirstResult(firstIndex);
		criteria.setMaxResults(page.getPageSize());
		// 查询分页数据
		List<T> rows = criteria.list();

		// 填充分页信息
		page.setTotal(total);
		page.setRows(rows);

		return page;
	}

	/**
	 * @param hql
	 *            在业务层组装好的hql语句
	 * @param page
	 *            页面对象(包含当前页码，每页记录数)
	 * @return 填充后的页面(记录数据，总记录数)
	 */
	public Pager<T> getPageRecords(String hql, Pager<T> page) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param t
	 *            一个对id赋值的对象
	 * @return 唯一符合条件的记录
	 */
	public T getRecordById(Class c, Serializable id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return (T)session.get(c, id);
	}
	
	/**
	 * @param t
	 *            一个对id赋值的对象
	 * @return 唯一符合条件的记录
	 */
	public T getRecordByIdUpd(Class c, Serializable id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return (T)session.get(c, id,LockOptions.UPGRADE);
	}

	/**
	 * @param t
	 *            一个赋好值的对象
	 */
	public void modifyRecord(T t) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	/**
	 * @param c           查询对象的Class类型
	 * @param criList     条件集合
	 * @return            符合条件的记录条数
	 */
	public Long getRowCount(Class c, List<Criterion> criList) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// 设置多条件查询的条件
		Criteria criteria = session.createCriteria(c, "t");
		for (Criterion q : criList) {
			criteria.add(q);
		}
		// 获取总记录数
		Long total = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return total;
	}

}
