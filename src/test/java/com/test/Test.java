package com.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        List<String> strs = new ArrayList<>();
        Scanner scanner1 = new Scanner(System.in);
        int t = scanner1.nextInt();
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()&&i!=t){
            String str;
            for (i=0;i<t;i++){
                str = scanner.nextLine();
                strs.add(transFStr(str));
            }
            if (i == t){
                break;
            }
        }
        strs.forEach(System.out::println);
    }

    public static String transFStr(String str){
        String[] strings = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        String strUp;
        for (String str1: strings){
            strUp = str1.toUpperCase();
            strUp = strUp.substring(0,1);
            stringBuilder.append(strUp);
        }
        return stringBuilder.toString();
    }
}
