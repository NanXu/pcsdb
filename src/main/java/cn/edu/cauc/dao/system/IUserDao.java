/**
 * 
 */
package cn.edu.cauc.dao.system;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.system.User;
import cn.edu.cauc.model.vo.Page;


/**
 * 作者：徐楠
 *
 * 描述：<p>用户 DAO 接口</p>
 * 创建时间：2016年2月12日
 */
public interface IUserDao extends IBaseDao<User> {
	
	/**
	 * 校验系统用户名是否存在
	 * 
	 * @param username
	 * 				系统用户名
	 * @return
	 */
	public boolean isUsernameExist(String username);
	
	/**
	 * 校验登录名是否存在
	 * 
	 * @param loginName
	 * 				系统登录名
	 * @return
	 */
	public boolean isLoginNameExist(String loginName);

	/**
	 * 通过用户名和密码查询用户信息
	 * 
	 * @param loginName
	 * 			用户名
	 * @param password
	 * 			密码
	 * @return
	 */
	public User getUserByLoginNameAndPwd(String loginName, String password);
	
	/**
	 * 查询用户信息
	 * 
	 * @param user
	 * 			用户信息参数	
	 * @return
	 */
	public Page<User> getUserList(User user, int pageNo, int pageSize);

	/**
	 * 查询事故用户信息
	 * 
	 * @param user
	 * 			用户信息参数	
	 * @return
	 */
	public Page<User> geEventUserList(User user, int pageNo, int pageSize);
}
