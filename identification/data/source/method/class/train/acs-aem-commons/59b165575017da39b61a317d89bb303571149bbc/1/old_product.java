@Override
    protected final String decode(String encoded) {
        try {
            return StringUtils.isBlank(encoded) ? "" : URLDecoder.decode(encoded, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            return encoded;
        }
    }