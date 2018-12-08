package cn.edu.cauc.dao.system;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.system.UserRoleRelation;

/**
 * 作者：徐楠
 *
 * 描述：<p>用户角色关系 DAO 接口</p>
 * 创建时间：2016年2月14日
 */
public interface IUserRoleRelationDao extends IBaseDao<UserRoleRelation> {

	/**
	 * 查询用户角色关系
	 * 
	 * @param userId
	 * 			用户ID
	 * @return
	 */
	public UserRoleRelation getUserRoleRelationByUserId(Integer userId);
	
	/**
	 * 查询用户角色关系
	 * 
	 * @param roleId
	 * 			角色ID
	 * @return
	 */
	public UserRoleRelation getUserRoleRelationByRoleId(Integer roleId);
}
