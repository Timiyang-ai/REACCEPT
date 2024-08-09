public static String urlEncode(String string, String charset) {
        try {
            return URLEncoder.encode(string, charset);
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }