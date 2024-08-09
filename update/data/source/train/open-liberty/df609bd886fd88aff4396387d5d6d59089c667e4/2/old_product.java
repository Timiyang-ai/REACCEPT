public String getJavaScriptForRedirect(String reqUrlCookieName, String redirectUrl) throws Exception {
        String javascript = createJavaScriptForRedirectCookie(reqUrlCookieName) + "\n";
        javascript += createJavaScriptStringToPerformRedirect(redirectUrl) + "\n";
        return wrapInJavascriptHtmlTags(javascript);
    }