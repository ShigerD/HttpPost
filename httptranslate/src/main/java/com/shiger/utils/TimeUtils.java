package com.shiger.utils;

/**
 * created by shiger on 9/13/18.
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.shiger.utils.Utils.logD;


public class TimeUtils {
    private static String TAG = "TimeUtils";

    protected static final int SOCKET_TIMEOUT = 10000; // 10S
    protected static final String GET = "GET";
    /**
     * @param targetUrl
     * @return urlDate 
     */
    public static String getDateTimeFromUrl(String targetUrl){
        logD(TAG, "getDateTimeFromUrl：targetUrl >"+ targetUrl);


        String urlDate = null;
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          /*  if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }*/
            conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
            conn.setRequestMethod(GET);
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Utils.logD(TAG,"Http错误码：" + statusCode);
            }
            /*URLConnection conn = url.openConnection();
            conn.connect();  //*/
            Date date = new Date(conn.getDate());  //get date&time
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");  //format
            urlDate = df.format(date);
            logD(TAG, "getDateTimeFromUrl：urlDate >"+ urlDate);
            conn.disconnect(); // 断开连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlDate;
    }

    public static String getTimestampFromUrl(String url){
        String timestamp =  dateToStamp(getDateTimeFromUrl(url));
        logD(TAG, "getDateTimeFromUrl：timestamp >"+ timestamp);
        return timestamp;
    }

    public static String getTimestampFromBaidu(){
        String url = "http://www.baidu.com";
        String timestamp =  getTimestampFromUrl(url);
        logD(TAG, "getDateTimeFromUrl：timestamp >"+ timestamp);
        return timestamp;
    }


    /*
    * 将时间转换为时间戳
    */
    public static String dateToStamp(String time) {
        if(null==time){
            return null;
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}