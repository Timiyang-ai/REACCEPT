public static String inputStream2String(final InputStream is, final String charsetName) {
        if (is == null || isSpace(charsetName)) return "";
        try {
            ByteArrayOutputStream baos = input2OutputStream(is);
            if (baos == null) return "";
            return baos.toString(charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }