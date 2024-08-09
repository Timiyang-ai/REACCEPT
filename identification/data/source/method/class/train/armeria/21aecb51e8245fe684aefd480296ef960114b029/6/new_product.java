public T responseCauseSanitizer(Function<? super Throwable, ?> responseCauseSanitizer) {
        this.responseCauseSanitizer = requireNonNull(responseCauseSanitizer, "responseCauseSanitizer");
        return self();
    }