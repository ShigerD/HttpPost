package com.shiger.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHeader;

public class HttpUtils {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static HttpClientContext context = new HttpClientContext();

    private HttpUtils() {

    }

    public static String sendHttp(String url,Boolean isPost) {
        CloseableHttpResponse response = null;
        String content = null;

        try {
            HttpRequestBase get = null;
            if (isPost) {
                get = new HttpPost(url);
            } else {
                get = new HttpGet(url);
            }
            response = httpClient.execute(get, context);
            HttpEntity entity = response.getEntity();
            entity = new BufferedHttpEntity(entity);

            InputStream result = new BufferedInputStream(entity.getContent());
            StringBuffer out = new StringBuffer();

            byte[] b = new byte[4096];
            for (int n; (n = result.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            content = out.toString();
            result.close();
//            System.out.println(content);
            entity = null;
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String sendPost(String url, List<NameValuePair> nvps) {
        CloseableHttpResponse response = null;
        String content = null;
        try {
            //　HttpClient中的post请求包装类
            HttpPost post = new HttpPost(url);
            // nvps是包装请求参数的list
            if (nvps != null) {
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            response = httpClient.execute(post, context);
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            entity = new BufferedHttpEntity(entity);
            InputStream result = new BufferedInputStream(entity.getContent());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = result.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            content = out.toString();
            result.close();
//          EntityUtils.consume(entity);
            entity = null;
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static String sendPost(String url,List<Header> headersPair,
                                  List<NameValuePair> nvps) {
        CloseableHttpResponse response = null;
        String content = null;
        try {
            //　HttpClient中的post请求包装类
            HttpPost post = new HttpPost(url);
            //
            for (Header header:
                    headersPair) {
                post.addHeader(header);
            }
            // nvps是包装请求参数的list
            if (nvps != null) {
                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            try {
                response = httpClient.execute(post, context);
            }catch (NoHttpResponseException exception){
                exception.printStackTrace();
            }
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            entity = new BufferedHttpEntity(entity);
            InputStream result = new BufferedInputStream(entity.getContent());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = result.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            content = out.toString();
            result.close();
//          EntityUtils.consume(entity);
            entity = null;
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }


    public static String sendEvcardGet(String urlStr , List<Header> headersPair) {
        CloseableHttpResponse response = null;
        String content = null;

        try {

            //　HttpClient中的post请求包装类
            HttpGet httpGet = new HttpGet(urlStr);

            System.out.println("urlStr = " + urlStr);

//            System.out.println("bodyStr = " + bodyStr);
            //headersPair
            System.out.println("headersPair = " + headersPair );

            for (Header header:
                    headersPair) {
                httpGet.addHeader(header);
            }

//            HttpEntity entityIn = new StringEntity(bodyStr);
//            httpGet.setEntity(entityIn);
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            response = httpClient.execute(httpGet, context);
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            entity = new BufferedHttpEntity(entity);
            InputStream result = new BufferedInputStream(entity.getContent());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = result.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            content = out.toString();
            result.close();
//          EntityUtils.consume(entity);
            entity = null;
            System.out.println("\r");
            System.out.println("sendPost--result = " + content);
            System.out.println("\r\n"+"\r\n");
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static String sendEvcardPost(String urlStr, String bodyStr , List<Header> headersPair) {
        CloseableHttpResponse response = null;
        String content = null;

           try {

               //　HttpClient中的post请求包装类
                HttpPost post = new HttpPost(urlStr);

                System.out.println("urlStr = " + urlStr);

                System.out.println("bodyStr = " + bodyStr);
                       //headersPair
                System.out.println("headersPair = " + headersPair );

             for (Header header:
                 headersPair) {
                post.addHeader(header);
            }
//            System.out.println("header = " + header);

               /*
            Header header = new BasicHeader("Content-Type","application/json");
            post.addHeader(header);
    */
//            post.addHeader("Content-Type","application/json");
            HttpEntity entityIn = new StringEntity(bodyStr);
            post.setEntity(entityIn);
            // 执行请求用execute方法，content用来帮我们附带上额外信息
            response = httpClient.execute(post, context);
            // 得到相应实体、包括响应头以及相应内容
            HttpEntity entity = response.getEntity();
            // 得到response的内容
            entity = new BufferedHttpEntity(entity);
            InputStream result = new BufferedInputStream(entity.getContent());
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = result.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            content = out.toString();
            result.close();
//          EntityUtils.consume(entity);
            entity = null;
            System.out.println("\r");
            System.out.println("sendPost--result = " + content);
            System.out.println("\r\n"+"\r\n");
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    /**
     * evcard test  POST
     */
    public static void evTest (String urlStr) {
        try {
            //传递参数
            String Parma = "?cardType={}&cardID={}";

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            connection.addRequestProperty("from", "sfzh");  //来源哪个系统
            connection.setRequestProperty("user", "user");  //访问申请用户
            InetAddress address = InetAddress.getLocalHost();
            String ip=address.getHostAddress();//获得本机IP
 /*           connection.setRequestProperty("ip",ip);  //请求来源IP
            connection.setRequestProperty("encry", "123456");*/
            connection.setRequestProperty("Content-Type", "application/json");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            String parm ="username=zhagnsan&password=0000";
            System.out.println("传递参数："+parm);
            dataout.writeBytes(parm);
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            while ((line = bf.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST (String urlStr) {
        try {
            //传递参数
            String Parma = "?cardType={}&cardID={}";

            URL url = new URL(urlStr);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            //addRequestProperty添加相同的key不会覆盖，如果相同，内容会以{name1,name2}
            connection.addRequestProperty("from", "sfzh");  //来源哪个系统
            //setRequestProperty添加相同的key会覆盖value信息
            //setRequestProperty方法，如果key存在，则覆盖；不存在，直接添加。
            //addRequestProperty方法，不管key存在不存在，直接添加。
            connection.setRequestProperty("user", "user");  //访问申请用户
            InetAddress address = InetAddress.getLocalHost();
            String ip=address.getHostAddress();//获得本机IP
            connection.setRequestProperty("ip",ip);  //请求来源IP
            connection.setRequestProperty("encry", "123456");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm ="username=zhagnsan&password=0000";
            System.out.println("传递参数："+parm);
            // 将参数输出到连接
            dataout.writeBytes(parm);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}