package com.demo.framework.constant;

/**
 * 通用常量信息
 *
 * @author 30
 */
public class Constant {

    //Redis - 存AccessUser的key.后面跟clientId
    public static final String REDIS_KEY_PREFIX_ACCESS_USER_CLIENT_ID = "auth_user_client_id_";
    //Redis - 存AccessUser的key.后面跟token
    public static final String REDIS_KEY_PREFIX_ACCESS_USER_TOKEN = "auth_user_token_";
    //Redis - 存AccessUser的key.后面跟token.过期时间(秒)
    public static final long REDIS_KEY_EXPIRE_ACCESS_USER_TOKEN = 360;

    //请求URL路径后面的token参数键
    public static final String REQUEST_URL_PARAMETER_TOKEN = "token";

    //session key - 登录验证码
    public static final String SESSION_KEY_CAPTCHA = "SESSION_KEY_CAPTCHA";

    //config key - 默认皮肤样式名称
    public static final String CONFIG_KEY_SKIN_NAME = "sys.index.skinName";
    //config key - 账号初始密码
    public static final String CONFIG_KEY_INIT_PASSWORD = "sys.user.initPassword";
    //config key - 侧边栏主题
    public static final String CONFIG_KEY_SIDE_THEME = "sys.index.sideTheme";
    //config key - 是否开启用户注册功能
    public static final String CONFIG_KEY_REGISTER_USER = "sys.account.registerUser";
    //config key - 是否开启登录验证码
    public static final String CONFIG_KEY_CAPTCHA_ENABLED = "sys.captcha.enabled";
    //config key - 登录验证码类型
    public static final String CONFIG_KEY_CAPTCHA_TYPE = "sys.captcha.type";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";
    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";
    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";
    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";
    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";
    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
