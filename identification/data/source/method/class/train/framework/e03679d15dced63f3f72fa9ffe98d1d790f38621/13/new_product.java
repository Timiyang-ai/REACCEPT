public static <T> Validator<T> from(SerializablePredicate<T> guard,
            ErrorMessageProvider errorMessageProvider) {
        return from(guard, errorMessageProvider, ErrorLevel.ERROR);
    }