package com.shiger.youdaoCrawl;

import com.shiger.utils.EncodeUtils;
import com.shiger.utils.HttpUtils;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shiger on 2018/7/21.
 */

public class YouDaoCrawl {

    static String TAG = "YouDaoCrawl";

    //
    static String YouDaoTargetUrl =
            "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule";

    //head
    static String User_Agent =
//            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
            "Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0";
    //body
    static String action =	"FY_BY_CLICKBUTTION";
    static String client	= "fanyideskweb";
    static String doctype	= "json";
    static String from	= "AUTO";
    static String i	= "hello friend";
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

        while (true){
  /*          System.out.println("请输入需要翻译的文本：");
            Scanner in =new Scanner(System.in);
            String inputMag=in.nextLine();//
            System.out.println(inputMag);*/

            String inputMag="hello";

            //getSign
            YouDaoCrawl youDaoCrawl = new YouDaoCrawl();
            salt = youDaoCrawl.getYouDaoSalt();
            sign = youDaoCrawl.getYouDaoSign(inputMag);
            List<Header> headersPair = youDaoCrawl.getYouDaoHead();
            List<NameValuePair> body = youDaoCrawl.getYouDaoBody(inputMag, "en", "zh");
            String youDaoReturn = HttpUtils.sendPost(YouDaoTargetUrl,headersPair, body);
            System.out.println("youReturn ：\r\n" + youDaoReturn);
        }



    }

    private String getYouDaoSalt(){
        //r = "" + ((new Date).getTime() + parseInt(10 * Math.random(), 10)),
        Date date = new Date();
        int random =  (int)(1+Math.random()*(10)); //从1到10的int型随数;
        String salt = String.valueOf(date.getTime() + random);
        System.out.println("~date.getTime()!~" + date.getTime());
        System.out.println("random " + random);
        System.out.println("salt " + salt);
        return salt;
    }

    private String getYouDaoSign(String input){
        String S = "fanyideskweb";
        String n = i;
        String md5In = "fanyideskweb" + input + salt + "ebSeFb%=XZ%T[KZ)c(sy!";
//        String md5In = "fanyideskweb" + input + salt + "rY0D^0'nM0}g5Mm1z%1G4";
        try {
            sign = EncodeUtils.md5Encode(md5In);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("sign " + sign);
        return sign;
    }

    private  List<NameValuePair> getYouDaoBody (String input){
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair("i", input));
        body.add(new BasicNameValuePair("from", from));
        body.add(new BasicNameValuePair("to", to));
        body.add(new BasicNameValuePair("smartresult", smartresult));
        body.add(new BasicNameValuePair("client", client));
        body.add(new BasicNameValuePair("salt", salt));
        body.add(new BasicNameValuePair("sign", sign));
        body.add(new BasicNameValuePair("doctype", doctype));
        body.add(new BasicNameValuePair("keyfrom", keyfrom));
        body.add(new BasicNameValuePair("action", action));
        body.add(new BasicNameValuePair("typoResult", typoResult));
        body.add(new BasicNameValuePair("version", version));
        return body;
    }

    private List<Header> getYouDaoHead(){
        //
        //head
        List<Header> headersPair = new ArrayList<Header>();
        headersPair.add(new BasicHeader("User-Agent", User_Agent));
        headersPair.add(new BasicHeader("Host", "fanyi.youdao.com"));
        headersPair.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"));
        headersPair.add(new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
        headersPair.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        headersPair.add(new BasicHeader("Referer", "http://fanyi.youdao.com/"));
        headersPair.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
        headersPair.add(new BasicHeader("X-Requested-With", "XMLHttpRequest"));
        headersPair.add(new BasicHeader("Connection", "keep-alive"));
        headersPair.add(new BasicHeader("Pragma", "no-cache"));
        headersPair.add(new BasicHeader("Cache-Control", "no-cache"));

        /*
//        Content-Length
//        207
         */
        return headersPair;
    }

    private  List<NameValuePair> getYouDaoBody (String input, String from, String to){
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair("i", input));
        body.add(new BasicNameValuePair("from", from));
        body.add(new BasicNameValuePair("to", to));
        body.add(new BasicNameValuePair("smartresult", smartresult));
        body.add(new BasicNameValuePair("client", client));
        body.add(new BasicNameValuePair("salt", salt));
        body.add(new BasicNameValuePair("sign", sign));
        body.add(new BasicNameValuePair("doctype", doctype));
        body.add(new BasicNameValuePair("keyfrom", keyfrom));
        body.add(new BasicNameValuePair("action", action));
        body.add(new BasicNameValuePair("typoResult", typoResult));
        body.add(new BasicNameValuePair("version", version));
        return body;
    }


}
