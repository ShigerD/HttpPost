package com.shiger.genericTest;

import com.shiger.utils.Utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * created by shiger on 2/16/19.
 */

public class GenericTest <T> {

    T mName;


    public T getName() {
        return mName;
    }

    public GenericTest setName(T name) {
        this.mName = name;
        return this;
    }



    public static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    public static <E,T> void printGeneric (E[] arr){

        for (int i = 0; i < arr.length ; i++) {
            Utils.logD(TAG, " arr[i] > " + arr[i]);
        }
    }

    public static <E,T> void printGeneric2 (List arr){

        arr.getClass();
        for (int i = 0; i < arr.size() ; i++) {
            Utils.logD(TAG,     arr.get(i).getClass() + "  arr[i] > " + arr.get(i));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        String getStackTrace = Thread.currentThread().getStackTrace()[1].getFileName();
        Utils.logD(TAG, getStackTrace);

        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
        printGeneric(intArray);
        printGeneric(charArray);

        Utils.logD(TAG, " compareGetMax > " + compareGetMax(1,2,3));
        Utils.logD(TAG, " compareGetMax > " + compareGetMax("a","b","c"));

        GenericTest<String> genericTest = new GenericTest<>();
        genericTest.setName("a");

        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Double> number = new ArrayList<>();
        name.add("icon");
        age.add(18);
        number.add(314.0);

        printGeneric2(name);
        printGeneric2(age);
        printGeneric2(number);
        getFeild();
    }


    public static <N extends Comparable<N> > N compareGetMax(N a, N b , N c){
        if(a.compareTo(b)>0){
            return a;
        }else if(b.compareTo(c)>0){
            return b;
        }else {
            return c;
        }
    }

    static void getFeild(){
        GenericTest f = new GenericTest();
// 获取f对象对应类中的所有属性域
        Field[] fields = f.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
// 对于每个属性，获取属性名
            String varName = fields[i].getName();
            System.out.println("变量名称为（首字母大写）："+(varName));
        }

    }


}
