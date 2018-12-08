package cn.edu.cauc.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cauc.dao.system.IMenuDao;
import cn.edu.cauc.dao.system.IRoleMenuRelationDao;
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.po.system.RoleMenuRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.TreeNode;
import cn.edu.cauc.service.system.IMenuService;
import cn.edu.cauc.util.StringUtil;

/**
 * 作者：徐楠
 *
 * 描述：<p>菜单Service 接口实现</p>
 * 创建时间：2016年2月20日
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	
	@Resource
	private IMenuDao menuDao;
	@Resource
	private IRoleMenuRelationDao roleMenuRelationDao;

	@Override
	public Page<Menu> findMenuList(Menu menu, Integer start, Integer pageSize) {
		return menuDao.getMenuList(menu, start, pageSize);
	}

	@Override
	public Menu findMenuById(Integer id) {
		return menuDao.getById(id);
	}

	@Override
	public boolean isMenuNameExist(String name) {
		return menuDao.isMenuNameExist(name);
	}

	@Override
	public void save(Menu menu) {
		menuDao.insert(menu);
	}

	@Override
	public boolean validateMenuHasAssociationRole(String ids) {
		boolean flag = false;
		String[] array = ids.split(",");
		if(array != null && array.length > 0) {
			for(int i = 1; i < array.length; i ++) {
				RoleMenuRelation relation = roleMenuRelationDao.getRoleMenuRelationByMenuId(array[i]);
				if(relation != null) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean hasRootMenu(String ids) {
		boolean flag = false;
		String[] array = ids.split(",");
		if(array != null && array.length > 0) {
			for(int i = 1; i < array.length; i ++) {
				Integer id = Integer.parseInt(array[i]);
				Menu menu = menuDao.getById(id);
				if(menu != null) {
					if(menu.getParentId() == 0) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	@Override
	public void deleteMenus(String ids) {
		String[] idArray = ids.split(",");
		if(idArray != null && idArray.length > 0) {
			for(int i = 1; i < idArray.length; i++) {
				Integer id = Integer.parseInt(idArray[i]);
				Menu menu = menuDao.getById(id);
				if(menu != null) {
					if(menu.getParentId() == 0) {
						//根节点级联删除
						//Integer parentId = menu
						//menuDao.delete(id);
						menuDao.deleteMenuCascade(id);
					} else {
						menuDao.delete(menu.getId());
					}
					
				}
			}
		}
	}

	@Override
	public String addRootMenuIds(String menuIds) {
		if(!StringUtil.isNull(menuIds)) {
			String[] menuIdArray = menuIds.split(",");
			if(menuIdArray != null && menuIdArray.length > 0) {
				for(String menuId : menuIdArray) {
					Integer id = Integer.parseInt(menuId);
					Menu menu = menuDao.getById(id);
					Menu parent = menuDao.getById(menu.getParentId());
					if(parent != null && !menuIds.contains(parent.getId()+"")) {
						menuIds += ","+parent.getId();
					}
				}
			}
		}
		return menuIds;
	}

	@Override
	public List<TreeNode> findRoleMenuTree(Integer roleId) {
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		RoleMenuRelation relation = roleMenuRelationDao.getRoleMenuRelationByRoleId(roleId);
		if(relation != null) {
			String menuIds = relation.getMenuIds();
			String[] menuArray = menuIds.split(",");
			List<Menu> menuList = menuDao.getAll();
			if(menuList != null && !menuList.isEmpty()) {
				for(Menu menu : menuList) {
					TreeNode treeNode = new TreeNode();
					treeNode.setId(menu.getId()+"");
					treeNode.setName(menu.getName());
					treeNode.setParentId(menu.getParentId()+"");
					if(menu.getOrder() == 0) {
						treeNode.setOpen(true);
					}
					if(menuArray.length > 0) {
						for(String menuId : menuArray) {
							if(menuId.equals(menu.getId()+"")) {
								treeNode.setOpen(true);
								treeNode.setChecked(true);
							}
						}
					}
					treeNodeList.add(treeNode);
				}
			}
			
		} else {//加载所有菜单
			List<Menu> menuList = menuDao.getAll();
			if(menuList != null && !menuList.isEmpty()) {
				for(Menu menu : menuList) {
					TreeNode treeNode = new TreeNode();
					treeNode.setId(menu.getId()+"");
					treeNode.setName(menu.getName());
					treeNode.setParentId(menu.getParentId()+"");
					if(menu.getOrder() == 0) {
						treeNode.setOpen(true);
					}
					treeNodeList.add(treeNode);
				}
			}
		}
		return treeNodeList;
	}
	
	
}
