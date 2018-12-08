package cn.edu.cauc.dao.system.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IRoleDao;
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色 DAO 接口实现</p>
 * 创建时间：2016年2月14日
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<Role> getRoleList(Role role, int pageNo, int pageSize) {
		Page<Role> page = new Page<Role>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Role.class);
		if(role != null) {
			if(!StringUtil.isNull(role.getName())) {
				criteria.add(Restrictions.like("name", "%"+role.getName()+"%"));
			}
			if(!StringUtil.isNull(role.getDescription())) {
				criteria.add(Restrictions.like("description", "%"+role.getDescription()+"%"));
			}
		}
		int count = criteria.list().size();
		List<Role> roleList = (List<Role>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(roleList);
		return page;
	}

	@Override
	public boolean isRoleNameExist(String name) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.eq("name", name));
		Role role = (Role)criteria.uniqueResult();
		if(role != null) {
			return false;
		} else {
			return true;
		}
	}

}
