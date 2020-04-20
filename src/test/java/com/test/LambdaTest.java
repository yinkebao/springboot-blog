package com.test;

import com.lambda.LambdaInterface1;
import com.lambda.LambdaInterface2;
import com.lambda.LambdaInterface3;
import com.lambda.LambdaInterface6;

/**
 * @ClassName LambdaTest
 * @Description
 * @Author lsx
 * @Date 2019/11/3
 **/
public class LambdaTest {
    public static void main(String[] args) {
//        test1();
    }

    /**
     * lambda语法精简
     */
    public static void test1(){
        LambdaInterface1 lambda1 = () -> {
            System.out.println("hello word!");
        };
        lambda1.test();

        LambdaInterface2 lambdaInterface2 = a -> System.out.println(a);
        lambdaInterface2.test(10);

        LambdaInterface3 lambdaInterface3 = (a,b) ->{
            System.out.println(a+b);
        };
        lambdaInterface3.test(10,11);

        LambdaInterface6 lambdaInterface6 = (a, b) -> a*b;
        lambdaInterface6.test(1,2);
    }

    /**
     * 方法引用
     * 可以引用其他方法来实现接口（不需要该方法的类实现接口）
     *
     * 注意：
     * 1. 参数列表和返回值一定要和接口中的抽象方法一致
     */
    public static void test2(){
        LambdaInterface6 lambdaInterface6 = LambdaTest::test3;

        LambdaInterface6 lambdaInterface = (a,b) -> test3(a,b);
    }

    /**
     * 待引用的方法
     */
    private static int test3(int a,int b){
        return a*2+b;
    }

    /**
     * 待引用的方法
     */
    private static void functionInterface(){
        // Predicate<T>  :       参数T 返回boolean
        //      IntPredicate    int -> boolean
        //      LongPredicate   Long -> boolean
        //      Double          double -> boolean

        // Consumer<T>   :       参数T 返回void
        //

        // Function<T,R>    :   参数T 返回R

        // Supplier<T>      :   参数无 返回T
    }

}
