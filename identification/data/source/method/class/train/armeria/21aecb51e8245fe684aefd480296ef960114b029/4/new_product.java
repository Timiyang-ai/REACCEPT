public T responseTrailersSanitizer(Function<? super HttpHeaders, ?> responseTrailersSanitizer) {
        this.responseTrailersSanitizer = requireNonNull(responseTrailersSanitizer, "responseTrailersSanitizer");
        return self();
    }