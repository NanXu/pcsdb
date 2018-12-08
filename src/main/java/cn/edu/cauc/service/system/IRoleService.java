package cn.edu.cauc.service.system;

import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.RoleMenuRelation;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.RoleMenu;
import cn.edu.cauc.model.vo.UserRole;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色Service 接口</p>
 * 创建时间：2016年2月17日
 */
public interface IRoleService {

	/**
	 * 角色列表分页查询
	 * 
	 * @param role
	 * 			角色查询条件
	 * @param pageNo
	 * 			起始记录
	 * @param pageSize
	 * 			偏移量
	 * @return
	 */
	public Page<Role> findRoleList(Role role, int pageNo, int pageSize);

	/**
	 * 角色名称是否已存在
	 * 
	 * @param name
	 * 			角色名称
	 * @return
	 * 		存在：false;不存在：true
	 */
	public boolean isRoleNameExist(String name);

	/**
	 * 角色添加
	 * 
	 * @param role
	 * 			角色信息
	 */
	public void save(Role role);
	
	/**
	 * 校验以逗号分隔的角色id字符串对应的角色是否已分配用户
	 * 
	 * @param roleIds
	 * 			逗号分隔的角色id字符串
	 * @return
	 * 		返回为以逗号分隔的角色名称字符串 
	 * 		""字符串代表角色ids没有分配用户
	 */
	public String validateRoleHasUsers(String roleIds);
	
	/**
	 * 根据角色id字符串删除角色信息
	 * 
	 * @param ids
	 * 		逗号分隔的角色id字符串
	 */
	public void deleteRoles(String ids);

	/**
	 * 查询用户角色分配列表
	 * 
	 * @param userRole
	 * 			用户角色关系
	 * @param pageNo
	 * 			起始页
	 * @param pageSize
	 * 			偏移量
	 * @return
	 */
	public Page<UserRole> findUserRoleList(UserRole userRole, Integer pageNo,
			Integer pageSize);

	/**
	 * 角色关联菜单 保存
	 * 
	 * @param relation
	 * 			角色菜单关联表
	 */
	public void saveRoleMenuRelation(RoleMenuRelation relation);
	
	/**
	 * 角色关联菜单 修改
	 * 
	 * @param relation
	 * 			角色菜单关联表
	 */
	public void modifyRoleMenuRelation(RoleMenuRelation relation);

	/**
	 * 查询角色菜单列表
	 * 
	 * @param roleMenu
	 * 			角色菜单对象
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<RoleMenu> findRoleMenuList(RoleMenu roleMenu, Integer pageNo,
			Integer pageSize);

	/**
	 * 根据角色ID查询角色菜单关联
	 * 
	 * @param roleId
	 * 			角色ID
	 * @return
	 */
	public RoleMenuRelation findRoleMenuRelationByRoleId(Integer roleId);
}
