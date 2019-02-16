package com.shiger.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by shiger on 2018/7/15.
 */

public class EncodeUtils {
    private static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    /**
     * MD5加密 生成32位md5码（小写）
     *
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws UnsupportedEncodingException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            Utils.logD(TAG,e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    //md5
    private static String MD5Str(String data) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = data.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);
            md5 = bytesToHex(md5Byte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }


    private static String bytesToHex(byte[] bytes) {
        Utils.logD(TAG,"bytes.length - " + bytes.length);
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toLowerCase();
    }
}
