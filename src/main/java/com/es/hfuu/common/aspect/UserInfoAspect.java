package com.es.hfuu.common.aspect;

import com.es.hfuu.common.constants.Constants;
import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.common.util.exception.base.ServiceValidationException;
import com.es.hfuu.common.util.exception.util.LoggerUtil;
import com.es.hfuu.common.util.threadlocal.ThreadLocalMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName UserInfoAspect
 * @Description ccuu切面
 * @Author lsx
 * @Date 2019/12/12
 */
@Aspect
@Component
public class UserInfoAspect {
    protected Logger logger = LoggerFactory.getLogger(UserInfoAspect.class);

    /**
     * 拦截新增
     * @Title: saveUserInfo
     * @param joinPoint 连接点
     * @return void
     */
    @Before("execution(public * com.es.hfuu..mapper.*Mapper.save*(..) )")
    public void saveUserInfo(JoinPoint joinPoint){
        if(joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){
            Object[] args = joinPoint.getArgs();
            if (args == null || args[0] == null) {
                return;
            }
            try {
                BaseDomain info = (BaseDomain) args[0];
                info.setCreateUser((String) ThreadLocalMap.get(Constants.THREADLOCAL_USERNAME));
                info.setCreateUser("lsx");
                info.setCreateDate(new Date());
            } catch (Exception e) {
                logger.error("saveUserInfo:{}", LoggerUtil.processTrace(e));
                throw new ServiceValidationException("新增时补全用户信息出错", e);
            }
        }
    }

    /**
     * 拦截新增
     * @Title: saveUserInfo
     * @param joinPoint 连接点
     * @return void
     */
    @Before("execution(public * com.es.hfuu..mapper.*Mapper.update*(..) )")
    public void updateUserInfo(JoinPoint joinPoint){
        if(joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){
            Object[] args = joinPoint.getArgs();
            if (args == null || args[0] == null) {
                return;
            }
            try {
                BaseDomain info = (BaseDomain) args[0];
//                info.setUpdateUser((String) ThreadLocalMap.get(Constants.THREADLOCAL_USERNAME));
                info.setUpdateUser("lsx");
                info.setUpdateDate(new Date());
            } catch (Exception e) {
                logger.error("updateUserInfo:{}", LoggerUtil.processTrace(e));
                throw new ServiceValidationException("修改时补全用户信息出错", e);
            }
        }
    }
}
