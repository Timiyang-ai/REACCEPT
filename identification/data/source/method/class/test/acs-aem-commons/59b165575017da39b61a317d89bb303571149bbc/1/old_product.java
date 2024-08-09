@Override
    protected final String decode(String encoded) {
        return StringUtils.isBlank(encoded) ? "" : org.apache.sling.commons.json.http.Cookie.unescape(encoded);
    }