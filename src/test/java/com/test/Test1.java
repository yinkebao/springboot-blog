package com.test;

import jdk.nashorn.internal.objects.annotations.Where;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        srotNum();
    }

    public static void srotNum(){
        Scanner sc = new Scanner(System.in);
        List<Integer> nums = new ArrayList<>();
        int i=0,n = 1;
        while (sc.hasNext()&&i!=n){
            n = sc.nextInt();
            Integer[] ints = new Integer[5];
            for(i =0;i<n;i++){
                ints[i] = sc.nextInt();
                if (ints[i] != 0){
                    nums.add(ints[i]);
                }
            }
            if (i == n){
                break;
            }
        }
        Collections.sort(nums);
        nums.forEach(System.out::print);
    }
}
