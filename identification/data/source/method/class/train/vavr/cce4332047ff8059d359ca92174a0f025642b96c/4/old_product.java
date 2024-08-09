static <T> Try<T> failure(Throwable exception) {
        return new Failure<>(exception);
    }