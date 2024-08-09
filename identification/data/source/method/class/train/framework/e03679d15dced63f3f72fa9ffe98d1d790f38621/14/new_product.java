public static <T> Validator<T> from(SerializablePredicate<T> guard,
            String errorMessage) {
        Objects.requireNonNull(errorMessage, "errorMessage cannot be null");
        return from(guard, ctx -> errorMessage);
    }