package cn.edu.cauc.service.system;

import java.util.List;

import cn.edu.cauc.model.po.system.Menu;
import cn.edu.cauc.model.po.system.Role;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.po.system.UserRoleRelation;
import cn.edu.cauc.model.vo.Page;


/**
 * 作者：徐楠
 *
 * 描述：<p>用户 Service 接口</p>
 * 创建时间：2016年2月12日
 */
public interface IUserService {
	
	/**
	 * 校验用户名是否存在
	 * 
	 * @param username
	 * 			页面输入的用户名
	 * @return
	 * 		存在：false;不存在：true
	 */
	public boolean isUsernameExist(String username);
	
	/**
	 * 校验登录名是否存在
	 * 
	 * @param loginName
	 * 			页面输入的登录名
	 * @return
	 * 		存在：false;不存在：true
	 */
	public boolean isLoginNameExist(String loginName);
	
	/**
	 * 通过登录名和密码查找用户信息
	 * 
	 * @param loginName
	 * 			登录名
	 * @param password
	 * 			密码
	 * @return
	 */
	public User findUserByLoginNameAndPwd(String loginName, String password);
	
	/**
	 * 查询用户的角色信息
	 * 
	 * @param userId
	 * 			用户ID
	 * @return
	 */
	public Role findRoleByUserId(Integer userId);
	
	/**
	 * 查询用户菜单
	 * 
	 * @param userId
	 * 			用户ID
	 * @return
	 */
	public List<Menu> findMenuByUserId(Integer userId);
	
	/**
	 * 查询用户信息
	 * 
	 * @param user
	 * 			查询条件
	 * @return
	 */
	public Page<User> findUserList(User user, int pageNo, int pageSize);
	
	/**
	 * 查询事故用户信息
	 * 
	 * @param user
	 * 			查询条件
	 * @return
	 */
	public Page<User> findEventUserList(User user, int pageNo, int pageSize);
	
	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * 			用户信息
	 */
	public void save(User user);
	
	/**
	 * 编辑用户
	 * 
	 * @param user
	 */
	public void edit(User user);
	
	/**
	 * 添加用户角色关系
	 * 
	 * @param relation
	 * 			用户角色关系
	 */
	public void saveUserRoleRelation(UserRoleRelation relation);
	
	/**
	 * 修改用户状态
	 * 
	 * @param id
	 * 		主键ID
	 * @param status
	 * 		用户状态
	 */
	public void modifyStatusById(Integer id, String status);
	
	/**
	 * 根据主键数组批量删除用户
	 * 
	 * @param ids
	 * 			用户ID字符串
	 */
	public void deleteUsers(String ids);
	
	/**
	 * 查询用户角色关系
	 * 
	 * @param userId
	 * 			用户ID
	 * @return
	 */
	public UserRoleRelation findUserRoleRelationByUserId(Integer userId);
}
