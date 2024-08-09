    private String encode(final String unencoded) {
        return org.apache.sling.commons.json.http.Cookie.escape(unencoded);
    }