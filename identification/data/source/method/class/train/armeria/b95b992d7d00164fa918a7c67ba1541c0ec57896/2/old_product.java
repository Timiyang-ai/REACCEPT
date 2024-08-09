public T requestHeadersSanitizer(
            Function<? super HttpHeaders, ? extends HttpHeaders> requestHeadersSanitizer) {
        this.requestHeadersSanitizer = requireNonNull(requestHeadersSanitizer, "requestHeadersSanitizer");
        return self();
    }