@Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (matcher.matches(url, currentPageURL)) {
            return new WebResourceResponse(null, null, null);
        }

        return super.shouldInterceptRequest(view, url);
    }