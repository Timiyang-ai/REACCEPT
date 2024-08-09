public String getJavaScriptHtmlCookieString(String name, String value, Map<String, String> cookieProperties) {
        return "document.cookie=\"" + getHtmlCookieString(name, value, cookieProperties) + "\";";
    }