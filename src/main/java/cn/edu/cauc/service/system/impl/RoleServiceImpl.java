package cn.edu.cauc.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.system.IMenuDao;
import cn.edu.cauc.dao.system.IRoleDao;
import cn.edu.cauc.dao.system.IRoleMenuDao;
import cn.edu.cauc.dao.system.IRoleMenuRelationDao;
import cn.edu.cauc.dao.system.IUserRoleDao;
import cn.edu.cauc.dao.system.IUserRoleRelationDao;
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.RoleMenuRelation;
import cn.edu.cauc.model.po.system.UserRoleRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.RoleMenu;
import cn.edu.cauc.model.vo.UserRole;
import cn.edu.cauc.service.system.IRoleService;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色Service 接口实现</p>
 * 创建时间：2016年2月17日
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	
	@Resource
	private IRoleDao roleDao;	//角色DAO
	@Resource
	private IUserRoleRelationDao userRoleRelationDao; //用户角色DAO
	@Resource
	private IRoleMenuRelationDao roleMenuRelationDao; //角色菜单DAO
	@Resource
	private IUserRoleDao userRoleDao;
	@Resource
	private IRoleMenuDao roleMenuDao;
	@Resource
	private IMenuDao menuDao;

	@Override
	public Page<Role> findRoleList(Role role, int pageNo, int pageSize) {
		return roleDao.getRoleList(role, pageNo, pageSize);
	}

	@Override
	public boolean isRoleNameExist(String name) {
		return roleDao.isRoleNameExist(name);
	}

	@Override
	public void save(Role role) {
		roleDao.insert(role);
	}

	@Override
	public String validateRoleHasUsers(String roleIds) {
		String roleNames = "";
		String[] roleIdArray = roleIds.split(",");
		if(roleIdArray != null && roleIdArray.length > 0) {
			for(int i = 1; i < roleIdArray.length; i++) {
				Integer roleId = Integer.parseInt(roleIdArray[i]);
				UserRoleRelation relation = userRoleRelationDao.getUserRoleRelationByRoleId(roleId);
				if(relation != null) {
					Role role = roleDao.getById(relation.getRoleId());
					roleNames += role.getName() + ",";
				}
			}
		}
		if(roleNames.indexOf(",") != -1) {
			roleNames = roleNames.substring(0, roleNames.length() - 1);
		}
		return roleNames;
	}

	@Override
	public void deleteRoles(String ids) {
		String[] idArray = ids.split(",");
		if(idArray != null && idArray.length > 0) {
			for(int i = 1; i < idArray.length; i++) {
				Integer id = Integer.parseInt(idArray[i]);
				//删除角色菜单关联
				RoleMenuRelation relation = roleMenuRelationDao.getRoleMenuRelationByRoleId(id);
				if(relation != null) {
					roleMenuRelationDao.delete(relation.getId());
				}
				//删除角色本身
				roleDao.delete(id);
			}
		}
	}

	@Override
	public Page<UserRole> findUserRoleList(UserRole userRole, Integer pageNo,
			Integer pageSize) {
		//StringBuffer sb = new StringBuffer();
		String hql = "select new cn.edu.cauc.model.vo.UserRole(user.id as userId, role.id as roleId, user.username as username, role.name as roleName, user.status as status) "
				+ "from UserRoleRelation relation, User user, Role role "
				+ "where relation.userId = user.id and relation.roleId = role.id ";
		if(userRole != null) {
			if(!StringUtil.isNull(userRole.getUsername())) {
				hql += "and user.username like '%" + userRole.getUsername() + "%' ";
			}
			if(!StringUtil.isNull(userRole.getRoleName())) {
				hql += "and role.name like '%" + userRole.getRoleName() + "%' ";
			}
			if(!StringUtil.isNull(userRole.getStatus())) {
				hql += "and user.status = '" + userRole.getStatus() + "' ";
			}
		}
		Page<UserRole> page = userRoleDao.findPagerListByHql(hql, pageNo, pageSize);
		return page;
	}

	@Override
	public void saveRoleMenuRelation(RoleMenuRelation relation) {
		roleMenuRelationDao.insert(relation);
	}

	@Override
	public void modifyRoleMenuRelation(RoleMenuRelation relation) {
		roleMenuRelationDao.update(relation);
	}

	@Override
	public Page<RoleMenu> findRoleMenuList(RoleMenu roleMenu, Integer pageNo,
			Integer pageSize) {
		String hql = "select new cn.edu.cauc.model.vo.RoleMenu(role.name as roleName, relation.menuIds as menuIds) "
				+ "from RoleMenuRelation relation, Role role where relation.roleId = role.id ";
		if(roleMenu != null) {
			if(!StringUtil.isNull(roleMenu.getRoleName())) {
				hql += "and role.name like '%" + roleMenu.getRoleName() + "%' ";
			}
		}
		Page<RoleMenu> page = roleMenuDao.findPagerListByHql(hql, pageNo, pageSize);
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		if(page != null) {
			List<RoleMenu> list = page.getList();
			if(list != null && !list.isEmpty()) {
				for(RoleMenu rm : list) {
					RoleMenu tempRoleMenu = new RoleMenu();
					tempRoleMenu.setRoleName(rm.getRoleName());
					tempRoleMenu.setMenuIds(rm.getMenuIds());
					//BeanUtils.copyProperties(rm, tempRoleMenu);
					String menuIds = rm.getMenuIds();
					List<Menu> menuList = menuDao.findMenuListByIds(menuIds);
					if(menuList != null && !menuList.isEmpty()) {
						String menuNames = "";
						for(Menu menu : menuList) {
							menuNames += "," + menu.getName();
						}
						tempRoleMenu.setMenuNames(menuNames.substring(1));
					}
					roleMenuList.add(tempRoleMenu);
				}
			}
		}
		page.setList(roleMenuList);
		return page;
	}

	@Override
	public RoleMenuRelation findRoleMenuRelationByRoleId(Integer roleId) {
		return roleMenuRelationDao.getRoleMenuRelationByRoleId(roleId);
	}

}
