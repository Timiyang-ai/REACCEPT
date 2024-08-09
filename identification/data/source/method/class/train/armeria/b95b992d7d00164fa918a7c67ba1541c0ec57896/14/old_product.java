public T responseContentSanitizer(Function<Object, Object> responseContentSanitizer) {
        this.responseContentSanitizer = requireNonNull(responseContentSanitizer, "responseContentSanitizer");
        return unsafeCast(this);
    }