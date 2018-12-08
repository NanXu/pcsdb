/**
 * 
 */
package cn.edu.cauc.dao.system.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IUserDao;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户 DAO 接口实现类</p>
 * 创建时间：2016年2月12日
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	// extends BaseDaoImpl<UserEntity, Integer>
	
	@Override
	public boolean isUsernameExist(String username) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		if(user != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isLoginNameExist(String loginName) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("loginName", loginName));
		User user = (User)criteria.uniqueResult();
		if(user != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public User getUserByLoginNameAndPwd(String loginName, String password) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("loginName", loginName));
		criteria.add(Restrictions.eq("password", password));
		return (User)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<User> getUserList(User user, int pageNo, int pageSize) {
		Page<User> page = new Page<User>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		if(user != null) {
			if(!StringUtil.isNull(user.getLoginName())) {
				criteria.add(Restrictions.like("loginName", "%"+user.getLoginName()+"%"));
			}
			if(!StringUtil.isNull(user.getUsername())) {
				criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
			}
			if(!StringUtil.isNull(user.getStatus())) {
				criteria.add(Restrictions.eq("status", user.getStatus()));
			}
			
		}
		int count = criteria.list().size();
		List<User> userList = (List<User>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(userList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<User> geEventUserList(User user, int pageNo, int pageSize) {
		Page<User> page = new Page<User>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ne("loginName", "xunan"));
		criteria.add(Restrictions.ne("loginName", "admin"));
		if(user != null) {
			if(!StringUtil.isNull(user.getLoginName())) {
				criteria.add(Restrictions.like("loginName", "%"+user.getLoginName()+"%"));
			}
			if(!StringUtil.isNull(user.getUsername())) {
				criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
			}
			if(!StringUtil.isNull(user.getStatus())) {
				criteria.add(Restrictions.eq("status", user.getStatus()));
			}
		}
		int count = criteria.list().size();
		List<User> userList = (List<User>)criteria.setFirstResult(pageNo).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(userList);
		return page;
	}
	
	
}
