@Override
    protected final String encode(String unencoded) {
        try {
            return StringUtils.isBlank(unencoded) ? "" : URLEncoder.encode(unencoded, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.error("Cannot encode '{}' to UTF-8", unencoded, ex);
            return unencoded;
        }
    }