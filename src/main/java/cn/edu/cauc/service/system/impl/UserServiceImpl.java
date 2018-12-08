package cn.edu.cauc.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.system.IMenuDao;
import cn.edu.cauc.dao.system.IRoleDao;
import cn.edu.cauc.dao.system.IRoleMenuRelationDao;
import cn.edu.cauc.dao.system.IUserDao;
import cn.edu.cauc.dao.system.IUserRoleRelationDao;
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.RoleMenuRelation;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.po.system.UserRoleRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.service.system.IUserService;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户 Service 接口实现</p>
 * 创建时间：2016年2月12日
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;	//用户DAO
	@Resource
	private IUserRoleRelationDao userRoleRelationDao; //用户角色关系DAO
	@Resource
	private IRoleDao roleDao;	//角色DAO
	@Resource
	private IRoleMenuRelationDao roleMenuRelationDao; //角色菜单关系DAO
	@Resource
	private IMenuDao menuDao;	//菜单DAO

	@Override
	public boolean isUsernameExist(String username) {
		return userDao.isUsernameExist(username);
	}

	@Override
	public boolean isLoginNameExist(String loginName) {
		return userDao.isLoginNameExist(loginName);
	}

	@Override
	public User findUserByLoginNameAndPwd(String loginName, String password) {
		return userDao.getUserByLoginNameAndPwd(loginName, password);
	}

	@Override
	public Role findRoleByUserId(Integer userId) {
		UserRoleRelation userRoleRelation = userRoleRelationDao.getUserRoleRelationByUserId(userId);
		if(userRoleRelation != null) {
			Role role = roleDao.getById(userRoleRelation.getRoleId());
			return role;
		} else {
			return null;
		}
	}

	@Override
	public List<Menu> findMenuByUserId(Integer userId) {
		List<Menu> list = new ArrayList<Menu>();
		Role role = findRoleByUserId(userId);
		if(role != null) {
			RoleMenuRelation roleMenuRelation = roleMenuRelationDao.getRoleMenuRelationByRoleId(role.getId());
			if(roleMenuRelation != null) {
				String strMenuIds = roleMenuRelation.getMenuIds();
				String[] menuIds = StringUtil.strSplitArray(strMenuIds);
				if(menuIds != null) {
					for(String menuId : menuIds) {
						Menu menu = menuDao.getById(Integer.parseInt(menuId));
						list.add(menu);
					}
				}
			}
		}
		return list;
	}

	@Override
	public Page<User> findUserList(User user, int pageNo, int pageSize) {
		return userDao.getUserList(user,pageNo,pageSize);
	}
	
	@Override
	public Page<User> findEventUserList(User user, int pageNo, int pageSize) {
		return userDao.geEventUserList(user,pageNo,pageSize);
	}

	@Override
	public User findUserById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public void save(User user) {
		userDao.insert(user);
	}
	
	@Override
	public void edit(User user) {
		userDao.update(user);
	}

	@Override
	public void saveUserRoleRelation(UserRoleRelation relation) {
		UserRoleRelation urRelation = userRoleRelationDao.getUserRoleRelationByUserId(relation.getUserId());
		if(urRelation != null) {
			urRelation.setRoleId(relation.getRoleId());
			userRoleRelationDao.update(urRelation);
		} else {
			userRoleRelationDao.insert(relation);
		}
	}

	@Override
	public void modifyStatusById(Integer id, String status) {
		User user = userDao.getById(id);
		if(user != null) {
			user.setStatus(status);
			userDao.update(user);
		}
	}

	@Override
	public void deleteUsers(String ids) {
		String[] idArray = ids.split(",");
		if(idArray != null && idArray.length > 0) {
			for(int i = 1; i < idArray.length; i++) {
				Integer id = Integer.parseInt(idArray[i]);
				//删除用户角色关联
				UserRoleRelation relation = userRoleRelationDao.getUserRoleRelationByUserId(id);
				if(relation != null) {
					userRoleRelationDao.delete(relation.getId());
				}
				//删除用户本身数据
				userDao.delete(id);
			}
		}
	}

	@Override
	public UserRoleRelation findUserRoleRelationByUserId(Integer userId) {
		return userRoleRelationDao.getUserRoleRelationByUserId(userId);
	}

}
