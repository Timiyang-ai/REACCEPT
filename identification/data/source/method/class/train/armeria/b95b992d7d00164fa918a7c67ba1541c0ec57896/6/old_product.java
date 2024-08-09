public T responseHeadersSanitizer(Function<HttpHeaders, HttpHeaders> responseHeadersSanitizer) {
        this.responseHeadersSanitizer = requireNonNull(responseHeadersSanitizer, "responseHeadersSanitizer");
        return unsafeCast(this);
    }