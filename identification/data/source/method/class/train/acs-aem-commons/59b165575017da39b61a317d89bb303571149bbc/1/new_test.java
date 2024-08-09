    private String decode(final String encodedForm) {
        return org.apache.sling.commons.json.http.Cookie.unescape(encodedForm);
    }