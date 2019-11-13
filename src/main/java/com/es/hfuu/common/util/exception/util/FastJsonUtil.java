package com.es.hfuu.common.util.exception.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FastJsonUtil
 * @Description 阿里fastjson的json序列化和反序列化的工具类
 * @Author ykb
 * @Date 2019/11/8
 */
public class FastJsonUtil {
    /**
     * 序列化特性
     */
    private static SerializerFeature[] serializerfeatures = new SerializerFeature[] {
            //SerializerFeature.IgnoreNonFieldGetter,
            SerializerFeature.IgnoreErrorGetter,
            //输出key时是否使用双引号,默认为true
            //SerializerFeature.QuoteFieldNames,
            //使用单引号而不是双引号,默认为false
            //SerializerFeature.UseSingleQuotes,
            // 是否输出值为null的字段,默认为false
            SerializerFeature.WriteMapNullValue,
            //Enum输出name()或者original,默认为false
            //SerializerFeature.WriteEnumUsingToString,
            //Date使用ISO8601格式输出，默认为false
            // SerializerFeature.UseISO8601DateFormat,
            //List字段如果为null,输出为[],而非null
            SerializerFeature.WriteNullListAsEmpty,
            //字符类型字段如果为null,输出为"",而非null
            SerializerFeature.WriteNullStringAsEmpty,
            //数值字段如果为null,输出为0,而非null
            SerializerFeature.WriteNullNumberAsZero,
            //Boolean字段如果为null,输出为false,而非null
            SerializerFeature.WriteNullBooleanAsFalse,
            //SerializerFeature.SkipTransientField,				//如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
            //SerializerFeature.SortField,						//按字段名称排序后输出。默认为false
            //SerializerFeature.WriteTabAsSpecial,				//把\t做转义输出，默认为false
            //SerializerFeature.PrettyFormat,						//结果是否格式化,默认为false
            //序列化时写入类型信息，默认为false。反序列化是需用到
            //SerializerFeature.WriteClassName,
            //SerializerFeature.DisableCircularReferenceDetect,	//消除对同一对象循环引用的问题，默认为false
            //SerializerFeature.WriteSlashAsSpecial,				//对斜杠’/’进行转义
            //SerializerFeature.BrowserCompatible,				//将中文都会序列化为\\uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
            //SerializerFeature.DisableCheckSpecialChar,			//一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
            //SerializerFeature.BeanToArray,						//将对象转为array输出
            //全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 反序列化特性
     */
    private static Feature[] features = new Feature[] {
            Feature.DisableFieldSmartMatch,
            Feature.InternFieldNames
    };


    /**
     * @Title: toJsonStr
     * @Description: fastJson将对象转成字符串
     * @param obj
     * @return String
     */
    public static String toJsonStr(Object obj){
        return JSONObject.toJSONString(obj,serializerfeatures);
        //return JSONObject.toJSONString(obj);
    }

    /**
     * @Title: parseObject
     * @Description: 将对象字符串转成Object对象
     * @param objStr 对象字符串
     * @return Object
     */
    public static Object parseObject(String objStr){
        return JSONObject.parseObject(objStr);
    }

    /**
     * @Title: parseObject
     * @Description: 根据对象字符串和Class生成对应Class的类。
     * @param objStr 对象字符串
     * @param clazz 类型的Class
     * @return T 返回的实体类
     */
    public static <T> T parseObject(String objStr,Class<T> clazz){
        return JSONObject.parseObject(objStr,clazz,features);
    }

    /**
     * @Title: parseObject
     * @Description: 根据对象的byte数组和Class生成对应的Class类
     * @param bytes 对象的byte数组
     * @param clazz 类型的Class
     * @return T 返回的实体类
     */
    public static <T> T parseObject(byte[] bytes,Class<T> clazz){
        return JSONObject.parseObject(bytes,clazz);
    }

    /**
     * @Title: parseJsonObject
     * @Description: 根据对象字符串生成对应的JSONObject对象
     * @param objStr 对象字符串
     * @return JSONObject
     */
    public static JSONObject parseJsonObject(String objStr){
        return JSONObject.parseObject(objStr);
    }

    /**
     * @Title: parseJsonArray
     * @Description: 根据对象字符串生成对应的JSONArray对象
     * @param objStr 对象字符串
     * @return JSONArray
     */
    public static JSONArray parseJsonArray(String objStr){
        return JSONObject.parseArray(objStr);
    }

    /**
     * @Title: parseJsonArray
     * @Description: 根据对象字符串和Class生成对应Class的集合。
     * @param objStr 对象字符串
     * @param clazz 类型的Class
     * @return List<T> 返回的实体类集合
     */
    public static <T> List<T> parseJsonArray(String objStr, Class<T> clazz){
        return JSONObject.parseArray(objStr,clazz);
    }

    /**
     * @Title: parseObject
     * @Description: 根据对象字符串和TypeReference<T>生成对应T对象。
     * @param objStr 对象字符串
     * @param type 类型的Class
     * @return T
     */
    public static <T> T parseObject(String objStr, TypeReference<T> type){
        return JSONObject.parseObject(objStr,type);
    }

    public static void main(String[] args) {
        List<Demo> list = new ArrayList<Demo>();
        Demo demo1 = new Demo();
        demo1.setAge(1);
        demo1.setName("demo1");
        demo1.setBirthday(new Date());
        Demo demo2 = new Demo();
        demo2.setAge(1);
        demo2.setName("demo2");
        demo2.setBirthday(new Date());
        Demo demo3 = new Demo();
        demo3.setAge(1);
        demo3.setName("demo3");
        demo3.setBirthday(new Date());

        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        String str1 = toJsonStr(demo1);
        System.out.println(str1);
        Demo demoTmp = parseObject(str1,new TypeReference<Demo>(){});
        System.out.println(demoTmp.getBirthday()+"		"+demoTmp.getAge()+"	"+demoTmp.getName());
        String str = toJsonStr(list);
        System.out.println(str);
        List<Demo> list1 = parseObject(str,new TypeReference<List<Demo>>(){});
        for (Demo demo : list1){
            System.out.println(demo.getBirthday()+"		"+demo.getAge()+"	"+demo.getName());
        }
    }
}

class Demo{
    private int age;
    private String name;
    private Date birthday;

    public void say(){
        System.out.println("!@#!@#");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
