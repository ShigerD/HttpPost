package com.shiger;

import com.shiger.utils.EncodeUtils;
import com.shiger.utils.GzipUtil;
import com.shiger.utils.HttpUtils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class EvcardApiTest {

//    private static final String TERMINAL_ID = "EB1805310002";
    private static final String TERMINAL_ID = "EB1805310004";


//    private static final String TERMINAL_ID = "CB1805080002";
//    private static final String TERMINAL_ID = "CB1805100001";
//    private static final String TERMINAL_ID = "AA1804090004";
//    private static final String TERMINAL_ID = "AA1804090003";
//    private static final String TERMINAL_ID = "AA1804090002";

    //18321277721091339144
    //    private static final String AUTH_ID = "18321277721091339144";
    //18019252932135335553
    private static final String AUTH_ID = "83839";

    static String appkey = "svm_rest";
    static String appsecret = "9e11ab2d-2740-455d-9338-29feb1e87ec4";


    //target url
    static String qureSipNumUrl = "http://139.224.37.24:8102/evcard-svm/api/querySipNo/" + TERMINAL_ID;

    static String urlStr_1 = "http://139.224.37.24:8102/evcard-svm/api/activityList";
    static String urlStr_2 = "http://139.224.37.24:8102/evcard-svm/api/selfTesting";
    static String urlStr_3 = "http://139.224.37.24:8102/evcard-svm/api/authentication";
    static String urlStr_4 = "http://139.224.37.24:8102/evcard-svm/api/alarmInfo";
    static String urlStr_5 = "http://139.224.37.24:8102/evcard-svm/api/thresholdInfo";

    //target body
    static String body1 = "{\"terminalId\":\"" + TERMINAL_ID + "\"}";
    static String body2 = "{\"terminalId\":\"" + TERMINAL_ID + "\"," +
            "\"eventType\":\"1\"," +
            "\"value\":\"123\"}";

    static String body3 = "{\n" +
            "\t\"authId\":\"" + AUTH_ID + "\",\n" +
            "\t\"result\":\"结果\",\n" +
            "\t\"picUrl\":\"***.jpg\"\n" +
            "}";

    static String body4 = "{\n" +
            "\t\"authId\":\"" + AUTH_ID + "\",\n" +
            "\t\"type\":\"烟雾\",\n" +
            "\t\"value\":\"12.1\",\n" +
            "\t\"picUrl\":\"***.jpg\"\n" +
            "}";

    static String body5 = "{\n" +
            "\t\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    static String urlStr_6 = "http://139.224.37.24:8102/evcard-svm/api/membershipInfo";
    static String body6 = "{\n" +
            "\t\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    static String urlStr_7 = "http://139.224.37.24:8102/evcard-svm/api/orderInfo";
    static String body7 = "{\n" +
            "\t\"authId\":\"" + AUTH_ID + "\"\n" +
            "}";

    static String urlStr_8 = "http://139.224.37.24:8102/evcard-svm/api/firstUsingInfo";
    static String body8 = "{\n" +
            "\t\"authId\":\"" + AUTH_ID + "\"\n" +
            "}";

    static String urlStr_9 = "http://139.224.37.24:8102/evcard-svm/api/getShopBaseInfoListGZIP";
    static String body9 = "{\n" +
            "\t\"authId\":\"123\",\n" +
            "\t\"updatedTime\":\"20180225143453636\"\n" +
            "}";

    static String urlStr_10 = "http://139.224.37.24:8102/evcard-svm/api/getShopRealInfoGzip";
    static String body10 = "{\n" +
            "\t\"authId\":\"" + AUTH_ID + "\",\n" +
            "\t\"shopSeq\":\"156\"\n" +
            "}";

    static String urlStr_11 = "http://139.224.37.24:8102/evcard-svm/api/queryVehicleNo";
    static String body11 = "{\n" +
            "\t\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    //userRating
    static String urlStr_12 = "http://139.224.37.24:8102/evcard-svm/api/userRating";
    static String body12 = "{" +
            " \"violentDrivingTimes\":\"1\",\n" +
            " \"smokingTimes\":\"2\",\n" +
            " \"overspeedTimes\":\"3\",\n" +
            " \"c\":\"50\",\n" +
            " \"d\":\"10\"\n" +
            "}";

    //http://139.224.37.24:8102/evcard-svm/api/uploadImage

    //userRating
    static String urlStr_13 = "http://139.224.37.24:8102/evcard-svm/api/uploadImage";
    static String body13 = "{" +
            "\"authId\":\"" + AUTH_ID + "\",\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\",\n" +
            "\"urls\":\"1,2,3\"\n"
            + "}";


    static String[] urlStrArr = {urlStr_1, urlStr_2, urlStr_3, urlStr_4, urlStr_5, urlStr_6, urlStr_7,
            urlStr_8, urlStr_9, urlStr_10, urlStr_11, urlStr_12, urlStr_13};

    static String[] bodyStrArr = {body1, body2, body3, body4, body5, body6, body7, body8, body9, body10,
            body11, body12, body13};



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
            sign = EncodeUtils.md5Encode(appkey + appsecret + timestamp + random);
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

        HttpUtils.sendEvcardGet(qureSipNumUrl,headersPair);

        String data = "H4sIAAAAAAAAAG1Tz08TQRT+X/ZsZLelJXJriCbECoSWcDAepjOP7djZmXV2FqnGRL0AhouJGjQmxEQ4asQYCMH0n3FrOfEvOLMzu90Sb/N97+d8772Hzz2EMcQKcdylEXiLPGXslocIkZAk3qL35/zN+NdZdv46O9zPvn3KDi6udt+OTz+OfxxNjkeTs++NRuP6cm8yysnTn5q5vtz3dIoQOB62IpFy5S36BbFMdFLfNw4S0JIguqZXD/wgmNdcD1GStpGiKjWGelCbb/r+ncIgeOgsQS2o1xcW5pvaRExOXaAHzDjwsAsosgxLQQmh+vcYCnMG95EModKVJdZBpZJbr6AgO1oVgiQxMniGBDxwDRuo21dArGoVYiMBaQkCDBSUpWFHSbQqw2mKLYQh1yN/77gHlYla5YxyqE5kS8i1tMcoznOFEhVJKE9iwIoK3oZtYLmZJnc56jFwYB0SJSlWDnb6Qqp14AVOsJ5+/uOcYDPyB82aFoTpZWgxJKOVNOqZ//kVzrZpmNn51JoLTR0bCQk3Y0uujOVom4Zav1a5efpvQoZuX/yuhjGSg8roDNQ5y/cmGrpNMEjvQVvgQaVoTPFgG/qVDBJCrRslDkQ6zBaWWh09/gHMODuuqCnzpZnNmPRFvMREks9uRcgIMZtxxrAJMGDDqeE+5aQMb4tQbMhK2APE9e3IYlMMtYLyrdOH18peHrUmo8Pry4Ps64fJ75O/J69yuK8vdPzl+Or97vjdKLv47CJXY+D/663gb7a2RrFtZi6NmUBkTpNPl/mWWI7CuQDVazVc828/jsPCX4oYpBrmd2SIDjzR70bTIYVUmpR/7QwTBVF3GMO0okU22u5pqVvpZPnytEqm+IVzpNH02BKFcLkuiZmjLaSRAidEGpPZm3bE9Kaf0dg8Xjz6B6zvQDY6BQAA";
        //先base64解码, 再base64解压缩
        byte[] bytes = data.getBytes();
        byte[] decoded = null;
        try {
            decoded = Base64.decode(bytes);
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        byte[] resultByte = GzipUtil.unCompress(decoded);
        String resultStr = new String(resultByte);
        System.out.println("base64- gzip - decode - resultStr - " + resultStr);


        test();
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

    private static void test() {

        int len = 256;
        int i = 10;
        System.out.println("10 >> 1==" + (i >> 1));
        System.out.println("+ 10 >> 1==" + (+(i >> 1)));
        System.out.println("256 + +10 >> 1==" + (len + +(i >> 1)));


    }




}
