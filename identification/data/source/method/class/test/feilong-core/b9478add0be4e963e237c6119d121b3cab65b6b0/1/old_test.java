@Test
    public final void getResponseBodyAsString(){
        String templateFile = "http://10.8.25.80:6666/template.csv?sign=123456";

        HttpURLConnectionParam httpURLConnectionParam = new HttpURLConnectionParam();
        httpURLConnectionParam.setContentCharset(CharsetType.GBK);

        String responseBodyAsString = URLConnectionUtil.getResponseBodyAsString(templateFile, httpURLConnectionParam);
        LOGGER.info(responseBodyAsString);
    }