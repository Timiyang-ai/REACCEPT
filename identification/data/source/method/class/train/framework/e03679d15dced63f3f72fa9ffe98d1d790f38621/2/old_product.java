public static <T> Validator<T> from(SerializablePredicate<T> guard,
            String errorMessage) {
        Objects.requireNonNull(guard, "guard cannot be null");
        Objects.requireNonNull(errorMessage, "errorMessage cannot be null");
        return value -> {
            try {
                if (guard.test(value)) {
                    return Result.ok(value);
                } else {
                    return Result.error(errorMessage);
                }
            } catch (Exception e) {
                return Result.error(errorMessage);
            }
        };
    }