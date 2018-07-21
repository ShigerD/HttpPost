package com.shiger.baiduTrans;


public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "";
    private static final String SECURITY_KEY = "";

    private static final String APPID = "20180712000185169";
    private static final String SECRET = "MBuKuWNVZMbYFaqE4_Lr";

    public static void main(String[] args) {

        TransApi api = new TransApi(APPID, SECRET);

        String query = "高度600米";
        System.out.println(api.getTransResult(query, "auto", "en"));



        //
        String strSrcEn = "hello";
        String strZh =  api.getTransResult(strSrcEn, "en", "zh");
        System.out.println(strZh);
    }


    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

}
