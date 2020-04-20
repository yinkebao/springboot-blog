package com.es.hfuu.common.util.exception.facade;

import com.es.hfuu.common.util.exception.annotation.Exceptional;
import com.es.hfuu.common.util.exception.base.BaseAppException;
import com.es.hfuu.common.util.exception.base.BaseAppRuntimeException;
import com.es.hfuu.common.util.exception.base.SystemUtilException;
import com.es.hfuu.common.util.exception.base.handler.ExceptionHandler;
import com.es.hfuu.common.util.exception.domain.ExceptionDefinition;
import com.es.hfuu.common.util.exception.domain.ExceptionResult;
import com.es.hfuu.common.util.exception.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExceptionHandlerFacade
 * @Description 全局异常处理
 * @Author lsx
 * @Date 2019/11/8
 */
public class ExceptionHandlerFacade {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerFacade.class);

    private static Map<Class<?>, ExceptionDefinition> exceptionMap = new HashMap<>();

    public static ExceptionResult dealBaseAppRuntimeException(Exception ex) {
        ExceptionResult result = new ExceptionResult();
        ExceptionDefinition exceptionDefinition = getExceptionDefinition(ex.getClass());
        if (exceptionDefinition == null) {
            return dealUnknownException(ex);
        }
        String errorCode = exceptionDefinition.getErrorCode();
        if (exceptionDefinition.getIsLogging()) {
            logger.error("intercept exception [errorCode=" + errorCode + getExceptionTitle(ex) + "]:{}", LoggerUtil.processTrace(ex));
        }
        result.setExpLevel(exceptionDefinition.getExpLevel());
        result.setErrorCode(errorCode);
        exceptionDefinition.getHandler().handleException(ex, result);
        return result;
    }

    private static String getExceptionTitle(Exception ex) {
        StringBuilder sb = new StringBuilder();
        String title = null;
        if ((ex instanceof BaseAppRuntimeException)) {
            title = ((BaseAppRuntimeException) ex).getTitle();
        }
        if ((ex instanceof BaseAppException)) {
            title = ((BaseAppException) ex).getTitle();
        }
        if (isNotEmpty(title)) {
            sb.append(", title=").append(title);
        }
        return sb.toString();
    }

    private static boolean isNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static ExceptionResult dealUnknownException(Exception ex) {
        ExceptionResult result = new ExceptionResult();
        logger.error("intercept exception [errorCode=UN100-001]:", ex);
        result.setErrorCode("UN100-001");
        result.setMessage("异常代码:[UN100-001] 操作失败，请重试或者联系管理员！");
        result.setExpLevel("error");
        return result;
    }

    private static ExceptionDefinition getExceptionDefinition(Class<?> expClazz) {
        if (!BaseAppRuntimeException.class.isAssignableFrom(expClazz.getSuperclass())) {
            return null;
        }
        if (exceptionMap.containsKey(expClazz)) {
            return exceptionMap.get(expClazz);
        } else {
            Exceptional exceptional = expClazz.getAnnotation(Exceptional.class);
            ExceptionDefinition expDefinition = new ExceptionDefinition(exceptional.errorCode(),
                    exceptional.expLevel(), exceptional.isLogging(), getExceptionHandler(exceptional.handler()));
            exceptionMap.put(expClazz, expDefinition);
            return expDefinition;
        }
    }

    private static ExceptionHandler getExceptionHandler(Class<? extends ExceptionHandler> handlerClazz) {
        try {
            return handlerClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("setExceptionHandler:{}", LoggerUtil.processTrace(e));
            throw new SystemUtilException("异常初始化失败", e);
        }
    }
}
