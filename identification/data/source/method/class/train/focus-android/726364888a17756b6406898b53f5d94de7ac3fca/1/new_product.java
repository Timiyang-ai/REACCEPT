@Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        // shouldInterceptRequest() might be called _before_ onPageStarted or shouldOverrideUrlLoading
        // are called (this happens when the webview is first shown). However we are notified of the URL
        // via notifyCurrentURL in that case.
        final UrlMatcher matcher = getMatcher(view.getContext());

        // Don't block the main frame from being loaded. This also protects against cases where we
        // open a link that redirects to another app (e.g. to the play store).
        if ((!request.isForMainFrame()) &&
                matcher.matches(request.getUrl().toString(), currentPageURL)) {
            return new WebResourceResponse(null, null, null);
        }

        return super.shouldInterceptRequest(view, request);
    }