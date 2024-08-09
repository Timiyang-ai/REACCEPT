    private static <T> Try<T> failure() {
        return Try.failure(new RuntimeException());
    }