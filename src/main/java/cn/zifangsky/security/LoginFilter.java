package cn.zifangsky.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.zifangsky.model.bo.UsrUserBO;

public class LoginFilter extends AuthenticatingFilter {

	/**
	 * 生成需要认证的信息
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		UsernamePasswordToken token = null;
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest r = attributes.getRequest();
		
		//从session中取出用户
		UsrUserBO userBO = (UsrUserBO) r.getSession().getAttribute("userBO");
		if(userBO != null){
			token = new UsernamePasswordToken(userBO.getUsername(), userBO.getPassword(), false, getHost(request));
		}
		return token;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return this.executeLogin(request, response);
	}

}
