public T requestHeadersSanitizer(Function<HttpHeaders, HttpHeaders> requestHeadersSanitizer) {
        this.requestHeadersSanitizer = requireNonNull(requestHeadersSanitizer, "requestHeadersSanitizer");
        return unsafeCast(this);
    }