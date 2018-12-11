package cn.edu.cauc.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.cauc.model.vo.KeywordsStatView;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.vo.EventStatView;
import cn.edu.cauc.model.vo.Page;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	
	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
    @Resource
    private SessionFactory sessionFactory;
    protected Class<T> clazz;
    
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }

	@Override
	public void delete(Integer id) {
		Object object = (Object) getSession().get(clazz, id);
        getSession().delete(object);
	}

	@Override
	public T getById(Integer id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getAll() {
		return getSession().createQuery("from " + clazz.getSimpleName()).list();
	}

	@Override
	public List<T> getByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return getSession().createQuery(
                "FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")
                .setParameterList("ids", ids).list();
	}

	@Override
	public int getTotalCount() {
		int totalCount = ((Long)getSession().createQuery("select count(*) from " + clazz.getSimpleName()).list().get(0)).intValue();
        return totalCount;
	}

	@Override
	public Integer insert(T entity) {
		Integer id = (Integer) getSession().save(entity);
        return id;
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public int update(String hql, Object... field) {
		logger.info(hql);
        //System.out.println(hql);
        for(Object obj : field){
            //System.out.println(obj);
            logger.info(obj);
        }
        Query query = getSession().createQuery(hql);
        if(field.length != 0){
            for(int i=0 ;i < field.length; i++){
                query.setParameter(i, field[i]);
            }
        }
        return query.executeUpdate(); 
	}

	@Override
	public Page<T> findPagerListByHql(String hql, Integer pageNo,
			Integer pageSize) {
		Page<T> page = new Page<T>();
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		int count = query.list().size();
		List<T> list = query.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(list);
		return page;
	}

	@Override
	public List<T> findListByHql(String hql) {
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public Page<EventInfo> findEventPagerListBySQL(String sql, Integer pageNo, Integer pageSize) {
		Page<EventInfo> page = new Page<EventInfo>();
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql).addEntity(EventInfo.class);
		int count = query.list().size();
		List<EventInfo> list = query.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(list);
		return page;
	}

	@Override
	public Page<EventStatView> findEventStatInfoBySQL(String type, String sql, Integer pageNo, Integer pageSize) {
		Page<EventStatView> page = new Page<EventStatView>();
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql);
				//.setResultTransformer(Transformers.aliasToBean(EventStatView.class));
				//addEntity(EventStatView.class);
		int count = query.list().size();
		List list = query.setFirstResult(pageNo).setMaxResults(pageSize).list();
		List<EventStatView> statList = new ArrayList<EventStatView>();
		for(Iterator iterator = list.iterator();iterator.hasNext();) {
			  Object[] objects = (Object[]) iterator.next(); 
			  //EventStatView view = new EventStatView(objects[0].toString(), Long.valueOf(objects[1].toString()));
			  EventStatView view = new EventStatView();
			  if(objects[0] != null) {
				  view.setName(objects[0].toString());
			  } else {
				  view.setName("");
			  }
			  if(objects[1] != null) {
				  view.setTotal(Long.valueOf(objects[1].toString()));
			  } else {
				  view.setTotal(0);
			  }
			  
			  view.setType(type);
			  statList.add(view);
		}
		page.setCount(count);
		page.setList(statList);
		return page;
	}

	@Override
	public Page<KeywordsStatView> findEventPagerByKeywords(String sql, Integer pageNo, Integer pageSize) {
		Page<KeywordsStatView> page = new Page<KeywordsStatView>();
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql);
		//.setResultTransformer(Transformers.aliasToBean(EventStatView.class));
		//addEntity(EventStatView.class);
		int count = query.list().size();
		List list = query.setFirstResult(pageNo).setMaxResults(pageSize).list();
		List<KeywordsStatView> statList = new ArrayList<KeywordsStatView>();
		for(Iterator iterator = list.iterator();iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			//EventStatView view = new EventStatView(objects[0].toString(), Long.valueOf(objects[1].toString()));
			KeywordsStatView view = new KeywordsStatView();
			view.setSource((String) objects[0]);
			view.setTotal((Long) objects[1]);

			statList.add(view);
		}
		page.setCount(count);
		page.setList(statList);
		return page;
	}

	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	protected void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
}
