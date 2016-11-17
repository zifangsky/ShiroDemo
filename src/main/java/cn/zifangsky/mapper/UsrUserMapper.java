package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.UsrUser;

public interface UsrUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UsrUser usrUser);

	int insertSelective(UsrUser usrUser);

	UsrUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UsrUser usrUser);

	int updateByPrimaryKey(UsrUser usrUser);

	/**
	 * 根据用户名、密码查询UsrUser对象
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	UsrUser selectByNamePasswd(@Param("username") String username, @Param("password") String password);

	/**
	 * 根据条件查询总数
	 * 
	 * @param usrUser
	 * @return 符合条件的用户数目
	 */
	int findAllCount(UsrUser usrUser);

	/**
	 * 分页查询
	 * 
	 * @param pageInfo
	 *            页面信息，包括：每页数据量、当前页、查询的起始id、按什么排序、顺序或倒序等
	 * @param usrUser
	 *            用户信息搜索条件
	 * @return
	 */
	List<UsrUser> findAll(@Param("pageInfo") PageInfo pageInfo, @Param("usrUser") UsrUser usrUser);

}