public String getCacheKey() {
        String url = getUrl();
        // If this is a GET request, just use the URL as the key.
        // For callers using DEPRECATED_GET_OR_POST, we assume the method is GET, which matches
        // legacy behavior where all methods had the same cache key. We can't determine which method
        // will be used because doing so requires calling getPostBody() which is expensive and may
        // throw AuthFailureError.
        // TODO(#190): Remove support for non-GET methods.
        int method = getMethod();
        if (method == Method.GET || method == Method.DEPRECATED_GET_OR_POST) {
            return url;
        }
        return Integer.toString(method) + '-' + url;
    }