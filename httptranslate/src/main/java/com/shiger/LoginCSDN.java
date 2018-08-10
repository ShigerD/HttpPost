package com.shiger;

import com.shiger.utils.HttpUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiger on 12/20/17.
 * e-mail : tigerwithwings@foxmail.com
 */

public class LoginCSDN {

    String LOGIN_URL = "https://passport.csdn.net/account/login;jsessionid=B4A6A0D398CB284EAAB47F704FF3090B.tomcat2?fromAuto=http://my.csdn.net/my/mycsdn";
    String usr = "602073555@qq.com";
    String psd = "csdn011416";

    List<NameValuePair> getNVPS() {
        String html = HttpUtils.sendHttp(LOGIN_URL, false);
        Document doc = Jsoup.parse(html);
        Element form = doc.select(".user-pass").get(0);
        String lt = form.select("input[name=lt]").get(0).val();
        String execution = form.select("input[name=execution]").get(0).val();
        String _eventId = form.select("input[name=_eventId]").get(0).val();
        System.out.println("lt=" + lt);
        System.out.println("execution=" + execution);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", usr));
        nvps.add(new BasicNameValuePair("password", psd));
        nvps.add(new BasicNameValuePair("lt", lt));
        nvps.add(new BasicNameValuePair("execution", execution));
        nvps.add(new BasicNameValuePair("_eventId", _eventId));
        System.out.println("nvps = " + nvps);
        return nvps;
    }

    boolean isLoginSus(List<NameValuePair> nvps) {
        boolean isSus = false;
        String result = HttpUtils.sendPost(LOGIN_URL, nvps);
        System.out.println(result);
        if (result.indexOf("redirect_back") > -1) {
            System.out.println("登陆成功。。。。。");
            isSus = true;
        } else if (result.indexOf("登录太频繁") > -1) {
            System.out.println("登录太频繁，请稍后再试。。。。。");
        } else {
            System.out.println("登陆失败。。。。。");
        }
        return isSus;
    }
}
