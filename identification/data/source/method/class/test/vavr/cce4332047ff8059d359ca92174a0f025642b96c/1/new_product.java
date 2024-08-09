public static <T> Try<T> success(T value) {
        return new Success<>(value);
    }