package com.es.hfuu.common.util.exception.util;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.common.function.*;
import com.es.hfuu.common.util.exception.annotation.ExceptionTitle;
import com.es.hfuu.common.util.exception.base.*;
import com.es.hfuu.common.util.exception.contants.ExceptionCode;
import com.es.hfuu.common.util.exception.domain.ExceptionResult;
import com.es.hfuu.common.util.exception.facade.ExceptionHandlerFacade;
import com.es.hfuu.common.vo.EsResult;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author ykb
 * @className ExceptionUtil
 * @description 异常处理工具类
 * @date 2019/11/8
 **/
public class ExceptionUtil {

    private ExceptionUtil() {}

    /** 默认异常主题 */
    private static final String DEFAULT_EXCEPTION_TITLE = "系统功能";

    /**
     * @Title: requireNonNull
     * @Description: 校验目标对象是否为空，并抛出参数非法异常
     * @param message 异常提示信息
     * @param obj 目标对象
     * @throw {@link ParameterIllegalException}
     * @return void 无
     */
    public static void requireNonNull(String message, Object obj) {
        requireNonNulls(message, obj);
    }

    /**
     * @Title: requireNonNull
     * @Description: 校验目标对象数组是否存在null值，并抛出参数非法异常
     * @param message 异常提示信息
     * @param objects 目标对象数组
     * @throw {@link ParameterIllegalException}
     * @return void 无
     */
    public static void requireNonNulls(String message, Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new ParameterIllegalException(message);
            }
        }
    }

    public static void requireValidString(String message, String param) {
        if (null == param || param.trim().length() == 0) {
            throw new ParameterIllegalException(message);
        }
    }

    /**
     * 校验目标baseDomain是否合法，并抛出参数非法异常
     * @Title: requireValidBaseDomain
     * @param message 异常提示信息
     * @param domain 目标baseDomain
     * @return void
     */
    public static void requireValidBaseDomain(String message, BaseDomain domain) {
        if (domain == null || domain.getId() == null) {
            throw new ParameterIllegalException(message);
        }
    }

    /**
     * 校验目标集合是否合法，并抛出参数非法异常
     * @Title: requireValidCollection
     * @param message 异常提示信息
     * @param collection 目标集合
     * @return void
     */
    public static void requireValidCollection(String message, Collection collection) {
        if (isEmpty(collection)) {
            throw new ParameterIllegalException(message);
        }
    }

    /**
     * isEmpty
     * @Title: 集合的空判断
     * @param collection
     * @return boolean 空：true，非空：false
     */
    private static <T> boolean isEmpty(Collection<T> collection){
        return collection == null || collection.size() == 0;
    }


    /**
     * 校验实体类和get方法调用的属性是否存在
     * @Title: requireValidDomainParam
     * @param businessDesc 业务描述
     * @param domain 实体类
     * @param getParam 实体类获取属性供给型接口（某属性的get方法）
     * @return void
     */
    public static void requireValidDomainParam(BaseDomain domain, Supplier<BaseDomain> getParam, String businessDesc) {
        if (domain == null) {
            throw new BusinessValidationException(businessDesc + "不存在");
        }
        if (getParam.get() == null || getParam.get().getId() == null) {
            throw new BusinessValidationException(businessDesc + "属性缺失");
        }
    }

    /**
     * @Title: serviceInvokeSupplier
     * @Description: 执行供给型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param supplier 供应者
     * @param businessDesc 业务描述
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 供给型接口返回值
     */
    public static <T> T serviceInvokeSupplier(Supplier<T> supplier, String businessDesc) {
        T t;
        try {
            t = supplier.get();
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
        return t;
    }

    /**
     * @Title: serviceInvokeFunction
     * @Description: 执行单参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 单参数功能型接口 {@link Function}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 单参数功能型接口返回值
     */
    public static <A, T> T serviceInvokeFunction(Function<A, T> function, String businessDesc, A a) {
        T t;
        try {
            t = function.apply(a);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
        return t;
    }

    /**
     * @Title: serviceInvokeFunction
     * @Description: 执行双参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 双参数功能型接口  {@link TwoParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 双参数功能型接口返回值
     */
    public static <A, B, T> T serviceInvokeFunction(TwoParamFunction<A, B, T> function, String businessDesc, A a, B b) {
        T t;
        try {
            t = function.apply(a, b);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
        return t;
    }

    /**
     * @Title: serviceInvokeFunction
     * @Description: 执行三参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 三参数功能型接口  {@link ThreeParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 三参数功能型接口返回值
     */
    public static <A, B, C, T> T serviceInvokeFunction(ThreeParamFunction<A, B, C, T> function,
                                                       String businessDesc, A a, B b, C c) {
        T t;
        try {
            t = function.apply(a, b, c);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
        return t;
    }

    /**
     * @Title: serviceInvokeFunction
     * @Description: 执行四参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 四参数功能型接口  {@link FourParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 四参数功能型接口返回值
     */
    public static <A, B, C, D, T> T serviceInvokeFunction(FourParamFunction<A, B, C, D, T> function,
                                                          String businessDesc, A a, B b, C c, D d) {
        T t;
        try {
            t = function.apply(a, b, c, d);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
        return t;
    }

    /**
     * @Title: serviceInvokeFunction
     * @Description: 执行五参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 五参数功能型接口  {@link FiveParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 五参数功能型接口返回值
     */
    public static <A, B, C, D, E, T> T serviceInvokeFunction(FiveParamFunction<A, B, C, D, E, T> function,
                                                             String businessDesc, A a, B b, C c, D d, E e) {
        T t;
        try {
            t = function.apply(a, b, c, d, e);
        } catch (Exception ex) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", ex);
        }
        return t;
    }


    /**
     * @Title: serviceInvokeSupplierAndValidate
     * @Description: 执行供给型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param supplier 供应者
     * @param businessDesc 业务描述
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 供给型接口返回值
     */
    public static <T> T serviceInvokeSupplierAndValidate(Supplier<T> supplier, String businessDesc) {
        T t = serviceInvokeSupplier(supplier, businessDesc);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: serviceInvokeFunctionAndValidate
     * @Description: 执行单参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 单参数功能型接口 {@link Function}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 单参数功能型接口返回值
     */
    public static <A, T> T serviceInvokeFunctionAndValidate(Function<A, T> function, String businessDesc, A a) {
        T t = serviceInvokeFunction(function, businessDesc, a);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: serviceInvokeFunctionAndValidate
     * @Description: 执行双参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 双参数功能型接口  {@link TwoParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 双参数功能型接口返回值
     */
    public static <A, B, T> T serviceInvokeFunctionAndValidate(TwoParamFunction<A, B, T> function, String businessDesc, A a, B b) {
        T t = serviceInvokeFunction(function, businessDesc, a, b);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: serviceInvokeFunctionAndValidate
     * @Description: 执行三参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 三参数功能型接口  {@link ThreeParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 三参数功能型接口返回值
     */
    public static <A, B, C, T> T serviceInvokeFunctionAndValidate(ThreeParamFunction<A, B, C, T> function,
                                                                  String businessDesc, A a, B b, C c) {
        T t = serviceInvokeFunction(function, businessDesc, a, b, c);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: serviceInvokeFunctionAndValidate
     * @Description: 执行四参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 四参数功能型接口  {@link FourParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 四参数功能型接口返回值
     */
    public static <A, B, C, D, T> T serviceInvokeFunctionAndValidate(FourParamFunction<A, B, C, D, T> function,
                                                                     String businessDesc, A a, B b, C c, D d) {
        T t = serviceInvokeFunction(function, businessDesc, a, b, c, d);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: serviceInvokeFunctionAndValidate
     * @Description: 执行五参数功能型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     *               校验返回值，若结果为null则抛出服务返回值非法异常
     * @param function 五参数功能型接口  {@link FiveParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @throw {@link ServiceValidationException}
     * @throw {@link BusinessValidationException}
     * @return T 五参数功能型接口返回值
     */
    public static <A, B, C, D, E, T> T serviceInvokeFunctionAndValidate(FiveParamFunction<A, B, C, D, E, T> function,
                                                                        String businessDesc, A a, B b, C c, D d, E e) {
        T t = serviceInvokeFunction(function, businessDesc, a, b, c, d, e);
        validateServiceResult(t, businessDesc);
        return t;
    }

    /**
     * @Title: validateServiceResult
     * @Description: 校验目标对象是否为空，并抛出服务调用返回值非法异常
     * @param t 目标对象
     * @param businessDesc 业务描述
     * @throw {@link BusinessValidationException}
     * @return void 无
     */
    private static <T> void validateServiceResult(T t, String businessDesc) {
        if (t == null) {
            throw new BusinessValidationException(businessDesc + "不存在");
        }
    }

    /**
     * @Title: serviceInvokePredicate
     * @Description: 执行单参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param predicate 单参数断言型接口 {@link Predicate}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link ServiceValidationException}
     * @return boolean
     */
    public static <A> boolean serviceInvokePredicate(Predicate<A> predicate, String businessDesc, A a) {
        try {
            return predicate.test(a);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokePredicate
     * @Description: 执行双参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param predicate 双参数断言型接口 {@link TwoParamPredicate}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link ServiceValidationException}
     * @return boolean
     */
    public static <A, B> boolean serviceInvokePredicate(TwoParamPredicate<A, B> predicate, String businessDesc, A a, B b) {
        try {
            return predicate.test(a, b);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokePredicate
     * @Description: 执行三参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param predicate 三参数断言型接口 {@link ThreeParamPredicate}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link ServiceValidationException}
     * @return boolean
     */
    public static <A, B, C> boolean serviceInvokePredicate(ThreeParamPredicate<A, B, C> predicate,
                                                           String businessDesc, A a, B b, C c) {
        try {
            return predicate.test(a, b, c);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokePredicate
     * @Description: 执行四参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param predicate 四参数断言型接口 {@link FourParamPredicate}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link ServiceValidationException}
     * @return boolean
     */
    public static <A, B, C, D> boolean serviceInvokePredicate(FourParamPredicate<A, B, C, D> predicate,
                                                              String businessDesc, A a, B b, C c, D d) {
        try {
            return predicate.test(a, b, c, d);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokePredicate
     * @Description: 执行五参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param predicate 五参数断言型接口 {@link FiveParamPredicate}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @throw {@link ServiceValidationException}
     * @return boolean
     */
    public static <A, B, C, D, E> boolean serviceInvokePredicate(FiveParamPredicate<A, B, C, D, E> predicate,
                                                                 String businessDesc, A a, B b, C c, D d, E e) {
        try {
            return predicate.test(a, b, c, d, e);
        } catch (Exception ex) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", ex);
        }
    }

    /**
     * @Title: serviceInvokeConsumer
     * @Description: 执行单参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param consumer 单参数消费型接口 {@link Consumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link ServiceValidationException}
     * @return void 无
     */
    public static <A> void serviceInvokeConsumer(Consumer<A> consumer, String businessDesc, A a) {
        try {
            consumer.accept(a);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokeConsumer
     * @Description: 执行双参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param consumer 双参数消费型接口 {@link TwoParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link ServiceValidationException}
     * @return void 无
     */
    public static <A, B> void serviceInvokeConsumer(TwoParamConsumer<A, B> consumer,
                                                    String businessDesc, A a, B b) {
        try {
            consumer.accept(a, b);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokeConsumer
     * @Description: 执行三参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param consumer 三参数消费型接口 {@link ThreeParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link ServiceValidationException}
     * @return void 无
     */
    public static <A, B, C> void serviceInvokeConsumer(ThreeParamConsumer<A, B, C> consumer,
                                                       String businessDesc, A a, B b, C c) {
        try {
            consumer.accept(a, b, c);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokeConsumer
     * @Description: 执行四参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param consumer 四参数消费型接口 {@link FourParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link ServiceValidationException}
     * @return void 无
     */
    public static <A, B, C, D> void serviceInvokeConsumer(FourParamConsumer<A, B, C, D> consumer,
                                                          String businessDesc, A a, B b, C c, D d) {
        try {
            consumer.accept(a, b, c, d);
        } catch (Exception e) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", e);
        }
    }

    /**
     * @Title: serviceInvokeConsumer
     * @Description: 执行五参数断言型接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出服务调用异常
     * @param consumer 五参数消费型接口 {@link FiveParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @throw {@link ServiceValidationException}
     * @return void 无
     */
    public static <A, B, C, D, E> void serviceInvokeConsumer(FiveParamConsumer<A, B, C, D, E> consumer,
                                                             String businessDesc, A a, B b, C c, D d, E e) {
        try {
            consumer.accept(a, b, c, d, e);
        } catch (Exception ex) {
            throw new ServiceValidationException(businessDesc + "服务调用失败", ex);
        }
    }

    /**
     * @Title: dbInvokeSupplier
     * @Description: 执行供给型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param supplier 供给型数据库接口 {@link Supplier}
     * @param businessDesc 业务描述
     * @throw {@link OperationFailedException}
     * @return T 供给型数据库接口返回值
     */
    public static <T> T dbInvokeSupplier(Supplier<T> supplier, String businessDesc) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e);
        }
    }

    /**
     * @Title: dbInvokeFunction
     * @Description: 执行单参数功能型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param function 单参数功能型数据库接口 {@link Function}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link OperationFailedException}
     * @return T 单参数功能型数据库接口返回值
     */
    public static <A, T> T dbInvokeFunction(Function<A, T> function, String businessDesc, A a) {
        try {
            return function.apply(a);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e, a);
        }
    }

    /**
     * @Title: dbInvokeFunction
     * @Description: 执行双参数功能型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param function 双参数功能型数据库接口 {@link TwoParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link OperationFailedException}
     * @return T 双参数功能型数据库接口返回值
     */
    public static <A, B, T> T dbInvokeFunction(TwoParamFunction<A, B, T> function,
                                               String businessDesc, A a, B b) {
        try {
            return function.apply(a, b);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e, a, b);
        }
    }

    /**
     * @Title: dbInvokeFunction
     * @Description: 执行三参数功能型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param function 三参数功能型数据库接口 {@link ThreeParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link OperationFailedException}
     * @return T 三参数功能型数据库接口返回值
     */
    public static <A, B, C, T> T dbInvokeFunction(ThreeParamFunction<A, B, C, T> function,
                                                  String businessDesc, A a, B b, C c) {
        try {
            return function.apply(a, b, c);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e, a, b, c);
        }
    }

    /**
     * @Title: dbInvokeFunction
     * @Description: 执行四参数功能型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param function 四参数功能型数据库接口 {@link FourParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link OperationFailedException}
     * @return T 四参数功能型数据库接口返回值
     */
    public static <A, B, C, D, T> T dbInvokeFunction(FourParamFunction<A, B, C, D, T> function,
                                                     String businessDesc, A a, B b, C c, D d) {
        try {
            return function.apply(a, b, c, d);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e, a, b, c, d);
        }
    }

    /**
     * @Title: dbInvokeFunction
     * @Description: 执行五参数功能型数据库接口并返回调用结果
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param function 五参数功能型数据库接口 {@link FiveParamFunction}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link OperationFailedException}
     * @return T 五参数功能型数据库接口返回值
     */
    public static <A, B, C, D, E, T> T dbInvokeFunction(FiveParamFunction<A, B, C, D, E, T> function,
                                                        String businessDesc, A a, B b, C c, D d, E e) {
        try {
            return function.apply(a, b, c, d, e);
        } catch (Exception ex) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", ex, a, b, c, d, e);
        }
    }

    /**
     * @Title: dbInvokeConsumer
     * @Description: 执行单参数消费型数据库接口
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param consumer 单参数消费型数据库接口 {@link Consumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @throw {@link OperationFailedException}
     * @return void 无
     */
    public static <A> void dbInvokeConsumer(Consumer<A> consumer, String businessDesc, A a) {
        try {
            consumer.accept(a);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e);
        }
    }

    /**
     * @Title: dbInvokeConsumer
     * @Description: 执行双参数消费型数据库接口
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param consumer 双参数消费型数据库接口 {@link TwoParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @throw {@link OperationFailedException}
     * @return void 无
     */
    public static <A, B> void dbInvokeConsumer(TwoParamConsumer<A, B> consumer,
                                               String businessDesc, A a, B b) {
        try {
            consumer.accept(a, b);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e);
        }
    }

    /**
     * @Title: dbInvokeConsumer
     * @Description: 执行三参数消费型数据库接口
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param consumer 三参数消费型数据库接口 {@link ThreeParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @throw {@link OperationFailedException}
     * @return void 无
     */
    public static <A, B, C> void dbInvokeConsumer(ThreeParamConsumer<A, B, C> consumer,
                                                  String businessDesc, A a, B b, C c) {
        try {
            consumer.accept(a, b, c);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e);
        }
    }

    /**
     * @Title: dbInvokeConsumer
     * @Description: 执行四参数消费型数据库接口
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param consumer 四参数消费型数据库接口 {@link FourParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @throw {@link OperationFailedException}
     * @return void 无
     */
    public static <A, B, C, D> void dbInvokeConsumer(FourParamConsumer<A, B, C, D> consumer,
                                                     String businessDesc, A a, B b, C c, D d) {
        try {
            consumer.accept(a, b, c, d);
        } catch (Exception e) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", e);
        }
    }

    /**
     * @Title: dbInvokeConsumer
     * @Description: 执行五参数消费型数据库接口
     *               若执行失败，则进行异常捕获，并抛出操作失败异常
     * @param consumer 五参数消费型数据库接口 {@link FiveParamConsumer}
     * @param businessDesc 业务描述
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @throw {@link OperationFailedException}
     * @return void 无
     */
    public static <A, B, C, D, E> void dbInvokeConsumer(FiveParamConsumer<A, B, C, D, E> consumer,
                                                        String businessDesc, A a, B b, C c, D d, E e) {
        try {
            consumer.accept(a, b, c, d, e);
        } catch (Exception ex) {
            throw new OperationFailedException(businessDesc + "数据库操作失败", ex);
        }
    }

    /**
     * @Title: providerServiceInvokeSupplier
     * @Description: 执行供给型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param supplier 供给型服务端接口 {@link Supplier}
     * @return EsResult {@link EsResult}
     */
    public static <R> EsResult<R> providerServiceInvokeSupplier(Supplier<R> supplier) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(supplier.get());
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeFunction
     * @Description: 执行单参数功能型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param function 单参数功能型服务端接口 {@link Function}
     * @param a 参数1
     * @return EsResult {@link EsResult}
     */
    public static <A, R> EsResult<R> providerServiceInvokeFunction(Function<A, R> function, A a) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(function.apply(a));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeFunction
     * @Description: 执行双参数功能型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param function 双参数功能型服务端接口 {@link TwoParamFunction}
     * @param a 参数1
     * @param b 参数2
     * @return EsResult {@link EsResult}
     */
    public static <A, B, R> EsResult<R> providerServiceInvokeFunction(TwoParamFunction<A, B, R> function, A a, B b) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(function.apply(a, b));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeFunction
     * @Description: 执行三参数功能型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param function 三参数功能型服务端接口 {@link ThreeParamFunction}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, R> EsResult<R> providerServiceInvokeFunction(ThreeParamFunction<A, B, C, R> function,
                                                                           A a, B b, C c) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(function.apply(a, b, c));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeFunction
     * @Description: 执行四参数功能型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param function 四参数功能型服务端接口 {@link FourParamFunction}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D, R> EsResult<R> providerServiceInvokeFunction(FourParamFunction<A, B, C, D, R> function,
                                                                            A a, B b, C c, D d) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(function.apply(a, b, c, d));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeFunction
     * @Description: 执行五参数功能型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param function 五参数功能型服务端接口 {@link FiveParamFunction}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D, E, R> EsResult<R> providerServiceInvokeFunction(FiveParamFunction<A, B, C, D, E, R> function,
                                                                               A a, B b, C c, D d, E e) {
        EsResult<R> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(function.apply(a, b, c, d, e));
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokePredicate
     * @Description: 执行单参数断言型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param predicate 单参数断言型服务端接口 {@link Predicate}
     * @param a 参数1
     * @return EsResult {@link EsResult}
     */
    public static <A> EsResult<Boolean> providerServiceInvokePredicate(Predicate<A> predicate, A a) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(predicate.test(a));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokePredicate
     * @Description: 执行双参数断言型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param predicate 双参数断言型服务端接口 {@link TwoParamPredicate}
     * @param a 参数1
     * @param b 参数2
     * @return EsResult {@link EsResult}
     */
    public static <A, B> EsResult<Boolean> providerServiceInvokePredicate(TwoParamPredicate<A, B> predicate, A a, B b) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(predicate.test(a, b));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokePredicate
     * @Description: 执行三参数断言型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param predicate 三参数断言型服务端接口 {@link ThreeParamPredicate}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C> EsResult<Boolean> providerServiceInvokePredicate(ThreeParamPredicate<A, B, C> predicate,
                                                                               A a, B b, C c) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(predicate.test(a, b, c));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokePredicate
     * @Description: 执行四参数断言型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param predicate 四参数断言型服务端接口 {@link FourParamPredicate}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D> EsResult<Boolean> providerServiceInvokePredicate(FourParamPredicate<A, B, C, D> predicate,
                                                                                  A a, B b, C c, D d) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(predicate.test(a, b, c, d));
        } catch (BaseAppRuntimeException e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(e);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception e) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(e);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokePredicate
     * @Description: 执行五参数断言型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param predicate 五参数断言型服务端接口 {@link FiveParamPredicate}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D, E> EsResult<Boolean> providerServiceInvokePredicate(FiveParamPredicate<A, B, C, D, E> predicate,
                                                                                   A a, B b, C c, D d, E e) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            result.setModuleWithSuccess(predicate.test(a, b, c, d, e));
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeSelfSufficient
     * @Description: 自给型消费函数。
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 单参数消费型服务端接口 {@link Consumer}
     * @return EsResult {@link EsResult}
     */
    public static EsResult<Boolean> providerServiceInvokeSelfSufficient(SelfSufficient consumer) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.sufficient();
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeConsumer
     * @Description: 执行单参数消费型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 单参数消费型服务端接口 {@link Consumer}
     * @param a 参数1
     * @return EsResult {@link EsResult}
     */
    public static <A> EsResult<Boolean> providerServiceInvokeConsumer(Consumer<A> consumer, A a) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.accept(a);
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeConsumer
     * @Description: 执行双参数消费型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 双参数消费型服务端接口 {@link TwoParamConsumer}
     * @param a 参数1
     * @param b 参数2
     * @return EsResult {@link EsResult}
     */
    public static <A, B> EsResult<Boolean> providerServiceInvokeConsumer(TwoParamConsumer<A, B> consumer, A a, B b) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.accept(a, b);
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeConsumer
     * @Description: 执行三参数消费型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 三参数消费型服务端接口 {@link ThreeParamConsumer}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C> EsResult<Boolean> providerServiceInvokeConsumer(ThreeParamConsumer<A, B, C> consumer,
                                                                              A a, B b, C c) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.accept(a, b, c);
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeConsumer
     * @Description: 执行四参数消费型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 四参数消费型服务端接口 {@link ThreeParamConsumer}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D> EsResult<Boolean> providerServiceInvokeConsumer(FourParamConsumer<A, B, C, D> consumer,
                                                                                 A a, B b, C c, D d) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.accept(a, b, c, d);
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * @Title: providerServiceInvokeConsumer
     * @Description: 执行五参数消费型服务端接口
     *               若执行失败，则进行异常捕获，并封装异常结果
     * @param consumer 五参数消费型服务端接口 {@link ThreeParamConsumer}
     * @param a 参数1
     * @param b 参数2
     * @param c 参数3
     * @param d 参数4
     * @param e 参数5
     * @return EsResult {@link EsResult}
     */
    public static <A, B, C, D, E> EsResult<Boolean> providerServiceInvokeConsumer(FiveParamConsumer<A, B, C, D, E> consumer,
                                                                                    A a, B b, C c, D d, E e) {
        EsResult<Boolean> result = new EsResult<>();
        try {
            consumer.accept(a, b, c, d, e);
            result.setSuccess(true);
        } catch (BaseAppRuntimeException ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealBaseAppRuntimeException(ex);
            result.setErr(exceptionResult.getErrorCode(), exceptionResult.getMessage());
        } catch (Exception ex) {
            ExceptionResult exceptionResult = ExceptionHandlerFacade.dealUnknownException(ex);
            result.setErr(ExceptionCode.UNKNOWN_CODE, exceptionResult.getMessage());
        }
        return result;
    }

    /**
     * 运行时获取目标类预定义的异常主题
     * @Title: getExceptionTitle
     * @param clazz 目标类
     * @return String 目标类异常主题
     */
    public static String getExceptionTitle(Class<?> clazz) {
        ExceptionTitle exceptionTitle = clazz.getDeclaredAnnotation(ExceptionTitle.class);
        if (exceptionTitle == null) {
            return DEFAULT_EXCEPTION_TITLE;
        }
        return exceptionTitle.value();
    }

}
