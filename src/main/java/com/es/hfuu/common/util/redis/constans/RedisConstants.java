package com.es.hfuu.common.util.redis.constans;

/**
 * @ClassName RedisConstants
 * @Description Redis常量类
 * @Author ykb
 * @Date 2019/11/12
 */
public interface RedisConstants {

    /**
     * 本系统session信息保存到cookie的key
     */
    String LOGIN_SESSION_ID = "rsid";

    /**
     * 本系统登录用户名信息保存到cookie的key
     */
    String LOGIN_USER_NAME = "rname";

    /**
     * session超时的时间（毫秒）30分钟
     */
    long SESSION_TIME_OUT = 1800000;

    /**
     * sessionId的前缀
     */
    String PREFIX_SESSION = "SSN_";

    /**
     * redis存储session的键
     */
    String SYSTEM_REDIS_SESSION_HASH_CACHE = "system_redis:session:ssnhash";

    /**
     * redis存储session的score的键
     */
    String SYSTEM_REDIS_SESSION_SCORE_CACHE = "system_redis:session:score4ssn";

    /**
     * 在线用户的Id缓存
     */
    String SYSTEM_REDIS_USR_ONLINE_CACHE = "system_redis:user:onlineuser";

    /**
     * 保存角色Id和对应的权限ename
     */
    String SYSTEM_REDIS_PERMISSION_ROLE_CACHE = "system_redis:permission:role";

    /**
     * 保存角色Id和对应的权限ename
     */
    String SHIRO_REDIS_SUBJECT_HASH_CACHE = "shiro_redis:subject:hash";


    /**
     * 保存角色Id和对应的菜单
     */
    String SYSTEM_REDIS_PERMISSION_MENU_ROLE_CACHE = "system_redis:permissionMenu:role";


}
