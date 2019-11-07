package com.test;

/**
 * @ClassName Employee
 * @Description
 * @Author ykb
 * @Date 2019/11/1
 **/
public class Employee {

    private Integer id;
    private String name;

    public Employee(Integer id, String name){
        this.id = id;
        this.name = name;
        System.out.println("Employee的有参构造方法");
    }

    public Employee(){
        System.out.println("Employee的无参构造方法");
    }

    /**
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
//    @Override
//    public int compareTo(Employee o) {
//        return Integer.compare(id,o.id);
//    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
