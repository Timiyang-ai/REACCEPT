public T requestContentSanitizer(Function<Object, Object> requestContentSanitizer) {
        this.requestContentSanitizer = requireNonNull(requestContentSanitizer, "requestContentSanitizer");
        return unsafeCast(this);
    }