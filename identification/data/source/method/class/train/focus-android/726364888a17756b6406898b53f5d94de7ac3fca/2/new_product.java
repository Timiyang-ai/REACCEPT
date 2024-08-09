@Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        // There's not guarantee that data has finished loading by now. shouldInterceptRequest() is
        // called off the UI thread, so it's safe for us to block on loading if necessary.
        final UrlMatcher matcher = getMatcher(view.getContext());

        if (matcher.matches(url, currentPageURL)) {
            return new WebResourceResponse(null, null, null);
        }

        return super.shouldInterceptRequest(view, url);
    }