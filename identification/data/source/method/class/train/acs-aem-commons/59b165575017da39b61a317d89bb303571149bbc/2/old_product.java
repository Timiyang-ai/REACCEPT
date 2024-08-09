@Override
    protected final String encode(String unencoded) {
        return StringUtils.isBlank(unencoded) ? "" : org.apache.sling.commons.json.http.Cookie.escape(unencoded);
    }