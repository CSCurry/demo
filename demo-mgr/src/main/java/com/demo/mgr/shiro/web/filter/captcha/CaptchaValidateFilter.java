package com.demo.mgr.shiro.web.filter.captcha;

import com.demo.business.service.mgr.ISysConfigService;
import com.demo.framework.constant.Constant;
import com.demo.framework.constant.ShiroConstants;
import com.demo.framework.util.StringUtil;
import com.demo.mgr.shiro.ShiroUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码过滤器
 *
 * @author 30
 */
public class CaptchaValidateFilter extends AccessControlFilter {

    @Resource
    private ISysConfigService configService;

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        boolean captchaEnabled = "true".equals(configService.selectConfigByKey(Constant.CONFIG_KEY_CAPTCHA_ENABLED));
        request.setAttribute(ShiroConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(ShiroConstants.CURRENT_TYPE, configService.selectConfigByKey(Constant.CONFIG_KEY_CAPTCHA_TYPE));
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 验证码禁用 或不是表单提交 允许访问
        if (!"true".equals(configService.selectConfigByKey(Constant.CONFIG_KEY_CAPTCHA_ENABLED)) || !"post".equals(httpServletRequest.getMethod().toLowerCase())) {
            return true;
        }
        return validateResponse(httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE));
    }

    public boolean validateResponse(String validateCode) {
        Object obj = ShiroUtils.getSession().getAttribute(Constant.SESSION_KEY_CAPTCHA);
        String code = String.valueOf(obj != null ? obj : "");
        return StringUtil.isNotEmpty(validateCode) && validateCode.equalsIgnoreCase(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        request.setAttribute(ShiroConstants.CURRENT_CAPTCHA, ShiroConstants.CAPTCHA_ERROR);
        return true;
    }
}
