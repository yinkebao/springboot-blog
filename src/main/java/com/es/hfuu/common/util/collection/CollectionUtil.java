package com.es.hfuu.common.util.collection;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @ClassName CollectionUtil
 * @Description 集合工具类
 * @Author ykb
 * @Date 2019/12/5
 */
public class CollectionUtil {

    /**
     * @Title: arrayToList
     * @Description: 将数组转成List
     * @param arr 数组
     * @return List<T>
     */
    public static <T> List<T> arrayToList(T... arr) {
        if (null == arr) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<T>(arr.length);
        for(T t: arr){
            list.add(t);
        }
        return list;
    }

    /**
     * @Title: arrayToList
     * @Description: 将int数组转成List
     * @param arr 数组
     * @return List<T>
     */
    public static List intArrToList(int... arr) {
        if (null == arr) {
            return new ArrayList<>();
        }
        List list = new ArrayList(arr.length);
        for(int t: arr){
            list.add(t);
        }
        return list;
    }

    /**
     * @Title: arrayToList
     * @Description: 将long数组转成List
     * @param arr 数组
     * @return List<T>
     */
    public static List longArrToList(long... arr) {
        if (null == arr) {
            return new ArrayList<>();
        }
        List list = new ArrayList(arr.length);
        for(long t: arr){
            list.add(t);
        }
        return list;
    }

    /**
     * 新建一个ArrayList
     *
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return ArrayList对象
     */
    public static <T> List<T> collectionToList(Collection<T> collection) {
        if (null == collection) {
            return new ArrayList<>();
        }
        return new ArrayList<T>(collection);
    }

