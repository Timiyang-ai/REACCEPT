public static <T> Validator<T> from(Predicate<T> guard,
            String errorMessage) {
        Objects.requireNonNull(guard, "guard cannot be null");
        Objects.requireNonNull(errorMessage, "errorMessage cannot be null");
        return value -> {
            if (guard.test(value)) {
                return Result.ok(value);
            } else {
                return Result.error(errorMessage);
            }
        };
    }