public static <T> Validator<T> from(SerializablePredicate<T> guard,
            ErrorMessageProvider errorMessageProvider, ErrorLevel errorLevel) {
        Objects.requireNonNull(guard, "guard cannot be null");
        Objects.requireNonNull(errorMessageProvider,
                "errorMessageProvider cannot be null");
        Objects.requireNonNull(errorLevel, "errorLevel cannot be null");
        return (value, context) -> {
            try {
                if (guard.test(value)) {
                    return ValidationResult.ok();
                }
                return ValidationResult.create(
                        errorMessageProvider.apply(context), errorLevel);
            } catch (Exception e) {
                return ValidationResult.create(
                        errorMessageProvider.apply(context), errorLevel);
            }
        };
    }