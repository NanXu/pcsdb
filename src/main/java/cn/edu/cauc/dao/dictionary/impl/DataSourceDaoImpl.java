package cn.edu.cauc.dao.dictionary.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.dictionary.IDataSourceDao;
import cn.edu.cauc.model.po.dictionary.DataSource;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2016年9月14日
 */
@Repository("dataSourceDao")
public class DataSourceDaoImpl extends BaseDaoImpl<DataSource> implements
		IDataSourceDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<DataSource> findDataSourceList(DataSource dataSource, Integer pageNo,
			Integer pageSize) {
		Page<DataSource> page = new Page<DataSource>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(DataSource.class);
		if(dataSource != null) {
//			if(!StringUtil.isNull(user.getLoginName())) {
//				criteria.add(Restrictions.like("loginName", "%"+user.getLoginName()+"%"));
//			}
//			if(!StringUtil.isNull(user.getUsername())) {
//				criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
//			}
//			if(!StringUtil.isNull(user.getStatus())) {
//				criteria.add(Restrictions.eq("status", user.getStatus()));
//			}
		}
		int count = criteria.list().size();
		List<DataSource> dataSourceList = (List<DataSource>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(dataSourceList);
		return page;
	}

}
