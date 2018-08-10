package com.shiger.baiduTrans;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("fromAuto", from);
        params.put("toAuto", to);

        params.put("appid", appid);

        // 随机数
//        String salt = String.valueOf(System.currentTimeMillis());
        String salt = "1435660288";
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文

        params.put("sign", MD5.md5(src));
        System.out.println("sign:" + MD5.md5(src) + "\n");
        return params;
    }

}
