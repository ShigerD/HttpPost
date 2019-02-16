package com.shiger.tuLinChat;

import com.shiger.utils.HttpUtils;
import com.shiger.utils.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiger on 2018/7/21.
 */

public class TuLinTest {

    private static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        zz1();

        zz2();

        Utils.logD(TAG, "~welcome!~" + TAG);

        String inputMag = "hello";

        while (true) {
            Utils.logD(TAG, "Please talk：");
            Scanner in = new Scanner(System.in);
            inputMag = in.nextLine();//
            Utils.logD(TAG, inputMag);

            List<NameValuePair> body = getTuLinody(inputMag, "1");
            String outMsg = HttpUtils.sendPost(TuLinConstants.TuLinUrl, body);
            outMsg = getResultFromTulin(outMsg);
            Utils.logD(TAG, outMsg);
        }
    }

    private static List<NameValuePair> getTuLinody(String info, String userid) {
        List<NameValuePair> body = new ArrayList<NameValuePair>();
        body.add(new BasicNameValuePair("key", TuLinConstants.KEY));
        body.add(new BasicNameValuePair("info", info));
        body.add(new BasicNameValuePair("userid", userid));
        return body;
    }


    private static String getResultFromTulin(String str ){
        // 要验证的字符串
        // 正则表达式规则
        String regEx = "\"text\":\"(.*)\"";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        System.out.println("Found value ,groupCount: " + matcher.groupCount() );

        if (matcher.find()) {
            System.out.println("Found value: " + matcher.group(0) );
            System.out.println("Found value: " + matcher.group(1) );
        } else {
            System.out.println("Error NO MATCH");
        }
        return matcher.group(1);
    }

    private static void zz1(){
        // 要验证的字符串
        String str = "{\"code\":100000,\"text\":\"R\"}";
        // 正则表达式规则
        String regEx = "\"text\":\"(.*)\"";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        System.out.println("Found value ,groupCount: " + matcher.groupCount() );

        if (matcher.find()) {
            System.out.println("Found value: " + matcher.group(0) );
            System.out.println("Found value: " + matcher.group(1) );
//            System.out.println("Found value: " + matcher.group(2) );
//            System.out.println("Found value: " + matcher.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }

    static void zz2(){
        // 按指定模式在字符串查找
//        String line = "This order was placed for QT3000! OK?";

        String line = "{\"code\":100000,\"text\":\"R\"}";
        String pattern = "(\\D*)(\\d+),\"text\":\"(.*)\"}";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        System.out.println("Found value ,groupCount: " + m.groupCount() );


        if (m.find( )) {
            for (int i = 0; i <= m.groupCount() ; i++) {
                System.out.println("Found value> i=" + i+">>" + m.group(i) );
            }

        } else {
            System.out.println("NO MATCH");
        }
    }
}
