package com.shiger.youdaoCrawl;

/**
 * created by shiger on 7/30/18.
 */

public class YoudaoConstant {

    public static String Fanyi_Youdao_Com_Home = "http://fanyi.youdao.com/";
    //
    public static String YouDaoTargetUrl =
            "http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule";//computer
//    "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule";//mobile
    //head
    public static String User_Agent =
//            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
            "Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0";
    //body
    public static String action = "FY_BY_CLICKBUTTION";
    public static String client = "fanyideskweb";
    public static String doctype = "json";
    public static String fromAuto = "AUTO";
    public static String i = "hello friend";
    public static String keyfrom = "fanyi.web";
    //
    public static String smartresult = "dict";
    public static String toAuto = "AUTO";
    public static String typoResult = "false";
    public static String version = "2.1";

/*  //all kind of youDao
    AUTO: "AUTO",
    EN2ZH_CN: "en2zh-CHS",
    ZH_CN2EN: "zh-CHS2en",
    JA2ZH_CN: "ja2zh-CHS",
    ZH_CN2JA: "zh-CHS2ja",
    FR2ZH_CN: "fr2zh-CHS",
    ZH_CN2FR: "zh-CHS2fr",
    KR2ZH_CN: "ko2zh-CHS",
    ZH_CN2KR: "zh-CHS2ko",
    RU2ZH_CN: "ru2zh-CHS",
    ZH_CN2RU: "zh-CHS2ru",
    SP2ZH_CN: "es2zh-CHS",
    ZH_CN2SP: "zh-CHS2es",
    PT2ZH_CN: "pt2zh-CHS",
    ZH_CN2PT: "zh-CHS2pt"
    */
}
