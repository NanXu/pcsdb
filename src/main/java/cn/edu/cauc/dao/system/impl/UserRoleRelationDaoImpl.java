package cn.edu.cauc.dao.system.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IUserRoleRelationDao;
import cn.edu.cauc.model.po.system.UserRoleRelation;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户角色关系 DAO 接口实现</p>
 * 创建时间：2016年2月14日
 */
@Repository("userRoleRelationDao")
public class UserRoleRelationDaoImpl extends BaseDaoImpl<UserRoleRelation> implements
		IUserRoleRelationDao {

	@Override
	public UserRoleRelation getUserRoleRelationByUserId(Integer userId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(UserRoleRelation.class);
		criteria.add(Restrictions.eq("userId", userId));
		return (UserRoleRelation)criteria.uniqueResult();
	}

	@Override
	public UserRoleRelation getUserRoleRelationByRoleId(Integer roleId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(UserRoleRelation.class);
		criteria.add(Restrictions.eq("roleId", roleId));
		return (UserRoleRelation)criteria.uniqueResult();
	}

}
