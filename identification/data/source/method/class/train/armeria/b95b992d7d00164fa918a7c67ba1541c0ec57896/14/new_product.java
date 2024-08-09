public T responseContentSanitizer(Function<Object, ?> responseContentSanitizer) {
        this.responseContentSanitizer = requireNonNull(responseContentSanitizer, "responseContentSanitizer");
        return self();
    }