    /**
     * 新建一个ArrayList<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T> 集合元素类型
     * @param iterable {@link Iterable}
     * @return ArrayList对象
     * @since 3.1.0
     */
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return (null == iterable) ? new ArrayList<T>() : iteratorToList(iterable.iterator());
    }

    /**
     * 新建一个ArrayList<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T> 集合元素类型
     * @param iter {@link Iterator}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> List<T> iteratorToList(Iterator<T> iter) {
        final List<T> list = new ArrayList<>();
        if (null == iter) {
            return list;
        }
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }

    /**
     * 新建一个ArrayList<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T> 集合元素类型
     * @param enumration {@link Enumeration}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> List<T> enumerationToList(Enumeration<T> enumration) {
        final List<T> list = new ArrayList<>();
        if (null == enumration) {
            return list;
        }
        while (enumration.hasMoreElements()) {
            list.add(enumration.nextElement());
        }
        return list;
    }

    /**
     * {@link Iterable}转为{@link Collection}<br>
     * 首先尝试强转，强转失败则构建一个新的{@link ArrayList}
     *
     * @param <E> 集合元素类型
     * @param iterable {@link Iterable}
     * @return {@link Collection} 或者 {@link ArrayList}
     * @since 3.0.9
     */
    public static <E> Collection<E> iterableToCollection(Iterable<E> iterable) {
        return (iterable instanceof Collection) ? (Collection<E>) iterable : iteratorToList(iterable.iterator());
    }

    /**
     * @Title: removeRepeatData
     * @Description: 去除list中的重复元素。
     * @param list
     * @return
     */
    public static <T> void removeRepeatData(List<T> list){
        if (null == list || list.size() < 1) {
            return;
        }
        Set<T> tSet = new HashSet<>(list);
        list.clear();
        list.addAll(tSet);
        tSet.clear();
    }

    /**
     * 将对象的list转换成id的list
     * @Title: objectListToObjectIdList
     * @Description: 将对象的list转换成id的list
     * @param list
     * @return: List
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> objectListToObjectIdList(List list) {
        try {
            List<T> returnList = new ArrayList<>();
            if(list != null && list.size() > 0){
                Method method = list.get(0).getClass().getMethod("getId");
                for (Object object : list) {
                    returnList.add((T) method.invoke(object));
                }
            }
            return returnList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将集合转为数组
     *
     * @param <T> 数组元素类型
     * @param iterator {@link Iterator}
     * @param componentType 集合元素类型
     * @return 数组
     * @since 3.0.9
     */
    public static <T> T[] iteratorToArray(Iterator<T> iterator, Class<T> componentType) {
        return collectionToArray(CollectionUtil.iteratorToList(iterator), componentType);
    }

    /**
     * 将集合转为数组
     *
     * @param <T> 数组元素类型
     * @param iterable {@link Iterable}
     * @param componentType 集合元素类型
     * @return 数组
     * @since 3.0.9
     */
    public static <T> T[] iterableToArray(Iterable<T> iterable, Class<T> componentType) {
        return collectionToArray(CollectionUtil.iterableToCollection(iterable), componentType);
    }

    /**
     * 将集合转为数组
     *
     * @param <T> 数组元素类型
     * @param collection 集合
     * @param componentType 集合元素类型
     * @return 数组
     * @since 3.0.9
     */
    public static <T> T[] collectionToArray(Collection<T> collection, Class<T> componentType) {
        final T[] array;
        if(null == collection){
            array = (T[]) Array.newInstance(componentType, 0);
        }else{
            array = (T[]) Array.newInstance(componentType, collection.size());
        }
        return collection.toArray(array);
    }

    /**
     * 将集合转为数组
     *
     * @param <T> 数组元素类型
     * @param collection 集合
     * @return 数组
     * @since 3.0.9
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] collectionToArray(Collection<T> collection) {
        if(null == collection){
            return collection.toArray((T[]) new Object[0]);
        }
        return collection.toArray((T[]) new Object[collection.size()]);
        //return arr.toArray(new Long[arr.size()]);
    }

    /**
     * @Title: isEmpty
     * @Description: 判断集合为空
     * @param collection
     * @return boolean
     */
    public static <T> boolean isEmpty(Collection<T> collection){
        return collection == null || collection.size() == 0;
    }

    /**
     * @Title: isNotEmpty
     * @Description: 判断集合不为空
     * @param collection
     * @return boolean
     */
    public static <T> boolean isNotEmpty(Collection<T> collection){
        return collection != null && collection.size() >= 1;
    }

    /**
     * 集合循环赋值
     * @Title: cyclicFillProperties
     * @param rawCollection 原始集合
     * @param consumer 赋值行为
     * @return void
     */
    public static <T> void cyclicFillProperties(Collection<T> rawCollection, Consumer<T> consumer) {
        Optional<Collection<T>> rawCollectionOptional = Optional.ofNullable(rawCollection);
        rawCollectionOptional.ifPresent(collection -> collection.stream().filter(Objects::nonNull).forEach(consumer));
    }

    /** 集合批量执行默认上限 */
    public static final int BATCH_CONSUME_LIMIT = 1;

    /**
     * 集合批量执行消费型函数
     * @Title: batchConsume
     * @param consumer 单参数消费型接口 {@link Consumer}
     * @param collection 要分批处理的数据集合
     * @return void
     */
    public static <T extends Collection> void batchConsume(Consumer<T> consumer, T collection) {
        batchConsume(consumer, collection, BATCH_CONSUME_LIMIT);
    }

    /**
     * 集合批量执行消费型函数
     * @Title: batchConsume
     * @param consumer 单参数消费型接口 {@link Consumer}
     * @param collection 要分批处理的数据集合
     * @param limit 分批量
     * @return void
     */
    @SuppressWarnings("unchecked")
    public static <T extends Collection> void batchConsume(Consumer<T> consumer, T collection, int limit){
        if (isEmpty(collection)) {
            return;
        }
        T temp;
        boolean flag;
        if(collection.size() <= limit){
            temp = collection;
            flag = false;
        }else{
            temp = (T) collection.stream().limit(limit).collect(Collectors.toList());
            flag = true;
        }
        consumer.accept(temp);
        if(flag){
            batchConsume(consumer, (T) collection.stream().skip(limit).collect(Collectors.toList()), limit);
        }
    }

    public static void main(String[] args) {

        int[] intArray = {1,2,3};
        List<int[]> myList = intArrToList(intArray);
        System.out.println(myList.size());


        Integer[] integerArray = new Integer[]{1,2,3};
        List<Integer> integerList = arrayToList(integerArray);
        System.out.println(integerList.size());

        System.out.println(arrayToList(null));

        String[] ss = {"s1","s2","1"};
        List str = Arrays.asList(ss);
        Set s = new HashSet();
        s.add("1");
        s.add(1);
        s.add("2");
        s.add("3");
        s.addAll(str);
        System.out.println(s);
		/*List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		list.add(1L);
		list.add(3L);
		for(Long l : list){
			System.out.println(l);
		}
		CollectionUtil.removeRepeatData(list);
		for(Long l : list){
			System.out.println(l+"		@@@@@@@");
		}*/
    }
}

