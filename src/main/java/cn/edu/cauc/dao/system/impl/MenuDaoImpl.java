package cn.edu.cauc.dao.system.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IMenuDao;
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>菜单 DAO 实现</p>
 * 创建时间：2016年2月14日
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements IMenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<Menu> getMenuList(Menu menu, Integer start, Integer pageSize) {
		Page<Menu> page = new Page<Menu>();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Menu.class);
		if(menu != null) {
			if(!StringUtil.isNull(menu.getName())) {
				criteria.add(Restrictions.like("name", "%"+menu.getName()+"%"));
			}
		}
		criteria.addOrder(Order.asc("order"));
		int count = criteria.list().size();
		List<Menu> menuList = (List<Menu>)criteria.setFirstResult(start).setMaxResults(pageSize).list();
		page.setCount(count);
		page.setList(menuList);
		return page;
	}

	@Override
	public boolean isMenuNameExist(String name) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.eq("name", name));
		Menu menu = (Menu)criteria.uniqueResult();
		if(menu != null) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getChildrenMenus(Integer parentId) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Menu.class);
		criteria.add(Restrictions.eq("parentId", parentId));
		return (List<Menu>)criteria.list();
	}

	@Override
	public int deleteMenuCascade(Integer id) {
		Session session = this.getSession();
		String hql = "delete from Menu m where m.parentId = " + id + " or m.id = " + id;
		Query query = session.createQuery(hql);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findMenuListByIds(String ids) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Menu.class);
		String[] idArray =  ids.split(",");
		criteria.add(Restrictions.in("id",StringUtil.strArrToInteger(idArray)));
		criteria.addOrder(Order.asc("order"));
		return (List<Menu>)criteria.list();
	}

}
