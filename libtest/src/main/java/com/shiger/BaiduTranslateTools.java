package com.shiger;

import com.shiger.utils.EncodeUtils;
import com.shiger.utils.HttpUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by shiger on 2018/7/15.
 */

public class BaiduTranslateTools {

    /*
        appid=2015063000000001
        salt=1435660288
        平台分配的密钥: 12345678
        */

/*    private static final String APPID = "2015063000000001";
    private static final String SECRET = "12345678";
    private static final String Salt = "1435660288";*/


    private static final String APPID = "20180712000185169";
    private static final String SECRET = "MBuKuWNVZMbYFaqE4_Lr";
//    private static final String SECRET = "12345678";
    private static final String Salt = "1435660288";
    private static final String API_HTTP_URL =
            "http://api.fanyi.baidu.com/api/trans/vip/translate?";


/*    private static String getTranslateEn2ZhSign() {
        String inputEnglish = "apple";
        String from = "en";
        String to = "zh";
        String md5Input = APPID + inputEnglish + Salt + SECRET;
        String sign = "";
        try {
            sign = EncodeUtils.md5Encode(md5Input);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }*/

    private static String getTranslateEn2ZhSign(String inputEnglish) {
        String from = "en";
        String to = "zh";
        String salt = String.valueOf(System.currentTimeMillis());
        String md5Input = APPID + inputEnglish + Salt + SECRET;
        String sign = "";
        try {
            sign = EncodeUtils.md5Encode(md5Input);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

/*    private static String getTranslateEn2ZhUrl() {
        String targetUrl = API_HTTP_URL + "q=apple&from=en&to=zh&appid="
                + APPID + "&sign=" + getTranslateEn2ZhSign();
        return targetUrl;
    }*/

    private static String getTranslateEn2ZhUrl(String inputEnglish) {
        String targetUrl = API_HTTP_URL + "q=" + inputEnglish + "&from=en&to=zh&appid="
                + APPID + "&sign=" + getTranslateEn2ZhSign(inputEnglish);
        return targetUrl;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("~welcome!~");

        String testStr = "hello";
        System.out.println("sign: " + getTranslateEn2ZhSign(testStr));
        System.out.println("targetUrl: " + getTranslateEn2ZhUrl(testStr));

        String result = HttpUtils.sendHttp(getTranslateEn2ZhUrl(testStr),false);
        System.out.println("result: " + result);


    }
}
