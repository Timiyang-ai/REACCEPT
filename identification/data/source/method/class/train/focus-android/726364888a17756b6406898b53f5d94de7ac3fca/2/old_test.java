    @Test
    public void shouldInterceptRequest() throws Exception {
        trackingProtectionWebViewClient.notifyCurrentURL("http://www.mozilla.org");

        // Just some generic sanity checks that a definitely not blocked domain can be loaded, and
        // definitely blocked domains can't be
        {
            final WebResourceRequest request = createRequest("http://mozilla.org/about", false);
            final WebResourceResponse response = trackingProtectionWebViewClient.shouldInterceptRequest(webView, request);
            assertResourceAllowed(response);
        }

        {
            final WebResourceRequest request = createRequest("http://trackersimulator.org/foobar", false);
            final WebResourceResponse response = trackingProtectionWebViewClient.shouldInterceptRequest(webView, request);
            assertResourceBlocked(response);
        }
    }