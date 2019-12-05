package com.es.hfuu.common.mapper;

import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.vo.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SessionMapper
 * @Description 会话信息数据库层接口
 * @Author ykb
 * @Date 2019/12/5
 */
public interface SessionMapper extends BaseMapper<Session, PagingVO> {
    /**
     * 根据sesionId获取会话信息
     * @Title: getSessionBySessionId
     * @param sessionId 会话Id
     * @return Session 会话
     */
    Session getSessionBySessionId(String sessionId);

    /**
     * 根据userId查询sessionId
     * @Title: listSessionIdsByUserId
     * @param userId   用户ID
     * @return List<String> 会话Id列表
     */
    List<String> listSessionIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据userId删除session
     * @Title: deleteSessionByUserId
     * @param userId   用户ID
     * @return Integer 返回受影响行数（配合函数式接口使用，外部并无实际用处）
     */
    Integer deleteSessionByUserId( @Param("userId") Long userId);

    /**
     * 清除所有在线用户（启动服务时候用到，慎用）
     * @Title: removeAllOnlineUser
     */
    void removeAllOnlineUser();
}

