@Override
    protected final String decode(String encoded) {
        try {
            return StringUtils.isBlank(encoded) ? "" : URLDecoder.decode(encoded, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.error("Cannot decode '{}'", ex);
            return encoded;
        }
    }