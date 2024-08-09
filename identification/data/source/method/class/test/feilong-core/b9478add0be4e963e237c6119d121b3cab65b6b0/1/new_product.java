public static String getResponseBodyAsString(String urlString,ConnectionConfig connectionConfig){
        if (null == connectionConfig){
            connectionConfig = new ConnectionConfig();
        }
        InputStream inputStream = getInputStream(urlString, connectionConfig);
        return InputStreamUtil.inputStream2String(inputStream, connectionConfig.getContentCharset());

    }