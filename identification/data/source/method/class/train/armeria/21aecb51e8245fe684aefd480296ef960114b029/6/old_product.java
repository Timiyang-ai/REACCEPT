public T responseCauseSanitizer(Function<? super Throwable, ? extends Throwable> responseCauseSanitizer) {
        this.responseCauseSanitizer = requireNonNull(responseCauseSanitizer, "responseCauseSanitizer");
        return self();
    }