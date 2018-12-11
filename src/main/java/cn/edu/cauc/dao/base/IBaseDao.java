package cn.edu.cauc.dao.base;

import java.util.List;

import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.KeywordsStatView;
import cn.edu.cauc.model.vo.Page;

public interface IBaseDao<T> {

	/**
     * 通过实体的主键ID删除对象
     * 
     * @param id
     * 		主键ID
     */
	public void delete(Integer id);
	
	/**
     * 通过ID查询对象
     * 
     * @param id
     * 		主键ID
     * @return
     */
    public T getById(Integer id);
    
    /**
     * 查询所有的对象List
     * @return
     */
    public List<T> getAll();
    
    /**
     * 通过ID数组查询所有的对象List
     * 
     * @param ids
     * @return
     */
    public List<T> getByIds(String[] ids);
    
    /**
     * 获取总数
     * 
     * @return
     */
    public int getTotalCount();
    
    /**
     * 保存对象
     * @param entity
     * @return
     */
    public Integer insert(T entity);
    
    /**
     * 更新对象
     * @param entity
     */
    public void update(T entity);
    
    /**
     * 批量更新
     * 
     * @param hql
     * @param field
     * @return
     */
    public int update(String hql,Object...field);
    
    /**
     * 通过HQL语句进行分页查询
     * 
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<T> findPagerListByHql(String hql, Integer pageNo, Integer pageSize);
    
    /**
     * HQL查询LIst
     * 
     * @param hql
     * 		HQL语句
     * @return
     */
    public List<T> findListByHql(String hql);
    
    /**
     * 通过SQL语句查询对象
     * 
     * @param sql
     * @return
     */
    public Page<EventInfo> findEventPagerListBySQL(String sql, Integer pageNo, Integer pageSize);
    
    public Page<EventStatView> findEventStatInfoBySQL(String type, String sql, Integer pageNo, Integer pageSize);

    public Page<KeywordsStatView> findEventPagerByKeywords(String sql, Integer pageNo, Integer pageSize);
}
