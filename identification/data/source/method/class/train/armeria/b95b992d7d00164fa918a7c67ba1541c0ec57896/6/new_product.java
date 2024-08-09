public T responseHeadersSanitizer(
            Function<? super HttpHeaders, ? extends HttpHeaders> responseHeadersSanitizer) {
        this.responseHeadersSanitizer = requireNonNull(responseHeadersSanitizer, "responseHeadersSanitizer");
        return self();
    }