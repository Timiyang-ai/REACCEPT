public T responseHeadersSanitizer(Function<? super HttpHeaders, ?> responseHeadersSanitizer) {
        this.responseHeadersSanitizer = requireNonNull(responseHeadersSanitizer, "responseHeadersSanitizer");
        return self();
    }