package cn.zifangsky.test.shiro.func;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RoleTest {
	/**
	 * 粒度大，如果某种角色不存在了则需要删除所有的用户对应的角色信息
	 * */
	@Test
	public void testHasRole() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/func/shiro-role.ini");

		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");

		subject.login(token);

		System.out.println(subject.hasRole("role1"));
		System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		boolean[] results = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		System.out.println("results[0]： " + results[0]);
		System.out.println("results[1]： " + results[1]);
		System.out.println("results[2]： " + results[2]);
		
//		subject.checkRole("role3");
	}
	
	/**
	 * 粒度小，如果某种角色不存在了只需要删除该角色即可
	 * */
	@Test
	public void testIsPermitted(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/func/shiro-permission.ini");

		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");

		subject.login(token);
		
		System.out.println(subject.isPermitted("user:create"));
		System.out.println(subject.isPermittedAll("user:create","user:delete"));
	}

}
