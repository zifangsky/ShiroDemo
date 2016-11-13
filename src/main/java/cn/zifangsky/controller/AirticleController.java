package cn.zifangsky.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AirticleController {
	
	@RequestMapping("/article/delete.html")
	@RequiresPermissions({ "WZGL:SC" })
	public ModelAndView test() {
		System.out.println("拥有'文章管理-删除'的权限");
		return new ModelAndView("/article/delete");
	}
}
