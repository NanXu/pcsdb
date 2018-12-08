package cn.edu.cauc.dao.system;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.system.RoleMenuRelation;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色菜单 DAO 接口</p>
 * 创建时间：2016年2月14日
 */
public interface IRoleMenuRelationDao extends IBaseDao<RoleMenuRelation> {

	/**
	 * 查询角色菜单关联
	 * 
	 * @param roleId
	 * 			角色ID
	 * @return
	 */
	public RoleMenuRelation getRoleMenuRelationByRoleId(Integer roleId);
	
	/**
	 * 查询角色菜单关联
	 * 
	 * @param menuId
	 * 			菜单ID
	 * @return
	 */
	public RoleMenuRelation getRoleMenuRelationByMenuId(String menuId);
}
