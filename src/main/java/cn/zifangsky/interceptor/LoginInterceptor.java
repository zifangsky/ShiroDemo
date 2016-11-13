package cn.zifangsky.interceptor;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 用于校验验证码是否验证成功
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Boolean codeCheck = (Boolean) session.getAttribute("codeCheck");  //验证码是否校验成功标志
		
		// 使用之后从session中删掉
		session.removeAttribute("codeCheck");

		// 校验验证码是否验证成功
		if (codeCheck != null && codeCheck) {
			return true;
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(403);

			// 页面友好提示信息
			OutputStream oStream = response.getOutputStream();
			if(codeCheck == null || (codeCheck != null && !codeCheck)){
				oStream.write("验证码未校验通过，返回原始页面刷新后再次尝试！！！".getBytes("UTF-8"));
			}
			
			return false;
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
