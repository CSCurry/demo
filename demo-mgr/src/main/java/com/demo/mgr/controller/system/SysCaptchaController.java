package com.demo.mgr.controller.system;

import com.demo.business.service.mgr.ISysConfigService;
import com.demo.framework.base.BaseController;
import com.demo.framework.constant.Constant;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制器
 *
 * @author 30
 */
@Controller
@RequestMapping("/captcha")
public class SysCaptchaController extends BaseController {

    @Resource
    private ISysConfigService configService;

    /**
     * 生成验证码
     *
     * <a href="https://gitee.com/whvse/EasyCaptcha></a>
     */
    @GetMapping("/captchaImage")
    public void captcha(HttpServletResponse response) throws Exception {
        //设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setDateHeader("Expires", 0);

        String captchaType = configService.selectConfigByKey(Constant.CONFIG_KEY_CAPTCHA_TYPE);
        if ("math".equals(captchaType)) {
            //Arithmetic是算术类型验证码
            //设置宽、高、位数
            ArithmeticCaptcha captcha = new ArithmeticCaptcha(123, 42, 2);
            //获取运算的结果
            String result = captcha.text();

            //验证码结果存session
            getSession().setAttribute(Constant.SESSION_KEY_CAPTCHA, result);
            //输出图片流
            captcha.out(response.getOutputStream());
        } else if ("char".equals(captchaType)) {
            //SpecCaptcha是字符验证码
            //设置宽、高、位数
            SpecCaptcha captcha = new SpecCaptcha(123, 42, 4);
            //设置类型，纯数字、纯字母、字母数字混合
            captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

            //验证码结果存session
            getSession().setAttribute(Constant.SESSION_KEY_CAPTCHA, captcha.text().toLowerCase());
            //输出图片流
            captcha.out(response.getOutputStream());
        }
    }
}