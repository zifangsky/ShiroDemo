package cn.zifangsky.test.dao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.mapper.UsrRoleFuncMapper;
import cn.zifangsky.mapper.UsrUserMapper;
import cn.zifangsky.mapper.UsrUserRoleMapper;
import cn.zifangsky.model.UsrFunc;
import cn.zifangsky.model.UsrRole;
import cn.zifangsky.model.UsrUser;
import cn.zifangsky.utils.EncryptionUtil;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/context.xml"})
public class TestTable {
	
	@Autowired
	private UsrUserMapper userMapper;
	
	@Autowired
	private UsrUserRoleMapper userRoleMapper;

	@Autowired
	private UsrRoleFuncMapper roleFuncMapper;
	/**
	 * 测试jdbc连接
	 */
	@Test
	public void testSelectByPrimaryKey(){
		UsrUser user = userMapper.selectByPrimaryKey(1);
		
//		System.out.println(user.getName());
		Assert.assertEquals("admin", user.getUsername());  //预期值-实际值
	}
	
	/**
	 * 测试登录
	 */
	@Test
	public void testLogin(){
		UsrUser user = userMapper.selectByNamePasswd("admin", EncryptionUtil.sha256Hex("admin"));
		Assert.assertEquals("admin", user.getUsername());
	}
	
	/**
	 * 测试获取用户总数
	 */
	@Test
	public void testFindAllCount(){
		UsrUser user = new UsrUser();
		user.setUsername("test");
		user.setPassword("123456");
		
		int count = userMapper.findAllCount(user);
		System.out.println(count);
	}
	
	/**
	 * 查询第current条数据的id
	 */
	@Test
	public void testCurrent(){
		System.out.println(userMapper.currentNumId((long) 3));
	}
	
	/**
	 * 测试分页查询
	 */
	@Test
	public void testFindAll(){
		PageInfo pageInfo = new PageInfo();
		long perSize = 2;
		long currentPage = 2;
		//当前页的第一条数据是第几条数据（序列从0开始）
		long fromNum = (perSize * (currentPage - 1)) < 0 ? 0 : perSize * (currentPage - 1);
		//当前页的第一条数据的id
		long fromId = userMapper.currentNumId(fromNum);
		
		pageInfo.setPerSize(perSize);
		pageInfo.setCurrentPage(currentPage);		
		pageInfo.setFrom(fromId);
		pageInfo.setSortName("id");
		pageInfo.setSortOrder("ASC");
		
		UsrUser source = new UsrUser();
		
		List<UsrUser> list = userMapper.findAll(pageInfo, source);
		System.out.println("总数： " + list.size());
		
	}
	
	/**
	 * 测试根据用户ID查所有角色
	 */
	@Test
	public void testSelectRolesByUserId(){
		List<UsrRole> roles = userRoleMapper.selectRolesByUserId(5);
		for(UsrRole role : roles){
			System.out.println(role.getRolename());
		}
	}
	
	/**
	 * 测试根据角色ID查所有权限
	 */
	@Test
	public void testSelectFuncsByRoleId(){
		List<UsrFunc> funcs = roleFuncMapper.selectFuncsByRoleId(3);
		for(UsrFunc func : funcs){
			System.out.println(func.getName());
		}
	}

}
