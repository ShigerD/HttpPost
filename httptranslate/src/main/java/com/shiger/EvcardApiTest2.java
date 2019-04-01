package com.shiger;

import com.shiger.utils.EncodeUtils;
import com.shiger.utils.GzipUtil;
import com.shiger.utils.HttpUtils;
import com.shiger.utils.TimeUtils;
import com.shiger.utils.Utils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EvcardApiTest2 {
    private static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    //    private static final String TERMINAL_ID = "EB1805310002";
    private static final String TERMINAL_ID = "EB1805310004";
//    private static final String TERMINAL_ID = "EA1807270006";
//    private static final String TERMINAL_ID = "AA1804090003";
//    private static final String TERMINAL_ID = "CB1805080002";
//    private static final String TERMINAL_ID = "CB1805100001";
//    private static final String TERMINAL_ID = "AA1804090004";
//    private static final String TERMINAL_ID = "AA1804090002";

    //18321277721091339144
    //    private static final String AUTH_ID = "18321277721091339144";
    //18019252932135335553
    private static final String AUTH_ID = "310114199111270052";

    static String appkey = "svm_rest";
    static String appsecret = "9e11ab2d-2740-455d-9338-29feb1e87ec4";

//new csms-st.evcard.vip:180

    static final String BaseUrl = "http://csms-st.evcard.vip:180/";
//    static final String BaseUrl = "http://139.224.37.24:8102/";
final static String data_base64_gzip =
        "H4sIAAAAAAAAAG1Tz08TQRT+X" +
                "/ZsZLelJXJriCbECoSWcDAepjOP7djZmXV2FqnGRL0AhouJGjQmxEQ4asQYCMH0n3FrOfEvOLMzu90Sb/N97+d8772Hzz2EMcQKcdylEXiLPGXslocIkZAk3qL35/zN+NdZdv46O9zPvn3KDi6udt+OTz+OfxxNjkeTs++NRuP6cm8yysnTn5q5vtz3dIoQOB62IpFy5S36BbFMdFLfNw4S0JIguqZXD/wgmNdcD1GStpGiKjWGelCbb/r+ncIgeOgsQS2o1xcW5pvaRExOXaAHzDjwsAsosgxLQQmh+vcYCnMG95EModKVJdZBpZJbr6AgO1oVgiQxMniGBDxwDRuo21dArGoVYiMBaQkCDBSUpWFHSbQqw2mKLYQh1yN/77gHlYla5YxyqE5kS8i1tMcoznOFEhVJKE9iwIoK3oZtYLmZJnc56jFwYB0SJSlWDnb6Qqp14AVOsJ5+/uOcYDPyB82aFoTpZWgxJKOVNOqZ//kVzrZpmNn51JoLTR0bCQk3Y0uujOVom4Zav1a5efpvQoZuX/yuhjGSg8roDNQ5y/cmGrpNMEjvQVvgQaVoTPFgG/qVDBJCrRslDkQ6zBaWWh09/gHMODuuqCnzpZnNmPRFvMREks9uRcgIMZtxxrAJMGDDqeE+5aQMb4tQbMhK2APE9e3IYlMMtYLyrdOH18peHrUmo8Pry4Ps64fJ75O/J69yuK8vdPzl+Or97vjdKLv47CJXY+D/663gb7a2RrFtZi6NmUBkTpNPl/mWWI7CuQDVazVc828/jsPCX4oYpBrmd2SIDjzR70bTIYVUmpR/7QwTBVF3GMO0okU22u5pqVvpZPnytEqm+IVzpNH02BKFcLkuiZmjLaSRAidEGpPZm3bE9Kaf0dg8Xjz6B6zvQDY6BQAA";

    //target url
//    static String qureSipNumUrl = BaseUrl + "/evcard-svm/api/querySipNo/" + TERMINAL_ID;
    static String qureSipNumUrl = BaseUrl + "/evcard-svm/api/querySipNo/" + TERMINAL_ID;

    static String urlStr_1 = BaseUrl + "/evcard-svm/api/membershipInfo";
    static String urlStr_2 = BaseUrl + "/evcard-svm/api/selfTesting";
    static String urlStr_3 = BaseUrl + "/evcard-svm/api/authentication";
    static String urlStr_4 = BaseUrl + "/evcard-svm/api/alarmInfo";
    static String urlStr_5 = BaseUrl + "/evcard-svm/api/thresholdInfo";

    //target body
    static String body1 = "{\"terminalId\":\"" + TERMINAL_ID + "\"}";
    static String body2 = "{\"terminalId\":\"" + TERMINAL_ID + "\"," +
            "\"eventType\":\"1\"," +
            "\"value\":\"123\"}";
    /*
        static String body3 = "{\n" +
                "\"authId\":\"" + AUTH_ID + "\",\n" +
                "\"result\":\"结果\",\n" +
                "\"picUrl\":\"***.jpg\"\n" +
                "}";*/
    static String body3 = "{\n" +
            "\"orderSeq\":\"C2019031210160000028\",\n" +
            "\"confidence\":78.25,\n" +
            "\"errorCode\":\"2000\",\n" +
            "\"result\":1,\n" +
            "\"picUrl\":\"https://img2.woyaogexing.com/2019/03/14/34ee764774a3b765!400x400_big" +
            ".jpg\"\n" +
            "}";

    /*
    1.4 上传报警信息
    服务名称：上传报警信息
    服务地址：http://[host]:[port]/[path]/alarmInfo HTTP Method：POST
    请求Body
    Body格式：JSON
    参数名 		    类型 	含义 	说明
    authId 		    String 	会员Id
    terminalId 		String 	终端号
    alarmInfo 		list 	报警信息
	type 	String 	报警类型 	smoking：烟雾，tirePressure1：左前胎压,tirePressure2：右前胎压,tirePressure3：左后胎压,
	tirePressure4：右后胎压，tireTemperature1：左前胎温,tireTemperature2：右前胎温,tireTemperature3：左后胎温,
	tireTemperature4：右后胎温，violentDriving:暴力驾驶， overspeed:超速
	，collision：碰撞，lowVoltage：低电压，sysError：车辆故障报警
	value 	String 	报警值 	暴力驾驶时：1急加速 2急减速 3 急转弯；车辆故障报警：故障码+故障信号
	picUrl 	String 	图片保存路径 	url1,url2,...
Body示例
{
	"authId":"123456",
	"terminalId":"123",
	"alarmInfo":
	[
	    {
            "type":"smoking",
            "value":"12.1",
            "picUrl":"***.jpg"
	    },
	     {
		    "type":"tirePressure1",
		    "value":"80",
		    "picUrl":"***.jpg"

	     }
	]
}
    */
    static String body4 = "{\n" +
            "\"authId\":\"" + AUTH_ID + "\",\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\",\n" +
            "\"alarmInfo\":\n" +
            "[\n" +
            "    {\n" +
            "            \"type\":\"smoking\",\n" +
            "            \"value\":\"12.1\",\n" +
            "            \"picUrl\":\"***.jpg\"\n" +
            "    },\n" +
            "     {\n" +
            "    \"type\":\"tirePressure1\",\n" +
            "    \"value\":\"80\",\n" +
            "    \"picUrl\":\"***.jpg\"\n" +
            "         \n" +
            "     }\n" +
            "]\n" +
            "}";

    static String body5 = "{\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    static String urlStr_6 = BaseUrl + "/evcard-svm/api/activityList";

    static String body6 = "{\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    static String urlStr_7 = BaseUrl + "/evcard-svm/api/orderInfo";
    static String body7 = "{\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    /**
     * {
     * "terminalId":"123",
     * "authId":"123456"
     * }
     */
    static String urlStr_8 = BaseUrl + "/evcard-svm/api/firstUsingInfo";
    static String body8 =
            "{\n" +
                    "\"terminalId\":\"" + TERMINAL_ID  + "\",\n" +
                    "\"authId\":\"" + AUTH_ID + "\"\n" +
                    "}";

    static String urlStr_9 = BaseUrl + "/evcard-svm/api/getShopBaseInfoListGZIP";
    static String body9 = "{\n" +
            "\"authId\":\"" + AUTH_ID + "\",\n" +
            "\"updatedTime\":\"20180225143453636\"\n" +
            "}";

    static String urlStr_10 = BaseUrl + "/evcard-svm/api/getShopRealInfoGzip";
    static String body10 = "{\n" +
            "\"authId\":\"" + AUTH_ID + "\",\n" +
            "\"shopSeq\":\"156\"\n" +
            "}";

    static String urlStr_11 = BaseUrl + "/evcard-svm/api/queryVehicleNo";
    static String body11 = "{\n" +
            "\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    //userRating
    static String urlStr_12 = BaseUrl + "/evcard-svm/api/userRating";
    //http://csms-st.evcard.vip:180/evcard-svm/api/membershipInfo
    static String body12 = "{" +
            "\"terminalId\":\"" + TERMINAL_ID + "\"\n" +
            "}";

    //http://csms-st.evcard.vip:180/evcard-svm/api/uploadImage

    //uploadImage
    /**
     * imgBase64 	String 	base64图片 	先base64编码，然后GZIP压缩，再base64编码
     */
    static String urlStr_13 = BaseUrl + "/evcard-svm/api/uploadImage";
    static String body13 = "{" +
            "\"imgBase64\":\"" + data_base64_gzip  + "\"\n" +
            "}";



    static String[] urlStrArr = {urlStr_1, urlStr_2, urlStr_3, urlStr_4, urlStr_5, urlStr_6,
            urlStr_7,
            urlStr_8, urlStr_9, urlStr_10, urlStr_11, urlStr_12, urlStr_13};

    static String[] bodyStrArr = {body1, body2, body3, body4, body5, body6, body7, body8, body9,
            body10,
            body11, body12, body13};



    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Utils.logD(TAG, "~welcome!~");
/*
        CSDNLoginUtil loginCSDN = new CSDNLoginUtil();
        List<NameValuePair> nameValuePairs = loginCSDN.getNVPS();
        boolean isSus = loginCSDN.isLoginSus(nameValuePairs);
        logD(TAG,"isSus = " + isSus);
*/

/*
        参数名 	类型 	必须 	默认值 	示例 	说明
        appkey 	String 	Y 			由Evcard平台分配给第三方平台的识别号
        timestamp 	String 	Y 			API调用者传递时间戳，为时间转换为毫秒的值，也就是从1970年1月1日起至今的时间转换为毫秒，默认15分钟内有效
        random 	String 	Y 		随机串6位
        sign 	String 	Y 			(appkey+ appsecret+timestamp.toString()+random).ToMD5()
        (32位，小写)
        Content-Type 	String 	Y 		application/json
                */

//        String timestamp = Calendar.getInstance().getTimeInMillis() + "";

        String url = "http://www.baidu.com";  //北京时间
//        timestamp = "1536914457000";//1536913604000
        String timestamp = TimeUtils.getTimestampFromUrl(url);
        String random = "123456";
        String sign = null;
        String appkey = "svm_rest";
        String appsecret = "9e11ab2d-2740-455d-9338-29feb1e87ec4";
        try {
            sign = EncodeUtils.md5Encode(appkey + appsecret + timestamp + random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String type = "application/json";

        Utils.logD(TAG, "appkey - " + appkey);
        Utils.logD(TAG, "timestamp - " + timestamp);
        Utils.logD(TAG, "random - " + random);
        Utils.logD(TAG, "sign - " + sign);
        Utils.logD(TAG, "Content-Type  - " + type);

        List<Header> headersPair = new ArrayList<Header>();
        headersPair.add(new BasicHeader("appkey", appkey));
        headersPair.add(new BasicHeader("timestamp", timestamp));
        headersPair.add(new BasicHeader("random", random));
        headersPair.add(new BasicHeader("sign", sign));
        headersPair.add(new BasicHeader("Content-Type", type));

        //http://csms-st.evcard.vip:180/evcard-svm/api/activityList?terminalId=123456
//        String html = HttpUtils.sendHttp(BaseUrl + "/evcard-svm/api/activityList", true);

//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("{\"terminalId\":\"123456\"}",null));

//        String result = HttpUtils.sendPost(html, nvps, headersPair);
//        logD(TAG,result);
//        HttpUtils.evTest(urlStr);
//        HttpUtils.sendEvcardPost(urlStr_1,"{\"terminalId\":\"123456\"}", headersPair);


        for (int i = 0; i < urlStrArr.length; i++) {
            Utils.logD(TAG, "\r\n" + "-------- 1." + String.valueOf(i + 1) + "-----------");

            HttpUtils.sendEvcardPost(urlStrArr[i], bodyStrArr[i], headersPair);
        }

        HttpUtils.sendEvcardGet(qureSipNumUrl, headersPair);

          //先base64解码,
        byte[] bytes = data_base64_gzip.getBytes();
        byte[] decoded = null;
        try {
            decoded = Base64.decode(bytes);
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        //再GzipUtil解压缩
        byte[] resultByte = GzipUtil.unCompress(decoded);
        String resultStr = new String(resultByte);
        Utils.logD(TAG, "base64- gzip - decode - resultStr - " + resultStr);

//        test();
    }

    private static void test() {

        int len = 256;
        int i = 10;
        Utils.logD(TAG, "10 >> 1==" + (i >> 1));
        Utils.logD(TAG, "+ 10 >> 1==" + (+(i >> 1)));
        Utils.logD(TAG, "256 + +10 >> 1==" + (len + +(i >> 1)));

    }


}
