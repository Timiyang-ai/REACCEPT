public T responseTrailersSanitizer(
            Function<? super HttpHeaders, ? extends HttpHeaders> responseTrailersSanitizer) {
        this.responseTrailersSanitizer = requireNonNull(responseTrailersSanitizer, "responseTrailersSanitizer");
        return self();
    }