package cn.edu.cauc.dao.system.impl;

import org.springframework.stereotype.Repository;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.system.IUserRoleDao;
import cn.edu.cauc.model.vo.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements IUserRoleDao {

}
