package com.es.hfuu.common.service;

import com.es.hfuu.common.domain.Session;

import java.util.List;

/**
 * @ClassName SessionService
 * @Description 会话业务层接口
 * @Author ykb
 * @Date 2019/12/5
 */
public interface SessionService {
    /**
     * 保存session
     * @Title: saveSession
     * @Description: 保存session
     * @param session session信息
     * @return: void 无
     */
    void saveSession(Session session);

    /**
     * 根据userId和登录的userType删除session
     * @Title: deleteSessionByUserId
     * @param userId   用户Id
     * @return Integer 返回受影响行数（配合函数式接口使用，外部并无实际用处）
     */
    Integer deleteSessionByUserId(Long userId);

    /**
     * 更新session信息
     * @Title: updateSession
     * @Description: 更新session信息
     * @param session session信息
     * @return void 无
     */
    void updateSession(Session session);

    /**
     * 根据sesionId获取会话信息
     * @Title: getSessionBySessionId
     * @Description: 根据sesionId获取会话信息
     * @param sessionId 会话Id
     * @return Session 会话
     */
    Session getSessionBySessionId(String sessionId);

    /**
     * 根据用户ID查询sessionId集合
     * @Title: listSessionIdsByUserId
     * @param userId   用户Id
     * @return List<String> 会话Id列表
     */
    List<String> listSessionIdsByUserId(Long userId);
}
