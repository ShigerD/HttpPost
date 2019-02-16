
//厂家给的key
static String appkey = "svm_rest";
static String appsecret = "9e11ab2d-2740-455d-9338-29feb1e87ec4";

//目标接口url
static String urlStr_1 = "http://139.224.37.24:8102/evcard-svm/api/activityList";

//目标接口body参数
static String body1 = "{\"terminalId\":\"123456\"}";


//时间戳
 String timestamp = Calendar.getInstance().getTimeInMillis() + "";
//随机数
 String timestamp = Calendar.getInstance().getTimeInMillis() + "";
        String random = "123456";
//获取sign参数
String sign = md5Encode(appkey + appsecret + timestamp + random);


//添加头请求头参数
List<Header> headersPair = new ArrayList<Header>();
        headersPair.add(new BasicHeader("appkey", appkey));
        headersPair.add(new BasicHeader("timestamp", timestamp));
        headersPair.add(new BasicHeader("random", random));
        headersPair.add(new BasicHeader("sign", sign));
        headersPair.add(new BasicHeader("Content-Type", type));

//发送http请求，请求方法为post，得出结果.  具体方法见HttpUtils.sendEvcardPost函数。
result = HttpUtils.sendEvcardPost(urlStr_1,body1, headersPair);


    /**
     * MD5加密 生成32位md5码（小写）
     * @return 返回32位md5码
     */
    public static  String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            logD(TAG,e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        logD(TAG,"bytes.length - " + bytes.length );
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toLowerCase();
    }

