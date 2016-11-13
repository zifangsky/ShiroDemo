package cn.zifangsky.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import cn.zifangsky.model.VerifyCode;

@Controller
public class CaptchaController {
	
	@Autowired
	private Producer producer;
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/user/user/verify.html")
	public void generate(HttpServletRequest request,HttpServletResponse response){
		response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        
        String validateText = producer.createText();  //生成验证码文字
        //存储到session中
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, validateText);
        
        BufferedImage bImage = producer.createImage(validateText);
        try {
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write(bImage, "jpg", outputStream);
			outputStream.flush();
			
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验验证码
	 * @return 返回是否校验成功
	 */
	@RequestMapping(value="/user/user/checkVerifyCode.json",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> check(@RequestBody VerifyCode verifyCode,HttpServletRequest request){
		HttpSession session = request.getSession();
		Map<String, String> result = new HashMap<>();
		
		//session中的验证码
		String codeFromSession = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);  //使用之后删除
		
		if(StringUtils.isNotBlank(verifyCode.getVerifyCodeValue()) && StringUtils.isNotBlank(codeFromSession)){
			if(verifyCode.getVerifyCodeValue().equalsIgnoreCase(codeFromSession)){
				session.setAttribute("codeCheck", true);  //设置校验成功标志
				result.put("result", "ok");
			}else{
				result.put("result", "error");
			}
		}else{
			result.put("result", "error");
		}
		return result;
	}
}
