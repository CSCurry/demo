package com.demo.framework.xss;

import com.demo.framework.util.html.EscapeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSS过滤处理
 *
 * @author 30
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * @param request HttpServletRequest
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                // 防xss攻击和过滤前后空格
                escapeValues[i] = EscapeUtil.clean(values[i]).trim();
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }
}