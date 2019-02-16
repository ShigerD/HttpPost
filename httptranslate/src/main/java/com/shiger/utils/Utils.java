package com.shiger.utils;

import java.text.SimpleDateFormat;

/**
 * created by shiger on 8/20/18.
 */

public class Utils {
/*

    public static void logD(TAG,Object s) {
        System.out.println(getCurTime() + s);
    }

    public static void logD(TAG,long s) {
        System.out.println(getCurTime() + s);
    }


    public static void logD(TAG,int s) {
        System.out.println(getCurTime() + s);
    }

    public static void logD(TAG,String s) {
        System.out.println(getCurTime() + s.toString());
    }
*/
    public static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    public static void logD(String TAG, String s) {
        System.out.println(getCurTime() + TAG + ": " + s);
    }

    public static void logD(String TAG, Object s) {
        System.out.println(getCurTime() + TAG + ": " + s);
    }

    public static String getCurTime() {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:sss  ");
        return df.format(System.currentTimeMillis());
    }
}
