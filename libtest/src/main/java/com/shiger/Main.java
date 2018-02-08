package com.shiger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class Main {

    static String appkey = "svm_rest";
    static String appsecret = "9e11ab2d-2740-455d-9338-29feb1e87ec4";

    //target url
    static String urlStr_1 = "http://139.224.37.24:8102/evcard-svm/api/activityList";
    static String urlStr_2 = "http://139.224.37.24:8102/evcard-svm/api/selfTesting";
    static String urlStr_3 = "http://139.224.37.24:8102/evcard-svm/api/authentication";
    static String urlStr_4 = "http://139.224.37.24:8102/evcard-svm/api/alarmInfo";
    static String urlStr_5 = "http://139.224.37.24:8102/evcard-svm/api/thresholdInfo";

    //target body
    static String body1 = "{\"terminalId\":\"123456\"}";
    static String body2 = "{\"terminalId\":\"123456\"," +
            "\"eventType\":\"1\"," +
            "\"value\":\"123\"}";

    static String body3 = "{\n" +
            "\t\"authId\":\"123456\",\n" +
            "\t\"result\":\"结果\",\n" +
            "\t\"picUrl\":\"***.jpg\"\n" +
            "}";

    static String body4 = "{\n" +
            "\t\"authId\":\"123456\",\n" +
            "\t\"type\":\"烟雾\",\n" +
            "\t\"value\":\"12.1\",\n" +
            "\t\"picUrl\":\"***.jpg\"\n" +
            "}";

    static String body5 = "{\n" +
            "\t\"terminalId\":\"123456\"\n" +
            "}";

    static String urlStr_6 = "http://139.224.37.24:8102/evcard-svm/api/membershipInfo";
    static String body6 = "{\n" +
            "\t\"terminalId\":\"123456\"\n" +
            "}";

    static String urlStr_7 = "http://139.224.37.24:8102/evcard-svm/api/orderInfo";
    static String body7 = "{\n" +
            "\t\"authId\":\"123456\"\n" +
            "}";

    static String urlStr_8 = "http://139.224.37.24:8102/evcard-svm/api/firstUsingInfo";
    static String body8 = "{\n" +
            "\t\"authId\":\"123456\"\n" +
            "}";

    static String urlStr_9 = "http://139.224.37.24:8102/evcard-svm/api/getShopBaseInfoListGZIP";
    static String body9 = "{\n" +
            "\t\"authId\":\"123\",\n" +
            "\t\"updatedTime\":\"20180225143453636\"\n" +
            "}";

    static String urlStr_10 = "http://139.224.37.24:8102/evcard-svm/api/getShopRealInfoGzip";
    static String body10 = "{\n" +
            "\t\"authId\":\"123456\",\n" +
            "\t\"shopSeq\":\"156\"\n" +
            "}";

    static String urlStr_11 = "http://139.224.37.24:8102/evcard-svm/api/queryVehicleNo";
    static String body11 = "{\n" +
            "\t\"terminalId\":\"123456\"\n" +
            "}";

    static String[] urlStrArr = {urlStr_1, urlStr_2, urlStr_3, urlStr_4, urlStr_5, urlStr_6, urlStr_7, urlStr_8, urlStr_9, urlStr_10, urlStr_11};
    static String[] bodyStrArr = {body1, body2, body3, body4, body5, body6, body7, body8, body9, body10, body11};

    private static String bytesToHex(byte[] bytes) {
        System.out.println("bytes.length - " + bytes.length);
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


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("~welcome!~");
/*

        LoginCSDN loginCSDN = new LoginCSDN();
        List<NameValuePair> nameValuePairs = loginCSDN.getNVPS();
        boolean isSus = loginCSDN.isLoginSus(nameValuePairs);
        System.out.println("isSus = " + isSus);
*/

/*
        参数名 	类型 	必须 	默认值 	示例 	说明
        appkey 	String 	Y 			由Evcard平台分配给第三方平台的识别号
        timestamp 	String 	Y 			API调用者传递时间戳，为时间转换为毫秒的值，也就是从1970年1月1日起至今的时间转换为毫秒，默认15分钟内有效
        random 	String 	Y 		随机串6位
        sign 	String 	Y 			(appkey+ appsecret+timestamp.toString()+random).ToMD5() (32位，小写)
        Content-Type 	String 	Y 		application/json
                */

        String timestamp = Calendar.getInstance().getTimeInMillis() + "";
        String random = "123456";
        String sign = null;
        try {
            sign = md5Encode(appkey + appsecret + timestamp + random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String type = "application/json";

        System.out.println("appkey - " + appkey);
        System.out.println("timestamp - " + timestamp);
        System.out.println("random - " + random);
        System.out.println("sign - " + sign);
        System.out.println("Content-Type  - " + type);

        List<Header> headersPair = new ArrayList<Header>();
        headersPair.add(new BasicHeader("appkey", appkey));
        headersPair.add(new BasicHeader("timestamp", timestamp));
        headersPair.add(new BasicHeader("random", random));
        headersPair.add(new BasicHeader("sign", sign));
        headersPair.add(new BasicHeader("Content-Type", type));

        //http://139.224.37.24:8102/evcard-svm/api/activityList?terminalId=123456
//        String html = HttpUtils.sendHttp("http://139.224.37.24:8102/evcard-svm/api/activityList", true);

//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("{\"terminalId\":\"123456\"}",null));

//        String result = HttpUtils.sendPost(html, nvps, headersPair);
//        System.out.println(result);

//        HttpUtils.evTest(urlStr);

//        HttpUtils.sendEvcardPost(urlStr_1,"{\"terminalId\":\"123456\"}", headersPair);

   /*     HttpUtils.sendEvcardPost(urlStr_1, body1, headersPair);
        HttpUtils.sendEvcardPost(urlStr_2, body2, headersPair);
        HttpUtils.sendEvcardPost(urlStr_3, body3, headersPair);
        HttpUtils.sendEvcardPost(urlStr_4, body4, headersPair);
        HttpUtils.sendEvcardPost(urlStr_9, body9, headersPair);*/

        for (int i = 0; i < urlStrArr.length; i++) {
            System.out.println("\r\n" + "-------- 1." + String.valueOf(i + 1) + "-----------");

            HttpUtils.sendEvcardPost(urlStrArr[i], bodyStrArr[i], headersPair);
        }
/*

        String data ="H4sIAAAAAAAAAG1Tz08TQRT+X/ZsZLelJXJriCbECoSWcDAepjOP7djZmXV2FqnGRL0AhouJGjQmxEQ4asQYCMH0n3FrOfEvOLMzu90Sb/N97+d8772Hzz2EMcQKcdylEXiLPGXslocIkZAk3qL35/zN+NdZdv46O9zPvn3KDi6udt+OTz+OfxxNjkeTs++NRuP6cm8yysnTn5q5vtz3dIoQOB62IpFy5S36BbFMdFLfNw4S0JIguqZXD/wgmNdcD1GStpGiKjWGelCbb/r+ncIgeOgsQS2o1xcW5pvaRExOXaAHzDjwsAsosgxLQQmh+vcYCnMG95EModKVJdZBpZJbr6AgO1oVgiQxMniGBDxwDRuo21dArGoVYiMBaQkCDBSUpWFHSbQqw2mKLYQh1yN/77gHlYla5YxyqE5kS8i1tMcoznOFEhVJKE9iwIoK3oZtYLmZJnc56jFwYB0SJSlWDnb6Qqp14AVOsJ5+/uOcYDPyB82aFoTpZWgxJKOVNOqZ//kVzrZpmNn51JoLTR0bCQk3Y0uujOVom4Zav1a5efpvQoZuX/yuhjGSg8roDNQ5y/cmGrpNMEjvQVvgQaVoTPFgG/qVDBJCrRslDkQ6zBaWWh09/gHMODuuqCnzpZnNmPRFvMREks9uRcgIMZtxxrAJMGDDqeE+5aQMb4tQbMhK2APE9e3IYlMMtYLyrdOH18peHrUmo8Pry4Ps64fJ75O/J69yuK8vdPzl+Or97vjdKLv47CJXY+D/663gb7a2RrFtZi6NmUBkTpNPl/mWWI7CuQDVazVc828/jsPCX4oYpBrmd2SIDjzR70bTIYVUmpR/7QwTBVF3GMO0okU22u5pqVvpZPnytEqm+IVzpNH02BKFcLkuiZmjLaSRAidEGpPZm3bE9Kaf0dg8Xjz6B6zvQDY6BQAA";
        byte[] bytes = data.getBytes();
        byte[] resultByte = GzipUtil.unCompress(bytes);
        String resultStr = new String(resultByte);
        System.out.println("resultStr - " + resultStr);
*/

        String strInput = "abcdefg123";
        byte[] gzipInput = GzipUtil.compress(strInput.getBytes("UTF-8"));
        System.out.println("gzipInput - " + Arrays.toString(gzipInput));
        String gzipStr =  new String(gzipInput);
        System.out.println("gzipStr - " + gzipStr);

        byte[] gzipStrBytes = gzipStr.getBytes();
        System.out.println("gzipStrBytes - " + Arrays.toString(gzipStrBytes));
        byte[] resultByte = GzipUtil.unCompress(gzipStrBytes);
        String resultStr = new String(resultByte);
        System.out.println("resultStr - " + resultStr);


/*
        "terminalId":"123456",
                "eventType":"1",
                "value":"123"
                */
  /*      String html = HttpUtils.sendHttp("http://139.224.37.24:8102/evcard-svm/api/selfTesting", true);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("terminalId", "123456"));
        nvps.add(new BasicNameValuePair("eventType", "1"));
        nvps.add(new BasicNameValuePair("value", "123"));
        String result = HttpUtils.sendPost(html, nvps);
        System.out.println(result);
*/
    }

    /**
     * MD5加密 生成32位md5码（小写）
     *
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
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


}
