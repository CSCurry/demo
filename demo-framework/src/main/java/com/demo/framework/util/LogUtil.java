package com.demo.framework.util;

import com.demo.framework.util.json.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * 处理并记录日志文件
 *
 * @author 30
 */
public class LogUtil {

    public static final Logger ERROR_LOG = LoggerFactory.getLogger("sys-error");
    public static final Logger ACCESS_LOG = LoggerFactory.getLogger("sys-access");

    /**
     * 记录访问日志 [username][jsessionid][ip][accept][UserAgent][url][params][Referer]
     *
     * @param request HttpServletRequest
     * @throws Exception Exception
     */
    public static void logAccess(HttpServletRequest request) throws Exception {
        String username = getUsername();
        String jSessionId = request.getRequestedSessionId();
        String ip = IpUtil.getIpAddr(request);
        String accept = request.getHeader("accept");
        String userAgent = request.getHeader("User-Agent");
        String url = request.getRequestURI();
        String params = getParams(request);

        StringBuilder s = new StringBuilder();
        s.append(StringUtil.getBlock(username));
        s.append(StringUtil.getBlock(jSessionId));
        s.append(StringUtil.getBlock(ip));
        s.append(StringUtil.getBlock(accept));
        s.append(StringUtil.getBlock(userAgent));
        s.append(StringUtil.getBlock(url));
        s.append(StringUtil.getBlock(params));
        s.append(StringUtil.getBlock(request.getHeader("Referer")));
        getAccessLog().info(s.toString());
    }

    /**
     * 记录异常错误 格式 [exception]
     *
     * @param message message
     * @param e       Throwable
     */
    public static void logError(String message, Throwable e) {
        String username = getUsername();
        StringBuilder s = new StringBuilder();
        s.append(StringUtil.getBlock("exception"));
        s.append(StringUtil.getBlock(username));
        s.append(StringUtil.getBlock(message));
        ERROR_LOG.error(s.toString(), e);
    }

    /**
     * 记录页面错误 错误日志记录 [page/eception][username][statusCode][errorMessage][servletName][uri][exceptionName][ip][exception]
     *
     * @param request HttpServletRequest
     */
    public static void logPageError(HttpServletRequest request) {
        String username = getUsername();

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (statusCode == null) {
            statusCode = 0;
        }

        StringBuilder s = new StringBuilder();
        s.append(StringUtil.getBlock(t == null ? "page" : "exception"));
        s.append(StringUtil.getBlock(username));
        s.append(StringUtil.getBlock(statusCode));
        s.append(StringUtil.getBlock(message));
        s.append(StringUtil.getBlock(IpUtil.getIpAddr(request)));

        s.append(StringUtil.getBlock(uri));
        s.append(StringUtil.getBlock(request.getHeader("Referer")));
        StringWriter sw = new StringWriter();

        while (t != null) {
            t.printStackTrace(new PrintWriter(sw));
            t = t.getCause();
        }
        s.append(StringUtil.getBlock(sw.toString()));
        getErrorLog().error(s.toString());

    }

    protected static String getParams(HttpServletRequest request) throws Exception {
        Map<String, String[]> params = request.getParameterMap();
        return JSON.marshal(params);
    }

    protected static String getUsername() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    public static Logger getAccessLog() {
        return ACCESS_LOG;
    }

    public static Logger getErrorLog() {
        return ERROR_LOG;
    }
}
