package cn.zifangsky.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zifangsky.manager.UsrUserManager;
import cn.zifangsky.model.UsrUser;
import cn.zifangsky.model.bo.UsrUserBO;

@Controller
public class UserController {
	@Resource(name = "usrUserManager")
	private UsrUserManager userManager;

	/**
	 * 登录校验
	 * 
	 * @param UsrUser
	 *            登录时的User对象，包括form表单中的用户名和密码
	 * @param request
	 * @return 是否登录成功标志
	 */
	@RequestMapping(value = "/user/user/check.json",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> loginCheck(@RequestBody UsrUser user,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, String> result = new HashMap<>();

		Boolean codeCheck = (Boolean) session.getAttribute("codeCheck");  //验证码是否校验成功标志
		session.removeAttribute("codeCheck");
		
		if (codeCheck != null && codeCheck) {
			UsrUserBO userBO = userManager.login(user.getUsername(), user.getPassword());
			if (userBO != null) {
				session.setAttribute("userBO", userBO); // 登录成功之后加入session中
				result.put("result", "success");
			} else {
				result.put("result", "error");
			}
		}else{
			result.put("result", "error");
		}
		return result;
	}
	
	/**
	 * 注销登录
	 * 
	 * @param request
	 * @return 重定向回登录页面
	 */
	@RequestMapping("/user/user/logout.html")
	public ModelAndView logout(HttpServletRequest request) {
		userManager.logout(request.getSession());
		return new ModelAndView("redirect:/user/user/login.html");
	}

}
