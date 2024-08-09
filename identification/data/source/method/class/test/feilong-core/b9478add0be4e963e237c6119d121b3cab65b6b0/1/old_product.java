public static String getResponseBodyAsString(String urlString,HttpURLConnectionParam httpURLConnectionParam){
        if (null == httpURLConnectionParam){
            httpURLConnectionParam = new HttpURLConnectionParam();
        }
        InputStream inputStream = getInputStream(urlString, httpURLConnectionParam);
        String inputStream2String = InputStreamUtil.inputStream2String(inputStream, httpURLConnectionParam.getContentCharset());
        return inputStream2String;

    }