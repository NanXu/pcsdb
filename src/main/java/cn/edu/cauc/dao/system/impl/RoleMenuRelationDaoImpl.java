package cn.edu.cauc.dao.system.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IRoleMenuRelationDao;
import cn.edu.cauc.model.po.system.RoleMenuRelation;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色菜单关系 DAO 实现</p>
 * 创建时间：2016年2月14日
 */
@Repository("roleMenuRelationDao")
public class RoleMenuRelationDaoImpl extends BaseDaoImpl<RoleMenuRelation> implements
		IRoleMenuRelationDao {

	@Override
	public RoleMenuRelation getRoleMenuRelationByRoleId(Integer roleId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RoleMenuRelation.class);
		criteria.add(Restrictions.eq("roleId", roleId));
		return (RoleMenuRelation)criteria.uniqueResult();
	}

	@Override
	public RoleMenuRelation getRoleMenuRelationByMenuId(String menuId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RoleMenuRelation.class);
		criteria.add(Restrictions.like("menuIds", "%"+menuId+"%"));
		return (RoleMenuRelation)criteria.uniqueResult();
	}
	
}
