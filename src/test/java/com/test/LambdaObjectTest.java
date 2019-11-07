package com.test;

/**
 * @ClassName LambdaObjectTest
 * @Description
 * @Author ykb
 * @Date 2019/11/3
 **/
public class LambdaObjectTest {
    public static void main(String[] args) {
        /**
         * 1. 初始化无参构造器
         */
        EmpCreate empCreat0 = () -> new Employee();
        empCreat0.getEmp();

        EmpCreate empCreate =  Employee::new ;
        empCreate.getEmp();

        //2. 初始化有参构造器
        EmpCreate1 empCreate1 = Employee::new;
        System.out.println(empCreate1.getEmp(10,"xiaohua").toString());


    }
}

interface EmpCreate{
    Employee getEmp();
}

interface EmpCreate1{
    Employee getEmp(Integer id, String name);
}