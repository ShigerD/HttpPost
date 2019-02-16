package com.shiger;

import com.shiger.utils.HttpUtils;
import com.shiger.utils.Utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by shiger on 8/8/18.
 */

public class BingBackGround {
    private static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    private String Bing_International_Home = "https://cn.bing.com/?FORM=BEHPTB&ensearch=1";
    private String Bing_National_Home = "https://cn.bing.com/?FORM=BEHPTB";

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Utils.logD(TAG,"~welcome!~");

        BingBackGround bingBackGround = new BingBackGround();
        //
        String bingNationalHtml = HttpUtils.sendHttp(bingBackGround.Bing_National_Home, false);
//        logD(TAG,"bingHome------\n" + bingNationalHtml);
        bingBackGround.getBingInterntionalBgUrl(bingNationalHtml);
        //
        String bingInternationalHtml = HttpUtils.sendHttp(bingBackGround.Bing_International_Home, false);
//        logD(TAG,"bingHome------\n" + bingInternationalHtml);
        bingBackGround.getBingInterntionalBgUrl(bingInternationalHtml);


 /*     //lead to the ubuntu crash
        String command = "firefox --new-window  " + bingInternationalHtml;
        Process p = Runtime.getRuntime().exec(command);*/
    }

    private String getBingInterntionalBgUrl(String bingHtml){
        String result = "";
        String ref = "url\\:?+(.*),id";
        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(ref);
        Matcher matcher = pattern.matcher(bingHtml);
        if (matcher.find()) {
            result = matcher.group(1);
            Utils.logD(TAG,"group(0) >>>  " + result);
            Utils.logD(TAG,"group(1) >>>  " + matcher.group(1));
            result = result.replaceAll("\"", "");
            result = "https://cn.bing.com" + result.trim();
            Utils.logD(TAG,"result >>>  " + result);
        } else {
            Utils.logD(TAG,"NO MATCH");
        }
        return result;
    }

}
