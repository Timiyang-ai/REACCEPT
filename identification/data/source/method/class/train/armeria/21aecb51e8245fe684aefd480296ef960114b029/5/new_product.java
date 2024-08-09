public T requestTrailersSanitizer(Function<? super HttpHeaders, ?> requestTrailersSanitizer) {
        this.requestTrailersSanitizer = requireNonNull(requestTrailersSanitizer, "requestTrailersSanitizer");
        return self();
    }