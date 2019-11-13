package com.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName JavaTest
 * @Description
 * @Author ykb
 * @Date 2019/11/1
 **/
public class JavaTest implements Serializable {


    public static void main(String[] args) {
//        sortEmp();
        sortArr();
    }

    /**
     * @Description: 对象数组根据id排序
     */
    public static void sortEmp(){
        Employee[] emps = new Employee[3];
        emps[0] = new Employee(12,"唐三");
        emps[1] = new Employee(5,"马红俊");
        emps[2] = new Employee(8,"沐白");
        // 1. 需要实现Comparator接口 并实现compareTo()方法
        /*Arrays.sort(emps);
        for (Employee emp : emps){
            System.out.println(emp.toString());
        }*/
        // 2. lambda不用实现Comparator接口
        Arrays.sort(emps, (o1,o2) -> o2.getId() - o1.getId());
        for (Employee employee : emps){
            System.out.println(employee);
        }
    }

    /**
     * @Description: 整型数组排序
     */
    public static void sortArr(){
        Integer[] arrs = {12,3,5,14,8,21};
        Arrays.sort(arrs);
        for (Integer arr: arrs){
            System.out.println(arr);
        }
    }

    public static void serializableTest(){}
}
