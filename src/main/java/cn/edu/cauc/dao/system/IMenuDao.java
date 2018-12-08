package cn.edu.cauc.dao.system;

import java.util.List;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者：徐楠
 *
 * 描述：<p>菜单 DAO 接口</p>
 * 创建时间：2016年2月14日
 */
public interface IMenuDao extends IBaseDao<Menu> {

	/**
	 * 查询菜单列表
	 * 
	 * @param menu
	 * 			菜单对象
	 * @param start
	 * 			起始值
	 * @param pageSize
	 * 			偏移量
	 * @return
	 */
	Page<Menu> getMenuList(Menu menu, Integer start, Integer pageSize);

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
	 * 查询子菜单
	 * 
	 * @param parentId
	 * 			父菜单ID
	 * @return
	 */
	public List<Menu> getChildrenMenus(Integer parentId);
	
	/**
	 * 级联删除
	 * 
	 * @param parentId
	 * 				父节点
	 */
	public int deleteMenuCascade(Integer id);
	
	/**
	 * 查询菜单列表
	 * 
	 * @param ids
	 * 		菜单ID字符串
	 * @return
	 */
	public List<Menu> findMenuListByIds(String ids);
}
