package com.shiger.fanshe;

import com.shiger.utils.Utils;

import java.lang.reflect.Field;

/**
 * created by shiger on 8/9/18.
 */

/*
 * 获取成员变量并调用：
 *
 * 1.批量的
 * 		1).Field[] getFields():获取所有的"公有字段"
 * 		2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
 * 2.获取单个的：
 * 		1).public Field getField(String fieldName):获取某个"公有的"字段；
 * 		2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
 *
 * 	 设置字段的值：
 * 		Field --> public void set(Object obj,Object value):
 * 					参数说明：
 * 					1.obj:要设置的字段所在的对象；
 * 					2.value:要为字段设置的值；
 *
 */
public class Fields {
    private static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    public static void main(String[] args) throws Exception {
        //1.获取Class对象
        Class stuClass = Class.forName("com.shiger.fanshe.Student");
        //2.获取字段
        Utils.logD(TAG,"************获取所有公有的字段********************");
        Field[] fieldArray = stuClass.getFields();
        for(Field f : fieldArray){
            Utils.logD(TAG,f);
        }
        Utils.logD(TAG,"************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass.getDeclaredFields();
        for(Field f : fieldArray){
            Utils.logD(TAG,f);
        }
        Utils.logD(TAG,"*************获取公有字段**并调用***********************************");
        Field f = stuClass.getField("name");
        Utils.logD(TAG,f);
        //获取一个对象
        Object obj = stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student)obj;
        Utils.logD(TAG,"验证姓名：" + stu.name);


        Utils.logD(TAG,"**************获取私有字段****并调用********************************");
        f = stuClass.getDeclaredField("phoneNum");
        Utils.logD(TAG,f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        Utils.logD(TAG,"验证电话：" + stu);

    }
}
