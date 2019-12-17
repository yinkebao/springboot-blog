package com.test;

/**
 * @ClassName StringTest
 * @Description
 * @Author ykb
 * @Date 2019/11/13
 */
public class StringTest {
    public static void main(String[] args) {
        String orgName = "全国->河北省->驻马店市->区层级->街道->社区->网格";
        String[] names = orgName.split("->");
        StringBuffer sd = new StringBuffer();
        if (names.length>3){
            for (int i=names.length-3;i<names.length;i++){
                sd.append(names[i]);
                if (i == names.length-1){
                    break;
                }
                sd.append("->");
            }
            System.out.println(sd.toString());
        }else {
            System.out.println(orgName);
        }
    }
}
