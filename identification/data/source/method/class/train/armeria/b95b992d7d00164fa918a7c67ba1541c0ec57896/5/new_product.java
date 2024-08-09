public T requestContentSanitizer(Function<Object, ?> requestContentSanitizer) {
        this.requestContentSanitizer = requireNonNull(requestContentSanitizer, "requestContentSanitizer");
        return self();
    }