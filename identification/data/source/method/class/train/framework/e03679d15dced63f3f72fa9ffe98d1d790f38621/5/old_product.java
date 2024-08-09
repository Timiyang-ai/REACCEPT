public static <T> Validator<T> from(SerializablePredicate<T> guard,
            ErrorMessageProvider errorMessageProvider) {
        Objects.requireNonNull(guard, "guard cannot be null");
        Objects.requireNonNull(errorMessageProvider,
                "errorMessageProvider cannot be null");
        return (value, context) -> {
            try {
                if (guard.test(value)) {
                    return Result.ok(value);
                } else {
                    return Result.error(errorMessageProvider.apply(context));
                }
            } catch (Exception e) {
                return Result.error(errorMessageProvider.apply(context));
            }
        };
    }