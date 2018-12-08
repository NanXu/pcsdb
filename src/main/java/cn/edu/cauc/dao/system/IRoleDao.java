package cn.edu.cauc.dao.system;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.vo.Page;

/**
 * 作者：徐楠
 *
 * 描述：<p>角色 DAO 接口</p>
 * 创建时间：2016年2月14日
 */
public interface IRoleDao extends IBaseDao<Role> {

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
	Page<Role> getRoleList(Role role, int pageNo, int pageSize);

	/**
	 * 角色名称是否已存在
	 * 
	 * @param name
	 * 			角色名称
	 * @return
	 * 		存在：false;不存在：true
	 */
	boolean isRoleNameExist(String name);

}
