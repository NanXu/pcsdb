package cn.edu.cauc.dao.system.impl;

import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IRoleMenuDao;
import cn.edu.cauc.model.vo.RoleMenu;

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements IRoleMenuDao {

}
