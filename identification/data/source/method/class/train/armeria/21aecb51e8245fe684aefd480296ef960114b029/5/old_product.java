public T requestTrailersSanitizer(
            Function<? super HttpHeaders, ? extends HttpHeaders> requestTrailersSanitizer) {
        this.requestTrailersSanitizer = requireNonNull(requestTrailersSanitizer, "requestTrailersSanitizer");
        return self();
    }