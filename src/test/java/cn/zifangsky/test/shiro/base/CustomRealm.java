package cn.zifangsky.test.shiro.base;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class CustomRealm implements Realm {
	/**
	 * 返回一个唯一的Realm名称
	 * */
	@Override
	public String getName() {
		return "CustomRealm";
	}

	/**
	 * 判断此Realm是否支持此Token
	 * */
	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}
	
	/**
	 * 自定义认证
	 * */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//根据token获取需要认证的信息（登录时输入的信息）
		String username = (String) token.getPrincipal();  //获取用户名
		String password = String.valueOf((char[])token.getCredentials());  //获取密码
		
		if(!"test".equals(username))
			throw new UnknownAccountException();
		if(!"123456".equals(password))
			throw new IncorrectCredentialsException();
		
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
