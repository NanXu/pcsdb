package cn.edu.cauc.service.system;

import java.util.List;

import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.TreeNode;

/**
 * 作者：徐楠
 *
 * 描述：<p>菜单Service 接口</p>
 * 创建时间：2016年2月20日
 */
public interface IMenuService {

	/**
	 * 查询菜单列表
	 * 
	 * @param menu
	 * 			菜单对象
	 * @param start
	 * 			起始值
	 * @param pageSize
	 * 		 	偏移量
	 * @return
	 */
	Page<Menu> findMenuList(Menu menu, Integer start, Integer pageSize);
	
	/**
	 * 根据主键ID查询菜单
	 * 
	 * @param id
	 * 		主键ID
	 * @return
	 */
	public Menu findMenuById(Integer id);

	/**
	 * 菜单名称是否已存在
	 * 
	 * @param name
	 * 			菜单名称
	 * @return
	 * 		存在：false;不存在：true
	 */
	public boolean isMenuNameExist(String name);

	/**
	 * 保存菜单
	 * 
	 * @param menu
	 * 			菜单
	 */
	public void save(Menu menu);

	/**
	 * 菜单是否关联角色
	 * 
	 * @param ids
	 * 			菜单ID字符串
	 * @return
	 */
	public boolean validateMenuHasAssociationRole(String ids);
	
	/**
	 * 菜单中是否包含根节点菜单
	 * 
	 * @param ids
	 * 			菜单ID
	 * @return
	 */
	public boolean hasRootMenu(String ids);
	
	/**
	 * 删除菜单
	 * 
	 * @param ids
	 * 			菜单ID字符串
	 */
	public void deleteMenus(String ids);
	
	/**
	 * 将根节点添加到字符串中
	 * 
	 * @param menuIds
	 * 			菜单Id
	 * @return
	 */
	public String addRootMenuIds(String menuIds);
	
	/**
	 * 根据角色ID查询菜单
	 * 
	 * @param roleId
	 * 			角色ID
	 * @return
	 */
	public List<TreeNode> findRoleMenuTree(Integer roleId);

}
