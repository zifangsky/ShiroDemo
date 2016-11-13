package cn.zifangsky.manager;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.UsrUser;
import cn.zifangsky.model.bo.UsrUserBO;

public interface UsrUserManager {
	public int deleteByPrimaryKey(Integer id);

	public int insert(UsrUser usrUser);

	public int insertSelective(UsrUser usrUser);

	public UsrUser selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(UsrUser usrUser);

	public int updateByPrimaryKey(UsrUser usrUser);

	/**
	 * 根据条件查询总数
	 * 
	 * @param usrUser
	 * @return 符合条件的用户数目
	 */
	public int findAllCount(UsrUser usrUser);

	/**
	 * 分页查询
	 * 
	 * @param pageInfo
	 *            页面信息，包括：每页数据量、当前页、查询的起始id、按什么排序、顺序或倒序等
	 * @param usrUser
	 *            用户信息搜索条件
	 * @return 一页的UsrUser列表
	 */
	List<UsrUser> findAll(@Param("pageInfo") PageInfo pageInfo, @Param("usrUser") UsrUser usrUser);

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public UsrUserBO login(String username, String password);

	/**
	 * 退出登录
	 */
	public void logout(HttpSession session);
}
