package com.es.hfuu.common.util.exception.util;

import com.es.hfuu.common.util.exception.base.BaseAppRuntimeException;
import com.es.hfuu.common.util.exception.base.ParameterizableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName LoggerUtil
 * @Description 异常捕获的日志工具类
 * @Author ykb
 * @Date 2019/11/8
 */
public class LoggerUtil {

    private static void error(String msg, Exception e) {
        innerError(innerGet(), msg, e);
    }

    private static void innerError(Logger log, String msg, Exception e) {
        log.error(msg, processTrace(e));
    }

    private static String processTrace(Throwable e, String parameterJson) {
        if (e == null) {
            return "";
        }
        if (e instanceof InvocationTargetException) {
            return processTrace(((InvocationTargetException)e).getTargetException());
        }
        if (e instanceof BaseAppRuntimeException && e.getCause() != null) {
            String parameters = null;
            if (e instanceof ParameterizableException) {
                ParameterizableException ex = (ParameterizableException) e;
                if (ex.getParameters() != null && ex.getParameters().length > 0) {
                    parameters = FastJsonUtil.toJsonStr(ex.getParameters());
                }
            }
            return processTrace(e.getCause(), parameters);
        }
        if (e.getStackTrace() == null || e.getStackTrace().length == 0) {
            return "";
        }
        List<StackTraceElement> tqStackElement =
                Arrays.stream(e.getStackTrace()).filter(element -> element.getClassName().startsWith("com.tianque")).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder("--error message detail：\n");
        if (null != parameterJson && parameterJson.trim().length() > 0) {
            sb.append("Parameters:").append(parameterJson).append("\n");
        }
        tqStackElement.forEach(element -> sb.append("\tat ").append(element.getClassName()).append(".").append(element.getMethodName())
                .append("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(")\n"));
        return sb.toString();
    }

    public static String processTrace(Throwable e) {
        return processTrace(e, null);
    }

    private static Logger innerGet() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[3].getClassName());
    }
}
