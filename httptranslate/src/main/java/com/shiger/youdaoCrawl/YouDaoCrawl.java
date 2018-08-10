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

import static com.shiger.youdaoCrawl.YoudaoConstant.*;

/**
 * Created by shiger on 2018/7/21.
 */

public class YouDaoCrawl {

    public static String TAG = "YouDaoCrawl";


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("~welcome!~" + TAG);

        String inputMag = "hello";

/*        //getSign
        YouDaoCrawl youDaoCrawl = new YouDaoCrawl();
        salt = youDaoCrawl.getYouDaoSalt();
        sign = youDaoCrawl.getYouDaoSign(inputMag);

        //
        List<Header> headersPair = youDaoCrawl.getYouDaoHead();
        //
        String httpGetYouDao = HttpUtils.sendHttp(Fanyi_Youdao_Com_Home,false);
        System.out.println("httpGetYouDao ：\r\n" + httpGetYouDao);
*/

        while (true) {
            System.out.println("请输入需要翻译的文本：");
            Scanner in = new Scanner(System.in);
            inputMag = in.nextLine();//
            System.out.println(inputMag);

/*
            HttpUtils.sendHttp("http://fanyi.youdao.com/ctlog?pos=&action=MT_BUTTON_CLICK",false);
            //
            List<NameValuePair> body = youDaoCrawl.getYouDaoBody(inputMag, "en", "zh");
            String youDaoReturn = HttpUtils.sendPost(YouDaoTargetUrl,headersPair, body);
            System.out.println("youReturn ：\r\n" + youDaoReturn);
*/
            YouDaoCrawl youDaoCrawl = new YouDaoCrawl();
            youDaoCrawl.youTranslate(inputMag);
        }
    }

    private String youTranslate(String inputMag) {
        //getSign
        YouDaoCrawl youDaoCrawl = new YouDaoCrawl();
        String salt = youDaoCrawl.getYouDaoSalt();
        String sign = youDaoCrawl.getYouDaoSign(inputMag, salt);
        //
        List<Header> headersPair = youDaoCrawl.getYouDaoHead();
        System.out.println("headersPair ：\r\n" + headersPair.toString());
        // 反扒
//        HttpUtils.sendHttp("http://fanyi.youdao.com/ctlog?pos=&action=MT_BUTTON_CLICK", false);
        //
        List<NameValuePair> bodyZh2en = youDaoCrawl.getYouDaoBodyZh2en(inputMag, salt, sign);
        System.out.println("body ：\r\n" + bodyZh2en.toString());
        String youDaoReturn = HttpUtils.sendPost(YouDaoTargetUrl, headersPair, bodyZh2en);
        System.out.println("youReturn ：\r\n" + youDaoReturn);
        return youDaoReturn;
    }

    private String getYouDaoSalt() {
        //r = "" + ((new Date).getTime() + parseInt(10 * Math.random(), 10)),
        Date date = new Date();
        int random = (int) (1 + Math.random() * (10)); //从1到10的int型随数;
        String salt = String.valueOf(date.getTime() + random);
        System.out.println("~date.getTime()!~" + date.getTime());
        System.out.println("random " + random);
        System.out.println("salt " + salt);
        return salt;
    }

    private String getYouDaoSign(String input, String salt) {

        String md5In = "fanyideskweb" + input + salt + "ebSeFb%=XZ%T[KZ)c(sy!";
//        String md5In = "fanyideskweb" + input + salt + "rY0D^0'nM0}g5Mm1z%1G4";
        String sign = "";
        try {
            sign = EncodeUtils.md5Encode(md5In);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("sign " + sign);
        return sign;
    }


    private List<Header> getYouDaoHead() {
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
        headersPair.add(new BasicHeader("Cookie"
//                , "YOUDAO_MOBILE_ACCESS_TYPE=1; OUTFOX_SEARCH_USER_ID=-1289760786@10.168.8.76; OUTFOX_SEARCH_USER_ID_NCOO=939708194.3484184; _ntes_nnid=3261dca1448d041f16596bf4942976dd,1524295653112; JSESSIONID=aaaWj8T4yP2lIcfjqeSlw; ___rl__test__cookies="
                , "OUTFOX_SEARCH_USER_ID_NCOO=615567211.0544564; YOUDAO_MOBILE_ACCESS_TYPE=1; OUTFOX_SEARCH_USER_ID=-377961237@10.169.0.83; fanyi-ad-id=47865; fanyi-ad-closed=1; JSESSIONID=aaalmrxz4Tr2Q4uczePtw; ___rl__test__cookies="
                + String.valueOf((new Date()).getTime())));
        return headersPair;
    }

    private List<NameValuePair> getYouDaoBodyEn2Zh(String input, String salt, String sign) {
        List<NameValuePair> body = getYouDaoBody(input, "en", "zh-CHS", salt, sign);
        return body;
    }

    private List<NameValuePair> getYouDaoBodyZh2en(String input, String salt, String sign) {

        List<NameValuePair> body = getYouDaoBody(input, "zh-CHS", "en", salt, sign);
        return body;
    }

    private List<NameValuePair> getYouDaoBody(String input, String from, String to, String salt, String sign) {
        List<NameValuePair> body = new ArrayList<NameValuePair>();
//        body.add(new BasicNameValuePair("action", "lan-select"));
        body.add(new BasicNameValuePair("action", action));
        body.add(new BasicNameValuePair("i", input));
        body.add(new BasicNameValuePair("from", from));
        body.add(new BasicNameValuePair("to", to));
        body.add(new BasicNameValuePair("smartresult", smartresult));
        body.add(new BasicNameValuePair("client", client));
        body.add(new BasicNameValuePair("salt", salt));
        body.add(new BasicNameValuePair("sign", sign));
        body.add(new BasicNameValuePair("doctype", doctype));
        body.add(new BasicNameValuePair("keyfrom", keyfrom));
        body.add(new BasicNameValuePair("typoResult", typoResult));
        body.add(new BasicNameValuePair("version", version));
        return body;
    }


}
