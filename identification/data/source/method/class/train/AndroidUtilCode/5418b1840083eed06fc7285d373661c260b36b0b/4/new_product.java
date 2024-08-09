public static String inputStream2String(final InputStream is, final String charsetName) {
        if (is == null || isSpace(charsetName)) return "";
        try {
            return new String(inputStream2Bytes(is), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }