package com.test;

import java.util.*;

/**
 * @ClassName LambdaList
 * @Description 使用lambda进行集合排序
 * @Author lsx
 * @Date 2019/11/3
 **/
public class LambdaList {
    public static void main(String[] args) {
//        testTreeSet();
//        testForEach();
        testDel();
    }

    /** list排序 */
    public static void testList(){
        //需求：已知在一个ArrayList中由若干个Employee对象，根据id降序排序
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10,"红红"));
        employees.add(new Employee(20,"小明"));
        employees.add(new Employee(7,"大话"));
        employees.add(new Employee(11,"规范"));
        employees.add(new Employee(11,"明月"));
        employees.add(new Employee(17,"小平"));
        //顺序排序
        //employees.sort(Comparator.comparingInt(Employee::getId));
        //倒序排序
        employees.sort((o1, o2) -> o2.getId() - o1.getId());
        System.out.println(employees);
    }

    /**
     *set排序
     */
    public static void testTreeSet(){
        //使用lambda表达式来实现Comparator接口，并实例化一个TreeSet对象
        //set容器会去重 当o2.getId() - o1.getId()=0时则相等，相等就会去重
//        Set<Employee> employees = new TreeSet<>((o1, o2) -> o2.getId() - o1.getId());
        //解决去重
        //自定义逻辑 不要返回0
        Set<Employee> employees = new TreeSet<>((o1, o2) -> o2.getId() >= o1.getId() ? -1 : 1);
        employees.add(new Employee(10,"红红"));
        employees.add(new Employee(20,"小明"));
        employees.add(new Employee(7,"大话"));
        employees.add(new Employee(11,"规范"));
        employees.add(new Employee(17,"小平"));
        employees.add(new Employee(11,"明月"));

        System.out.println(employees);
    }

    /**
     * forEach 输出偶数
     */
    public static void testForEach(){
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7,8,9,0);
//        将集合中的每一个元素都带入到方法accept中
//        list.forEach(System.out::println);
        //自定义条件判断：输出偶数
        list.forEach(e ->{
            if (e%2 == 0){
                System.out.println(e);
            }
        });
    }

    /**
     * 条件删除集合中的值
     */
    public static void testDel(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(10,"红红"));
        employees.add(new Employee(20,"小明"));
        employees.add(new Employee(7,"大话"));
        employees.add(new Employee(11,"规范"));
        employees.add(new Employee(11,"明月"));
        employees.add(new Employee(17,"小平"));
        //删除id>10的元素
        //实现Predicate接口中的test方法，条件为true时，删除元素
        //顺序排序
        //employees.sort(Comparator.comparingInt(Employee::getId));
        //倒序排序
        employees.removeIf(e -> e.getId()>11);
        System.out.println(employees);
    }
}

