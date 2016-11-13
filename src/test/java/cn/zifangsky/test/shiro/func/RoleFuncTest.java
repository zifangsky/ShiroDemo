package cn.zifangsky.test.shiro.func;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Assert;

@RunWith(JUnit4.class)
public class RoleFuncTest {
	
	@Test
	public void test(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cn/zifangsky/test/shiro/func/shiro-jdbc-func.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
		try {
			subject.login(token);
			Assert.assertEquals(true, subject.isAuthenticated());
			
			//判断用户是否拥有某个角色
			System.out.println(subject.hasRole("manager"));  //true
			System.out.println(subject.hasRole("editor"));  //false
			
			//判断是否被授权
			System.out.println(subject.isPermitted("YHGL:CX"));  //false
			System.out.println(subject.isPermitted("YHGL:XZ"));  //false
			
			subject.logout();
		} catch (IncorrectCredentialsException e) {
			System.out.println("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");  
        } catch (ExcessiveAttemptsException e) {  
            System.out.println("登录失败次数过多");  
        } catch (LockedAccountException e) {  
            System.out.println("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");  
        } catch (DisabledAccountException e) {  
            System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");  
        } catch (ExpiredCredentialsException e) {  
            System.out.println("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");  
        } catch (UnknownAccountException e) {  
            System.out.println("帐号不存在. There is no user with username of " + token.getPrincipal());  
        } 
		
	}
	
}
