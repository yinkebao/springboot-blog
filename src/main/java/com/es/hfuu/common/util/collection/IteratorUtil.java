package com.es.hfuu.common.util.collection;

import java.util.*;

/**
 * @ClassName IteratorUtil
 * @Description 迭代器工具类
 * @Author ykb
 * @Date 2019/12/5
 */
public class IteratorUtil {
    /**
     * Iterable是否为空
     *
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }

    /**
     * Iterator是否为空
     *
     * @param iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterator<?> iterator) {
        return null == iterator || false == iterator.hasNext();
    }

    /**
     * Iterable是否为空
     *
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }

    /**
     * Iterator是否为空
     *
     * @param iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterator<?> iterator) {
        return null != iterator && iterator.hasNext();
    }

    /**
     * 是否包含{@code null}元素
     *
     * @param iter 被检查的{@link Iterable}对象，如果为{@code null} 返回false
     * @return 是否包含{@code null}元素
     */
    public static boolean hasNull(Iterable<?> iter) {
        return hasNull(null == iter ? null : iter.iterator());
    }

    /**
     * 是否包含{@code null}元素
     *
     * @param iter 被检查的{@link Iterator}对象，如果为{@code null} 返回false
     * @return 是否包含{@code null}元素
     */
    public static boolean hasNull(Iterator<?> iter) {
        if (null == iter) {
            return true;
        }
        while (iter.hasNext()) {
            if (null == iter.next()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否全部元素为null
     *
     * @param iter iter 被检查的{@link Iterable}对象，如果为{@code null} 返回true
     * @return 是否全部元素为null
     * @since 3.3.0
     */
    public static boolean isAllNull(Iterable<?> iter) {
        return isAllNull(null == iter ? null : iter.iterator());
    }

    /**
     * 是否全部元素为null
     *
     * @param iter iter 被检查的{@link Iterator}对象，如果为{@code null} 返回true
     * @return 是否全部元素为null
     * @since 3.3.0
     */
    public static boolean isAllNull(Iterator<?> iter) {
        if (null == iter) {
            return true;
        }

        while (iter.hasNext()) {
            if (null != iter.next()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据集合返回一个元素计数的 {@link Map}<br>
     * 所谓元素计数就是假如这个集合中某个元素出现了n次，那将这个元素做为key，n做为value<br>
     * 例如：[a,b,c,c,c] 得到：<br>
     * a: 1<br>
     * b: 1<br>
     * c: 3<br>
     *
     * @param <T> 集合元素类型
     * @param iter {@link Iterable}，如果为null返回一个空的Map
     * @return {@link Map}
     */
    public static <T> Map<T, Integer> countMap(Iterable<T> iter) {
        return countMap(null == iter ? null : iter.iterator());
    }

    /**
     * 根据集合返回一个元素计数的 {@link Map}<br>
     * 所谓元素计数就是假如这个集合中某个元素出现了n次，那将这个元素做为key，n做为value<br>
     * 例如：[a,b,c,c,c] 得到：<br>
     * a: 1<br>
     * b: 1<br>
     * c: 3<br>
     *
     * @param <T> 集合元素类型
     * @param iter {@link Iterator}，如果为null返回一个空的Map
     * @return {@link Map}
     */
    public static <T> Map<T, Integer> countMap(Iterator<T> iter) {
        final HashMap<T, Integer> countMap = new HashMap<>(4);
        if (null != iter) {
            Integer count;
            T t;
            while (iter.hasNext()) {
                t = iter.next();
                count = countMap.get(t);
                if (null == count) {
                    countMap.put(t, 1);
                } else {
                    countMap.put(t, count + 1);
                }
            }
        }
        return countMap;
    }

    /**
     * 将键列表和值列表转换为Map<br>
     * 以键为准，值与键位置需对应。如果键元素数多于值元素，多余部分值用null代替。<br>
     * 如果值多于键，忽略多余的值。
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @param keys 键列表
     * @param values 值列表
     * @return 标题内容Map
     * @since 3.1.0
     */
    public static <K, V> Map<K, V> toMap(Iterable<K> keys, Iterable<V> values) {
        return toMap(null == keys ? null : keys.iterator(), null == values ? null : values.iterator());
    }

    /**
     * 将键列表和值列表转换为Map<br>
     * 以键为准，值与键位置需对应。如果键元素数多于值元素，多余部分值用null代替。<br>
     * 如果值多于键，忽略多余的值。
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @param keys 键列表
     * @param values 值列表
     * @return 标题内容Map
     * @since 3.1.0
     */
    public static <K, V> Map<K, V> toMap(Iterator<K> keys, Iterator<V> values) {
        final Map<K, V> resultMap = new HashMap<>(4);
        if (isNotEmpty(keys)) {
            while (keys.hasNext()) {
                resultMap.put(keys.next(), (null != values && values.hasNext()) ? values.next() : null);
            }
        }
        return resultMap;
    }

    /**
     * Iterator转List<br>
     * 不判断，直接生成新的List
     *
     * @param <E> 元素类型
     * @param iter {@link Iterator}
     * @return List
     * @since 4.0.6
     */
    public static <E> List<E> toList(Iterable<E> iter) {
        return toList(iter.iterator());
    }

    /**
     * Iterator转List<br>
     * 不判断，直接生成新的List
     *
     * @param <E> 元素类型
     * @param iter {@link Iterator}
     * @return List
     * @since 4.0.6
     */
    public static <E> List<E> toList(Iterator<E> iter) {
        final List<E> list = new ArrayList<>();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }


    /**
     * {@link Iterator} 转为 {@link Iterable}
     *
     * @param <E> 元素类型
     * @param iter {@link Iterator}
     * @return {@link Iterable}
     */
    public static <E> Iterable<E> asIterable(final Iterator<E> iter) {
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return iter;
            }
        };
    }

    /**
     * 获取集合的第一个元素
     *
     * @param <T> 集合元素类型
     * @param iterable {@link Iterable}
     * @return 第一个元素
     */
    public static <T> T getFirst(Iterable<T> iterable) {
        if (null != iterable) {
            return getFirst(iterable.iterator());
        }
        return null;
    }

    /**
     * 获取集合的第一个元素
     *
     * @param <T> 集合元素类型
     * @param iterator {@link Iterator}
     * @return 第一个元素
     */
    public static <T> T getFirst(Iterator<T> iterator) {
        if (null != iterator && iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
