package cn.zifangsky.test.shiro.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Helloworld {
	
	/**
	 * 第一个实例
	 * 参考：http://jinnianshilongnian.iteye.com/blog/2019547
	 * */
	@Test
	public void testHello(){
		//1 获取SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/base/shiro.ini");
		
		//2  获取SecurityManager实例，并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//3 获取Subject，创建用户名/密码验证Token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
		
		try {
			//4 登录，即：身份认证
			subject.login(token);
			System.out.println("登录认证成功");
		} catch (AuthenticationException e) {
			//5 认证失败
			System.err.println("认证失败");
		}
		
		//6 退出登录
		subject.logout();
	}
	
	/**
	 * 测试自定义Realm
	 * */
	@Test
	public void testCustomRealm(){
		//1 获取SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/base/shiro-realm.ini");
		
		//2  获取SecurityManager实例，并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//3 获取Subject，创建用户名/密码验证Token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("test", "123456");
		try {
			//4 登录，即：身份认证
			subject.login(token);
			System.out.println("登录认证成功");
		} catch (AuthenticationException e) {
			//5 认证失败
			System.err.println("认证失败");
		}
		
		//6 退出登录
		subject.logout();
	}
	
	/**
	 * 测试JDBC Realm
	 * */
	@Test
	public void testJdbcRealm(){
		//1 获取SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/base/shiro-jdbc-realm.ini");
		
		//2  获取SecurityManager实例，并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//3 获取Subject，创建用户名/密码验证Token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		try {
			//4 登录，即：身份认证
			subject.login(token);
			System.out.println("登录认证成功");
		} catch (AuthenticationException e) {
			//5 认证失败
			System.err.println("认证失败");
		}
		
		//6 退出登录
		subject.logout();
	}

}
