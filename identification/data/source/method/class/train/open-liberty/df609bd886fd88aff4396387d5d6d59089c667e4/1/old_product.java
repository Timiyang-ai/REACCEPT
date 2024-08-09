public String getHtmlCookieString(String name, String value, Map<String, String> cookieProperties) {
        if (name == null || name.isEmpty()) {
            if (tc.isDebugEnabled()) {
                Tr.debug(tc, "Cannot create a cookie string because the cookie name [" + name + "] was null or empty.");
            }
            return "";
        }
        String cookieString = createHtmlCookiePropertyString(name, value);
        cookieString += createHtmlCookiePropertiesString(cookieProperties);
        return cookieString;
    }