package com.shiger.youdaoCrawl;

import com.shiger.utils.EncodeUtils;
import com.shiger.utils.HttpUtils;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shiger on 2018/7/21.
 */

public class YouDaoCrawl {

    static String TAG = "YouDaoCrawl";

    //
    static String YouDaoTargetUrl =
            "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=null";

    //head
    static String User_Agent =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    //body
    static String action =	"FY_BY_CLICKBUTTION";
    static String client	= "fanyideskweb";
    static String doctype	= "json";
    static String from	= "AUTO";
    static String i	= "hello";
    static String keyfrom = "fanyi.web";
    //
    static String salt	;
    static String sign	;
    //
    static String smartresult	= "dict";
    static String to	= "AUTO";
    static String typoResult	= "false";
    static String version	= "2.1";


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("~welcome!~" + TAG);



        //r = "" + ((new Date).getTime() + parseInt(10 * Math.random(), 10)),
        Date date = new Date();
        int random =  (int)(1+Math.random()*(10)); //从1到10的int型随数;
        salt = String.valueOf(date.getTime() + random);
        System.out.println("~date.getTime()!~" + date.getTime());
        System.out.println("random " + random);
        System.out.println("salt " + salt);

        String S = "fanyideskweb";
        //
        String D = "rY0D^0\'nM0}g5Mm1z%1G4";
        //
        String n = i;
        //
        String md5In = S + n + salt + D;
        sign = EncodeUtils.md5Encode(md5In);
        System.out.println("sign " + sign);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("i", i));
        nvps.add(new BasicNameValuePair("from", from));
        nvps.add(new BasicNameValuePair("to", to));
        nvps.add(new BasicNameValuePair("smartresult", smartresult));
        nvps.add(new BasicNameValuePair("client", client));
        nvps.add(new BasicNameValuePair("salt", salt));
        nvps.add(new BasicNameValuePair("sign", sign));
        nvps.add(new BasicNameValuePair("doctype", doctype));
        nvps.add(new BasicNameValuePair("keyfrom", keyfrom));
        nvps.add(new BasicNameValuePair("action", action));
        nvps.add(new BasicNameValuePair("typoResult", typoResult));
        nvps.add(new BasicNameValuePair("version", version));

        //
        //head
        List<Header> headersPair = new ArrayList<Header>();
        headersPair.add(new BasicHeader("User-Agent", User_Agent));
//        headersPair.add(new BasicHeader("Host", "fanyi"));
//        headersPair.add(new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
//        headersPair.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
//        headersPair.add(new BasicHeader("Referer", "http://fanyi.youdao.com/"));
//        headersPair.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
//        headersPair.add(new BasicHeader("X-Requested-With", "XMLHttpRequest"));
//        headersPair.add(new BasicHeader("Connection", "keep-alive"));
//        headersPair.add(new BasicHeader("Pragma", "no-cache"));
//        headersPair.add(new BasicHeader("Cache-Control", "no-cache"));


        //head end
        String youReturn = HttpUtils.sendPost(YouDaoTargetUrl,headersPair,nvps);
        System.out.println("youReturn ：\r\n" + youReturn);

    }

}
