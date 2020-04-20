package com.es.hfuu.common.service.impl;

import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.mapper.SessionMapper;
import com.es.hfuu.common.service.SessionService;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.collection.CollectionUtil;
import com.es.hfuu.common.util.exception.base.BusinessValidationException;
import com.es.hfuu.common.util.exception.base.ServiceValidationException;
import com.es.hfuu.common.util.exception.util.LoggerUtil;
import com.es.hfuu.common.util.redis.util.SessionRedisUtil;
import com.es.hfuu.common.vo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.dbInvokeFunction;
import static com.es.hfuu.common.util.exception.util.ExceptionUtil.requireNonNull;

/**
 * @ClassName SessionServiceImpl
 * @Description 会话业务层实现
 * @Author lsx
 * @Date 2019/12/5
 */
@Service("sessionService")
public class SessionServiceImpl extends BaseServiceImpl<Session, PagingVO> implements SessionService {

    @Autowired
    SessionMapper sessionMapper;
    @Override
    public BaseMapper<Session, PagingVO> getBaseMapper() {
        return sessionMapper;
    }

    @Autowired
    @Qualifier("sessionRedisUtil")
    private SessionRedisUtil sessionRedisUtil;

    /**
     * 保存session
     * @Title: saveSession
     * @Description: 保存session
     * @param session session信息
     * @return: void 无
     */
    @Override
    public void saveSession(Session session) {
        if (null == session) {
            throw new BusinessValidationException("会话信息不能为空");
        }
        try {
            // 清除已有的session信息；
            deleteSessionByUserId(session.getUserId());
            // 保存session到数据库中
            dbInvokeFunction(this::saveEntity,"保存session信息",session);
            //将session保存到redis中
            sessionRedisUtil.saveSession(session);
        } catch (Exception e) {
            logger.error("saveSession:{}", LoggerUtil.processTrace(e));
            throw new ServiceValidationException("新增会话信息失败！", e);
        }
    }

    /**
     * 根据userId删除session
     * @Title: deleteSessionByUserId
     * @param userId   用户Id
     * @return Integer 返回受影响行数（配合函数式接口使用，外部并无实际用处）
     */
    @Override
    public Integer deleteSessionByUserId(Long userId) {
        requireNonNull("用户Id不能为空", userId);
        // 删除redis中保存的session信息
        List<String> list = listSessionIdsByUserId(userId);
        sessionRedisUtil.deleteSession(CollectionUtil.collectionToArray(list, String.class), new Long[]{userId});
        // 删除数据库中保存的session信息
        return dbInvokeFunction(sessionMapper::deleteSessionByUserId, getExceptionTitle(), userId);
    }

    /**
     * 更新session信息
     * @Title: updateSession
     * @Description: 更新session信息
     * @param session session信息
     * @return void 无
     */
    @Override
    public void updateSession(Session session) {
        if (null == session) {
            throw new BusinessValidationException("会话信息不能为空");
        }
        try {
            // 保存session到数据库中
            sessionMapper.updateEntity(session);
            //将session保存到redis中
            sessionRedisUtil.saveSession(session);
        } catch (Exception e) {
            logger.error("updateSession:{}", LoggerUtil.processTrace(e));
            throw new ServiceValidationException("更新会话信息失败！", e);
        }
    }

    /**
     * 根据sesionId获取会话信息
     * @Title: getSessionBySessionId
     * @Description: 根据sesionId获取会话信息
     * @param sessionId 会话Id
     * @return Session 会话
     */
    @Override
    public Session getSessionBySessionId(String sessionId) {
        if (StringUtil.isEmpty(sessionId)) {
            throw new BusinessValidationException("会话信息Id不能为空");
        }
        try {
            //从redis中获取session信息
            return sessionRedisUtil.getSessionBySessionId(sessionId);
        } catch (Exception e) {
            logger.error("saveSession:{}", LoggerUtil.processTrace(e));
            throw new ServiceValidationException("查询会话信息失败！", e);
        }
    }

    /**
     * 根据用户ID查询sessionId集合
     * @Title: listSessionIdsByUserId
     * @param userId   用户Id
     * @return List<String> 会话Id列表
     */
    @Override
    public List<String> listSessionIdsByUserId(Long userId) {
        requireNonNull("用户Id不能为空", userId);
        List<String> sessionIdList = dbInvokeFunction(sessionMapper::listSessionIdsByUserId, getExceptionTitle(), userId);
        if (sessionIdList == null) {
            sessionIdList = new ArrayList<>(0);
        }
        return sessionIdList;
    }

}

