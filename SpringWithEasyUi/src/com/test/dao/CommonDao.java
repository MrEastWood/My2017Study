package com.test.dao;

import java.util.List;
import java.util.Map;

import com.test.entry.Pager;

public interface CommonDao<T> {

	
	/**
	 * @param t 需要插入的对象
	 * @return  0 - 成功删除     -1 - 删除失败
	 */
	public int insertRecord(T t);
	
	/**
	 * @param t 一个对id赋值的对象
	 * @return 0 - 成功删除   -1 - 删除失败
	 */
	public int deleteRecordById(T t);
	
	/**
	 * @param queryParms 需要删除记录的条件集合
	 * @return 0 - 成功删除   -1 - 删除失败
	 */
	public int deleteRecords(Map<String,Object> queryParms);
	
	/**
	 * @param queryParms 查询条件的集合
	 * @return 返回记录列表(不分页)
	 */
	public List<T> getRecordList(Map<String,Object> queryParms); 
	
	/**
	 * @param queryParms 查询条件的集合
	 * @param page 页面对象(包含当前页码，每页记录数)
	 * @return 填充后的页面(记录数据，总记录数)
	 */
	public Pager<T> getPageRecords(Map<String,String> queryParms,Pager<T> page);
	
	
	/**
	 * @param t 一个对id赋值的对象
	 * @return 唯一符合条件的记录
	 */
	public T getRecordById(T t);
	
	/**
	 * @param t 一个对id赋值的对象
	 * @return 0 - 成功修改   -1 - 修改失败
	 */
	public int modifyRecord(T t);
	
}
