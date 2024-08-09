public static <T> Validator<T> from(SerializablePredicate<T> guard,
            ErrorMessageProvider errorMessageProvider) {
        Objects.requireNonNull(guard, "guard cannot be null");
        Objects.requireNonNull(errorMessageProvider,
                "errorMessageProvider cannot be null");
        return (value, context) -> {
            try {
                if (guard.test(value)) {
                    return ValidationResult.ok();
                } else {
                    return ValidationResult
                            .error(errorMessageProvider.apply(context));
                }
            } catch (Exception e) {
                return ValidationResult
                        .error(errorMessageProvider.apply(context));
            }
        };
    }