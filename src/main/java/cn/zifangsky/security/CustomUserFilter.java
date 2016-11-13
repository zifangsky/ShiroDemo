package cn.zifangsky.security;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.UserFilter;

/**
 * 自定义UserFilter，添加忽略列表
 * @author zifangsky
 *
 */
public class CustomUserFilter extends UserFilter {
	private List<String> ignoreList;  //忽略列表
	
	public List<String> getIgnoreList() {
		return ignoreList;
	}

	public void setIgnoreList(List<String> ignoreList) {
		this.ignoreList = ignoreList;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) {
		String uri = ((HttpServletRequest)request).getRequestURI();
		
		if(ignoreList != null && ignoreList.size() > 0){
			for(String ignore : ignoreList){
				if(uri.contains(ignore)){
					return true;
				}
			}
		}
		
		return super.isAccessAllowed(request, response, object);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		return super.onAccessDenied(request, response);
	}
	
}
