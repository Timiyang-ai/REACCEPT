public String getHtmlCookieString(String name, String value, Map<String, String> cookieProperties) {
        return createHtmlCookieString(name, value, cookieProperties, true);
    }