public T requestHeadersSanitizer(Function<? super HttpHeaders, ?> requestHeadersSanitizer) {
        this.requestHeadersSanitizer = requireNonNull(requestHeadersSanitizer, "requestHeadersSanitizer");
        return self();
    }